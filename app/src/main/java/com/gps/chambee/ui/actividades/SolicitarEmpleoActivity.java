package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorStringNoVacio;

public class SolicitarEmpleoActivity extends AppCompatActivity {

    private ImageView ivRegresarSolicitarTrabajo;
    private CircleImageView civFotoUsuario;
    private EditText etPublicar;
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

        // TODO Poner imagen de perfil del usuario

        ivRegresarSolicitarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicar();
            }
        });
    }

    private void publicar() {

        // obtener datos de la UI

        String publicacion = etPublicar.getText().toString();

        // validar datos

        ValidadorStringNoVacio validador = new ValidadorStringNoVacio(publicacion);

        if (!validador.validar()) {
            Toast.makeText(this, validador.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO Servicio web para hacer publicacion
    }
}
