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
import com.gps.chambee.negocios.casos.CUActualizarUsuario;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorNombre;
import com.gps.chambee.ui.Sesion;

public class NombreCompletoActivity extends AppCompatActivity {

    private TextView etNombreActual;
    private EditText etNuevoNombre;
    private EditText etNuevoApellido;
    private Button btnListoNombre;
    private ImageView ivRegresarNombre;
    private ProgressDialog progressDialog;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_completo);

        etNombreActual = findViewById(R.id.tvNombreActual);
        etNuevoApellido = findViewById(R.id.etNuevoApellido);
        etNuevoNombre = findViewById(R.id.etNuevoNombre);
        btnListoNombre = findViewById(R.id.btnListoNombre);
        ivRegresarNombre = findViewById(R.id.ivRegresarNombre);

        etNombreActual.setText(usuarioFirebase.getNombres() + " " + usuarioFirebase.getApellidos());

        ivRegresarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnListoNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarNombre();
            }
        });
    }

    private void actualizarNombre() {
        progressDialog = new ProgressDialog(this);

        String nombres = etNuevoNombre.getText().toString();
        String apellidos = etNuevoApellido.getText().toString();

        ValidadorNombre validadorNombres = new ValidadorNombre(nombres);
        ValidadorNombre validadorApellidos = new ValidadorNombre(apellidos);

        if(!validadorNombres.validar()) {
            Toast.makeText(NombreCompletoActivity.this, validadorNombres.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        if(!validadorApellidos.validar()) {
            Toast.makeText(NombreCompletoActivity.this, validadorApellidos.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        new CUActualizarUsuario(this, new CasoUso.EventoPeticionAceptada<String>() {

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
                Toast.makeText(NombreCompletoActivity.this, "No tienes internet.", Toast.LENGTH_LONG).show();
            }

        });
    }
}
