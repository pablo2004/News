package com.jolijun.news.Core.Animation;

import com.jolijun.news.R;

/**
 * Created by pablo on 18/01/17.
 */

public class FragmentAnimation {

    private int enter;
    private int exit;
    private int popEnter;
    private int popExit;

    public static FragmentAnimation getAnimation1(){
        return new FragmentAnimation(R.anim.enter_fragment, R.anim.exit_fragment, R.anim.enter_fragment, R.anim.exit_fragment);
    }

    public FragmentAnimation(){

    }

    public FragmentAnimation(int enter, int exit, int popEnter, int popExit){

        this.setEnter(enter);
        this.setExit(exit);
        this.setPopEnter(popEnter);
        this.setPopExit(popExit);

    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public int getPopEnter() {
        return popEnter;
    }

    public void setPopEnter(int popEnter) {
        this.popEnter = popEnter;
    }

    public int getPopExit() {
        return popExit;
    }

    public void setPopExit(int popExit) {
        this.popExit = popExit;
    }
}
