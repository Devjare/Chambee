package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gps.chambee.R;

public class SolicitarEmpleoActivity extends AppCompatActivity {

    private EditText etPublicar;
    private CircleImageView civFotoUsuario;
    private ImageView ivRegresarSolicitarTrabajo;
    private ImageView ivSubirImagenPublicacion;
    private Button btnPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_empleo);

        etPublicar = findViewById(R.id.etPublicar);
        civFotoUsuario = findViewById(R.id.civFotoPerfil);
        ivRegresarSolicitarTrabajo = findViewById(R.id.ivRegresarSolicitarTrabajo);
        ivSubirImagenPublicacion = findViewById(R.id.ivSubirImagenPublicacion);
        btnPublicar = findViewById(R.id.btnPublicar);

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolicitarEmpleoActivity.super.onBackPressed();
            }
        });

        ivRegresarSolicitarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolicitarEmpleoActivity.super.onBackPressed();
            }
        });
    }
}
