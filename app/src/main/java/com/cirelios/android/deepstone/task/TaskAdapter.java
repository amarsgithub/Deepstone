package com.cirelios.android.deepstone.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cirelios.android.deepstone.MainActivity;
import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.Utils;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskStruct> {

    public static class Holder {

        public LinearLayout Item;
        public TextView Name;
        public TextView Color;
        public ImageView Icon;
        public TextView Experience;
        public CheckBox CheckBox;

    }

    public TaskAdapter(Context context, List<TaskStruct> assignments) {
        super(context, R.layout.tasks_adapter, assignments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
            holder = new Holder();
            holder.Item = convertView.findViewById(R.id.task_item);
            holder.Name = convertView.findViewById(R.id.task_name);
            holder.Color = convertView.findViewById(R.id.task_color);
            holder.Icon = convertView.findViewById(R.id.task_icon);
            holder.Experience = convertView.findViewById(R.id.task_experience);
            holder.CheckBox = convertView.findViewById(R.id.task_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final View root = convertView.getRootView();

        holder.CheckBox.setPressed(false);
        final TaskStruct task = getItem(position);
        if (task != null) {
            System.out.println("Found task " + task.Name + ", " + task.Category.Name);
            System.out.println(task.DueDate + " :: " + System.currentTimeMillis());
            if (task.DueDate - System.currentTimeMillis() <= 0) {
                holder.Item.setBackgroundResource(R.color.CherryRed);
                convertView.findViewById(R.id.task_border).setBackgroundResource(R.color.Firebrick);
            }
            holder.Name.setText(task.Name);
            holder.Color.setBackgroundResource(task.Category.Color);
            holder.Icon.setImageResource(task.Category.Icon);
            holder.Experience.setText(Integer.toString(task.Experience));
            holder.CheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Clicked for task " + task.Name);
                    Utils.TASKS.remove(task.Name);

                    int level = MainActivity.addExperience(task.Experience);
                    if (level > 0) {
                        Toast.makeText(getContext(), "You are now level " + level + " !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Assignment Completed!", Toast.LENGTH_SHORT).show();
                    }

                    final Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new AccelerateInterpolator());
                    fadeOut.setDuration(800);

                    holder.Item.startAnimation(fadeOut);
                    holder.Item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TaskAdapter.this.clear();
                            if (Utils.TASKS.isEmpty()) {
                                root.findViewById(R.id.empty_tasks).setVisibility(View.VISIBLE);
                            } else {
                                TaskAdapter.this.addAll(Utils.getSortedTasks());
                            }
                        }
                    }, 800);
                }
            });
        }

        return convertView;
    }
}
