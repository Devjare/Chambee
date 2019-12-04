package com.gps.chambee.negocios.validadores;

import com.gps.chambee.entidades.Usuario;

import java.util.regex.Pattern;

public class ValidadorCorreo extends Validador<String> {


    public ValidadorCorreo(String s) {
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
                return "El correo no puede estár vacío";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });
        agregarValidacion(new ValidadorPropiedad() {


            @Override
            public boolean validar() {
                Pattern regex = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                return regex.matcher(t.trim()).find();


            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El correo electronico debe de tener el siguiente formato: ejemplo@dominio.com";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

    }
}
