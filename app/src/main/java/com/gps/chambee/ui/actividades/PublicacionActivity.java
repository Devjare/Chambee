package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;
import com.gps.chambee.entidades.vistas.DetallePublicacionEmpresa;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.negocios.casos.CUObtenerDetallesPublicacionEmpresa;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.adaptadores.CategoriasAdapter;
import com.gps.chambee.ui.adaptadores.ComentarioTrabajoAdapter;
import com.gps.chambee.ui.adaptadores.ServiciosAdapter;
import com.gps.chambee.ui.adaptadores.InteresadosAdapter;

import java.util.ArrayList;
import java.util.List;

public class PublicacionActivity extends AppCompatActivity {

    private CircleImageView civFotoPerfil;
    private RecyclerView rvInteresados;
    private RecyclerView rvComentariosTrabajo;
    private RecyclerView rvAreasDeInteres;
    private RecyclerView rvEtiquetas;
    private ImageView ivRegresarPublicacion;
    private ImageView ivPortada;
    private TextView tvDescripcionTrabajo;
    private TextView tvNombreTrabajo;
    private TextView tvNombrePerfil;
    private TextView tvNumeroInteresados;
    private TextView tvCostos;
    private EditText etComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);

        ivPortada = findViewById(R.id.ivPortada);
        tvNumeroInteresados = findViewById(R.id.tvNumeroInteresados);
        rvComentariosTrabajo = findViewById(R.id.rvComentariosTrabajo);
        //rvAreasDeInteres = findViewById(R.id.rvAreasDeInteres);
        ivRegresarPublicacion = findViewById(R.id.ivRegresarPublicacion);
        tvDescripcionTrabajo = findViewById(R.id.tvDescripcionTrabajo);
        tvNombrePerfil = findViewById(R.id.tvNombrePerfil);
        tvCostos = findViewById(R.id.tvCostos);
        civFotoPerfil = findViewById(R.id.civFotoPerfil);
        rvInteresados = findViewById(R.id.rvInteresados);
        etComentario = findViewById(R.id.etComentario);
        rvEtiquetas = findViewById(R.id.rvServicios);

        List<Object> lista = new ArrayList<>();
        lista.add(0);

        ServiciosAdapter adapter = new ServiciosAdapter(this,lista);
        rvEtiquetas.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvEtiquetas.setAdapter(adapter);

        new CUObtenerDetallesPublicacionEmpresa(
                getApplicationContext(),
                new CasoUso.EventoPeticionAceptada<DetallePublicacionEmpresa>(){
                    @Override
                    public void alAceptarPeticion(DetallePublicacionEmpresa detallePublicacionEmpresa) {
                        llenarDetallePublicacion(detallePublicacionEmpresa);
                    }
                },
                new CasoUso.EventoPeticionRechazada(){
                    @Override
                    public void alRechazarOperacion() { }
                });

        rvInteresados.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) { } ); 
        ivRegresarPublicacion.setOnClickListener(new View.OnClickListener() {  
            @Override
            public void onClick(View view) {
                PublicacionActivity.super.onBackPressed();
            }
        });
    }

    private void llenarDetallePublicacion(DetallePublicacionEmpresa detallePublicacionEmpresa) {

        tvDescripcionTrabajo.setText(detallePublicacionEmpresa.getDescripcion());
        tvNombrePerfil.setText(detallePublicacionEmpresa.getPublicacion().getNombreEmpresa());
        tvNombreTrabajo.setText(detallePublicacionEmpresa.getPublicacion().getNombreTrabajo());
        tvNumeroInteresados.setText(detallePublicacionEmpresa.getCantidadInteresados());

        llenarComentarios(detallePublicacionEmpresa.getListaComentarios());
        llenarInteresados(detallePublicacionEmpresa.getListaInteresados());
        llenarAreasDeInteres(detallePublicacionEmpresa.getListaAreasDeInteres());
    }

    private void llenarAreasDeInteres(List<Categoria> listaAreasDeInteres) {
        CategoriasAdapter adapter = new CategoriasAdapter(this, listaAreasDeInteres);

        rvAreasDeInteres.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvAreasDeInteres.setAdapter(adapter);
    }

    private void llenarComentarios(List<ComentarioPublicacion> comentarios) {
        ComentarioTrabajoAdapter adapter = new ComentarioTrabajoAdapter(this, comentarios);

        rvComentariosTrabajo.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvComentariosTrabajo.setAdapter(adapter);
    }

    private void llenarInteresados(List<Perfil> interesados) {
        InteresadosAdapter iaAdapter = new InteresadosAdapter(this, interesados);

        rvInteresados.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvInteresados.setAdapter(iaAdapter);
    }

}