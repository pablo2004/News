package com.jolijun.news.Core.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pablo on 09/12/2016.
 */

public class Ui {

    private Context context = null;
    private View view = null;

    public Ui(Context context, View view){
        this.setContext(context);
        this.setView(view);
    }

    public Ui(Context context){
        this.setContext(context);
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }
        }
        return views;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Context getContext(){
        return this.context;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public ImageView getImageView(int widgetId){
        return (ImageView) this.getView().findViewById(widgetId);
    }

    public View getItemView(int widgetId){
        return this.getView().findViewById(widgetId);
    }

    public EditText getEditText(int widgetId){
        return (EditText) this.getView().findViewById(widgetId);
    }

    public Button getButton(int widgetId){
        return (Button) this.getView().findViewById(widgetId);
    }

    public TextView getTextView(int widgetId){
        return (TextView) this.getView().findViewById(widgetId);
    }

    public LinearLayout getLinearLayout(int widgetId){
        return (LinearLayout) this.getView().findViewById(widgetId);
    }

    public DrawerLayout getDrawerLayout(int widgetId){
        return (DrawerLayout) this.getView().findViewById(widgetId);
    }

    public RecyclerView getRecyclerView(int widgetId){
        return (RecyclerView) this.getView().findViewById(widgetId);
    }

    public RelativeLayout getRelativeLayout(int widgetId){
        return (RelativeLayout) this.getView().findViewById(widgetId);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(int widgetId){
        return (SwipeRefreshLayout) this.getView().findViewById(widgetId);
    }

    public Spinner getSpinner(int widgetId){
        return (Spinner) this.getView().findViewById(widgetId);
    }

    public CardView getCardView(int widgetId){
        return (CardView) this.getView().findViewById(widgetId);
    }

    public ProgressBar getProgressBar(int widgetId){
        return (ProgressBar) this.getView().findViewById(widgetId);
    }

    public Toolbar getToolbar(int widgetId){
        return (Toolbar) this.getView().findViewById(widgetId);
    }

    public NavigationView getNavigationView(int widgetId){
        return (NavigationView) this.getView().findViewById(widgetId);
    }

    public FloatingActionButton getFloatingActionButton(int widgetId){
        return (FloatingActionButton) this.getView().findViewById(widgetId);
    }

    public String string(int stringId){

        if(getContext() != null){
            try {
                return getContext().getResources().getString(stringId);
            }
            catch (NullPointerException e){
                return "ERROR";
            }
        }
        else{
            return "ERROR";
        }

    }

    public int color(int colorId){

        if(getContext() != null){
            return getContext().getResources().getColor(colorId);
        }
        else{
            return 0;
        }

    }

    public Drawable drawable(int drawerId){

        if(getContext() != null){
            return getContext().getResources().getDrawable(drawerId);
        }
        else{
            return null;
        }

    }

    public static void setHtmlToTextView(TextView textView, String htmlString){
        Spanned html;

        if (android.os.Build.VERSION.SDK_INT < 24)
            html = Html.fromHtml(htmlString);
        else
            html = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT);

        textView.setText(html);
    }

}