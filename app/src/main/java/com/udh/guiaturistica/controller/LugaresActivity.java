package com.udh.guiaturistica.controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.udh.guiaturistica.R;
import com.udh.guiaturistica.adapter.LugarAdapter;
import com.udh.guiaturistica.modelo.LugarItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class LugaresActivity extends AppCompatActivity {

    private List<LugarItem> listaCompleta = new ArrayList<>();
    private RecyclerView rvLugares;
    private SearchView svBuscar;
    private Spinner spFiltroTipo;
    private Spinner spFiltroHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        svBuscar = findViewById(R.id.svBuscar);
        spFiltroTipo = findViewById(R.id.spFiltroTipo);
        spFiltroHorario = findViewById(R.id.spFiltroHorario);
        rvLugares = findViewById(R.id.rvLugares);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        Lugares();

        String[] opcionesTipo = {"Todos los tipos", "Cultural", "Gastronómico", "Natural"};
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opcionesTipo);
        spFiltroTipo.setAdapter(adapterTipo);

        String[] opcionesHorario = {"Todos los horarios", "Abierto en este momento", "Abierto todo el tiempo (24h)"};
        ArrayAdapter<String> adapterHorario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opcionesHorario);
        spFiltroHorario.setAdapter(adapterHorario);

        svBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                aplicarFiltros();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                aplicarFiltros();
                return true;
            }
        });

        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aplicarFiltros();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spFiltroTipo.setOnItemSelectedListener(spinnerListener);
        spFiltroHorario.setOnItemSelectedListener(spinnerListener);

        aplicarFiltros();
    }

    private void Lugares() {
        listaCompleta.clear();
        listaCompleta.add(new LugarItem(
                "Botanica Food",
                "Restaurante gastronómico que combina ingredientes locales y cocina fusión en un ambiente acogedor.",
                "Gastronómico",
                12, 23, false,
                R.drawable.botanicafood
        ));
        listaCompleta.add(new LugarItem(
                "Cueva de las Lechuzas",
                "Espectacular gruta de la cadena montañosa de la Bella Durmiente, habitada por guácharos y murciélagos.",
                "Natural",
                6, 18, false,
                R.drawable.cueva_de_las_lechuzas
        ));
        listaCompleta.add(new LugarItem(
                "El Bambú",
                "Reconocido restaurante campestre y turístico especializado en platos típicos de la región de Huánuco.",
                "Gastronómico",
                11, 22, false,
                R.drawable.elbambu
        ));
        listaCompleta.add(new LugarItem(
                "Kotosh - Templo de las Manos Cruzadas",
                "Sitio arqueológico y uno de los centros ceremoniales más antiguos de América y del Perú.",
                "Cultural",
                8, 17, false,
                R.drawable.kotosh
        ));
        listaCompleta.add(new LugarItem(
                "Puente Calicanto",
                "Histórico puente sobre el río Huallaga construido a base de piedra canto rodado, cal y clara de huevo.",
                "Cultural",
                0, 24, true,
                R.drawable.puente_calicanto
        ));
        listaCompleta.add(new LugarItem(
                "Trapiche",
                "Tradicional espacio gastronómico para disfrutar de la mejor sazón regional, bebidas típicas y parrillas.",
                "Gastronómico",
                11, 23, false,
                R.drawable.trapiche
        ));
    }

    private void aplicarFiltros() {
        String textoBusqueda = svBuscar.getQuery() != null ? svBuscar.getQuery().toString().toLowerCase().trim() : "";
        String filtroTipo = spFiltroTipo.getSelectedItem() != null ? spFiltroTipo.getSelectedItem().toString() : "Todos los tipos";
        String filtroHorario = spFiltroHorario.getSelectedItem() != null ? spFiltroHorario.getSelectedItem().toString() : "Todos los horarios";

        int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        List<LugarItem> filtrados = new ArrayList<>();
        for (LugarItem lugar : listaCompleta) {
            boolean coincideNombre = lugar.getNombre().toLowerCase().contains(textoBusqueda);

            boolean coincideTipo = filtroTipo.equalsIgnoreCase("Todos los tipos") ||
                    lugar.getTipo().equalsIgnoreCase(filtroTipo);

            boolean coincideHorario = true;
            if (filtroHorario.equalsIgnoreCase("Abierto en este momento")) {
                coincideHorario = lugar.estaAbiertoAhora(horaActual);
            } else if (filtroHorario.contains("24h")) {
                coincideHorario = lugar.isEs24Horas();
            }

            if (coincideNombre && coincideTipo && coincideHorario) {
                filtrados.add(lugar);
            }
        }

        filtrados.sort(Comparator.comparing(LugarItem::getNombre));
        LugarAdapter adapter = new LugarAdapter(filtrados);
        rvLugares.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAgregarLugar) {
            Toast.makeText(this, "Opción: Agregar lugar", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menuEditarLugar) {
            Toast.makeText(this, "Opción: Editar lugar", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menuEliminarLugar) {
            Toast.makeText(this, "Opción: Eliminar lugar", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
