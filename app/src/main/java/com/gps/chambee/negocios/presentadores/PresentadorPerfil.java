package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorPerfil {
    public Perfil procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("pefiles");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        long id = jsonObject.optLong("id_perfil");
        String urlPerfil= jsonObject.optString("url_perfil");
        String urlPortada= jsonObject.optString("url_portada");
        String oficio = jsonObject.optString("oficio");
        String acerca = jsonObject.optString("acerca");
        String fechaNacimiento= jsonObject.optString("fecha_nacimiento");
        String idUsuario= jsonObject.optString("id_usuario");
        String idColonia= jsonObject.optString("id_colonia");
        String idCalle= jsonObject.optString("id_calle");
        float calificacion= (float)jsonObject.optDouble("calificacion");
        int idLocalidad= jsonObject.optInt("id_localidad");



        return new Perfil.PerfilBuilder()
                .setId(id)
                .setUrlPerfil(urlPerfil)
                .setUrlPortada(urlPortada)
                .setOficio(oficio)
                .setAcerca(acerca)
                .setFechaNacimiento(fechaNacimiento)
                .setIdLocalidad(idLocalidad)
                .setIdUsuario(idUsuario)
                .setIdColonia(idColonia)
                .setIdCalle(idCalle)
                .setCalificacion(calificacion)
                .setIdLocalidad(idLocalidad)
                .build();
    }
}
