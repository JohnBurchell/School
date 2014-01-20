package com.adapa;

/**
 * Created by CHURLZ on 2013-12-20.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-06-13.
 */
public class MarketsListAdapter extends ArrayAdapter<Market> {

    ArrayList<Market> list;

    public MarketsListAdapter(Context context, int textViewResourceId, ArrayList<Market> objects) {
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
    public Market getItem(int i) {
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
            v = inflater.inflate(R.layout.stocks_list_item, parent, false);
        }

        final Market a = list.get(position);
        if (position % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            v.setBackgroundColor(Color.parseColor("#ECEEEE"));
        }

        if (a != null) {
            TextView market = (TextView) v.findViewById(R.id.textView);
            TextView name = (TextView) v.findViewById(R.id.textView1);
            TextView company = (TextView) v.findViewById(R.id.textView2);
            TextView volume = (TextView) v.findViewById(R.id.textView3);
            TextView change = (TextView) v.findViewById(R.id.textView4);
            ImageView imageView2 = (ImageView) v.findViewById(R.id.imageView2);

            if (market != null) {
                market.setText(a.symbol);
                if (a.symbol.equals(".IXIC")) {
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_NASDAQ));
                    market.setText("NASDAQ");
                } else if (a.symbol.equals("DAX")) {
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DAX));
                } else if (a.symbol.equals("LSE")) {
                    market.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_LSE));
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            if (name != null) {
                name.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARKER));
                name.setText(a.symbol);
            }
            if (company != null) {
                company.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARK));
                company.setText(a.companyName);
            }
            if (volume != null) {
                volume.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARK));
                volume.setText(decimalFormat.format(a.stockData.getChange()));
            }
            if (change != null) {

                Double d = a.stockData.getChangePercent();

                if (d < 0.0) {
                    change.setTextColor(Color.parseColor(MainActivity.CHART_COLOR_RED));
                } else {
                    change.setTextColor(Color.parseColor(MainActivity.CHART_COLOR_GREEN_2));
                }
                change.setText("(%" + decimalFormat.format(d)+")");
            }
        }
        // the view must be returned to our activity
        return v;
    }
}