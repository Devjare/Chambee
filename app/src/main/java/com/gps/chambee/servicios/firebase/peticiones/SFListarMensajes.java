package com.gps.chambee.servicios.firebase.peticiones;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gps.chambee.servicios.firebase.ServicioFirebaseLectura;

public class SFListarMensajes extends ServicioFirebaseLectura {

    public SFListarMensajes(EventoTareaCompletada<DataSnapshot> eventoTareaCompletada, EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }

    @Override
    public void ejecutarTarea(Object... args) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("mensajes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventoTareaCompletada.alCompletarTarea(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventoTareaCancelada.alCancelarTarea(databaseError);
            }
        });
    }
}
