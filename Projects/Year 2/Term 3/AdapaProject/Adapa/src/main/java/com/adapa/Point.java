package com.adapa;

import java.util.Date;

/**
 * Created by CHURLZ on 2013-12-17.
 */
public class Point {

    public double o, c, h, l, v, pr;
    public Date dt;

    public Point() {
    }

    public Point(double op, double hi, double lo, double cl, double vo, double pri, Date dat) {
        o = op;
        c = cl;
        h = hi;
        l = lo;
        v = vo;
        dt = dat;
        pr = pri;
    }

    public double getOpen() {
        return o;
    }

    public double getClose() {
        return c;
    }

    public double getHigh() {
        return h;
    }

    public double getLow() {
        return l;
    }

    public double getVolume() {
        return v;
    }

    public Date getDate() {
        return dt;
    }

    public double getPrice() {return pr;}


}
