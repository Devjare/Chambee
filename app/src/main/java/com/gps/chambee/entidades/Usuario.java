package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String contrasenia;
    private String telefono;
    private int id;

    private Usuario(UsuarioBuilder builder){
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.correoElectronico = builder.correoElectronico;
        this.telefono = builder.telefono;
        this.contrasenia = builder.contrasenia;
    }

    protected Usuario(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        correoElectronico = in.readString();
        contrasenia = in.readString();
        telefono = in.readString();
        id = in.readInt();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(correoElectronico);
        dest.writeString(contrasenia);
        dest.writeString(telefono);
        dest.writeInt(id);
    }

    public static class UsuarioBuilder{
        private String nombre;
        private String apellidos;
        private String correoElectronico;
        private String contrasenia;
        private String telefono;
        private int id;

        public UsuarioBuilder(){  }

        public UsuarioBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UsuarioBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public UsuarioBuilder setApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public UsuarioBuilder setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return  this;
        }

        public UsuarioBuilder setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
            return this;
        }

        public UsuarioBuilder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Usuario build(){
            return new Usuario(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
