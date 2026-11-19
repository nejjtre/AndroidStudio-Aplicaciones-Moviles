package com.udh.guiaturistica.negocio;

import com.udh.guiaturistica.modelo.ResenaItem;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ResenaService {

    private static final List<String> PALABRAS_PROHIBIDAS = Arrays.asList("spam", "estafa", "basura", "tonto");

    public String validarResena(String usuario, float calificacion, String comentario,
                                List<ResenaItem> resenasExistentes, boolean esEdicion, int idActual) {

        if (calificacion <= 0 || calificacion > 5) {
            return "Regla de negocio: Debe seleccionar entre 1 y 5 estrellas.";
        }
        if (usuario == null || usuario.trim().length() < 3) {
            return "Regla de negocio: El nombre debe tener al menos 3 caracteres.";
        }
        if (comentario == null || comentario.trim().length() < 10) {
            return "Regla de negocio: La reseña debe tener mínimo 10 caracteres.";
        }

        String comentarios = comentario.toLowerCase(Locale.ROOT);
        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (comentarios.contains(palabra)) {
                return "Regla de negocio: El comentario contiene lenguaje no permitido.";
            }
        }

        if (resenasExistentes != null) {
            for (ResenaItem r : resenasExistentes) {
                if (r.getUsuario().equalsIgnoreCase(usuario.trim())) {
                    if (!esEdicion || r.getId() != idActual) {
                        return "Regla de negocio: Este usuario ya publicó una reseña aquí. Edite la existente.";
                    }
                }
            }
        }

        return null;
    }

    public String obtenerResumenCalificacion(List<ResenaItem> resenas) {
        if (resenas == null || resenas.isEmpty()) {
            return "Sin calificaciones aún";
        }
        float suma = 0;
        for (ResenaItem r : resenas) {
            suma += r.getCalificacion();
        }
        float promedio = suma / resenas.size();
        String estado;

        if (promedio >= 4.5f) {
            estado = "Destino Top";
        } else if (promedio >= 3.5f) {
            estado = "Recomendado";
        } else {
            estado = "Regular";
        }

        return String.format(Locale.US, "Promedio: %.1f ★ (%d reseñas) - %s", promedio, resenas.size(), estado);
    }
}