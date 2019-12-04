package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Usuario;
import com.gps.chambee.entidades.UsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CFRegistrarUsuario;
import com.gps.chambee.negocios.casos.firebase.CFSeleccionarUsuarioFirebase;
import com.gps.chambee.negocios.casos.firebase.CasoUsoFirebase;
import com.gps.chambee.negocios.validadores.ValidadorPool;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorContrasenia;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorCorreoElectronico;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorNombre;
import com.gps.chambee.servicios.firebase.ServicioFirebase;
import com.gps.chambee.servicios.firebase.peticiones.SFRegistrarUsuario;
import com.gps.chambee.ui.Sesion;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvIniciar;
    private Button btnRegister;
    private EditText etNombre;
    private EditText etApellidos;
    private EditText etCorreo;
    private EditText etContrasena;
    private EditText etConfirmarContrasena;
    private EditText etNombreUsuario;

    private ProgressDialog progressDialog;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvIniciar = findViewById(R.id.tvIniciar);
        btnRegister = findViewById(R.id.bRegister);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatosRegistro();
            }
        });
    }

    private void validarDatosRegistro() {
        ValidadorPool validadorPool;

        // validar datos del usuario

        validadorPool = new ValidadorPool.Builder()
                .agregarValidador(new ValidadorNombre(etNombreUsuario.getText().toString()))
                .agregarValidador(new ValidadorNombre(etNombre.getText().toString()))
                .agregarValidador(new ValidadorNombre(etApellidos.getText().toString()))
                .agregarValidador(new ValidadorCorreoElectronico(etCorreo.getText().toString()))
                .build();

        if (!validadorPool.validarTodo()) {
            Toast.makeText(this, validadorPool.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        // validar contrasenas

        String contrasena = etContrasena.getText().toString();
        String contrasenaConfimada = etConfirmarContrasena.getText().toString();

        validadorPool = new ValidadorPool.Builder()
                .agregarValidador(new ValidadorContrasenia(contrasena))
                .build();

        if (!validadorPool.validarTodo()) {
            Toast.makeText(this, validadorPool.ultimoError().mensajeError(), Toast.LENGTH_LONG).show();
            return;
        }

        // checar que las contrasenas sean iguales

        if (!contrasena.equals(contrasenaConfimada)) {
            Toast.makeText(this, "Las contrasenas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // validarDatosRegistro al usuario

        usuario = new Usuario.UsuarioBuilder()
                .setId(etNombreUsuario.getText().toString())
                .setNombre(etNombre.getText().toString())
                .setApellidos(etApellidos.getText().toString())
                .setCorreoElectronico(etCorreo.getText().toString())
                .setContrasenia(etConfirmarContrasena.getText().toString())
                .setTelefono("8311146563")
                .build();
      
        ValidadorUsuario validadorUsuario = new ValidadorUsuario(usuario);

        if (!validadorUsuario.validar()) {
            progressDialog.dismiss();
            Toast.makeText(this, validadorUsuario.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.dismiss();
        registrarUsuarioFB();
    }

    private void registrarUsuarioSW() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        new CFRegistrarUsuario(new CasoUsoFirebase.EventoPeticionAceptada<String>() {
            @Override
            public void alAceptarPeticion(String s) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {

                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "No se pudo hacer el registro sw", Toast.LENGTH_SHORT).show();

            }
        }).enviarPeticion(usuario);
    }

    private void registrarUsuarioFB() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando usuario...");
        progressDialog.show();

        new SFRegistrarUsuario(new ServicioFirebase.EventoTareaCompletada<String>() {

            @Override
            public void alCompletarTarea(String s) {
                progressDialog.dismiss();
                agregarUsuarioSesion();
            }

        }, new ServicioFirebase.EventoTareaCancelada() {

            @Override
            public void alCancelarTarea(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "No se pudo hacer el registro fb", Toast.LENGTH_LONG).show();
            }

        }).ejecutarTarea(usuario);
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

                Intent intent = new Intent(RegisterActivity.this, PostRegistroActivity.class);
                startActivity(intent);
                finish();

            }
        }, new CasoUsoFirebase.EventoPeticionRechazada() {
            @Override
            public void alRechazarOperacion(DatabaseError databaseError) {
                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, "Error al obtener usuario de firebase", Toast.LENGTH_SHORT).show();
            }
        }).enviarPeticion();

        // TODO servicio web para obtener datos del usuario (normal, no firebase)
        // TODO agregar usuario (normal, no firebase) al singleton de sesion
    }
}
