
package com.hotel.modelo;

import java.util.Date;

public class Reserva {
    private int id;
    private int id_usuario;
    private int id_huesped;
    private Date fecha_reserva;
    private Date fecha_salida;

    public Reserva(int id, int id_usuario, int id_huesped, Date fecha_reserva, Date fecha_salida) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_huesped = id_huesped;
        this.fecha_reserva = fecha_reserva;
        this.fecha_salida = fecha_salida;
    }

    public Reserva(int id_usuario, int id_huesped, Date fecha_reserva, Date fecha_salida) {
        this.id_usuario = id_usuario;
        this.id_huesped = id_huesped;
        this.fecha_reserva = fecha_reserva;
        this.fecha_salida = fecha_salida;
    }

    public Reserva() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    
    
           
}
