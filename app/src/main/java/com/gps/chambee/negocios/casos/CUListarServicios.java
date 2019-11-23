package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.negocios.presentadores.vistas.PresentadorListaServicios;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWListarServicios;

import org.json.JSONObject;

import java.util.List;

public class CUListarServicios extends CasoUso<List<String>>{
    public CUListarServicios(Context context, EventoPeticionAceptada<List<String>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWListarServicios(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        PresentadorListaServicios presentadorListaServicios = new PresentadorListaServicios();
                        List<String> servicios = presentadorListaServicios.procesar(response);
                        eventoPeticionAceptada.alAceptarPeticion(servicios);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        }
        );
    }
}
