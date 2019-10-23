package com.gps.chambee.ui.adaptadores;

import android.content.Context;

import com.gps.chambee.ui.fragmentos.InicioFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExploraAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public ExploraAdapter(Context context, FragmentManager fm,int totalTabs){
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                InicioFragment in = new InicioFragment();
                return in;
            case 1:
                InicioFragment an = new InicioFragment();
                return an;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
