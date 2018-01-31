package com.example.plucky.mytree;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


class MyFormatter {
    private IAxisValueFormatter WeeklyFormatter,SemiAnnualFormatter;
    MyFormatter(final int today) {
        final String[] quarters = new String[] { "周一", "周二", "周三", "周四","周五","周六","周日" };

        WeeklyFormatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(((int) value)+today)%quarters.length];
            }
        };
        SemiAnnualFormatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return " ";
            }
        };
    }

    IAxisValueFormatter getWeeklyFormatter() {
        return WeeklyFormatter;
    }

    public IAxisValueFormatter getSemiAnnualFormatter() {
        return SemiAnnualFormatter;
    }
}
