package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Colonia implements Parcelable{
    private String nombre;
    private long id;

    public Colonia(ColoniaBuilder builder){
        this.id = builder.id;
        this.nombre = builder.nombre;
    }

    protected Colonia(Parcel in) {
        nombre = in.readString();
        id = in.readLong();
    }

    public static final Creator<Colonia> CREATOR = new Creator<Colonia>() {
        @Override
        public Colonia createFromParcel(Parcel in) {
            return new Colonia(in);
        }

        @Override
        public Colonia[] newArray(int size) {
            return new Colonia[size];
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

    public static class ColoniaBuilder{
        public long id;
        public String nombre;

        public ColoniaBuilder(){ }

        public ColoniaBuilder setId(long id){
            this.id = id;
            return this;
        }
        public ColoniaBuilder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Colonia build(){
            return new Colonia(this);
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
