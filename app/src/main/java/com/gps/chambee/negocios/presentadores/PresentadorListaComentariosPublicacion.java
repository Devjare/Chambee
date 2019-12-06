package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.vistas.ComentarioPublicacion;
import com.gps.chambee.entidades.vistas.PublicacionGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaComentariosPublicacion extends Presentador<List<ComentarioPublicacion>> {
    @Override
    public List<ComentarioPublicacion> procesar(JSONObject json) {

        JSONArray jsonArray = json.optJSONArray("json");
        List<ComentarioPublicacion> comentarios = new ArrayList<>();

        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;
            ComentarioPublicacion comentario = new ComentarioPublicacion.ComentarioPublcacionBuilder()
                    .setComentario(jsonObject.optString("comentario"))
                    .setIdUsuario(jsonObject.optInt("id_usuarios"))
                    .setNombreUsuario(jsonObject.optString("nombre"))
                    .setTiempo(jsonObject.optString("fecha"))
                    .setUrl_imagen(jsonObject.optString("url_perfil")).build();

            comentarios.add(comentario);
        }

        return comentarios;
    }
}
