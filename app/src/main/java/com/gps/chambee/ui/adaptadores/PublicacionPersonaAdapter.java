package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.PublicacionPersona;
import com.gps.chambee.negocios.casos.CasoUso;

import com.gps.chambee.negocios.casos.CUObtenerImagen;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublicacionPersonaAdapter extends RecyclerView.Adapter<PublicacionPersonaAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView civFotoPerfilPersona;
        TextView tvNombrePersonaPublicacion;
        TextView tvTiempoPublicacionPersona;
        TextView tvEtiquetaPublicacionPersona;
        TextView tvLikesPersona;
        TextView tvComentariosPersona;
        TextView tvVistosPersona;
        TextView tvDescripcionPersona;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            civFotoPerfilPersona = itemView.findViewById(R.id.civFotoPerfil);
            tvNombrePersonaPublicacion = itemView.findViewById(R.id.tvNombrePersonaPublicacion);
            tvTiempoPublicacionPersona = itemView.findViewById(R.id.tvTiempoPublicacionPersona);
            tvEtiquetaPublicacionPersona = itemView.findViewById(R.id.tvEtiquetaPublicacionPersona);
            tvLikesPersona = itemView.findViewById(R.id.tvLikesPersona);
            tvComentariosPersona = itemView.findViewById(R.id.tvComentariosPersona);
            tvVistosPersona = itemView.findViewById(R.id.tvVistosPersona);
            tvDescripcionPersona = itemView.findViewById(R.id.tvDescripcionPersona);

        }
    }

    private Context context;
    private List<PublicacionPersona> lista;

    public PublicacionPersonaAdapter(Context context,List<PublicacionPersona> lista){
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion_persona,parent,false);
        return new PublicacionPersonaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PublicacionPersonaAdapter.ViewHolder holder, int position) {

        PublicacionPersona publicacion = lista.get(position);

        holder.tvComentariosPersona.setText(publicacion.getComentarios().toString());
        holder.tvDescripcionPersona.setText(publicacion.getDescripcion());
        holder.tvEtiquetaPublicacionPersona.setText(publicacion.getEtiqueta());
        holder.tvLikesPersona.setText(publicacion.getInteresados().toString());
        holder.tvNombrePersonaPublicacion.setText(publicacion.getNombrePersona());
        holder.tvTiempoPublicacionPersona.setText(publicacion.getTiempo().toString());
        holder.tvVistosPersona.setText(publicacion.getVistos().toString());

        if (publicacion.getUrlImagenEmpresa().equals("default")) {
            Bitmap defaultImg = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ic_person
            );
            if (defaultImg != null)
                holder.civFotoPerfilPersona.setImageBitmap(defaultImg);
        } else {
            new CUObtenerImagen(context, new CasoUso.EventoPeticionAceptada<Bitmap>() {
                @Override
                public void alAceptarPeticion(Bitmap bitmap) {
                    holder.civFotoPerfilPersona.setImageBitmap(bitmap);
                }
            }, new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    Bitmap defaultImg = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ic_person
                    );
                    holder.civFotoPerfilPersona.setImageBitmap(defaultImg);
                }
            }).enviarPeticion(publicacion.getUrlImagenEmpresa());
        }

    }

    //Cambia el nombre de la UrlImagenEmpresa a Persona

    @Override
    public int getItemCount() {
        return lista.size();
    }
}