package com.adapa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-06-13.
 */
public class NewsListAdapter extends ArrayAdapter<News> {

    ArrayList<News> list;

    public NewsListAdapter(Context context, int textViewResourceId, ArrayList<News> objects) {
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
    public News getItem(int i) {
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
            v = inflater.inflate(R.layout.news_list_item, parent, false);
        }

        News a = list.get(position);
        if (position % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            v.setBackgroundColor(Color.parseColor("#ECEEEE"));
        }

        //TODO:Correct nullpointerexceptions
        if (a != null) {
            TextView title = (TextView) v.findViewById(R.id.textView);
            TextView body = (TextView) v.findViewById(R.id.textView2);
            TextView date = (TextView) v.findViewById(R.id.textView3);

            if (title != null) {
                title.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARKER));
                title.setText(a.title.replaceAll("&#39;", "'"));
            }
            if (body != null) {
                body.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARK));
                if (a.body.length() > 130) {
                    body.setText(a.body.substring(0, 150) + "...");
                } else {
                    body.setText("");
                }
            }
            if (date != null) {
                if (a.date.length() > 17) {
                    date.setText(a.date.substring(0, 17));
                } else {
                    date.setText("");
                }
            }

        }
        // the view must be returned to our activity
        return v;
    }
}