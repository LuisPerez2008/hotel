
package com.hotel.controlador;

import com.hotel.modelo.Extra;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtraDaoImpl implements ExtraDao{
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
      @Override
    public List<Extra> listar() {
        List<Extra> extras = new ArrayList();
        String sql = "SELECT * FROM extra";
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
        String sql = "INSERT INTO extra (id_reserva, id_insumo, precio) VALUES (?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, extra.getId_reserva());
            ps.setInt(2, extra.getId_insumo());
            ps.setDouble(3, extra.getPrecio());
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
        String sql = "UPDATE extra SET id_reserva = ?, id_insumo = ?, precio = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, extra.getId_reserva());
            ps.setInt(2, extra.getId_insumo());
            ps.setDouble(3, extra.getPrecio());
            ps.setInt(4, extra.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM extra WHERE id = ?";
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
        String sql = "SELECT * FROM extra WHERE id = ?";
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
    
}
