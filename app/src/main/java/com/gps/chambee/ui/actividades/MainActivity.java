package com.gps.chambee.ui.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CFEnviarMensaje;
import com.gps.chambee.negocios.casos.firebase.CFListarUsuarios;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.fragmentos.ExploraFragment;
import com.gps.chambee.ui.fragmentos.InicioFragment;
import com.gps.chambee.ui.fragmentos.MensajesFragment;
import com.gps.chambee.ui.fragmentos.NotificacionesFragment;
import com.gps.chambee.ui.fragmentos.PerfilFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bRegistrar;
    private ImageView ivNotificaciones;
    private ImageView ivConfiguracion;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivConfiguracion = findViewById(R.id.ivConfiguracion);
        ivConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              startActivity(new Intent(MainActivity.this, ConfiguracionesActivity.class));
            }
        });
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.iHome);

        Fragment sFragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId=menuItem.getItemId();
                Fragment sFragment=null;
                switch (itemId){
                    case R.id.iHome:
                        sFragment = new InicioFragment();
                        break;
                    case R.id.iExplorar:
                        sFragment =new ExploraFragment();
                        break;
                    case R.id.iTrabajo:
                        sFragment = new MensajesFragment();
                        break;
                    case R.id.iPerfil:
                        sFragment = new PerfilFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).commit();
                return true;
            }
        });

        ivNotificaciones = findViewById(R.id.ivNotificaciones);
        ivNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment sFragment = new NotificacionesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
            }
        });

        foo();
        //listUsers();
    }

    private void foo() {
        UsuarioFirebase usuarioFirebase = (UsuarioFirebase) Sesion.instance().obtenerEntidad(UsuarioFirebase.getNombreClase());
        Toast.makeText(this, usuarioFirebase.toString(), Toast.LENGTH_SHORT).show();
    }

    private void listUsers() {
        new CFListarUsuarios(new CasoUsoFirebase.EventoPeticionAceptada<List<Usuario>>() {
            @Override
            public void alAceptarPeticion(List<Usuario> usuarios) {
                Toast.makeText(MainActivity.this, String.valueOf(usuarios.size()), Toast.LENGTH_SHORT).show();
                //sendMessage(usuarios);
            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }).enviarPeticion();
    }

    private void sendMessage(List<Usuario> usuarios) {
        Log.e("chambee", usuarios.toString());

        String emisor = usuarios.get(0).getId();
        String receptor = usuarios.get(1).getId();
        String mensaje = "hola perro k ace";

        Log.e("chambee", String.format("emisor: '%s', receptor: '%s', mensaje: '%s'", emisor, receptor, mensaje));

        new CFEnviarMensaje(new CasoUsoFirebase.EventoPeticionAceptada<String>() {
            @Override
            public void alAceptarPeticion(String s) {
                Toast.makeText(MainActivity.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error mensaje no enviado", Toast.LENGTH_SHORT).show();
            }
        }).enviarPeticion(emisor, receptor, mensaje);
    }
}


