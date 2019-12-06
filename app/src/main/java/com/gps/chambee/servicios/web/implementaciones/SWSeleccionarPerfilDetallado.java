package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.servicios.web.ServicioWebLectura;

import org.json.JSONObject;

public class SWSeleccionarPerfilDetallado  extends ServicioWebLectura {

    public SWSeleccionarPerfilDetallado(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected String definirUrl(Object... args) {
        String idUsuario = args[0].toString();
        return "http://chambee.online/chambee_php/lectura/serviceweb_lectura_detalles_usuario.php?id_usuario=" + idUsuario;
    }
}
