package com.example.androidcourseproject;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidcourseproject.fragments.CitiesList;
import com.example.androidcourseproject.model.GeoData;

public class CitiesListActivity extends BaseActivity implements View.OnClickListener {
    public final static String COUNTRY_TAG = "country";
    public final static String GEO_TAG = "geo";

    private String country, city;
    private CitiesList citiesListFragment;

    public GeoData getGeoData() {
        return new GeoData(country, city);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cities_list);
        citiesListFragment = (CitiesList) getSupportFragmentManager().findFragmentById(R.id.citiesListFragment);
        GeoData data = getIntent().getParcelableExtra(GEO_TAG);
        if (data != null) {
            country = data.getCountry();
            city = data.getCity();
            citiesListFragment.setGeodata(data);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.citiesListFragment && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent();
            intent.putExtra(RETURN_TAG_FROM_INPUT, new GeoData(country, citiesListFragment.getCity()));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(RETURN_TAG_FROM_INPUT, new GeoData(country, citiesListFragment.getCity()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
