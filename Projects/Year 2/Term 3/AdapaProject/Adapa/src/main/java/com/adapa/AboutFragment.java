package com.adapa;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-12-17.
 */
public class AboutFragment extends CustomFragment{

    ListView listView;
    ArrayList<News> names;
    NewsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        names = new ArrayList<News>();
        names.add(new News("Ludvig", "Gee", "", "adapa", true));
        names.add(new News("Carl", "Berglund", "", "adapa", true));

        listView = (ListView)rootView.findViewById(R.id.listView);
        adapter = new NewsListAdapter(getActivity(), R.layout.news_list_item, names);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void setActionBar(Context context, ActionBar actionBar) {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }
}
