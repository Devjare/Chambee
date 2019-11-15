package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.negocios.presentadores.firebase.PresentadorFBUsuario;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFListarUsuarios;

import java.util.List;

public class CFListarUsuarios extends CasoUsoFirebase<List<Usuario>> {

    public CFListarUsuarios(EventoPeticionAceptada<List<Usuario>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFListarUsuarios(new ServicioFirebase.EventoTareaCompletada<DataSnapshot>() {
            @Override
            public void alCompletarTarea(DataSnapshot snapshot) {
                PresentadorFBUsuario presentador = new PresentadorFBUsuario();
                List<Usuario> usuario = presentador.procesar(snapshot);
                eventoPeticionAceptada.alAceptarPeticion(usuario);
            }
        }, new ServicioFirebase.EventoTareaCancelada() {
            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                eventoPeticionRechazada.alRechazarOperacion(databaseError);
            }
        });
    }
}
