package com.gps.chambee.ui.fragmentos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gps.chambee.R;

import java.util.Calendar;

import androidx.fragment.app.Fragment;

public class PostRegistroTresFragment extends Fragment {

    private EditText etLocalidad;
    private EditText etColonia;
    private Button btnFecha;
    private int dia, mes, ano;
    private String fecha;

    public View onCreateView(LayoutInflater inflater, ViewGroup context, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_post_parte_3, context, false);

        btnFecha = view.findViewById(R.id.btnFecha);
        etLocalidad = view.findViewById(R.id.etLocalidad);
        etColonia = view.findViewById(R.id.etColonia);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        fecha = i2+"-"+(i1+1)+"-"+i;
                        btnFecha.setText(fecha);
                    }
                }, ano, mes, dia);

                datePickerDialog.show();
            }
        });

        return view;
    }
    public String getLocalidad(){
        return etLocalidad.getText().toString();
    }
    public String getColonia(){
        return etColonia.getText().toString();
    }
    public String getFecha(){
        return fecha;
    }
}
