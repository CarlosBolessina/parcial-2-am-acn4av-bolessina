package com.example.miapp;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProyectos {

    private static final List<ProyectoMusical> listaProyectos = new ArrayList<>();
    private static boolean inicializado = false;

    // Carga 2 proyectos de ejemplo la primera vez para que la lista no arranque vacía
    private static void inicializarSiEsNecesario() {
        if (!inicializado) {
            listaProyectos.add(new ProyectoMusical(
                    "Noches de Buenos Aires",
                    "Rock",
                    "🎸 Guitarra, 🎸 Bajo, 🥁 Batería",
                    "Mi",
                    "124",
                    "Riff principal en Mi menor con entrada de batería en el compás 5."
            ));
            listaProyectos.add(new ProyectoMusical(
                    "Lluvia en Octubre",
                    "Jazz",
                    "🎹 Piano, 🎻 Violín",
                    "Fa",
                    "85",
                    "Acordes séptimos suaves en piano eléctrico para intro."
            ));
            inicializado = true;
        }
    }

    public static List<ProyectoMusical> obtenerTodos() {
        inicializarSiEsNecesario();
        return listaProyectos;
    }

    public static void agregarProyecto(ProyectoMusical proyecto) {
        inicializarSiEsNecesario();
        // Lo agregamos al principio (posición 0) para que los más nuevos salgan arriba
        listaProyectos.add(0, proyecto);
    }

    public static void actualizarLetras(String nombreProyecto, String nuevasLetras) {
        inicializarSiEsNecesario();
        for (ProyectoMusical p : listaProyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                p.setLetras(nuevasLetras);
                break;
            }
        }
    }

    public static ProyectoMusical buscarPorNombre(String nombreProyecto) {
        inicializarSiEsNecesario();
        for (ProyectoMusical p : listaProyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                return p;
            }
        }
        return null;
    }
}
