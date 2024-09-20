package com.organizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        if (task != null) {
            if (!ConflictObserver.checkConflict(tasks, task)) {
                tasks.add(task);
                Collections.sort(tasks, Comparator.comparing(Task::getStartTime));  // Sort by start time
                System.out.println("Task added successfully. No conflicts.");
            }
        }
    }

    public void removeTask(String description) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task.getStartTime() + " - " + task.getEndTime() + ": " + task.getDescription() + " [" + task.getPriority() + "]");
        }
    }

    public void editTask(String oldDescription, String newDescription, String newStartTime, String newEndTime, String newPriority) {
        for (Task task : tasks) {
            if (task.getDescription().equals(oldDescription)) {
                if (newDescription != null) task.setDescription(newDescription);
                if (newStartTime != null) task.setStartTime(newStartTime);
                if (newEndTime != null) task.setEndTime(newEndTime);
                if (newPriority != null) task.setPriority(newPriority);
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Error: Task not found.");
    }

    public void markTaskAsCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.setCompleted(true);
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Error: Task not found.");
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority().equals(priority)) {
                System.out.println(task.getStartTime() + " - " + task.getEndTime() + ": " + task.getDescription() + " [" + task.getPriority() + "]");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with priority " + priority);
        }
    }
}
