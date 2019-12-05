package com.gps.chambee.negocios.validadores.propiedades;

import com.gps.chambee.negocios.validadores.Validador;

public class ValidadorFecha extends Validador<String> {

    public ValidadorFecha(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {

        agregarValidacion(new ValidadorPropiedad() {

            @Override
            public boolean validar() {
                String[] tokens = t.split("-");

                try {
                    Integer.parseInt(tokens[0]);
                    Integer.parseInt(tokens[1]);
                    Integer.parseInt(tokens[2]);
                    return true;
                }
                catch (Exception ex) {
                    return false;
                }
            }

        }, new ErrorValidacion() {

            @Override
            public String mensajeError() {
                return "El formato de fecha debe ser: AAAA-DD-MM";
            }

            @Override
            public Object propiedadInvalida() {
                return null;
            }
        });
    }
}
