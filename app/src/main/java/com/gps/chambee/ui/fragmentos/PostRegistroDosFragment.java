package com.gps.chambee.ui.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gps.chambee.R;

import androidx.fragment.app.Fragment;

public class PostRegistroDosFragment extends Fragment {

    private EditText etProfesion;

    public View onCreateView(LayoutInflater inflater, ViewGroup context, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_post_parte_2, context, false);
        etProfesion = view.findViewById(R.id.etProfesion);
        return view;
    }

    public String getProfesion(){
        return etProfesion.getText().toString();
    }

}
