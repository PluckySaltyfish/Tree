package com.example.plucky.mytree.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


class MyFormatter {
    private IAxisValueFormatter WeeklyFormatter, MonthlyFormatter;
    MyFormatter(final int today) {
        final String[] quarters = new String[] { "Mon", "Tues", "Wed", "Thurs","Fri","Sat","Sun" };

        WeeklyFormatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(((int) value)+today)%quarters.length];
            }
        };
        MonthlyFormatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String[] quarters0 = new String[31];
                return " ";
            }
        };
    }

    IAxisValueFormatter getWeeklyFormatter() {
        return WeeklyFormatter;
    }

    public IAxisValueFormatter getMonthlyFormatter() {
        return MonthlyFormatter;
    }


}
