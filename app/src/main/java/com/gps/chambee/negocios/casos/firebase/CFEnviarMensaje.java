package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFEnviarMensaje;

public class CFEnviarMensaje extends CasoUsoFirebase<String> {

    public CFEnviarMensaje(EventoPeticionAceptada<String> eventoPeticionAceptada,
                           EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFEnviarMensaje(new ServicioFirebase.EventoTareaCompletada<String>() {
            @Override
            public void alCompletarTarea(String s) {
                eventoPeticionAceptada.alAceptarPeticion(s);
            }
        }, new ServicioFirebase.EventoTareaCancelada() {
            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                eventoPeticionRechazada.alRechazarOperacion(databaseError);
            }
        });
    }
}

