package com.gps.chambee.negocios.presentadores.vistas;

import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.negocios.presentadores.Presentador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentadorPerfilDetallado  extends Presentador<PerfilDetallado> {

    @Override
    public PerfilDetallado procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("json");

        JSONObject jsonObject = null;
        try{
            jsonObject = jsonArray.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }

        assert jsonObject != null;

        return new PerfilDetallado.Builder()
                .setIdUsuario(jsonObject.optString("id_usuario"))
                .setNombreUsuario(jsonObject.optString("nombre_usuario"))
                .setApellidosUsuario(jsonObject.optString("apellidos_usuario"))
                .setTelefono(jsonObject.optString("telefono"))
                .setCorreo(jsonObject.optString("correo"))
                .setContrasena(jsonObject.optString("contrasena"))
                .setIdPerfil(jsonObject.optInt("id_perfil"))
                .setUrlPerfil(jsonObject.optString("url_perfil"))
                .setUrlPortada(jsonObject.optString("url_portada"))
                .setAcerca(jsonObject.optString("acerca"))
                .setFechaNac(jsonObject.optString("fecha_nacimiento"))
                .setCalificacion(Float.parseFloat(String.valueOf(jsonObject.optDouble("calificacion"))))
                .setCalle(jsonObject.optString("calle"))
                .setColonia(jsonObject.optString("colonia"))
                .setCiudad(jsonObject.optString("ciudad"))
                .setEstado(jsonObject.optString("estado"))
                .setPais(jsonObject.optString("pais"))
                .build();
    }
}
