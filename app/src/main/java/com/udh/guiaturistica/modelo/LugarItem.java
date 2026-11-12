package com.udh.guiaturistica.modelo;

public class LugarItem {
    private final String nombre;
    private final String descripcion;
    private final int imagenResId;

    public LugarItem(String nombre, String descripcion, int imagenResId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
