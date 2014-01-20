package com.adapa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by CHURLZ on 2013-11-19.
 */
public abstract class CustomFragment extends Fragment {

    public void createActionBar(MenuInflater inflater, Menu menu){
        //inflater.inflate(R.menu.main, menu);
    }

    public void prepareActionBar(Menu menu){
        //
    }

    public void setActionBar(Context context, ActionBar actionBar){

    }
    public void refreshList() {

    }
    public void removeFromList(Stock s){

    }
    public void fetchSingleStockData(Stock s) {

    }
    public void marketList(){

    }
}
