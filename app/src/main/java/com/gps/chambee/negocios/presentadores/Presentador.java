package com.gps.chambee.negocios.presentadores;

import org.json.JSONObject;

public abstract class Presentador<T>{
    public abstract T procesar(JSONObject json);
}

