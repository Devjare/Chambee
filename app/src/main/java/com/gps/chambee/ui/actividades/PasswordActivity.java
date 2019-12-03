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

    private TextView tvOlvidasteContrasena;
    private EditText etContrasenaActualSeguridad;
    private EditText etConfirmarContrasenaSeguridad;
    private EditText etNuevaContrasenaSeguridad;
    private ImageView ivRegresarContrasena;
    private Button btnListoContrasena;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        etNuevaContrasenaSeguridad = findViewById(R.id.etNuevaContrasenaSeguridad);
        tvOlvidasteContrasena = findViewById(R.id.tvOlvidasteContrasena);
        etContrasenaActualSeguridad = findViewById(R.id.etContrasenaActualSeguridad);
        etConfirmarContrasenaSeguridad = findViewById(R.id.etConfirmarContrasenaSeguridad);
        ivRegresarContrasena = findViewById(R.id.ivRegresarContrasena);
        btnListoContrasena = findViewById(R.id.btnListoContrasena);

        btnListoContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contrasenaActual = etContrasenaActualSeguridad.getText().toString();
                ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia(contrasenaActual);

                if(!validadorContrasenia.validar()){
                    Toast.makeText(PasswordActivity.this, validadorContrasenia.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
                    return;
                }

                String confirmarContrasena = etConfirmarContrasenaSeguridad.getText().toString();
                if(!contrasenaActual.equals(confirmarContrasena)){
                    Toast.makeText(PasswordActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!contrasenaActual.equals(usuarioFirebase.getContrasena())){

                    Toast.makeText(PasswordActivity.this, "Contraseña actual incorrecta.", Toast.LENGTH_LONG).show();
                    return;
                }

                String contrasenaNueva = etNuevaContrasenaSeguridad.getText().toString();
                validadorContrasenia = new ValidadorContrasenia(contrasenaNueva);

                if(!validadorContrasenia.validar()){
                    Toast.makeText(PasswordActivity.this, validadorContrasenia.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
                    return;
                }

                usuarioFirebase.setContrasena(contrasenaNueva);

                PasswordActivity.super.onBackPressed();
            }
        });

        ivRegresarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordActivity.super.onBackPressed();
            }
        });

        tvOlvidasteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasswordActivity.this, OlvideContrasenaActivity.class));
            }
        });

    }
}
