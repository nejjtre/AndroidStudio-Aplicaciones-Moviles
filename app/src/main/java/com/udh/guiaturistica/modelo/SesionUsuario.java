package com.udh.guiaturistica.modelo;

public class SesionUsuario {

    private static Usuario usuarioActual;

    private SesionUsuario(){
    }

    public static void iniciar(Usuario usuario){
        usuarioActual = usuario;
    }

    public static void cerrar(){
        usuarioActual = null;
    }

    public static Usuario getUsuario(){
        return usuarioActual;
    }

    public static boolean haySesion(){
        return usuarioActual != null;
    }
}
