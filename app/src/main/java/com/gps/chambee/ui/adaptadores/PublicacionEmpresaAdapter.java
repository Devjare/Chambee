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

public class PublicacionEmpresaAdapter extends RecyclerView.Adapter<PublicacionEmpresaAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreReclutador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreReclutador = itemView.findViewById(R.id.tvNombreReclutador);
        }
    }

    private Context context;
    private List<Object> lista;

    public PublicacionEmpresaAdapter(Context context, List<Object> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion_empresa, parent, false);
        return new PublicacionEmpresaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}
