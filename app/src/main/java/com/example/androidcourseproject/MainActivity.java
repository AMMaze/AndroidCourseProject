package com.example.androidcourseproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcourseproject.fragments.WeatherCard;
import com.example.androidcourseproject.grammar.RussianLangTools;
import com.example.androidcourseproject.model.GeoData;

public class MainActivity extends BaseActivity implements View.OnClickListener {
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
        ft.commit();


        Log.i("lifeCycle", getString(R.string.create));

        Button changeCityBtn = findViewById(R.id.changeCityBtn);
        changeCityBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeCityBtn:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.cityListBtn:
//                Intent intent = new Intent(this, )
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
                    city = RussianLangTools.getPredicativeCaseForCity(country, receivedData.getCity());
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
