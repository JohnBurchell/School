package com.adapa;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.stockchart.StockChartView;
import org.stockchart.core.Area;
import org.stockchart.core.Axis;
import org.stockchart.core.AxisRange;
import org.stockchart.misc.DateTimeScaleValuesProvider;
import org.stockchart.series.BarSeries;
import org.stockchart.series.LinearSeries;
import org.stockchart.series.StockSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gimpleton on 2013-12-17.
 */

public class StockChart extends ActionBarActivity {

    public static final int STEP_DAILY = 0;
    public static final int STEP_HOURLY = 4;
    public static final int STEP_WEEKLY = 1000;
    public static final int STEP_MONTHLY = 2000;
    public static final int STEP_YEARLY = 3000;

    private static final SimpleDateFormat DF = new SimpleDateFormat();

    private ArrayList<Point> fPreparedPoints = new ArrayList<Point>();
    private ArrayList<Point> tempPoints = new ArrayList<Point>();

    private StockChartView[] fViews;

    private StockSeries fBarSeries;
    private StockSeries fCandleSeries;
    private LinearSeries fLineSeries;
    private BarSeries fVolumeSeries;


    private int fStep = STEP_DAILY;
    private Context context;

    public StockChart(Context c) {
        context = c;
        setupView(c);
    }

    public void setupView(Context context) {
        fViews = new StockChartView[]{new StockChartView(context),
                new StockChartView(context),
                new StockChartView(context)};
    }

    public StockChartView[] getViews(Stock stock){
        reloadAllStuff(stock);
        return fViews;
    }
    private void reloadAllStuff(Stock stock) {
        preparePoints(stock);
        prepareCharts();
        reloadPoints();
        invalidateAll();
    }

    private void setupStockChart(StockChartView v, int configuration) {
        v.reset();

        Area primaryArea = v.addArea();

        primaryArea.setAxesVisible(false, false, true, false);
        primaryArea.getRightAxis().setLinesCount(2);


        AxisRange ar = new AxisRange();
        ar.setZoomable(true);
        ar.setMovable(true);
        v.enableGlobalAxisRange(Axis.Side.BOTTOM, ar);

        switch (configuration) {
            case 0: {
                fCandleSeries = new StockSeries();
                fCandleSeries.setViewType(StockSeries.ViewType.CANDLESTICK);
                fCandleSeries.getAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_GREEN));
                fCandleSeries.getFallAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_RED));
                primaryArea.getSeries().add(fCandleSeries);
            }
            break;
            case 1: {
                fBarSeries = new StockSeries();
                fBarSeries.setViewType(StockSeries.ViewType.BAR);
                primaryArea.getSeries().add(fBarSeries);
            }
            break;
            case 2: {
                fLineSeries = new LinearSeries();
                primaryArea.getSeries().add(fLineSeries);
            }
            break;
        }

        if (fVolumeSeries == null) {
            fVolumeSeries = new BarSeries();
        }
        DateTimeScaleValuesProvider svp = new DateTimeScaleValuesProvider(fVolumeSeries);
        primaryArea.getBottomAxis().setScaleValuesProvider(svp);
    }

    public void invalidateAll() {
        for (View v : fViews)
            v.invalidate();
    }

    private void prepareCharts() {
        for (int i = 0; i < fViews.length; i++) {
            setupStockChart(fViews[i], i);
        }
    }

    private void reloadPoints() {
        fBarSeries.getPoints().clear();
        fCandleSeries.getPoints().clear();
        fLineSeries.getPoints().clear();
        fVolumeSeries.getPoints().clear();

        for (Point p : fPreparedPoints) {
            fBarSeries.addPoint(p.o, p.h, p.l, p.c).setID(p.dt);
            fCandleSeries.addPoint(p.o, p.h, p.l, p.c).setID(p.dt);
            fLineSeries.addPoint(p.c).setID(p.dt);
            fVolumeSeries.addPoint(0.0, p.v).setID(p.dt);

        }
    }

    private void createPoints(Stock stock) {
        for (StockData sD : stock.getStockHistory()) {
            double o = sD.getOpen();
            double h = sD.getHigh();
            double l = sD.getLow();
            double c = sD.getClose();
            double v = sD.getVolume();
            double pr = sD.getPrice();
            Date dt = dateParser(sD.getLocalTimeStamp());
            Point p = new Point(pr, o, h, l, c, v, dt);
            tempPoints.add(p);
        }
    }

    private Date dateParser(String D) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            date = format.parse(D);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void preparePoints(Stock stock) {
        Calendar c = Calendar.getInstance();

        fPreparedPoints.clear();
        createPoints(stock);
        fPreparedPoints.add(tempPoints.get(0));

        for (int i = 1; i < tempPoints.size(); i++) {
            Point p1 = fPreparedPoints.get(fPreparedPoints.size() - 1);
            Point p2 = tempPoints.get(i);

            if (checkMerge(p2, p1, fStep, c)) {
                p1.c = p2.c;

                if (p2.h > p1.h)
                    p1.h = p2.h;

                if (p2.l < p1.l)
                    p1.l = p2.l;

                p1.v += p2.v;
                fPreparedPoints.add(p2);
            } else {
                fPreparedPoints.add(p2);
            }
        }
    }

    private static boolean checkMerge(Point p1, Point p2, int step, Calendar c) {
        switch (step) {
            case STEP_HOURLY: {
                c.setTime(p1.getDate());
                int h1 = c.get(Calendar.HOUR_OF_DAY);
                int y1 = c.get(Calendar.YEAR);
                c.setTime(p2.getDate());
                int h2 = c.get(Calendar.HOUR_OF_DAY);
                int y2 = c.get(Calendar.YEAR);

                return h1 == h2 && y1 == y2;
            }
            case STEP_DAILY: {
                c.setTime(p1.getDate());
                int d1 = c.get(Calendar.DAY_OF_YEAR);
                int y1 = c.get(Calendar.YEAR);
                c.setTime(p2.getDate());
                int d2 = c.get(Calendar.DAY_OF_YEAR);
                int y2 = c.get(Calendar.YEAR);

                return d1 == d2 && y1 == y2;
            }
            case STEP_WEEKLY: {
                c.setTime(p1.dt);
                int w1 = c.get(Calendar.WEEK_OF_YEAR);
                int y1 = c.get(Calendar.YEAR);

                c.setTime(p2.getDate());
                int w2 = c.get(Calendar.WEEK_OF_YEAR);
                int y2 = c.get(Calendar.YEAR);

                return w1 == w2 && y1 == y2;
            }
            case STEP_MONTHLY: {
                c.setTime(p1.getDate());

                int m1 = c.get(Calendar.MONTH);
                int y1 = c.get(Calendar.YEAR);
                c.setTime(p2.getDate());
                int m2 = c.get(Calendar.MONTH);
                int y2 = c.get(Calendar.YEAR);

                return m1 == m2 && y1 == y2;
            }
            case STEP_YEARLY: {
                c.setTime(p1.getDate());

                int y1 = c.get(Calendar.YEAR);
                c.setTime(p2.getDate());
                int y2 = c.get(Calendar.YEAR);

                return y1 == y2;
            }
        }
        return false;
    }

}



