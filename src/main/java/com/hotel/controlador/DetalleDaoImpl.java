
package com.hotel.controlador;

import com.hotel.modelo.Detalle;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DetalleDaoImpl implements DetalleDao{
   
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
        String sql = "INSERT INTO detalle (id_habitacion, id_reserva, id_metodoPago, precioTotal) VALUES (?, ?, ?, ?)";
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
        String sql = "UPDATE detalle SET id_habitacion = ?, id_reserva = ?, id_metodoPago = ?, precioTotal = ? WHERE id = ?";
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
                detalle.setPrecioTotal(rs.getDouble("precioTotal"));
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
}
