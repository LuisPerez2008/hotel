
package com.hotel.controlador;

import com.hotel.modelo.Usuario;
import java.util.List;


public interface UsuarioDao {
    public List<Usuario> listar();
    public void registrar(Usuario usuario);
    public void actualizar(Usuario usuario);
    public void borrar(int id);
    public Usuario buscarPorId(int id);
    public Usuario iniciarSesion(String correo, String pass);
}
