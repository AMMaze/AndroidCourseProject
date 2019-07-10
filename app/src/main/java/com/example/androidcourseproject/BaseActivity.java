package com.example.androidcourseproject;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String RETURN_TAG_FROM_INPUT = "input";
    protected static final String RETURN_TAG_FROM_RESTORE = "saved";

    protected static final int RETURN_INPUT_CODE = 100;

    protected void showMessage(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
