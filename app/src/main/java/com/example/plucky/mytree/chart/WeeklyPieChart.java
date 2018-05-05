package com.example.plucky.mytree.chart;


import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
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
        int[] colors = {Color.rgb(27,125,135),Color.rgb(152,200,195),
                Color.rgb(254,185,125),Color.rgb(249,135,139)};
        mDataSet.setColors(colors);
        mDataSet.setDrawValues(false);
        mDataSet.setValueTextColor(Color.WHITE);
        mDataSet.setValueTextSize(12);

    }

    public void drawChart(){

        PieData pieData = new PieData(mDataSet);
        mPieChart.setEntryLabelTextSize(14);
        mPieChart.setDrawCenterText(true);
        mPieChart.setHoleRadius(45);
        mPieChart.setData(pieData);
        mPieChart.setUsePercentValues(true);
        mPieChart.setDrawSliceText(false);

        mPieChart.setDescription(null);
        mPieChart.setHoleColor(Color.rgb(30,101,112));
        mPieChart.setCenterText("任务类型");
        mPieChart.setCenterTextColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(1);
        mPieChart.setTransparentCircleAlpha(Color.WHITE);

        Legend legend = mPieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setXEntrySpace(5f);

        mPieChart.invalidate(); // refresh
    }
}
