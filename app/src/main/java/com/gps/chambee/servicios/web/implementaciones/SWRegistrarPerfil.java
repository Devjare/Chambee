package com.gps.chambee.servicios.web.implementaciones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.servicios.web.ServicioWebEscritura;

import java.util.HashMap;
import java.util.Map;

public class SWRegistrarPerfil extends ServicioWebEscritura{
    public SWRegistrarPerfil(Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected Map<String, String> definirParams(Object... args) {
        String ciudad = args[0].toString();
        String estado = args[1].toString();
        String pais = args[2].toString();
        String calle = args[3].toString();
        String colonia = args[4].toString();
        String especialidad = args[5].toString();
        String acerca = args[6].toString();
        String imagenPortada = args[7].toString();
        String imagenPerfil = args[8].toString();
        String fechaNacimiento = args[9].toString();
        String idUsuarios = args[10].toString();

        Map<String, String> params = new HashMap<>();
        params.put("ciudad", ciudad);
        params.put("estado", estado);
        params.put("pais", pais);
        params.put("calle", calle);
        params.put("colonia", colonia);
        params.put("especialidad", especialidad);
        params.put("acerca", acerca);
        params.put("imagen_portada", imagenPortada);
        params.put("imagen_perfil", imagenPerfil);
        params.put("fecha_nacimiento", fechaNacimiento);
        params.put("id_usuarios", idUsuarios);

        return params;
    }

    @Override
    protected String definirUrl(Object... args) {
        return "http://chambee.online/chambee_php/escritura/alta/serviceweb_registrar_perfil.php";
    }
}
