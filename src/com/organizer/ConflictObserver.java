package com.organizer;

import java.util.List;

public class ConflictObserver {

    public static boolean checkConflict(List<Task> tasks, Task newTask) {
        for (Task task : tasks) {
            if (overlap(task.getStartTime(), task.getEndTime(), newTask.getStartTime(), newTask.getEndTime())) {
                System.out.println("Error: Task conflicts with existing task: " + task.getDescription());
                return true;
            }
        }
        return false;
    }

    private static boolean overlap(String start1, String end1, String start2, String end2) {
        return start1.compareTo(end2) < 0 && start2.compareTo(end1) < 0;
    }
}
