package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gps.chambee.R;

public class PublicarTrabajoActivity extends AppCompatActivity {

    private ImageView ivRegresarPublicarTrabajo;
    private ImageView ivSubirImagen;
    private EditText etNombreEmpleo;
    private EditText etAreasInteres;
    private EditText etDescripcionEmpleo;
    private EditText etTiempoTrabajo;
    private EditText etPaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_trabajo);

        ivSubirImagen = findViewById(R.id.ivSubirImagen);
        etAreasInteres = findViewById(R.id.etAreasInteres);
        etNombreEmpleo = findViewById(R.id.etNombreEmpleo);
        etDescripcionEmpleo = findViewById(R.id.etDescripcionEmpleo);
        etTiempoTrabajo = findViewById(R.id.etTiempoTrabajo);
        etPaga = findViewById(R.id.etPaga);

        ivRegresarPublicarTrabajo = findViewById(R.id.ivRegresarPublicarTrabajo);
        ivRegresarPublicarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicarTrabajoActivity.super.onBackPressed();
            }
        });
    }
}
