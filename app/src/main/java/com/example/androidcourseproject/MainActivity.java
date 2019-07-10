package com.example.androidcourseproject;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcourseproject.grammar.RussianLangTools;
import com.example.androidcourseproject.model.LocalParcel;

public class MainActivity extends BaseActivity implements View.OnClickListener {
//    private TextView lifeCycleLog;
    private String country, city;
    private TextView base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (country == null && city == null) {
            country = getString(R.string.defaultCountry);
            city = getString(R.string.defaultCity);
        }

        setContentView(R.layout.activity_main);

        base = findViewById(R.id.base);
        base.setText(getString(R.string.weatherIn, city, country));

//        lifeCycleLog = findViewById(R.id.life_cycle);
//        lifeCycleLog.setText(getString(R.string.create));

//        showMessage(this, getString(R.string.create));
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

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                LocalParcel receivedData = (LocalParcel) data.getExtras().get(RETURN_TAG_FROM_INPUT);

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
        outState.putParcelable(RETURN_TAG_FROM_RESTORE, new LocalParcel(country, city));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LocalParcel parcel = savedInstanceState.getParcelable("saved");
        country = parcel.getCountry();
        city = parcel.getCity();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.restart);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.restart));
        Log.i(getString(R.string.lifecycle), getString(R.string.restart));

        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onStart() {
        super.onStart();
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.start);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.start));
        Log.i(getString(R.string.lifecycle), getString(R.string.start));

        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.resume);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.resume));
        Log.i(getString(R.string.lifecycle), getString(R.string.resume));

        base.setText(getString(R.string.weatherIn, city, country));
    }

    @Override
    protected void onPause() {
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.pause);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.pause));
        Log.i(getString(R.string.lifecycle), getString(R.string.pause));
        super.onPause();
    }

    @Override
    protected void onStop() {
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.stop);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.stop));
        Log.i(getString(R.string.lifecycle), getString(R.string.stop));
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.destroy);
//        lifeCycleLog.setText(str);
//        showMessage(this, getString(R.string.destroy));
        Log.i(getString(R.string.lifecycle), getString(R.string.destroy));
        super.onDestroy();
    }

}
