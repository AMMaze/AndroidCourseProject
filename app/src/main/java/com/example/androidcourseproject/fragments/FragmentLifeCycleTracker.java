package com.example.androidcourseproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidcourseproject.R;

public abstract class FragmentLifeCycleTracker extends Fragment {
    private String observedClass;

    public FragmentLifeCycleTracker() {
        observedClass = getClass().getSimpleName();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(observedClass, getString(R.string.attach));
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(observedClass, getString(R.string.start));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(observedClass, getString(R.string.resume));
    }

    @Override
    public void onPause() {
        Log.i(observedClass, getString(R.string.pause));
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(observedClass, getString(R.string.stop));
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(observedClass, getString(R.string.destroy));
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(observedClass, getString(R.string.create));
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(observedClass, getString(R.string.create_view));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(observedClass, getString(R.string.activity_created));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.i(observedClass, getString(R.string.destroy_view));
        super.onDestroyView();
    }
}
