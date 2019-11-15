package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.PublicacionEmpresa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaPublicacionEmpresa extends
        Presentador<List<PublicacionEmpresa>> {

    @Override
    public List<PublicacionEmpresa> procesar(JSONObject json) {

        JSONArray jsonArray = json.optJSONArray("publicacion_empresa");
        List<PublicacionEmpresa> publicaciones = new ArrayList<>();

        publicaciones.add(new PublicacionEmpresa.PublicacionEmpresaBuilder()
                .setComentarios(0)
                .setUrlImagenTrabajo("default")
                .setUrlImagenEmpresa("default")
                .setNombreTrabajo("job")
                .setEtiqueta("tag")
                .setDescripcion("desc")
                .setInteresada(1)
                .setInteresados(1)
                .setNombreEmpresa("company")
                .setTiempo("Time")
                .setVista(1)
                .setVistos(1)
                .build());

        /*for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            assert jsonObject != null;
            PublicacionEmpresa publicacion = new PublicacionEmpresa.PublicacionEmpresaBuilder()
                    .setNombreEmpresa(jsonObject.optString("nombre_empresa"))
                    .setNombreTrabajo(jsonObject.optString("nombre_trabajo"))
                    .setComentarios(jsonObject.optInt("comentarios"))
                    .setDescripcion(jsonObject.optString("descripcion"))
                    .setEtiqueta(jsonObject.optString("etiqueta"))
                    .setInteresada(jsonObject.optInt("interesada"))
                    .setInteresados(jsonObject.optInt("interesados"))
                    .setTiempo(jsonObject.optString("tiempo"))
                    .setVista(jsonObject.optInt("vista"))
                    .setVistos(jsonObject.optInt("vistos"))
                    .setUrlImagenTrabajo(jsonObject.optString("url_imagen_trabajo"))
                    .setUrlImagenEmpresa(jsonObject.optString("url_imagen_empresa")).build();
            publicaciones.add(publicacion);
        }*/

        return publicaciones;
    }
}