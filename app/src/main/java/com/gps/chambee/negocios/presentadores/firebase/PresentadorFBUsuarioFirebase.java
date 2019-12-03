package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.UsuarioFirebase;

public class PresentadorFBUsuarioFirebase extends PresentadorFirebase<UsuarioFirebase> {

    @Override
    public UsuarioFirebase procesar(DataSnapshot snapshot) {
        return snapshot.getValue(UsuarioFirebase.class);
    }

}
