package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.servicios.web.ServicioWebImagen;

public class SWObtenerImagen extends ServicioWebImagen {
    public SWObtenerImagen(Context context,
                           Response.Listener<Bitmap> responseListener,
                           Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected String definirUrl(Object... args) {
        String urlImagen = args[0].toString();
        return context.getString(R.string.sw_obtener_imagen) + urlImagen;
    }
}

