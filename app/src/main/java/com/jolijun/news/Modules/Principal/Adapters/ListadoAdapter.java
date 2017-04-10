package com.jolijun.news.Modules.Principal.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jolijun.news.Config;
import com.jolijun.news.Core.View.BaseRecyclerAdapter;
import com.jolijun.news.Modules.Principal.Services.Models.Noticias;
import com.jolijun.news.R;

/**
 * Created by pablo on 18/01/17.
 */

public class ListadoAdapter extends BaseRecyclerAdapter<Noticias.Noticia> {

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_principal_listado, parent, false);
        return new ViewHolder(view);
    }

    private class ViewHolder extends RecyclerViewHolder<Noticias.Noticia> {

        TextView nombre;
        TextView extracto;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = getUi().getTextView(R.id.nombre);
            extracto = getUi().getTextView(R.id.extracto);
            imagen = getUi().getImageView(R.id.imagen);
        }

        @Override
        public void setItem(Noticias.Noticia noticia, int position) {
            nombre.setText(noticia.getTitulo());
            extracto.setText(noticia.getExtracto());

            Glide.with(getUi().getContext()).load(Config.WS_BASE_URL+"/archivos/noticias/thumb_"+noticia.getImagen()).into(imagen);
        }

    }
}
