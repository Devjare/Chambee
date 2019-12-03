package com.gps.chambee.negocios.casos;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebImagen;
import com.gps.chambee.servicios.web.implementaciones.SWObtenerImagen;

public class CUObtenerImagen extends CasoUso<Bitmap> {
    public CUObtenerImagen(Context context,
                           EventoPeticionAceptada<Bitmap> eventoPeticionAceptada,
                           EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWObtenerImagen(
                context,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {
                        Bitmap imagen = (Bitmap) response;
                        eventoPeticionAceptada.alAceptarPeticion(imagen);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        eventoPeticionRechazada.alRechazarOperacion();
                    }
                });
    }
}
