package com.example.miapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LetrasActivity extends AppCompatActivity {

    private LinearLayout contenedorSecciones;
    private StringBuilder letrasAcumuladas = new StringBuilder();
    private String nombreProyecto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letras);

        TextView txtNombreProyecto = findViewById(R.id.txtLetrasNombreProyecto);
        Spinner spinnerSeccion = findViewById(R.id.spinnerSeccionLetra);
        EditText edtContenido = findViewById(R.id.edtContenidoLetra);
        Button btnAgregarSeccion = findViewById(R.id.btnAgregarSeccion);
        Button btnGuardarLetras = findViewById(R.id.btnGuardarLetras);
        contenedorSecciones = findViewById(R.id.contenedorSeccionesLetra);

        // Recibimos el proyecto por Extras
        ProyectoMusical proyecto = (ProyectoMusical) getIntent().getSerializableExtra("proyecto");

        if (proyecto != null) {
            nombreProyecto = proyecto.getNombre();
            txtNombreProyecto.setText(nombreProyecto);

            // Buscamos la versión más reciente en el repositorio por si ya tenía letras guardadas
            ProyectoMusical actualizado = RepositorioProyectos.buscarPorNombre(nombreProyecto);
            String letrasPrevias = (actualizado != null) ? actualizado.getLetras() : proyecto.getLetras();

            if (letrasPrevias != null && !letrasPrevias.trim().isEmpty()) {
                letrasAcumuladas.append(letrasPrevias);
                mostrarBloquePrevio(letrasPrevias);
            }
        }

        btnAgregarSeccion.setOnClickListener(v -> {
            String tipoSeccion = spinnerSeccion.getSelectedItem().toString();
            String texto = edtContenido.getText().toString().trim();

            if (texto.isEmpty()) {
                Toast.makeText(this, "Escribí la letra antes de agregar", Toast.LENGTH_SHORT).show();
                return;
            }

            // Formateamos la sección y la agregamos al acumulador
            String bloque = "[" + tipoSeccion.toUpperCase() + "]\n" + texto + "\n\n";
            letrasAcumuladas.append(bloque);

            // Creamos visualmente la tarjeta de esta sección
            agregarTarjetaSeccion(tipoSeccion, texto);

            // Limpiamos el campo para escribir la siguiente estrofa
            edtContenido.setText("");
        });

        btnGuardarLetras.setOnClickListener(v -> {
            RepositorioProyectos.actualizarLetras(nombreProyecto, letrasAcumuladas.toString());
            Toast.makeText(this, "Letras guardadas en el proyecto", Toast.LENGTH_SHORT).show();
            finish(); // Cierra LetrasActivity y vuelve al Detalle del proyecto
        });
    }

    private void agregarTarjetaSeccion(String titulo, String contenido) {
        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.VERTICAL);
        tarjeta.setPadding(24, 20, 24, 20);
        tarjeta.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 16);
        tarjeta.setLayoutParams(params);

        TextView txtTitulo = new TextView(this);
        txtTitulo.setText(titulo);
        txtTitulo.setTextSize(16);
        txtTitulo.setTypeface(null, Typeface.BOLD);
        txtTitulo.setTextColor(ContextCompat.getColor(this, R.color.button_primary));

        TextView txtContenido = new TextView(this);
        txtContenido.setText(contenido);
        txtContenido.setTextSize(15);
        txtContenido.setTextColor(ContextCompat.getColor(this, R.color.text_primary));
        txtContenido.setPadding(0, 6, 0, 0);

        tarjeta.addView(txtTitulo);
        tarjeta.addView(txtContenido);
        contenedorSecciones.addView(tarjeta);
    }

    private void mostrarBloquePrevio(String textoCompleto) {
        agregarTarjetaSeccion("Letras guardadas", textoCompleto.trim());
    }
}