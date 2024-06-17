
package com.hotel.controlador;

import com.hotel.modelo.Reserva;
import java.util.List;

public interface ReservaDao {
    public List<Reserva> listar();
    public void registrar(Reserva reserva);
    public void actualizar(Reserva reserva);
    public void borrar(int id);
    public Reserva buscarPorId(int id);
}
