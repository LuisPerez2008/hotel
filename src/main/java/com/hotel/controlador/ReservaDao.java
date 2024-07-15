
package com.hotel.controlador;

import com.hotel.modelo.Reserva;
import java.util.Date;
import java.util.List;

public interface ReservaDao {
    public List<Reserva> listar();
    public void registrar(Reserva reserva);
    public void actualizar(Reserva reserva);
    public void borrar(int id);
    public Reserva buscarPorId(int id);
    public Reserva ultimaReserva();
    public Date obtenerfechaSalida(int idDetalle);
    public Date obtenerfechaEntrada(int idDetalle);
    public String obtenerNombreHuesped(int idReserva);
    public int obtenerIdDetalleXreserva(int idReserva);
}
