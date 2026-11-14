package com.example.miapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MisProyectosActivity extends AppCompatActivity {

    private LinearLayout contenedorProyectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);

        contenedorProyectos = findViewById(R.id.contenedorProyectos);
        Button btnCrearDesdeLista = findViewById(R.id.btnCrearDesdeLista);

        btnCrearDesdeLista.setOnClickListener(v -> {
            Intent intent = new Intent(MisProyectosActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Usamos onResume para que la lista se refresque automáticamente si creas un proyecto y vuelves
    @Override
    protected void onResume() {
        super.onResume();
        cargarProyectos();
    }

    private void cargarProyectos() {
        contenedorProyectos.removeAllViews();
        List<ProyectoMusical> proyectos = RepositorioProyectos.obtenerTodos();

        for (ProyectoMusical proyecto : proyectos) {
            LinearLayout tarjeta = new LinearLayout(this);
            tarjeta.setOrientation(LinearLayout.VERTICAL);
            tarjeta.setBackgroundColor(getResources().getColor(android.R.color.white, getTheme()));
            int padding = (int) (16 * getResources().getDisplayMetrics().density);
            tarjeta.setPadding(padding, padding, padding, padding);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int margenInferior = (int) (12 * getResources().getDisplayMetrics().density);
            params.setMargins(0, 0, 0, margenInferior);
            tarjeta.setLayoutParams(params);

            TextView txtNombre = new TextView(this);
            txtNombre.setText(proyecto.getNombre());
            txtNombre.setTextSize(18);
            txtNombre.setTypeface(null, android.graphics.Typeface.BOLD);
            txtNombre.setTextColor(getResources().getColor(android.R.color.black, getTheme()));

            TextView txtInfo = new TextView(this);
            String tempoTexto = proyecto.getTempo().trim().isEmpty()
                    ? "-"
                    : getString(R.string.formato_bpm, proyecto.getTempo());
            txtInfo.setText(getString(
                    R.string.formato_info_proyecto,
                    proyecto.getGenero(),
                    proyecto.getTonalidad(),
                    tempoTexto
            ));
            txtInfo.setTextSize(14);
            txtInfo.setPadding(0, (int) (4 * getResources().getDisplayMetrics().density), 0, 0);

            tarjeta.addView(txtNombre);
            tarjeta.addView(txtInfo);

            tarjeta.setOnClickListener(v -> {
                Intent intent = new Intent(MisProyectosActivity.this, DetalleProyectoActivity.class);
                intent.putExtra("proyecto", proyecto);
                startActivity(intent);
            });

            contenedorProyectos.addView(tarjeta);
        }
    }
}