package com.gps.chambee.ui.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gps.chambee.R;
import com.gps.chambee.ui.fragmentos.ExploraFragment;
import com.gps.chambee.ui.fragmentos.InicioFragment;
import com.gps.chambee.ui.fragmentos.NotificacionesFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView ivNotificaciones;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.iHome);

        Fragment sFragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId=menuItem.getItemId();
                Fragment sFragment=null;
                switch (itemId){
                    case R.id.iHome:
                        //addFragment(new InicioFragment());
                        sFragment =new InicioFragment();
                        break;
                    case R.id.iExplorar:
                        sFragment =new ExploraFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).commit();
                return true;
            }
        });

        ivNotificaciones = findViewById(R.id.ivNotificaciones);
        ivNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment sFragment = new NotificacionesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
            }
        });
    }
}
