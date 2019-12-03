package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.ChatFirebase;
import com.gps.chambee.entidades.UsuarioFirebase;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBListaChatsUsuarios extends PresentadorFirebase<List<UsuarioFirebase>> {

    private List<ChatFirebase> chats;

    public PresentadorFBListaChatsUsuarios(List<ChatFirebase> chats) {
        this.chats = chats;
    }

    @Override
    public List<UsuarioFirebase> procesar(DataSnapshot snapshot) {
        List<UsuarioFirebase> usuarios = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

            UsuarioFirebase usuarioFirebase = dataSnapshot.getValue(UsuarioFirebase.class);

            for (ChatFirebase chatFirebase : chats) {
                if (usuarioFirebase.getId().equals(chatFirebase.getIdUsuario())) {
                    usuarios.add(usuarioFirebase);
                }
            }
        }

        return usuarios;
    }
}
