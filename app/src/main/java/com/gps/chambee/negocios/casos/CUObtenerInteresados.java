package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.negocios.presentadores.PresentadorListaInteresados;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWObtenerInteresados;

import org.json.JSONObject;

import java.util.List;

public class CUObtenerInteresados extends CasoUso<List<Perfil>> {
    public CUObtenerInteresados(Context context, EventoPeticionAceptada<List<Perfil>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWObtenerInteresados(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        PresentadorListaInteresados presentador = new PresentadorListaInteresados();
                        List<Perfil> interesados = presentador.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(interesados);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        eventoPeticionRechazada.alRechazarOperacion();
                    }
                }
        );
    }
}
