package com.gps.chambee.negocios.validadores;

import java.util.regex.Pattern;

public class ValidadorNombreUsuario extends Validador<String> {

    public ValidadorNombreUsuario(String s) {
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
                return "No puede estar vacío el nombre de usuario";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

        //validar que tenga solo letras

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String nombre = t;
                Pattern regex = Pattern.compile("[^a-zA-Z]");
                return regex.matcher(nombre).find();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El nombre solo puede contener letras de la 'A' a la 'Z'";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String nombre = t;
                Pattern regex = Pattern.compile("^[a-zA-Z0-9_-]{3,16}$");
                return regex.matcher(nombre).find();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "solo se puede ingresar letras,numberos y _";
            }

            @Override
            public Object propiedadInvalida() {
                return t;
            }
        });

    }
}

