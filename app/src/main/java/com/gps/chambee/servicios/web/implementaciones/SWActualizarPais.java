package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.entidades.Pais;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWActualizarPais extends ServicioWebEscritura {
    public SWActualizarPais(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(context, listener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        String id = args[0].toString();
        Pais pais = (Pais) args[1];

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("nombre", pais.getNombre());

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return "chabee.online/escritura/serviceweb_cambio_paises.php";
    }

    @Override
    protected Request definirRequest(String url, Object... args) {
        return null;
    }
}