package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.ui.Sesion;

public class DatosPersonalesActivity extends AppCompatActivity {

    private LinearLayout llNombre;
    private LinearLayout llCorreoElectronico;
    private LinearLayout llTelefono;
    private ImageView ivRegresarDatosPersonales;
    private TextView tvUserName;
    private TextView tvUserEmailAddress;
    private TextView tvUserPhone;

    private UsuarioFirebase usuarioFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        llTelefono = findViewById(R.id.llTelefono);
        llNombre = findViewById(R.id.llNombre);
        llCorreoElectronico = findViewById(R.id.llCorreoElectronico);
        ivRegresarDatosPersonales = findViewById(R.id.ivRegresarDatosPersonales);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmailAddress = findViewById(R.id.tvUserEmailAddress);
        tvUserPhone = findViewById(R.id.tvUserPhone);

        ivRegresarDatosPersonales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatosPersonalesActivity.super.onBackPressed();
            }
        });

        llNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatosPersonalesActivity.this, NombreCompletoActivity.class));
            }
        });

        llCorreoElectronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatosPersonalesActivity.this, CorreoElectronicoActivity.class));
            }
        });

        llTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatosPersonalesActivity.this, TelefonoActivity.class));
            }
        });

        llenarDatosPersonales();
    }

    private void llenarDatosPersonales() {
        usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());
        tvUserName.setText(usuarioFirebase.getNombres() + " " + usuarioFirebase.getApellidos());
        tvUserEmailAddress.setText(usuarioFirebase.getCorreo());
        tvUserPhone.setText(usuarioFirebase.getTelefono());
    }
}
