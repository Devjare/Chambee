package com.gps.chambee.negocios.validadores.propiedades;

import com.gps.chambee.negocios.validadores.Validador;

public class ValidadorContrasenia extends Validador<String> {

    public ValidadorContrasenia(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.isEmpty();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "La contraseña no puede estar vacía";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return t.length() >= 6;
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "La contraseña debe contener al menos 6 caracteres ";
            }
            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });
    }
}
