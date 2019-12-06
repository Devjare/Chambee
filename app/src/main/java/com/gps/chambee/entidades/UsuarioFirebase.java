package com.gps.chambee.entidades;

public class UsuarioFirebase extends EntidadChambee {

    public class Builder {
        private String apellidos;
        private String contrasena;
        private String correo;
        private String id;
        private String nombres;
        private String telefono;

        public Builder() { }

        public Builder setApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder setContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public UsuarioFirebase build() {
            return new UsuarioFirebase(this);
        }
    }

    private String id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String telefono;

    public UsuarioFirebase() {}

    private UsuarioFirebase(Builder builder) {
        this.apellidos = builder.apellidos;
        this.contrasena = builder.contrasena;
        this.correo = builder.correo;
        this.id = builder.id;
        this.nombres = builder.nombres;
        this.telefono = builder.telefono;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "UsuarioFirebase{" +
                "apellidos='" + apellidos + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
