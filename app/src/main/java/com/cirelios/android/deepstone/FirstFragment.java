package com.cirelios.android.deepstone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by amars on 1/20/2018.
 */

public class FirstFragment extends Fragment {

    View myView;

    @Override   public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
   // @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);

        String[] tasks = {"Physics HW", "SwampHacks 2018", "Overdue"};
        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        ListView listView = (ListView) myView.findViewById(R.id.amarsListView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String task = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(getActivity(), task, Toast.LENGTH_SHORT).show();
                    }
                }

        );

        return myView;

    }
}
