package com.gps.chambee.ui.actividades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gps.chambee.R;
import com.gps.chambee.negocios.validadores.ValidadorPool;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorNumeroString;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorStringNoVacio;

import java.io.IOException;

public class PublicarTrabajoActivity extends AppCompatActivity {

    public static final int PETICION_CAMARA = 1;
    public static final int PETICION_GALERIA = 2;

    private ImageView ivRegresarPublicarTrabajo;
    private ImageView ivSubirImagen;
    private EditText etNombreEmpleo;
    private EditText etDescripcionEmpleo;
    private Spinner sCategorias;
    private TextView tvOpcionesAvanzadas;
    private LinearLayout llOpcionesAvanzadas;
    private EditText etPagaMin;
    private EditText etPagaMax;
    private EditText etTiempoTrabajo;
    private Button bPublicar;

    private Bitmap imagenUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_trabajo);

        ivRegresarPublicarTrabajo = findViewById(R.id.ivRegresarPublicarTrabajo);
        ivSubirImagen = findViewById(R.id.ivSubirImagen);
        etNombreEmpleo = findViewById(R.id.etNombreEmpleo);
        etDescripcionEmpleo = findViewById(R.id.etDescripcionEmpleo);
        sCategorias = findViewById(R.id.sCategorias);
        tvOpcionesAvanzadas = findViewById(R.id.tvOpcionesAvanzadas);
        llOpcionesAvanzadas = findViewById(R.id.llOpcionesAvanzadas);
        etPagaMin = findViewById(R.id.etPagaMin);
        etPagaMax = findViewById(R.id.etPagaMax);
        etTiempoTrabajo = findViewById(R.id.etTiempoTrabajo);
        bPublicar = findViewById(R.id.bPublicar);

        ivRegresarPublicarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvOpcionesAvanzadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarEstadoOpcionesAvanzadas();
            }
        });

        bPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicar();
            }
        });
    }

    private void abrirCamara() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
        };

        if (ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permissions[2]) == PackageManager.PERMISSION_GRANTED)
        {
            startActivityForResult(camera, PETICION_CAMARA);
        }
        else {
            requestPermissions(permissions, PETICION_CAMARA);
        }
    }

    private void abrirGaleria() {
        String action = Intent.ACTION_PICK;
        Intent intent = new Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), PETICION_GALERIA);
    }

    private void cambiarEstadoOpcionesAvanzadas() {
        llOpcionesAvanzadas.setVisibility(
                llOpcionesAvanzadas.getVisibility() == View.VISIBLE ?
                        View.GONE :
                        View.VISIBLE
        );
    }

    private boolean concederPermisosCamara() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private boolean estanOpcionesAvanzadasActivadas() {
        return llOpcionesAvanzadas.getVisibility() == View.VISIBLE;
    }

    private void mostrarOpcionesImagen() {
        final CharSequence[] opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0: {
                        if (concederPermisosCamara()) {
                            abrirCamara();
                        }
                        break;
                    }

                    case 1: {
                        abrirGaleria();
                        break;
                    }

                    case 2: {
                        dialogInterface.dismiss();
                        break;
                    }
                }
            }
        };

        AlertDialog.Builder builer = new AlertDialog.Builder(this)
                .setTitle("Elige una opcion")
                .setItems(opciones, onClickListener);

        builer.show();
    }

    private void publicar() {

        // Obtener datos de la UI

        String nombreEmpleo = etNombreEmpleo.getText().toString();
        String descEmpleo = etDescripcionEmpleo.getText().toString();
        String pagaMinima = etPagaMin.getText().toString();
        String pagaMaxima = etPagaMax.getText().toString();
        String tiempoTrabajo = etTiempoTrabajo.getText().toString();

        // Validar datos de la publicacion

        ValidadorPool validadorPool = new ValidadorPool.Builder()
                .agregarValidador(new ValidadorStringNoVacio(nombreEmpleo))
                .agregarValidador(new ValidadorStringNoVacio(descEmpleo))
                .build();

        if (estanOpcionesAvanzadasActivadas()) {
            validadorPool.agregarValidador(new ValidadorNumeroString(pagaMinima));
            validadorPool.agregarValidador(new ValidadorNumeroString(pagaMaxima));
            validadorPool.agregarValidador(new ValidadorStringNoVacio(tiempoTrabajo));
        }

        if (!validadorPool.validarTodo()) {
            Toast.makeText(this, validadorPool.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return;
        }

        // publicar

        // TODO Servicio web para hacer la publicacion

        finish();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "Error al realizar operacion", Toast.LENGTH_SHORT).show();
            return;
        }

        switch(requestCode) {
            case PETICION_GALERIA: {
                try {
                    Uri uriImage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);

                    imagenUsuario = bitmap;
                    ivSubirImagen.setImageBitmap(imagenUsuario);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            case PETICION_CAMARA: {
                Bundle bundle = data.getExtras();

                if (bundle == null)
                    return;

                Bitmap bitmap = (Bitmap) bundle.get("data");

                if (bitmap == null)
                    return;

                imagenUsuario = bitmap;
                ivSubirImagen.setImageBitmap(imagenUsuario);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        abrirCamara();
    }
}
