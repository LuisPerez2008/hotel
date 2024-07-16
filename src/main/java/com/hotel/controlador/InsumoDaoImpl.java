/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.controlador;

import com.hotel.modelo.Insumos;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsumoDaoImpl implements InsumoDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Insumos> listar() {
        List<Insumos> insumosList = new ArrayList<>();
        String sql = "SELECT * FROM insumos WHERE estado = '1'";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Insumos insumo = new Insumos();
                insumo.setId(rs.getInt("id"));
                insumo.setCodigo(rs.getString("codigo"));
                insumo.setNombre(rs.getString("nombre"));
                insumo.setPrecio(rs.getDouble("precio"));
                insumo.setStock(rs.getInt("stock"));
                insumosList.add(insumo);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return insumosList;

    }

    @Override
    public void registrar(Insumos insumos) {
        String sql = "INSERT INTO insumos (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, insumos.getCodigo());
            ps.setString(2, insumos.getNombre());
            ps.setDouble(3, insumos.getPrecio());
            ps.setInt(4, insumos.getStock());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

    }

    @Override
    public void actualizar(Insumos insumos) {
        String sql = "UPDATE insumos SET codigo = ?, nombre = ?, precio = ?, stock = ? WHERE id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, insumos.getCodigo());
            ps.setString(2, insumos.getNombre());
            ps.setDouble(3, insumos.getPrecio());
            ps.setInt(4, insumos.getStock());
            ps.setInt(5, insumos.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

    }

    @Override
    public void borrar(int id) {
        String sql = "UPDATE insumos SET estado = '2' WHERE id = ?";

        try {

            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        } finally {

            Conexion.cerrar();
        }
    }

    @Override
    public Insumos buscarPorId(int id) {
        Insumos insumo = null;
        String sql = "SELECT * FROM insumos WHERE id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                insumo = new Insumos();
                insumo.setId(rs.getInt("id"));
                insumo.setCodigo(rs.getString("codigo"));
                insumo.setNombre(rs.getString("nombre"));
                insumo.setPrecio(rs.getDouble("precio"));
                insumo.setStock(rs.getInt("stock"));
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return insumo;
    }

    @Override
    public Insumos buscarPorCodigo(String codigo) {
        Insumos insumo = null;
        String sql = "SELECT * FROM insumos WHERE codigo = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                insumo = new Insumos();
                insumo.setId(rs.getInt("id"));
                insumo.setCodigo(rs.getString("codigo"));
                insumo.setNombre(rs.getString("nombre"));
                insumo.setPrecio(rs.getDouble("precio"));
                insumo.setStock(rs.getInt("stock"));
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return insumo;
    }

    @Override
    public void actualizarStok(int id, int stock) {
        String sql = "UPDATE insumos SET stock = ? WHERE id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public List<Insumos> buscar(String nombre) {
        List<Insumos> insumos = new ArrayList<>();
        String sql = "SELECT * FROM insumos WHERE nombre LIKE ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Insumos insumo = new Insumos();
                insumo.setId(rs.getInt("id"));
                insumo.setCodigo(rs.getString("codigo"));
                insumo.setNombre(rs.getString("nombre"));
                insumo.setPrecio(rs.getDouble("precio"));
                insumo.setStock(rs.getInt("stock"));

                insumos.add(insumo);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return insumos; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Integer> productoxCantidad() {
        Map<String, Integer> productos = new HashMap<>();
        String sql = "SELECT i.nombre, SUM(e.cantidad) AS cantidad "
                + "FROM extras e "
                + "JOIN insumos i ON i.id = e.id_insumo "
                + "GROUP BY i.nombre "
                + "ORDER BY cantidad DESC "
                + "LIMIT 5";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                productos.put(nombre, cantidad);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return productos;
    }

    @Override
    public Map<String, Integer> productoxCantidadMenos() {
        Map<String, Integer> productos = new HashMap<>();
        String sql = "SELECT i.nombre, SUM(e.cantidad) AS cantidad "
                + "FROM extras e "
                + "JOIN insumos i ON i.id = e.id_insumo "
                + "GROUP BY i.nombre "
                + "ORDER BY cantidad ASC "
                + "LIMIT 5";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                productos.put(nombreProducto, cantidad);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {

            Conexion.cerrar();
        }

        return productos;
    }
}