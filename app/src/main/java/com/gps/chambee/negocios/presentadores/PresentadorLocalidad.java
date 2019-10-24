package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Localidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorLocalidad {

    public Localidad procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("localidades");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_localidad");
        String idPais= jsonObject.optString("id_pais");
        String idEstado= jsonObject.optString("id_estado");
        String idCiudad = jsonObject.optString("id_ciudad");



        return new Localidad.LocalidadBuilder()
                .setId(id)
                .setIdPais(idPais)
                .setIdEstado(idEstado)
                .setIdCiudad(idCiudad)
                .build();
    }
}
