package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gps.chambee.R;

public class ConfiguracionesActivity extends AppCompatActivity {

    private LinearLayout llDatosPersonales;
    private LinearLayout llSeguridad;
    private LinearLayout llCerrarSesion;
    private ImageView ivRegresarConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        llSeguridad = findViewById(R.id.llSeguridad);
        llDatosPersonales = findViewById(R.id.llDatosPersonales);
        ivRegresarConfiguracion = findViewById(R.id.ivRegresarConfiguracion);
        llCerrarSesion = findViewById(R.id.llCerrarSesion);

        ivRegresarConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfiguracionesActivity.super.onBackPressed();
            }
        });

        llDatosPersonales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionesActivity.this, DatosPersonalesActivity.class));
            }
        });

        llSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionesActivity.this, SeguridadActivity.class));
            }
        });

        llCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE: {
                        Toast.makeText(ConfiguracionesActivity.this, "Si", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        break;
                    }

                    case DialogInterface.BUTTON_NEGATIVE: {
                        Toast.makeText(ConfiguracionesActivity.this, "No", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        break;
                    }
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas seguro de que quieres cerrar sesion")
                .setPositiveButton("Si", onClickListener)
                .setNegativeButton("No", onClickListener)
                .show();
    }
}
