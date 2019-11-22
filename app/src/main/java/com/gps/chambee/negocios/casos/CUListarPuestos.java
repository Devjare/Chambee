package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.Puesto;
import com.gps.chambee.negocios.presentadores.vistas.PresentadorListaPuestos;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWListarPuestos;

import org.json.JSONObject;

import java.util.List;

public class CUListarPuestos extends CasoUso<List<Puesto>>{
    public CUListarPuestos(Context context, EventoPeticionAceptada<List<Puesto>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWListarPuestos(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        PresentadorListaPuestos presentadorPuesto = new PresentadorListaPuestos();
                        List<Puesto> puestos = presentadorPuesto.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(puestos);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}
