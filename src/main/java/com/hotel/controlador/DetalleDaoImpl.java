package com.hotel.controlador;

import com.hotel.modelo.Detalle;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetalleDaoImpl implements DetalleDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Detalle> listar() {
        List<Detalle> detalles = new ArrayList();
        String sql = "SELECT * FROM detalle";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setId(rs.getInt("id"));
                detalle.setId_habitacion(rs.getInt("id_habitacion"));
                detalle.setId_reserva(rs.getInt("id_reserva"));
                detalle.setId_metodoPago(rs.getInt("id_metodoPago"));
                detalle.setPrecioTotal(rs.getDouble("precioTotal"));
                detalles.add(detalle);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return detalles;
    }

    @Override
    public void registrar(Detalle detalle) {
        String sql = "INSERT INTO detalle (id_habitacion, id_reserva, id_metodoPago, precio_total) VALUES (?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, detalle.getId_habitacion());
            ps.setInt(2, detalle.getId_reserva());
            ps.setInt(3, detalle.getId_metodoPago());
            ps.setDouble(4, detalle.getPrecioTotal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Detalle detalle) {
        String sql = "UPDATE detalle SET id_habitacion = ?, id_reserva = ?, id_metodoPago = ?, precio_total = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, detalle.getId_habitacion());
            ps.setInt(2, detalle.getId_reserva());
            ps.setInt(3, detalle.getId_metodoPago());
            ps.setDouble(4, detalle.getPrecioTotal());
            ps.setInt(5, detalle.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM detalle WHERE id = ?";
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
    public Detalle buscarPorId(int id) {
        Detalle detalle = null;
        String sql = "SELECT * FROM detalle WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                detalle = new Detalle();
                detalle.setId(rs.getInt("id"));
                detalle.setId_habitacion(rs.getInt("id_habitacion"));
                detalle.setId_reserva(rs.getInt("id_reserva"));
                detalle.setId_metodoPago(rs.getInt("id_metodoPago"));
                detalle.setPrecioTotal(rs.getDouble("precio_total"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return detalle;
    }

    @Override
    public int buscarPoridHbaitacion(int idHabitacion) {
        int idDetalle = -1; // Valor por defecto si no se encuentra un registro
        String sql = "SELECT id FROM detalle WHERE id_habitacion = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idHabitacion); // Establecer el parámetro en la consulta
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

    @Override
    public List<HashMap<String, Object>> listarDetalle() {
        String sql = "SELECT d.id, hab.numero AS \"N° habitacion\", h.nombre AS Cliente, r.fecha_reserva, r.fecha_salida, d.precio_total "
                + "FROM detalle d "
                + "JOIN reserva r ON d.id_reserva = r.id "
                + "JOIN huesped h ON r.id_huesped = h.id "
                + "JOIN habitacion hab ON d.id_habitacion = hab.id";

        List<HashMap<String, Object>> lista = new ArrayList<>();
        try {
            conn = Conexion.conectar(); // Establecer la conexión
            ps = conn.prepareStatement(sql); // Preparar la consulta
            rs = ps.executeQuery(); // Ejecutar la consulta
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<>();

                map.put("id", rs.getInt("id"));
                map.put("N° habitacion", rs.getString("N° habitacion"));
                map.put("Cliente", rs.getString("Cliente"));
                map.put("fecha_reserva", rs.getDate("fecha_reserva"));
                map.put("fecha_salida", rs.getDate("fecha_salida"));
                map.put("precio_total", rs.getBigDecimal("precio_total"));

                lista.add(map);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cerrar ResultSet
                }
                if (ps != null) {
                    ps.close(); // Cerrar PreparedStatement
                }
                if (conn != null) {
                    Conexion.cerrar(); // Cerrar conexión
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
        return lista;

    }
}
