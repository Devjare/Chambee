package com.gps.chambee.entidades;

public class dummyEntity {
    public void foo(){
        Usuario usuario = new Usuario.UsuarioBuilder()
                .setNombre("Andres")
                .setApellidos("Reyna")
                .setContrasenia("Esta")
                .setTelefono("8129381")
                .setCorreoElectronico("c@gmail.com")
                .build();

    }
}
