package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorContrasenia;
import com.gps.chambee.ui.Sesion;

public class PasswordActivity extends AppCompatActivity {

    private ImageView ivRegresarContrasena;
    private EditText etContrasenaActual;
    private EditText etNuevaContrasena;
    private EditText etConfirmarNuevaContrasena;
    private TextView tvOlvidasteContrasena;
    private Button btnListoContrasena;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        ivRegresarContrasena = findViewById(R.id.ivRegresarContrasena);
        etContrasenaActual = findViewById(R.id.etContrasenaActual);
        etNuevaContrasena = findViewById(R.id.etNuevaContrasena);
        etConfirmarNuevaContrasena = findViewById(R.id.etConfirmarNuevaContrasena);
        tvOlvidasteContrasena = findViewById(R.id.tvOlvidasteContrasena);
        btnListoContrasena = findViewById(R.id.btnListoContrasena);

        ivRegresarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvOlvidasteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasswordActivity.this, OlvideContrasenaActivity.class));
            }
        });

        btnListoContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarContrasena();
            }
        });
    }

    private void actualizarContrasena() {

        // Checar la contrasena actual
        String contrasenaActual = etContrasenaActual.getText().toString();

        if (!contrasenaActual.equals(usuarioFirebase.getContrasena())) {
            Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar la nueva contrasena
        String nuevaContrasena = etNuevaContrasena.getText().toString();
        ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia(nuevaContrasena);

        if (!validadorContrasenia.validar()) {
            Toast.makeText(this, validadorContrasenia.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Checar que las contrasenas coincidan

        String confirmarNuevaContrasena = etConfirmarNuevaContrasena.getText().toString();

        if (!confirmarNuevaContrasena.equals(nuevaContrasena)) {
            Toast.makeText(PasswordActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO Servicio web para actualizar la contrasena del usuario

        finish();
    }
}
