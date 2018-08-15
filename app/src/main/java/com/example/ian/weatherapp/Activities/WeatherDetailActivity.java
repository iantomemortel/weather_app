package com.example.ian.weatherapp.Activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ian.weatherapp.Adapters.DetailsExpandableListAdapter;
import com.example.ian.weatherapp.Model.Details;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.Util.CustomExpandableListView;
import com.example.ian.weatherapp.Util.NetworkConnectivityChecker;
import com.example.ian.weatherapp.WeatherApplication;
import com.example.ian.weatherapp.data.local.db.AppDatabase;
import com.example.ian.weatherapp.detailPage.DaggerDetailActivityComponent;
import com.example.ian.weatherapp.detailPage.DetailActivityComponent;
import com.example.ian.weatherapp.detailPage.DetailActivityModule;
import com.example.ian.weatherapp.entity.Item;
import com.example.ian.weatherapp.network.WeatherService;
import com.example.ian.weatherapp.thread.DefaultExecutorSupplier;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailActivity extends AppCompatActivity {


    @BindView(R.id.name)
    TextView name;

    @Nullable
    @BindView(R.id.expandableDetailList)
    CustomExpandableListView expandableListView;

    @BindView(R.id.progreessBarParentLayout)
    LinearLayout progreessBarParentLayout;

    @BindView(R.id.btnRefresh)
    Button btnRefresh;

    @BindView(R.id.iconWeather)
    ImageView iconWeather;

    @Inject
    WeatherService weatherService;

    @Inject
    Gson gson;

    public static Item location;

    private ArrayList<Details> listDataHeader;
    private Map<Details, ArrayList<Details>> listDataChild;
    private DetailsExpandableListAdapter expandableListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        DetailActivityComponent component = DaggerDetailActivityComponent.builder()
                .detailActivityModule(new DetailActivityModule(this))
                .weatherApplicationComponent(WeatherApplication.get(this).weatherApplicationComponent())
                .build();

        component.injectDetailActivity(this);

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(new Runnable() {
            @Override
            public void run() {
//                location = AppDatabase.getAppDatabase(getApplicationContext()).locationDao().getItem(getIntent().getExtras().getString("id"));
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Weather Details");

        name.setText("Weather Details for " + location.name);

        prepareData();
        updateUI();

        refresh();

    }

    private void prepareData() {
//        String sunrise = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa").format(location.getSys().sunrise);
//        String sunset = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa").format(location.getSys().sunset);
        String link = "http://openweathermap.org/img/w/" + location.getWeather().get(0).icon + ".png";
        Glide.with(this).load(link).into(iconWeather);
        listDataHeader = new ArrayList<Details>();
        listDataChild = new HashMap<Details, ArrayList<Details>>();

        Details detail0 = new Details("Clouds", location.getClouds().all + " %");
        Details detail1 = new Details("Actual Weather", location.getWeather().get(0).main);
        Details detail2 = new Details("Weather Description", location.getWeather().get(0).description);
        Details detail3 = new Details("Temperature", location.getMain().temp + " °C");
        Details detail4 = new Details("Pressure", location.getMain().pressure + " hpa");
        Details detail5 = new Details("Humidity", location.getMain().humidity);
        Details detail6 = new Details("Minimum Temperature", location.getMain().temp_min + " °C");
        Details detail7 = new Details("Maximum Temperature", location.getMain().temp_max + " °C");
        Details detail8 = new Details("Wind Speed", location.getWind().speed + " m/s");
        Details detail9 = new Details("Wind Deg", location.getWind().deg);
//        Details details10 = new Details("Sunrise", sunrise);
//        Details details11 = new Details("Sunset", sunset);
        Details detail12 = new Details("Visibility", location.getVisibility());


        listDataHeader.add(detail0);
        listDataChild.put(detail0, new ArrayList<Details>());
        listDataHeader.add(detail1);
        listDataChild.put(detail1, new ArrayList<Details>());
        listDataHeader.add(detail2);
        listDataChild.put(detail2, new ArrayList<Details>());
        listDataHeader.add(detail3);
        listDataChild.put(detail3, new ArrayList<Details>());
        listDataHeader.add(detail4);
        listDataChild.put(detail4, new ArrayList<Details>());
        listDataHeader.add(detail5);
        listDataChild.put(detail5, new ArrayList<Details>());
        listDataHeader.add(detail6);
        listDataChild.put(detail6, new ArrayList<Details>());
        listDataHeader.add(detail7);
        listDataChild.put(detail7, new ArrayList<Details>());
        listDataHeader.add(detail8);
        listDataChild.put(detail8, new ArrayList<Details>());
        listDataHeader.add(detail9);
        listDataChild.put(detail9, new ArrayList<Details>());
//        listDataHeader.add(details10);
//        listDataChild.put(details10, new ArrayList<Details>());
//        listDataHeader.add(details11);
//        listDataChild.put(details11, new ArrayList<Details>());
        listDataHeader.add(detail12);
        listDataChild.put(detail12, new ArrayList<Details>());

    }

    private void refresh() {
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataHeader.clear();
                listDataChild.clear();
                updateUI();
                btnRefresh.setVisibility(View.GONE);
                progreessBarParentLayout.setVisibility(View.VISIBLE);

                fetchData();
            }
        });

    }

    private void fetchData() {
        String id = location.getId();
        String url = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&units=metric&APPID=5bea56b1d8227d22befcfa6582bc9b7c";

        if (NetworkConnectivityChecker.isNetworkAvailable(this)) {
            Call<ResponseBody> call = weatherService.downloadFile(url);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response != null) {
                            Item location = null;
                            location = gson.fromJson(response.body().string(), Item.class);
                            WeatherDetailActivity.location = location;

                            progreessBarParentLayout.setVisibility(View.GONE);
                            btnRefresh.setVisibility(View.VISIBLE);
                            prepareData();
                            updateUI();
                        } else {
                            progreessBarParentLayout.setVisibility(View.GONE);
                            btnRefresh.setVisibility(View.VISIBLE);
                            Toast.makeText(WeatherDetailActivity.this, "Error Downloading Data", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } else {
            progreessBarParentLayout.setVisibility(View.GONE);
            btnRefresh.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUI() {
        expandableListAdapter = new DetailsExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setExpanded(true);
        expandableListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
