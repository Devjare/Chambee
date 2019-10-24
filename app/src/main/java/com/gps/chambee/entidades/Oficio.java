package com.gps.chambee.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Oficio implements Parcelable{
    private String nombre;
    private String especialidad;
    private long id;

    public Oficio(OficioBuilder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.especialidad = builder.especialidad;
    }

    protected Oficio(Parcel in) {
        nombre = in.readString();
        especialidad = in.readString();
        id = in.readLong();
    }

    public static final Creator<Oficio> CREATOR = new Creator<Oficio>() {
        @Override
        public Oficio createFromParcel(Parcel in) {
            return new Oficio(in);
        }

        @Override
        public Oficio[] newArray(int size) {
            return new Oficio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(especialidad);
        dest.writeLong(id);
    }

    public static class OficioBuilder{
        private String nombre;
        private String especialidad;
        private long id;

        public OficioBuilder(){ }

        public OficioBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        public OficioBuilder setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }
        public OficioBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public Oficio build(){
            return new Oficio(this);
        }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}