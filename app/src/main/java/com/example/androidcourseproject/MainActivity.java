package com.example.androidcourseproject;

import androidx.annotation.NonNull;
import androidx.core.os.ConfigurationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidcourseproject.fragments.CitiesList;
import com.example.androidcourseproject.fragments.WeatherCard;
import com.example.androidcourseproject.grammar.RussianLangTools;
import com.example.androidcourseproject.model.GeoData;

import java.util.Locale;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final int INPUT_FORM_CODE = 1;
    public static final int CITIES_LIST_CODE = 2;


    private String country, city;
    WeatherCard weatherCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (country == null && city == null) {
            country = getString(R.string.defaultCountry);
            city = getString(R.string.defaultCity);
        }

        setContentView(R.layout.activity_main);

        weatherCard = WeatherCard.newInstance(new GeoData(country, city));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.card_frame, weatherCard);

//        LinearLayout mainBuutons =

        ft.commit();

        Log.i("lifeCycle", getString(R.string.create));

        Button changeCityBtn = findViewById(R.id.changeCityBtn);
        changeCityBtn.setOnClickListener(this);

        Button cityListBtn = findViewById(R.id.cityListBtn);
        cityListBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeCityBtn:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, INPUT_FORM_CODE);
                break;

            case R.id.cityListBtn:
                Intent intentList = new Intent(this, CitiesListActivity.class);
                intentList.putExtra(CitiesListActivity.GEO_TAG, new GeoData(country, city));
                startActivityForResult(intentList, CITIES_LIST_CODE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                GeoData receivedData = (GeoData) data.getExtras().get(RETURN_TAG_FROM_INPUT);

                if (receivedData != null) {
                    country = receivedData.getCountry();
                    city = Locale.getDefault().getLanguage().equals("en") ?
                            receivedData.getCity() : RussianLangTools.getPredicativeCaseForCity(country, receivedData.getCity());
//                    city = receivedData.getCity();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RETURN_TAG_FROM_RESTORE, new GeoData(country, city));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        GeoData parcel = savedInstanceState.getParcelable("saved");
        if (parcel != null) {
            country = parcel.getCountry();
            city = parcel.getCity();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getString(R.string.lifecycle), getString(R.string.restart));
        Bundle bundle = new Bundle();
        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
        weatherCard.setArguments(bundle);

//        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getString(R.string.lifecycle), getString(R.string.start));
        Bundle bundle = new Bundle();
        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
        weatherCard.setArguments(bundle);

//        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getString(R.string.lifecycle), getString(R.string.resume));
        Bundle bundle = new Bundle();
        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
        weatherCard.setArguments(bundle);

//        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onPause() {
        Log.i(getString(R.string.lifecycle), getString(R.string.pause));
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(getString(R.string.lifecycle), getString(R.string.stop));
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(getString(R.string.lifecycle), getString(R.string.destroy));
        super.onDestroy();
    }

}
