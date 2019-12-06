package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Medalla;
import com.gps.chambee.entidades.vistas.MedallasPerfil;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CasoUso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedallasAdapter extends RecyclerView.Adapter<MedallasAdapter.ViewHolder>  {

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView ivMedalla;
        TextView tvNumeroMedallas;
        TextView tvTituloMedalla;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivMedalla = itemView.findViewById(R.id.ivMedalla);
            tvNumeroMedallas = itemView.findViewById(R.id.tvNumeroMedallas);
            tvTituloMedalla = itemView.findViewById(R.id.tvTituloMedalla);
        }
    }

    private Context context;
    private List<MedallasPerfil> lista;

    public MedallasAdapter(Context context, List<MedallasPerfil> lista) {
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public MedallasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medallas,parent,false);
        return new MedallasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MedallasAdapter.ViewHolder holder, int position) {
        MedallasPerfil medalla = lista.get(position);

        holder.tvNumeroMedallas.setText(String.valueOf(medalla.getCantidad()));
        holder.tvTituloMedalla.setText(medalla.getMedalla());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
