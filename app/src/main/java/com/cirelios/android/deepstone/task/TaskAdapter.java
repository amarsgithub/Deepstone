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
import com.cirelios.android.deepstone.managers.AssignmentsManager;
import com.cirelios.android.deepstone.managers.ExperienceManager;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskStruct> {

    public LinearLayout Item;
    public TextView Name;
    public TextView Color;
    public ImageView Icon;
    public TextView Experience;
    public CheckBox CheckBox;

    public TaskAdapter(Context context, List<TaskStruct> assignments) {
        super(context, R.layout.tasks_adapter, assignments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
            Item = convertView.findViewById(R.id.task_item);
            Name = convertView.findViewById(R.id.task_name);
            Color = convertView.findViewById(R.id.task_color);
            Icon = convertView.findViewById(R.id.task_icon);
            Experience = convertView.findViewById(R.id.task_experience);
            CheckBox = convertView.findViewById(R.id.task_checkbox);
        }
        final View root = convertView.getRootView();

        final TaskStruct task = getItem(position);
        if (task != null) {
            System.out.println("Found task " + task.Name + ", " + task.Category.Name);
            Name.setText(task.Name);
            Color.setBackgroundColor(task.Category.Color);
            Icon.setImageResource(task.Category.Icon);
            Experience.setText(Integer.toString(task.Experience));
            CheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Clicked for task " + task.Name);
                    AssignmentsManager.removeAssignment(task);

                    int addLevel = ExperienceManager.addExperience(task.Experience);
                    int level = Integer.parseInt(MainActivity.experienceLevel.getText().toString().substring(6));
                    System.out.println(level);
                    if (addLevel != 0) {
                        level = Math.random() > .5 ? level + 1 : level + 2;
                        ExperienceManager.setProfileTitle(level);
                        Toast toast = Toast.makeText(getContext(), "You are now level " + 0 + " !", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getContext(), "Assignment Completed!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    final Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new AccelerateInterpolator());
                    fadeOut.setDuration(800);

                    Item.startAnimation(fadeOut);
                    Item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Item.setVisibility(View.GONE);
                            if (AssignmentsManager.getAssignmentList().isEmpty()) {
                                root.findViewById(R.id.ds_noAssignments).setVisibility(View.GONE);
                            }
                        }
                    }, 800);
                }
            });
        }

        return convertView;
    }
}
