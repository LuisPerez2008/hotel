
package com.hotel.modelo;


public class Detalle {
    private int id ;
    private int id_habitacion;
    private int id_reserva;
    private int id_metodoPago;
    private double precioTotal;

    public Detalle(int id, int id_habitacion, int id_reserva, int id_metodoPago, double precioTotal) {
        this.id = id;
        this.id_habitacion = id_habitacion;
        this.id_reserva = id_reserva;
        this.id_metodoPago = id_metodoPago;
        this.precioTotal = precioTotal;
    }

    public Detalle(int id_habitacion, int id_reserva, int id_metodoPago, double precioTotal) {
        this.id_habitacion = id_habitacion;
        this.id_reserva = id_reserva;
        this.id_metodoPago = id_metodoPago;
        this.precioTotal = precioTotal;
    }

    public Detalle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_metodoPago() {
        return id_metodoPago;
    }

    public void setId_metodoPago(int id_metodoPago) {
        this.id_metodoPago = id_metodoPago;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
