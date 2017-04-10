package com.jolijun.news.Modules.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.jolijun.news.Core.Module.ActivityBase;
import com.jolijun.news.Modules.Principal.Fragments.ComentarioFragment;
import com.jolijun.news.Modules.Principal.Fragments.ListadoFragment;
import com.jolijun.news.R;

public class PrincipalActivity extends ActivityBase {

    public PrincipalActivity(){
        setActivityLayout(R.layout.activity_principal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = getUi().getToolbar(R.id.toolbar);
        NavigationView navigationView = getUi().getNavigationView(R.id.nav_view);
        DrawerLayout drawer = getUi().getDrawerLayout(R.id.drawer_layout);

        initNavigationDrawer(toolbar, drawer, navigationView);

        ImageView actionBack = getUi().getImageView(R.id.accionBack);
        ImageView actionShare = getUi().getImageView(R.id.accionShare);

        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        actionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.congresotamaulipas.gob.mx/");
                startActivity(Intent.createChooser(sharingIntent, "Congreso del Estado de Tamaulipas"));
            }
        });

        ListadoFragment listadoFragment = new ListadoFragment();
        listadoFragment.transaction(this, null, false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        toggleDrawer();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            toggleDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
