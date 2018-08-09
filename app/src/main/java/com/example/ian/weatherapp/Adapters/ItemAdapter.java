package com.example.ian.weatherapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ian.weatherapp.Activities.WeatherDetailActivity;
import com.example.ian.weatherapp.Home.ItemClickCallback;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.databinding.ItemListItemBinding;
import com.example.ian.weatherapp.entity.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private ItemClickCallback itemClickCallback;

    public ItemAdapter(Context context, ItemClickCallback itemClickCallback) {
        this.context = context;
        this.itemClickCallback = itemClickCallback;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListItemBinding itemListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_item, parent, false);
        itemListItemBinding.setCallback(itemClickCallback);
        return new ViewHolder(itemListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String link = "http://openweathermap.org/img/w/" + items.get(position).getWeather().get(0).icon + ".png";
        Glide.with(context).load(link).into(holder.itemListItemBinding.iconWeather);
        holder.itemListItemBinding.setItem(items.get(position));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemListItemBinding itemListItemBinding;

        public ViewHolder(ItemListItemBinding itemListItemBinding) {
            super(itemListItemBinding.getRoot());
            this.itemListItemBinding = itemListItemBinding;
        }
    }
}
