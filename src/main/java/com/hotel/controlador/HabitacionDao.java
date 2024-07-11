
package com.hotel.controlador;

import com.hotel.modelo.Habitacion;
import java.util.List;


public interface HabitacionDao {
    public List<Habitacion> listar();
    public void registrar(Habitacion habitacion);
    public void actualizar(Habitacion habitacion);
    public void borrar(int id);
    public Habitacion buscarPorId(int id);
    public String traerTipo(int id_tipoHabitacion);
    public void cambiarEstado(int idHabitacion, char esatdo);
}
