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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gps.chambee.R;

import java.io.IOException;

public class PublicarTrabajoActivity extends AppCompatActivity {

    private static final int PETICION_CAMARA = 1;
    private static final int PETICION_GALERIA = 2;

    private ImageView ivRegresarPublicarTrabajo;
    private ImageView ivSubirImagen;
    private EditText etNombreEmpleo;
    private EditText etAreasInteres;
    private EditText etDescripcionEmpleo;
    private EditText etTiempoTrabajo;
    private EditText etPaga;
    private Button btnPublicar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_trabajo);

        ivSubirImagen = findViewById(R.id.ivSubirImagen);
        etAreasInteres = findViewById(R.id.etAreasInteres);
        etNombreEmpleo = findViewById(R.id.etNombreEmpleo);
        etDescripcionEmpleo = findViewById(R.id.etDescripcionEmpleo);
        etTiempoTrabajo = findViewById(R.id.etTiempoTrabajo);
        etPaga = findViewById(R.id.etPaga);
        btnPublicar = findViewById(R.id.btnPublicar);



        ivRegresarPublicarTrabajo = findViewById(R.id.ivRegresarPublicarTrabajo);
        ivRegresarPublicarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicarTrabajoActivity.super.onBackPressed();
            }
        });

        ivSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarOpcionesImagen();
                Toast.makeText(v.getContext(), "Subir Foto", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarOpcionesImagen() {
        final CharSequence[] opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};

        final AlertDialog.Builder builer = new AlertDialog.Builder(this)
                .setTitle("Elige una opcion")
                .setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                if (cameraPermission()==true){
                                    abrirCamara();
                                }

                                break;
                            }
                            case 1: {
                                abrirGaleria();
                                break;
                            }
                            case 2: {
                                dialog.dismiss();
                                break;
                            }
                        }
                    }
                });
        builer.show();
    }

    private boolean cameraPermission(){
        int result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }
    private void abrirCamara(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


        if (ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permissions[2]) == PackageManager.PERMISSION_GRANTED) {

            startActivityForResult(camera, PETICION_CAMARA);


        }else{
            requestPermissions(permissions,PETICION_CAMARA);
        }

    }
    private void abrirGaleria(){
        String accion = Intent.ACTION_PICK;
        Intent intent = new Intent(accion, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione imagen"), PETICION_GALERIA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        abrirCamara();
        Log.i("Si entro","Entro en el request permission");
        Toast.makeText(this, "Despues de pedir permisos", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK ){
            Toast.makeText(this, "Exitoso", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode){
            case PETICION_GALERIA: {
                try {
                    Uri uriImagen = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uriImagen);
                    Glide.with(this)
                            .load(bitmap)
                            .into(ivSubirImagen);
                    Toast.makeText(this, "Entro en galeria", Toast.LENGTH_SHORT).show();


                }catch (IOException ex){
                    ex.printStackTrace();

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

                Glide.with(this)
                        .load(bitmap)
                        .into(ivSubirImagen);
                Toast.makeText(this, "Exito", Toast.LENGTH_SHORT).show();

            }

        }

    }



}
