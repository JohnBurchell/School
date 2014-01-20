package com.adapa;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gimpleton on 2013-11-19.
 */
public class Stock {
    private static final String TAG_TICKER = "_id";
    private static final String TAG_DATA = "Data";
    private static final String TAG_NAME = "Name";

    String symbol;
    String companyName;
    String marketName;
    String marketURL;
    boolean added;

    ArrayList<StockData> stockList = new ArrayList<StockData>();
    ArrayList<StockData> stockHistory = new ArrayList<StockData>();
    JSONObject data;

    public Stock(String s, String n, String m, String murl) {
        symbol = s;
        companyName = n;
        marketName = m;
        marketURL = murl;
    }

    public boolean collectData(JSONObject a) {
        try {
            data = a;
            String TAG_STOCKDATA = "StockData";
            String TAG_TIMESTAMP = "TimeStamp";
            String TAG_LOCALTIMESTAMP = "LocalTimestamp";
            String TAG_PRICE = "Price";
            String TAG_CHANGE = "Change";
            String TAG_CHANGEPERCENT = "ChangePercent";
            String TAG_HIGH = "High";
            String TAG_LOW = "Low";
            String TAG_OPEN = "Open";
            String TAG_VOLUME = "Volume";
            String TAG_CLOSE = "Close";

            stockList.clear();
            stockHistory.clear();
            JSONArray stockdata = data.getJSONObject(TAG_DATA).getJSONArray(TAG_STOCKDATA);
            for (int i = 0; i < stockdata.length(); i++) {
                JSONObject history = stockdata.getJSONObject(i);
                String timeStamp = history.getString(TAG_TIMESTAMP);
                String localTimeStamp = history.getString(TAG_LOCALTIMESTAMP);
                double price = history.getDouble(TAG_PRICE);
                double change = history.getDouble(TAG_CHANGE);
                double changePercent = history.getDouble(TAG_CHANGEPERCENT);
                double high = history.getDouble(TAG_HIGH);
                double low = history.getDouble(TAG_LOW);
                double open = history.getDouble(TAG_OPEN);
                int volume = history.getInt(TAG_VOLUME);
                double close = history.getDouble(TAG_CLOSE);

                StockData stock = new StockData(timeStamp, localTimeStamp, price,
                        change, changePercent, high, low, open, volume, close);
                stockList.add(stock);
            }
            stockdata = data.getJSONObject(TAG_DATA).getJSONArray("Historical");
            for (int i = 0; i < stockdata.length(); i++) {
                JSONObject history = stockdata.getJSONObject(i);
                String timeStamp = history.getString(TAG_TIMESTAMP);
                String localTimeStamp = history.getString(TAG_LOCALTIMESTAMP);
                double price = history.getDouble(TAG_PRICE);
                double change = history.getDouble(TAG_CHANGE);
                double changePercent = history.getDouble(TAG_CHANGEPERCENT);
                double high = history.getDouble(TAG_HIGH);
                double low = history.getDouble(TAG_LOW);
                double open = history.getDouble(TAG_OPEN);
                int volume = history.getInt(TAG_VOLUME);
                double close = history.getDouble(TAG_CLOSE);

                StockData stock = new StockData(timeStamp, localTimeStamp, price,
                        change, changePercent, high, low, open, volume, close);
                stockHistory.add(stock);
            }
        } catch (JSONException e) {
            Log.d("STOCKDATA", e.getMessage() + " in stock: " + symbol);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<StockData> getStockHistory() {
        return stockHistory;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<StockData> getStockList() {
        return stockList;
    }

    public void setAdded(Boolean a) {
        added = a;
    }

    public boolean getAdded() {
        return added;
    }
}
