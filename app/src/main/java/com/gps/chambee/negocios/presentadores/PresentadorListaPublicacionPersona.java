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


//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = jsonArray.getJSONObject(i);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            assert jsonObject != null;
//            PublicacionPersona publicacion = new PublicacionPersona.PublicacionPersonaBuilder()
//                    .setNombreUsuario(jsonObject.optString("nombre"))
//                    .setComentarios(jsonObject.optInt("comentarios"))
//                    .setDescripcion(jsonObject.optString("descripcion"))
//                    .setEtiqueta(jsonObject.optString("etiqueta"))
//                    .setInteresada(jsonObject.optInt("interesada"))
//                    .setInteresados(jsonObject.optInt("interesados"))
//                    .setTiempo(jsonObject.optString("tiempo"))
//                    .setVista(jsonObject.optInt("vista"))
//                    .setVistos(jsonObject.optInt("vistos"))
//                    .setUrlImagenPersona(jsonObject.optString("url_imagen_empresa")).build();
//
//            publicaciones.add(publicacion);
//        }

        return publicaciones;
    }
}
