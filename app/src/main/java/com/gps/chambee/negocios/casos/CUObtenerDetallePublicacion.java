package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.DetallePublicacion;
import com.gps.chambee.negocios.presentadores.PresentadorDetallesPublicacion;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWObtenerDetallePublicacion;

import org.json.JSONObject;

public class CUObtenerDetallePublicacion extends CasoUso<DetallePublicacion> {

    public CUObtenerDetallePublicacion(
            Context context,
            EventoPeticionAceptada<DetallePublicacion> eventoPeticionAceptada,
            EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {

        return new SWObtenerDetallePublicacion(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        PresentadorDetallesPublicacion presentador = new PresentadorDetallesPublicacion();
                        DetallePublicacion publicacion = presentador.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(publicacion);
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
