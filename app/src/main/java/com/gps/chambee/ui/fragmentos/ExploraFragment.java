package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.gps.chambee.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ExploraFragment extends Fragment {

    private TabLayout tabExplorar;
    private ViewPager vpExplora;
    private ImageView ivFiltros;
    private EditText etBusquedaExplorar;

    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle savedInstanceState){
        View view = inflate.inflate(R.layout.fragment_explorar, container, false);
        setHasOptionsMenu(true);

        etBusquedaExplorar = view.findViewById(R.id.etBusquedaExplorar);
        ivFiltros = view.findViewById(R.id.ivFiltros);
        tabExplorar = view.findViewById(R.id.tabExplorar);
        vpExplora = view.findViewById(R.id.vpExplora);

        ivFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiltroFragment ff = new FiltroFragment();
                ff.show(getFragmentManager(), "Dialogo");
            }
        });

        vpExplora.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabExplorar));
        tabExplorar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpExplora.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabExplorar.addTab(tabExplorar.newTab().setText("Empleos"));
        tabExplorar.addTab(tabExplorar.newTab().setText("Empleados"));
        tabExplorar.addTab(tabExplorar.newTab().setText("Usuarios"));
        tabExplorar.setTabMode(TabLayout.MODE_SCROLLABLE);

        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new ExplorarEmpleosFragment(),"Empleo");
        adapter.addFragment(new ExplorarEmpleadosFragment(),"Empleado");
        adapter.addFragment(new ExplorarUsuariosFragment(),"Usuarios");
        vpExplora.setAdapter(adapter);

        return view;
    }

    static class Adapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm){
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitles.get(position);
        }
    }
}
