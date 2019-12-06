package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;
import com.gps.chambee.negocios.presentadores.PresentadorListaComentariosPublicacion;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWObtenerComentariosPublicacion;

import org.json.JSONObject;

import java.util.List;

public class CUObtenerComentariosPublicacion extends CasoUso<List<ComentarioPublicacion>> {
    public CUObtenerComentariosPublicacion(Context context, EventoPeticionAceptada<List<ComentarioPublicacion>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWObtenerComentariosPublicacion(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        PresentadorListaComentariosPublicacion presentador = new PresentadorListaComentariosPublicacion();
                        List<ComentarioPublicacion> comentarioPublicacions = presentador.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(comentarioPublicacions);
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
