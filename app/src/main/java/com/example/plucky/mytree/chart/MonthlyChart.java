package com.example.plucky.mytree.chart;


import android.graphics.Color;

import com.example.plucky.mytree.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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


    public void setData(){
        mDataSet = new LineDataSet(entries, "该月内任务完成率（%）"); // add entries to dataset
        mDataSet.setColor(Color.rgb(110,203,225));
        mDataSet.setDrawValues(false);
        //mDataSet.setValueTextColor(Color.rgb(242,156,56	)); // styling
        //mDataSet.setValueTextSize(12f);
        mDataSet.setDrawCircles(false);
        mDataSet.setCubicIntensity(0.2f);
        mDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mDataSet.setDrawFilled(true);
        mDataSet.setFillColor(Color.rgb(110,203,225));

    }
    public void drawChart(){
        LineData lineData = new LineData(mDataSet);

        mLineChart.setDescription(null);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setData(lineData);
        mLineChart.setDrawGridBackground(false);
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
        axis.setTextSize(16);

        mLineChart.invalidate(); // refresh
    }
}

