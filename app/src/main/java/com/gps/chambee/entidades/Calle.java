package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Calle implements Parcelable{
    private String nombre;
    private int id;

    public Calle(CalleBuilder builder){
        this.nombre = builder.nombre;
        this.id = builder.id;
    }

    protected Calle(Parcel in) {
        nombre = in.readString();
        id = in.readInt();
    }

    public static final Creator<Calle> CREATOR = new Creator<Calle>() {
        @Override
        public Calle createFromParcel(Parcel in) {
            return new Calle(in);
        }

        @Override
        public Calle[] newArray(int size) {
            return new Calle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(id);
    }

    public static class CalleBuilder{
        public int id;
        public String nombre;

        public CalleBuilder(){ }

        public Calle build(){
            return new Calle(this);
        }

        public CalleBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }
        public CalleBuilder setId(int id){
            this.id = id;
            return this;
        }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
