package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.UsuarioFirebase;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBListaUsuariosFirebase extends PresentadorFirebase<List<UsuarioFirebase>> {

    @Override
    public List<UsuarioFirebase> procesar(DataSnapshot snapshot) {
        List<UsuarioFirebase> usuarios = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            UsuarioFirebase usuario = dataSnapshot.getValue(UsuarioFirebase.class);
            usuarios.add(usuario);
        }

        return usuarios;
    }

}
