package com.gps.chambee.entidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class PublicacionGeneral implements Parcelable {

    private int idPublicacion;
    private String urlImagen;
    private String nombre;
    private String tiempo;
    private String etiqueta;
    private Integer interesados;
    private Integer comentarios;
    private Integer vistos;
    private String descripcion;
    private String nombreTrabajo;
    private String urlImagenTrabajo;
    private Integer vista;
    private Integer interesada;

    public PublicacionGeneral(PublicacionGeneralBuilder builder) {
        this.idPublicacion = builder.idPublicacion;
        this.urlImagen = builder.urlImagen;
        this.nombre = builder.nombre;
        this.tiempo = builder.tiempo;
        this.etiqueta = builder.etiqueta;
        this.interesada = builder.interesados;
        this.comentarios = builder.comentarios;
        this.vistos = builder.vistos;
        this.descripcion = builder.descripcion;
        this.nombreTrabajo = builder.nombreTrabajo;
        this.urlImagenTrabajo = builder.urlImagenTrabajo;
        this.vista = builder.vista;
        this.interesados = builder.interesados;
    }

    protected PublicacionGeneral(Parcel in) {
        idPublicacion = in.readInt();
        urlImagen = in.readString();
        nombre = in.readString();
        tiempo = in.readString();
        etiqueta = in.readString();
        if (in.readByte() == 0) {
            interesados = null;
        } else {
            interesados = in.readInt();
        }
        if (in.readByte() == 0) {
            comentarios = null;
        } else {
            comentarios = in.readInt();
        }
        if (in.readByte() == 0) {
            vistos = null;
        } else {
            vistos = in.readInt();
        }
        descripcion = in.readString();
        nombreTrabajo = in.readString();
        urlImagenTrabajo = in.readString();
        if (in.readByte() == 0) {
            vista = null;
        } else {
            vista = in.readInt();
        }
        if (in.readByte() == 0) {
            interesada = null;
        } else {
            interesada = in.readInt();
        }
    }

    public static final Creator<PublicacionGeneral> CREATOR = new Creator<PublicacionGeneral>() {
        @Override
        public PublicacionGeneral createFromParcel(Parcel in) {
            return new PublicacionGeneral(in);
        }

        @Override
        public PublicacionGeneral[] newArray(int size) {
            return new PublicacionGeneral[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPublicacion);
        dest.writeString(urlImagen);
        dest.writeString(nombre);
        dest.writeString(tiempo);
        dest.writeString(etiqueta);
        if (interesados == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(interesados);
        }
        if (comentarios == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(comentarios);
        }
        if (vistos == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(vistos);
        }
        dest.writeString(descripcion);
        dest.writeString(nombreTrabajo);
        dest.writeString(urlImagenTrabajo);
        if (vista == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(vista);
        }
        if (interesada == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(interesada);
        }
    }

    public static class PublicacionGeneralBuilder {
        private int idPublicacion;
        private String urlImagen;
        private String nombre;
        private String tiempo;
        private String etiqueta;
        private Integer interesados;
        private Integer comentarios;
        private Integer vistos;
        private String descripcion;
        private String nombreTrabajo;
        private String urlImagenTrabajo;
        private Integer vista;
        private Integer interesada;

        public PublicacionGeneral build() {
            return new PublicacionGeneral(this);
        }

        public PublicacionGeneralBuilder setIdPublicacion(int idPublicacion) {
            this.idPublicacion = idPublicacion;
            return this;
        }

        public PublicacionGeneralBuilder setUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public PublicacionGeneralBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public PublicacionGeneralBuilder setTiempo(String tiempo) {
            this.tiempo = tiempo;
            return this;
        }

        public PublicacionGeneralBuilder setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
            return this;
        }

        public PublicacionGeneralBuilder setInteresados(Integer interesados) {
            this.interesados = interesados;
            return this;
        }

        public PublicacionGeneralBuilder setComentarios(Integer comentarios) {
            this.comentarios = comentarios;
            return this;
        }

        public PublicacionGeneralBuilder setVistos(Integer vistos) {
            this.vistos = vistos;
            return this;
        }

        public PublicacionGeneralBuilder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public PublicacionGeneralBuilder setNombreTrabajo(String nombreTrabajo) {
            this.nombreTrabajo = nombreTrabajo;
            return this;
        }

        public PublicacionGeneralBuilder setUrlImagenTrabajo(String urlImagenTrabajo) {
            this.urlImagenTrabajo = urlImagenTrabajo;
            return this;
        }

        public PublicacionGeneralBuilder setVista(Integer vista) {
            this.vista = vista;
            return this;
        }

        public PublicacionGeneralBuilder setInteresada(Integer interesada) {
            this.interesada = interesada;
            return this;
        }
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getInteresados() {
        return interesados;
    }

    public void setInteresados(Integer interesados) {
        this.interesados = interesados;
    }

    public Integer getComentarios() {
        return comentarios;
    }

    public void setComentarios(Integer comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getVistos() {
        return vistos;
    }

    public void setVistos(Integer vistos) {
        this.vistos = vistos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getUrlImagenTrabajo() {
        return urlImagenTrabajo;
    }

    public void setUrlImagenTrabajo(String urlImagenTrabajo) {
        this.urlImagenTrabajo = urlImagenTrabajo;
    }

    public Integer getVista() {
        return vista;
    }

    public void setVista(Integer vista) {
        this.vista = vista;
    }

    public Integer getInteresada() {
        return interesada;
    }

    public void setInteresada(Integer interesada) {
        this.interesada = interesada;
    }
}