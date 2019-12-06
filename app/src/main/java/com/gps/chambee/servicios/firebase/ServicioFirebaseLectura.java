package com.gps.chambee.servicios.firebase;

import com.google.firebase.database.DataSnapshot;

public abstract class ServicioFirebaseLectura extends ServicioFirebase {

    public ServicioFirebaseLectura(EventoTareaCompletada<DataSnapshot> eventoTareaCompletada,
                                   EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }
}

