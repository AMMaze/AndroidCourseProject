package com.example.androidcourseproject.fragments.history;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.adapters.DataSourceBuilder;
import com.example.androidcourseproject.adapters.WeatherHistoryAdapter;
import com.example.androidcourseproject.fragments.FragmentLifeCycleTracker;

public class WeatherHistory extends FragmentLifeCycleTracker {

    private String[] data ={"one", "two", "three"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_history, container, false);
        Activity activity = getActivity();
        RecyclerView recyclerView = (RecyclerView) view;
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        WeatherHistoryAdapter adapter = new WeatherHistoryAdapter(builder.build());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
