package com.jolijun.news.Modules.Principal.Fragments;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jolijun.news.Bootstrap;
import com.jolijun.news.Config;
import com.jolijun.news.Core.Module.FragmentBase;
import com.jolijun.news.Core.View.BaseRecyclerAdapter;
import com.jolijun.news.Modules.Principal.Adapters.ListadoAdapter;
import com.jolijun.news.Modules.Principal.Services.PrincipalServices;
import com.jolijun.news.Modules.Principal.Services.Models.Noticias;
import com.jolijun.news.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListadoFragment extends FragmentBase {

    private int i = 0;

    ProgressDialog dialogoListado;

    PrincipalServices service;

    public ListadoFragment(){
        super();

        service = Bootstrap.getServiceInstance().create(PrincipalServices.class);
        setViewId(R.id.principalFrame);
        setLayoutView(R.layout.fragment_principal_listado);
    }

    @Override
    public void onViewReady(){

        init();

    }

    private void init(){

        dialogoListado = new ProgressDialog(getActivityBase());
        dialogoListado.setTitle("Cargando...");
        dialogoListado.show();

        Observable<Noticias> cargar = Observable.create(new ObservableOnSubscribe<Noticias>() {
            @Override
            public void subscribe(final ObservableEmitter<Noticias> emmitter) throws Exception {

                Call<Noticias> peliculas = service.noticias(Config.PARTICION_ID, Config.API_KEY);

                peliculas.enqueue(new Callback<Noticias>() {
                    @Override
                    public void onResponse(Call<Noticias> call, Response<Noticias> response) {

                        if(response.body() != null) {
                            emmitter.onNext(response.body());
                        }
                        else{
                            emmitter.onError(new NullPointerException("Objeto response nulo."));
                        }
                        emmitter.onComplete();
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<Noticias> call, Throwable throwable) {
                        emmitter.onError(throwable);
                        call.cancel();
                    }
                });
            }
        });
        cargar.subscribeOn(Schedulers.io());
        cargar.observeOn(AndroidSchedulers.mainThread());

        Disposable listar = cargar.subscribeWith(new DisposableObserver<Noticias>(){
            @Override
            public void onNext(Noticias value) {


                RecyclerView listadoVista = getUi().getRecyclerView(R.id.listado);
                listadoVista.setHasFixedSize(true);
                listadoVista.setLayoutManager(getActivityBase().getLinearLayoutManagerVertical());

                ListadoAdapter listadoAdapter = new ListadoAdapter();
                listadoAdapter.setItems(value.getNoticias());

                listadoAdapter.setItemSelectedListener(new BaseRecyclerAdapter.ItemSelectedListener<Noticias.Noticia>() {
                    @Override
                    public void onItemSelected(Noticias.Noticia noticia, int position) {

                        ElementoFragment elementoFragment = new ElementoFragment();
                        elementoFragment.setNoticia(noticia);
                        elementoFragment.transaction(getSelf(), true);

                    }
                });

                listadoVista.setAdapter(listadoAdapter);
            }

            @Override
            public void onError(Throwable e) {
                dialogoListado.dismiss();
            }

            @Override
            public void onComplete() {
                dialogoListado.dismiss();
            }
        });

    }


}
