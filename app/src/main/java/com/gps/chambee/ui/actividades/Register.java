package com.gps.chambee.ui.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gps.chambee.R;

public class Register extends AppCompatActivity {
    private TextView tvIniciar;
    private Button bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvIniciar=findViewById(R.id.tvIniciar);
        bRegister=findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,MainActivity.class));
                finish();
            }
        });
        tvIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register.super.onBackPressed();
            }
        });
    }
}
