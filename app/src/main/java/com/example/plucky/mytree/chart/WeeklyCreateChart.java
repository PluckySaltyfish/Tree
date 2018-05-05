package com.example.plucky.mytree.chart;


import android.graphics.Color;
import android.graphics.Typeface;

import com.example.plucky.mytree.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class WeeklyCreateChart extends BaseChartSettings{
    private BarChart mBarChart;
    private PairData[] dataObjects;
    private BarDataSet mDataSet;
    private List<BarEntry> entries = new ArrayList<>();
    private int today;
    public WeeklyCreateChart(BarChart chart, PairData[] dataObjects, int today) {
        super(chart, dataObjects,2);
        mBarChart = chart;
        this.dataObjects = dataObjects;
        entries = this.getBarEntries();
        this.today = today;
        xAxisSettings(today);

    }

    public void xAxisSettings(int today){
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setTextSize(10);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new MyFormatter(today).getWeeklyFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7, false);
    }

    public void setData(){
        mDataSet = new BarDataSet(entries, "一周内新建任务数"); // add entries to dataset

        int []colors = {Color.rgb(29,101,112	),Color.rgb(186,97,90),
                Color.rgb(44,143,153),Color.rgb(167,209,206),
                Color.rgb(255,197,143),Color.rgb(252,156,157	),
                Color.rgb(89,173,173)
        };
        mDataSet.setColors(colors);
        mDataSet.setDrawValues(false);
        mDataSet.setValueTextSize(12);




    }

    public void drawChart(){
        BarData BarData = new BarData(mDataSet);

        mBarChart.setDescription(null);
        mBarChart.setData(BarData);
        BarData.setBarWidth(0.9f);
        YAxis rightAxis = mBarChart.getAxisRight();
        YAxis leftAxis = mBarChart.getAxisLeft();
        rightAxis.setDrawLabels(false);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(true);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextSize(12);
        mBarChart.setDrawGridBackground(false);
        rightAxis.setDrawLabels(false);
        mBarChart.setDrawBorders(true);
        mBarChart.setBorderWidth(3f);

        mBarChart.setBorderColor(Color.rgb(102,102,102));
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.invalidate(); // refresh
    }

    public void setWidth_m(float f){
        mBarChart.getBarData().setBarWidth(f);

        //mBarChart.invalidate(); // refresh
        xAxisSettings(today);

    }

    public void setTypeface(Typeface tf){
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setTypeface(tf);
    }
}
