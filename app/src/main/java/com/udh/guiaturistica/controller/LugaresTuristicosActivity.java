package com.udh.guiaturistica.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udh.guiaturistica.R;
import com.udh.guiaturistica.adapter.LugarAdapter;
import com.udh.guiaturistica.modelo.LugarItem;

import java.util.ArrayList;
import java.util.List;

public class LugaresTuristicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_turisticos);

        setTitle(R.string.titulo_lugares_turisticos);

        RecyclerView rvLugares = findViewById(R.id.rvLugaresTuristicos);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        List<LugarItem> lista = new ArrayList<>();
        lista.add(new LugarItem(
                "Kotosh - Templo de las Manos Cruzadas",
                "Sitio arqueológico y uno de los centros ceremoniales más antiguos de América y del Perú.",
                R.drawable.kotosh
        ));
        lista.add(new LugarItem(
                "Cueva de las Lechuzas",
                "Espectacular gruta de la cadena montañosa de la Bella Durmiente, habitada por guácharos y murciélagos.",
                R.drawable.cueva_de_las_lechuzas
        ));
        lista.add(new LugarItem(
                "Puente Calicanto",
                "Histórico puente sobre el río Huallaga construido a base de piedra canto rodado, cal y clara de huevo.",
                R.drawable.puente_calicanto
        ));

        LugarAdapter adapter = new LugarAdapter(lista);
        rvLugares.setAdapter(adapter);
    }
}
