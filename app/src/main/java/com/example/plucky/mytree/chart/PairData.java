package com.example.plucky.mytree.chart;


public class PairData {
    private int ValueX,ValueY;
    private float x, y;

    public PairData(int valueX, int valueY) {
        ValueX = valueX;
        ValueY = valueY;
    }

    public PairData(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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
