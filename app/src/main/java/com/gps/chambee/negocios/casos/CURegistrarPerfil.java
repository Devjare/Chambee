package com.gps.chambee.negocios.casos;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWRegistrarPerfil;

public class CURegistrarPerfil extends CasoUso<String> {

    public CURegistrarPerfil(Context context, EventoPeticionAceptada<String> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWRegistrarPerfil(context, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("chambee", "Respuesta JSON: " + response);

                if (response.equals("1"))
                    eventoPeticionAceptada.alAceptarPeticion(response);
                else
                    eventoPeticionRechazada.alRechazarOperacion();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}
