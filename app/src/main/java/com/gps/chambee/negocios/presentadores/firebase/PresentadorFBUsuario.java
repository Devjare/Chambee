package com.gps.chambee.negocios.presentadores.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.gps.chambee.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PresentadorFBUsuario extends PresentadorFirebase<List<Usuario>> {

    public static class UsuarioFirebase {

        private String apellidos;
        private String contrasena;
        private String correo;
        private String id;
        private String nombres;
        private String telefono;

        public UsuarioFirebase() {}

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return "UsuarioFirebase{" +
                    "apellidos='" + apellidos + '\'' +
                    ", contrasena='" + contrasena + '\'' +
                    ", correo='" + correo + '\'' +
                    ", id='" + id + '\'' +
                    ", nombres='" + nombres + '\'' +
                    ", telefono='" + telefono + '\'' +
                    '}';
        }
    }

    @Override
    public List<Usuario> procesar(DataSnapshot snapshot) {

        List<Usuario> usuarios = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            UsuarioFirebase usuarioFirebase = dataSnapshot.getValue(UsuarioFirebase.class);

            Log.e("chambee", "Usuario obtenido: " + usuarioFirebase.toString());

            Usuario usuario = new Usuario.UsuarioBuilder()
                    .setApellidos(usuarioFirebase.getApellidos())
                    .setContrasenia(usuarioFirebase.getContrasena())
                    .setCorreoElectronico(usuarioFirebase.getCorreo())
                    .setId(usuarioFirebase.getId())
                    .setNombre(usuarioFirebase.getNombres())
                    .setTelefono(usuarioFirebase.getTelefono())
                    .build();

            Log.e("chambee", "Usuario procesado: " + usuario.toString());

            usuarios.add(usuario);
        }

        return usuarios;
    }
}
