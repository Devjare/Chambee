package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gps.chambee.R;
import com.gps.chambee.entidades.MensajeFirebase;

import java.util.List;

public class MensajesChatAdapter extends RecyclerView.Adapter<MensajesChatAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMensaje;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMensaje = itemView.findViewById(R.id.tvMensaje);
        }
    }

    public static final int MENSAJE_IZQ = 0;
    public static final int MENSAJE_DER = 1;

    private Context context;
    private List<MensajeFirebase> mensajes;

    private FirebaseUser firebaseUser;

    public MensajesChatAdapter(Context context, List<MensajeFirebase> mensajes) {
        this.context = context;
        this.mensajes = mensajes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MENSAJE_IZQ) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_mensaje_izq, parent, false);
            return new MensajesChatAdapter.ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_mensaje_der, parent, false);
            return new MensajesChatAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MensajeFirebase mensaje = mensajes.get(position);

        holder.tvMensaje.setText(mensaje.getMensaje());
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mensajes.get(position).getEmisor().equals(firebaseUser.getUid()))
            return MENSAJE_DER;
        else
            return MENSAJE_IZQ;
    }
}
