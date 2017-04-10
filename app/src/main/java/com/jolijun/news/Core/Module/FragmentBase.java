package com.jolijun.news.Core.Module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jolijun.news.Core.Animation.FragmentAnimation;
import com.jolijun.news.Core.Interface.OnBackPress;
import com.jolijun.news.Core.View.Ui;



public class FragmentBase extends Fragment implements OnBackPress {

    private Ui ui;
    private int viewId;
    private FragmentAnimation animation;
    private int layoutView;
    private boolean replaceAll = false;
    private ActivityBase activityBase;

    private void setReplaceAll(boolean replaceAll){
        this.replaceAll = replaceAll;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public int getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(int layoutView) {
        this.layoutView = layoutView;
    }

    public FragmentAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(FragmentAnimation animation) {
        this.animation = animation;
    }

    public FragmentBase(){
        setUi(new Ui(this.getContext()));
        setAnimation(FragmentAnimation.getAnimation1());
    }

    public FragmentBase getSelf(){
        return this;
    }

    public ActivityBase getActivityBase() {
        ActivityBase activity;
        if(activityBase == null){
            activity = (ActivityBase) getActivity();
        }
        else {
            activity = activityBase;
        }

        return activity;
    }

    public void setActivityBase(ActivityBase activityBase) {
        this.activityBase = activityBase;
    }

    public void onViewReady(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutView(), container, false);
        getUi().setView(view);
        onViewReady();
        return view;

    }

    public void transaction(@NonNull ActivityBase activityBase, FragmentBase fragmentBase, boolean backstack) {

        FragmentAnimation animation = getAnimation();
        setActivityBase(activityBase);

        if (activityBase != null && !activityBase.isDestroyed() && !activityBase.isFinishing()) {

            FragmentManager manager = activityBase.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            if(fragmentBase != null) {
                transaction.hide(fragmentBase);
            }

            if (backstack) {
                transaction.addToBackStack(this.getClass().getCanonicalName());
            }

            if (getAnimation() != null) {
                transaction.setCustomAnimations(animation.getEnter(), animation.getExit(), animation.getPopEnter(), animation.getPopExit());
            }

            if (replaceAll) {
                transaction.replace(getViewId(), this, this.getClass().getCanonicalName());
            } else {
                transaction.add(getViewId(), this, this.getClass().getCanonicalName());
            }

            try{
                transaction.commit();
            }
            catch (Exception e){
                Log.e("ERROR", e.getMessage());
            }

        }
    }

    public void transaction(FragmentBase fragmentBase, boolean backstack){
        transaction(fragmentBase.getActivityBase(), fragmentBase, backstack);
    }

    @Override
    public boolean onBack() {
        return true;
    }
}
