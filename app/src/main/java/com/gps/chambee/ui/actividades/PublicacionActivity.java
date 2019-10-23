package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.ComentarioTrabajoAdapter;

import java.util.ArrayList;
import java.util.List;

public class PublicacionActivity extends AppCompatActivity {

    private CircleImageView civFotoPerfil;
    private RecyclerView rvComentariosTrabajo;
    private ImageView ivRegresarPublicacion;
    private ImageView ivPortada;
    private TextView tvDescripcionTrabajo;
    private TextView tvNombreTrabajo;
    private TextView tvNombrePerfil;
    private TextView tvNumeroInteresados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);

        ivPortada = findViewById(R.id.ivPortada);
        tvNumeroInteresados = findViewById(R.id.tvNumeroInteresados);
        rvComentariosTrabajo = findViewById(R.id.rvComentariosTrabajo);
        ivRegresarPublicacion = findViewById(R.id.ivRegresarPublicacion);
        tvDescripcionTrabajo = findViewById(R.id.tvDescripcionTrabajo);
        tvNombrePerfil = findViewById(R.id.tvNombrePerfil);
        civFotoPerfil = findViewById(R.id.civFotoPerfil);

        List<Object> lista=new ArrayList<>();
        lista.add(0);
        lista.add(0);

        ComentarioTrabajoAdapter adapter=new ComentarioTrabajoAdapter(this,lista);

        rvComentariosTrabajo.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically(){
                return false;
            }
        });
        rvComentariosTrabajo.setAdapter(adapter);

        ivRegresarPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicacionActivity.super.onBackPressed();
            }
        });
    }
}
