package com.udh.guiaturistica.modelo;

import java.util.ArrayList;

public class Repositorio {

    private static final ArrayList<Usuario> usuarios = new ArrayList<>();

    public Repositorio(){
        if (usuarios.isEmpty()){
            usuarios.add(new Usuario("Turista de prueba", "prueba1@guiaturistico.com","turista1"));
        }
    }

    public Usuario autenticar(String correo, String clave){
        for (Usuario u : usuarios){
            if (u.getCorreo().equalsIgnoreCase(correo) && u.getClave().equals(clave)){
                return u;
            }
        }
        return null;
    }

    public boolean correoRegistrado(String correo){
        for (Usuario u : usuarios){
            if (u.getCorreo().equalsIgnoreCase(correo)){
                return true;
            }
        }
        return false;
    }
    public Usuario registrarUsuario(String nombre,String correo, String clave){
        Usuario nuevo = new Usuario(nombre,correo,clave);
        usuarios.add(nuevo);
        return nuevo;
    }
}
