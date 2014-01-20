package com.adapa;

/**
 * Created by CHURLZ on 2013-12-20.
 */

import org.json.JSONException;
import org.stockchart.StockChartView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup.LayoutParams;
/**
 * @author alexv
 *
 */
public abstract class StockChartDaddy extends ActionBarActivity
{
    private StockChartView fStockChartView;

    private final String TAG = "StockChartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        fStockChartView = new StockChartView(this);

        if(null != savedInstanceState)
        {
            restoreChartFromState(savedInstanceState);
        }
        else
        {
            initChart();
        }

        setContentView(fStockChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

    public StockChartView getStockChartView()
    {
        return fStockChartView;
    }

    protected abstract void initChart();
    protected abstract void restoreChart();

    protected void onSaveInstanceState (Bundle outState)
    {
        try
        {
            outState.putString(TAG, fStockChartView.save());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void restoreChartFromState(Bundle state)
    {
        String s = state.getString(TAG);

        try
        {
            fStockChartView.load(s);

            restoreChart();
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
