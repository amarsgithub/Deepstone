package com.cirelios.android.deepstone.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cirelios.android.deepstone.R;

import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<CategoryStruct> {

    public TextView Name;
    public TextView Color;
    public ImageView Icon;
    public ImageView Border;

    public CategoryAdapter(Context context, List<CategoryStruct> users) {
        super(context, R.layout.category_item, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
            Name = convertView.findViewById(R.id.category_name);
            Color = convertView.findViewById(R.id.category_color);
            Icon = convertView.findViewById(R.id.category_icon);
            Border = convertView.findViewById(R.id.category_border);
        }

        CategoryStruct category = getItem(position);
        if (category != null) {
            Name.setText(category.Name);
            Color.setBackgroundColor(category.Color);
            Icon.setImageResource(category.Icon);
        }

        return convertView;
    }

}