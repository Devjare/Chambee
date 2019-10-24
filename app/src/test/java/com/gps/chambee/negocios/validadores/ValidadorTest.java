package com.gps.chambee.negocios.validadores;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidadorTest {

    @Test
    public void validar() throws JSONException {
        int expected = 10;
        int actual = suma(5, 5);

        assertEquals(expected, actual);
    }

    public int suma(int a, int b){
        return a + b + 1;
    }
}