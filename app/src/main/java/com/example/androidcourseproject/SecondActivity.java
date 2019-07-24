package com.example.androidcourseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.androidcourseproject.fragments.CitiesList;
import com.example.androidcourseproject.fragments.LinearButtons;
import com.example.androidcourseproject.fragments.settings.CCInputFields;
import com.example.androidcourseproject.model.GeoData;

public class SecondActivity extends BaseActivity {
    private EditText etCountry, etCity;

    public EditText getEtCountry() {
        return etCountry;
    }

    public EditText getEtCity() {
        return etCity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawSecondLayout();
    }

    private void drawSecondLayout() {
        setContentView(R.layout.activity_second);

        CCInputFields inputFields = new CCInputFields();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.textInputFrame, inputFields);

        LinearButtons submitBtn = LinearButtons.newInstance(R.layout.fragment_submit_button,
                new LinearButtons.SerializableConsumer[] {SecondActivity::onSubmitClick},  new int[] {R.id.submit});
        ft.add(R.id.submitBtnFrame, submitBtn);
        ft.commit();
    }

//    @Override
//    public void onClick(View v) {
//        if (etCountry.getText().toString().equals("")){
//            Toast.makeText(this, "Введите страну", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (etCity.getText().toString().equals("")){
//            Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        switch (v.getId()) {
//            case R.id.submit:
//                Intent intent = new Intent();
//                intent.putExtra(RETURN_TAG_FROM_INPUT, new GeoData(etCountry.getText().toString(), etCity.getText().toString()));
//                setResult(RESULT_OK, intent);
//                finish();
//            default:
//                break;
//        }
//    }

    public static void onSubmitClick(View v) {
        Activity activity = LinearButtons.getActivityFromView(v);
        if (!(activity instanceof SecondActivity))
            return;
        SecondActivity secondActivity = (SecondActivity) activity;
        EditText etCountry = secondActivity.findViewById(R.id.countryInput);
        EditText etCity = secondActivity.findViewById(R.id.cityInput);
        if (etCountry.getText().toString().equals("")){
            Toast.makeText(secondActivity, "Введите страну", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etCity.getText().toString().equals("")){
            Toast.makeText(secondActivity, "Введите город", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()) {
            case R.id.submit:
                Intent intent = new Intent();
                intent.putExtra(RETURN_TAG_FROM_INPUT, new GeoData(etCountry.getText().toString(), etCity.getText().toString()));
                secondActivity.setResult(RESULT_OK, intent);
                secondActivity.finish();
            default:
                break;
        }
    }

}
