package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Comentario;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWRegistrarComentarioPublicacion extends ServicioWebEscritura {
    public SWRegistrarComentarioPublicacion(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(context, listener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {

        Map<String, String> params = new HashMap<>();
        params.put("id_publicaciones", args[0].toString());
        params.put("id_perfiles", args[1].toString());
        params.put("comentario", args[2].toString());

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return context.getString(R.string.sw_alta_comentario_publicacion);
    }

}