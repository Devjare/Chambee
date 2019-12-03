package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.entidades.ChatFirebase;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.presentadores.firebase.PresentadorFBListaChatsUsuarios;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFListarChatsUsuarios;

import java.util.List;

public class CFListarChatsUsuarios extends CasoUsoFirebase<List<UsuarioFirebase>> {

    private List<ChatFirebase> chats;

    public CFListarChatsUsuarios(List<ChatFirebase> chats, EventoPeticionAceptada<List<UsuarioFirebase>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);

        this.chats = chats;
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFListarChatsUsuarios(new ServicioFirebase.EventoTareaCompletada<DataSnapshot>() {

            @Override
            public void alCompletarTarea(DataSnapshot snapshot) {
                PresentadorFBListaChatsUsuarios presentador = new PresentadorFBListaChatsUsuarios(chats);
                List<UsuarioFirebase> usuarios = presentador.procesar(snapshot);
                eventoPeticionAceptada.alAceptarPeticion(usuarios);
            }

        }, new ServicioFirebase.EventoTareaCancelada() {

            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                eventoPeticionRechazada.alRechazarOperacion(databaseError);
            }

        });
    }
}
