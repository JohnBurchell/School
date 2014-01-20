package com.adapa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-11-14.
 */
public class MyStocksFragment extends CustomFragment {
    //TODO: Sort by. Market sort by name
    ListView listView;
    MyStockListAdapter adapter;

    static ProgressDialog pDialog;
    static PopupWindow popupWindow;

    SearchListAdapter popUpAdapter;
    ListView popUpListView;

    int stockCounter = 0;
    boolean stocksLoaded= false;

    private void fillMyStocks() {
        MainActivity.user.myStocks = new ArrayList<Stock>();
        for (Stock s : MainActivity.stocks) {
            if (s.added) {
                MainActivity.user.myStocks.add(s);
            }
        }
        updateList();
    }

    /**
     * Recreates myStock and fetches data for all.
     */
    private void fetchStockData() {
        showDialog();
        MainActivity.user.myStocks = new ArrayList<Stock>();
        for (final Stock s : MainActivity.stocks) {
            if (s.added) {
                stockCounter++;
                JSONAsync task = new JSONAsync(MainActivity.URL + s.marketURL + MainActivity.STOCK, s);
                task.execute();
                task.setOnJSONAsyncComplete(new JSONListener() {
                    @Override
                    public void onJSONAsyncComplete(Boolean validData) {
                        stockCounter--;
                        Log.d("STOCKDATA_LOG", Integer.toString(stockCounter));
                        if (stockCounter <= 0) {
                            stockCounter = 0;
                            dismissDialog();
                            updateList();
                        }
                        if (validData) {
                            MainActivity.user.myStocks.add(s);
                        } else {
                            MainActivity.stocks.remove(s);
                        }
                    }
                });
            }
        }
        stocksLoaded = true;
        updateList();
    }

    /**
     * Assuming stock is already in myStocks
     */
    @Override
    public void fetchSingleStockData(final Stock s) {
        final JSONAsync task = new JSONAsync(MainActivity.URL + s.marketURL + MainActivity.STOCK, s);
        task.execute();
        task.setOnJSONAsyncComplete(new JSONListener() {
            @Override
            public void onJSONAsyncComplete(Boolean validData) {
                if (validData) {
                    MainActivity.user.addStock(s);
                    fillMyStocks();
                } else {
                    alertInvalidStock(s.symbol);
                    //removeStock(s);
                }
            }
        });
    }

    private void updateList() {
        adapter = new MyStockListAdapter(getActivity(), R.layout.stocks_list_item, MainActivity.user.myStocks);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pDialog = new ProgressDialog(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_my_stocks, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        if(!stocksLoaded)
            fetchStockData();
        Animation slide = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_from_left);
        listView.startAnimation(slide);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //MainActivity.selectItem(MainActivity.STOCK_FRAGMENT, getActivity().getSupportFragmentManager());
                Stock s = (Stock) parent.getItemAtPosition(position);
                loadGraph(s);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
    }

    public void loadGraph(Stock stock) {
        MainActivity.activeStock = stock;
        Intent intent = new Intent(getActivity(), SimpleStockChartActivity.class);
        startActivity(intent);
    }

    @Override
    public void createActionBar(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.menu_my_stocks, menu);
    }

    private void addStock(Stock s) {
        MainActivity.user.addStock(s);
        updateList();
    }

    @Override
    public void removeFromList(Stock s) {
        final Stock stock = s;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete stock " + s.symbol + " from MY STOCKS?")
                .setPositiveButton("Ya, sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeStock(stock);
                    }
                })
                .setNegativeButton("No.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void removeStock(Stock s) {
        MainActivity.user.deleteStock(s);
        updateList();
    }

    private boolean updateSearchFragment(String s) {
        if (popupWindow != null && popupWindow.isShowing()) {
            ArrayList<Stock> list = new ArrayList<Stock>();
            for (Stock stock : MainActivity.stocks) {
                if (stock.symbol.toLowerCase().contains(s) || stock.companyName.toLowerCase().contains(s)) {
                    list.add(stock);
                }
            }
            if (!list.isEmpty()) {
                popUpAdapter.clear();
                popUpAdapter = new SearchListAdapter(getActivity(), R.layout.search_list_item, list);
                popUpListView.setAdapter(popUpAdapter);
                popupWindow.update();
            } else {
                popupWindow.dismiss();
            }
            return true;
        }
        return false;
    }

    private void showSearchFragment(String s, View v) {
        LayoutInflater layoutInflater
                = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_drop_down, null);
        popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setFocusable(false);

        ArrayList<Stock> list = new ArrayList<Stock>();
        for (Stock stock : MainActivity.stocks) {
            if (stock.symbol.contains(s) || stock.companyName.contains(s)) {
                list.add(stock);
            }
        }

        popUpListView = (ListView) view.findViewById(R.id.searchListView);
        popUpAdapter = new SearchListAdapter(getActivity(), R.layout.search_list_item, list);
        popUpListView.setAdapter(popUpAdapter);
        popUpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stock newStock = (Stock) parent.getItemAtPosition(position);
                addStock(newStock);
                popupWindow.dismiss();
                Log.d("SEARCH_DATA", "touching!");
            }
        });
        Animation slide = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_from_top);
        popUpListView.startAnimation(slide);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                fillMyStocks();
            }
        });
        popupWindow.showAsDropDown(v);
    }

    @Override
    public void prepareActionBar(Menu menu) {
        MenuItem update_item = (MenuItem) menu.findItem(R.id.action_refresh);
        update_item.setIcon(R.drawable.ic_menu_refresh_w);
        MenuItem search_item = (MenuItem) menu.findItem(R.id.action_add);
        search_item.setIcon(R.drawable.ic_menu_search_w);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search_item);
        searchView.setGravity(Gravity.RIGHT);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                showSearchFragment(s, searchView);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.equals("")) {
                    if (!updateSearchFragment(s)) {
                        showSearchFragment(s, searchView);
                    }
                } else {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
                return false;
            }
        });

        // When using the support library, the setOnActionExpandListener() method is
        // static and accepts the MenuItem object as an argument
        MenuItemCompat.setOnActionExpandListener(search_item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;  // Return true to expand action view
            }
        });
    }

    @Override
    public void setActionBar(Context context, ActionBar actionBar) {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    @Override
    public void refreshList() {
       fetchUserStocks();
    }

    private void fetchUserStocks() {
        showDialog();
        JSONUserAsync task = new JSONUserAsync(MainActivity.URL+"users/" + MainActivity.user.name);
        task.setOnJSONAsyncComplete(new JSONListener() {
            @Override
            public void onJSONAsyncComplete(Boolean validData) {
                if(validData){
                    fetchStockData();
                }
            }
        });
        task.execute();
    }

    private void showDialog() {
        if (stockCounter != 0 || !pDialog.isShowing()) {
            pDialog.setMessage("Getting Stock Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }

    private void dismissDialog() {
        pDialog.dismiss();
    }

    private void alertInvalidStock(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Invalid Stock");
        builder.setMessage("Stock " + name + " is invalid and will be removed");
        builder.setCancelable(false);

        // Set up the buttons
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        builder.show();
    }
}
