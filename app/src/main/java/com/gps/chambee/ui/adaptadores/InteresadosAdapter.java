package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gps.chambee.R;
import com.gps.chambee.entidades.Perfil;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CasoUso;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Perfil perfil = lista.get(position);

        Bitmap imagen = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.ic_person
        );

        Glide.with(context)
                .load(imagen)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.civInteresado);

        new CUObtenerImagen(
                context,
                new CasoUso.EventoPeticionAceptada<Bitmap>() {
                    @Override
                    public void alAceptarPeticion(Bitmap bitmap) {

                        Glide.with(context)
                                .load(bitmap)
                                .apply(RequestOptions.circleCropTransform())
                                .into(holder.civInteresado);

                    }
                },
                new CasoUso.EventoPeticionRechazada() {
                    @Override
                    public void alRechazarOperacion() {
                        Bitmap imagen = BitmapFactory.decodeResource(
                                context.getResources(),
                                R.drawable.ic_person
                        );

                        Glide.with(context)
                                .load(imagen)
                                .apply(RequestOptions.circleCropTransform())
                                .into(holder.civInteresado);

                    }
                }
        ).enviarPeticion(perfil.getUrlPerfil());

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
