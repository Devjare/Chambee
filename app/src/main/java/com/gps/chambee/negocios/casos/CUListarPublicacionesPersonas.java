package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.PublicacionPersona;
import com.gps.chambee.negocios.presentadores.PresentadorListaPublicacionPersona;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWSeleccionarPublicacionPersona;

import org.json.JSONObject;

import java.util.List;

public class CUListarPublicacionesPersonas extends CasoUso{

    public CUListarPublicacionesPersonas(Context context, EventoPeticionAceptada eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWSeleccionarPublicacionPersona(
                context,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                PresentadorListaPublicacionPersona presentadorListaPublicacionEmpresa =
                        new PresentadorListaPublicacionPersona();
                List<PublicacionPersona> publicaciones =
                        presentadorListaPublicacionEmpresa.procesar(response);

                eventoPeticionAceptada.alAceptarPeticion(publicaciones);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}