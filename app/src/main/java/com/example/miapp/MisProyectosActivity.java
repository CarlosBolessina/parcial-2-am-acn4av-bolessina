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
            // 1. Creamos el contenedor de la tarjeta
            LinearLayout tarjeta = new LinearLayout(this);
            tarjeta.setOrientation(LinearLayout.VERTICAL);
            tarjeta.setPadding(32, 24, 32, 24);
            tarjeta.setBackgroundColor(Color.WHITE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 24);
            tarjeta.setLayoutParams(params);

            // 2. Título de la canción
            TextView txtTitulo = new TextView(this);
            txtTitulo.setText(proyecto.getNombre());
            txtTitulo.setTextSize(20);
            txtTitulo.setTypeface(null, Typeface.BOLD);
            txtTitulo.setTextColor(ContextCompat.getColor(this, R.color.text_primary));

            // 3. Subtítulo con género, tonalidad y tempo
            TextView txtInfo = new TextView(this);
            String tempoTexto = proyecto.getTempo().trim().isEmpty() ? "-" : proyecto.getTempo() + " BPM";
            txtInfo.setText(proyecto.getGenero() + "  •  Tonalidad: " + proyecto.getTonalidad() + "  •  " + tempoTexto);
            txtInfo.setTextSize(15);
            txtInfo.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
            txtInfo.setPadding(0, 8, 0, 8);

            // 4. Instrumentos
            TextView txtInstrumentos = new TextView(this);
            txtInstrumentos.setText(proyecto.getInstrumentos());
            txtInstrumentos.setTextSize(14);
            txtInstrumentos.setTextColor(ContextCompat.getColor(this, R.color.button_primary));

            tarjeta.addView(txtTitulo);
            tarjeta.addView(txtInfo);
            tarjeta.addView(txtInstrumentos);

            // 5. Al tocar la tarjeta, enviamos ese proyecto a DetalleProyectoActivity
            tarjeta.setOnClickListener(v -> {
                Intent intent = new Intent(MisProyectosActivity.this, DetalleProyectoActivity.class);
                intent.putExtra("proyecto", proyecto);
                startActivity(intent);
            });

            contenedorProyectos.addView(tarjeta);
        }
    }
}