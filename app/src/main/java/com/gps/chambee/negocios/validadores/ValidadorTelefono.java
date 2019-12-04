package com.gps.chambee.negocios.validadores;

import java.util.regex.Pattern;

public class ValidadorTelefono extends Validador<String> {
    public ValidadorTelefono(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return t.isEmpty();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El campo de teléfono no puede estar vacío";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {

                Pattern regex = Pattern.compile("^/d(?:-/d{3}){3}/d$");
                return regex.matcher(t).find();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "Formato de télefono incorrecto, debe contener 10 números y no contener letras";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });



    }
}
