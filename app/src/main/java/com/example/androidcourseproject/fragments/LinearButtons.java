package com.example.androidcourseproject.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidcourseproject.model.LinearButtonsState;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Consumer;

public class LinearButtons extends FragmentLifeCycleTracker implements View.OnClickListener {

    private int layoutID;
    private SerializableConsumer[] handlers;
    private int[] btnIDs;

    /*
     *  Interface for methods references that should be executed once corresponding button is pressed.
     *  It can't be parcelable since parcelable interface requires at least two methods to be implemented
     *  but functional interface (such as Consumer<>) can have only one method.
     *
     *  One thing is to notice that all methods are to be passed to handlers as SerializableConsumers
     *  should be static, otherwise they will implicitly capture the whole object they're in
     *  and in this case object itself will also have to be serializable.
     */
    public interface SerializableConsumer extends Consumer<View>, Serializable {
    }

    public LinearButtons() {
    }

    private LinearButtons (int layoutID, SerializableConsumer[] handlers, int[] btnIDs) {
        this.layoutID = layoutID;
        this.handlers = handlers;
        this.btnIDs = btnIDs;
    }

    public static LinearButtons newInstance(int layoutID, SerializableConsumer[] handlers, int[] btnIDs) throws ArrayIndexOutOfBoundsException{
        if (handlers.length != btnIDs.length)
            throw new ArrayIndexOutOfBoundsException();
        return new LinearButtons(layoutID, handlers, btnIDs);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        restoreState(savedInstanceState);

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
        handlers[Arrays.binarySearch(btnIDs, view.getId())].accept(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LinearButtonsState.BUTTONS_SAVED_TAG, new LinearButtonsState(layoutID, handlers, btnIDs));
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        restoreState(savedInstanceState);
    }

    private void restoreState(@Nullable Bundle savedInstanceState) {
        LinearButtonsState state = savedInstanceState == null ? null : savedInstanceState.getParcelable(LinearButtonsState.BUTTONS_SAVED_TAG);
        if (state != null) {
            layoutID = state.getLayout();
            btnIDs = state.getBtnIDs();
            this.handlers = state.getHandlers();
        }
    }

    /**
     * This method is intended for use in handler for buttons that are defined in some
     * activity in order to retrieve the wrapping activity class.
     * @param v
     * @return
     */
    public static Activity getActivityFromView (View v) {
        Context context = v.getContext();
        Activity activity = null;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                activity = (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return  activity;
    }

}
