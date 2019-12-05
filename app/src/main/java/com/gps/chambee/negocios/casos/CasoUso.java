package com.gps.chambee.negocios.casos;

import android.content.Context;

import com.gps.chambee.servicios.web.ServicioWeb;

public abstract class CasoUso<T> {

    public interface EventoPeticionAceptada<T> {
        void alAceptarPeticion(T t);
    }

    public interface EventoPeticionRechazada {
        void alRechazarOperacion();
    }

    protected Context context;
    protected EventoPeticionAceptada<T> eventoPeticionAceptada;
    protected EventoPeticionRechazada eventoPeticionRechazada;

    public CasoUso(Context context,
                   EventoPeticionAceptada<T> eventoPeticionAceptada,
                   EventoPeticionRechazada eventoPeticionRechazada) {
        this.context = context;
        this.eventoPeticionAceptada = eventoPeticionAceptada;
        this.eventoPeticionRechazada = eventoPeticionRechazada;
    }

    protected abstract ServicioWeb definirServicioWeb();

    public void enviarPeticion(Object... args) {
        ServicioWeb servicioWeb = definirServicioWeb();
        servicioWeb.enviarPeticion(args);
    }
}

