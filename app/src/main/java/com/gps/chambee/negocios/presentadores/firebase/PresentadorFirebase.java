package com.gps.chambee.negocios.presentadores.firebase;

import com.google.firebase.database.DataSnapshot;

public abstract class PresentadorFirebase<T> {

    public abstract T procesar(DataSnapshot snapshot);

}
