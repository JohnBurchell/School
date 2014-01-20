package com.adapa;

/**
 * Created by CHURLZ on 2013-11-21.
 */

import org.stockchart.StockChartActivity;
import org.stockchart.StockChartView;
import org.stockchart.core.Area;
import org.stockchart.core.Axis;
import org.stockchart.core.Axis.Side;
import org.stockchart.series.BarSeries;
import org.stockchart.series.LinearSeries;
import org.stockchart.series.StockSeries;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SimpleStockChartActivity extends StockChartDaddy {
    private StockSeries fBarSeries;
    private StockSeries fCandleSeries;
    private LinearSeries fLineSeries;
    private double o, h, l, c;

    int configuration = 2;
    boolean loaded = false;

    private static final int POINTS_COUNT = MainActivity.activeStock.stockList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        populateChart();
        this.getStockChartView().invalidate();
    }

    private void populateChart() {
        ArrayList<StockData> list = MainActivity.activeStock.stockHistory;
        for (StockData stockData : list) {
            o = stockData.getOpen();
            h = stockData.getHigh();
            l = stockData.getLow();
            c = stockData.getPrice();

            fBarSeries.addPoint(o, h, l, c);
            fCandleSeries.addPoint(o, h, l, c);
            fLineSeries.addPoint(c);
            loaded = true;
        }
    }

    @Override
    protected void initChart() {
        fCandleSeries = new StockSeries();
        fBarSeries = new StockSeries();
        fLineSeries = new LinearSeries();
    }

    private void loadCharts() {
        Log.d("CHARTS_", Integer.toString(configuration));

        Area a;
        if (this.getStockChartView().getAreas().isEmpty()) {
            a = this.getStockChartView().addArea();
        } else {
            this.getStockChartView().getAreas().remove(0);
            a = this.getStockChartView().addArea();
        }
        switch (configuration) {
            case 0:
                fCandleSeries.setViewType(StockSeries.ViewType.CANDLESTICK);
                fCandleSeries.setName("candle");
                fCandleSeries.getAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_GREEN));
                fCandleSeries.getFallAppearance().setFillColors(Color.parseColor(MainActivity.CHART_COLOR_RED));
                a.getSeries().add(fCandleSeries);
                a.getSeries().set(0, fCandleSeries);
                break;
            case 1:
                fBarSeries.setViewType(StockSeries.ViewType.BAR);
                fBarSeries.setName("bar");
                a.getSeries().add(fBarSeries);
                a.getSeries().set(0, fBarSeries);
                break;
            case 2:
                fLineSeries.setName("line");
                a.getSeries().add(fLineSeries);
                a.getSeries().set(0, fLineSeries);
                break;
        }

        a.calcAutoValues();
        a.getBottomAxis().setDefaultLabelFormat("Day ");
        a.getBottomAxis().setLinesCount(MainActivity.activeStock.stockHistory.size());
        this.getStockChartView().invalidate();
    }

    @Override
    protected void restoreChart() {
        fCandleSeries = (StockSeries) this.getStockChartView().findSeriesByName("candle");
        fBarSeries = (StockSeries) this.getStockChartView().findSeriesByName("bar");
        fLineSeries = (LinearSeries) this.getStockChartView().findSeriesByName("line");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        String[] temp = getResources().getStringArray(R.array.charts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, temp);
        ActionBar.OnNavigationListener mNavListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                configuration = i;
                if (loaded)
                    loadCharts();
                return false
                        ;
            }
        };
        ActionBar actionBar = getSupportActionBar();
        actionBar.setListNavigationCallbacks(adapter, mNavListener);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setTitle(MainActivity.activeStock.symbol);
        actionBar.setIcon(R.drawable.ic_icon_mystocks);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }
}
