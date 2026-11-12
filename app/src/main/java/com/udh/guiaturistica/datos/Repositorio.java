package com.udh.guiaturistica.datos;

import com.udh.guiaturistica.modelo.Lugar;
import com.udh.guiaturistica.modelo.Resena;
import com.udh.guiaturistica.modelo.Usuario;

import java.util.ArrayList;

public class Repositorio {

    private static final ArrayList<Usuario> usuarios = new ArrayList<>();
    private static final ArrayList<Lugar> lugares = new ArrayList<>();
    private static final ArrayList<Resena> resenas = new ArrayList<>();
    private static int siguienteIdLugar = 1;
    private static int siguienteIdResena = 1;

    public Repositorio() {
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("Turista de prueba", "prueba1@guiaturistico.com", "turista1"));
        }
        if (lugares.isEmpty()) {
            lugares.add(new Lugar(siguienteIdLugar++, "Kotosh",
                    "Templo de las Manos Cruzadas, uno de los centros ceremoniales más antiguos de América.",
                    "Cultural", 8, 17,"R.drawable.kotosh"));
            lugares.add(new Lugar(siguienteIdLugar++, "Cueva de las Lechuzas",
                    "Espectacular cueva habitada por guácharos y murciélagos en el Parque Nacional Tingo María.",
                    "Natural", 6, 18, ""));
            lugares.add(new Lugar(siguienteIdLugar++, "Puente Calicanto",
                    "Histórico puente construido sobre el río Huallaga a base de piedra, cal y clara de huevo.",
                    "Cultural", 0, 24, ""));
        }
    }

    public Usuario autenticar(String correo, String clave) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo) && u.getClave().equals(clave)) {
                return u;
            }
        }
        return null;
    }

    public boolean correoRegistrado(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    public Usuario registrarUsuario(String nombre, String correo, String clave) {
        Usuario nuevo = new Usuario(nombre, correo, clave);
        usuarios.add(nuevo);
        return nuevo;
    }

    public void insertarLugar(Lugar lugar) {
        if (lugar.getIdLugar() == 0) {
            Lugar nuevoLugar = new Lugar(
                    siguienteIdLugar++,
                    lugar.getNombre(),
                    lugar.getDescripcion(),
                    lugar.getTipo(),
                    lugar.getHoraApertura(),
                    lugar.getHoraCierre(),
                    lugar.getFoto()
            );
            nuevoLugar.setPromedio(lugar.getPromedio());
            lugares.add(nuevoLugar);
        } else {
            lugares.add(lugar);
        }
    }

    public Lugar obtenerLugar(int idLugar) {
        for (Lugar l : lugares) {
            if (l.getIdLugar() == idLugar) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<Lugar> listarLugares() {
        return new ArrayList<>(lugares);
    }

    public void insertarResena(Resena resena) {
        if (resena.getIdResena() == 0) {
            Resena nuevaResena = new Resena(
                    siguienteIdResena++,
                    resena.getIdLugar(),
                    resena.getNombreUsuario(),
                    resena.getCalificacion(),
                    resena.getComentario(),
                    resena.getRecomendacion()
            );
            resenas.add(nuevaResena);
        } else {
            resenas.add(resena);
        }
        actualizarPromedio(resena.getIdLugar());
    }

    public ArrayList<Resena> listarResenas(int idLugar) {
        ArrayList<Resena> filtradas = new ArrayList<>();
        for (Resena r : resenas) {
            if (r.getIdLugar() == idLugar) {
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    private void actualizarPromedio(int idLugar) {
        Lugar lugar = obtenerLugar(idLugar);
        if (lugar == null) return;

        ArrayList<Resena> resenasLugar = listarResenas(idLugar);
        if (resenasLugar.isEmpty()) {
            lugar.setPromedio(0.0);
            return;
        }

        double suma = 0;
        for (Resena r : resenasLugar) {
            suma += r.getCalificacion();
        }
        lugar.setPromedio(suma / resenasLugar.size());
    }
}
