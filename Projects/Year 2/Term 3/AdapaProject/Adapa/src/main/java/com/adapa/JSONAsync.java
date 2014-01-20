package com.adapa;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by CHURLZ on 2013-11-20.
 */
class JSONAsync extends AsyncTask<String, String, JSONObject> implements JSONListener {

    String url;
    Stock stock;
    JSONListener mJSONListener;

    public JSONAsync(String url, Stock stock) {
        this.stock = stock;
        this.url = url + stock.symbol;
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
            if (!stock.collectData(json)) {
                onJSONAsyncComplete(false);
            } else {
                onJSONAsyncComplete(true);
            }
        } catch (Exception e) {
            onJSONAsyncComplete(false);
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
