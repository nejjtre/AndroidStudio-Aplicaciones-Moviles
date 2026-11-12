package com.udh.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.controller.AcercaDeActivity;
import com.udh.guiaturistica.controller.AyudaActivity;
import com.udh.guiaturistica.controller.DetalleActivity;
import com.udh.guiaturistica.controller.LugaresTuristicosActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLugaresTuristicos = findViewById(R.id.btnLugaresTuristicos);
        Button btnLugaresGastronomicos = findViewById(R.id.btnLugaresGastronomicos);
        Button btnLugaresNaturales = findViewById(R.id.btnLugaresNaturales);

        btnLugaresTuristicos.setOnClickListener(v -> startActivity(new Intent(this, LugaresTuristicosActivity.class)));
        btnLugaresGastronomicos.setOnClickListener(v -> abrirPantalla(getString(R.string.titulo_lugares_gastronomicos)));
        btnLugaresNaturales.setOnClickListener(v -> abrirPantalla(getString(R.string.titulo_lugares_naturales)));
    }

    private void abrirPantalla(String titulo) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("titulo", titulo);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAcercaDe) {
            startActivity(new Intent(this, AcercaDeActivity.class));
            return true;
        }
        if (id == R.id.menuAyuda) {
            startActivity(new Intent(this, AyudaActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}