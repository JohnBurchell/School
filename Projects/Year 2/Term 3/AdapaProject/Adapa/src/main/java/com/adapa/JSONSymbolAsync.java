package com.adapa;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by CHURLZ on 2013-11-20.
 */
class JSONSymbolAsync extends AsyncTask<String, String, JSONObject> {
    String url;
    String market;
    String marketURL;

    public JSONSymbolAsync(String url, String market, String murl) {
        this.url = url;
        this.market = market;
        this.marketURL = murl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        JSONParser jParser = new JSONParser();
        return jParser.getJSONFromUrl(url);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            JSONArray symbols = json.getJSONArray("rows");
            for (int i = 0; i < symbols.length(); i++) {
                JSONObject object = symbols.getJSONObject(i);
                String symbol = object.getString("id");
                String name = object.getString("value");
                Stock s = new Stock(symbol, name, market, marketURL);
                s.added = false;
                MainActivity.stocks.add(s);
                Log.d("STOCKDATA", s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}