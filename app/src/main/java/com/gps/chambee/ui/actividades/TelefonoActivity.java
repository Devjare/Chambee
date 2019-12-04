package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorTelefono;
import com.gps.chambee.ui.Sesion;

public class TelefonoActivity extends AppCompatActivity {

    private ImageView ivRegresarTelefono;
    private TextView tvTelefonoActual;
    private EditText etNuevoCorreo;
    private Button btnListoTelefono;
    private ProgressDialog progressDialog;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefono);

        tvTelefonoActual = findViewById(R.id.tvTelefonoActual);
        etNuevoCorreo = findViewById(R.id.etNuevoCorreo);
        btnListoTelefono = findViewById(R.id.btnListoTelefono);
        ivRegresarTelefono = findViewById(R.id.ivRegresarTelefono);

        tvTelefonoActual.setText(usuarioFirebase.getTelefono());

        ivRegresarTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnListoTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarTelefono();
            }
        });
    }

    private void actualizarTelefono() {
        String telefono = etNuevoCorreo.getText().toString();
        ValidadorTelefono validadorTelefono = new ValidadorTelefono(telefono);

        if (!validadorTelefono.validar()) {
            Toast.makeText(TelefonoActivity.this, validadorTelefono.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando...");
        progressDialog.show();

        // TODO Caso de uso para actualizar telefono del usuario

        /*new CUActualizarUsuario(this, new CasoUso.EventoPeticionAceptada<String>() {

            @Override
            public void alAceptarPeticion(String s) {
                progressDialog.dismiss();

                Sesion.instance().agregarEntidad(UsuarioFirebase.getNombreClase(), usuarioFirebase);
                finish();
            }

        }, new CasoUso.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion() {
                progressDialog.dismiss();
                Toast.makeText(TelefonoActivity.this, "No tienes internet.", Toast.LENGTH_LONG).show();
            }

        });*/
    }
}
