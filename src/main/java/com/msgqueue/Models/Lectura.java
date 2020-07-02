package com.msgqueue.Models;

public class Lectura {
    private String fecha;
    private int idDispositivo;
    private double temperatura;
    private double humedad;

    public Lectura() {
    }

    public Lectura(String fecha, int idDispositivo, double temperatura, double humedad) {
        this.fecha = fecha;
        this.idDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }
}
