package com.gps.chambee.entidades.vistas;

public class MedallasPerfil {

    public static class Builder {

        private int idPerfil;
        private int idMedalla;
        private String medalla;
        private String descripcion;
        private String urlMedalla;
        private int cantidad;

        public Builder setIdPerfil(int idPerfil) {
            this.idPerfil = idPerfil;
            return this;
        }

        public Builder setIdMedalla(int idMedalla) {
            this.idMedalla = idMedalla;
            return this;
        }

        public Builder setMedalla(String medalla) {
            this.medalla = medalla;
            return this;
        }

        public Builder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder setUrlMedalla(String urlMedalla) {
            this.urlMedalla = urlMedalla;
            return this;
        }

        public Builder setCantidad(int cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public MedallasPerfil build() {
            return new MedallasPerfil(this);
        }
    }

    private int idPerfil;
    private int idMedalla;
    private String medalla;
    private String descripcion;
    private String urlMedalla;
    private int cantidad;

    private MedallasPerfil() { }

    private MedallasPerfil(Builder builder) {
        this.idPerfil = builder.idPerfil;
        this.idMedalla = builder.idMedalla;
        this.medalla = builder.medalla;
        this.descripcion = builder.descripcion;
        this.urlMedalla = builder.urlMedalla;
        this.cantidad = builder.cantidad;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(int idMedalla) {
        this.idMedalla = idMedalla;
    }

    public String getMedalla() {
        return medalla;
    }

    public void setMedalla(String medalla) {
        this.medalla = medalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlMedalla() {
        return urlMedalla;
    }

    public void setUrlMedalla(String urlMedalla) {
        this.urlMedalla = urlMedalla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
