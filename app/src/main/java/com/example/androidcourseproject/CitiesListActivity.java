package com.example.androidcourseproject;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidcourseproject.fragments.CitiesList;
import com.example.androidcourseproject.model.GeoData;

public class CitiesListActivity extends BaseActivity implements View.OnClickListener {
    public final static String COUNTRY_TAG = "country";
    public final static String GEO_TAG = "geo";

    private String country, city;
    private CitiesList citiesList;

    public GeoData getGeoData() {
        return new GeoData(country, city);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cities_list);
        citiesList = (CitiesList) getSupportFragmentManager().findFragmentById(R.id.citiesListFragment);
        GeoData data = getIntent().getParcelableExtra(GEO_TAG);
        if (data != null) {
            country = data.getCountry();
            city = data.getCity();
            citiesList.setGeodata(data);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.citiesListFragment && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent();
            intent.putExtra(RETURN_TAG_FROM_INPUT, new GeoData(country, citiesList.getCity()));
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
