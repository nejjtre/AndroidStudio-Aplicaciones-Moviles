package com.udh.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.udh.guiaturistica.datos.Repositorio;
import com.udh.guiaturistica.modelo.Lugar;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String[] TIPOS = {"Todos", "Cultural", "Gastronómico", "Natural"};
    private static final String[] HORARIOS = {"Cualquier hora", "Abierto ahora"};

    private Spinner spTipo, spHorario;
    private ListView lvLugares;
    private Repositorio repositorio;
    private ArrayList<Lugar> lugares = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTipo = findViewById(R.id.spTipo);
        spHorario = findViewById(R.id.spHorario);
        lvLugares = findViewById(R.id.lvLugares);
        Button btnFiltrar = findViewById(R.id.btnFiltrar);
        Button btnAgregar = findViewById(R.id.btnAgregar);

        repositorio = new Repositorio();
        spTipo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TIPOS));
        spHorario.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, HORARIOS));

        btnFiltrar.setOnClickListener(v -> cargarLugares());
        btnAgregar.setOnClickListener(v -> startActivity(new Intent(this, AgregarLugarActivity.class)));
        lvLugares.setOnItemClickListener((parent, view, position, id) -> abrirDetalle(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLugares();
    }

    private void cargarLugares() {
        String tipo = spTipo.getSelectedItem().toString();
        int hora = -1;
        if (spHorario.getSelectedItemPosition() == 1) {
            hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
        lugares = repositorio.listarLugares(tipo, hora);
        lvLugares.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lugares));
        if (lugares.isEmpty()) {
            Toast.makeText(this, "No hay lugares con ese filtro", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirDetalle(int posicion) {
        Intent intent = new Intent(this, DetalleLugarActivity.class);
        intent.putExtra("idLugar", lugares.get(posicion).getIdLugar());
        startActivity(intent);
    }
}