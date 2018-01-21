package com.cirelios.android.deepstone.category;

import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.managers.CategoriesManager;

import static com.cirelios.android.deepstone.R.layout.categories;

public class CategoriesFragment extends ListFragment implements OnItemClickListener {

    private ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(categories, container, false);

        if (CategoriesManager.getCategoriesList().isEmpty()) {
            ListView assignmentList = view.findViewById(R.id.list);
            if (assignmentList != null) {
                assignmentList.setVisibility(View.GONE);
            }
            //create.setVisibility(View.GONE);

            Snackbar.make(view, "No classes exist", Snackbar.LENGTH_LONG)
                    .setAction("CREATE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                    .replace(R.id.fragment_container, new CreateCategoryFragment())
                                    .commit();
                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.NebulaGreen))
                    .show();

            Button createClass = view.findViewById(R.id.ds_class_create);
            createClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                            .replace(R.id.fragment_container, new CreateCategoryFragment())
                            .commit();
                }
            });
        } else {
            /*create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                            .replace(R.id.fragment_container, new CreateCategoryFragment())
                            .commit();
                }
            });*/
            LinearLayout noClasses = view.findViewById(R.id.ds_noClasses);
            noClasses.setVisibility(LinearLayout.GONE);
            if (adapter != null) {
                adapter.clear();
                adapter.addAll(CategoriesManager.getCategoriesList());
            }
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new CategoryAdapter(getActivity(), CategoriesManager.getCategoriesList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Category: " + position, Toast.LENGTH_SHORT).show();
    }
}