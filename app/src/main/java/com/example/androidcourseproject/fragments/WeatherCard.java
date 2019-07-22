package com.example.androidcourseproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.model.GeoData;

public class WeatherCard extends FragmentLifeCycleTracker {
    public static final String INPUT_GEODATA_TAG = "geodata";

    private static final String SAVED_DATA_TAG = "saved";

    private String country="", city="";
    private TextView tvGeo;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public static WeatherCard newInstance(GeoData data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INPUT_GEODATA_TAG, data);

        WeatherCard weatherCard = new WeatherCard();
        weatherCard.setArguments(bundle);

        return weatherCard;
    }

    private void readGeoData(Bundle bundle) {
        if (bundle != null) {
            GeoData data = bundle.getParcelable(INPUT_GEODATA_TAG);
            country = data.getCountry();
            city = data.getCity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_card, container, false);
        tvGeo = view.findViewById(R.id.base);
        readGeoData(getArguments());

        tvGeo.setText(getString(R.string.weatherIn, city, country));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        readGeoData(getArguments());
        tvGeo.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    public void onStart() {
        super.onStart();
        readGeoData(getArguments());
        tvGeo.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_DATA_TAG, new GeoData(country, city));
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        GeoData parcel = savedInstanceState == null ? null : savedInstanceState.getParcelable(SAVED_DATA_TAG);
        if (parcel != null) {
            country = parcel.getCountry();
            city = parcel.getCity();
        }
    }
}
