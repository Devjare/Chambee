package com.gps.chambee.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.gps.chambee.R;

public class PreferenceManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context){
        this.context = context;
        getSharedPreferencer();
    }

    private void getSharedPreferencer(){
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
    }

    public void writePreference(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.app_name),"INIT OK");
        editor.commit();
    }
    public boolean checkPreference(){
        boolean status = false;
        if(sharedPreferences.getString(context.getString(R.string.app_name),null).equals("null")){
            status = false;
        }else{
            status = true;
        }
        return status;
    }

    public void clearPreference(){
        sharedPreferences.edit().clear().commit();
    }
}
