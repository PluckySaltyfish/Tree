package com.example.plucky.mytree;


import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class SemiAnnualChart extends BaseChartSettings {
    private LineChart mLineChart;
    private PairData[] dataObjects;
    private LineDataSet mDataSet;
    private List<Entry> entries = new ArrayList<>();

    public SemiAnnualChart(LineChart lineChart, PairData[] dataObjects) {
        super(lineChart, dataObjects);
        mLineChart = lineChart;
        this.dataObjects = dataObjects;
        entries = this.getEntries();
        xAxisSettings();
    }

    public void xAxisSettings(){
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setValueFormatter(new MyFormatter(3).getSemiAnnualFormatter());
    }

    public void setData(){
        mDataSet = new LineDataSet(entries, "近6个月内任务完成率（%）"); // add entries to dataset
        mDataSet.setColor(Color.rgb(110,203,225));
        mDataSet.setValueTextColor(Color.BLACK); // styling, ...
        mDataSet.setDrawCircles(false);
        mDataSet.setCubicIntensity(0.2f);
        mDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mDataSet.setDrawFilled(true);
        mDataSet.setFillColor(Color.rgb(110,203,225));
    }
    public void drawChart(){
        LineData lineData = new LineData(mDataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
    }
}
