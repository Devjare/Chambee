package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.vistas.VistaExplorarUsuario;
import com.gps.chambee.ui.actividades.ChatActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ExplorarUsuariosAdapter extends RecyclerView.Adapter<ExplorarUsuariosAdapter.ViewHolder> {

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        CircleImageView civFotoPerfilExplorarUsuario;
        TextView tvReceptor;
        TextView tvNombreExplorarUsuario;
        TextView tvCiudadUsuario;
        ImageView ivMensajeUsuario;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            civFotoPerfilExplorarUsuario = itemView.findViewById(R.id.civFotoPerfilExplorarUsuario);
            tvReceptor = itemView.findViewById(R.id.tvReceptor);
            tvNombreExplorarUsuario = itemView.findViewById(R.id.tvNombreExplorarUsuario);
            tvCiudadUsuario = itemView.findViewById(R.id.tvCiudadUsuario);
            ivMensajeUsuario = itemView.findViewById(R.id.ivMensajeUsuario);

            ivMensajeUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
                    intent.putExtra("idReceptor", tvReceptor.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    private Context context;
    private List<VistaExplorarUsuario> lista;

    public ExplorarUsuariosAdapter(Context context, List<VistaExplorarUsuario> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ExplorarUsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_explorar_usuario,parent,false);
        return new ExplorarUsuariosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExplorarUsuariosAdapter.ViewHolder holder, int position) {
        VistaExplorarUsuario vistaExplorarUsuario = lista.get(position);

        holder.tvReceptor.setText(vistaExplorarUsuario.getIdUsuario());
        holder.tvNombreExplorarUsuario.setText(vistaExplorarUsuario.getNombreUsuario());
        holder.tvCiudadUsuario.setText(vistaExplorarUsuario.getCiudad());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
