package com.example.androidcourseproject;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void showMessage(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
