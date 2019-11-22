package com.gps.chambee.ui.fragmentos;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Medalla;
import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.entidades.vistas.Puesto;
import com.gps.chambee.negocios.casos.CUListarPuestos;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CUSeleccionarMedallas;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.negocios.casos.firebase.CUSeleccionarPerfilDetallado;
import com.gps.chambee.ui.adaptadores.EtiquetaAdapter;
import com.gps.chambee.ui.adaptadores.MedallasAdapter;
import com.gps.chambee.ui.adaptadores.RegistroTrabajosAdapter;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private RecyclerView rvRegistroTrabajos;
    private RecyclerView rvMedallas;
    private CircleImageView cimImagenPerfilUsuario;
    private TextView tvNombreUsuario;
    private TextView tvEdadUsuario;
    private ImageView ivInsigniaPrincipal;
    private TextView tvPuertoUsuario;
    private TextView tvPuntajeEstrellas;
    private TextView tvNumeroCalificaciones;
    private TextView tvCiudadUsuario;
    private TextView tvAcercaDeMiPerfil;
    private RecyclerView rvEtiquetas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_perfil,container,false);

        rvRegistroTrabajos = view.findViewById(R.id.rvRegistroTrabajos);
        cimImagenPerfilUsuario = view.findViewById(R.id.cimImagenPerfilUsuario);
        tvNombreUsuario = view.findViewById(R.id.tvNombreUsuario);
        tvEdadUsuario = view.findViewById(R.id.tvEdadUsuario);
        ivInsigniaPrincipal = view.findViewById(R.id.ivInsigniaPrincipal);
        tvPuertoUsuario = view.findViewById(R.id.tvPuestoUsuario);
        tvPuntajeEstrellas = view.findViewById(R.id.tvPuntajeEstrellas);
        tvNumeroCalificaciones = view.findViewById(R.id.tvNumeroCalificaciones);
        tvCiudadUsuario = view.findViewById(R.id.tvCiudadUsuario);
        tvAcercaDeMiPerfil = view.findViewById(R.id.tvAcercaDeMiPerfil);
        rvMedallas = view.findViewById(R.id.rvMedallas);
        rvEtiquetas = view.findViewById(R.id.rvEtiquetas);

        List<Object> lista = new ArrayList<>();
        lista.add(0);

        EtiquetaAdapter adapter = new EtiquetaAdapter(view.getContext(),lista);
        rvEtiquetas.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvEtiquetas.setAdapter(adapter);

        final List<Puesto> listaPuestos = new ArrayList<>();

        RegistroTrabajosAdapter adapte = new RegistroTrabajosAdapter(view.getContext(),listaPuestos);
        rvRegistroTrabajos.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvRegistroTrabajos.setAdapter(adapte);

        //
        final List<Medalla> listaMedallas = new ArrayList<>();

        final MedallasAdapter adMedallas = new MedallasAdapter(view.getContext(),listaMedallas);
        rvMedallas.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvMedallas.setAdapter(adMedallas);

        /*String nombreUsuario = tvNombreUsuario.getText().toString();
        String edadUsuario = tvEdadUsuario.getText().toString();
        String puestoUsuario = tvPuertoUsuario.getText().toString();
        String puntajeEstrella = tvPuntajeEstrellas.getText().toString();
        String numeroCalificaciones = tvNumeroCalificaciones.getText().toString();
        String paisUsuario = tvCiudadUsuario.getText().toString();
        String acercaDeMiPerfil = tvAcercaDeMiPerfil.getText().toString();*/


        CUObtenerImagen cuImagenPerfil = new CUObtenerImagen(getContext(), new CasoUso.EventoPeticionAceptada<Bitmap>() {
            @Override
            public void alAceptarPeticion(Bitmap bitmap) {
                cimImagenPerfilUsuario.setImageBitmap(bitmap);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        });

        CUObtenerImagen cuImagenPortada = new CUObtenerImagen(getContext(), new CasoUso.EventoPeticionAceptada<Bitmap>() {
            @Override
            public void alAceptarPeticion(Bitmap bitmap) {
                ivInsigniaPrincipal.setImageBitmap(bitmap);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        });

        new CUSeleccionarMedallas(getContext(), new CasoUso.EventoPeticionAceptada<List<Medalla>>() {
            @Override
            public void alAceptarPeticion(List<Medalla> medallas) {
                llenarMedallas(listaMedallas, view);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        }).enviarPeticion();

        new CUListarPuestos(getContext(), new CasoUso.EventoPeticionAceptada<List<Puesto>>() {
            @Override
            public void alAceptarPeticion(List<Puesto> puestos) {
                llenarRegistroTrabajoAdapter(listaPuestos, view);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        });

        new CUSeleccionarPerfilDetallado(getContext(), new CasoUso.EventoPeticionAceptada<List<PerfilDetallado>>() {
            @Override
            public void alAceptarPeticion(List<PerfilDetallado> perfilDetallados) {

            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        }).enviarPeticion();

        return view;
    }

    private void llenarMedallas(List<Medalla> listaMedallas, View view){
        MedallasAdapter adapter = new MedallasAdapter(view.getContext(), listaMedallas);
        rvMedallas.setLayoutManager(new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically(){return false;}
        });
        rvMedallas.setHasFixedSize(true);
        rvMedallas.setAdapter(adapter);
    }

    private void llenarRegistroTrabajoAdapter(List<Puesto> puestos, View view){
        RegistroTrabajosAdapter adapter = new RegistroTrabajosAdapter(view.getContext(), puestos);
        rvRegistroTrabajos.setLayoutManager(new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically(){return false;}
        });
        rvRegistroTrabajos.setHasFixedSize(true);
        rvRegistroTrabajos.setAdapter(adapter);
    }
}