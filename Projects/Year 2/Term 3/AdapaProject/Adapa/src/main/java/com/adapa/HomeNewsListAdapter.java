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
public class HomeNewsListAdapter extends ArrayAdapter<News> {

    ArrayList<News> list;

    public HomeNewsListAdapter(Context context, int textViewResourceId, ArrayList<News> objects) {
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
            v = inflater.inflate(R.layout.home_list_item, parent, false);
        }

        News a = list.get(position);
        if (position % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            v.setBackgroundColor(Color.parseColor("#ECEEEE"));
        }

        if (a != null) {
            TextView title = (TextView) v.findViewById(R.id.textView);

            if (title != null) {
                title.setTextColor(Color.parseColor(MainActivity.TEXT_COLOR_DARK));
                title.setText(a.title.replaceAll("&#39;", "'"));
            }
        }
        // the view must be returned to our activity
        return v;
    }
}