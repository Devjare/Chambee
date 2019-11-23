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
import android.widget.TextView;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;
import com.gps.chambee.entidades.vistas.DetallePublicacion;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.negocios.casos.CUObtenerDetallePublicacion;
import com.gps.chambee.negocios.casos.CURegistrarComentarioPublicacion;
import com.gps.chambee.negocios.casos.CUSeleccionarCategorias;
import com.gps.chambee.negocios.casos.CUSeleccionarComentarios;
import com.gps.chambee.negocios.casos.CUSeleccionarInteresados;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.adaptadores.ComentarioTrabajoAdapter;
import com.gps.chambee.ui.adaptadores.EtiquetaAdapter;
import com.gps.chambee.ui.adaptadores.InteresadosAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        rvEtiquetas = findViewById(R.id.rvEtiquetas);
        ivComentar = findViewById(R.id.ivComentar);

        final Usuario usuario = (Usuario) Sesion.instance().obtenerEntidad("usuario");

        Intent intent = getIntent();
        final int idPublicacion = intent.getIntExtra("id", -1);

        new CUObtenerDetallePublicacion(
            getApplicationContext(),
            new CasoUso.EventoPeticionAceptada<DetallePublicacion>() {

                @Override
                public void alAceptarPeticion(final DetallePublicacion detallePublicacion) {

                    tvCostos.setText(detallePublicacion.getCostos());
                    tvDescripcionTrabajo.setText(detallePublicacion.getDescripcion());
                    tvNombrePerfil.setText(detallePublicacion.getNombrePerfil());
                    tvNombreTrabajo.setText(detallePublicacion.getNombreTrabajo());
                    tvNumeroInteresados.setText(detallePublicacion.getListaInteresados().size());

                    new CUSeleccionarCategorias(
                            getApplicationContext(),
                            new CasoUso.EventoPeticionAceptada<List<Categoria>>() {
                                @Override
                                public void alAceptarPeticion(List<Categoria> categorias) {
                                    detallePublicacion.setListaAreasDeInteres(categorias);
                                }
                            },
                            new CasoUso.EventoPeticionRechazada() {
                                @Override
                                public void alRechazarOperacion() {
                                    Toast.makeText(PublicacionActivity.this, "Fallo cargar etiquetas", Toast.LENGTH_SHORT).show();
                                    //detallePublicacion.setListaAreasDeInteres(new List<Categoria>());
                                }
                            }
                    ).enviarPeticion(idPublicacion);

                    new CUSeleccionarInteresados(
                            getApplicationContext(),
                            new CasoUso.EventoPeticionAceptada<List<Perfil>>() {
                                @Override
                                public void alAceptarPeticion(List<Perfil> perfiles) {
                                    detallePublicacion.setListaInteresados(perfiles);
                                }
                            },
                            new CasoUso.EventoPeticionRechazada() {
                                @Override
                                public void alRechazarOperacion() {

                                }
                            }
                    ).enviarPeticion(idPublicacion);

                    new CUSeleccionarComentarios(
                            getApplicationContext(),
                            new CasoUso.EventoPeticionAceptada<List<ComentarioPublicacion>>() {
                                @Override
                                public void alAceptarPeticion(List<ComentarioPublicacion> comentarios) {
                                    detallePublicacion.setListaComentarios(comentarios);
                                }
                            },
                            new CasoUso.EventoPeticionRechazada() {
                                @Override
                                public void alRechazarOperacion() {

                                }
                            }
                    ).enviarPeticion(idPublicacion);

                    EtiquetaAdapter categoriasAdapter = new EtiquetaAdapter(
                            getApplicationContext(),
                            detallePublicacion.getListaAreasDeInteres());
                    rvEtiquetas.setAdapter(categoriasAdapter);

                    ComentarioTrabajoAdapter comentariosAdapter = new ComentarioTrabajoAdapter(
                            getApplicationContext(),
                            detallePublicacion.getListaComentarios()
                    );
                    rvComentariosTrabajo.setAdapter(comentariosAdapter);

                    InteresadosAdapter interesadosAdapter = new InteresadosAdapter(
                            getApplicationContext(),
                            detallePublicacion.getListaInteresados()
                    );
                    rvInteresados.setAdapter(interesadosAdapter);

                }
            },
            new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    // TODO Alternativa en rechazo.
                }
            }
        ).enviarPeticion(usuario);

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
                            }
                        },
                        new CasoUso.EventoPeticionRechazada() {
                            @Override
                            public void alRechazarOperacion() {
                                Toast.makeText(PublicacionActivity.this, "Fallo al enviar comentario!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ).enviarPeticion( usuario.getId(), comentario, fecha, idPublicacion);
            }
        });
    }

}