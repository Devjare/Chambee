package com.gps.chambee.ui;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class Sesion {

    private static Sesion INSTANCIA;
    private final Map<String,Object> entidades = new HashMap<>();
    private final Map<String, Bitmap> imagenes = new HashMap<>();

    private Sesion() { }

    public static Sesion instance() {
        if (INSTANCIA == null)
            INSTANCIA = new Sesion();

        return INSTANCIA;
    }

    public void agregarEntidad(String id, Object entidad){
        entidades.put(id,entidad);
    }

    public Object obtenerEntidad(String id){
        return entidades.get(id);
    }

    public boolean existeImagen(String id) {
        return imagenes.containsKey(id);
    }

    public void agregarImagen(String id, Bitmap imagen) {
        imagenes.put(id, imagen);
    }

    public Bitmap obtenerImagen(String id) {
        return imagenes.get(id);
    }
}
