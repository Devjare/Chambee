package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Estado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorEstado {
    public Estado procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("estados");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_perfil");
        String nombre = jsonObject.optString("nombre");


        return new Estado.EstadoBuilder()
                .setId(id)
                .setNombre(nombre)
                .build();
    }
}
