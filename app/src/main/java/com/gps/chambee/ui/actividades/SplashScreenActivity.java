package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gps.chambee.R;

public class SplashScreenActivity extends AppCompatActivity {

    private RelativeLayout rlLogos;
    private LinearLayout llNombre;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        rlLogos = findViewById(R.id.rlLogos);
        ivLogo = findViewById(R.id.ivLogo);
        llNombre=findViewById(R.id.llNombre);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent sharedIntent =new Intent(SplashScreenActivity.this, LoginActivity.class);
                Pair[] pairs=new Pair[2];
                pairs[0] = new Pair<View,String>(ivLogo,"imageTransition");
                pairs[1] = new Pair<View,String>(llNombre,"layoutTransition");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this,pairs);
                startActivity(sharedIntent,options.toBundle());
                cerrarDespuesDeUnSegundo();
            }
        },3000);
    }

    private void cerrarDespuesDeUnSegundo() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivLogo.setVisibility(View.INVISIBLE);
                finish();
            }
        }, 1000);
    }
}
