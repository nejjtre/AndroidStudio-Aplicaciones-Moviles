package com.udh.guiaturistica.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.R;

// Pantalla principal: punto de partida de la navegación
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDetalle = findViewById(R.id.btnDetalle);
        btnDetalle.setOnClickListener(v -> {
            // Intent explícito con putExtra: envía un dato a la otra pantalla
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra("titulo", getString(R.string.titulo_detalle_ejemplo));
            startActivity(intent);
        });
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