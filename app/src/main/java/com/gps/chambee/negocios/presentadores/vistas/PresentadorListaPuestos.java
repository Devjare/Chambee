package com.gps.chambee.negocios.presentadores.vistas;

import com.gps.chambee.entidades.vistas.Puesto;
import com.gps.chambee.negocios.presentadores.Presentador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaPuestos extends Presentador<List<Puesto>> {
    @Override
    public List<Puesto> procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("puesto");
        List<Puesto> puestos = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            assert jsonObject != null;
            Puesto puesto = new Puesto.PuestoBuilder().setPuestoTrabajo(jsonObject.optString("puestoTrabajo"))
                    .setEmpresa(jsonObject.optString("empresa"))
                    .setEstrella((float) jsonObject.optDouble("estrella"))
                    .setUrlImagenEmpresa(jsonObject.optString("imagenEmpresa")).build();
            puestos.add(puesto);
        }
        return puestos;
    }
}
