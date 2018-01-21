package com.cirelios.android.deepstone.managers;

import com.cirelios.android.deepstone.category.CategoryStruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategoriesManager {

    private static List<CategoryStruct> classList = new ArrayList<>();
    private static int selectedClass = 0;

    public static List<CategoryStruct> getCategoriesList() {
        sortClassList();
        return classList;
    }

    public static void sortClassList() {
        Collections.sort(classList, new Comparator<CategoryStruct>() {
            @Override
            public int compare(CategoryStruct struct1, CategoryStruct struct2) {
                return struct1.Name.compareToIgnoreCase(struct2.Name);
            }
        });
    }
    public static void addClass(CategoryStruct cStruct) {
        classList.add(cStruct);
    }
}
