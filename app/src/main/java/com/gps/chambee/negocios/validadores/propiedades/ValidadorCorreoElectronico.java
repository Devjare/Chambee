package com.gps.chambee.negocios.validadores.propiedades;

import com.gps.chambee.negocios.validadores.Validador;

import java.util.regex.Pattern;

public class ValidadorCorreoElectronico extends Validador<String> {

    public ValidadorCorreoElectronico(String s) {
        super(s);
    }

    @Override
    protected void definirValidaciones() {
        agregarValidacion(new ValidadorPropiedad() {

            @Override
            public boolean validar() {
                Pattern regex = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                return regex.matcher(t).find();
            }

        }, new ErrorValidacion() {

            @Override
            public String mensajeError() {
                return "El correo debe de tener el siguiente formato: correo@dominio.com";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });
    }
}
