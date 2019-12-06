package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Categoria;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombreCategoria;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvNombreCategoria = itemView.findViewById(R.id.tvNombreCategoria);
        }
    }
    private Context context;
    private List<Categoria> lista;

    public CategoriasAdapter(Context context, List<Categoria> lista){
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public CategoriasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_categoria,parent,false);
        return new CategoriasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdapter.ViewHolder holder, int position) {
        Categoria categoria = lista.get(position);
        holder.tvNombreCategoria.setText(categoria.getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}










