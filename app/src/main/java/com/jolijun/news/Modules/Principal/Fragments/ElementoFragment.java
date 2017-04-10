package com.jolijun.news.Modules.Principal.Fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.jolijun.news.Config;
import com.jolijun.news.Core.Module.FragmentBase;
import com.jolijun.news.Core.View.Ui;
import com.jolijun.news.Modules.Principal.Services.Models.Noticias;
import com.jolijun.news.R;

/**
 * Created by pablo on 18/01/17.
 */

public class ElementoFragment extends FragmentBase {

    private Noticias.Noticia noticia;

    public ElementoFragment(){
        super();
        setViewId(R.id.principalFrame);
        setLayoutView(R.layout.fragment_principal_elemento);
    }

    public Noticias.Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticias.Noticia noticia) {
        this.noticia = noticia;
    }

    @Override
    public void onViewReady(){

        ImageView imagen = getUi().getImageView(R.id.imagen);
        TextView noticia = getUi().getTextView(R.id.noticia);
        TextView titulo = getUi().getTextView(R.id.titulo);

        getActivityBase().getUi().getImageView(R.id.accionBack).setVisibility(View.VISIBLE);
        getActivityBase().getUi().getImageView(R.id.accionShare).setVisibility(View.VISIBLE);

        titulo.setText(getNoticia().getTitulo());
        Ui.setHtmlToTextView(noticia, getNoticia().getNoticia());
        Glide.with(this).load(Config.WS_BASE_URL+"/archivos/noticias/"+getNoticia().getImagen()).into(imagen);
        //YoYo.with(Techniques.Bounce).delay(1000).duration(2000).playOn(imagen);

        FloatingActionButton accionComment = getActivityBase().getUi().getFloatingActionButton(R.id.accionComment);
        accionComment.setVisibility(View.VISIBLE);
        accionComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComentarioFragment comentarioFragment = new ComentarioFragment();
                comentarioFragment.transaction(getSelf(), true);
            }
        });

    }

    @Override
    public boolean onBack() {

        getActivityBase().getUi().getImageView(R.id.accionBack).setVisibility(View.GONE);
        getActivityBase().getUi().getImageView(R.id.accionShare).setVisibility(View.GONE);
        getActivityBase().getUi().getImageView(R.id.accionComment).setVisibility(View.GONE);

        return true;
    }

}
