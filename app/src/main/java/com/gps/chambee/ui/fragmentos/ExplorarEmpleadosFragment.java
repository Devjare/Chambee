package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.entidades.PublicacionEmpresa;
import com.gps.chambee.entidades.PublicacionPersona;
import com.gps.chambee.negocios.casos.CUListarPublicacionesEmpresas;
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

        new CUListarPublicacionesPersonas(getContext(), new CasoUso.EventoPeticionAceptada<List<PublicacionPersona>>() {

            @Override
            public void alAceptarPeticion(List<PublicacionPersona> publicaciones) {
                llenarPublicacionesEmpleados(publicaciones, view);
            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {
            }
        }).enviarPeticion();

        return view;
    }

    private void llenarPublicacionesEmpleados(List<PublicacionPersona> publicaciones, View view){
        PublicacionPersonaAdapter adapter = new PublicacionPersonaAdapter(view.getContext(), publicaciones);
        rvEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically(){return false;}
        });
        rvEmpleados.setHasFixedSize(true);
        rvEmpleados.setAdapter(adapter);
    }

}