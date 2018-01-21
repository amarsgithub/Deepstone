package com.cirelios.android.deepstone.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cirelios.android.deepstone.R;

public class SkillsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_skills, container, false);
        FloatingActionButton fabSort = (FloatingActionButton) v.findViewById(R.id.fab_sort);
        fabSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "The assignment Deepstone is overdue!", Snackbar.LENGTH_LONG)
                        .setAction("Got it!", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), "Of course you are!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.NebulaGreen))
                        .show();
            }
        });

        return v;
    }
}