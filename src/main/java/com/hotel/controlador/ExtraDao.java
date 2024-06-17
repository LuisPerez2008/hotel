
package com.hotel.controlador;

import com.hotel.modelo.Extra;
import java.util.List;


public interface ExtraDao {
    public List<Extra> listar();
    public void registrar(Extra extra);
    public void actualizar(Extra extra);
    public void borrar(int id);
    public Extra buscarPorId(int id);
}
