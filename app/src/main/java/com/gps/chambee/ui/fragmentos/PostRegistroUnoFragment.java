package com.gps.chambee.ui.fragmentos;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gps.chambee.R;
import com.gps.chambee.ui.actividades.PostRegistroActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class PostRegistroUnoFragment extends Fragment {

    public static final int PETICION_CAMARA = 1;
    public static final int PETICION_GALERIA = 2;


    private ImageView civFotoPost;
    private EditText etAcercaDeMi;
    private Bitmap imagenUsuario;


    public View onCreateView(LayoutInflater inflater, ViewGroup context, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_parte_1, context, false);

        civFotoPost = view.findViewById(R.id.civFotoPost);
        etAcercaDeMi = view.findViewById(R.id.etAcercaDeMi);

        String acerca = etAcercaDeMi.getText().toString();
        civFotoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesImagen();
            }
        });

        civFotoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarOpcionesImagen();
                Toast.makeText(view.getContext(), "Subir Foto", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void mostrarOpcionesImagen() {
        final CharSequence[] opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};

        final AlertDialog.Builder builer = new AlertDialog.Builder(getContext())
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

    private void abrirGaleria() {
        String action = Intent.ACTION_PICK;
        Intent intent = new Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione imagen"), PETICION_GALERIA);

    }
    private boolean cameraPermission(){
        int result = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    private void abrirCamara() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


        if (ContextCompat.checkSelfPermission(getActivity(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), permissions[2]) == PackageManager.PERMISSION_GRANTED) {

            startActivityForResult(camera, PETICION_CAMARA);


        }else{
            requestPermissions(permissions,PETICION_CAMARA);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        abrirCamara();
        Log.i("Si entro","Entro en el request permission");
        Toast.makeText(getContext(), "Despues de pedir permisos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if  (resultCode != RESULT_OK){
            Toast.makeText(getContext(), "No exitante", Toast.LENGTH_SHORT).show();
            return;
        }

        switch(requestCode) {
            case PETICION_GALERIA:{
                try {
                    Uri uriImage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriImage);
                    Glide.with(getActivity())
                            .load(bitmap)
                            .into(civFotoPost);
                    Toast.makeText(getContext(), "Entro en la peticion de la galeria", Toast.LENGTH_SHORT).show();
                    imagenUsuario = bitmap;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
            case PETICION_CAMARA:{
                Bundle bundle = data.getExtras();
                if (bundle == null)
                    return;

                Bitmap bitmap = (Bitmap) bundle.get("data");
                if (bitmap == null)
                    return;
                Glide.with(getActivity())
                        .load(bitmap)
                        .into(civFotoPost);
                Toast.makeText(getContext(),"Exitante", Toast.LENGTH_SHORT).show();
                imagenUsuario = bitmap;

            }
        }
    }

    public Bitmap getImagenUsuario(){
        return imagenUsuario;
    }
    public String getAcercaDeMi(){
        return etAcercaDeMi.getText().toString();
    }
}
