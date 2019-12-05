package com.gps.chambee.ui.fragmentos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gps.chambee.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

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

        civFotoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarOpcionesImagen();
            }
        });

        return view;
    }

    private void mostrarOpcionesImagen() {
        final CharSequence[] opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0: {
                        if (cameraPermission()) {
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

        AlertDialog.Builder builer = new AlertDialog.Builder(getContext())
                .setTitle("Elige una opcion")
                .setItems(opciones, onClickListener);

        builer.show();
    }

    private void abrirCamara() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
        };

        if (ContextCompat.checkSelfPermission(getActivity(), permissions[0]) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(getActivity(), permissions[1]) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(getActivity(), permissions[2]) == PackageManager.PERMISSION_GRANTED)
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

    private boolean cameraPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(getContext(), "Error al realizar operacion", Toast.LENGTH_SHORT).show();
            return;
        }

        switch(requestCode) {
            case PETICION_GALERIA: {
                try {
                    Uri uriImage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriImage);

                    Glide.with(getActivity())
                            .load(bitmap)
                            .into(civFotoPost);

                    imagenUsuario = bitmap;
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

                Glide.with(getActivity())
                        .load(bitmap)
                        .into(civFotoPost);

                imagenUsuario = bitmap;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        abrirCamara();
    }

    public Bitmap getImagenUsuario(){
        return imagenUsuario;
    }
    public String getAcercaDeMi(){
        return etAcercaDeMi.getText().toString();
    }
}
