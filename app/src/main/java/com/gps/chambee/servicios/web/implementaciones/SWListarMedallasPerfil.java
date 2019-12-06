package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Response;
import com.gps.chambee.servicios.web.ServicioWebLectura;

import org.json.JSONObject;

public class SWListarMedallasPerfil extends ServicioWebLectura {

    public SWListarMedallasPerfil(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected String definirUrl(Object... args) {
        String idPerfiles = args[0].toString();
        return "http://chambee.online/chambee_php/lectura/serviceweb_lectura_medallas_perfil.php?id_perfiles=" + idPerfiles;
    }
}
