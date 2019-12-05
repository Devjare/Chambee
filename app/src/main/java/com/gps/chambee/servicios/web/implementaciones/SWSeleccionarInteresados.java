package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.servicios.web.ServicioWebLectura;

import org.json.JSONObject;

public class SWSeleccionarInteresados extends ServicioWebLectura {
    public SWSeleccionarInteresados(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected Request definirRequest(String url, Object... args) {

        return super.definirRequest(url, args);
    }

    @Override
    protected String definirUrl(Object... args) {
        int id = Integer.parseInt(args[0].toString());
        return context.getString(R.string.sw_seleccionar_interesados) + "?id_publicaciones=" + id;
    }
}
