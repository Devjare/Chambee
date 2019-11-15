package com.gps.chambee.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.entidades.PublicacionEmpresa;
import com.gps.chambee.entidades.PublicacionPersona;
import com.gps.chambee.negocios.casos.CUListarPublicacionesEmpresas;
import com.gps.chambee.negocios.casos.CUListarPublicacionesPersonas;
import com.gps.chambee.negocios.casos.CUSeleccionarCategorias;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.actividades.PublicacionActivity;
import com.gps.chambee.ui.actividades.PublicarTrabajoActivity;
import com.gps.chambee.ui.actividades.SolicitarEmpleoActivity;
import com.gps.chambee.ui.adaptadores.CategoriasAdapter;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;
import com.gps.chambee.ui.adaptadores.PublicacionPersonaAdapter;

import java.util.List;

public class InicioFragment extends Fragment {

    private String TAG = this.getTag();

    private FloatingActionButton btnSolicitarEmpleo;
    private FloatingActionButton btnPublicarEmpleo;
    private RecyclerView rvPublicaciones;
    private RecyclerView rvPublicacionesEmpleados;
    private RecyclerView rvCategorias;
    private TextView tvVerTodoEmpleos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);
        rvPublicacionesEmpleados = view.findViewById(R.id.rvPublicacionesEmpleados);
        rvCategorias = view.findViewById(R.id.rvCategorias);
        tvVerTodoEmpleos = view.findViewById(R.id.tvVerTodoEmpleos);
        btnPublicarEmpleo = view.findViewById(R.id.btnPublicarEmpleo);
        btnSolicitarEmpleo = view.findViewById(R.id.btnSolicitarEmpleo);

        tvVerTodoEmpleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PublicacionActivity.class));
            }
        });
        btnPublicarEmpleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PublicarTrabajoActivity.class));
            }
        });
        btnSolicitarEmpleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SolicitarEmpleoActivity.class));
            }
        });

        String result = cargarInicio(view);
        Log.d(TAG, "onCreateView: cargarInicio result: " + result);
        if (result != "Everything went well!"){
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private String cargarInicio(final View view){

        final String[] message = {"Everything went well!"};

        CUSeleccionarCategorias cuSeleccionarCategorias = new CUSeleccionarCategorias(getContext(),
                new CasoUso.EventoPeticionAceptada<List<Categoria>>() {
                    @Override
                    public void alAceptarPeticion(List<Categoria> categorias) {
                        // mostrar categorías.
                        llenarCategorias(categorias, view);
                    }
                }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {
                message[0] = "Failed to load categories!";
            }
        });

        cuSeleccionarCategorias.enviarPeticion();

        new CUListarPublicacionesEmpresas(getContext(),
                new CasoUso.EventoPeticionAceptada<List<PublicacionEmpresa>>() {
                    @Override
                    public void alAceptarPeticion(List<PublicacionEmpresa> publicaciones) {
                        // mostrar categorías.
                        llenarPublicacionesEmpresas(publicaciones, view);
                    }
                }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {
                message[0] = "Failed to load categories!";
                Log.i("InicioFragment", message[0]);
            }
        }).enviarPeticion();

        new CUListarPublicacionesPersonas(
                getContext(),
                new CasoUso.EventoPeticionAceptada<List<PublicacionPersona>>() {
                    @Override
                    public void alAceptarPeticion(List<PublicacionPersona> publicacionPersonas) {
                        llenarPublicacionesPersonas(publicacionPersonas, view);
                    }
                }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {
                message[0] = "Failed to load categories!";
            }
        }).enviarPeticion();

        return message[0];
    }

    private void llenarCategorias(List<Categoria> categorias, View view) {

        CategoriasAdapter caAdapter = new CategoriasAdapter(view.getContext(), categorias);
        rvCategorias.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvCategorias.setHasFixedSize(true);
        rvCategorias.setAdapter(caAdapter);

    }

    private void llenarPublicacionesEmpresas(List<PublicacionEmpresa> empleados, View view){
        PublicacionEmpresaAdapter ppAdapter = new PublicacionEmpresaAdapter(
                view.getContext(),
                empleados);
        rvPublicacionesEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPublicacionesEmpleados.setAdapter(ppAdapter);
    }

    private void llenarPublicacionesPersonas(List<PublicacionPersona> publicacionPersonas, View view) {
        PublicacionPersonaAdapter adapter = new PublicacionPersonaAdapter(
                view.getContext(),
                publicacionPersonas);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPublicaciones.setHasFixedSize(true);
        rvPublicaciones.setAdapter(adapter);
    }
}