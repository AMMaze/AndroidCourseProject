package com.example.androidcourseproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.androidcourseproject.fragments.LinearButtons;
import com.example.androidcourseproject.fragments.main.WeatherCard;
import com.example.androidcourseproject.fragments.settings.CCInputFields;
import com.example.androidcourseproject.grammar.RussianLangTools;
import com.example.androidcourseproject.model.GeoData;

import java.util.Locale;

public class MainActivity extends BaseActivity {
    public static final int INPUT_FORM_CODE = 1;
    public static final int CITIES_LIST_CODE = 2;

//MainActivity
    private String country, city;
    private WeatherCard weatherCard;
    private LinearButtons mainButtonsFragment;
    private ImageView background;
    private Animation fadeOutAndHide, fadeInAndShow;

//    SecondActivity
    private CCInputFields inputfields;
    private LinearButtons submitBtn;

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

    public WeatherCard getWeatherCard() {
        return weatherCard;
    }

    public LinearButtons getMainButtonsFragment() {
        return mainButtonsFragment;
    }

    public ImageView getBackground() {
        return background;
    }

    public Animation getFadeOutAndHide() {
        return fadeOutAndHide;
    }

    public Animation getFadeInAndShow() {
        return fadeInAndShow;
    }

    public CCInputFields getInputfields() {
        return inputfields;
    }

    public LinearButtons getSubmitBtn() {
        return submitBtn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (country == null && city == null) {
            country = getString(R.string.defaultCountry);
            city = getString(R.string.defaultCity);
        }

        drawMainLayout();
        drawSecondLayout();
        Log.i("lifeCycle", getString(R.string.create));

    }

    private void drawMainLayout() {
        setContentView(R.layout.activity_main);

        weatherCard = WeatherCard.newInstance(new GeoData(country, city));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.card_frame, weatherCard);

        mainButtonsFragment = LinearButtons.newInstance(R.layout.fragment_main_buttons,
                new LinearButtons.SerializableConsumer[] {MainActivity::onChangeCityBtnClick, MainActivity::onCityListBtn},
                new int[]{R.id.changeCityBtn, R.id.cityListBtn});

        ft.add(R.id.mainBtns, mainButtonsFragment);

        ft.commit();

        background = findViewById(R.id.mainLayoutBG);
        background.setImageDrawable(getDrawable(R.drawable.leaves));

        fadeOutAndHide = new AlphaAnimation(1, 0);
        fadeOutAndHide.setInterpolator(new AccelerateInterpolator());
        fadeOutAndHide.setDuration(600);
        fadeOutAndHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                background.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeInAndShow = new AlphaAnimation(0, 1);
        fadeInAndShow.setInterpolator(new AccelerateInterpolator());
        fadeInAndShow.setDuration(600);
        fadeInAndShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                background.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private static void hideMainLayout(MainActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.hide(activity.getWeatherCard());
        ft.hide(activity.getMainButtonsFragment());
        ft.commit();
        activity.getBackground().startAnimation(activity.getFadeOutAndHide());
    }

    private static void showMainLayout(MainActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.show(activity.getWeatherCard());
        ft.show(activity.getMainButtonsFragment());
        ft.commit();
        activity.getBackground().startAnimation(activity.getFadeInAndShow());
    }

    /**
     * Handler for clicking on change city button event
     * @param v
     */
    private static void onChangeCityBtnClick(View v) {
        Activity activity = LinearButtons.getActivityFromView(v);
        if (!(activity instanceof MainActivity))
            return;
        hideMainLayout((MainActivity) activity);
        showSecondLayout((MainActivity) activity);
//        Intent intent = new Intent(activity, SecondActivity.class);
//        activity.startActivityForResult(intent, INPUT_FORM_CODE);
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


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            if (data != null && data.getExtras() != null) {
//                GeoData receivedData = (GeoData) data.getExtras().get(RETURN_TAG_FROM_INPUT);
//
//                if (receivedData != null) {
//                    country = receivedData.getCountry();
//                    city = Locale.getDefault().getLanguage().equals("en") ?
//                            receivedData.getCity() : RussianLangTools.getPredicativeCaseForCity(country, receivedData.getCity());
//                }
//            }
//        }
//    }

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
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
//        weatherCard.setArguments(bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getString(R.string.lifecycle), getString(R.string.start));
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
//        weatherCard.setArguments(bundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getString(R.string.lifecycle), getString(R.string.resume));
        Bundle bundle = new Bundle();
        bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
        weatherCard.setArguments(bundle);
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

    /*
        Second activity
     */

    public void drawSecondLayout(){
        inputfields = new CCInputFields();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.textInputFrame, inputfields);
        ft.hide(inputfields);

        submitBtn = LinearButtons.newInstance(R.layout.fragment_submit_button,
                new LinearButtons.SerializableConsumer[] {MainActivity::onSubmitClick},  new int[] {R.id.submit});
        ft.add(R.id.submitBtnFrame, submitBtn);
        ft.hide(submitBtn);
        ft.commit();
    }

    private static void hideSecondLayout(MainActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.hide(activity.getInputfields());
        ft.hide(activity.getSubmitBtn());
        ft.commit();
    }

    private static void showSecondLayout(MainActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.show(activity.getInputfields());
        ft.show(activity.getSubmitBtn());
        ft.commit();
    }

    public static void onSubmitClick(View v) {
        Activity activity = LinearButtons.getActivityFromView(v);
        if (!(activity instanceof MainActivity))
            return;
        MainActivity mainActivity = (MainActivity) activity;
        EditText etCountry = mainActivity.findViewById(R.id.countryInput);
        EditText etCity = mainActivity.findViewById(R.id.cityInput);
        String country = etCountry.getText().toString();
        String  city = etCity.getText().toString();
        if (country.equals("")){
            Toast.makeText(mainActivity, "Введите страну", Toast.LENGTH_SHORT).show();
            return;
        }

        if (city.equals("")){
            Toast.makeText(mainActivity, "Введите город", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()) {
            case R.id.submit:
                mainActivity.setCountry(country);
                mainActivity.setCity(Locale.getDefault().getLanguage().equals("en") ?
                        city : RussianLangTools.getPredicativeCaseForCity(country, city));
                Bundle bundle = new Bundle();
                bundle.putParcelable(WeatherCard.INPUT_GEODATA_TAG, new GeoData(country, city));
                mainActivity.getWeatherCard().setArguments(bundle);
                hideSecondLayout(mainActivity);
                showMainLayout(mainActivity);
            default:
                break;
        }
    }

}
