package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Ciudad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorCiudad {

    public Ciudad procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("ciudades");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_ciudad");
        String nombre = jsonObject.optString("Nombre");

        return new Ciudad.CiudadBuilder()
                .setId(id)
                .setNombre(nombre)
                .build();
    }
}
