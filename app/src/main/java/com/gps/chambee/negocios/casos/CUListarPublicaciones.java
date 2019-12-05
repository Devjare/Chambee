package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.PublicacionGeneral;
import com.gps.chambee.negocios.presentadores.PresentadorListaPublicacionesGenerales;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWListarPublicaciones;

import org.json.JSONObject;

import java.util.List;

public class CUListarPublicaciones extends CasoUso<List<PublicacionGeneral>> {
    public CUListarPublicaciones(Context context, EventoPeticionAceptada<List<PublicacionGeneral>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWListarPublicaciones(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        PresentadorListaPublicacionesGenerales presentador = new PresentadorListaPublicacionesGenerales();
                        List<PublicacionGeneral> publicacionesGenerales = presentador.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(publicacionesGenerales);
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
