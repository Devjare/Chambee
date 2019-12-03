package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.entidades.MensajeFirebase;
import com.gps.chambee.negocios.presentadores.firebase.PresentadorFBListaMensajes;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFListarMensajes;

import java.util.List;

public class CFListarMensajes extends CasoUsoFirebase<List<MensajeFirebase>> {

    private String idEmisor;
    private String idReceptor;

    public CFListarMensajes(String idEmisor, String idReceptor, EventoPeticionAceptada<List<MensajeFirebase>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);

        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFListarMensajes(new ServicioFirebase.EventoTareaCompletada<DataSnapshot>() {
            @Override
            public void alCompletarTarea(DataSnapshot snapshot) {

                PresentadorFBListaMensajes presentador = new PresentadorFBListaMensajes(idEmisor, idReceptor);
                List<MensajeFirebase> mensajes = presentador.procesar(snapshot);

                eventoPeticionAceptada.alAceptarPeticion(mensajes);
            }
        }, new ServicioFirebase.EventoTareaCancelada() {
            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                eventoPeticionRechazada.alRechazarOperacion(databaseError);
            }
        });
    }
}
