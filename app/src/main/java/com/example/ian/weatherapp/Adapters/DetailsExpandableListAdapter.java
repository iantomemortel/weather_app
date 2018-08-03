package com.example.ian.weatherapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ian.weatherapp.Model.Details;
import com.example.ian.weatherapp.R;

import java.util.ArrayList;
import java.util.Map;


public class DetailsExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Details> _listDataHeader;
    private Map<Details, ArrayList<Details>> _listDataChild;

    public DetailsExpandableListAdapter(Context context, ArrayList<Details> _listDataHeader, Map<Details, ArrayList<Details>> _listDataChild) {
        this.context = context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Nullable
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, @Nullable View convertView, ViewGroup parent) {
        Details childDetails = (Details) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_detail_item_child, null);
        }

        TextView key = (TextView) convertView.findViewById(R.id.pdpDetailItemKey);
        TextView value = (TextView) convertView.findViewById(R.id.pdpDetailItemValue);
        LinearLayout container = (LinearLayout) convertView.findViewById(R.id.pdpDetailItemContainer);
        container.setBackgroundColor(Color.parseColor((groupPosition % 2 == 0) ? "#E6FAFB" : "#FFFFFF"));

        key.setText(childDetails.getKey());

        value.setText(childDetails.getValue());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Nullable
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, @Nullable View convertView, ViewGroup parent) {
        Details headerTitle = (Details) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_detail_item, null);
        }
        LinearLayout container = (LinearLayout) convertView.findViewById(R.id.pdpDetailItemContainer);
        TextView key = (TextView) convertView.findViewById(R.id.pdpDetailItemKey);
        TextView value = (TextView) convertView.findViewById(R.id.pdpDetailItemValue);
        ImageView indicator = (ImageView) convertView.findViewById(R.id.pdpDetailItemIndicator);

        if (getChildrenCount(groupPosition) > 0) {
            indicator.setVisibility(View.VISIBLE);
            if(isExpanded){
                indicator.setImageResource(R.drawable.ic_expand_less_white_24px);
            }else{
                indicator.setImageResource(R.drawable.ic_expand_more_white_24px);
            }
        }else{
            indicator.setVisibility(View.GONE);
        }

        container.setBackgroundColor(Color.parseColor((groupPosition % 2 == 0) ? "#E6FAFB" : "#FFFFFF"));
        key.setText(headerTitle.getKey());
        value.setText(headerTitle.getValue());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}
