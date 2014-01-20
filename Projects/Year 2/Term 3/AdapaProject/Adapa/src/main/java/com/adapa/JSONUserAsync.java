package com.adapa;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CHURLZ on 2013-11-25.
 */
class JSONUserAsync extends AsyncTask<String, String, JSONObject> implements JSONListener {
    String url;
    JSONListener mJSONListener;

    public JSONUserAsync(String url) {
        this.url = url;
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
            JSONArray stockdata = json.getJSONArray("Symbols");
            for (int i = 0; i < stockdata.length(); i++) {
                JSONObject data = (JSONObject)stockdata.get(i);
                String symbol = data.getString("Symbol").toString();
                //TODO: FETCH MARKET VAL ALSO
                MainActivity.getStockAndAdd(symbol);
            }
            onJSONAsyncComplete(true);
        } catch (JSONException e) {
            onJSONAsyncComplete(false);
            Log.d("JSON_", e.getMessage());
            e.printStackTrace();
        }
    }

    public void setOnJSONAsyncComplete(JSONListener listener) {
        mJSONListener = listener;
    }

    @Override
    public void onJSONAsyncComplete(Boolean validData) {
        mJSONListener.onJSONAsyncComplete(validData);
    }
}