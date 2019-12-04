package com.gps.chambee.negocios.validadores;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPool {

    public static class Builder {

        private List<Validador> validadores = new ArrayList<>();

        public Builder agregarValidador(Validador validador) {
            validadores.add(validador);
            return this;
        }

        public ValidadorPool build() {
            return new ValidadorPool(this);
        }
    }

    private List<Validador> validadores = new ArrayList<>();
    private Validador.ErrorValidacion ultimoError;

    private ValidadorPool() { }

    private ValidadorPool(Builder builder) {
        this.validadores = builder.validadores;
    }

    public Validador.ErrorValidacion ultimoError() {
        return ultimoError;
    }

    public boolean validarTodo() {
        for (int index = 0; index < validadores.size(); index++) {
            Validador validadorActual = validadores.get(index);

            if (!validadorActual.validar()) {
                ultimoError = validadorActual.ultimoError();
                return false;
            }
        }

        ultimoError = null;
        return true;
    }
}
