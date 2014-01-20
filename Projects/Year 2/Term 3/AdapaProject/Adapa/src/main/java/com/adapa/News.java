package com.adapa;

/**
 * Created by CHURLZ on 2013-11-19.
 */
public class News {

    String title;
    String body;
    String link;
    String date;
    boolean show;

    public News(String t, String b, String l, String d, boolean s) {
        title = t;
        body = b;
        link = l;
        date = d;
        show = s;
    }
}
