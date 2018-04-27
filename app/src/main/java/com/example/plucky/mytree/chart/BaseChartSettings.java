
package com.example.plucky.mytree.chart;


import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

 class BaseChartSettings {
    private Chart mChart;
    private PairData[] dataObjects;
    private Pie_Data[] PieDataObjects;
    private List<Entry> entries = new ArrayList<>();
    private List<BarEntry> mBarEntries = new ArrayList<>();
    private List<PieEntry> mPieEntries = new ArrayList<>();

    BaseChartSettings(Chart chart, PairData[] dataObjects,int i) {
        mChart = chart;
        this.dataObjects = dataObjects;
        if (i==1) {

            for (PairData data : dataObjects) {
                entries.add(new Entry(data.getX(), data.getY()));
            }
        }
        else {
            for (PairData data : dataObjects) {
                mBarEntries.add(new BarEntry(data.getValueX(), data.getValueY()));
            }
        }

    }

     BaseChartSettings(PieChart chart, Pie_Data[] dataObjects) {
         mChart = chart;
         PieDataObjects = dataObjects;
         for (Pie_Data data :dataObjects){
             mPieEntries.add(new PieEntry(data.getValue(),data.getLabel()));
         }
     }


     List<BarEntry> getBarEntries() {
         return mBarEntries;
     }

     List<PieEntry> getPieEntries() {
         return mPieEntries;
     }

     List<Entry> getEntries() {
         return entries;
     }
 }

