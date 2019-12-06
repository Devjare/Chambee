package com.gps.chambee.entidades.vistas;

public class PerfilDetallado {

    public static class Builder {

        private String idUsuario;
        private String nombreUsuario;
        private String apellidosUsuario;
        private String telefono;
        private String correo;
        private String contrasena;
        private int idPerfil;
        private String urlPerfil;
        private String urlPortada;
        private String acerca;
        private String fechaNac;
        private String puesto;
        private float calificacion;
        private String calle;
        private String colonia;
        private String ciudad;
        private String estado;
        private String pais;

        public Builder setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Builder setApellidosUsuario(String apellidosUsuario) {
            this.apellidosUsuario = apellidosUsuario;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder setContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder setIdPerfil(int idPerfil) {
            this.idPerfil = idPerfil;
            return this;
        }

        public Builder setUrlPerfil(String urlPerfil) {
            this.urlPerfil = urlPerfil;
            return this;
        }

        public Builder setUrlPortada(String urlPortada) {
            this.urlPortada = urlPortada;
            return this;
        }

        public Builder setAcerca(String acerca) {
            this.acerca = acerca;
            return this;
        }

        public Builder setFechaNac(String fechaNac) {
            this.fechaNac = fechaNac;
            return this;
        }

        public Builder setPuesto(String puesto) {
            this.puesto = puesto;
            return this;
        }

        public Builder setCalificacion(float calificacion) {
            this.calificacion = calificacion;
            return this;
        }

        public Builder setCalle(String calle) {
            this.calle = calle;
            return this;
        }

        public Builder setColonia(String colonia) {
            this.colonia = colonia;
            return this;
        }

        public Builder setCiudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public Builder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public Builder setPais(String pais) {
            this.pais = pais;
            return this;
        }

        public PerfilDetallado build() {
            return new PerfilDetallado(this);
        }
    }

    private String idUsuario;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String telefono;
    private String correo;
    private String contrasena;
    private int idPerfil;
    private String urlPerfil;
    private String urlPortada;
    private String acerca;
    private String fechaNac;
    private String puesto;
    private float calificacion;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;
    private String pais;

    private PerfilDetallado() { }

    public PerfilDetallado(Builder builder) {
        this.idUsuario = builder.idUsuario;
        this.nombreUsuario = builder.nombreUsuario;
        this.apellidosUsuario = builder.apellidosUsuario;
        this.telefono = builder.telefono;
        this.correo = builder.correo;
        this.contrasena = builder.contrasena;
        this.idPerfil = builder.idPerfil;
        this.urlPerfil = builder.urlPerfil;
        this.urlPortada = builder.urlPortada;
        this.acerca = builder.acerca;
        this.fechaNac = builder.fechaNac;
        this.puesto = builder.puesto;
        this.calificacion = builder.calificacion;
        this.calle = builder.calle;
        this.colonia = builder.colonia;
        this.ciudad = builder.ciudad;
        this.estado = builder.estado;
        this.pais = builder.pais;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getUrlPerfil() {
        return urlPerfil;
    }

    public void setUrlPerfil(String urlPerfil) {
        this.urlPerfil = urlPerfil;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getAcerca() {
        return acerca;
    }

    public void setAcerca(String acerca) {
        this.acerca = acerca;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
