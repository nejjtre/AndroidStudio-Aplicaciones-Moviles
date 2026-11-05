package com.udh.guiaturistica.modelo;

import java.util.Locale;

public class Lugar {

    private int idLugar;
    private String nombre;
    private String descripcion;
    private String tipo;
    private int horaApertura;
    private int horaCierre;
    private String foto;
    private double promedio;

    public Lugar(int idLugar, String nombre, String descripcion, String tipo, int horaApertura, int horaCierre, String foto) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.foto = foto;
    }

    public int getIdLugar() {
        return idLugar; }
    public String getNombre() {
        return nombre; }
    public String getDescripcion() {
        return descripcion; }
    public String getTipo() {
        return tipo; }
    public int getHoraApertura() {
        return horaApertura; }
    public int getHoraCierre() {
        return horaCierre; }
    public String getFoto() {
        return foto; }
    public double getPromedio() {
        return promedio; }
    public void setPromedio(double promedio) {
        this.promedio = promedio; }

    public String getHorario() {
        return String.format(Locale.getDefault(), "%02d:00 - %02d:00", horaApertura, horaCierre);
    }

    @Override
    public String toString() {
        return nombre + "\n" + tipo + "  |  " + getHorario() + "  |  "
                + String.format(Locale.getDefault(), "%.1f", promedio) + " ★";
    }
}