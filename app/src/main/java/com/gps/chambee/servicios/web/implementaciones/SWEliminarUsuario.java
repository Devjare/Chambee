package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWEliminarUsuario extends ServicioWebEscritura {
    public SWEliminarUsuario(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(context, listener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        HashMap<String, String> params = new HashMap<>();

        int id = Integer.parseInt(args[0].toString());
        params.put("id", String.valueOf(id));

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return "Here goes the URL With the php script.";
    }

    @Override
    protected Request definirRequest(String url, Object... args) {
        return null;
    }
}
