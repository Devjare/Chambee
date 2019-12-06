package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.entidades.Publicacion;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;
import com.gps.chambee.entidades.vistas.DetallePublicacion;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.entidades.vistas.PublicacionEmpresa;
import com.gps.chambee.entidades.vistas.PublicacionGeneral;
import com.gps.chambee.entidades.vistas.PublicacionPersona;
import com.gps.chambee.negocios.casos.CUObtenerCategoriasPublicacion;
import com.gps.chambee.negocios.casos.CUObtenerComentariosPublicacion;
import com.gps.chambee.negocios.casos.CUObtenerInteresados;
import com.gps.chambee.negocios.casos.CURegistrarComentarioPublicacion;
import com.gps.chambee.negocios.casos.CUSeleccionarPerfilDetallado;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.servicios.web.ServicioWeb;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.adaptadores.ComentarioTrabajoAdapter;
import com.gps.chambee.ui.adaptadores.EtiquetaAdapter;
import com.gps.chambee.ui.adaptadores.InteresadosAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicacionActivity extends AppCompatActivity {

    private CircleImageView civFotoPerfil;
    private RecyclerView rvInteresados;
    private RecyclerView rvComentariosTrabajo;
    //private RecyclerView rvAreasDeInteres;
    private RecyclerView rvEtiquetas;
    private ImageView ivRegresarPublicacion;
    private ImageView ivPortada;
    private ImageView ivComentar;
    private TextView tvDescripcionTrabajo;
    private TextView tvNombreTrabajo;
    private TextView tvNombrePerfil;
    private TextView tvNumeroInteresados;
    private TextView tvCostos;
    private EditText etComentario;

    private PublicacionGeneral publicacionGeneral;
    private DetallePublicacion detallePublicacion;
    private PerfilDetallado perfil;

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
        tvNombreTrabajo = findViewById(R.id.tvNombreTrabajo);
        civFotoPerfil = findViewById(R.id.civFotoPerfil);
        rvInteresados = findViewById(R.id.rvInteresados);
        etComentario = findViewById(R.id.etComentario);
        rvEtiquetas = findViewById(R.id.rvEtiquetas);
        ivComentar = findViewById(R.id.ivComentar);

        ivComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etComentario.setText("");
                rvComentariosTrabajo.requestFocus();

                comentar(publicacionGeneral);
            }
        });

        ivRegresarPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Programar regresar de detalle publicacion a principal.
            }
        });

        Intent intent = getIntent();

        int tipo = intent.getIntExtra("tipo", -1);
        publicacionGeneral = intent.getParcelableExtra("publicacion");

        cargarDetallesPublicacion(publicacionGeneral, tipo);

    }

    private void cargarDetallesPublicacion(PublicacionGeneral publicacionGeneral, int tipo) {

        obtenerPerfil();

        cargarDatosGenerales(publicacionGeneral, tipo);

        cargarInteresados(publicacionGeneral);

        cargarCategorias(publicacionGeneral.getIdPublicacion());

        cargarComentarios(publicacionGeneral);

    }

    private void cargarComentarios(PublicacionGeneral publicacionGeneral) {

        new CUObtenerComentariosPublicacion(
                getApplicationContext(),
                new CasoUso.EventoPeticionAceptada<List<ComentarioPublicacion>>() {
                    @Override
                    public void alAceptarPeticion(List<ComentarioPublicacion> comentarioPublicacions) {
                        llenarComentariosPublicacion(comentarioPublicacions);
                    }
                },
                new CasoUso.EventoPeticionRechazada() {
                    @Override
                    public void alRechazarOperacion() {
                        Toast.makeText(PublicacionActivity.this, "Fallo al cargar comentarios publicacion", Toast.LENGTH_SHORT).show();
                    }
                }
        ).enviarPeticion(publicacionGeneral.getIdPublicacion());
    }

    private void llenarComentariosPublicacion(List<ComentarioPublicacion> comentarioPublicacions) {

        ComentarioTrabajoAdapter comentariosAdapter = new ComentarioTrabajoAdapter(
                getApplicationContext(),
                comentarioPublicacions
        );
        rvComentariosTrabajo.setAdapter(comentariosAdapter);
        rvComentariosTrabajo.setLayoutManager(new LinearLayoutManager(this));

    }

    private void cargarInteresados(PublicacionGeneral publicacionGeneral) {

        new CUObtenerInteresados(
                getApplicationContext(),
                new CasoUso.EventoPeticionAceptada<List<Perfil>>() {
                    @Override
                    public void alAceptarPeticion(List<Perfil> interesados) {
                        llenarInteresados(interesados);
                    }
                },
                new CasoUso.EventoPeticionRechazada() {
                    @Override
                    public void alRechazarOperacion() {
                        Toast.makeText(PublicacionActivity.this, "Fallo al cargar interesados", Toast.LENGTH_SHORT).show();
                    }
                }
        ).enviarPeticion(publicacionGeneral.getIdPublicacion());

    }

    private void llenarInteresados(List<Perfil> interesados) {

        if (interesados.size() < 1){
            Toast.makeText(this, "Ningun interesado en esta publicacion!", Toast.LENGTH_SHORT).show();
        }
        
        InteresadosAdapter interesadosAdapter = new InteresadosAdapter(
                getApplicationContext(),
                interesados
        );
        rvInteresados.setAdapter(interesadosAdapter);
        rvInteresados.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

    }

    private void cargarDatosGenerales(PublicacionGeneral publicacionGeneral, int tipo) {

        if (tipo == PublicacionGeneral.EMPRESA) {

            detallePublicacion = new DetallePublicacion.DetallePublicacionBuilder()
                    .setCantidadInteresados(publicacionGeneral.getInteresados())
                    .setTrabajo(publicacionGeneral.getNombreTrabajo())
                    .setNombrePerfil(publicacionGeneral.getNombre())
                    .setDescripcion(publicacionGeneral.getDescripcion())
                    .setUrlPortada(publicacionGeneral.getUrlPortada())
                    .setCostos("Min: " + publicacionGeneral.getPagoMin() + ", " +
                            "Max: " + publicacionGeneral.getPagoMax())
                    .setUrlPerfil(publicacionGeneral.getUrlImagen())
                    .build();

        } else {

            detallePublicacion = new DetallePublicacion.DetallePublicacionBuilder()
                    .setCantidadInteresados(publicacionGeneral.getInteresados())
                    .setTrabajo(publicacionGeneral.getNombreTrabajo())
                    .setNombrePerfil(publicacionGeneral.getNombre())
                    .setDescripcion(publicacionGeneral.getDescripcion())
                    .setUrlPortada(publicacionGeneral.getUrlPortada())
                    .setCostos("Min: " + publicacionGeneral.getPagoMin() + ", " +
                            "Max: " + publicacionGeneral.getPagoMax())
                    .setUrlPerfil(publicacionGeneral.getUrlImagen())
                    .build();
        }

        tvCostos.setText(detallePublicacion.getCostos());
        tvDescripcionTrabajo.setText(detallePublicacion.getDescripcion());
        tvNombrePerfil.setText(detallePublicacion.getNombrePerfil());
        tvNombreTrabajo.setText(detallePublicacion.getNombreTrabajo());

    }

    private void cargarCategorias(int idPublicacion) {
        new CUObtenerCategoriasPublicacion(
                getApplicationContext(),
                new CasoUso.EventoPeticionAceptada<List<Categoria>>() {
                    @Override
                    public void alAceptarPeticion(List<Categoria> categorias) {
                        llenarCategorias(categorias);
                    }
                },
                new CasoUso.EventoPeticionRechazada() {
                    @Override
                    public void alRechazarOperacion() {
                        Toast.makeText(PublicacionActivity.this, "Fallo al cargar categorias publicacion", Toast.LENGTH_SHORT).show();
                    }
                }
        ).enviarPeticion(idPublicacion);
    }

    private void llenarCategorias(List<Categoria> areasDeInteres){
        EtiquetaAdapter categoriasAdapter = new EtiquetaAdapter(
                getApplicationContext(),
                areasDeInteres);
        rvEtiquetas.setAdapter(categoriasAdapter);
        rvEtiquetas.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void obtenerPerfil() {

        UsuarioFirebase usuario = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

        new CUSeleccionarPerfilDetallado(getApplicationContext(),
                new CasoUso.EventoPeticionAceptada<PerfilDetallado>() {
            @Override
            public void alAceptarPeticion(PerfilDetallado perfilDetallado) {
                perfil = perfilDetallado;
            }

        }, new CasoUso.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion() {
                Toast.makeText(getApplicationContext(), "Error al obtener perfil detallado del usuario", Toast.LENGTH_SHORT).show();
            }

        }).enviarPeticion(usuario.getId());
    }

    private void comentar(final PublicacionGeneral datosPublicacion){
        final String comentario = etComentario.getText().toString();
        // anio-dia-mes
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        Date date = new Date();

        final String fecha = formatter.format(date);
        ivComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CURegistrarComentarioPublicacion(
                        getApplicationContext(),
                        new CasoUso.EventoPeticionAceptada<String>() {
                            @Override
                            public void alAceptarPeticion(String s) {
                                Toast.makeText(PublicacionActivity.this, "Comentado exitosamente!", Toast.LENGTH_SHORT).show();

                                cargarComentarios(publicacionGeneral);
                            }
                        },
                        new CasoUso.EventoPeticionRechazada() {
                            @Override
                            public void alRechazarOperacion() {
                                Toast.makeText(PublicacionActivity.this, "Fallo al enviar comentario!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ).enviarPeticion( datosPublicacion.getIdPublicacion(), perfil.getIdPerfil(), comentario);
            }
        });
    }

}