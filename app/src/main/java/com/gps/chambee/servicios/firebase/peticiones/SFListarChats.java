package com.gps.chambee.servicios.firebase.peticiones;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.servicios.firebase.ServicioFirebaseLectura;

public class SFListarChats extends ServicioFirebaseLectura {

    public SFListarChats(EventoTareaCompletada<DataSnapshot> eventoTareaCompletada, EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }

    @Override
    public void ejecutarTarea(Object... args) {

    }

}
