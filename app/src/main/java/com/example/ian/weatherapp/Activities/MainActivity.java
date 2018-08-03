package com.example.ian.weatherapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.ian.weatherapp.Fragments.MainScreenFragment;
import com.example.ian.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TextView toolbarTitle;

    MainScreenFragment mainScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainScreenFragment = new MainScreenFragment().newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainScreenFragment).commit();

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
