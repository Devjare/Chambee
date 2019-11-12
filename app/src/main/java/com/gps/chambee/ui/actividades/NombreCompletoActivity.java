package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.SesionSingleton;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.negocios.casos.CUActualizarUsuario;
import com.gps.chambee.negocios.casos.CasoUso;

public class NombreCompletoActivity extends AppCompatActivity {

    private TextView etNombreActual;
    private EditText etNuevoNombre;
    private EditText etNuevoApellido;
    private Button btnListoNombre;
    private ImageView ivRegresarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_completo);

        etNombreActual = findViewById(R.id.tvNombreActual);
        etNuevoApellido = findViewById(R.id.etNuevoApellido);
        etNuevoNombre = findViewById(R.id.etNuevoNombre);
        btnListoNombre = findViewById(R.id.btnListoNombre);
        ivRegresarNombre = findViewById(R.id.ivRegresarNombre);

        ivRegresarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NombreCompletoActivity.super.onBackPressed();
            }
        });

        btnListoNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NombreCompletoActivity.super.onBackPressed();
                // CUActualizar.
                CUActualizarUsuario cuActualizarUsuario = new CUActualizarUsuario(
                        NombreCompletoActivity.this,
                        new CasoUso.EventoPeticionAceptada<String>() {
                            @Override
                            public void alAceptarPeticion(String s) {

                            }
                        }, new CasoUso.EventoPeticionRechazada() {
                    @Override
                    public void alRechazarOperacion() {

                    }
                });
            }
        });

    }
}
