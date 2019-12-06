package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.negocios.presentadores.PresentadorListaCategoriasPublicacion;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.implementaciones.SWObtenerCategoriasPublicacion;

import org.json.JSONObject;

import java.util.List;

public class CUObtenerCategoriasPublicacion extends CasoUso<List<Categoria>> {
    public CUObtenerCategoriasPublicacion(Context context, EventoPeticionAceptada<List<Categoria>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(context, eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioWeb definirServicioWeb() {
        return new SWObtenerCategoriasPublicacion(
                context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        PresentadorListaCategoriasPublicacion presentador = new PresentadorListaCategoriasPublicacion();
                        List<Categoria> categorias = presentador.procesar(response);

                        eventoPeticionAceptada.alAceptarPeticion(categorias);
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
