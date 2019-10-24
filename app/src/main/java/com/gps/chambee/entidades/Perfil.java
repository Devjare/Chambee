package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Perfil implements Parcelable {
    public String urlPerfil;
    public String urlPortada;
    public String oficio;
    public String acerca;
    public String fechaNacimiento;
    public long id;
    public int idLocalidad;
    public String idUsuario;
    public String idColonia;
    public String idCalle;
    public float calificacion;

    protected Perfil(Parcel in) {
        urlPerfil = in.readString();
        urlPortada = in.readString();
        oficio = in.readString();
        acerca = in.readString();
        fechaNacimiento = in.readString();
        id = in.readLong();
        idLocalidad = in.readInt();
        idUsuario = in.readString();
        idColonia = in.readString();
        idCalle = in.readString();
        calificacion = in.readFloat();
    }

    public static final Creator<Perfil> CREATOR = new Creator<Perfil>() {
        @Override
        public Perfil createFromParcel(Parcel in) {
            return new Perfil(in);
        }

        @Override
        public Perfil[] newArray(int size) {
            return new Perfil[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlPerfil);
        dest.writeString(urlPortada);
        dest.writeString(oficio);
        dest.writeString(acerca);
        dest.writeString(fechaNacimiento);
        dest.writeLong(id);
        dest.writeInt(idLocalidad);
        dest.writeString(idUsuario);
        dest.writeString(idColonia);
        dest.writeString(idCalle);
        dest.writeFloat(calificacion);
    }

    public static class PerfilBuilder{

        public String urlPerfil;
        public String urlPortada;
        public String oficio;
        public String acerca;
        public String fechaNacimiento;
        public long id;
        public int idLocalidad;
        public String idUsuario;
        public String idCalle;
        public String idColonia;
        public float calificacion;

        public PerfilBuilder(){ }

        public PerfilBuilder setCalificacion(float calificacion){
            this.calificacion = calificacion;
            return this;
        }
        public PerfilBuilder setIdUsuario(String idUsuario){
            this.idUsuario = idUsuario;
            return this;
        }
        public PerfilBuilder setOficio(String oficio){
            this.oficio = oficio;
            return this;
        }
        public PerfilBuilder setUrlPortada(String urlPortada){
            this.urlPortada = urlPortada;
            return  this;
        }
        public PerfilBuilder setIdColonia(String idColonia){
            this.idColonia = idColonia;
            return this;
        }
        public PerfilBuilder setIdCalle(String idCalle){
            this.idCalle = idCalle;
            return this;
        }
        public PerfilBuilder setUrlPerfil(String urlPerfil){
            this.urlPerfil = urlPerfil;
            return this;
        }
        public PerfilBuilder setFechaNacimiento(String fechaNacimiento){
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }
        public PerfilBuilder setIdLocalidad(int idLocalidad){
            this.idLocalidad = idLocalidad;
            return this;
        }
        public PerfilBuilder setAcerca(String acerca){
            this.acerca = acerca;
            return this;
        }
        public PerfilBuilder setId(long id){
            this.id = id;
            return this;
        }

        public Perfil build(){
            return new Perfil(this);
        }
    }

    public Perfil(PerfilBuilder builder){
        this.urlPerfil = builder.urlPerfil;
        this.idLocalidad = builder.idLocalidad;
        this.acerca = builder.acerca;
        this.urlPortada = builder.urlPortada;
        this.calificacion = builder.calificacion;
        this.idColonia = builder.idColonia;
        this.idCalle = builder.idCalle;
        this.idUsuario = builder.idUsuario;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.oficio = builder.oficio;
        this.id = builder.id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlPerfil() {
        return urlPerfil;
    }

    public void setUrlPerfil(String urlPerfil) {
        this.urlPerfil = urlPerfil;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getAcerca() {
        return acerca;
    }

    public void setAcerca(String acerca) {
        this.acerca = acerca;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(String idColonia) {
        this.idColonia = idColonia;
    }

    public String getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(String idCalle) {
        this.idCalle = idCalle;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
