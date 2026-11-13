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

public class LugaresGastronomicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_gastronomicos);

        setTitle(R.string.titulo_lugares_gastronomicos);

        RecyclerView rvLugares = findViewById(R.id.rvLugaresGastronomicos);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        List<LugarItem> lista = new ArrayList<>();
        lista.add(new LugarItem(
                "Botanica Food",
                "Restaurante gastronómico que combina ingredientes locales y cocina fusión en un ambiente acogedor.",
                R.drawable.botanicafood
        ));
        lista.add(new LugarItem(
                "El Bambú",
                "Reconocido restaurante campestre y turístico especializado en platos típicos de la región de Huánuco.",
                R.drawable.elbambu
        ));
        lista.add(new LugarItem(
                "Trapiche",
                "Tradicional espacio gastronómico para disfrutar de la mejor sazón regional, bebidas típicas y parrillas.",
                R.drawable.trapiche
        ));

        LugarAdapter adapter = new LugarAdapter(lista);
        rvLugares.setAdapter(adapter);
    }
}
