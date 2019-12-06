package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Response;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWRegistrarUsuario extends ServicioWebEscritura {

    public SWRegistrarUsuario(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(context, listener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        UsuarioFirebase user = (UsuarioFirebase) args[0];

        Map<String, String> params = new HashMap<>();
        params.put("id_usuarios", user.getId());
        params.put("nombre", user.getNombres());
        params.put("apellidos", user.getApellidos());
        params.put("telefono", user.getTelefono());
        params.put("correo_electronico", user.getCorreo());
        params.put("contrasena", user.getContrasena());

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return "http://chambee.online/chambee_php/escritura/alta/serviceweb_registrar_usuarios.php";
    }

}
