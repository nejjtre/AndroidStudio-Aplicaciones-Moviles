package com.udh.guiaturistica.datos;

import com.udh.guiaturistica.R;
import com.udh.guiaturistica.modelo.Lugar;
import com.udh.guiaturistica.modelo.Resena;
import java.util.ArrayList;

public class Repositorio {

    private static final ArrayList<Lugar> lugares = new ArrayList<>();
    private static final ArrayList<Resena> resenas = new ArrayList<>();
    private static int ultimoId = 0;

    public Repositorio() {
        if (lugares.isEmpty()) {
            cargarDatosEjemplo();
        }
    }

    private void cargarDatosEjemplo() {
        lugares.add(new Lugar(nuevoId(), "Templo de Kotosh",
                "Sitio arqueológico del Templo de las Manos Cruzadas.", "Cultural", 8, 17,
                foto(R.drawable.kotosh)));
        lugares.add(new Lugar(nuevoId(), "Puente Calicanto",
                "Puente de piedra sobre el río Huallaga.", "Cultural", 0, 24,
                foto(R.drawable.puente_calicanto)));
        lugares.add(new Lugar(nuevoId(), "Mercado Modelo",
                "Platos típicos de la región a buen precio.", "Gastronómico", 6, 18,
                foto(R.drawable.mercado_modelo_huanuco)));
        lugares.add(new Lugar(nuevoId(), "Cueva de las Lechuzas",
                "Cueva natural en el Parque Nacional Tingo María.", "Natural", 8, 17,
                foto(R.drawable.cueva_de_las_lechuzas)));
    }

    private String foto(int idImagen) {
        return "android.resource://com.udh.guiaturistica/" + idImagen;
    }

    private int nuevoId() {
        ultimoId++;
        return ultimoId;
    }


    public int insertarLugar(Lugar lugar) {
        Lugar nuevo = new Lugar(nuevoId(), lugar.getNombre(), lugar.getDescripcion(), lugar.getTipo(),
                lugar.getHoraApertura(), lugar.getHoraCierre(), lugar.getFoto());
        lugares.add(nuevo);
        return nuevo.getIdLugar();
    }
    public ArrayList<Lugar> listarLugares(String tipo, int hora) {
        ArrayList<Lugar> resultado = new ArrayList<>();
        for (Lugar l : lugares) {
            if (!tipo.equals("Todos") && !l.getTipo().equals(tipo)) {
                continue;
            }
            if (hora >= 0 && (hora < l.getHoraApertura() || hora >= l.getHoraCierre())) {
                continue;
            }
            l.setPromedio(calcularPromedio(l.getIdLugar()));
            resultado.add(l);
        }
        return resultado;
    }

    public Lugar obtenerLugar(int idLugar) {
        for (Lugar l : lugares) {
            if (l.getIdLugar() == idLugar) {
                l.setPromedio(calcularPromedio(idLugar));
                return l;
            }
        }
        return null;
    }

    public int insertarResena(Resena resena) {
        Resena nueva = new Resena(nuevoId(), resena.getIdLugar(), resena.getNombreUsuario(),
                resena.getCalificacion(), resena.getComentario(), resena.getRecomendacion());
        resenas.add(nueva);
        return nueva.getIdResena();
    }

    public ArrayList<Resena> listarResenas(int idLugar) {
        ArrayList<Resena> resultado = new ArrayList<>();
        for (int i = resenas.size() - 1; i >= 0; i--) {
            if (resenas.get(i).getIdLugar() == idLugar) {
                resultado.add(resenas.get(i));
            }
        }
        return resultado;
    }

    private double calcularPromedio(int idLugar) {
        int suma = 0;
        int cantidad = 0;
        for (Resena r : resenas) {
            if (r.getIdLugar() == idLugar) {
                suma += r.getCalificacion();
                cantidad++;
            }
        }
        if (cantidad == 0) {
            return 0;
        }
        return (double) suma / cantidad;
    }
}