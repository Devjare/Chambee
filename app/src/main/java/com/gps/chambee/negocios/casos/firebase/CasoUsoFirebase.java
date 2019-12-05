package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.servicios.firebase.ServicioFirebase;

public abstract class CasoUsoFirebase<T> {

    public interface EventoPeticionAceptada<T> {
        void alAceptarPeticion(T t);
    }

    public interface EventoPeticionRechazada {
        void alRechazarOperacion(DatabaseError databaseError);
    }

    protected EventoPeticionAceptada<T> eventoPeticionAceptada;
    protected EventoPeticionRechazada eventoPeticionRechazada;

    public CasoUsoFirebase(EventoPeticionAceptada<T> eventoPeticionAceptada,
                           EventoPeticionRechazada eventoPeticionRechazada) {
        this.eventoPeticionAceptada = eventoPeticionAceptada;
        this.eventoPeticionRechazada = eventoPeticionRechazada;
    }

    protected abstract ServicioFirebase definirServicioFirebase();

    public void enviarPeticion(Object...args) {
        ServicioFirebase servicioFirebase = definirServicioFirebase();
        servicioFirebase.ejecutarTarea(args);
    }

}

