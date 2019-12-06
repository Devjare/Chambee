package com.gps.chambee.servicios.firebase;

public abstract class ServicioFirebaseEscritura extends ServicioFirebase {

    public ServicioFirebaseEscritura(EventoTareaCompletada<String> eventoTareaCompletada,
                                     EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }
}

