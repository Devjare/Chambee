package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gps.chambee.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvEtiqueta;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvEtiqueta = itemView.findViewById(R.id.tvEtiqueta);
        }
    }
    private Context context;
    private List<String> lista;

    public ServiciosAdapter(Context context, List<String> lista){
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public ServiciosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_etiquetas,parent,false);
        return new ServiciosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiciosAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}