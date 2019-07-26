package com.example.androidcourseproject.fragments.history;

import com.example.androidcourseproject.fragments.FragmentLifeCycleTracker;

public class WeatherData extends FragmentLifeCycleTracker {
    private String data;
    private int icon;

    public WeatherData(String data, int icon) {
        this.data = data;
        this.icon = icon;
    }

    public String getData() {
        return data;
    }

    public int getIcon() {
        return icon;
    }
}