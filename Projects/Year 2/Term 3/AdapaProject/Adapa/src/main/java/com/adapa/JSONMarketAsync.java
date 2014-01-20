package com.adapa;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by CHURLZ on 2013-11-20.
 */
class JSONMarketAsync extends AsyncTask<String, String, JSONObject> implements JSONListener {

    String url;
    Market market;
    JSONListener mJSONListener;

    public JSONMarketAsync(String url, Market m) {
        this.market = m;
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
            if (!market.collectData(json)) {
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
