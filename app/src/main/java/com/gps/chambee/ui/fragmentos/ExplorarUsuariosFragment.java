package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gps.chambee.R;
import com.gps.chambee.entidades.vistas.PublicacionEmpresa;
import com.gps.chambee.ui.adaptadores.MedallasAdapter;
import com.gps.chambee.ui.adaptadores.UsuariosAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorarUsuariosFragment extends Fragment {

    private RecyclerView rvUsuarios;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.fragment_explorar_usuarios, container, false);

        rvUsuarios = view.findViewById(R.id.rvUsuarios);
        List<Object> lista = new ArrayList<>();
        lista.add(0);
        lista.add(0);
        lista.add(0);

        UsuariosAdapter adUsuarios = new UsuariosAdapter(view.getContext(),lista);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvUsuarios.setAdapter(adUsuarios);
        return view;
    }

}
