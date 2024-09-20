package com.organizer;

public class TaskFactory {

    public static Task createTask(String description, String startTime, String endTime, String priority) {
        if (validateTime(startTime) && validateTime(endTime)) {
            return new Task(description, startTime, endTime, priority);
        } else {
            System.out.println("Error: Invalid time format.");
            return null;
        }
    }

    public static boolean validateTime(String time) {
        String[] timeParts = time.split(":");
        if (timeParts.length != 2) return false;
        try {
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            return (hour >= 0 && hour < 24) && (minute >= 0 && minute < 60);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
