package com.gps.chambee.negocios.validadores.propiedades;

import com.gps.chambee.negocios.validadores.Validador;

public class ValidadorNumeroString extends Validador<String> {

    public ValidadorNumeroString(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {
        agregarValidacion(new ValidadorPropiedad() {

            @Override
            public boolean validar() {
                try {
                    Double.parseDouble(t);
                    return true;
                }
                catch (Exception ex) {
                    return false;
                }
            }

        }, new ErrorValidacion() {

            @Override
            public String mensajeError() {
                return null;
            }

            @Override
            public Object propiedadInvalida() {
                return null;
            }
        });
    }
}
