package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaInteresados extends Presentador<List<Perfil>> {
    @Override
    public List<Perfil> procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("json");
        List<Perfil> interesados = new ArrayList<>();

        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;
            Perfil comentario = new Perfil.PerfilBuilder()
                    .setId(jsonObject.optInt("id_perfiles"))
                    .setUrlPerfil("url_perfil")
                    .setNombre("nombre").build();

            interesados.add(comentario);
        }

        return interesados;
    }
}
