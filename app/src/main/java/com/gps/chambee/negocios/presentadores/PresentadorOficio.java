package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Oficio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorOficio {

    public Oficio procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("oficios");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_oficio");
        String nombre= jsonObject.optString("nombre");
        String especialidad= jsonObject.optString("especialidad");




        return new Oficio.OficioBuilder()
                .setId(id)
                .setNombre(nombre)
                .setEspecialidad(especialidad)

                .build();
    }


}
