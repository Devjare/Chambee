package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.MensajeFirebase;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CFEnviarMensaje;
import com.gps.chambee.negocios.casos.firebase.CFListarMensajes;
import com.gps.chambee.negocios.casos.firebase.CFSeleccionarUsuarioFirebaseId;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.adaptadores.MensajesChatAdapter;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ImageView ivRegresar;
    private ImageView ivCalificar;
    private TextView tvNombreUsuario;
    private TextView tvUserCelPhoneActual;
    private EditText etMensaje;
    private ImageView ivEnviarMensaje;
    private RecyclerView rvChat;
    private ProgressDialog progressDialog;

    private UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());
    private UsuarioFirebase usuarioReceptor;
    private String idReceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ivRegresar = findViewById(R.id.ivRegresar);
        ivCalificar = findViewById(R.id.ivCalificar);
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario);
        tvUserCelPhoneActual = findViewById(R.id.tvUserCelPhoneActual);
        etMensaje = findViewById(R.id.etMensaje);
        ivEnviarMensaje = findViewById(R.id.ivEnviarMensaje);
        rvChat = findViewById(R.id.rvChat);

        ivRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this, CalificarUsuarioActivity.class));
            }
        });

        ivEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensaje();
            }
        });

        idReceptor = getIntent().getStringExtra("idReceptor");

        llenarDatosReceptor();
        llenarMensajes();
    }

    private void llenarMensajes() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        new CFListarMensajes(usuarioFirebase.getId(), idReceptor, new CasoUsoFirebase.EventoPeticionAceptada<List<MensajeFirebase>>() {

            @Override
            public void alAceptarPeticion(List<MensajeFirebase> mensajeFirebases) {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, mensajeFirebases.toString(), Toast.LENGTH_LONG).show();

                MensajesChatAdapter adapter = new MensajesChatAdapter(ChatActivity.this, mensajeFirebases);
                rvChat.setAdapter(adapter);
                rvChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }

        }).enviarPeticion();
    }

    private void llenarDatosReceptor() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        new CFSeleccionarUsuarioFirebaseId(new CasoUsoFirebase.EventoPeticionAceptada<UsuarioFirebase>() {

            @Override
            public void alAceptarPeticion(UsuarioFirebase usuarioFirebase) {
                progressDialog.dismiss();

                usuarioReceptor = usuarioFirebase;
                tvNombreUsuario.setText(usuarioReceptor.getNombres() + " " + usuarioReceptor.getApellidos());
                tvUserCelPhoneActual.setText(usuarioReceptor.getTelefono());

            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }

        }).enviarPeticion(idReceptor);
    }

    private void enviarMensaje() {
        String mensaje = etMensaje.getText().toString();

        if (mensaje.isEmpty())
            return;

        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        String emisor = usuarioFirebase.getId();
        String receptor = usuarioReceptor.getId();

        new CFEnviarMensaje(new CasoUsoFirebase.EventoPeticionAceptada<String>() {

            @Override
            public void alAceptarPeticion(String s) {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, s, Toast.LENGTH_SHORT).show();

                etMensaje.setText("");
            }

        }, new CasoUsoFirebase.EventoPeticionRechazada() {

            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }

        }).enviarPeticion(emisor, receptor, mensaje);
    }
}
