package com.adapa;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;


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
 * Created by Gimpleton on 2013-12-18.
 */
public class StockChartActivity extends ActionBarActivity {

    private static ViewPager viewPager;
    private static SimplePagerAdapter pagerAdapter;
    static Chart chart;
    static boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        chart = new Chart(this);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        startStuff(0);
    }

    public static void startStuff(int config) {
        StockChartView[] views = chart.getViews();
        pagerAdapter = new SimplePagerAdapter();
        pagerAdapter.addView(views[config]);
        viewPager.setAdapter(pagerAdapter);
        //pagerAdapter.notifyDataSetChanged();
        loaded = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        String[] temp = getResources().getStringArray(R.array.charts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, temp);
        ActionBar.OnNavigationListener mNavListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                if(loaded)
                    startStuff(i);
                return false
                        ;
            }
        };
        ActionBar actionBar = getSupportActionBar();
        actionBar.setListNavigationCallbacks(adapter, mNavListener);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }
}
