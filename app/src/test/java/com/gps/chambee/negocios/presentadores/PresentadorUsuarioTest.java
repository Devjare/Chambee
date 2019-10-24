package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Usuario;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class PresentadorUsuarioTest {

    @Test
    public void procesarInvalido() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombre", "Andres");
        jsonObject.put("apellidos", "Reyna");
        jsonObject.put("correoElectronico", "andresreyna@gmail.com");
        jsonObject.put("contrasenia", "contrasenia");
        jsonObject.put("telefono", "1234567890");

        Usuario expected = new Usuario.UsuarioBuilder()
                .setNombre("Andres").setApellidos("Reyna")
                .setContrasenia("contrasenia")
                .setCorreoElectronico("andresreyna@gmail.com")
                .setTelefono("1234567890").build();

        PresentadorUsuario presentadorUsuario = new PresentadorUsuario();
        Usuario actual = presentadorUsuario.procesar(jsonObject);

        assertEquals(expected, actual);
    }

    @Test
    public void procesarValido() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombre", "Andres");
        jsonObject.put("apellidos", "Reyna");
        jsonObject.put("correoElectronico", "andresreyna@gmail.com");
        jsonObject.put("contrasenia", "contrasenia");
        jsonObject.put("telefono", "1234567890");

        Usuario expected = new Usuario.UsuarioBuilder()
                .setNombre("Andres").setApellidos("Reyna")
                .setContrasenia("contrasenia")
                .setCorreoElectronico("andresreyna@gmail.com")
                .setTelefono("1234567890").build();

        PresentadorUsuario presentadorUsuario = new PresentadorUsuario();
        Usuario actual = presentadorUsuario.procesar(jsonObject);

        assertEquals(expected, actual);
    }
}