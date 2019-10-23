package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.PublicacionPersonaAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorarEmpleadosFragment extends Fragment {

    private RecyclerView rvEmpleados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_explorar_empleados, container, false);

        List<Object> list = new ArrayList<>();
        list.add(0);
        list.add(0);

        PublicacionPersonaAdapter adapter = new PublicacionPersonaAdapter(view.getContext(),list);
        rvEmpleados = view.findViewById(R.id.rvEmpleados);

        rvEmpleados.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvEmpleados.setAdapter(adapter);

        return view;
    }

}
