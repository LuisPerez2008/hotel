
package com.hotel.modelo;


public class Extra {
    private int id;
    private int id_reserva;
    private int id_insumo;
    private double precio;

    public Extra() {
    }

    public Extra(int id_reserva,int id_insumo, double precio) {
        this.id_reserva = id_reserva;
        this.id_insumo = id_insumo;
        this.precio = precio;
    }

    public Extra(int id, int id_reserva,int id_insumo, double precio) {
        this.id = id;
        this.id_reserva = id_reserva;
        this.id_insumo = id_insumo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }
    
     public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    

    
    
}
