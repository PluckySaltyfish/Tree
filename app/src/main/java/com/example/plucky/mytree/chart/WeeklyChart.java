package com.example.plucky.mytree.chart;


import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class WeeklyChart extends BaseChartSettings {
    private LineChart mLineChart;
    private PairData[] dataObjects;
    private LineDataSet mDataSet;
    private List<Entry> entries = new ArrayList<>();

    public WeeklyChart(LineChart lineChart, PairData[] dataObjects,int today) {
        super(lineChart, dataObjects);
        mLineChart = lineChart;
        this.dataObjects = dataObjects;
        entries = this.getEntries();
        xAxisSettings(today);
    }

    public void xAxisSettings(int today){
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setValueFormatter(new MyFormatter(today).getWeeklyFormatter());
    }

    public void setData(){
        mDataSet = new LineDataSet(entries, "一周内任务完成率（%）"); // add entries to dataset
        mDataSet.setColor(Color.rgb(110,203,225));
        mDataSet.setValueTextColor(Color.rgb(242,156,56	)); // styling
        mDataSet.setValueTextSize(12f);
        mDataSet.setDrawCircles(false);
        mDataSet.setCubicIntensity(0.2f);
        mDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mDataSet.setDrawFilled(true);
        mDataSet.setFillColor(Color.rgb(110,203,225));

    }
    public void drawChart(){
        LineData lineData = new LineData(mDataSet);

        mLineChart.setDescription(null);
        mLineChart.setData(lineData);
        YAxis rightAxis = mLineChart.getAxisRight();
        mLineChart.setDrawGridBackground(false);
        rightAxis.setDrawLabels(false);

        mLineChart.invalidate(); // refresh
    }
}
