package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.negocios.presentadores.PresentadorUsuario;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWIniciarSesion;

import org.json.JSONException;
import org.json.JSONObject;

public class CUIniciarSesion  extends CasoUso<String> {
    public CUIniciarSesion(Context context, EventoPeticionAceptada<String> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {

        return  new SWIniciarSesion(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject res;
                String usuario="";

                try {
                    res = response.getJSONArray("respuestas").getJSONObject(0);
                    usuario = res.optString("respuesta");

                } catch (JSONException e) {
                    e.printStackTrace();

                }



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
