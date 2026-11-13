package com.example.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button btnNuevo = findViewById(R.id.btnIrNuevoProyecto);
        Button btnMisProyectos = findViewById(R.id.btnIrMisProyectos);

        // Navegación hacia la pantalla de creación (tu MainActivity actual)
        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Navegación hacia el listado de proyectos
        btnMisProyectos.setOnClickListener(v -> {
            Intent intent = new Intent(InicioActivity.this, MisProyectosActivity.class);
            startActivity(intent);
        });
    }
}
