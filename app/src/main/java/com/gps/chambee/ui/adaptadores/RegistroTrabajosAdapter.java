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
import com.gps.chambee.entidades.vistas.Puesto;
import com.gps.chambee.negocios.casos.CUObtenerImagen;
import com.gps.chambee.negocios.casos.CasoUso;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RegistroTrabajosAdapter extends RecyclerView.Adapter<RegistroTrabajosAdapter.ViewHolder> {

    private Context context;
    private List<Puesto> lista;

    public RegistroTrabajosAdapter(Context context, List<Puesto> lista){
        this.context=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_registro_trabajos,parent,false);

        return new RegistroTrabajosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RegistroTrabajosAdapter.ViewHolder holder, int position) {
        Puesto puesto = lista.get(position);

        holder.tvCalificacionEstrellas.setText((String.valueOf(puesto.getEstrella())));
        holder.tvEmpresaRegistro.setText(puesto.getEmpresa().toString());
        holder.tvNombrePuesto.setText(puesto.getPuestoTrabajo());

        if (puesto.getUrlImagenEmpresa().equals("default")) {
            Bitmap defaultImg = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ic_person
            );
            if (defaultImg != null)
                holder.ivFotoPuestoTrabajo.setImageBitmap(defaultImg);
        } else {
            new CUObtenerImagen(context, new CasoUso.EventoPeticionAceptada<Bitmap>() {
                @Override
                public void alAceptarPeticion(Bitmap bitmap) {
                    holder.ivFotoPuestoTrabajo.setImageBitmap(bitmap);
                }
            }, new CasoUso.EventoPeticionRechazada() {
                @Override
                public void alRechazarOperacion() {
                    Bitmap defaultImg = BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.ic_person
                    );
                    holder.ivFotoPuestoTrabajo.setImageBitmap(defaultImg);
                }
            }).enviarPeticion(puesto.getUrlImagenEmpresa());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView ivFotoPuestoTrabajo;
        TextView tvNombrePuesto;
        TextView tvEmpresaRegistro;
        TextView tvCalificacionEstrellas;
        TextView tvNumeroCalificacionTrabajos;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivFotoPuestoTrabajo = itemView.findViewById(R.id.ivFotoPuestoTrabajo);
            tvNombrePuesto = itemView.findViewById(R.id.tvNombrePuesto);
            tvEmpresaRegistro = itemView.findViewById(R.id.tvEmpresaRegistro);
            tvCalificacionEstrellas = itemView.findViewById(R.id.tvCalificacionEstrellas);
        }
    }
}
