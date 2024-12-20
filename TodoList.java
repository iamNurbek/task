import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String description;
    boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
}

public class TodoList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    private static void displayMenu() {
        System.out.println(CYAN + "\n--- Todo List Menu ---" + RESET);
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete Task");
        System.out.println("5. Edit Task");
        System.out.println("6. Exit");
        System.out.print(YELLOW + "Enter your choice: " + RESET);
    }

    private static void handleUserChoice (int choice) {
        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                viewTasks();
                break;  
            case 3:
                markTaskCompleted();
                break;
            case 4:
                deleteTask();
                break;
            case 5:
                editTask();
                break;
            case 6:
                System.out.println(GREEN + "Exiting... Goodbye!" + RESET);
                System.exit(0);
                break;
            default:
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
        }
    }

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            handleUserChoice(choice);
        }
    }

    private static Task getTaskByIndex(String prompt) {
        System.out.print(YELLOW + prompt + RESET);
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
            return null;
        }
    }
    
    private static void addTask() {
        System.out.print(YELLOW + "Enter task description: " + RESET);
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println(GREEN + "Task added successfully." + RESET);
    }

    private static void viewTasks() {
        System.out.println(CYAN + "\n--- Your Tasks ---" + RESET);
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks available." + RESET);
            return;
        }

        int completedCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isCompleted) completedCount++;
            System.out.println((i + 1) + ". " + task.description + " " +
                    (task.isCompleted ? GREEN + "(Completed)" + RESET : RED + "(Incomplete)" + RESET));
        }

        System.out.println(CYAN + "\nSummary: " + RESET +
                "Total: " + tasks.size() +
                ", Completed: " + GREEN + completedCount + RESET +
                ", Incomplete: " + RED + (tasks.size() - completedCount) + RESET);
    }

    private static void markTaskCompleted() {
        viewTasks();
        Task task = getTaskByIndex("Enter the number of the task to mark as completed: ");
        if (task != null) {
            task.isCompleted = true;
            System.out.println(GREEN + "Task marked as completed." + RESET);
        } 
    }

    private static void deleteTask() {
        viewTasks();
        Task task = getTaskByIndex("Enter the number of the task to delete: ");
        if (task != null) {
            tasks.remove(task);
            System.out.println(GREEN + "Task deleted successfully." + RESET);
        }
    }

    private static void editTask() {
        viewTasks();
        System.out.print(YELLOW + "Enter the number of the task to edit: " + RESET);
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < tasks.size()) {
            System.out.print(YELLOW + "Enter the new description: " + RESET);
            String newDescription = scanner.nextLine();
            tasks.get(index).description = newDescription;
            System.out.println(GREEN + "Task updated successfully." + RESET);
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
        }
    }
    

}