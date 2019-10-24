package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Pais implements Parcelable{
    private String nombre;
    private String id;

    public Pais(PaisBuilder builder){
        this.nombre = builder.nombre;
        this.id = builder.nombre;
    }

    protected Pais(Parcel in) {
        nombre = in.readString();
        id = in.readString();
    }

    public static final Creator<Pais> CREATOR = new Creator<Pais>() {
        @Override
        public Pais createFromParcel(Parcel in) {
            return new Pais(in);
        }

        @Override
        public Pais[] newArray(int size) {
            return new Pais[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(id);
    }

    public static class PaisBuilder{
        private String nombre;
        private String id;

        public PaisBuilder(){ }

        public PaisBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }
        public PaisBuilder setId(String id){
            this.id = id;
            return this;
        }

        public Pais build(){
            return new Pais(this);
        }
    }
}
