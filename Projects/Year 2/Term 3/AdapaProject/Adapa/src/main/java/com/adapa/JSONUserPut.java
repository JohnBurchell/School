package com.adapa;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Gimpleton on 2013-11-26.
 */

public class JSONUserPut extends AsyncTask<String, Void, JSONObject> implements JSONListener {

    String url;
    JSONObject json;
    JSONListener mJSONListener;
    // constructor
    public JSONUserPut(String u, JSONObject j) {
        url = u;
        json = j;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            HttpPut httpPutRequest = new HttpPut(url);
            StringEntity body = new StringEntity(json.toString(), "utf8");
            httpPutRequest.setEntity(body);
            Log.d("JSON_", json.toString());
            httpPutRequest.setHeader("Accept", "application/json");
            httpPutRequest.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = (HttpResponse) new DefaultHttpClient().execute(httpPutRequest);
            onJSONAsyncComplete(true);
        } catch (UnsupportedEncodingException e) {
            Log.d("JSON_", e.getMessage());
            onJSONAsyncComplete(false);
            e.printStackTrace();
        }catch (IOException e) {
            Log.d("JSON_", e.getMessage());
            onJSONAsyncComplete(false);
            e.printStackTrace();
        }
        return null;
    }

    public void setOnJSONAsyncComplete(JSONListener listener) {
        mJSONListener = listener;
    }

    @Override
    public void onJSONAsyncComplete(Boolean validData) {
        mJSONListener.onJSONAsyncComplete(validData);
    }
}
