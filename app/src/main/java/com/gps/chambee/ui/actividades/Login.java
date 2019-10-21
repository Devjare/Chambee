package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gps.chambee.R;

public class Login extends AppCompatActivity {
    private TextView tvRegistrate;
    private LinearLayout llNombre,llSociales;
    private RelativeLayout rlAbajo;
    private ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivLogo=findViewById(R.id.ivLogo);
        tvRegistrate=findViewById(R.id.tvRegistrate);
        rlAbajo=findViewById(R.id.rlAbajo);
        llNombre=findViewById(R.id.llNombre);
        llSociales=findViewById(R.id.llSociales);
        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharedIntent=new Intent(Login.this,Register.class);
                Pair[] pair=new Pair[3];
                pair[0]=new Pair<View,String>(ivLogo,"imageTransition");
                pair[1]=new Pair<View,String>(llNombre,"layoutTransition");
                pair[2]=new Pair<View,String>(rlAbajo,"linearTransition");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login.this,pair);
                startActivity(sharedIntent,options.toBundle());
                overridePendingTransition(0, 0);
            }
        });
    }
}
