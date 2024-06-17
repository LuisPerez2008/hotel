

package com.hotel.controlador;


import com.hotel.modelo.Reserva;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImpl implements ReservaDao{
    
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
}
