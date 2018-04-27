package com.example.plucky.mytree.chart;


import android.graphics.Color;

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
    public WeeklyCreateChart(BarChart chart, PairData[] dataObjects, int today) {
        super(chart, dataObjects,2);
        mBarChart = chart;
        this.dataObjects = dataObjects;
        entries = this.getBarEntries();
        xAxisSettings(today);

    }

    public void xAxisSettings(int today){
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setTextSize(10);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new MyFormatter(today).getWeeklyFormatter());
    }

    public void setData(){
        mDataSet = new BarDataSet(entries, "一周内新建任务数"); // add entries to dataset

        int []colors = {Color.rgb(71,168,237	),Color.rgb(252,233,96),
                Color.rgb(236,98,55),Color.rgb(71,168,237),
                Color.rgb(252,233,96),Color.rgb(236,98,55),
                Color.rgb(71,168,237	)
        };
        mDataSet.setColors(colors);
        mDataSet.setValueTextSize(12);



    }

    public void drawChart(){
        BarData BarData = new BarData(mDataSet);


        mBarChart.setDescription(null);
        mBarChart.setData(BarData);
        BarData.setBarWidth(0.9f);
        YAxis rightAxis = mBarChart.getAxisRight();
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setTextSize(12);
        rightAxis.setDrawAxisLine(false);
        mBarChart.setDrawGridBackground(false);
        rightAxis.setDrawLabels(false);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.invalidate(); // refresh
    }
}
