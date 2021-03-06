package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.servicios.web.ServicioWebLectura;
import com.gps.chambee.servicios.web.Servidor;

import org.json.JSONObject;

public class SWIniciarSesion extends ServicioWebLectura {

    public SWIniciarSesion(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected String definirUrl(Object... args) {
        String contrasena = args[1].toString();
        int tipoInicio =  Integer.parseInt( args[2].toString());

        switch (tipoInicio) {
            case 2: {
                String nombre = args[0].toString();
                return context.getString(R.string.login) + "?" + nombre + "?" + contrasena;
            }

            case 1: {
                String correo = args[0].toString();
                return " " + correo + " " + contrasena;
            }

            case 3: {
                String telefono = args[0].toString();
                return "" + telefono + "" + contrasena;
            }

            default: return null;
        }
    }
}
