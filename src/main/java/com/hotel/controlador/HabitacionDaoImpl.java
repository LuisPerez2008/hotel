
package com.hotel.controlador;


import com.hotel.modelo.Habitacion;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements HabitacionDao{
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
      @Override
    public List<Habitacion> listar() {
        List<Habitacion> habitaciones = new ArrayList();
        String sql = "SELECT * FROM habitacion";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setId(rs.getInt("id"));
                habitacion.setNumero(rs.getString("numero"));
                habitacion.setId_tipo(rs.getInt("id_tipo"));
                habitacion.setPiso(rs.getString("piso"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitacion.setEstado(rs.getString("estado").charAt(0));
                habitaciones.add(habitacion);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return habitaciones;
    }

    @Override
    public void registrar(Habitacion habitacion) {
        String sql = "INSERT INTO habitacion (numero, id_tipo, piso, precio, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, habitacion.getNumero());
            ps.setInt(2, habitacion.getId_tipo());
            ps.setString(3, habitacion.getPiso());
            ps.setDouble(4, habitacion.getPrecio());
            ps.setString(5, String.valueOf(habitacion.getEstado()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Habitacion habitacion) {
        String sql = "UPDATE habitacion SET numero = ?, id_tipo = ?, piso = ?, precio = ?, estado = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, habitacion.getNumero());
            ps.setInt(2, habitacion.getId_tipo());
            ps.setString(3, habitacion.getPiso());
            ps.setDouble(4, habitacion.getPrecio());
            ps.setString(5, String.valueOf(habitacion.getEstado()));
            ps.setInt(6, habitacion.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM habitacion WHERE id = ?";
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
    public Habitacion buscarPorId(int id) {
        Habitacion habitacion = null;
        String sql = "SELECT * FROM habitacion WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                habitacion = new Habitacion();
                habitacion.setId(rs.getInt("id"));
                habitacion.setNumero(rs.getString("numero"));
                habitacion.setId_tipo(rs.getInt("id_tipo"));
                habitacion.setPiso(rs.getString("piso"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitacion.setEstado(rs.getString("estado").charAt(0));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return habitacion;
    }
}
