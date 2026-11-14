package com.example.miapp;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProyectos {

    private static final String COLECCION = "proyectos";
    private static final List<ProyectoMusical> listaProyectos = new ArrayList<>();

    // Interfaz para avisar a la pantalla cuando Firestore termina de descargar los datos
    public interface OnProyectosCargadosListener {
        void onCargados(List<ProyectoMusical> proyectos);
        void onError(Exception e);
    }

    // Descarga todos los proyectos desde Firestore y actualiza la lista local
    public static void cargarDesdeFirestore(OnProyectosCargadosListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLECCION)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listaProyectos.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ProyectoMusical p = doc.toObject(ProyectoMusical.class);
                        p.setId(doc.getId());
                        listaProyectos.add(p);
                    }
                    if (listener != null) {
                        listener.onCargados(listaProyectos);
                    }
                })
                .addOnFailureListener(e -> {
                    if (listener != null) {
                        listener.onError(e);
                    }
                });
    }

    public static List<ProyectoMusical> obtenerTodos() {
        return listaProyectos;
    }

    // Guarda el proyecto en Firestore y lo agrega a la lista local
    public static void agregarProyecto(ProyectoMusical proyecto) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference nuevoDoc = db.collection(COLECCION).document();
        proyecto.setId(nuevoDoc.getId());

        listaProyectos.add(0, proyecto);
        nuevoDoc.set(proyecto);
    }

    // Actualiza las letras tanto en memoria como en el documento de Firestore
    public static void actualizarLetras(String nombreProyecto, String nuevasLetras) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (ProyectoMusical p : listaProyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                p.setLetras(nuevasLetras);

                if (p.getId() != null && !p.getId().isEmpty()) {
                    db.collection(COLECCION)
                            .document(p.getId())
                            .update("letras", nuevasLetras);
                }
                break;
            }
        }
    }

    public static ProyectoMusical buscarPorNombre(String nombreProyecto) {
        for (ProyectoMusical p : listaProyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                return p;
            }
        }
        return null;
    }
}
