package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CFAutenticarUsuario;
import com.gps.chambee.negocios.casos.firebase.CFSeleccionarUsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.negocios.casos.CUIniciarSesion;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.negocios.validadores.ValidadorCorreo;
import com.gps.chambee.negocios.validadores.ValidadorNombreUsuario;
import com.gps.chambee.negocios.validadores.ValidadorPool;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorTelefono;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorContrasenia;
import com.gps.chambee.ui.Sesion;
import com.gps.chambee.ui.Utils;

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegistrate;
    private LinearLayout llNombre, llSociales;
    private RelativeLayout rlAbajo;
    private ImageView ivLogo;
    private EditText etUsuario;
    private EditText etContrasenaLogin;
    private Button btnIniciarSesion;
    private TextView tvOlvideContrasena;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvOlvideContrasena = findViewById(R.id.tvOlvideContrasena);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        etContrasenaLogin = findViewById(R.id.etContrasenaLogin);
        etUsuario = findViewById(R.id.etUsuario);
        ivLogo = findViewById(R.id.ivLogo);
        tvRegistrate = findViewById(R.id.tvRegistrate);
        rlAbajo = findViewById(R.id.rlAbajo);
        llNombre = findViewById(R.id.llNombre);
        llSociales = findViewById(R.id.llSociales);

        tvOlvideContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, OlvideContrasenaActivity.class));
            }
        });

        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharedIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                Pair[] pair = new Pair[3];
                pair[0] = new Pair<View, String>(ivLogo, "imageTransition");
                pair[1] = new Pair<View, String>(llNombre, "layoutTransition");
                pair[2] = new Pair<View, String>(rlAbajo, "linearTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pair);
                startActivity(sharedIntent, options.toBundle());
                overridePendingTransition(0, 0);
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iniciarSesionFirebase();
                iniciarSesionSW();

            }
        });
    }

    private void iniciarSesionSW() {

        String credencial = etUsuario.getText().toString();
        String contrasena = etContrasenaLogin.getText().toString();
        ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia(contrasena);

        // validamos si es correo

        ValidadorCorreo validadorCorreo = new ValidadorCorreo(credencial);
        ValidadorTelefono validadorTelefono = new ValidadorTelefono(credencial);
        ValidadorNombreUsuario validadorNombreUsuario = new ValidadorNombreUsuario(credencial);

        if (validadorContrasenia.validar() == false){
            Toast.makeText(this, validadorContrasenia.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        int tipoInicio = 0;
        if (validadorCorreo.validar() == true) {
            tipoInicio = 1;
            Log.e("Validacion ","Si entró en correo");
        }else {
            Toast.makeText(LoginActivity.this, validadorCorreo.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        /*if (validadorNombreUsuario.validar() == true) {
            tipoInicio = 2;
            Log.e("Validacion ","Si entró en nombre usuario");
        }else {
            Toast.makeText(LoginActivity.this, validadorNombreUsuario.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (validadorTelefono.validar() == true) {
            tipoInicio = 3;
            Log.e("Validacion ","Si entró en telefono");
        }else {
            Toast.makeText(LoginActivity.this, validadorTelefono.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }*/


        CUIniciarSesion cuIniciarSesion = new CUIniciarSesion(LoginActivity.this, new CasoUso.EventoPeticionAceptada<String>() {
            @Override
            public void alAceptarPeticion(String usuario) {
                Intent sharedIntent = new Intent(LoginActivity.this, MainActivity.class);//check this out

                startActivity(sharedIntent);
                finish();


            }
        }, new CasoUso.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion() {
                Toast.makeText(LoginActivity.this, "No se puede iniciar sesion", Toast.LENGTH_SHORT).show();

            }
        });


        cuIniciarSesion.enviarPeticion(credencial, contrasena, tipoInicio);
    }

    private void iniciarSesionFirebase() {

        // checar conexion a Internet

        if (!Utils.tieneConexionInternet(this)) {
            Toast.makeText(this, "No tienes conexión a Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        // obtener datos y validarlos

        String correo = etUsuario.getText().toString();
        String contrasena = etContrasenaLogin.getText().toString();

        ValidadorPool validadorPool = new ValidadorPool.Builder()
                .agregarValidador(new ValidadorCorreo(correo))
                .agregarValidador(new ValidadorContrasenia(contrasena))
                .build();

        if (!validadorPool.validarTodo()) {
            Toast.makeText(this, validadorPool.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        // proceder con inicio de sesion

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Comprobando usuario...");
        progressDialog.show();

        new CFAutenticarUsuario(new CasoUsoFirebase.EventoPeticionAceptada<String>() {
            @Override
            public void alAceptarPeticion(String s) {
                progressDialog.dismiss();
                agregarUsuarioSesion();

            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Las credenciales son incorrectas", Toast.LENGTH_LONG).show();

            }
        }).enviarPeticion(correo, contrasena);
    }

    private void agregarUsuarioSesion() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.show();

        // agregar usuario al singleton de sesion
        new CFSeleccionarUsuarioFirebase(new CasoUsoFirebase.EventoPeticionAceptada<UsuarioFirebase>() {
            @Override
            public void alAceptarPeticion(UsuarioFirebase usuarioFirebase) {
                progressDialog.dismiss();

                Sesion.instance().agregarEntidad(UsuarioFirebase.getNombreClase(), usuarioFirebase);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Error al obtener usuario de firebase", Toast.LENGTH_SHORT).show();

            }
        }).enviarPeticion();

        // TODO servicio web para obtener datos del usuario
        // TODO agregar usuario al singleton de sesion
    }

    @Override
    public void onBackPressed() {
        ivLogo.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }
}
