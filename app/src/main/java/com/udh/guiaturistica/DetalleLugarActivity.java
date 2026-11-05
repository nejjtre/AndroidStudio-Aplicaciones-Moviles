package com.udh.guiaturistica;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.datos.Repositorio;
import com.udh.guiaturistica.modelo.Lugar;
import com.udh.guiaturistica.modelo.Resena;

import java.util.ArrayList;
import java.util.Locale;

public class DetalleLugarActivity extends AppCompatActivity {

    private int idLugar;
    private ImageView ivFoto;
    private TextView tvNombre, tvInfo, tvPromedio, tvDescripcion;
    private RatingBar rbCalificacion;
    private EditText etNombreUsuario, etComentario, etRecomendacion;
    private LinearLayout llResenas;
    private Repositorio repositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lugar);

        idLugar = getIntent().getIntExtra("idLugar", 0);

        ivFoto = findViewById(R.id.ivFoto);
        tvNombre = findViewById(R.id.tvNombre);
        tvInfo = findViewById(R.id.tvInfo);
        tvPromedio = findViewById(R.id.tvPromedio);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        rbCalificacion = findViewById(R.id.rbCalificacion);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etComentario = findViewById(R.id.etComentario);
        etRecomendacion = findViewById(R.id.etRecomendacion);
        llResenas = findViewById(R.id.llResenas);
        Button btnPublicar = findViewById(R.id.btnPublicar);

        repositorio = new Repositorio();
        btnPublicar.setOnClickListener(v -> publicarResena());

        mostrarLugar();
        mostrarResenas();
    }

    private void mostrarLugar() {
        Lugar lugar = repositorio.obtenerLugar(idLugar);
        if (lugar == null) {
            finish();
            return;
        }
        tvNombre.setText(lugar.getNombre());
        tvInfo.setText(lugar.getTipo() + "  |  " + lugar.getHorario());
        tvPromedio.setText(String.format(Locale.getDefault(), "Calificación: %.1f ★", lugar.getPromedio()));
        tvDescripcion.setText(lugar.getDescripcion());
        if (!lugar.getFoto().isEmpty()) {
            ivFoto.setImageURI(Uri.parse(lugar.getFoto()));
        }
    }

    private void mostrarResenas() {
        llResenas.removeAllViews();
        ArrayList<Resena> resenas = repositorio.listarResenas(idLugar);
        if (resenas.isEmpty()) {
            TextView tvVacio = new TextView(this);
            tvVacio.setText("Aún no hay reseñas. ¡Sé el primero!");
            llResenas.addView(tvVacio);
            return;
        }
        for (Resena resena : resenas) {
            TextView tvResena = new TextView(this);
            tvResena.setText(resena.toString());
            tvResena.setPadding(0, 16, 0, 16);
            llResenas.addView(tvResena);
        }
    }

    private void publicarResena() {
        String nombreUsuario = etNombreUsuario.getText().toString().trim();
        int calificacion = (int) rbCalificacion.getRating();
        String comentario = etComentario.getText().toString().trim();
        String recomendacion = etRecomendacion.getText().toString().trim();

        if (nombreUsuario.isEmpty() || calificacion == 0 || comentario.isEmpty()) {
            mostrar("Escribe tu nombre, elige las estrellas y escribe tu reseña");
            return;
        }

        Resena resena = new Resena(0, idLugar, nombreUsuario, calificacion, comentario, recomendacion);
        repositorio.insertarResena(resena);

        rbCalificacion.setRating(0);
        etComentario.setText("");
        etRecomendacion.setText("");
        mostrar("Reseña agregada");

        mostrarLugar();
        mostrarResenas();
    }

    private void mostrar(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}