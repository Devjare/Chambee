package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.MedallasPerfil;
import com.gps.chambee.negocios.presentadores.PresentadorListaMedallasPerfil;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWListarMedallasPerfil;

import org.json.JSONObject;

import java.util.List;

public class CUListarMedallasPerfil extends CasoUso<List<MedallasPerfil>> {

    public CUListarMedallasPerfil(Context context, EventoPeticionAceptada<List<MedallasPerfil>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWListarMedallasPerfil(context, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                PresentadorListaMedallasPerfil presentador = new PresentadorListaMedallasPerfil();
                List<MedallasPerfil> medallas = presentador.procesar(response);
                eventoPeticionAceptada.alAceptarPeticion(medallas);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}
