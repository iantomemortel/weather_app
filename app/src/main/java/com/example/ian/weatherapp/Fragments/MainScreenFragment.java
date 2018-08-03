package com.example.ian.weatherapp.Fragments;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ian.weatherapp.Activities.WeatherDetailActivity;
import com.example.ian.weatherapp.Adapters.ItemAdapter;
import com.example.ian.weatherapp.Home.DaggerHomeFragmentComponent;
import com.example.ian.weatherapp.Home.HomeFragmentComponent;
import com.example.ian.weatherapp.Home.HomeFragmentModule;
import com.example.ian.weatherapp.Home.ItemClickCallback;
import com.example.ian.weatherapp.Home.RefreshButtonClickCallback;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.WeatherApplication;
import com.example.ian.weatherapp.databinding.FragmentMainScreenBinding;
import com.example.ian.weatherapp.viewmodel.LocationListViewModel;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class MainScreenFragment extends android.support.v4.app.Fragment {
    ItemAdapter itemAdapter;

    FragmentMainScreenBinding fragmentMainScreenBinding;

    LocationListViewModel viewModel;

    @Inject
    LocationListViewModel.Factory viewModelFactory;


    public MainScreenFragment() {

    }

    public MainScreenFragment newInstance() {
        MainScreenFragment mainScreenFragment = new MainScreenFragment();
        Bundle args = new Bundle();
        mainScreenFragment.setArguments(args);
        return mainScreenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentMainScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false);
        fragmentMainScreenBinding.setCallback(refreshButtonClickCallback);


        HomeFragmentComponent homeFragmentComponent = DaggerHomeFragmentComponent.builder()
                .homeFragmentModule(new HomeFragmentModule(this))
                .weatherApplicationComponent(WeatherApplication.get(getActivity()).weatherApplicationComponent())
                .build();

        homeFragmentComponent.inject(this);

        itemAdapter = new ItemAdapter(getContext(), itemClickCallback);
        fragmentMainScreenBinding.list.setAdapter(itemAdapter);

        return fragmentMainScreenBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationListViewModel.class);

        viewModel.liveDataListLocation.observe(this, new Observer<ArrayList<Location>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Location> locations) {
                if (locations != null) {
                    fragmentMainScreenBinding.setIsLoading(false);
                    itemAdapter.setItems(locations);
                } else {
                    fragmentMainScreenBinding.setIsLoading(true);
                }
                fragmentMainScreenBinding.executePendingBindings();
            }
        });

        viewModel.returnMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                fragmentMainScreenBinding.setIsLoading(false);
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private final ItemClickCallback itemClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(Location location) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("location", location);
                intent.putExtras(bundle);
                intent.setClass(Objects.requireNonNull(getContext()), WeatherDetailActivity.class);
                startActivity(intent);
            }
        }
    };

    private final RefreshButtonClickCallback refreshButtonClickCallback = new RefreshButtonClickCallback() {
        @Override
        public void onButtonRefreshClick() {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                fragmentMainScreenBinding.setIsLoading(true);
                viewModel.refresh();
            }
        }
    };

}
