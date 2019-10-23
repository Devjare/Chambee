package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gps.chambee.R;
import com.gps.chambee.ui.PreferenceManager;
import com.gps.chambee.ui.adaptadores.PostRegistroAdapter;

public class PostRegistroActivity extends AppCompatActivity {

    private ViewPager vpPostRegistro;
    private int[] layouts = {R.layout.post_parte_1, R.layout.post_parte_2, R.layout.post_parte_3};
    private PostRegistroAdapter prAdapter;
    private LinearLayout rlPuntos;
    private ImageView[] dots;
    private Button btnSiguientePuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_registro2);

        vpPostRegistro = findViewById(R.id.vpPostRegistro);
        prAdapter = new PostRegistroAdapter(layouts,this);
        vpPostRegistro.setAdapter(prAdapter);

        rlPuntos = findViewById(R.id.rlPuntos);
        btnSiguientePuntos = findViewById(R.id.btnSiguientePost);
        btnSiguientePuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextSlide = vpPostRegistro.getCurrentItem()+1;
                if(nextSlide<layouts.length){
                    vpPostRegistro.setCurrentItem(nextSlide);
                }else{
                    startActivity(new Intent(PostRegistroActivity.this,MainActivity.class));
                    finish();
                }
            }
        });

        createDots(0);
        vpPostRegistro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1){
                    btnSiguientePuntos.setText("Terminar");
                }else{
                    btnSiguientePuntos.setText("Avanzar");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int currentPosition){
        if(rlPuntos!=null)
            rlPuntos.removeAllViews();

        dots = new ImageView[layouts.length];
        for(int i = 0; i < layouts.length; i++){
            dots[i]=new ImageView(this);
            if(i == currentPosition){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active));
            } else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            rlPuntos.addView(dots[i],params);
        }
    }
    private void nextSlide(){
        int nextSlide =  vpPostRegistro.getCurrentItem()+1;
        if(nextSlide < layouts.length){
            vpPostRegistro.setCurrentItem(nextSlide);
        } else {

        }
    }
}
