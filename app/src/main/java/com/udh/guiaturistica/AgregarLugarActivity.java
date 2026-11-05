package com.udh.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.udh.guiaturistica.datos.Repositorio;
import com.udh.guiaturistica.modelo.Lugar;

public class AgregarLugarActivity extends AppCompatActivity {

    private static final String[] TIPOS = {"Cultural", "Gastronómico", "Natural"};
    private EditText etNombre, etDescripcion, etApertura, etCierre;
    private Spinner spTipo;
    private ImageView ivFoto;
    private String uriFoto = "";
    private Repositorio repositorio;

    private final ActivityResultLauncher<String[]> selectorFoto =
            registerForActivityResult(new ActivityResultContracts.OpenDocument(), uri -> {
                if (uri != null) {
                    try {
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } catch (SecurityException e) {
                    }
                    uriFoto = uri.toString();
                    ivFoto.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lugar);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etApertura = findViewById(R.id.etApertura);
        etCierre = findViewById(R.id.etCierre);
        spTipo = findViewById(R.id.spTipo);
        ivFoto = findViewById(R.id.ivFoto);
        Button btnFoto = findViewById(R.id.btnFoto);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        repositorio = new Repositorio();
        spTipo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TIPOS));

        btnFoto.setOnClickListener(v -> selectorFoto.launch(new String[]{"image/*"}));
        btnGuardar.setOnClickListener(v -> guardar());
    }

    private void guardar() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String textoApertura = etApertura.getText().toString().trim();
        String textoCierre = etCierre.getText().toString().trim();

        if (nombre.isEmpty() || textoApertura.isEmpty() || textoCierre.isEmpty()) {
            mostrar("Completa el nombre y el horario");
            return;
        }

        int apertura = Integer.parseInt(textoApertura);
        int cierre = Integer.parseInt(textoCierre);
        if (apertura > 23 || cierre > 24 || cierre <= apertura) {
            mostrar("Horario inválido: usa horas de 0 a 24 y el cierre mayor que la apertura");
            return;
        }

        Lugar lugar = new Lugar(0, nombre, descripcion, spTipo.getSelectedItem().toString(),
                apertura, cierre, uriFoto);
        repositorio.insertarLugar(lugar);
        mostrar("Lugar agregado");
        finish();
    }

    private void mostrar(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}