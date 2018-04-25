package com.example.plucky.mytree.chart;


public class PairData {
    private int ValueX,ValueY;

    public PairData(int valueX, int valueY) {
        ValueX = valueX;
        ValueY = valueY;
    }

    public int getValueX() {
        return ValueX;
    }

    public int getValueY() {
        return ValueY;
    }

    public void setValueX(int valueX) {
        ValueX = valueX;
    }

    public void setValueY(int valueY) {
        ValueY = valueY;
    }
}
