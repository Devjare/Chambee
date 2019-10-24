package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Estado implements Parcelable{
    private String nombre;
    private long id;

    public Estado(EstadoBuilder builder){
        this.nombre = builder.nombre;
        this.id = builder.id;
    }

    protected Estado(Parcel in) {
        nombre = in.readString();
        id = in.readLong();
    }

    public static final Creator<Estado> CREATOR = new Creator<Estado>() {
        @Override
        public Estado createFromParcel(Parcel in) {
            return new Estado(in);
        }

        @Override
        public Estado[] newArray(int size) {
            return new Estado[size];
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

    public static class EstadoBuilder{
        private String nombre;
        private long id;

        public EstadoBuilder(){ }

        public EstadoBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }
        public EstadoBuilder setId(long id){
            this.id = id;
            return this;
        }

        public Estado build(){
            return new Estado(this);
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
