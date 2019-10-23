package com.gps.chambee.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.gps.chambee.R;
import com.gps.chambee.ui.actividades.PublicacionActivity;
import com.gps.chambee.ui.actividades.PublicarTrabajoActivity;
import com.gps.chambee.ui.actividades.SolicitarEmpleoActivity;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;
import com.gps.chambee.ui.adaptadores.PublicacionPersonaAdapter;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private FloatingActionButton btnSolicitarEmpleo;
    private FloatingActionButton btnPublicarEmpleo;
    private RecyclerView rvPublicaciones;
    private RecyclerView rvPublicacionesEmpleados;
    private TextView tvVerTodoEmpleos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio,container,false);

        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);
        rvPublicacionesEmpleados = view.findViewById(R.id.rvPublicacionesEmpleados);
        tvVerTodoEmpleos=view.findViewById(R.id.tvVerTodoEmpleos);
        btnPublicarEmpleo=view.findViewById(R.id.btnPublicarEmpleo);
        btnSolicitarEmpleo = view.findViewById(R.id.btnSolicitarEmpleo);

        //RecyclerView de publicaciones de empleadores

        List<Object> lista = new ArrayList<>();
        lista.add(0);
        lista.add(0);

        PublicacionEmpresaAdapter adapter = new PublicacionEmpresaAdapter(view.getContext(), lista);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPublicaciones.setHasFixedSize(true);
        rvPublicaciones.setAdapter(adapter);

        //RecyclerView de publicaciones de personas
        List<Object> empleados=new ArrayList<>();
        empleados.add(0);
        empleados.add(0);

        PublicacionPersonaAdapter ppAdapter=new PublicacionPersonaAdapter(view.getContext(),empleados);
        rvPublicacionesEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()){
           @Override
           public boolean canScrollVertically(){
               return false;
           }
        });
        rvPublicacionesEmpleados.setAdapter(ppAdapter);

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

        return view;

    }
}
