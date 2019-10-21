package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.ComentarioTrabajoAdapter;

import java.util.ArrayList;
import java.util.List;

public class Publicacion extends AppCompatActivity {

    private RecyclerView rvComentariosTrabajo;
    private ImageView ivRegresarPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);

        List<Object> lista=new ArrayList<>();
        lista.add(0);
        lista.add(0);

        ComentarioTrabajoAdapter adapter=new ComentarioTrabajoAdapter(this,lista);

        rvComentariosTrabajo=findViewById(R.id.rvComentariosTrabajo);
        rvComentariosTrabajo.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically(){
                return false;
            }
        });
        rvComentariosTrabajo.setAdapter(adapter);

        ivRegresarPublicacion = findViewById(R.id.ivRegresarPublicacion);
        ivRegresarPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Publicacion.super.onBackPressed();
            }
        });
    }
}
