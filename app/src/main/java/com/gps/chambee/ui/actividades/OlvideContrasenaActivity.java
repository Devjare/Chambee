package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.negocios.validadores.ValidadorCorreo;

public class OlvideContrasenaActivity extends AppCompatActivity {

    private Button btnSiguienteCorreo;
    private EditText etCorreoRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_contrasena);

        etCorreoRecuperar = findViewById(R.id.etCodigoRecuperarContrasena);
        btnSiguienteCorreo = findViewById(R.id.btnSiguienteCorreo);

        btnSiguienteCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarContrasena();
            }
        });
    }

    private void recuperarContrasena() {
        String correoElectronico = etCorreoRecuperar.getText().toString();
        ValidadorCorreo validadorCorreo = new ValidadorCorreo(correoElectronico);

        if (!validadorCorreo.validar()) {
            Toast.makeText(this, validadorCorreo.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(new Intent(OlvideContrasenaActivity.this, RecuperarPasswordActivity.class));
    }
}
