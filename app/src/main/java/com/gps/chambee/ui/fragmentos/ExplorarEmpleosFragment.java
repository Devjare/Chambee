package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.entidades.vistas.PublicacionEmpresa;
import com.gps.chambee.entidades.vistas.PublicacionGeneral;
import com.gps.chambee.negocios.casos.CUListarPublicaciones;
import com.gps.chambee.negocios.casos.CUListarPublicacionesEmpresas;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorarEmpleosFragment extends Fragment {

    private RecyclerView rvEmpleos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.fragment_explorar_empleos, container, false);

        rvEmpleos = view.findViewById(R.id.rvEmpleos);
        List<PublicacionEmpresa> lista = new ArrayList<>();

        rvEmpleos.setLayoutManager(new LinearLayoutManager(view.getContext()));

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
        List<PublicacionEmpresa> publicacionEmpresas = new ArrayList<>();

        for (PublicacionGeneral publicacion: publicacionGenerals) {

            if (!(publicacion.getUrlImagenTrabajo().length() < 1 && publicacion.getNombreTrabajo().length() < 1)) {

            } else {
                PublicacionEmpresa publicacionEmpresa = publicacion.toPublicacionEmpresa();
                publicacionEmpresas.add( publicacionEmpresa );
            }
        }

        llenarPublicacionesEmpresas(publicacionEmpresas, view);
    }

    private void llenarPublicacionesEmpresas(List<PublicacionEmpresa> publicacionEmpresas, View view) {
        PublicacionEmpresaAdapter ppAdapter = new PublicacionEmpresaAdapter(
                view.getContext(),
                publicacionEmpresas);
        rvEmpleos.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvEmpleos.setAdapter(ppAdapter);
    }
}