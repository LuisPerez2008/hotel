package com.hotel.controlador;

import com.hotel.modelo.Reserva;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDaoImpl implements ReservaDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList();
        String sql = "SELECT * FROM reserva";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setId_huesped(rs.getInt("id_huesped"));
                reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
                reserva.setFecha_salida(rs.getDate("fecha_salida"));
                reservas.add(reserva);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return reservas;
    }

    @Override
    public void registrar(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_usuario, id_huesped, fecha_reserva, fecha_salida) VALUES (?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_huesped());
            ps.setDate(3, new java.sql.Date(reserva.getFecha_reserva().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFecha_salida().getTime()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET id_usuario = ?, id_huesped = ?, fecha_reserva = ?, fecha_salida = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_huesped());
            ps.setDate(3, new java.sql.Date(reserva.getFecha_reserva().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFecha_salida().getTime()));
            ps.setInt(5, reserva.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public Reserva buscarPorId(int id) {
        Reserva reserva = null;
        String sql = "SELECT * FROM reserva WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setId_huesped(rs.getInt("id_huesped"));
                reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
                reserva.setFecha_salida(rs.getDate("fecha_salida"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return reserva;
    }

    @Override
    public Reserva ultimaReserva() {
        Reserva reserva = null;
        String sql = "SELECT * FROM reserva\n"
                + "ORDER BY id DESC\n"
                + "LIMIT 1";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setId_huesped(rs.getInt("id_huesped"));
                reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
                reserva.setFecha_salida(rs.getDate("fecha_salida"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return reserva;
    }

    @Override
    public Date obtenerfechaSalida(int idDetalle) {
        Date fechaSalida = null;
        String sql = "SELECT r.fecha_salida "
                + "FROM detalle d "
                + "INNER JOIN reserva r ON d.id_reserva = r.id "
                + "INNER JOIN habitacion h ON d.id_habitacion = h.id "
                + "WHERE d.id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idDetalle); // Establecer el parámetro en la consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaSalida = rs.getDate("fecha_salida");
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return fechaSalida;
    }

    @Override
    public Date obtenerfechaEntrada(int idDetalle) {
        Date fechaEntrada = null;
        String sql = "SELECT r.fecha_reserva "
                + "FROM detalle d "
                + "INNER JOIN reserva r ON d.id_reserva = r.id "
                + "INNER JOIN habitacion h ON d.id_habitacion = h.id "
                + "WHERE d.id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idDetalle); // Establecer el parámetro en la consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                fechaEntrada = rs.getDate("fecha_reserva");
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return fechaEntrada; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String obtenerNombreHuesped(int idReserva) {
        String nombreHuesped = null;
        String sql = "SELECT h.nombre FROM huesped h "
                + "JOIN reserva r ON h.id = r.id_huesped "
                + "WHERE r.id_huesped = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idReserva);
            rs = ps.executeQuery();

            if (rs.next()) {
                nombreHuesped = rs.getString("nombre");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return nombreHuesped;
    }

    @Override
    public int obtenerIdDetalleXreserva(int idReserva) {
        int idDetalle = -1; // Valor predeterminado para indicar que no se encontró un detalle
        String sql = "SELECT d.id FROM reserva r JOIN detalle d ON r.id = d.id_reserva WHERE r.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idReserva);
            rs = ps.executeQuery();

            if (rs.next()) {
                idDetalle = rs.getInt("id");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return idDetalle;

    }
}
