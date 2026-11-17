package com.udh.guiaturistica.controller;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.udh.guiaturistica.R;
import com.udh.guiaturistica.modelo.ResenaItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleLugarActivity extends AppCompatActivity {

    private static Map<String, List<ResenaItem>> repositorioResenas = new HashMap<>();

    private String nombreLugar;
    private RatingBar rbCalificacion;
    private EditText etNombreUsuario;
    private EditText etComentarioResena;
    private LinearLayout llResenasPublicadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lugar);

        nombreLugar = getIntent().getStringExtra("nombre");
        String horario = getIntent().getStringExtra("horario");
        String descripcion = getIntent().getStringExtra("descripcion");
        int imagenResId = getIntent().getIntExtra("imagenResId", 0);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(nombreLugar != null ? nombreLugar : getString(R.string.titulo_detalle));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        ImageView ivFoto = findViewById(R.id.ivDetalleFoto);
        TextView tvNombre = findViewById(R.id.tvDetalleNombre);
        TextView tvHorario = findViewById(R.id.tvDetalleHorario);
        TextView tvDescripcion = findViewById(R.id.tvDetalleDescripcion);

        rbCalificacion = findViewById(R.id.rbCalificacion);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etComentarioResena = findViewById(R.id.etComentarioResena);
        llResenasPublicadas = findViewById(R.id.llResenasPublicadas);
        Button btnPublicar = findViewById(R.id.btnPublicar);

        if (nombreLugar != null) {
            tvNombre.setText(nombreLugar);
        }
        if (horario != null) {
            tvHorario.setText(horario);
        }
        if (descripcion != null) {
            tvDescripcion.setText(descripcion);
        }
        if (imagenResId != 0) {
            ivFoto.setImageResource(imagenResId);
        }

        btnPublicar.setOnClickListener(v -> agregarResena());

        mostrarResenas();
    }

    private void agregarResena() {
        String usuario = etNombreUsuario.getText().toString().trim();
        String comentario = etComentarioResena.getText().toString().trim();
        float calificacion = rbCalificacion.getRating();

        if (usuario.isEmpty() || comentario.isEmpty() || calificacion == 0) {
            Toast.makeText(this, R.string.msg_completar_campos, Toast.LENGTH_SHORT).show();
            return;
        }

        ResenaItem nuevaResena = new ResenaItem(usuario, calificacion, comentario);

        if (!repositorioResenas.containsKey(nombreLugar)) {
            repositorioResenas.put(nombreLugar, new ArrayList<>());
        }
        List<ResenaItem> listaLugar = repositorioResenas.get(nombreLugar);
        if (listaLugar != null) {
            listaLugar.add(nuevaResena);
        }

        etNombreUsuario.setText("");
        etComentarioResena.setText("");
        rbCalificacion.setRating(0);

        Toast.makeText(this, R.string.msg_resena_publicada, Toast.LENGTH_SHORT).show();
        mostrarResenas();
    }

    private void mostrarResenas() {
        llResenasPublicadas.removeAllViews();
        List<ResenaItem> lista = repositorioResenas.get(nombreLugar);

        if (lista == null || lista.isEmpty()) {
            return;
        }

        for (ResenaItem r : lista) {
            MaterialCardView cardView = new MaterialCardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, 16);
            cardView.setLayoutParams(cardParams);
            cardView.setRadius(16);
            cardView.setCardElevation(2);

            LinearLayout container = new LinearLayout(this);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(24, 24, 24, 24);

            TextView tvHeader = new TextView(this);
            tvHeader.setText(r.getUsuario() + "  |  " + (int) r.getCalificacion() + " ★");
            tvHeader.setTextSize(16);
            tvHeader.setTypeface(null, Typeface.BOLD);

            TextView tvComentario = new TextView(this);
            tvComentario.setText(r.getComentario());
            tvComentario.setTextSize(14);
            tvComentario.setPadding(0, 8, 0, 0);

            container.addView(tvHeader);
            container.addView(tvComentario);
            cardView.addView(container);

            llResenasPublicadas.addView(cardView);
        }
    }
}
