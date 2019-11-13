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

        String emisor = args[0].toString();
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

        // guardar o actualizar chat

        final DatabaseReference chats = FirebaseDatabase.getInstance()
                .getReference("chats")
                .child(emisor)
                .child(receptor);

        chats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                    chats.child("idUsuario").setValue(receptor);
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
*       nombreUsuario (chat con el usuario)
*       nombreUsuario (chat con el usuario)
* */
