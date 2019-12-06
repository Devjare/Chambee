package com.gps.chambee.entidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class ComentarioPublicacion implements Parcelable {

    private int idUsuario;

    private String comentario;
    private String nombreUsuario;
    private String tiempo;
    private String interesados;
    private String url_imagen;

    public ComentarioPublicacion(ComentarioPublcacionBuilder builder) {
        this.comentario = builder.comentario;
        this.nombreUsuario = builder.nombreUsuario;
        this.tiempo = builder.tiempo;
        this.interesados = builder.interesados;
        this.url_imagen = builder.url_imagen;
        this.idUsuario = builder.idUsuario;
    }

    protected ComentarioPublicacion(Parcel in) {
        comentario = in.readString();
        nombreUsuario = in.readString();
        tiempo = in.readString();
        interesados = in.readString();
        url_imagen = in.readString();
    }

    public static final Creator<ComentarioPublicacion> CREATOR = new Creator<ComentarioPublicacion>() {
        @Override
        public ComentarioPublicacion createFromParcel(Parcel in) {
            return new ComentarioPublicacion(in);
        }

        @Override
        public ComentarioPublicacion[] newArray(int size) {
            return new ComentarioPublicacion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comentario);
        dest.writeString(nombreUsuario);
        dest.writeString(tiempo);
        dest.writeString(interesados);
        dest.writeString(url_imagen);
    }

    public static class ComentarioPublcacionBuilder {
        private String comentario;
        private String nombreUsuario;
        private String tiempo;
        private String interesados;
        private String url_imagen;

        private int idUsuario;

        public ComentarioPublicacion build() {
            return new ComentarioPublicacion(this);
        }

        public ComentarioPublcacionBuilder setComentario(String comentario) {
            this.comentario = comentario;
            return this;
        }

        public ComentarioPublcacionBuilder setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public ComentarioPublcacionBuilder setTiempo(String tiempo) {
            this.tiempo = tiempo;
            return this;
        }

        public ComentarioPublcacionBuilder setInteresados(String interesados) {
            this.interesados = interesados;
            return this;
        }

        public ComentarioPublcacionBuilder setUrl_imagen(String url_imagen) {
            this.url_imagen = url_imagen;
            return this;
        }

        public ComentarioPublcacionBuilder setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }
    }

    public String getComentario() {
        return comentario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getInteresados() {
        return interesados;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
