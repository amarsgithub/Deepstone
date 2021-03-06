package com.cirelios.android.deepstone.task;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.Utils;
import com.cirelios.android.deepstone.category.CreateCategoryFragment;

public class TasksFragment extends ListFragment implements OnItemClickListener {

    private ArrayAdapter<TaskStruct> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks, container, false);

        if (Utils.TASKS.isEmpty()) {
            Button createAssignment = view.findViewById(R.id.asgmt_create);

            if (Utils.CATEGORIES.isEmpty()) {
                Snackbar.make(view, "No categories exist", Snackbar.LENGTH_LONG)
                        .setAction("CREATE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new CreateCategoryFragment())
                                        .commit();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.NebulaGreen))
                        .show();
                createAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getContext(), R.style.MyAlertDialogTheme)
                                .setMessage("You must have an existing category to create a task!\n\nCreate a new category?\n")
                                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        CreateCategoryFragment fragment = new CreateCategoryFragment();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                                        transaction.replace(R.id.fragment_container, fragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                    }
                                })
                                .setNegativeButton("No.", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .create().show();
                    }
                });
            } else {
                Snackbar.make(view, "No tasks exist", Snackbar.LENGTH_LONG)
                        .setAction("CREATE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new CreateTaskFragment())
                                        .commit();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.NebulaGreen))
                        .show();
                createAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CreateTaskFragment fragment = new CreateTaskFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                        transaction.replace(R.id.fragment_container, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        } else {
            LinearLayout noAssignments = view.findViewById(R.id.empty_tasks);
            noAssignments.setVisibility(LinearLayout.GONE);
            if (adapter != null) {
                adapter.clear();
                adapter.addAll(Utils.getSortedTasks());
            }
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new TaskAdapter(getActivity(), Utils.getSortedTasks());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Asgmt: " + position, Toast.LENGTH_SHORT).show();
    }
}