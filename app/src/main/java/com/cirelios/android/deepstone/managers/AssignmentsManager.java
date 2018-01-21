package com.cirelios.android.deepstone.managers;

import com.cirelios.android.deepstone.task.TaskStruct;
import com.cirelios.android.deepstone.task.TaskAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AssignmentsManager {

    private static List<TaskStruct> assignmentList = new ArrayList<>();
    private static TaskAdapter asgmtAdapter;

    public static List<TaskStruct> getAssignmentList() {
        sortAssignmentList();
        return assignmentList;
    }

    public static void addAssignment(TaskStruct as) {
        assignmentList.add(as);
    }

    public static void removeAssignment(TaskStruct as) {
        assignmentList.remove(as);
    }

    public static void sortAssignmentList() {
        Collections.sort(assignmentList, new Comparator<TaskStruct>() {
            @Override
            public int compare(TaskStruct struct1, TaskStruct struct2) {
                return struct1.Experience < struct2.Experience ? 1 : -1;
            }
        });
    }
}