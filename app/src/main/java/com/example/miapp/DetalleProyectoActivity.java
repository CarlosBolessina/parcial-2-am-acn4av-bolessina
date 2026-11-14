package com.example.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetalleProyectoActivity extends AppCompatActivity {

    private ProyectoMusical proyecto;

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
        ImageView imgGenero = findViewById(R.id.imgDetalleGenero);

        Button btnVerLetras = findViewById(R.id.btnVerLetras);
        Button btnVolverInicio = findViewById(R.id.btnVolverInicio);

        // Recibimos el objeto enviado desde la pantalla anterior mediante Extras
         proyecto = (ProyectoMusical) getIntent().getSerializableExtra("proyecto");

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
            String urlImagen = obtenerUrlPorGenero(proyecto.getGenero());
            Glide.with(this)
                    .load(urlImagen)
                    .placeholder(R.drawable.musica)
                    .error(R.drawable.musica)
                    .centerCrop()
                    .into(imgGenero);
        }

        btnVerLetras.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleProyectoActivity.this, LetrasActivity.class);
            intent.putExtra("proyecto", proyecto);
            startActivity(intent);
        });

        btnVolverInicio.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleProyectoActivity.this, InicioActivity.class);
            // Limpiamos el historial de pantallas anteriores para volver limpio al inicio
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
    private String obtenerUrlPorGenero(String genero) {
        if (genero == null) {
            return "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=800";
        }
        switch (genero.toLowerCase()) {
            case "rock":
                return "https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?w=800";
            case "jazz":
                return "https://images.unsplash.com/photo-1511192336575-5a79af67a629?w=800";
            case "pop":
                return "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=800";
            case "electrónica":
            case "electronica":
                return "https://images.unsplash.com/photo-1571266028243-3716f02d2d2e?w=800";
            case "clásica":
            case "clasica":
                return "https://images.unsplash.com/photo-1507838153414-b4b713384a76?w=800";
            default:
                return "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=800";
        }
    }
}