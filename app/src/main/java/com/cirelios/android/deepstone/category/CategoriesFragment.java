package com.cirelios.android.deepstone.category;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.Utils;

import static com.cirelios.android.deepstone.R.layout.categories;

public class CategoriesFragment extends ListFragment implements OnItemClickListener {

    private ArrayAdapter<CategoryStruct> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(categories, container, false);

        if (Utils.CATEGORIES.isEmpty()) {
            view.findViewById(R.id.list).setVisibility(View.GONE);
            Snackbar.make(view, "No categories!", Snackbar.LENGTH_LONG)
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
        } else {
            view.findViewById(R.id.empty_category).setVisibility(LinearLayout.GONE);
            if (adapter != null) {
                adapter.clear();
                adapter.addAll(Utils.getSortedCategories());
            }
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new CategoryAdapter(getActivity(), Utils.getSortedCategories());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("OnItemClick");
        Toast.makeText(getActivity(), "Category: " + position, Toast.LENGTH_SHORT).show();
    }

}