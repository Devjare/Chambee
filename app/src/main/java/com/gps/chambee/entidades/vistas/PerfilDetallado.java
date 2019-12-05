package com.gps.chambee.entidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

import com.gps.chambee.entidades.Medalla;

import java.util.List;

public class PerfilDetallado implements Parcelable {

    public String urlPerfil;
    public String urlPortada;
    public String nombrePersona;
    public String apellidosPersona;
    public int edad;
    public String puesto;
    public double estrellas;
    public String estado;
    public String ciudad;
    public String acerca;
    public List<String> servicios;
    public List<Medalla> medallas;

    public PerfilDetallado(){}

    public PerfilDetallado(String urlPerfil, String urlPortada, String nombrePersona, String apellidosPersona, int edad, String puesto, double estrellas, String estado, String ciudad, String acerca, List<String> servicios, List<Medalla> medallas) {
        this.urlPerfil = urlPerfil;
        this.urlPortada = urlPortada;
        this.nombrePersona = nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.edad = edad;
        this.puesto = puesto;
        this.estrellas = estrellas;
        this.estado = estado;
        this.ciudad = ciudad;
        this.acerca = acerca;
        this.servicios = servicios;
        this.medallas = medallas;
    }

    public String getUrlPerfil() {
        return urlPerfil;
    }

    public void setUrlPerfil(String urlPerfil) {
        this.urlPerfil = urlPerfil;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public void setApellidosPersona(String apellidosPersona) {
        this.apellidosPersona = apellidosPersona;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = estrellas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAcerca() {
        return acerca;
    }

    public void setAcerca(String acerca) {
        this.acerca = acerca;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public List<Medalla> getMedallas() {
        return medallas;
    }

    public void setMedallas(List<Medalla> medallas) {
        this.medallas = medallas;
    }

    public static Creator<PerfilDetallado> getCREATOR() {
        return CREATOR;
    }

    protected PerfilDetallado(Parcel in) {
        urlPerfil = in.readString();
        urlPortada = in.readString();
        nombrePersona = in.readString();
        apellidosPersona = in.readString();
        edad = in.readInt();
        puesto = in.readString();
        estrellas = in.readDouble();
        estado = in.readString();
        ciudad = in.readString();
        acerca = in.readString();
        servicios = in.createStringArrayList();
        medallas = in.createTypedArrayList(Medalla.CREATOR);
    }

    public static final Creator<PerfilDetallado> CREATOR = new Creator<PerfilDetallado>() {
        @Override
        public PerfilDetallado createFromParcel(Parcel in) {
            return new PerfilDetallado(in);
        }

        @Override
        public PerfilDetallado[] newArray(int size) {
            return new PerfilDetallado[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(urlPerfil);
        parcel.writeString(urlPortada);
        parcel.writeString(nombrePersona);
        parcel.writeString(apellidosPersona);
        parcel.writeInt(edad);
        parcel.writeString(puesto);
        parcel.writeDouble(estrellas);
        parcel.writeString(estado);
        parcel.writeString(ciudad);
        parcel.writeString(acerca);
        parcel.writeStringList(servicios);
        parcel.writeTypedList(medallas);
    }
}
