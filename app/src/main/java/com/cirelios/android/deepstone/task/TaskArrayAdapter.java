package com.cirelios.android.deepstone.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cirelios.android.deepstone.R;

import java.util.List;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    private LayoutInflater inflater;

    public TaskArrayAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("1");
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.task_layout, null);
        Task task = getItem(position);
        System.out.println("2");
        TextView color = convertView.findViewById(R.id.task_color);
        ImageView icon = convertView.findViewById(R.id.task_icon);
        TextView name = convertView.findViewById(R.id.task_name);
        TextView experience = convertView.findViewById(R.id.task_experience);
        System.out.println("3");
        color.setBackgroundColor(task.color);
        icon.setImageResource(task.icon);
        name.setText(task.name);
        experience.setText(task.experience + " XP");
        System.out.println("4");
        return convertView;
    }

}