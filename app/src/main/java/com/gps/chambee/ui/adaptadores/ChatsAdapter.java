package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gps.chambee.R;
import com.gps.chambee.entidades.vistas.VistaChat;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.actividades.ChatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView civFotoMensajes;
        TextView tvReceptor;
        TextView tvUsuarioChat;
        TextView tvMensaje;
        TextView tvNumeroMensaje;
        TextView tvFechaChat;

        public ViewHolder (final View itemView){
            super(itemView);

            civFotoMensajes = itemView.findViewById(R.id.civFotoMensajes);
            tvReceptor = itemView.findViewById(R.id.tvReceptor);
            tvUsuarioChat = itemView.findViewById(R.id.tvUsuarioChat);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvNumeroMensaje = itemView.findViewById(R.id.tvNumeroMensaje);
            tvFechaChat = itemView.findViewById(R.id.tvFechaChat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("idReceptor", tvReceptor.getText().toString());
                    context.startActivity(intent);
                }
            });
        }
    }

    private Context context;
    private List<VistaChat> lista;
    private Map<String, Bitmap> cacheBitmaps = new HashMap<>();

    public ChatsAdapter(Context context, List<VistaChat> lista){
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mensajes,parent,false);
        return new ChatsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final VistaChat chat = lista.get(position);

        // datos del chat
        holder.tvReceptor.setText(chat.getIdRecepetor());
        holder.tvFechaChat.setText(chat.getFechaUltimoMensaje());
        holder.tvMensaje.setText(chat.getUltimoMensaje());
        holder.tvNumeroMensaje.setText(String.valueOf(chat.getCantidadMensajesSinLeer()));
        holder.tvUsuarioChat.setText(chat.getNombreUsuario());

        // si la imagen ya esta en el cache no la consultamos del servidor
        if (cacheBitmaps.containsKey(chat.getNombreUsuario())) {
            Glide.with(context)
                    .load(cacheBitmaps.get(chat.getNombreUsuario()))
                    .into(holder.civFotoMensajes);
        }
        else {
            // sino, la consultamos del servidor
            new CUObtenerImagen(context, new CasoUso.EventoPeticionAceptada<Bitmap>() {
                @Override
                public void alAceptarPeticion(Bitmap bitmap) {
                    cacheBitmaps.put(chat.getNombreUsuario(), bitmap);
                    Glide.with(context)
                            .load(cacheBitmaps.get(chat.getNombreUsuario()))
                            .into(holder.civFotoMensajes);
                }
            }, new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    Toast.makeText(context, "Error al obtener imagen del chat", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
