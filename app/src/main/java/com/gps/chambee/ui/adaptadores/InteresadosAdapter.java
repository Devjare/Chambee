package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Perfil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class InteresadosAdapter extends RecyclerView.Adapter<InteresadosAdapter.ViewHolder> {

    private Context context;
    private List<Perfil> lista;

    public InteresadosAdapter(Context context, List<Perfil> lista){
        this.context = context;
        this.lista = lista;
    }
    @NonNull
    @Override
    public InteresadosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_interesados,parent,false);
        return new InteresadosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Perfil perfil = lista.get(position);

        if (perfil.getUrlPerfil() == "default"){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_person);
            holder.civInteresado.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView civInteresado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            civInteresado = itemView.findViewById(R.id.civInteresado);
        }
    }
}
