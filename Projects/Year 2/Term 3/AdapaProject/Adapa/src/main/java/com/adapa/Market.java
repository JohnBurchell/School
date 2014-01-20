package com.adapa;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CHURLZ on 2013-12-20.
 */
public class Market {
    String symbol;
    String companyName;
    JSONObject data;

    StockData stockData;

    public Market() {

    }

    public boolean collectData(JSONObject a) {
        try {
            data = a;
            String TAG_TIMESTAMP = "TimeStamp";
            String TAG_PRICE = "Price";
            String TAG_CHANGE = "Change";
            String TAG_CHANGEPERCENT = "ChangePercent";
            String TAG_HIGH = "High";
            String TAG_LOW = "Low";
            String TAG_OPEN = "Open";
            String TAG_VOLUME = "Volume";
            String TAG_CLOSE = "Close";

            JSONObject history = a;
            symbol = history.getString("Symbol");
            companyName = history.getString("Name");
            String timeStamp = history.getString(TAG_TIMESTAMP);
            String localTimeStamp = history.getString(TAG_TIMESTAMP);
            double price = 0.0;
            double change = history.getDouble(TAG_CHANGE);
            double changePercent = history.getDouble(TAG_CHANGEPERCENT);
            double high = history.getDouble(TAG_VOLUME);
            double low = history.getDouble(TAG_VOLUME);
            double open = history.getDouble(TAG_VOLUME);
            int volume = history.getInt(TAG_VOLUME);
            double close = history.getDouble(TAG_CLOSE);

            StockData stock = new StockData(timeStamp, localTimeStamp, price,
                    change, changePercent, high, low, open, volume, close);
            stockData = stock;

        } catch (JSONException e) {
            Log.d("MARKET_", e.getMessage() + " in market: " + symbol);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
