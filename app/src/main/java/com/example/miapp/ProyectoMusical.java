package com.example.miapp;

import java.io.Serializable;

public class ProyectoMusical implements Serializable {

    private String id;
    private String nombre;
    private String genero;
    private String instrumentos;
    private String tonalidad;
    private String tempo;
    private String idea;
    private String letras;

    // Constructor vacío obligatorio para Firebase Firestore
    public ProyectoMusical() {
        this.letras = "";
    }

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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getInstrumentos() { return instrumentos; }
    public void setInstrumentos(String instrumentos) { this.instrumentos = instrumentos; }

    public String getTonalidad() { return tonalidad; }
    public void setTonalidad(String tonalidad) { this.tonalidad = tonalidad; }

    public String getTempo() { return tempo; }
    public void setTempo(String tempo) { this.tempo = tempo; }

    public String getIdea() { return idea; }
    public void setIdea(String idea) { this.idea = idea; }

    public String getLetras() { return letras; }
    public void setLetras(String letras) { this.letras = letras; }
}