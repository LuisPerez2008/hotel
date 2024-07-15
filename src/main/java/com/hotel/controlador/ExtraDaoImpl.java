package com.hotel.controlador;

import com.hotel.modelo.Extra;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtraDaoImpl implements ExtraDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Extra> listar() {
        List<Extra> extras = new ArrayList();
        String sql = "SELECT * FROM extras";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Extra extra = new Extra();
                extra.setId(rs.getInt("id"));
                extra.setId_reserva(rs.getInt("id_reserva"));
                extra.setId_insumo(rs.getInt("id_insumo"));
                extra.setPrecio(rs.getDouble("precio"));
                extra.setPrecio(rs.getDouble("cantidad"));
                extras.add(extra);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return extras;
    }

    @Override
    public void registrar(Extra extra) {
        String sql = "INSERT INTO extras (id_reserva, id_insumo, precio, cantidad) VALUES (?, ?, ?,?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, extra.getId_reserva());
            ps.setInt(2, extra.getId_insumo());
            ps.setDouble(3, extra.getPrecio());
            ps.setDouble(4, extra.getCantidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Extra extra) {
        String sql = "UPDATE extras SET id_reserva = ?, id_insumo = ?, precio = ?, cantidad = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, extra.getId_reserva());
            ps.setInt(2, extra.getId_insumo());
            ps.setDouble(3, extra.getPrecio());
            ps.setDouble(4, extra.getCantidad());
            ps.setInt(5, extra.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM extras WHERE id = ?";
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
    public Extra buscarPorId(int id) {
        Extra extra = null;
        String sql = "SELECT * FROM extras WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                extra = new Extra();
                extra.setId(rs.getInt("id"));
                extra.setId_reserva(rs.getInt("id_reserva"));
                extra.setId_insumo(rs.getInt("id_insumo"));
                extra.setPrecio(rs.getDouble("precio"));
                extra.setPrecio(rs.getDouble("cantidad"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return extra;
    }

    @Override
    public int obtenerIdReservaXidHbaitacion(int id) {
        int idReserva = -1;
        String sql = "SELECT MAX(r.id) AS id "
                + "FROM reserva r "
                + "JOIN detalle d ON d.id_reserva = r.id "
                + "JOIN habitacion h ON d.id_habitacion = h.id "
                + "WHERE h.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                idReserva = rs.getInt("id");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return idReserva;
    }

    @Override
    public double sumaDeextrasXdetalle(int id) {
        double suma = 0.0;
        String sql = "SELECT COALESCE(SUM(e.precio), 0) FROM extras e " +
                 "JOIN reserva r ON r.id = e.id_reserva " +
                 "JOIN detalle d ON d.id_reserva = r.id " +
                 "WHERE d.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                suma = rs.getDouble(1); // Obtener la suma del primer campo
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return suma;
    }

}
