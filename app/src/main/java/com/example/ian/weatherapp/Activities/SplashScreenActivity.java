package com.example.ian.weatherapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.ian.weatherapp.BuildConfig;
import com.example.ian.weatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.splashScreen)
    RelativeLayout splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        if (BuildConfig.FLAVOR.equalsIgnoreCase("dev")) {
            splashScreen.setBackgroundColor(Color.GRAY);
        }
        else {
            splashScreen.setBackgroundColor(Color.WHITE);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
