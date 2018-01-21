package com.cirelios.android.deepstone.task;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.Utils;
import com.cirelios.android.deepstone.category.CategoryStruct;
import com.google.common.collect.Lists;

public class CreateTaskFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        CoordinatorLayout layout = (CoordinatorLayout) inflater.inflate(R.layout.task_create, container, false);

        final EditText asgmtName = layout.findViewById(R.id.task_create_name);
        final EditText asgmtDesc = layout.findViewById(R.id.task_create_desc);

        final Spinner classes = layout.findViewById(R.id.task_create_categories);
        classes.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.spinner_text, Lists.newArrayList(Utils.CATEGORIES.keySet())));

        final SeekBar difficultyBar = layout.findViewById(R.id.difficultyBar);
        final SeekBar urgencyBar = layout.findViewById(R.id.urgencyBar);
        final SeekBar timeBar = layout.findViewById(R.id.timeBar);
        final TextView timeText = layout.findViewById(R.id.task_create_time);
        final TextView difficultyText = layout.findViewById(R.id.difficultyText);
        final TextView urgencyText = layout.findViewById(R.id.urgencyText);

        difficultyBar.setMax(100);
        difficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                difficultyText.setText("Difficulty: " + progress);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }
        });
        urgencyBar.setMax(100);
        urgencyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                urgencyText.setText("Urgency: " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        timeBar.setMax(6 * 4);
        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int hour = progress / 4;
                int minute = 15 * (progress % 4);
                String minRemain = "" + minute;
                if (minute == 0)
                    minRemain = minRemain + "0";
                timeText.setText("Time: " + hour + ":" + minRemain);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }
        });
        Button create = layout.findViewById(R.id.task_create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = classes.getSelectedItem().toString();
                CategoryStruct category = Utils.CATEGORIES.get(name);
                TaskStruct task = new TaskStruct();
                task.Category = category;
                task.Name = asgmtName.getText().toString();
                task.Description = asgmtDesc.getText().toString();
                task.Time = timeBar.getProgress();
                task.Experience = (int) (Math.sqrt(difficultyBar.getProgress()) * urgencyBar.getProgress() * task.Time * 3 / 10);
                System.out.println(task.Experience);
                task.DueDate = Long.MAX_VALUE;
                Utils.TASKS.put(task.Name, task);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, new TasksFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        return layout;
    }
}