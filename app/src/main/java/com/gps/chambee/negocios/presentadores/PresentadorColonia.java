package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Colonia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorColonia {

    public Colonia procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("colonias");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_colonia");
        String nombre = jsonObject.optString("nombre");

        return new Colonia.ColoniaBuilder()
                .setId(id)
                .setNombre(nombre)
                .build();
    }
}
