package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gps.chambee.R;
import com.gps.chambee.negocios.validadores.ValidadorPool;
import com.gps.chambee.negocios.validadores.propiedades.ValidadorStringNoVacio;
import com.gps.chambee.ui.adaptadores.PostRegistroAdapter;
import com.gps.chambee.ui.fragmentos.PostRegistroDosFragment;
import com.gps.chambee.ui.fragmentos.PostRegistroTresFragment;
import com.gps.chambee.ui.fragmentos.PostRegistroUnoFragment;

import java.util.ArrayList;
import java.util.List;

public class PostRegistroActivity extends AppCompatActivity {

    private ViewPager vpPostRegistro;
    private PostRegistroAdapter prAdapter;
    private LinearLayout rlPuntos;
    private ImageView[] dots;
    private Button btnSiguientePuntos;

    // Datos de post-registro
    private Bitmap imagenUsuario;
    private String acercaDeMi;
    private String profesion;
    private String localidad;
    private String colonia;
    private String fecha;

    List<Fragment> fragmentos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_registro2);

        fragmentos = new ArrayList<>();
        fragmentos.add(new PostRegistroUnoFragment());
        fragmentos.add(new PostRegistroDosFragment());
        fragmentos.add(new PostRegistroTresFragment());

        vpPostRegistro = findViewById(R.id.vpPostRegistro);

        prAdapter = new PostRegistroAdapter(getSupportFragmentManager(), fragmentos);

        vpPostRegistro.setAdapter(prAdapter);

        rlPuntos = findViewById(R.id.rlPuntos);
        btnSiguientePuntos = findViewById(R.id.btnSiguientePost);
        btnSiguientePuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manejarSiguienteFragment();
            }
        });

        createDots(0);
        vpPostRegistro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override public void onPageScrollStateChanged(int state) { }

            @Override
            public void onPageSelected(int position) {
                createDots(position);

                if (position == 2) {
                    btnSiguientePuntos.setText("Terminar");
                }
                else {
                    btnSiguientePuntos.setText("Avanzar");
                }
            }
        });
    }

    private void createDots(int currentPosition) {
        if (rlPuntos != null)
            rlPuntos.removeAllViews();

        dots = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            dots[i] = new ImageView(this);
            if (i == currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            rlPuntos.addView(dots[i], params);
        }
    }

    private void manejarSiguienteFragment() {

        Fragment fragment = fragmentos.get(vpPostRegistro.getCurrentItem());

        switch (vpPostRegistro.getCurrentItem()) {

            case 0: {
                PostRegistroUnoFragment post1 = (PostRegistroUnoFragment) fragment;
                imagenUsuario = post1.getImagenUsuario();
                acercaDeMi = post1.getAcercaDeMi();

                if (!validarDatosFragmento1())
                    return;

                break;
            }

            case 1: {
                PostRegistroDosFragment post2 = (PostRegistroDosFragment) fragment;
                profesion = post2.getProfesion();

                if (!validarDatosFragmento2())
                    return;

                break;
            }

            case 2: {
                PostRegistroTresFragment post3 = (PostRegistroTresFragment) fragment;
                localidad = post3.getLocalidad();
                colonia = post3.getColonia();
                fecha = post3.getFecha();

                if (!validarDatosFragmento3())
                    return;

                break;
            }
        }

        int nextSlide = vpPostRegistro.getCurrentItem() + 1;

        if (nextSlide < 3) {
            vpPostRegistro.setCurrentItem(nextSlide);
        }
        else {
            actualizarPerfilUsuarioIniciarSesion();
        }
    }

    private boolean validarDatosFragmento1() {
        ValidadorStringNoVacio validador = new ValidadorStringNoVacio(acercaDeMi);

        if (!validador.validar()) {
            Toast.makeText(this, validador.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validarDatosFragmento2() {
        ValidadorStringNoVacio validador = new ValidadorStringNoVacio(profesion);

        if (!validador.validar()) {
            Toast.makeText(this, validador.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validarDatosFragmento3() {
        ValidadorPool validadorPool = new ValidadorPool.Builder()
                .agregarValidador(new ValidadorStringNoVacio(localidad))
                .agregarValidador(new ValidadorStringNoVacio(colonia))
                .agregarValidador(new ValidadorStringNoVacio(fecha))
                .build();

        if (!validadorPool.validarTodo()) {
            Toast.makeText(this, validadorPool.ultimoError().mensajeError(), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void actualizarPerfilUsuarioIniciarSesion() {
        // TODO Servicio web para la actualizacion de los datos del perfil del usuario recien registrado

        startActivity(new Intent(PostRegistroActivity.this, MainActivity.class));
        finish();
    }
}
