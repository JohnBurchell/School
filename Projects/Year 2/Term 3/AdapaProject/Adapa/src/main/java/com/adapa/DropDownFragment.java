package com.adapa;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-11-22.
 */
public class DropDownFragment extends Fragment {

    ListView listView;
    MyStockListAdapter adapter;
    ArrayList<Stock> searchResults;
    Context context;

    public DropDownFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drop_down, container, false);
        searchResults = new ArrayList<Stock>();
        listView = (ListView) rootView.findViewById(R.id.listView);
        for (Stock s : MainActivity.stocks){
            if (!s.added && (s.symbol.contains("AA") || s.companyName.contains("Inc")) ) {
                searchResults.add(s);
            }
        }
        updateList();
        return rootView;
    }

    private void updateList() {
        adapter = new MyStockListAdapter(context,
                R.layout.stocks_list_item, searchResults);
        listView.setAdapter(adapter);
    }

    public void fillList(String query) {
        searchResults = new ArrayList<Stock>();
        for (Stock s : MainActivity.stocks){
            if (!s.added && (s.symbol.contains(query) || s.companyName.contains(query)) ) {
                searchResults.add(s);
            }
        }
        updateList();
    }

}
