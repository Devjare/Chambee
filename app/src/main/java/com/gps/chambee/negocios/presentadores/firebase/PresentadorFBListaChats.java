package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.ChatFirebase;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBListaChats extends PresentadorFirebase<List<ChatFirebase>> {

    @Override
    public List<ChatFirebase> procesar(DataSnapshot snapshot) {
        List<ChatFirebase> chats = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            ChatFirebase chat = dataSnapshot.getValue(ChatFirebase.class);
            chats.add(chat);
        }

        return chats;
    }

}
