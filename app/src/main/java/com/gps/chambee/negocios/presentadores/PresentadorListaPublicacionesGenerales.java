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
                    .setNombre(jsonObject.optString("nombre_empresa"))
                    .setNombreTrabajo(jsonObject.optString("nombre_trabajo"))
                    .setComentarios(jsonObject.optInt("comentarios"))
                    .setDescripcion(jsonObject.optString("descripcion"))
                    .setEtiqueta(jsonObject.optString("etiqueta"))
                    .setInteresada(jsonObject.optInt("interesada"))
                    .setInteresados(jsonObject.optInt("interesados"))
                    .setTiempo(jsonObject.optString("tiempo"))
                    .setVista(jsonObject.optInt("vista"))
                    .setVistos(jsonObject.optInt("vistos"))
                    .setUrlImagenTrabajo(jsonObject.optString("url_imagen_trabajo"))
                    .setUrlImagen(jsonObject.optString("url_imagen_empresa")).build();

            publicaciones.add(publicacion);
        }

        return publicaciones;
    }
}