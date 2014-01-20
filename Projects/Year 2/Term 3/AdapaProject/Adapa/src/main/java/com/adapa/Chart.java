package com.adapa;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import org.stockchart.StockChartView;
import org.stockchart.core.Area;
import org.stockchart.series.BarSeries;
import org.stockchart.series.LinearSeries;
import org.stockchart.series.StockSeries;

import java.util.ArrayList;

/**
 * Created by CHURLZ on 2013-12-20.
 */
public class Chart {

    private StockSeries fBarSeries;
    private StockSeries fCandleSeries;
    private LinearSeries fLineSeries;
    private BarSeries fVolumeSeries;

    private StockChartView[] fViews;
    Context context;

    public Chart(Context c) {
        context = c;
        setupView(c);
        runAll();
    }

    public void setupView(Context context) {
        fViews = new StockChartView[]{new StockChartView(context),
                new StockChartView(context),
                new StockChartView(context)};
    }

    public StockChartView[] getViews() {
        return fViews;
    }

    public void runAll() {
        populateChart();
        prepareCharts();
        invalidateCharts();
    }

    private void populateChart() {
        fCandleSeries = new StockSeries();
        fVolumeSeries = new BarSeries();
        fLineSeries = new LinearSeries();
        fBarSeries = new StockSeries();

        ArrayList<StockData> list = MainActivity.activeStock.stockHistory;
        for (int i = 0; i < list.size(); i++) {
            Point p = new Point();
            StockData sd = list.get(i);
            double o, h, l, c;
            o = sd.getOpen();
            h = sd.getHigh();
            l = sd.getLow();
            c = sd.getPrice();

            fCandleSeries.addPoint(o, h, l, c);
            fVolumeSeries.addPoint(0.0, p.v);
            fBarSeries.addPoint(o, h, l, c);
            fLineSeries.addPoint(c);
        }
    }

    protected void initChart(StockChartView v, int configuration) {
       /* fCandleSeries = new StockSeries();
        fCandleSeries.setName(identifier);
        fCandleSeries.setYAxisSide(Axis.Side.RIGHT);*/
        //fVolumeSeries = new BarSeries();
        //fVolumeSeries.setName("volume");
        //fVolumeSeries.setYAxisSide(Side.LEFT);

        Area a = v.addArea();
        switch (configuration) {
            case 0:
                // fCandleSeries = new StockSeries();
                fCandleSeries.setViewType(StockSeries.ViewType.CANDLESTICK);
                fCandleSeries.getAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_GREEN));
                fCandleSeries.getFallAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_RED));
                a.getSeries().add(fCandleSeries);

            break;
            case 1:
                //fBarSeries = new StockSeries();
                fBarSeries.setViewType(StockSeries.ViewType.BAR);
                a.getSeries().add(fBarSeries);

            break;
            case 2:
                //fLineSeries = new LinearSeries();
                a.getSeries().add(fLineSeries);

            break;
        }
    }

    public void invalidateCharts() {
        for (View v : fViews)
            v.invalidate();
    }

    private void prepareCharts() {
        for (int i = 0; i < fViews.length; i++) {
            initChart(fViews[i], i);
        }
    }
}