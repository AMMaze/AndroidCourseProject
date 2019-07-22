package com.example.androidcourseproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LinearButtons extends FragmentLifeCycleTracker {

    private LayoutInflater inflater;
    private Intent[] activities;
    private int[] btnIDs;

    private LinearButtons (LayoutInflater inflater, Intent[] activities,  int[] btnIDs) {
        this.inflater = inflater;
        this.activities = activities;
        this.btnIDs = btnIDs;
    }

    public static LinearButtons newInstance(LayoutInflater inflater, Intent[] activities,  int[] btnIDs) throws ArrayIndexOutOfBoundsException{
        if (activities.length != btnIDs.length)
            throw new ArrayIndexOutOfBoundsException();

        return new LinearButtons(inflater, activities, btnIDs);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);



        return view;
    }
}
