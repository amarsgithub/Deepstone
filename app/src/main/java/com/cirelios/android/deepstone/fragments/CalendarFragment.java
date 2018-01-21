package com.cirelios.android.deepstone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.cirelios.android.deepstone.R;

public class CalendarFragment extends Fragment {

    View myView;
    private static final String TAG = "CalendarActivity";

    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.calendar_layout, container, false);


        return myView;
    }

}