package com.example.androidcourseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private TextView lifeCycleLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifeCycleLog = findViewById(R.id.life_cycle);
        lifeCycleLog.setText(getString(R.string.create));

        showMessage(this, getString(R.string.create));
        Log.i("lifeCycle", getString(R.string.create));

        ConstraintLayout mainContainer = findViewById(R.id.main_container);
        mainContainer.setOnClickListener(view -> {
            String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.click);
            lifeCycleLog.setText(str);
            showMessage(this, getString(R.string.click));
            Log.i(getString(R.string.lifecycle), getString(R.string.click));
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.restart);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.restart));
        Log.i(getString(R.string.lifecycle), getString(R.string.restart));
    }

    @Override
    protected void onStart() {
        super.onStart();
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.start);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.start));
        Log.i(getString(R.string.lifecycle), getString(R.string.start));
    }

    @Override
    protected void onResume() {
        super.onResume();
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.resume);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.resume));
        Log.i(getString(R.string.lifecycle), getString(R.string.resume));
    }

    @Override
    protected void onPause() {
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.pause);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.pause));
        Log.i(getString(R.string.lifecycle), getString(R.string.pause));
        super.onPause();
    }

    @Override
    protected void onStop() {
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.stop);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.stop));
        Log.i(getString(R.string.lifecycle), getString(R.string.stop));
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        String str = lifeCycleLog.getText().toString() + "\n" + getString(R.string.destroy);
        lifeCycleLog.setText(str);
        showMessage(this, getString(R.string.destroy));
        Log.i(getString(R.string.lifecycle), getString(R.string.destroy));
        super.onDestroy();
    }

}
