package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> {

    private Context context;
    private List<Object> lista;

    public UsuariosAdapter(Context context, List<Object> lista){
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public UsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_explorar_usuario,parent,false);
        return new UsuariosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{

        CircleImageView civFotoPerfilExplorarUsuario;
        TextView tvNombreExplorarUsuario;
        ImageView ivMensajeUsuario;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            civFotoPerfilExplorarUsuario = itemView.findViewById(R.id.civFotoPerfilExplorarUsuario);
            tvNombreExplorarUsuario = itemView.findViewById(R.id.tvNombreExplorarUsuario);
            ivMensajeUsuario = itemView.findViewById(R.id.ivMensajeUsuario);
        }
    }
}
