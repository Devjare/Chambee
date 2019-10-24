package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Localidad implements Parcelable{
    private long id;
    private String idPais;
    private String idEstado;
    private String idCiudad;

    public Localidad(LocalidadBuilder builder){
        this.id = builder.id;
        this.idPais = builder.idPais;
        this.idCiudad = builder.idCiudad;
        this.idEstado = builder.idEstado;
    }

    protected Localidad(Parcel in) {
        id = in.readLong();
        idPais = in.readString();
        idEstado = in.readString();
        idCiudad = in.readString();
    }

    public static final Creator<Localidad> CREATOR = new Creator<Localidad>() {
        @Override
        public Localidad createFromParcel(Parcel in) {
            return new Localidad(in);
        }

        @Override
        public Localidad[] newArray(int size) {
            return new Localidad[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(idPais);
        dest.writeString(idEstado);
        dest.writeString(idCiudad);
    }

    public static class LocalidadBuilder{
        private long id;
        private String idPais;
        private String idEstado;
        private String idCiudad;

        public LocalidadBuilder(){ }

        public LocalidadBuilder setId(long id){
            this.id = id;
            return this;
        }
        public LocalidadBuilder setIdPais(String idPais){
            this.idPais = idPais;
            return this;
        }
        public LocalidadBuilder setIdEstado(String idEstado){
            this.idEstado = idEstado;
            return this;
        }
        public LocalidadBuilder setIdCiudad(String idCiudad){
            this.idCiudad = idCiudad;
            return this;
        }

        public Localidad build(){
            return new Localidad(this);
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getIdPais() {
        return idPais;
    }
    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }
}
