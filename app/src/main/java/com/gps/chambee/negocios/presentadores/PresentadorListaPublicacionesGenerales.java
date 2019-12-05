package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.vistas.PublicacionGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaPublicacionesGenerales extends Presentador<List<PublicacionGeneral>> {

    @Override
    public List<PublicacionGeneral> procesar(JSONObject json) {

        JSONArray jsonArray = json.optJSONArray("json");
        List<PublicacionGeneral> publicaciones = new ArrayList<>();

        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;
            PublicacionGeneral publicacion = new PublicacionGeneral.PublicacionGeneralBuilder()
                    .setIdPublicacion(jsonObject.optInt("id_publicacion"))
                    .setNombre(jsonObject.optString("nombre_usuario"))
                    .setNombreTrabajo(jsonObject.optString("titulo_publicacion"))
                    .setComentarios(json.optInt("comentarios"))
                    .setDescripcion(jsonObject.optString("desc_publicacion"))
                    .setEtiqueta(jsonObject.optString("etiqueta"))
                    .setInteresada(jsonObject.optInt("interesada"))
                    .setInteresados(jsonObject.optInt("interesados"))
                    .setTiempo(jsonObject.optString("tiempo_publicacion"))
                    .setVista(jsonObject.optInt("vista"))
                    .setVistos(jsonObject.optInt("vistos"))
                    .setUrlImagenTrabajo(jsonObject.optString("url_desc_publicacion"))
                    .setUrlImagen(jsonObject.optString("url_perfil_perfil")).build();

            publicaciones.add(publicacion);
        }

        return publicaciones;
    }
}