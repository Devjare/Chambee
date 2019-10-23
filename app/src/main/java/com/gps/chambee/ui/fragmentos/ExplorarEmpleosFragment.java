package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.PublicacionEmpresaAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorarEmpleosFragment extends Fragment {

    private RecyclerView rvEmpleos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_explorar_empleos, container, false);

        rvEmpleos = view.findViewById(R.id.rvEmpleos);
        List<Object> lista = new ArrayList<>();
        lista.add(0);
        lista.add(0);

        rvEmpleos.setLayoutManager(new LinearLayoutManager(view.getContext()));

        PublicacionEmpresaAdapter adapter = new PublicacionEmpresaAdapter(view.getContext(),lista);
        rvEmpleos.setAdapter(adapter);

        return view;
    }
}
