package com.udh.guiaturistica.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.R;

public class AyudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

        setTitle(R.string.titulo_ayuda);
        TextView tvTexto = findViewById(R.id.tvTexto);
        tvTexto.setText(R.string.texto_ayuda);
    }
}