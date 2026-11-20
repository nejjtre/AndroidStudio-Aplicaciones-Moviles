package com.udh.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
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
}
