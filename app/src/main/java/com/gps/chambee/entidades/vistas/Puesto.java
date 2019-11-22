package com.gps.chambee.entidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class Puesto implements Parcelable{
    private String puestoTrabajo;
    private String empresa;
    private  float estrella;
    private String urlImagenEmpresa;

    public Puesto(PuestoBuilder puestoBuilder) {
        this.puestoTrabajo = puestoBuilder.puestoTrabajo;
        this.empresa = puestoBuilder.empresa;
        this.estrella = puestoBuilder.estrella;
        this.urlImagenEmpresa = puestoBuilder.urlImagenEmpresa;
    }

    public static class PuestoBuilder {
        private String puestoTrabajo;
        private String empresa;
        private  float estrella;
        private String urlImagenEmpresa;

        public PuestoBuilder(){}

        public PuestoBuilder setPuestoTrabajo(String puestoTrabajo) {
            this.puestoTrabajo = puestoTrabajo;
            return this;
        }

        public PuestoBuilder setEmpresa(String empresa) {
            this.empresa = empresa;
            return this;
        }

        public PuestoBuilder setEstrella(float estrella) {
            this.estrella = estrella;
            return this;
        }

        public PuestoBuilder setUrlImagenEmpresa(String urlImagenEmpresa) {
            this.urlImagenEmpresa = urlImagenEmpresa;
            return this;
        }

        public Puesto build(){
            return new Puesto(this);
        }
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public float getEstrella() {
        return estrella;
    }

    public String getUrlImagenEmpresa() {
        return urlImagenEmpresa;
    }

    protected Puesto(Parcel in) {
        puestoTrabajo = in.readString();
        empresa = in.readString();
        estrella = in.readFloat();
        urlImagenEmpresa = in.readString();
    }

    public static final Creator<Puesto> CREATOR = new Creator<Puesto>() {
        @Override
        public Puesto createFromParcel(Parcel in) {
            return new Puesto(in);
        }

        @Override
        public Puesto[] newArray(int size) {
            return new Puesto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(puestoTrabajo);
        parcel.writeString(empresa);
        parcel.writeFloat(estrella);
        parcel.writeString(urlImagenEmpresa);
    }
}
