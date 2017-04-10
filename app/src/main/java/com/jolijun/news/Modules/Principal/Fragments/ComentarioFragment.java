package com.jolijun.news.Modules.Principal.Fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jolijun.news.Config;
import com.jolijun.news.Core.Module.FragmentBase;
import com.jolijun.news.Core.View.Ui;
import com.jolijun.news.Modules.Principal.Services.Models.Noticias;
import com.jolijun.news.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

/**
 * Created by pablo on 18/01/17.
 */

public class ComentarioFragment extends FragmentBase {

    private Noticias.Noticia noticia;

    private Validator validator;

    @NotEmpty(message = "Debes escribir el comentario.")
    private EditText comentario;

    public ComentarioFragment(){
        super();
        setViewId(R.id.principalFrame);
        setLayoutView(R.layout.fragment_principal_comentario);
    }

    public Noticias.Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticias.Noticia noticia) {
        this.noticia = noticia;
    }

    @Override
    public void onViewReady(){

        comentario = getUi().getEditText(R.id.comentario);
        Button comentar = getUi().getButton(R.id.comentar);

        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                getActivityBase().onBackPressed();
                Toast.makeText(getActivityBase(), "Comentario Exitoso.", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for(ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(getActivityBase());
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    } else {
                        Toast.makeText(getActivityBase(), message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });


        getActivityBase().getUi().getImageView(R.id.accionShare).setVisibility(View.GONE);
        getActivityBase().getUi().getImageView(R.id.accionComment).setVisibility(View.GONE);

    }

    @Override
    public boolean onBack() {

        getActivityBase().getUi().getImageView(R.id.accionShare).setVisibility(View.VISIBLE);
        getActivityBase().getUi().getImageView(R.id.accionComment).setVisibility(View.VISIBLE);

        return true;
    }

}
