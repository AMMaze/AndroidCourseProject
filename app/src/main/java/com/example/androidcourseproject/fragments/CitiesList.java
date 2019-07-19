package com.example.androidcourseproject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.androidcourseproject.BaseActivity;
import com.example.androidcourseproject.CitiesListActivity;
import com.example.androidcourseproject.MainActivity;
import com.example.androidcourseproject.R;
import com.example.androidcourseproject.model.GeoData;

public class CitiesList extends ListFragment {
    private boolean isExistsWeatherCard;
    private int currentPosition = 0;
    private ListView lv;
    private String country, city;

    public void setCountry(@NonNull String country) {
        this.country = country;
    }

    public void setGeodata(@NonNull GeoData data) {
        this.country = data.getCountry();
        this.city = data.getCity();
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Activity parent = getActivity();
//        if (parent instanceof CitiesListActivity) {
//            setGeodata(((CitiesListActivity) parent).getGeoData());
//        }

//        lv = getActivity().findViewById(R.id.list);

        lv = getListView();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cities_array,
                android.R.layout.simple_list_item_activated_1);

        setListAdapter(adapter);

        View detailsFrame = getActivity().findViewById(R.id.weatherFrame);
        isExistsWeatherCard = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
            city = (String) lv.getItemAtPosition(currentPosition);
        }

        if (isExistsWeatherCard){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showWeatherCard();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentCity", currentPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        currentPosition = position;
        city = (String) lv.getItemAtPosition(currentPosition);
        showWeatherCard();
    }

    private void showWeatherCard() {
        if (isExistsWeatherCard) {
            getListView().setItemChecked(currentPosition, true);

            WeatherCard detail = (WeatherCard)
                    getFragmentManager().findFragmentById(R.id.weatherFrame);
            if (detail == null || detail.getCity().equals(city)) {
                detail = WeatherCard.newInstance(new GeoData(country, city));

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.weatherFrame, detail);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            CitiesListActivity listActivity = (CitiesListActivity) getActivity();
            Intent intent = new Intent();
            intent.putExtra(CitiesListActivity.RETURN_TAG_FROM_INPUT, new GeoData(country, city));
            listActivity.setResult(CitiesListActivity.RESULT_OK, intent);
            listActivity.finish();
        }
    }

}
