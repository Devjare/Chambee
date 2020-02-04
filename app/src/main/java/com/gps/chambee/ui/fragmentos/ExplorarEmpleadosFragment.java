package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.entidades.vistas.PublicacionEmpresa;
import com.gps.chambee.entidades.vistas.PublicacionGeneral;
import com.gps.chambee.entidades.vistas.PublicacionPersona;
import com.gps.chambee.negocios.casos.CUListarPublicaciones;
import com.gps.chambee.negocios.casos.CUListarPublicacionesPersonas;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;
import com.gps.chambee.ui.adaptadores.PublicacionPersonaAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorarEmpleadosFragment extends Fragment {

    private RecyclerView rvEmpleados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_explorar_empleados, container, false);

        List<PublicacionPersona> list = new ArrayList<>();

        rvEmpleados = view.findViewById(R.id.rvEmpleados);

        cargarPublicaciones(view);

        return view;
    }

    private void cargarPublicaciones(final View view) {
            new CUListarPublicaciones(getContext(),
                    new CasoUso.EventoPeticionAceptada<List<PublicacionGeneral>>() {
                        @Override
                        public void alAceptarPeticion(List<PublicacionGeneral> publicacionGenerals) {
                            llenarPublicaciones(publicacionGenerals, view);
                        }
                    },
                    new CasoUso.EventoPeticionRechazada() {
                        @Override
                        public void alRechazarOperacion() {

                        }
                    }).enviarPeticion();
    }

    private void llenarPublicaciones(List<PublicacionGeneral> publicacionGenerals, View view) {
        List<PublicacionPersona> publicaciones = new ArrayList<>();

        for (PublicacionGeneral publicacion: publicacionGenerals) {

            if (!(publicacion.getUrlImagenTrabajo().length() < 1 && publicacion.getNombreTrabajo().length() < 1)) {
                PublicacionPersona publicacionPersona = publicacion.toPublicacionPersona();
                publicaciones.add( publicacionPersona );
            }
        }

        llenarPublicacionesEmpleados(publicaciones, view);
    }

    private void llenarPublicacionesEmpleados(List<PublicacionPersona> publicaciones, View view){
        PublicacionPersonaAdapter ppAdapter = new PublicacionPersonaAdapter(
                view.getContext(),
                publicaciones);
        rvEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvEmpleados.setAdapter(ppAdapter);
    }
}