package com.adapa;

/**
 * Created by CHURLZ on 2013-11-20.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    ArrayList<DrawerItem> list;

    public DrawerListAdapter(Context context, int textViewResourceId, ArrayList<DrawerItem> objects) {
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
    public DrawerItem getItem(int i) {
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
            v = inflater.inflate(R.layout.drawer_list_item, parent, false);
        }

        DrawerItem a = list.get(position);
        if(position %2==0){
            v.setBackgroundColor(Color.parseColor("#0f0e0d"));
        }else{
            v.setBackgroundColor(Color.parseColor("#161613"));
        }

        if (a != null) {
            ImageView icon = (ImageView) v.findViewById(R.id.imageView);
            TextView name = (TextView) v.findViewById(R.id.textView);

            if (name != null) {
                name.setText(a.name);
            }
            if (icon != null) {
                icon.setImageResource(a.icon);

            }
        }
        // the view must be returned to our activity
        return v;
    }
}