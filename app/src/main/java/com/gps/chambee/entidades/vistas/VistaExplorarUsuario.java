package com.gps.chambee.entidades.vistas;

public class VistaExplorarUsuario {

    public static class Builder {

        private String idUsuario;
        private String urlImagen;
        private String nombreUsuario;
        private String ciudad;

        public Builder() { }

        public Builder setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder setUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public Builder setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Builder setCiudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public VistaExplorarUsuario build() {
            return new VistaExplorarUsuario(this);
        }
    }

    private String idUsuario;
    private String urlImagen;
    private String nombreUsuario;
    private String ciudad;

    private VistaExplorarUsuario() { }

    private VistaExplorarUsuario(Builder builder) {
        this.urlImagen = builder.urlImagen;
        this.nombreUsuario = builder.nombreUsuario;
        this.ciudad = builder.ciudad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
