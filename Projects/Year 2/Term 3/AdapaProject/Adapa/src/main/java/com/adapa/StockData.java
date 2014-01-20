package com.adapa;

/**
 * Created by Gimpleton on 2013-11-20.
 */
public class StockData {



    private String timeStamp;
    private String localTimeStamp;
    private double price;
    private double change;
    private double changePercent;
    private double high;
    private double low;
    private double open;
    private double close;
    private int volume;

    public StockData (String t, String lT, double p, double c, double cP,
                      double h, double l, double o, int v, double cL) {
        timeStamp = t;
        localTimeStamp = lT;
        price = p;
        change = c;
        changePercent = cP;
        high = h;
        low = l;
        open = o;
        volume = v;
        close = cL;
    }

    public String getTimeStamp () {
        return timeStamp;
    }
    public String getLocalTimeStamp () {
        return localTimeStamp;
    }
    public double getPrice () {
        return price;
    }
    public double getChange () {
        return change;
    }
    public double getChangePercent () {
        return changePercent;
    }
    public double getHigh () {
        return high;
    }
    public double getLow () {
        return low;
    }
    public double getOpen () {
        return open;
    }

    public int getVolume() {return volume;}

    public double getClose() {return close;}

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setLocalTimeStamp(String localTimeStamp) {
        this.localTimeStamp = localTimeStamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setOpen(double open) {
        this.open = open;
    }
}
