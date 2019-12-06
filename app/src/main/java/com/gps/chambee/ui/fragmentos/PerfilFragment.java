package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Medalla;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.entidades.vistas.PerfilDetallado;
import com.gps.chambee.negocios.casos.CUSeleccionarPerfilDetallado;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.adaptadores.MedallasAdapter;
import com.gps.chambee.ui.adaptadores.RegistroTrabajosAdapter;

import java.util.ArrayList;

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

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());

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

        new CUSeleccionarPerfilDetallado(getContext(), new CasoUso.EventoPeticionAceptada<PerfilDetallado>() {

            @Override
            public void alAceptarPeticion(PerfilDetallado perfilDetallado) {
                tvNombreUsuario.setText(perfilDetallado.getNombreUsuario() + " " + perfilDetallado.getApellidosUsuario());
                tvEdadUsuario.setText(String.valueOf(perfilDetallado.getFechaNac()));
                tvPuertoUsuario.setText(perfilDetallado.getPuesto());
                tvPuntajeEstrellas.setText(String.valueOf(perfilDetallado.getCalificacion()));
            }

        }, new CasoUso.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion() {
                Toast.makeText(getContext(), "Error al obtener perfil detallado del usuario", Toast.LENGTH_SHORT).show();
            }

        }).enviarPeticion(usuarioFirebase.getId());
    }

    private void listarMedallas() {

        // TODO Servicio web para listar medallas del usuario

        MedallasAdapter adapter = new MedallasAdapter(getContext(), new ArrayList<Medalla>());
        rvMedallas.setAdapter(adapter);
        rvMedallas.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void listarRegistrosTrabajo() {

        // TODO Servicio web para listar registros de trabajo

        RegistroTrabajosAdapter adapter = new RegistroTrabajosAdapter(getContext(), new ArrayList<>());
        rvRegistroTrabajos.setAdapter(adapter);
        rvRegistroTrabajos.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void listarServicios() {

    }
}
