package com.udh.guiaturistica.modelo;

public class ResenaItem {
    private int id;
    private String nombreLugar;
    private String usuario;
    private float calificacion;
    private String comentario;

    public ResenaItem(int id, String nombreLugar, String usuario, float calificacion, String comentario) {
        this.id = id;
        this.nombreLugar = nombreLugar;
        this.usuario = usuario;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public ResenaItem(String nombreLugar, String usuario, float calificacion, String comentario) {
        this.nombreLugar = nombreLugar;
        this.usuario = usuario;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }
    public String getNombreLugar() {
        return nombreLugar; }
    public String getUsuario() {
        return usuario; }
    public void setUsuario(String usuario) {
        this.usuario = usuario; }
    public float getCalificacion() { return calificacion; }
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion; }
    public String getComentario() {
        return comentario; }
    public void setComentario(String comentario) {
        this.comentario = comentario; }
}