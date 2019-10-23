package com.gps.chambee.ui.fragmentos;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.NotificacionesAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesFragment extends Fragment {

    private RecyclerView rvNotificaciones;
    private TextView tvSalirNotificaciones;

    @Override
    public View onCreateView(LayoutInflater layout, ViewGroup container, Bundle savedInstanceState) {
        View view = layout.inflate(R.layout.notificaciones_fragment,container,false);

        rvNotificaciones = view.findViewById(R.id.rvNotificaciones);
        tvSalirNotificaciones = view.findViewById(R.id.tvSalirNotificaciones);

        List<Object> lista = new ArrayList<>();
        lista.add(0);
        lista.add(0);

        NotificacionesAdapter adapter = new NotificacionesAdapter(view.getContext(),lista);

        rvNotificaciones.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvNotificaciones.setAdapter(adapter);

        tvSalirNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
