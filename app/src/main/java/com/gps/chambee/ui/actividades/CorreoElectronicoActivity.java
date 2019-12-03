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
import com.gps.chambee.negocios.validadores.ValidadorCorreo;
import com.gps.chambee.ui.Sesion;

public class CorreoElectronicoActivity extends AppCompatActivity {

    private TextView tvActualCorreoElectronico;
    private EditText etNuevoCorreo;
    private ImageView ivRegresarCorreo;
    private Button btnListoCorreo;
    private ProgressDialog progressDialog;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        tvActualCorreoElectronico = findViewById(R.id.tvActualCorreoElectronico);
        etNuevoCorreo = findViewById(R.id.etNuevoCorreo);
        ivRegresarCorreo = findViewById(R.id.ivRegresarCorreo);
        btnListoCorreo = findViewById(R.id.btnListoCorreo);

        tvActualCorreoElectronico.setText(usuarioFirebase.getCorreo());

        ivRegresarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnListoCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCorreo();
            }
        });
    }

    private void actualizarCorreo() {
        progressDialog = new ProgressDialog(this);

        String correoElectronico = etNuevoCorreo.getText().toString();
        ValidadorCorreo validadorCorreo = new ValidadorCorreo(correoElectronico);

        if (!validadorCorreo.validar()) {
            Toast.makeText(this, validadorCorreo.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(CorreoElectronicoActivity.this, "No tienes internet.", Toast.LENGTH_LONG).show();
            }

        });
    }
}
