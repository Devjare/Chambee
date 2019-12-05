package com.gps.chambee.servicios.firebase.peticiones;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.ServicioFirebaseEscritura;

public class SFAutenticarUsuario extends ServicioFirebaseEscritura {

    public SFAutenticarUsuario(ServicioFirebase.EventoTareaCompletada<String> eventoTareaCompletada,
                               EventoTareaCancelada eventoTareaCancelada) {
        super(eventoTareaCompletada, eventoTareaCancelada);
    }

    @Override
    public void ejecutarTarea(Object... args) {
        String correo = args[0].toString();
        String contrasena = args[1].toString();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        Task<AuthResult> authResultTask = firebaseAuth.signInWithEmailAndPassword(correo, contrasena);
        authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    eventoTareaCancelada.alCancelarTarea(null);
                    return;
                }

                eventoTareaCompletada.alCompletarTarea("exito");
            }
        });
    }
}


