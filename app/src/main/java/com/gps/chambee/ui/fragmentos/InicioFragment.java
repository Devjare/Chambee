package com.gps.chambee.ui.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.gps.chambee.ui.actividades.Publicacion;
import com.gps.chambee.ui.actividades.PublicarTrabajo;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;
import com.gps.chambee.ui.adaptadores.PublicacionPersonaAdapter;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private FloatingActionButton btnPublicarEmpleo;
    private RecyclerView rvPublicaciones;
    private RecyclerView rvPublicacionesEmpleados;
    private TextView tvVerTodoEmpleos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio,container,false);

        //RecyclerView de publicaciones de empleadores
        List<Object> lista = new ArrayList<>();
        lista.add(0);
        lista.add(0);

        PublicacionEmpresaAdapter adapter = new PublicacionEmpresaAdapter(view.getContext(), lista);

        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPublicaciones.setHasFixedSize(true);
        rvPublicaciones.setAdapter(adapter);

        //
        //RecyclerView de publicaciones de personas
        List<Object> empleados=new ArrayList<>();
        empleados.add(0);
        empleados.add(0);

        PublicacionPersonaAdapter ppAdapter=new PublicacionPersonaAdapter(view.getContext(),empleados);

        rvPublicacionesEmpleados = view.findViewById(R.id.rvPublicacionesEmpleados);
        rvPublicacionesEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()){
           @Override
           public boolean canScrollVertically(){
               return false;
           }
        });
        rvPublicacionesEmpleados.setAdapter(ppAdapter);

        //

        tvVerTodoEmpleos=view.findViewById(R.id.tvVerTodoEmpleos);
        tvVerTodoEmpleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Publicacion.class));
            }
        });

        btnPublicarEmpleo=view.findViewById(R.id.btnPublicarEmpleo);
        btnPublicarEmpleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PublicarTrabajo.class));
            }
        });

        return view;

    }
}
