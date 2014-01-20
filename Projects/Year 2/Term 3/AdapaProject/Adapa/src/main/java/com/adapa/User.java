package com.adapa;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-11-26.
 */
public class User {
    String name;
    ArrayList<Stock> myStocks;
    String addUrl;
    String deleteUrl;

    public User(String n) {
        name = n;
        myStocks = new ArrayList<Stock>();
        addUrl = MainActivity.URL+"users/_design/update/_update/update_user/" + name;
        deleteUrl = MainActivity.URL+"users/_design/update/_update/delete_symbol/" + name;
    }

    public void addStock(Stock s) {
        myStocks.add(s);
        s.added = true;
        pushToDataBase(getJSONSymbol(s));
    }

    public void deleteStock(Stock s) {
        myStocks.remove(s);
        s.added = false;
        deleteFromDataBase(getJSONSymbol(s));
    }

    private void pushToDataBase(JSONObject e) {
        JSONUserPut add = new JSONUserPut(addUrl, e);
        add.setOnJSONAsyncComplete(new JSONListener() {
            @Override
            public void onJSONAsyncComplete(Boolean validData) {
                if (validData) {
                    Log.d("JSON_", "Database successfully updated");
                } else {
                    Log.d("JSON_", "Something went wrong :(");
                }
            }
        });
        Log.d("JSON_", addUrl);
        add.execute();
    }

    private void deleteFromDataBase(JSONObject e) {
        JSONUserPut delete = new JSONUserPut(deleteUrl, e);
        delete.setOnJSONAsyncComplete(new JSONListener() {
            @Override
            public void onJSONAsyncComplete(Boolean validData) {
                if (validData) {
                    Log.d("JSON_", "Database successfully updated");
                } else {
                    Log.d("JSON_", "Something went wrong :(");
                }
            }
        });
        delete.execute();
    }

    public static User logIn(String name) {
        return new User(name.toLowerCase());
    }

    private JSONObject getJSONSymbol(Stock s) {
        JSONObject jObj = new JSONObject();
        try {
            jObj.put("Symbol", s.symbol);
            jObj.put("Market", s.marketName);
            jObj.put("Username", MainActivity.user.name);
            jObj.put("Password", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObj;
    }
}
