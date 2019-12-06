package com.gps.chambee.entidades.vistas;


import android.os.Parcel;
import android.os.Parcelable;

public class PublicacionEmpresa implements Parcelable {

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

    private Integer idUsuario;
    private String apellidos;
    private String urlPortada;
    private Double pagoMax;
    private Double pagoMin;
    private String fecha;

    public PublicacionEmpresa(PublicacionEmpresaBuilder builder) {
        this.idPublicacion = builder.idPublicacion;
        this.urlImagen = builder.urlImagen;
        this.nombre = builder.nombre;
        this.tiempo = builder.tiempo;
        this.etiqueta = builder.etiqueta;
        this.interesados = builder.interesados;
        this.comentarios = builder.comentarios;
        this.vistos = builder.vistos;
        this.descripcion = builder.descripcion;
        this.nombreTrabajo = builder.nombreTrabajo;
        this.urlImagenTrabajo = builder.urlImagenTrabajo;
        this.vista = builder.vista;
        this.interesada = builder.interesada;
    }

    public static class PublicacionEmpresaBuilder {
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

        private Integer idUsuario;
        private String apellidos;
        private String urlPortada;
        private Double pagoMax;
        private Double pagoMin;
        private String fecha;

        public PublicacionEmpresa build() {
            return new PublicacionEmpresa(this);
        }

        public PublicacionEmpresaBuilder setIdPublicacion(int idPublicacion) {
            this.idPublicacion = idPublicacion;
            return this;
        }

        public PublicacionEmpresaBuilder setUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public PublicacionEmpresaBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public PublicacionEmpresaBuilder setTiempo(String tiempo) {
            this.tiempo = tiempo;
            return this;
        }

        public PublicacionEmpresaBuilder setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
            return this;
        }

        public PublicacionEmpresaBuilder setInteresados(Integer interesados) {
            this.interesados = interesados;
            return this;
        }

        public PublicacionEmpresaBuilder setComentarios(Integer comentarios) {
            this.comentarios = comentarios;
            return this;
        }

        public PublicacionEmpresaBuilder setVistos(Integer vistos) {
            this.vistos = vistos;
            return this;
        }

        public PublicacionEmpresaBuilder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public PublicacionEmpresaBuilder setNombreTrabajo(String nombreTrabajo) {
            this.nombreTrabajo = nombreTrabajo;
            return this;
        }

        public PublicacionEmpresaBuilder setUrlImagenTrabajo(String urlImagenTrabajo) {
            this.urlImagenTrabajo = urlImagenTrabajo;
            return this;
        }

        public PublicacionEmpresaBuilder setVista(Integer vista) {
            this.vista = vista;
            return this;
        }

        public PublicacionEmpresaBuilder setInteresada(Integer interesada) {
            this.interesada = interesada;
            return this;
        }

        public PublicacionEmpresaBuilder setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public PublicacionEmpresaBuilder setApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public PublicacionEmpresaBuilder setUrlPortada(String urlPortada) {
            this.urlPortada = urlPortada;
            return this;
        }

        public PublicacionEmpresaBuilder setPagoMax(Double pagoMax) {
            this.pagoMax = pagoMax;
            return this;
        }

        public PublicacionEmpresaBuilder setPagoMin(Double pagoMin) {
            this.pagoMin = pagoMin;
            return this;
        }

        public PublicacionEmpresaBuilder setFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }
    }

    protected PublicacionEmpresa(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PublicacionEmpresa> CREATOR = new Creator<PublicacionEmpresa>() {
        @Override
        public PublicacionEmpresa createFromParcel(Parcel in) {
            return new PublicacionEmpresa(in);
        }

        @Override
        public PublicacionEmpresa[] newArray(int size) {
            return new PublicacionEmpresa[size];
        }
    };

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

    public static Creator<PublicacionEmpresa> getCREATOR() {
        return CREATOR;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public Double getPagoMax() {
        return pagoMax;
    }

    public void setPagoMax(Double pagoMax) {
        this.pagoMax = pagoMax;
    }

    public Double getPagoMin() {
        return pagoMin;
    }

    public void setPagoMin(Double pagoMin) {
        this.pagoMin = pagoMin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public PublicacionGeneral toPublicacionGeneral() {
        return new PublicacionGeneral.PublicacionGeneralBuilder()
                .setIdPublicacion(this.idPublicacion)
                .setComentarios(this.comentarios)
                .setDescripcion(this.descripcion)
                .setEtiqueta(this.etiqueta)
                .setInteresada(this.interesada)
                .setInteresados(this.interesados)
                .setNombre(this.nombre)
                .setTiempo(this.tiempo)
                .setNombreTrabajo(this.nombreTrabajo)
                .setUrlImagen(this.urlImagen)
                .setUrlImagenTrabajo(this.urlImagenTrabajo)
                .setVista(this.vista)
                .setVistos(this.vistos)
                .setIdUsuario(this.idUsuario)
                .setApellidos(this.apellidos)
                .setUrlPortada(this.urlPortada)
                .setPagoMax(this.pagoMax)
                .setPagoMin(this.pagoMin)
                .setFecha(this.fecha)
                .build();
    }

}
