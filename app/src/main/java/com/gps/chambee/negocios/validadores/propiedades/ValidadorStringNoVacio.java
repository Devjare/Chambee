package com.gps.chambee.negocios.validadores.propiedades;

import com.gps.chambee.negocios.validadores.Validador;

public class ValidadorStringNoVacio extends Validador<String> {

    public ValidadorStringNoVacio(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {

        agregarValidacion(new ValidadorPropiedad() {

            @Override
            public boolean validar() {
                return !t.trim().isEmpty();
            }

        }, new ErrorValidacion() {

            @Override
            public String mensajeError() {
                return "El campo no puede estar vacio";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });
    }
}
