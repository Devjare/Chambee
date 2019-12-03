package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.entidades.ChatFirebase;
import com.gps.chambee.negocios.presentadores.firebase.PresentadorFBListaChats;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFListarChats;

import java.util.List;

public class CFListarChats extends CasoUsoFirebase<List<ChatFirebase>> {

    private String idEmisor;

    public CFListarChats(String idEmisor, EventoPeticionAceptada<List<ChatFirebase>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);

        this.idEmisor = idEmisor;
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFListarChats(idEmisor, new ServicioFirebase.EventoTareaCompletada<DataSnapshot>() {

            @Override
            public void alCompletarTarea(DataSnapshot snapshot) {
                PresentadorFBListaChats presentador = new PresentadorFBListaChats();
                List<ChatFirebase> chats = presentador.procesar(snapshot);
                eventoPeticionAceptada.alAceptarPeticion(chats);
            }

        }, new ServicioFirebase.EventoTareaCancelada() {

            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                eventoPeticionRechazada.alRechazarOperacion(databaseError);
            }

        });
    }
}
