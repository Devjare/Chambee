package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.vistas.PublicacionPersona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaPublicacionPersona extends
        Presentador<List<PublicacionPersona>> {

    @Override
    public List<PublicacionPersona> procesar(JSONObject json) {

        JSONArray jsonArray = json.optJSONArray("publicacion_persona");
        List<PublicacionPersona> publicaciones = new ArrayList<>();

//        publicaciones.add(new PublicacionPersona.PublicacionPersonaBuilder()
//                .setComentarios(0)
//                .setEtiqueta("tag")
//                .setDescripcion("desc")
//                .setInteresada(1)
//                .setInteresados(1)
//                .setNombrePersona("company")
//                .setUrlImagenPersona("default")
//                .setTiempo("Time")
//                .setVista(1)
//                .setVistos(1)
//                .build());


        return publicaciones;
    }
}
