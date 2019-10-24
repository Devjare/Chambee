package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Ciudad implements Parcelable{
    private String nombre;
    private long id;

    public Ciudad(CiudadBuilder builder){
        this.nombre = builder.nombre;
        this.id = builder.id;
    }

    protected Ciudad(Parcel in) {
        nombre = in.readString();
        id = in.readLong();
    }

    public static final Creator<Ciudad> CREATOR = new Creator<Ciudad>() {
        @Override
        public Ciudad createFromParcel(Parcel in) {
            return new Ciudad(in);
        }

        @Override
        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeLong(id);
    }

    public static class CiudadBuilder{
        private String nombre;
        private long id;

        public CiudadBuilder(){ }

        public CiudadBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }
        public CiudadBuilder setId(long id){
            this.id = id;
            return this;
        }

        public Ciudad build(){
            return new Ciudad(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
