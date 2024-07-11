package com.hotel.controlador;

import com.hotel.modelo.Huesped;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuespedDaoImpl implements HuespedDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Huesped> listar() {
        List<Huesped> huespedes = new ArrayList();
        String sql = "SELECT * FROM huesped";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Huesped huesped = new Huesped();
                huesped.setId(rs.getInt("id"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setDocumento(rs.getString("documento"));
                huesped.setTelefono(rs.getString("telefono"));
                huesped.setCorreo(rs.getString("correo"));
                huespedes.add(huesped);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return huespedes;
    }

    @Override
    public void registrar(Huesped huesped) {
        String sql = "INSERT INTO huesped (nombre, documento, telefono, correo) VALUES (?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, huesped.getNombre());
            ps.setString(2, huesped.getDocumento());
            ps.setString(3, huesped.getTelefono());
            ps.setString(4, huesped.getCorreo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Huesped huesped) {
        String sql = "UPDATE huesped SET nombre = ?, documento = ?, telefono = ?, correo = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, huesped.getNombre());
            ps.setString(2, huesped.getDocumento());
            ps.setString(3, huesped.getTelefono());
            ps.setString(4, huesped.getCorreo());
            ps.setInt(5, huesped.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM huesped WHERE id = ?";
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
    public Huesped buscarPorId(int id) {
        Huesped huesped = null;
        String sql = "SELECT * FROM huesped WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                huesped = new Huesped();
                huesped.setId(rs.getInt("id"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setDocumento(rs.getString("documento"));
                huesped.setTelefono(rs.getString("telefono"));
                huesped.setCorreo(rs.getString("correo"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return huesped;
    }

    @Override
    public Huesped BuscarPorDni(String dni) {
         Huesped huesped = null;
        String sql = "SELECT * FROM huesped WHERE documento = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                huesped = new Huesped();
                huesped.setId(rs.getInt("id"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setDocumento(rs.getString("documento"));
                huesped.setTelefono(rs.getString("telefono"));
                huesped.setCorreo(rs.getString("correo"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return huesped; 
    }
}
