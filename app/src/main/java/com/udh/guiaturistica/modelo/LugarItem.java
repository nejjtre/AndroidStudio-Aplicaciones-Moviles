package com.udh.guiaturistica.modelo;

import java.util.Locale;

public class LugarItem {
    private int id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private int horaApertura;
    private int horaCierre;
    private boolean es24Horas;
    private int imagenResId;

    public LugarItem(int id, String nombre, String descripcion, String tipo, int horaApertura, int horaCierre, boolean es24Horas, int imagenResId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.es24Horas = es24Horas;
        this.imagenResId = imagenResId;
    }

    public LugarItem(String nombre, String descripcion, String tipo, int horaApertura, int horaCierre, boolean es24Horas, int imagenResId) {
        this(0, nombre, descripcion, tipo, horaApertura, horaCierre, es24Horas, imagenResId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    public boolean isEs24Horas() {
        return es24Horas;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public String getHorarioTexto() {
        if (es24Horas) {
            return "Horario: Abierto las 24 Horas";
        }
        return String.format(Locale.getDefault(), "Horario: %02d:00 - %02d:00", horaApertura, horaCierre);
    }

    public boolean estaAbiertoAhora(int horaActual) {
        if (es24Horas) return true;
        if (horaCierre > horaApertura) {
            return horaActual >= horaApertura && horaActual < horaCierre;
        } else {
            return horaActual >= horaApertura || horaActual < horaCierre;
        }
    }
}
