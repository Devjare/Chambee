package com.gps.chambee.negocios.presentadores;

import com.gps.chambee.entidades.Categoria;
import com.gps.chambee.entidades.vistas.ComentarioPublicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PresentadorListaCategoriasPublicacion extends Presentador<List<Categoria>> {
    @Override
    public List<Categoria> procesar(JSONObject json) {
        JSONArray jsonArray = json.optJSONArray("json");
        List<Categoria> categorias = new ArrayList<>();

        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;
            Categoria categoria = new Categoria.CategoriaBuilder()
                    .setId(jsonObject.optInt("id_categorias"))
                    .setNombre(jsonObject.optString("categoria")).build();

            categorias.add(categoria);
        }

        return categorias;
    }
}
