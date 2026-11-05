package com.udh.guiaturistica.modelo;

public class Resena {

    private int idResena;
    private int idLugar;
    private String nombreUsuario;
    private int calificacion;
    private String comentario;
    private String recomendacion;

    public Resena(int idResena, int idLugar, String nombreUsuario, int calificacion, String comentario, String recomendacion) {
        this.idResena = idResena;
        this.idLugar = idLugar;
        this.nombreUsuario = nombreUsuario;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.recomendacion = recomendacion;
    }

    public int getIdResena() {
        return idResena; }
    public int getIdLugar() {
        return idLugar; }
    public String getNombreUsuario() {
        return nombreUsuario; }
    public int getCalificacion() {
        return calificacion; }
    public String getComentario() {
        return comentario; }
    public String getRecomendacion() {
        return recomendacion; }

    @Override
    public String toString() {
        return nombreUsuario + "  -  " + calificacion + " ★\n"
                + comentario + "\nRecomienda: " + recomendacion;
    }
}