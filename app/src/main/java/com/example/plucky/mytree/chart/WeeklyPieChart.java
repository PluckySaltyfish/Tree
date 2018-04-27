package com.example.plucky.mytree.chart;


import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPieChart extends BaseChartSettings {
    private PieChart mPieChart;
    private PieDataSet mDataSet;
    private Pie_Data[] dataObjects;
    private List<PieEntry> entries = new ArrayList<>();

    public WeeklyPieChart(PieChart chart, Pie_Data[] dataObjects) {
        super(chart, dataObjects);
        mPieChart = chart;
        this.dataObjects = dataObjects;
        entries = this.getPieEntries();
    }


    public void setData(){
        mDataSet = new PieDataSet(entries,"任务类型"); // add entries to dataset
        int[] colors = {Color.rgb(255,249,131),Color.rgb(64,206,255),
                Color.rgb(178,255,89),Color.rgb(255,110,64)};
        mDataSet.setColors(colors);
        mDataSet.setValueTextColor(Color.WHITE);
        mDataSet.setValueTextSize(12);

    }

    public void drawChart(){

        PieData pieData = new PieData(mDataSet);
        mPieChart.setDrawCenterText(false);
        mPieChart.setEntryLabelTextSize(14);
        mPieChart.setData(pieData);
        mPieChart.setDescription(null);

        mPieChart.invalidate(); // refresh
    }
}
