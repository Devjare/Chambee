package com.gps.chambee.negocios.casos.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.presentadores.firebase.PresentadorFBListaUsuariosFirebase;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFListarUsuariosFirebase;

import java.util.List;

public class CFListarUsuariosFirebase extends CasoUsoFirebase<List<UsuarioFirebase>> {

    public CFListarUsuariosFirebase(EventoPeticionAceptada<List<UsuarioFirebase>> eventoPeticionAceptada, EventoPeticionRechazada eventoPeticionRechazada) {
        super(eventoPeticionAceptada, eventoPeticionRechazada);
    }

    @Override
    protected ServicioFirebase definirServicioFirebase() {
        return new SFListarUsuariosFirebase(new ServicioFirebase.EventoTareaCompletada<DataSnapshot>() {

            @Override
            public void alCompletarTarea(DataSnapshot snapshot) {
                PresentadorFBListaUsuariosFirebase presentador = new PresentadorFBListaUsuariosFirebase();
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
