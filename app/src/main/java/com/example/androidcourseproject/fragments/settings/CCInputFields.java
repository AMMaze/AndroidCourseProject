package com.example.androidcourseproject.fragments.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.fragments.FragmentLifeCycleTracker;

public class CCInputFields extends FragmentLifeCycleTracker {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_fields, container, false);
    }
}
