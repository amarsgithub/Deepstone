package com.cirelios.android.deepstone.category;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.cirelios.android.deepstone.R;
import com.cirelios.android.deepstone.managers.CategoriesManager;

public class EditCategoryFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        CoordinatorLayout layout = (CoordinatorLayout) inflater.inflate(R.layout.category_create, container, false);

        final EditText className = layout.findViewById(R.id.category_create_name);
        final EditText classDesc = layout.findViewById(R.id.category_create_description);
        final Spinner classColor = layout.findViewById(R.id.category_create_colors);
        final Spinner classIcon = layout.findViewById(R.id.category_create_icons);
        final Button create = layout.findViewById(R.id.category_create_button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click");
                if (className.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Category name is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Go:");
                    CategoryStruct category = new CategoryStruct();
                    category.Name = className.getText().toString();
                    category.Description = classDesc.getText().toString();
                    category.Color = CategoryStruct.getColorID(classColor.getSelectedItem().toString());
                    category.Icon = CategoryStruct.getIconID(classIcon.getSelectedItem().toString());
                    CategoriesManager.addClass(category);
                    System.out.println("Added: " + category.Name + ", " + category.Description + ", " + category.Color + ", " + category.Icon);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                            .replace(R.id.fragment_container, new CategoriesFragment()).commit();
                }
            }
        });
        return layout;
    }
}
