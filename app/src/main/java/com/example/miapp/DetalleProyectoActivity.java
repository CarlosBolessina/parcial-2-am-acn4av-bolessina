package com.example.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleProyectoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proyecto);

        TextView txtNombre = findViewById(R.id.txtDetalleNombre);
        TextView txtGenero = findViewById(R.id.txtDetalleGenero);
        TextView txtTonalidad = findViewById(R.id.txtDetalleTonalidad);
        TextView txtTempo = findViewById(R.id.txtDetalleTempo);
        TextView txtInstrumentos = findViewById(R.id.txtDetalleInstrumentos);
        TextView txtIdea = findViewById(R.id.txtDetalleIdea);

        Button btnVerLetras = findViewById(R.id.btnVerLetras);
        Button btnVolverInicio = findViewById(R.id.btnVolverInicio);

        // Recibimos el objeto enviado desde la pantalla anterior mediante Extras
        ProyectoMusical proyecto = (ProyectoMusical) getIntent().getSerializableExtra("proyecto");

        if (proyecto != null) {
            txtNombre.setText(proyecto.getNombre());
            txtGenero.setText(proyecto.getGenero());
            txtTonalidad.setText(proyecto.getTonalidad());
            txtInstrumentos.setText(proyecto.getInstrumentos());

            String tempo = proyecto.getTempo().trim().isEmpty()
                    ? getString(R.string.sin_especificar)
                    : proyecto.getTempo() + " BPM";
            txtTempo.setText(tempo);

            String idea = proyecto.getIdea().trim().isEmpty()
                    ? getString(R.string.sin_especificar)
                    : proyecto.getIdea();
            txtIdea.setText(idea);
        }

        btnVerLetras.setOnClickListener(v -> {
            Toast.makeText(this, "Próximamente: Pantalla de Letras", Toast.LENGTH_SHORT).show();
        });

        btnVolverInicio.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleProyectoActivity.this, InicioActivity.class);
            // Limpiamos el historial de pantallas anteriores para volver limpio al inicio
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}