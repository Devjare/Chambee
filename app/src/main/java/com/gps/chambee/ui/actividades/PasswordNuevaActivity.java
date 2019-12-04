package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorContrasenia;

public class PasswordNuevaActivity extends AppCompatActivity {

    private EditText etNuevaContrasena;
    private EditText etConfirmarNuevaContrasena;
    private Button bTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_password);

        etNuevaContrasena = findViewById(R.id.etNuevaContrasena);
        etConfirmarNuevaContrasena = findViewById(R.id.etConfirmarNuevaContrasena);
        bTerminar = findViewById(R.id.bTerminar);

        bTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarContrasena();
            }
        });
    }

    private void actualizarContrasena() {
        String nuevaContrasena = etNuevaContrasena.getText().toString();
        ValidadorContrasenia validador = new ValidadorContrasenia(nuevaContrasena);

        if (!validador.validar()) {
            Toast.makeText(this, validador.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        String confirmarNuevaContrasena = etConfirmarNuevaContrasena.getText().toString();

        if (!confirmarNuevaContrasena.equals(nuevaContrasena)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO Caso de uso para actualizar contrasena del usuario
    }
}
