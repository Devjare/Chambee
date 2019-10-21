package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gps.chambee.R;

public class PublicarTrabajo extends AppCompatActivity {

    private ImageView ivRegresarPublicarTrabajo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_trabajo);

        ivRegresarPublicarTrabajo = findViewById(R.id.ivRegresarPublicarTrabajo);
        ivRegresarPublicarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicarTrabajo.super.onBackPressed();
            }
        });
    }
}
