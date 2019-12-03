package com.gps.chambee.ui.fragmentos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.ChatFirebase;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.entidades.vistas.VistaChat;
import com.gps.chambee.negocios.casos.firebase.CFListarChats;
import com.gps.chambee.negocios.casos.firebase.CFListarChatsUsuarios;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.actividades.ChatActivity;
import com.gps.chambee.ui.adaptadores.ChatsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MensajesFragment extends Fragment {

    private RecyclerView rvMensajes;
    private ImageView ivCrearMensaje;
    private EditText etBuscarMensajes;
    private ProgressDialog progressDialog;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());
    private List<ChatFirebase> chatsFirebase;
    private List<UsuarioFirebase> chatsUsuarios;
    private List<VistaChat> vistasChats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);

        etBuscarMensajes = view.findViewById(R.id.etBuscarMensajes);
        rvMensajes = view.findViewById(R.id.rvMensajes);
        ivCrearMensaje = view.findViewById(R.id.ivCrearMensaje);

        ivCrearMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ChatActivity.class));
            }
        });

        obtenerChats();

        return view;
    }

    private void obtenerChats() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        new CFListarChats(usuarioFirebase.getId(), new CasoUsoFirebase.EventoPeticionAceptada<List<ChatFirebase>>() {

            @Override
            public void alAceptarPeticion(List<ChatFirebase> chats) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), chats.toString(), Toast.LENGTH_SHORT).show();

                chatsFirebase = chats;
                obtenerUsuariosChats();
            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }

        }).enviarPeticion();
    }

    private void obtenerUsuariosChats() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        new CFListarChatsUsuarios(chatsFirebase, new CasoUsoFirebase.EventoPeticionAceptada<List<UsuarioFirebase>>() {

            @Override
            public void alAceptarPeticion(List<UsuarioFirebase> usuarioFirebases) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), usuarioFirebases.toString(), Toast.LENGTH_SHORT).show();

                chatsUsuarios = usuarioFirebases;
                llenarVistasChats();
            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }

        }).enviarPeticion();
    }

    private void llenarVistasChats() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        vistasChats = new ArrayList<>();

        for (UsuarioFirebase usuario : chatsUsuarios) {

            VistaChat vistaChat = new VistaChat.Builder()
                    .setIdEmisor(usuarioFirebase.getId())
                    .setIdRecepetor(usuario.getId())
                    .setNombreUsuario(usuario.getNombres() + " " + usuario.getApellidos())
                    .build();

            vistasChats.add(vistaChat);
        }

        ChatsAdapter adapter = new ChatsAdapter(getContext(), vistasChats);
        rvMensajes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMensajes.setAdapter(adapter);

        progressDialog.dismiss();
    }
}
