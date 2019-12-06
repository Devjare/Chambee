package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.vistas.MedallasPerfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaMedallasPerfil extends Presentador<List<MedallasPerfil>> {

    @Override
    public List<MedallasPerfil> procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("json");
        List<MedallasPerfil> medallas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;

            MedallasPerfil medallasPerfil = new MedallasPerfil.Builder()
                    .setIdPerfil(jsonObject.optInt("id_perfiles"))
                    .setIdMedalla(jsonObject.optInt("id_medallas"))
                    .setMedalla(jsonObject.optString("medalla"))
                    .setDescripcion(jsonObject.optString("descripcion"))
                    .setUrlMedalla(jsonObject.optString("url_medalla"))
                    .setCantidad(jsonObject.optInt("cantidad"))
                    .build();

            medallas.add(medallasPerfil);
        }

        return medallas;
    }
}
