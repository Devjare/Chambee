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
import com.gps.chambee.negocios.casos.CUListarServicios;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CUSeleccionarMedallas;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.negocios.casos.firebase.CUSeleccionarPerfilDetallado;
import com.gps.chambee.ui.adaptadores.ServiciosAdapter;
import com.gps.chambee.ui.adaptadores.MedallasAdapter;
import com.gps.chambee.ui.adaptadores.RegistroTrabajosAdapter;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private CircleImageView civImagenPerfilUsuario;
    private TextView tvNombreUsuario;
    private TextView tvEdadUsuario;
    private ImageView ivInsigniaPrincipal;
    private TextView tvPuertoUsuario;
    private TextView tvPuntajeEstrellas;
    private TextView tvNumeroCalificaciones;
    private TextView tvCiudadUsuario;
    private TextView tvAcercaDeMi;
    private RecyclerView rvEtiquetas;
    private RecyclerView rvMedallas;
    private RecyclerView rvRegistroTrabajos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);

        civImagenPerfilUsuario = view.findViewById(R.id.civImagenPerfilUsuario);
        tvNombreUsuario = view.findViewById(R.id.tvNombreUsuario);
        tvEdadUsuario = view.findViewById(R.id.tvEdadUsuario);
        ivInsigniaPrincipal = view.findViewById(R.id.ivInsigniaPrincipal);
        tvPuertoUsuario = view.findViewById(R.id.tvPuestoUsuario);
        tvPuntajeEstrellas = view.findViewById(R.id.tvPuntajeEstrellas);
        tvNumeroCalificaciones = view.findViewById(R.id.tvNumeroCalificaciones);
        tvCiudadUsuario = view.findViewById(R.id.tvCiudadUsuario);
        tvAcercaDeMi = view.findViewById(R.id.tvAcercaDeMi);
        rvMedallas = view.findViewById(R.id.rvMedallas);
        rvEtiquetas = view.findViewById(R.id.rvEtiquetas);
        rvRegistroTrabajos = view.findViewById(R.id.rvRegistroTrabajos);

        llenarDatosPerfil();

        return view;
    }

    private void llenarDatosPerfil() {
        seleccionarPerfilDetallado();
        listarServicios();
        listarMedallas();
        listarRegistrosTrabajo();
    }

    private void seleccionarPerfilDetallado() {

        // TODO Caso de uso para seleccionar el perfil detallado del usuario

        // Suoponiendo que ya se obtuvo el perfil detallado del usuario...
        rvServicios = view.findViewById(R.id.rvServicios);

        List<String> lista = new ArrayList<>();

        ServiciosAdapter adapter = new ServiciosAdapter(view.getContext(),lista);
        rvServicios.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvServicios.setAdapter(adapter);

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

        PerfilDetallado perfilDetallado = new PerfilDetallado();

        tvNombreUsuario.setText(perfilDetallado.getNombrePersona() + " " + perfilDetallado.getApellidosPersona());
        tvEdadUsuario.setText(String.valueOf(perfilDetallado.getEdad()));
        tvPuertoUsuario.setText(perfilDetallado.getPuesto());
        tvPuntajeEstrellas.setText(String.valueOf(perfilDetallado.getEstrellas()));

        /*new CUSeleccionarPerfilDetallado(getContext(), new CasoUso.EventoPeticionAceptada<PerfilDetallado>() {

            @Override
            public void alAceptarPeticion(PerfilDetallado perfilDetallado) {

            }

        }, new CasoUso.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion() {

            }

        }).enviarPeticion();*/
    }

    private void listarMedallas() {
        new CUSeleccionarMedallas(getContext(), new CasoUso.EventoPeticionAceptada<List<Medalla>>() {
            @Override
            public void alAceptarPeticion(List<Medalla> medallas) {
                llenarMedallas(listaMedallas, view);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() { }
        });

        MedallasAdapter adapter = new MedallasAdapter(getContext(), null);
        rvMedallas.setAdapter(adapter);
        rvMedallas.setLayoutManager(new LinearLayoutManager(getContext()));
      
        
        new CUListarServicios(getContext(), new CasoUso.EventoPeticionAceptada<List<String>>() {
            @Override
            public void alAceptarPeticion(List<String> strings) {

            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {

            }
        });

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

       // new CUSeleccionarPerfilDetallado(getContext(), new CasoUso.EventoPeticionAceptada<List<PerfilDetallado>>() {
       //     @Override
       //     public void alAceptarPeticion(List<PerfilDetallado> perfilDetallados) { } 
    }

    private void listarRegistrosTrabajo() {

        // TODO Servicio web para listar registros de trabajo

        RegistroTrabajosAdapter adapter = new RegistroTrabajosAdapter(getContext(), null);
        rvRegistroTrabajos.setAdapter(adapter);
        rvRegistroTrabajos.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void listarServicios() {

    }

    private void llenarServicios(List<String> listaServicios, View view){
        ServiciosAdapter adapter = new ServiciosAdapter(view.getContext(), listaServicios);
        rvServicios.setLayoutManager(new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically(){return false;}
        });
        rvServicios.setHasFixedSize(true);
        rvServicios.setAdapter(adapter);
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