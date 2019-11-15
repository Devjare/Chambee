package com.gps.chambee.servicios.firebase.peticiones;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.servicios.firebase.ServicioFirebaseEscritura;

import java.util.HashMap;
import java.util.Map;

public class SFRegistrarUsuario extends ServicioFirebaseEscritura {

    public SFRegistrarUsuario(EventoTareaCompletada<String> eventoTareaCompletada, EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }

    @Override
    public void ejecutarTarea(Object... args) {
        final Usuario usuario = (Usuario) args[0];

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(usuario.getCorreoElectronico(), usuario.getContrasenia())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.e("chambee", task.getException().getMessage());
                    eventoTareaCancelada.alCancelarTarea(null);
                    return;
                }

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String idUsuario = firebaseUser.getUid();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("usuarios")
                        .child(idUsuario);

                Map<String, String> datos = new HashMap<>();
                datos.put("id", idUsuario);
                datos.put("nombreUsuario", usuario.getId());
                datos.put("nombres", usuario.getNombre());
                datos.put("apellidos", usuario.getApellidos());
                datos.put("correo", usuario.getCorreoElectronico());
                datos.put("contrasena", usuario.getContrasenia());
                datos.put("telefono", usuario.getTelefono());

                databaseReference.setValue(datos)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            eventoTareaCompletada.alCompletarTarea("exito");
                    }
                });
            }
        });
    }
}
