package com.gps.chambee.servicios.firebase.peticiones;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gps.chambee.servicios.firebase.ServicioFirebaseEscritura;

import java.util.HashMap;
import java.util.Map;

public class SFEnviarMensaje extends ServicioFirebaseEscritura {

    public SFEnviarMensaje(EventoTareaCompletada<String> eventoTareaCompletada, EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }

    @Override
    public void ejecutarTarea(Object... args) {

        // guardar mensaje

        final String emisor = args[0].toString();
        final String receptor = args[1].toString();
        String mensaje = args[2].toString();

        Map<String, String> datos = new HashMap<>();
        datos.put("emisor", emisor);
        datos.put("recepetor", receptor);
        datos.put("mensaje", mensaje);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("mensajes")
                .push()
                .setValue(datos);

        // si no existe el chat, guardarlo en la lista de chats del emisor

        final DatabaseReference chatsEmisor = FirebaseDatabase.getInstance()
                .getReference("chats")
                .child(emisor)
                .child(receptor);

        chatsEmisor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                    chatsEmisor.child("idUsuario").setValue(receptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventoTareaCancelada.alCancelarTarea(databaseError);
            }
        });

        // si no existe el chat, guardarlo en la lista de chats del receptor
        final DatabaseReference chatsReceptor = FirebaseDatabase.getInstance()
                .getReference("chats")
                .child(receptor)
                .child(emisor);

        chatsReceptor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                    chatsReceptor.child("idUsuario").setValue(emisor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventoTareaCancelada.alCancelarTarea(databaseError);
            }
        });

        eventoTareaCompletada.alCompletarTarea("exito");
    }
}

/*
* chats
*   nombreUsuario (chat del usuario)
*       nombreUsuario (chat con el usuario)
*           idUsuario (id del usuario con el que se tiene el chat)
*       nombreUsuario (chat con el usuario)
*           idUsuario (id del usuario con el que se tiene el chat)
*       nombreUsuario (chat con el usuario)
*           idUsuario (id del usuario con el que se tiene el chat)
* */
