
package com.hotel.controlador;

import com.hotel.modelo.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface UsuarioDao {
    public List<Usuario> listar();
    public void registrar(Usuario usuario);
    public void actualizar(Usuario usuario);
    public void borrar(int id);
    public Usuario buscarPorId(int id);
    public Usuario iniciarSesion(String correo, String pass);
    public String buscarNombreUsuarioXidDetalle(int idDetalle);
    public List<Usuario> BuscarPorNombre(String nombre);
    
    //para reportes 
    
    public Map<String, Integer> reporteCantidadDeregistrosXusuario() ;
    
    public List<HashMap<String, Object>>  buscarRegistroXUsuario(String nombre) ;
    
}
