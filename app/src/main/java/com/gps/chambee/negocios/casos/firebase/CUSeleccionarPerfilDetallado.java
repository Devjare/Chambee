package com.gps.chambee.negocios.casos.firebase;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.negocios.presentadores.vistas.PresentadorPerfilDetallado;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWSeleccionarPerfilDetallado;

import org.json.JSONObject;

import java.util.List;

public class CUSeleccionarPerfilDetallado extends CasoUso {

    public CUSeleccionarPerfilDetallado(Context context, EventoPeticionAceptada eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWSeleccionarPerfilDetallado(
            context,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    PresentadorPerfilDetallado presentadorPerfilDetallado =
                            new PresentadorPerfilDetallado();
                    List<PerfilDetallado> perfilDetalladoList =
                            (List<PerfilDetallado>) presentadorPerfilDetallado.procesar(response);

                    eventoPeticionAceptada.alAceptarPeticion(perfilDetalladoList);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });
    }
}
