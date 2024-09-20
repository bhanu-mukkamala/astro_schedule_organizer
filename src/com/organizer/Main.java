package com.organizer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: ");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter start time (HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.println("Enter end time (HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.println("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    manager.addTask(task);
                    break;
                case 2:
                    System.out.println("Enter description of task to remove: ");
                    String removeDescription = scanner.nextLine();
                    manager.removeTask(removeDescription);
                    break;
                case 3:
                    manager.viewTasks();
                    break;
                case 4:
                    System.out.println("Enter description of task to edit: ");
                    String oldDescription = scanner.nextLine();
                    System.out.println("Enter new description (or leave blank): ");
                    String newDescription = scanner.nextLine();
                    System.out.println("Enter new start time (or leave blank): ");
                    String newStartTime = scanner.nextLine();
                    System.out.println("Enter new end time (or leave blank): ");
                    String newEndTime = scanner.nextLine();
                    System.out.println("Enter new priority (or leave blank): ");
                    String newPriority = scanner.nextLine();
                    manager.editTask(oldDescription, newDescription.isEmpty() ? null : newDescription,
                            newStartTime.isEmpty() ? null : newStartTime, newEndTime.isEmpty() ? null : newEndTime,
                            newPriority.isEmpty() ? null : newPriority);
                    break;
                case 5:
                    System.out.println("Enter description of task to mark as completed: ");
                    String completedDescription = scanner.nextLine();
                    manager.markTaskAsCompleted(completedDescription);
                    break;
                case 6:
                    System.out.println("Enter priority level to view (High/Medium/Low): ");
                    String priorityLevel = scanner.nextLine();
                    manager.viewTasksByPriority(priorityLevel);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
