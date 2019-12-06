package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.negocios.presentadores.vistas.PresentadorPerfilDetallado;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWSeleccionarPerfilDetallado;

import org.json.JSONObject;

public class CUSeleccionarPerfilDetallado extends CasoUso<PerfilDetallado> {

    public CUSeleccionarPerfilDetallado(Context context, EventoPeticionAceptada<PerfilDetallado> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWSeleccionarPerfilDetallado(context, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                PresentadorPerfilDetallado presentador = new PresentadorPerfilDetallado();
                PerfilDetallado perfilDetallado = presentador.procesar(response);
                eventoPeticionAceptada.alAceptarPeticion(perfilDetallado);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}
