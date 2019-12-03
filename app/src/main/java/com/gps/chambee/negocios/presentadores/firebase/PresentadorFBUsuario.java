package com.gps.chambee.negocios.presentadores.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBUsuario extends PresentadorFirebase<List<Usuario>> {

    @Override
    public List<Usuario> procesar(DataSnapshot snapshot) {

        List<Usuario> usuarios = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            UsuarioFirebase usuarioFirebase = dataSnapshot.getValue(UsuarioFirebase.class);

            Log.e("chambee", "Usuario obtenido: " + usuarioFirebase.toString());

            Usuario usuario = new Usuario.UsuarioBuilder()
                    .setApellidos(usuarioFirebase.getApellidos())
                    .setContrasenia(usuarioFirebase.getContrasena())
                    .setCorreoElectronico(usuarioFirebase.getCorreo())
                    .setId(usuarioFirebase.getId())
                    .setNombre(usuarioFirebase.getNombres())
                    .setTelefono(usuarioFirebase.getTelefono())
                    .build();

            Log.e("chambee", "Usuario procesado: " + usuario.toString());

            usuarios.add(usuario);
        }

        return usuarios;
    }
}
