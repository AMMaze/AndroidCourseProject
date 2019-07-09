package com.example.androidcourseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcourseproject.model.LocalParcel;

public class SecondActivity extends BaseActivity implements View.OnClickListener {
    private EditText etCountry, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Button submitBtn = findViewById(R.id.submit);
        submitBtn.setOnClickListener(this);
        etCountry = findViewById(R.id.countryInput);
        etCity = findViewById(R.id.cityInput);
    }

    @Override
    public void onClick(View v) {
        if (etCountry.getText().toString().equals("")){
            Toast.makeText(this, "Введите страну", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etCity.getText().toString().equals("")){
            Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()) {
            case R.id.submit:
                Intent intent = new Intent();
                intent.putExtra(RETURN_TAG_FROM_INPUT, new LocalParcel(etCountry.getText().toString(), etCity.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            default:
                break;
        }
    }

}
