package com.gps.chambee.entidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class VistaChat implements Parcelable {

    public static class Builder {

        private String idEmisor;
        private String idRecepetor;

        private String urlImagen;
        private String nombreUsuario;
        private String ultimoMensaje;
        private String fechaUltimoMensaje;
        private int cantidadMensajesSinLeer;
        private boolean activo;

        public Builder() { }

        public Builder setIdEmisor(String idEmisor) {
            this.idEmisor = idEmisor;
            return this;
        }

        public Builder setIdRecepetor(String idRecepetor) {
            this.idRecepetor = idRecepetor;
            return this;
        }

        public Builder setUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public Builder setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Builder setUltimoMensaje(String ultimoMensaje) {
            this.ultimoMensaje = ultimoMensaje;
            return this;
        }

        public Builder setFechaUltimoMensaje(String fechaUltimoMensaje) {
            this.fechaUltimoMensaje = fechaUltimoMensaje;
            return this;
        }

        public Builder setCantidadMensajesSinLeer(int cantidadMensajesSinLeer) {
            this.cantidadMensajesSinLeer = cantidadMensajesSinLeer;
            return this;
        }

        public Builder setActivo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public VistaChat build() {
            return new VistaChat(this);
        }
    }

    private String idEmisor;
    private String idRecepetor;

    private String urlImagen;
    private String nombreUsuario;
    private String ultimoMensaje;
    private String fechaUltimoMensaje;
    private int cantidadMensajesSinLeer;
    private boolean activo;

    public VistaChat() { }

    public VistaChat(Builder builder) {
        this.idEmisor = builder.idEmisor;
        this.idRecepetor = builder.idRecepetor;

        this.urlImagen = builder.urlImagen;
        this.nombreUsuario = builder.nombreUsuario;
        this.ultimoMensaje = builder.ultimoMensaje;
        this.fechaUltimoMensaje = builder.fechaUltimoMensaje;
        this.cantidadMensajesSinLeer = builder.cantidadMensajesSinLeer;
        this.activo = builder.activo;
    }

    protected VistaChat(Parcel in) {
        urlImagen = in.readString();
        nombreUsuario = in.readString();
        ultimoMensaje = in.readString();
        fechaUltimoMensaje = in.readString();
        cantidadMensajesSinLeer = in.readInt();
        activo = in.readByte() != 0;
    }

    public static final Creator<VistaChat> CREATOR = new Creator<VistaChat>() {
        @Override
        public VistaChat createFromParcel(Parcel in) {
            return new VistaChat(in);
        }

        @Override
        public VistaChat[] newArray(int size) {
            return new VistaChat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(urlImagen);
        parcel.writeString(nombreUsuario);
        parcel.writeString(ultimoMensaje);
        parcel.writeString(fechaUltimoMensaje);
        parcel.writeInt(cantidadMensajesSinLeer);
        parcel.writeByte((byte) (activo ? 1 : 0));
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getIdRecepetor() {
        return idRecepetor;
    }

    public void setIdRecepetor(String idRecepetor) {
        this.idRecepetor = idRecepetor;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public String getFechaUltimoMensaje() {
        return fechaUltimoMensaje;
    }

    public void setFechaUltimoMensaje(String fechaUltimoMensaje) {
        this.fechaUltimoMensaje = fechaUltimoMensaje;
    }

    public int getCantidadMensajesSinLeer() {
        return cantidadMensajesSinLeer;
    }

    public void setCantidadMensajesSinLeer(int cantidadMensajesSinLeer) {
        this.cantidadMensajesSinLeer = cantidadMensajesSinLeer;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "VistaChat{" +
                "idEmisor='" + idEmisor + '\'' +
                ", idRecepetor='" + idRecepetor + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", ultimoMensaje='" + ultimoMensaje + '\'' +
                ", fechaUltimoMensaje='" + fechaUltimoMensaje + '\'' +
                ", cantidadMensajesSinLeer=" + cantidadMensajesSinLeer +
                ", activo=" + activo +
                '}';
    }
}
