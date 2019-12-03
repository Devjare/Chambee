package com.gps.chambee.entidades;

public class MensajeFirebase {

    public static class Builder {

        private String emisor;
        private String recepetor;
        private String mensaje;
        private int tipoVista;

        public Builder() { }

        public Builder setEmisor(String emisor) {
            this.emisor = emisor;
            return this;
        }

        public Builder setRecepetor(String recepetor) {
            this.recepetor = recepetor;
            return this;
        }

        public Builder setMensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        public Builder setTipoVista(int tipoVista) {
            this.tipoVista = tipoVista;
            return this;
        }

        public MensajeFirebase build() {
            return new MensajeFirebase(this);
        }
    }

    private String emisor;
    private String recepetor;
    private String mensaje;
    private int tipoVista;

    public MensajeFirebase() { }

    public MensajeFirebase(Builder builder) {
        this.emisor = builder.emisor;
        this.recepetor = builder.recepetor;
        this.mensaje = builder.mensaje;
        this.tipoVista = builder.tipoVista;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getRecepetor() {
        return recepetor;
    }

    public void setRecepetor(String recepetor) {
        this.recepetor = recepetor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoVista() {
        return tipoVista;
    }

    public void setTipoVista(int tipoVista) {
        this.tipoVista = tipoVista;
    }

    @Override
    public String toString() {
        return "MensajeFirebase{" +
                "emisor='" + emisor + '\'' +
                ", recepetor='" + recepetor + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", tipoVista=" + tipoVista +
                '}';
    }
}
