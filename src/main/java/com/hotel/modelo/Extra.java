
package com.hotel.modelo;


public class Extra {
    private int id;
    private int id_reserva;
    private int id_insumo;
    private double precio;
    private double cantidad;

    public Extra() {
    }

    public Extra(int id_reserva,int id_insumo, double precio,double cantidad) {
        this.id_reserva = id_reserva;
        this.id_insumo = id_insumo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Extra(int id, int id_reserva,int id_insumo, double precio, double cantidad) {
        this.id = id;
        this.id_reserva = id_reserva;
        this.id_insumo = id_insumo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
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
