package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorUsuario  {

    public Usuario procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonObject != null;
        String nombre= jsonObject.optString("nombre");
        String apellidos = jsonObject.optString("apellidos");
        String correoElectronico = jsonObject.optString("correoElectronico");
        String contrasenia = jsonObject.optString("contrasenia");
        String telefono = jsonObject.optString("telefono");


        return new Usuario.UsuarioBuilder()
                .setNombre(nombre)
                .setApellidos(apellidos)
                .setCorreoElectronico(correoElectronico)
                .setContrasenia(contrasenia)
                .setTelefono(telefono)
                .build();
    }
}
