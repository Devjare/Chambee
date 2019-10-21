package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComentarioTrabajoAdapter extends RecyclerView.Adapter<ComentarioTrabajoAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    private Context context;
    private List<Object> lista;

    public ComentarioTrabajoAdapter(Context context, List<Object> lista){
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public ComentarioTrabajoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comentario,parent,false);
        return new ComentarioTrabajoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioTrabajoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
