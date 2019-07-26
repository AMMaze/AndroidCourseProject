package com.example.androidcourseproject.adapters;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.fragments.history.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class DataSourceBuilder {
    private List<WeatherData> dataSource;
    private Resources resources;

    public DataSourceBuilder(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public List<WeatherData> build() {
        String[] data = resources.getStringArray(R.array.filler_temps);
        int[] icons = getImageArray();
        for (int i = 0; i < data.length; i++) {
            dataSource.add(new WeatherData(data[i], icons[i]));
        }
        return dataSource;
    }

    private int[] getImageArray() {
        TypedArray icons = resources.obtainTypedArray(R.array.icons);
        int length = icons.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = icons.getResourceId(i, 0);
        }
        return answer;
    }
}
