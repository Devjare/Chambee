package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.servicios.web.ServicioWebLectura;

import org.json.JSONObject;

public class SWObtenerCategoriasPublicacion extends ServicioWebLectura {
    public SWObtenerCategoriasPublicacion(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected String definirUrl(Object... args) {
        int idPublicacion = Integer.parseInt(args[0].toString());
        return context.getString(R.string.sw_leer_categorias_publicacion) + "?id_publicaciones=" + idPublicacion;
    }
}