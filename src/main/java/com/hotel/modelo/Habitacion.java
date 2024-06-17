
package com.hotel.modelo;

public class Habitacion {
    private int id;
    private String numero;
    private int id_tipo;
    private String piso;
    private double precio;
    private char estado;

    public Habitacion(int id, String numero, int id_tipo, String piso, double precio, char estado) {
        this.id = id;
        this.numero = numero;
        this.id_tipo = id_tipo;
        this.piso = piso;
        this.precio = precio;
        this.estado = estado;
    }

    public Habitacion(String numero, int id_tipo, String piso, double precio, char estado) {
        this.numero = numero;
        this.id_tipo = id_tipo;
        this.piso = piso;
        this.precio = precio;
        this.estado = estado;
    }

    public Habitacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    
}
