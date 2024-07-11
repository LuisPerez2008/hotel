
package com.hotel.controlador;

import com.hotel.modelo.Huesped;
import java.util.List;


public interface HuespedDao {
    public List<Huesped> listar();
    public void registrar(Huesped huesped);
    public void actualizar(Huesped huesped);
    public void borrar(int id);
    public Huesped buscarPorId(int id);
    public Huesped BuscarPorDni(String dni);
}
