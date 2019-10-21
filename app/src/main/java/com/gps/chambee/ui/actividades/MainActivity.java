package com.gps.chambee.ui.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gps.chambee.R;
import com.gps.chambee.ui.fragmentos.InicioFragment;

public class MainActivity extends AppCompatActivity {
    private boolean viewIsAtHome;
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation=findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.iHome);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId=menuItem.getItemId();
                Fragment sFragment=null;
                switch (itemId){
                    case R.id.iHome:
                        //addFragment(new InicioFragment());
                        sFragment =new InicioFragment();
                        viewIsAtHome=true;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragments,sFragment).commit();
                return true;
            }
        });
    }
    private void addFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragments,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}
