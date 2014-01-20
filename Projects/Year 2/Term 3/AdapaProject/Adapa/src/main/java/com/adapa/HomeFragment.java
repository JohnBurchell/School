package com.adapa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.stockchart.StockChartView;

import java.util.ArrayList;


/**
 * Created by CHURLZ on 2013-11-15.
 */
public class HomeFragment extends CustomFragment {

    ListView listView;
    HomeNewsListAdapter adapter;
    ArrayList<Market>markets;
    ListView marketsListView;
    MarketsListAdapter marketsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeNewsListAdapter adapter = (HomeNewsListAdapter) parent.getAdapter();
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
        markets = new ArrayList<Market>();
        markets.add(MainActivity.nasdaq);
        markets.add(MainActivity.lse);
        markets.add(MainActivity.dax);

        marketsListView = (ListView) rootView.findViewById(R.id.pager);
        if(MainActivity.marketsLoaded){
            marketList();
        }
        startService();
        return rootView;
    }

    @Override
    public void marketList(){
        Log.d("MARKET_", "Displaying market...");
        MainActivity.marketsLoaded = true;
        marketsAdapter = new MarketsListAdapter(getActivity(), R.layout.stocks_list_item, markets);
        marketsListView.setAdapter(marketsAdapter);
    }

    @Override
    public void createActionBar(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public void prepareActionBar(Menu menu) {

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

    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            try {
                ArrayList<News> items = (ArrayList<News>) resultData.getSerializable(RssService.ITEMS);
                if (items != null) {
                    items.remove(0);
                    adapter = new HomeNewsListAdapter(getActivity(), R.layout.home_list_item, items);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                            Toast.LENGTH_LONG).show();
                }
                listView.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                //
            }
        }

        ;
    };

    @Override
    public void setActionBar(Context context, ActionBar actionBar) {
        String[] temp = getResources().getStringArray(R.array.markets);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, temp);
        ActionBar.OnNavigationListener mNavListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                MainActivity.RSS_INDEX = 3 + i;
                startService();
                return false
                        ;
            }
        };
        actionBar.setListNavigationCallbacks(adapter, mNavListener);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }
}
