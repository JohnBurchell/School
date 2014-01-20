package com.adapa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-11-14.
 */
public class NewsFragment extends CustomFragment {
    ListView listView;
    NewsListAdapter adapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        startService();
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsListAdapter adapter = (NewsListAdapter) parent.getAdapter();
                News item;
                if (position == 0) {
                    item = (News) adapter.getItem(position);
                } else {
                    item = (News) adapter.getItem(position - 1);
                }
                Uri uri = Uri.parse(item.link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        return rootView;
    }

    @Override
    public void refreshList() {
        startService();
    }

    private void startService() {
        Intent intent = new Intent(getActivity(), RssService.class);
        intent.putExtra(RssService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
    }

    /**
     * Once the {@link RssService} finishes its task, the result is sent to this ResultReceiver.
     */
    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            try {
                ArrayList<News> items = (ArrayList<News>) resultData.getSerializable(RssService.ITEMS);
                if (items != null) {
                    items.remove(0);
                    adapter = new NewsListAdapter(getActivity(), R.layout.news_list_item, items);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                            Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                //
            }
        }

        ;
    };

    @Override
    public void createActionBar(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.menu_news, menu);
    }

    @Override
    public void prepareActionBar(Menu menu) {
        MenuItem item = (MenuItem) menu.findItem(R.id.action_refresh);
        item.setIcon(R.drawable.ic_menu_refresh_w);
    }

    @Override
    public void setActionBar(Context context, ActionBar actionBar) {
        // Spinner for marketName strings TODO: break out, one action bar per fragment;
        String[] temp = getResources().getStringArray(R.array.markets);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, MainActivity.RSS_NAMES);
        ActionBar.OnNavigationListener mNavListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                MainActivity.RSS_INDEX = i;
                startService();
                return false;
            }
        };

        actionBar.setListNavigationCallbacks(adapter, mNavListener);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }
}
