
package com.hotel.controlador;

import com.hotel.modelo.Insumos;
import java.util.List;


public interface InsumoDao {
    public List<Insumos> listar();
    public void registrar(Insumos insumos);
    public void actualizar(Insumos insumos);
    public void borrar(int id);
    public Insumos buscarPorId(int id);
    public Insumos buscarPorCodigo(String codigo);
    
    public void actualizarStok(int id , int stock);
    
    public List<Insumos> buscar(String nombre);
    
    
}
