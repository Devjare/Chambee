package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;
import android.util.JsonReader;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.servicios.web.ServicioWebEscritura;
import com.gps.chambee.servicios.web.ServicioWebLectura;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class SWSeleccionarPublicacionEmpresa extends ServicioWebLectura {

    public SWSeleccionarPublicacionEmpresa(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected Request definirRequest(String url, Object... args) {
        return super.definirRequest(url, args);
    }

    @Override
    protected String definirUrl(Object... args) {
        return context.getString(R.string.sw_select_publicaciones);
    }

}
