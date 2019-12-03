package com.gps.chambee.entidades;

public class ChatFirebase {

    // Esta propiedad representa el id del usuario con quien se tiene el chat
    private String idUsuario;

    public ChatFirebase() { }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "ChatFirebase{" +
                "idUsuario='" + idUsuario + '\'' +
                '}';
    }
}
