package com.jolijun.news;

import android.os.Bundle;
import android.os.Handler;

import java.util.Hashtable;

import com.jolijun.news.Core.Module.ActivityBase;
import com.jolijun.news.Modules.Principal.PrincipalActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Bootstrap extends ActivityBase {

    private static Bootstrap self;

    private static Retrofit serviceInstance;

    private static Hashtable<String, Hashtable<String, Object>> objectParams = new Hashtable<String, Hashtable<String, Object>>();

    public Bootstrap(){
        setActivityLayout(R.layout.activity_bootstrap);
    }

    public static void setBootstrap(Bootstrap bootstrap){
        Bootstrap.self = bootstrap;
    }

    public static Bootstrap getBootstrap(){
        return Bootstrap.self;
    }

    public static Hashtable<String, Hashtable<String, Object>> getObjectParams() {
        return objectParams;
    }

    public static void setObjectParams(Hashtable<String, Hashtable<String, Object>> objectParams) {
        Bootstrap.objectParams = objectParams;
    }

    public static Retrofit getServiceInstance() {
        return Bootstrap.serviceInstance;
    }

    public static void setServiceInstance(Retrofit serviceInstance) {
        Bootstrap.serviceInstance = serviceInstance;
    }

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);

        setBootstrap(this);
        setServiceInstance(new Retrofit.Builder()
                .baseUrl(Config.WS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new PrincipalActivity().startAndDestroy(getSelf());
            }
        }, 2000);

    }

}
