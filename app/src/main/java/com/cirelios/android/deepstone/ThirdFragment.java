package com.cirelios.android.deepstone;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by amars on 1/20/2018.
 */

public class ThirdFragment extends Fragment {

    View myView;
    private static final String TAG = "CalendarActivity";
    private static TextView physics;
    private static TextView swampHacks;

    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container, false);

        calendarView = (CalendarView) myView.findViewById(R.id.calendarView);
        physics = (TextView) myView.findViewById(R.id.physics);
        swampHacks = (TextView) myView.findViewById(R.id.swampHacks);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) { //year,month,day
                month++;
                Log.d(TAG, "mm/dd/yyyy" +  month + "/" + day + "/" + year);

                if (day == 20) {
                    physics.setText("PHYSICS");
                    physics.setTextColor(Color.RED);
                }
                if (day == 21) {
                    swampHacks.setText("SWAMP HACKS");
                    swampHacks.setTextColor(Color.RED);
                }

            }
        });


        return myView;
    }
}
