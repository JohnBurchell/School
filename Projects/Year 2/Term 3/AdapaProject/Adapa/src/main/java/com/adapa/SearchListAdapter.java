package com.adapa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-06-13.
 */
public class SearchListAdapter extends ArrayAdapter<Stock> {

    ArrayList<Stock> list;

    public SearchListAdapter(Context context, int textViewResourceId, ArrayList<Stock> objects) {
        super(context, textViewResourceId, objects);
        this.list = objects;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Stock getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*
         * we are overriding the getView method here - this is what defines how each
         * list item will look.
         */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.search_list_item, parent, false);
        }

        Stock a = list.get(position);
        /*if(position %2==0){
            v.setBackgroundColor(Color.parseColor("#0f0e0d"));
        }else{
            v.setBackgroundColor(Color.parseColor("#161613"));
        }*/

        if (a != null) {
            TextView market = (TextView) v.findViewById(R.id.textView);
            TextView name = (TextView) v.findViewById(R.id.textView1);
            TextView company = (TextView) v.findViewById(R.id.textView2);

            if (market != null) {
                market.setText(a.marketName);
                if (a.marketName.equals(MainActivity.TAG_NASDAQ)){
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_NASDAQ));
                }else if(a.marketName.equals(MainActivity.TAG_DAX)){
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DAX));
                }else if(a.marketName.equals(MainActivity.TAG_LSE)){
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_LSE));
                }
            }
            if (name != null) {
                name.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARKER));
                name.setText(a.symbol);
            }
            if (company != null) {
                company.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARK));
                company.setText(a.companyName);
            }
        }
        // the view must be returned to our activity
        return v;
    }
}