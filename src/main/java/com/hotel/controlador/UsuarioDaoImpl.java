package com.hotel.controlador;

import com.hotel.modelo.Usuario;
import com.hotel.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDaoImpl implements UsuarioDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList();
        String sql = "SELECT * FROM usuario WHERE estado = 1";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return usuarios;
    }

    @Override
    public void registrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, correo, pass, rol) VALUES (?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPass());
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, correo = ?, pass = ?, rol = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPass());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "UPDATE usuario SET estado = '2' WHERE id = ?";

        try {

            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        } finally {
            // Cerrar la conexión
            Conexion.cerrar();
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setRol(rs.getString("rol"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return usuario;
    }

    @Override
    public Usuario iniciarSesion(String correo, String pass) {
        Usuario user = new Usuario();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND pass=?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setCorreo(rs.getString("correo"));
                user.setPass(rs.getString("pass"));
                user.setRol(rs.getString("rol"));
                return user;

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public String buscarNombreUsuarioXidDetalle(int idDetalle) {
        String nombreUsuario = null;
        String sql = "SELECT u.nombre FROM detalle d "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN usuario u ON u.id = r.id_usuario "
                + "WHERE d.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idDetalle); // Establecer el parámetro en la consulta
            rs = ps.executeQuery();

            if (rs.next()) {
                nombreUsuario = rs.getString("nombre");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return nombreUsuario;
    }

    @Override
    public List<Usuario> BuscarPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nombre LIKE ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setCorreo(rs.getString("correo"));
                user.setRol(rs.getString("rol"));

                usuarios.add(user);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return usuarios;
    }

    @Override
    public Map<String, Integer> reporteCantidadDeregistrosXusuario() {
        Map<String, Integer> registrosPorUsuario = new HashMap<>();
        String sql = "SELECT u.nombre, COUNT(u.id) AS cantidad FROM detalle d "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN usuario u ON u.id = r.id_usuario "
                + "GROUP BY u.nombre";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                registrosPorUsuario.put(nombreUsuario, cantidad);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return registrosPorUsuario;
    }

    @Override
    public List<HashMap<String, Object>> buscarRegistroXUsuario(String nombre) {
        List<HashMap<String, Object>> registros = new ArrayList<>();
        String sql = "SELECT d.id, h.nombre, d.precio_total "
                + "FROM detalle d "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN usuario u ON u.id = r.id_usuario "
                + "JOIN huesped h ON h.id = r.id_huesped "
                + "WHERE u.nombre = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre); // Establecer el parámetro en la consulta
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> registro = new HashMap<>();
                registro.put("id", rs.getInt("id"));
                registro.put("nombre", rs.getString("nombre"));
                registro.put("precio_total", rs.getDouble("precio_total"));
                registros.add(registro);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return registros;
    }

}
