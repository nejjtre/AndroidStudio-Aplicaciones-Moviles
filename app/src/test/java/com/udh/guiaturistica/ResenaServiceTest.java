package com.udh.guiaturistica;

import org.junit.Test;
import static org.junit.Assert.*;
import com.udh.guiaturistica.negocio.ResenaService;
import com.udh.guiaturistica.modelo.ResenaItem;
import java.util.ArrayList;
import java.util.List;

public class ResenaServiceTest {

    private final ResenaService service = new ResenaService();

    @Test
    public void rechazaCalificacionCero() {
        String error = service.validarResena("Alexander", 0f, "Excelente comida típica", new ArrayList<>(), false, -1);
        assertNotNull(error);
    }

    @Test
    public void rechazaComentarioMuyCorto() {
        String error = service.validarResena("Alexander", 5f, "Rico", new ArrayList<>(), false, -1);
        assertNotNull(error);
    }

    @Test
    public void aceptaResenaValidaYCalculaPromedio() {
        List<ResenaItem> lista = new ArrayList<>();
        String error = service.validarResena("Alexander", 5f, "Muy buena atención y platos típicos de Huánuco", lista, false, -1);
        assertNull(error);

        lista.add(new ResenaItem("Botanica Food", "Alexander", 5f, "Muy buena atención y platos típicos de Huánuco"));
        String resumen = service.obtenerResumenCalificacion(lista);
        assertTrue(resumen.contains("5.0"));
    }
}