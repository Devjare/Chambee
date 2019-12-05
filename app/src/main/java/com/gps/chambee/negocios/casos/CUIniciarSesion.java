package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.negocios.presentadores.PresentadorUsuario;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWIniciarSesion;

import org.json.JSONObject;

public class CUIniciarSesion  extends CasoUso<Usuario> {
    public CUIniciarSesion(Context context,
                           EventoPeticionAceptada<Usuario> eventoPeticionAceptada,
                           EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {

        return  new SWIniciarSesion(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                PresentadorUsuario presentadorUsuario = new PresentadorUsuario();
                Usuario usuario = presentadorUsuario.procesar(response);
                eventoPeticionAceptada.alAceptarPeticion(usuario);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventoPeticionRechazada.alRechazarOperacion();
            }
        });

    }
}

