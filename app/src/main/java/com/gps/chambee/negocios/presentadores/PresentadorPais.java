package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Pais;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorPais {
    public Pais procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("paises");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        String id = jsonObject.optString("id_pais");
        String nombre= jsonObject.optString("nombre");

        return new Pais.PaisBuilder()

                .build();
    }
}
