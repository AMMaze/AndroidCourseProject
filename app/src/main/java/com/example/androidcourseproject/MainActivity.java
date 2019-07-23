package com.example.androidcourseproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.androidcourseproject.fragments.LinearButtons;
import com.example.androidcourseproject.fragments.WeatherCard;
import com.example.androidcourseproject.grammar.RussianLangTools;
import com.example.androidcourseproject.model.GeoData;

import java.util.Locale;

public class MainActivity extends BaseActivity {
    public static final int INPUT_FORM_CODE = 1;
    public static final int CITIES_LIST_CODE = 2;


    private String country, city;
    WeatherCard weatherCard;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (country == null && city == null) {
            country = getString(R.string.defaultCountry);
            city = getString(R.string.defaultCity);
        }

        drawMainLayout();

        Log.i("lifeCycle", getString(R.string.create));

    }

    private void drawMainLayout() {
        setContentView(R.layout.activity_main);

        weatherCard = WeatherCard.newInstance(new GeoData(country, city));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.card_frame, weatherCard);

        LinearButtons mainButtonsFragment = LinearButtons.newInstance(R.layout.fragment_main_buttons,
                new LinearButtons.SerializableConsumer[] {MainActivity::onChangeCityBtnClick, MainActivity::onCityListBtn}, new int[]{R.id.changeCityBtn, R.id.cityListBtn});

        ft.add(R.id.mainBtns, mainButtonsFragment);

        ft.commit();

        ImageView iv = findViewById(R.id.mainLayoutBG);
        iv.setImageDrawable(getDrawable(R.drawable.leaves));
    }

    /**
     * Handler for clicking on change city button event
     * @param v
     */
    private static void onChangeCityBtnClick(View v) {
        Activity activity = LinearButtons.getActivityFromView(v);
        if (activity == null)
            return;
        Intent intent = new Intent(activity, SecondActivity.class);
        activity.startActivityForResult(intent, INPUT_FORM_CODE);
    }

    /**
     * Handler for clicking on show cities list button event
     * @param v
     */
    private static void onCityListBtn(View v) {
        Activity activity = LinearButtons.getActivityFromView(v);
        if (!(activity instanceof MainActivity))
            return;
        MainActivity mainActivity = (MainActivity) activity;
        Intent intentList = new Intent(activity, CitiesListActivity.class);
        intentList.putExtra(CitiesListActivity.GEO_TAG, new GeoData(mainActivity.getCountry(), mainActivity.getCity()));
        mainActivity.startActivityForResult(intentList, CITIES_LIST_CODE);
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
