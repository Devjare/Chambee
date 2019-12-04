package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class
SWRegistrarUsuario extends ServicioWebEscritura {
    public SWRegistrarUsuario(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(context, listener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        Usuario user = (Usuario) args[0];

        Map<String, String> params = new HashMap<>();
        params.put("nombre", user.getNombre());
        params.put("apellidos", user.getApellidos());
        params.put("correo_electronico", user.getCorreoElectronico());
        params.put("telefono", user.getTelefono());
        params.put("contrasena", user.getContrasenia());

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return "chabee.online/escritura/serviceweb_alta_usuarios.php";
    }

    @Override
    protected Request definirRequest(String url, Object... args) {
        Usuario usuario = (Usuario) args[0];

        String contrasena = args[1].toString();
        return null;




    }
}