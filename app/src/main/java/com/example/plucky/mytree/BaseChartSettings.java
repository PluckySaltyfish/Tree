
package com.example.plucky.mytree;


import android.graphics.Color;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

 class BaseChartSettings {
    private Chart mChart;
    private PairData[] dataObjects;
    private List<Entry> entries = new ArrayList<>();

    BaseChartSettings(Chart chart, PairData[] dataObjects) {
        mChart = chart;
        this.dataObjects = dataObjects;
        for (PairData data : dataObjects) {
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }
    }




   List<Entry> getEntries() {
         return entries;
     }
 }

