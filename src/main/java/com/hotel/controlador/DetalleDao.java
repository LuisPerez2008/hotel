
package com.hotel.controlador;

import com.hotel.modelo.Detalle;
import java.util.List;

public interface DetalleDao {
    public List<Detalle> listar();
    public void registrar(Detalle detalle);
    public void actualizar(Detalle detalle);
    public void borrar(int id);
    public Detalle buscarPorId(int id);
}
