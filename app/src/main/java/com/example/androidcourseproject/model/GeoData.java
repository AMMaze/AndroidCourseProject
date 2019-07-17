package com.example.androidcourseproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoData implements Parcelable {
    private String country, city;

    public GeoData(Parcel parcel) {
        String[] array = new String[2];
        parcel.readStringArray(array);
        country = array[0];
        city = array[1];
    }

    public GeoData(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{country, city});
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }


    public static final Parcelable.Creator<GeoData> CREATOR = new Parcelable.Creator<GeoData>() {

        @Override
        public GeoData createFromParcel(Parcel source) {
            return new GeoData(source);
        }

        @Override
        public GeoData[] newArray(int size) {
            return new GeoData[size];
        }
    };
}
