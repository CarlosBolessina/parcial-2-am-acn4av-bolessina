package com.example.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

        // De momento avisamos; en el próximo paso la conectamos a MisProyectosActivity
        btnMisProyectos.setOnClickListener(v -> {
            Toast.makeText(this, "Próximamente: Mis Proyectos", Toast.LENGTH_SHORT).show();
        });
    }
}
