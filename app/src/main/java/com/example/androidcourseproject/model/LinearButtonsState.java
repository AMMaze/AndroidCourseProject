package com.example.androidcourseproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidcourseproject.fragments.LinearButtons;

public class LinearButtonsState implements Parcelable {
    public final static String BUTTONS_SAVED_TAG = "saved";



    private int layout;
    private int[] btnIDs;
    private LinearButtons.SerializableConsumer[] handlers;

    public LinearButtonsState(int layout, LinearButtons.SerializableConsumer[] handlers, int[] btnIDs) {
        this.layout = layout;
        this.btnIDs = btnIDs;
        this.handlers = handlers;
    }

    public int getLayout() {
        return layout;
    }

    public int[] getBtnIDs() {
        return btnIDs;
    }

    public LinearButtons.SerializableConsumer[] getHandlers() {
        return handlers;
    }

    public LinearButtonsState(Parcel parcel) {
        layout = parcel.readInt();
        parcel.readIntArray(btnIDs);
        handlers = (LinearButtons.SerializableConsumer[]) parcel.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(layout);
        parcel.writeIntArray(btnIDs);
        parcel.writeSerializable(handlers);
    }

    public static final Parcelable.Creator<LinearButtonsState> CREATOR = new Parcelable.Creator<LinearButtonsState>() {

        @Override
        public LinearButtonsState createFromParcel(Parcel source) {
            return new LinearButtonsState(source);
        }

        @Override
        public LinearButtonsState[] newArray(int size) {
            return new LinearButtonsState[size];
        }
    };
}
