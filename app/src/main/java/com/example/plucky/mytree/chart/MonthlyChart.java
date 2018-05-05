package com.example.plucky.mytree.chart;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.plucky.mytree.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MonthlyChart extends BaseChartSettings {
    private LineChart mLineChart;
    private PairData[] dataObjects;
    private LineDataSet mDataSet;
    private List<Entry> entries = new ArrayList<>();

    public MonthlyChart(LineChart lineChart, PairData[] dataObjects) {
        super(lineChart, dataObjects,1);
        mLineChart = lineChart;
        this.dataObjects = dataObjects;
        entries = this.getEntries();
    }


    public void setData() {
        mDataSet = new LineDataSet(entries, "该月内任务完成率（%）"); // add entries to dataset
        mDataSet.setColor(Color.rgb(44,143,153));
        mDataSet.setDrawValues(false);
        //mDataSet.setValueTextColor(Color.rgb(242,156,56	)); // styling
        //mDataSet.setValueTextSize(12f);
        mDataSet.setDrawCircles(false);
        mDataSet.setCubicIntensity(0.2f);
        mDataSet.setMode(LineDataSet.Mode.LINEAR);
        mDataSet.setDrawFilled(true);

    }

    public void drawChart(){
        LineData lineData = new LineData(mDataSet);

        mLineChart.setDescription(null);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setData(lineData);
        mLineChart.setDrawGridBackground(false);
        Legend l = mLineChart.getLegend();

        XAxis xAxis = mLineChart.getXAxis();
        YAxis rightAxis = mLineChart.getAxisRight();
        YAxis axis = mLineChart.getAxisLeft();


        rightAxis.setDrawLabels(false);
        axis.setDrawLabels(true);
        axis.setDrawAxisLine(true);
        rightAxis.setDrawAxisLine(false);
        xAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        axis.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        axis.setZeroLineColor(Color.BLACK);
        rightAxis.setZeroLineColor(Color.BLACK);

        xAxis.setAxisLineWidth(0.6f);
        xAxis.setDrawLabels(false);
        axis.setAxisLineWidth(0.6f);
        rightAxis.setAxisLineWidth(0.6f);
        mLineChart.setBorderColor(Color.rgb(102,102,102));
        axis.setTextSize(16);
        mLineChart.setDrawBorders(true);
        mLineChart.setBorderWidth(3f);

        mLineChart.invalidate(); // refresh
    }

    public void setGradient(Context context){
        mDataSet.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.line_chart_gradient));
    }
}

