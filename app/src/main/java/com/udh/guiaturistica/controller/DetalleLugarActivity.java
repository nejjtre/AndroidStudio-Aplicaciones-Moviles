package com.udh.guiaturistica.controller;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.udh.guiaturistica.R;
import com.udh.guiaturistica.data.ResenaRepository;
import com.udh.guiaturistica.modelo.ResenaItem;
import com.udh.guiaturistica.negocio.ResenaService;

import java.util.List;

public class DetalleLugarActivity extends AppCompatActivity {

    private ResenaRepository resenaRepository;
    private ResenaService resenaService;

    private String nombreLugar;
    private RatingBar rbCalificacion;
    private EditText etNombreUsuario;
    private EditText etComentarioResena;
    private LinearLayout llResenasPublicadas;
    private Button btnPublicar;
    private TextView tvResumenCalificacion;

    private ResenaItem resenaEnEdicion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lugar);

        resenaRepository = new ResenaRepository(this);
        resenaService = new ResenaService();

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
        btnPublicar = findViewById(R.id.btnPublicar);

        if (nombreLugar != null) tvNombre.setText(nombreLugar);
        if (horario != null) tvHorario.setText(horario);
        if (descripcion != null) tvDescripcion.setText(descripcion);
        if (imagenResId != 0) ivFoto.setImageResource(imagenResId);

        tvResumenCalificacion = new TextView(this);
        tvResumenCalificacion.setTextSize(15);
        tvResumenCalificacion.setTypeface(null, Typeface.BOLD);
        tvResumenCalificacion.setTextColor(Color.parseColor("#2E5B58"));
        tvResumenCalificacion.setPadding(0, 0, 0, 16);

        btnPublicar.setOnClickListener(v -> guardarOActualizarResena());

        mostrarResenas();
    }

    private void guardarOActualizarResena() {
        String usuario = etNombreUsuario.getText().toString().trim();
        String comentario = etComentarioResena.getText().toString().trim();
        float calificacion = rbCalificacion.getRating();

        List<ResenaItem> actuales = resenaRepository.obtenerResenasPorLugar(nombreLugar);
        boolean esEdicion = (resenaEnEdicion != null);
        int idActual = esEdicion ? resenaEnEdicion.getId() : -1;

        String errorRegla = resenaService.validarResena(usuario, calificacion, comentario, actuales, esEdicion, idActual);
        if (errorRegla != null) {
            Toast.makeText(this, errorRegla, Toast.LENGTH_LONG).show();
            return;
        }

        if (esEdicion) {
            resenaEnEdicion.setUsuario(usuario);
            resenaEnEdicion.setCalificacion(calificacion);
            resenaEnEdicion.setComentario(comentario);
            resenaRepository.actualizarResena(resenaEnEdicion);

            resenaEnEdicion = null;
            btnPublicar.setText("Publicar reseña");
            Toast.makeText(this, "Reseña actualizada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            ResenaItem nuevaResena = new ResenaItem(nombreLugar, usuario, calificacion, comentario);
            resenaRepository.insertarResena(nuevaResena);
            Toast.makeText(this, R.string.msg_resena_publicada, Toast.LENGTH_SHORT).show();
        }

        limpiarFormulario();
        mostrarResenas();
    }

    private void limpiarFormulario() {
        etNombreUsuario.setText("");
        etComentarioResena.setText("");
        rbCalificacion.setRating(0);
    }

    private void prepararEdicion(ResenaItem resena) {
        resenaEnEdicion = resena;
        etNombreUsuario.setText(resena.getUsuario());
        etComentarioResena.setText(resena.getComentario());
        rbCalificacion.setRating(resena.getCalificacion());
        btnPublicar.setText("Actualizar reseña");
        etComentarioResena.requestFocus();
    }

    private void confirmarEliminacion(ResenaItem resena) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar reseña")
                .setMessage("¿Seguro que deseas eliminar la reseña de " + resena.getUsuario() + "?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    resenaRepository.eliminarResena(resena.getId());
                    if (resenaEnEdicion != null && resenaEnEdicion.getId() == resena.getId()) {
                        resenaEnEdicion = null;
                        btnPublicar.setText("Publicar reseña");
                        limpiarFormulario();
                    }
                    Toast.makeText(this, "Reseña eliminada", Toast.LENGTH_SHORT).show();
                    mostrarResenas();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarResenas() {
        llResenasPublicadas.removeAllViews();

        List<ResenaItem> lista = resenaRepository.obtenerResenasPorLugar(nombreLugar);

        tvResumenCalificacion.setText(resenaService.obtenerResumenCalificacion(lista));
        llResenasPublicadas.addView(tvResumenCalificacion);

        if (lista.isEmpty()) {
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
            tvComentario.setPadding(0, 8, 0, 12);

            LinearLayout filaBotones = new LinearLayout(this);
            filaBotones.setOrientation(LinearLayout.HORIZONTAL);

            TextView btnEditar = new TextView(this);
            btnEditar.setText("Editar");
            btnEditar.setTextColor(Color.parseColor("#1976D2"));
            btnEditar.setTypeface(null, Typeface.BOLD);
            btnEditar.setPadding(0, 8, 32, 8);
            btnEditar.setOnClickListener(v -> prepararEdicion(r));

            TextView btnEliminar = new TextView(this);
            btnEliminar.setText("Eliminar");
            btnEliminar.setTextColor(Color.parseColor("#D32F2F"));
            btnEliminar.setTypeface(null, Typeface.BOLD);
            btnEliminar.setPadding(0, 8, 16, 8);
            btnEliminar.setOnClickListener(v -> confirmarEliminacion(r));

            filaBotones.addView(btnEditar);
            filaBotones.addView(btnEliminar);

            container.addView(tvHeader);
            container.addView(tvComentario);
            container.addView(filaBotones);
            cardView.addView(container);

            llResenasPublicadas.addView(cardView);
        }
    }
}