package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Calle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorCalle {

    public Calle procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("calles");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        int id = jsonObject.optInt("id_calles");
        String nombre = jsonObject.optString("nombre");

        return new Calle.CalleBuilder()
                .setId(id)
                .setNombre(nombre)
                .build();
    }
}
