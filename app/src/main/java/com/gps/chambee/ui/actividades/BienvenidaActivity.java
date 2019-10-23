package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gps.chambee.R;
import com.gps.chambee.ui.adaptadores.BienvenidaAdapter;

public class BienvenidaActivity extends AppCompatActivity {

    private ViewPager vpBienvenida;
    private int[] layouts = {R.layout.bienvenida_1, R.layout.bienvenida_2};
    private LinearLayout llPuntos;
    private BienvenidaAdapter adapter;
    private ImageView[] dots;
    private Button btnEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BienvenidaActivity.this, SplashScreenActivity.class));
                finish();
            }
        });

        vpBienvenida = findViewById(R.id.vpBienvenida);
        adapter = new BienvenidaAdapter(layouts,this);
        vpBienvenida.setAdapter(adapter);
        vpBienvenida.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1){
                    btnEmpezar.setVisibility(View.VISIBLE);
                }else{
                    btnEmpezar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        llPuntos = findViewById(R.id.llPuntos);
        createDots(0);
    }
    private void createDots(int currentPosition){
        if(llPuntos!=null)
            llPuntos.removeAllViews();

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
            llPuntos.addView(dots[i],params);
        }
    }
}
