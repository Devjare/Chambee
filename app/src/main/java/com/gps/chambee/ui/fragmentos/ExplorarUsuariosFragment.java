package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.entidades.vistas.VistaExplorarUsuario;
import com.gps.chambee.negocios.casos.firebase.CFListarUsuarios;
import com.gps.chambee.negocios.casos.firebase.CFListarUsuariosFirebase;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.ui.adaptadores.ExplorarUsuariosAdapter;

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

        listarUsuarios();

        return view;
    }

    private void listarUsuarios() {
        new CFListarUsuariosFirebase(new CasoUsoFirebase.EventoPeticionAceptada<List<UsuarioFirebase>>() {

            @Override
            public void alAceptarPeticion(List<UsuarioFirebase> usuarioFirebases) {
                List<VistaExplorarUsuario> vistas = new ArrayList<>();

                for (UsuarioFirebase usuario : usuarioFirebases) {

                    VistaExplorarUsuario vistaExplorarUsuario = new VistaExplorarUsuario.Builder()
                            .setIdUsuario(usuario.getId())
                            .setNombreUsuario(usuario.getNombres() + " " + usuario.getApellidos())
                            .setCiudad(usuario.getTelefono())
                            .build();

                    vistas.add(vistaExplorarUsuario);
                }

                ExplorarUsuariosAdapter adapter = new ExplorarUsuariosAdapter(ExplorarUsuariosFragment.this.getContext(), vistas);

                rvUsuarios.setLayoutManager(new LinearLayoutManager(ExplorarUsuariosFragment.this.getContext()));
                rvUsuarios.setAdapter(adapter);
            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                Toast.makeText(ExplorarUsuariosFragment.this.getContext(), "Error al buscar usuarios", Toast.LENGTH_SHORT).show();
            }

        }).enviarPeticion();
    }
}
