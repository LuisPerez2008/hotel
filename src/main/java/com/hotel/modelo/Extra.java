
package com.hotel.modelo;


public class Extra {
    private int id;
    private int id_reserva;
    private String descripcion;
    private double precio;

    public Extra(int id, int id_reserva, String descripcion, double precio) {
        this.id = id;
        this.id_reserva = id_reserva;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Extra(int id_reserva, String descripcion, double precio) {
        this.id_reserva = id_reserva;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Extra() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
