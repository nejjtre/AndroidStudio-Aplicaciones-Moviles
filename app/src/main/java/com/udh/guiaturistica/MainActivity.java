package com.udh.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.udh.guiaturistica.controller.AcercaDeActivity;
import com.udh.guiaturistica.controller.AyudaActivity;
import com.udh.guiaturistica.controller.LugaresActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnVerLugares = findViewById(R.id.btnVerLugares);
        btnVerLugares.setOnClickListener(v -> startActivity(new Intent(this, LugaresActivity.class)));
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
