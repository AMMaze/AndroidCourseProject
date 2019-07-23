package com.example.androidcourseproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidcourseproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class LinearButtons extends FragmentLifeCycleTracker implements View.OnClickListener {

    private int layoutID;
    private ArrayList<Consumer<View>> handlers;
    private int[] btnIDs;

    private LinearButtons (int layoutID, ArrayList<Consumer<View>> handlers,  int[] btnIDs) {
        this.layoutID = layoutID;
        this.handlers = handlers;
        this.btnIDs = btnIDs;
    }

    public static LinearButtons newInstance(int layoutID, ArrayList<Consumer<View>> handlers,  int[] btnIDs) throws ArrayIndexOutOfBoundsException{
        if (handlers.size() != btnIDs.length)
            throw new ArrayIndexOutOfBoundsException();
        return new LinearButtons(layoutID, handlers, btnIDs);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutID, container, false);

        ViewGroup group = (ViewGroup) view;

        for(int i = 0; i < group.getChildCount(); i++) {
            View btn = group.getChildAt(i);
            if (btn instanceof Button)
                btn.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        handlers.get(Arrays.binarySearch(btnIDs, view.getId())).accept(view);
    }
}
