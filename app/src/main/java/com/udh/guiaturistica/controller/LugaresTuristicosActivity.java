package com.udh.guiaturistica.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.R;

public class LugaresTuristicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_turisticos);

        setTitle(R.string.titulo_lugares_turisticos);
    }
}
