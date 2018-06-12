package com.chrisdev.android.myapplication;

public class Counter {
    private int value = 0;

    public int currentValue() {
        return value;
    }

    public void increment() {
        value++;
    }

    public void reset() {
        value = 0;
    }
}
