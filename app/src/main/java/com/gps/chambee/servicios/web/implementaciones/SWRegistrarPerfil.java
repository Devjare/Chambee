package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWRegistrarPerfil extends ServicioWebEscritura{
    public SWRegistrarPerfil(Context context,
                             Response.Listener<String> responseListener,
                             Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        Perfil perfil = (Perfil) args[0];

        Map<String, String> params = new HashMap<>();
        params.put("urlPerfil", perfil.getUrlPerfil());
        params.put("urlPortada", perfil.getUrlPortada());
        params.put("oficio", perfil.getOficio());
        params.put("acerca", perfil.getAcerca());
        params.put("fechaNacimiento", perfil.getFechaNacimiento());
        params.put("idLocalidad", String.valueOf(perfil.getIdLocalidad()));
        params.put("idUsuario", perfil.getIdUsuario());
        params.put("idColonia", perfil.getIdColonia());
        params.put("idCalle", perfil.getIdCalle());
        params.put("calificacion", String.valueOf(perfil.getCalificacion()));

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return context.getString(R.string.sw_registrar_perfil);
    }

}

