package com.gps.chambee.ui.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gps.chambee.R;
import com.gps.chambee.entidades.Publicacion;
import com.gps.chambee.entidades.vistas.PublicacionEmpresa;
import com.gps.chambee.entidades.vistas.PublicacionPersona;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CasoUso;
import com.gps.chambee.ui.actividades.MainActivity;
import com.gps.chambee.ui.actividades.PublicacionActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublicacionEmpresaAdapter extends RecyclerView.Adapter<PublicacionEmpresaAdapter.ViewHolder> {

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreReclutador;
        CircleImageView civFotoPerfilEmpresa;
        TextView tvTiempoPublicacion;
        TextView tvEtiquetaPrincipal;
        TextView tvInteresados;
        TextView tvComentariosEmpresa;
        TextView tvVistos;
        TextView tvNombreTrabajoPublicacion;
        TextView tvDescripcionPublicacionTrabajo;
        ImageView ivImagenPublicacionTrabajo;

        int idPublicacion;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvNombreReclutador = itemView.findViewById(R.id.tvNombreReclutador);
            civFotoPerfilEmpresa = itemView.findViewById(R.id.civFotoPerfilEmpresa);
            tvTiempoPublicacion = itemView.findViewById(R.id.tvTiempoPublicacion);
            tvEtiquetaPrincipal = itemView.findViewById(R.id.tvEtiquetaPrincipal);
            tvInteresados = itemView.findViewById(R.id.tvInteresados);
            tvComentariosEmpresa = itemView.findViewById(R.id.tvComentariosEmpresa);
            tvVistos = itemView.findViewById(R.id.tvVistos);
            tvNombreTrabajoPublicacion = itemView.findViewById(R.id.tvNombreTrabajoPublicacion);
            tvDescripcionPublicacionTrabajo = itemView.findViewById(R.id.tvDescripcionPublicacionTrabajo);
            ivImagenPublicacionTrabajo = itemView.findViewById(R.id.ivImagenPublicacionTrabajo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PublicacionActivity.class);
                    intent.putExtra("id", idPublicacion);
                    context.startActivity(intent);
                }
            });
        }
    }

    private Context context;
    private List<PublicacionEmpresa> lista;

    public PublicacionEmpresaAdapter(Context context, List<PublicacionEmpresa> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion_empresa, parent, false);
        return new PublicacionEmpresaAdapter.ViewHolder(view);
    }

    private String TAG = this.getClass().getSimpleName();
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
      
        PublicacionEmpresa publicacion = lista.get(position);

        holder.idPublicacion = publicacion.getIdPublicacionEmpresa();

        holder.tvComentariosEmpresa.setText(publicacion.getComentarios().toString());
        holder.tvDescripcionPublicacionTrabajo.setText(publicacion.getDescripcion());
        holder.tvEtiquetaPrincipal.setText(publicacion.getEtiqueta());
        holder.tvInteresados.setText(publicacion.getInteresados().toString());
        holder.tvNombreReclutador.setText(publicacion.getNombreEmpresa());
        holder.tvTiempoPublicacion.setText(publicacion.getTiempo().toString());
        holder.tvNombreTrabajoPublicacion.setText(publicacion.getNombreTrabajo());
        holder.tvVistos.setText(publicacion.getVistos().toString());

        if (publicacion.getUrlImagenEmpresa().equals("default")) {
            Bitmap defaultImg = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ic_person
            );
            holder.civFotoPerfilEmpresa.setImageBitmap(defaultImg);
        } else {

            // Obtener imagen del perfil de la empresa.
            new CUObtenerImagen(context, new CasoUso.EventoPeticionAceptada<Bitmap>() {
                @Override
                public void alAceptarPeticion(Bitmap bitmap) {
                    holder.civFotoPerfilEmpresa.setImageBitmap(bitmap);
                }
            }, new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    Bitmap defaultImg = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ic_person
                    );
                    holder.civFotoPerfilEmpresa.setImageBitmap(defaultImg);
                }
            }).enviarPeticion(publicacion.getUrlImagenEmpresa());
        }

        if (publicacion.getUrlImagenTrabajo().equals("default")) {
            Bitmap defaultImg = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ic_portfolio
            );
            holder.civFotoPerfilEmpresa.setImageBitmap(defaultImg);
        } else {
            // Obtener imagen del trabajo de la empresa.
            new CUObtenerImagen(context, new CasoUso.EventoPeticionAceptada<Bitmap>() {
                @Override
                public void alAceptarPeticion(Bitmap bitmap) {
                    holder.ivImagenPublicacionTrabajo.setImageBitmap(bitmap);
                }
            }, new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    Bitmap defaultImg = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ic_portfolio
                    );
                    holder.civFotoPerfilEmpresa.setImageBitmap(defaultImg);
                }
            }).enviarPeticion(publicacion.getUrlImagenTrabajo());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}