package com.example.miapp;

import java.io.Serializable;

public class ProyectoMusical implements Serializable {

    private String nombre;
    private String genero;
    private String instrumentos;
    private String tonalidad;
    private String tempo;
    private String idea;
    private String letras;

    public ProyectoMusical(String nombre, String genero, String instrumentos,
                           String tonalidad, String tempo, String idea) {
        this.nombre = nombre;
        this.genero = genero;
        this.instrumentos = instrumentos;
        this.tonalidad = tonalidad;
        this.tempo = tempo;
        this.idea = idea;
        this.letras = "";
    }

    public String getNombre() { return nombre; }
    public String getGenero() { return genero; }
    public String getInstrumentos() { return instrumentos; }
    public String getTonalidad() { return tonalidad; }
    public String getTempo() { return tempo; }
    public String getIdea() { return idea; }
    public String getLetras() { return letras; }

    public void setLetras(String letras) { this.letras = letras; }
}