
package com.hotel.controlador;

import com.hotel.modelo.Detalle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DetalleDao {
    public List<Detalle> listar();
    public void registrar(Detalle detalle);
    public void actualizar(Detalle detalle);
    public void borrar(int id);
    public Detalle buscarPorId(int id);
    public int buscarPoridHbaitacion(int idHabitacion);
    public List<HashMap<String, Object>> listarDetalle();
    public void actualizarPrecio (int idDetalle, double precio);
    public void convertSaleToPDF(int idDetalle ,String nomUusario, String fecharegistro ,int idcliente  , int idregistro , double total , String tipo_pago);

    //Resporte
    
    public double GanaciaUltimoMes();
    public double GanaciaProductoUltimoMes();
    public Map<String, Integer> reservasUltimos30Dias();
    public Map<String, Integer> cantMetodoPago();
    public List<HashMap<String, Object>> listarDetalleReporte(int id);
    public List<HashMap<String, Object>> listarExtrasReporte(int id);
}
