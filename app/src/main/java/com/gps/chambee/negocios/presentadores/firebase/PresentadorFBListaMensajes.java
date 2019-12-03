package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.MensajeFirebase;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBListaMensajes extends PresentadorFirebase<List<MensajeFirebase>> {

    private String idEmisor;
    private String idReceptor;

    public PresentadorFBListaMensajes(String idEmisor, String idReceptor) {
        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
    }

    @Override
    public List<MensajeFirebase> procesar(DataSnapshot snapshot) {
        List<MensajeFirebase> mensajes = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            MensajeFirebase mensajeFirebase = dataSnapshot.getValue(MensajeFirebase.class);

            assert mensajeFirebase != null;

            if (mensajeFirebase.getRecepetor().equals(idEmisor) && mensajeFirebase.getEmisor().equals(idReceptor) ||
                mensajeFirebase.getRecepetor().equals(idReceptor) && mensajeFirebase.getEmisor().equals(idEmisor))
            {
                mensajes.add(mensajeFirebase);
            }
        }

        return mensajes;
    }
}
