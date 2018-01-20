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

import com.cirelios.android.deepstone.task.Task;
import com.cirelios.android.deepstone.task.TaskArrayAdapter;
import com.google.common.collect.Lists;

import java.util.List;

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

        Task a = new Task();
        a.name = "Task A";
        a.color = R.color.metallic_silver;
        a.experience = 117;
        Task b = new Task();
        b.name = "B Task";
        b.color = R.color.iridium;
        b.experience = -14;

        System.out.println("TaskArrayAdapter");
        ListAdapter listAdapter = new TaskArrayAdapter(getActivity(), Lists.newArrayList(a, b));
        System.out.println("Success!");
        ListView listView = myView.findViewById(R.id.tasks_list);

        System.out.println("A");
        listView.setAdapter(listAdapter);
        System.out.println("B");
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("C");
                        String task = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(getActivity(), task, Toast.LENGTH_SHORT).show();
                    }
                }

        );
        System.out.println("C");

        return myView;

    }
}
