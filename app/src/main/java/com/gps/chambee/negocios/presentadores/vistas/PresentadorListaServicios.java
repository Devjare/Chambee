package com.gps.chambee.negocios.presentadores.vistas;

import com.gps.chambee.negocios.presentadores.Presentador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaServicios extends Presentador <List<String>> {
    @Override
    public List<String> procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("servicio");
        List<String> servicios = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            assert jsonObject != null;
            String servicio = jsonObject.optString("servicio");
            servicios.add(servicio);
        }
        return servicios;
    }
}
