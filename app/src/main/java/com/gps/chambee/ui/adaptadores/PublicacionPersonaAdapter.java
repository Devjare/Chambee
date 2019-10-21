package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PublicacionPersonaAdapter extends RecyclerView.Adapter<PublicacionPersonaAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private Context context;
    private List<Object> lista;

    public PublicacionPersonaAdapter(Context context,List<Object> lista){
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion_persona,parent,false);
        return new PublicacionPersonaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionPersonaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
