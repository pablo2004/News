package com.jolijun.news.Core.Module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import java.util.Hashtable;

import com.jolijun.news.Bootstrap;
import com.jolijun.news.Config;
import com.jolijun.news.Core.View.Ui;
import com.jolijun.news.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Pablo on 09/12/2016.
 */

public class ActivityBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int activityLayout;
    private Ui ui;
    private Bundle params = new Bundle();
    private ActivityBase self = null;
    private DrawerLayout drawer = null;
    private int drawerPosition = GravityCompat.START;

    public ActivityBase getSelf(){
        return this;
    }

    public void setSelf(ActivityBase self){
        this.self = self;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public Bundle getParams() {
        return params;
    }

    public void setParams(Bundle params) {
        this.params = params;
    }

    public ActivityBase(){
        setUi(new Ui(this));
    }

    public void setActivityLayout(int activityLayout){
        this.activityLayout = activityLayout;
    }

    public int getActivityLayout(){
        return this.activityLayout;
    }

    public int getDrawerPosition(){
        return this.drawerPosition;
    }

    public boolean isDrawerOpen(){
        boolean value = false;

        if(drawer != null){
            value = drawer.isDrawerOpen(getDrawerPosition());
        }

        return value;
    }

    public void toggleDrawer(){
        if(drawer != null){
            if(isDrawerOpen()){
                drawer.closeDrawer(getDrawerPosition());
            }
            else {
                drawer.openDrawer(getDrawerPosition());
            }
        }
    }

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setSelf(this);
        setParams(getIntent().getExtras());
        setContentView(getActivityLayout());
        getUi().setView(findViewById(android.R.id.content));

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/"+Config.FONT_DEFAULT)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void startOf(ActivityBase activity, boolean endLast){

        Intent intent = new Intent(activity, this.getClass());
        if(getParams().size() > 0){
            intent.putExtras(getParams());
        }

        activity.startActivity(intent);

        getParams().clear();
        if(endLast) {
            activity.finish();
        }

    }

    public void startOf(ActivityBase activity){
        startOf(activity, false);
    }

    public void startAndDestroy(ActivityBase activity){
        startOf(activity, true);
    }

    /* set Param */

    public void setParamByte(String index, byte value){
        getParams().putByte(index, value);
    }

    public void setParamInteger(String index, int value){
        getParams().putInt(index, value);
    }

    public void setParamString(String index, String value){
        getParams().putString(index, value);
    }

    public void setParamChar(String index, char value){
        getParams().putChar(index, value);
    }

    public void setParamFloat(String index, float value){
        getParams().putFloat(index, value);
    }

    public void setParamDouble(String index, double value){
        getParams().putDouble(index, value);
    }

    public void setParamBoolean(String index, boolean value){
        getParams().putBoolean(index, value);
    }

    public void setParamObject(String index, Object value){
        String className = this.getClass().getCanonicalName();

        if(Bootstrap.getObjectParams().containsKey(className)){
            Bootstrap.getObjectParams().get(className).put(index, value);
        }
        else{
            Hashtable<String, Object> newHastable = new Hashtable<>();
            newHastable.put(index, value);
            Bootstrap.getObjectParams().put(className, newHastable);
        }
    }

    public LinearLayoutManager getLinearLayoutManagerVertical(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    /* get Param */

    public Byte getParamByte(String index){
        return getParams().getByte(index);
    }

    public int getParamInteger(String index){
        return getParams().getInt(index);
    }

    public String getParamString(String index){
        return getParams().getString(index);
    }

    public char getParamChar(String index){
        return getParams().getChar(index);
    }

    public float getParamFloat(String index){
        return getParams().getFloat(index);
    }

    public double getParamDouble(String index){
        return getParams().getDouble(index);
    }

    public boolean getParamBoolean(String index){
        return getParams().getBoolean(index);
    }

    public Object getParamObject(String index){
        Object value = null;
        String className = this.getClass().getCanonicalName();

        if(Bootstrap.getObjectParams().containsKey(className)){
            value = Bootstrap.getObjectParams().get(className).get(index);
        }

        return value;
    }

    public void goBack(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        int fragmentCount = fragmentManager.getBackStackEntryCount();

        if (fragmentCount > 0) {

            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(fragmentCount - 1);
            String fragmentTag = backEntry.getName();
            Fragment fragmentBase = fragmentManager.findFragmentByTag(fragmentTag);

            if(fragmentBase != null){
                if(((FragmentBase)fragmentBase).onBack()){
                    goBack();
                }
            }
            else {
                goBack();
            }
        }
        else {
            goBack();
        }
    }

    public void initNavigationDrawer(Toolbar toolbar, DrawerLayout drawer, NavigationView navigationView){

        this.drawer = drawer;
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
