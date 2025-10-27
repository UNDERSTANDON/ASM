package Display;

import IO.StudentDataIO;
import Object.Student;
import StudentManager.StudentManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class that processes user input and manages the interaction
 * between
 * the user interface and business logic components.
 * 
 * Responsibilities:
 * - Menu navigation handling
 * - User input processing
 * - Input validation
 * - Command routing
 * - Integration between UI and data operations
 * 
 * Features:
 * - Main menu processing
 * - Student data management
 * - Search operations
 * - Sorting operations
 * - File I/O handling
 * 
 * @author UNDERSTANDON
 * @version 1.0
 */
public class DisplayProcessor {
    private final StudentManager studentManager;
    private final Scanner scanner;

    public DisplayProcessor(StudentManager studentManager, Scanner scanner) {
        this.studentManager = studentManager;
        this.scanner = scanner;
    }

    // Main menu processing
    public boolean processMainMenu() {
        DisplayInfo.displayMainMenu();
        int choice = getIntInput();

        switch (choice) {
            case 1 -> addStudent();
            case 2 -> editStudent();
            case 3 -> deleteStudent();
            case 4 -> searchStudents();
            case 5 -> sortStudents();
            case 6 -> displayAllStudents();
            case 7 -> displayStatistics();
            case 8 -> clearAllStudents();
            case 9 -> processIOMenu();
            case 0 -> {
                System.out.println("Thank you for using Student Management System!");
                return false;
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void addStudent() {
        // Init variables
        Student student;
        int id;
        String name;
        float mark;

        System.out.println("\n=== ADD STUDENT ===");
        System.out.print("Enter Student ID: ");
        id = getIntInput();

        System.out.print("Enter Student Name: ");
        name = scanner.nextLine().trim();

        while (true) {
            System.out.print("Enter Student Mark (0-10): ");
            mark = getFloatInput();

            // Rank checking since we don't want to add student with marking not within 0-10
            String rank = new Student().getRank(mark);
            if (!isValidRank(rank)) {
                System.out.println("Invalid marking! Please re-enter.");
                continue;
            }
            break;
        }
        student = new Student(id, mark, name);
        studentManager.addStudent(student);
    }

    private void editStudent() {
        System.out.println("\n=== EDIT STUDENT ===");
        System.out.print("Enter Student ID to edit: ");
        int id = getIntInput();

        Student existingStudent = studentManager.findStudentById(id);
        if (existingStudent == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current student information:");
        DisplayInfo.displayStudent(existingStudent);

        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            newName = existingStudent.getName();
        }

        System.out.print("Enter new mark (or -1 to keep current): ");
        float newMark = getFloatInput();
        if (newMark == -1) {
            newMark = existingStudent.getMark();
        }

        studentManager.editStudent(id, newName, newMark);
    }

    private void deleteStudent() {
        System.out.println("\n=== DELETE STUDENT ===");
        System.out.print("Enter Student ID to delete: ");
        int id = getIntInput();

        Student student = studentManager.findStudentById(id);
        if (student != null) {
            System.out.println("Student to be deleted:");
            DisplayInfo.displayStudent(student);
            System.out.print("Are you sure you want to delete this student? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.equals("y") || confirm.equals("yes")) {
                studentManager.deleteStudent(id);
            } else {
                System.out.println("Deletion cancelled.");
            }
        }
    }

    private void searchStudents() {
        boolean searching = true;
        while (searching) {
            DisplayInfo.displaySearchMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> searchById();
                case 2 -> searchByName();
                case 3 -> searchByMarkRange();
                case 4 -> searchByRanking();
                case 0 -> searching = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchById() {
        System.out.print("Enter Student ID: ");
        int id = getIntInput();
        Student student = studentManager.findStudentById(id);
        DisplayInfo.displayStudent(student);
    }

    private void searchByName() {
        System.out.print("Enter Student Name (partial match): ");
        String name = scanner.nextLine().trim();
        ArrayList<Student> results = studentManager.findStudentsByName(name);
        DisplayInfo.displaySearchResults(results, "Name Search");
    }

    private void searchByMarkRange() {
        System.out.print("Enter minimum mark: ");
        float minMark = getFloatInput();
        System.out.print("Enter maximum mark: ");
        float maxMark = getFloatInput();
        ArrayList<Student> results = studentManager.findStudentsByMarkRange(minMark, maxMark);
        DisplayInfo.displaySearchResults(results, "Mark Range Search");
    }

    private void searchByRanking() {
        System.out.println("Available rankings:");
        System.out.println("1. Fail");
        System.out.println("2. Medium");
        System.out.println("3. Good");
        System.out.println("4. Very Good");
        System.out.println("5. Excellent");
        System.out.print("Enter ranking number: ");
        int rankChoice = getIntInput();

        String rank;
        switch (rankChoice) {
            case 1 -> rank = "Fail";
            case 2 -> rank = "Medium";
            case 3 -> rank = "Good";
            case 4 -> rank = "Very Good";
            case 5 -> rank = "Excellent";
            default -> {
                System.out.println("Invalid ranking choice.");
                return;
            }
        }

        ArrayList<Student> results = studentManager.findStudentsByRank(rank);
        DisplayInfo.displaySearchResults(results, "Ranking Search");
    }

    private void sortStudents() {
        boolean sorting = true;
        while (sorting) {
            DisplayInfo.displaySortMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> studentManager.bubbleSortById();
                case 2 -> studentManager.quickSortById();
                case 3 -> studentManager.mergeSortById();
                case 4 -> studentManager.bubbleSortByMark();
                case 5 -> studentManager.quickSortByMark();
                case 6 -> studentManager.mergeSortByMark();
                case 0 -> sorting = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (choice >= 1 && choice <= 6) {
                System.out.println("Students after sorting:");
                DisplayInfo.displayStudentsTable(studentManager.getAllStudents());
            }
        }
    }

    private void displayAllStudents() {
        ArrayList<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("Choose display format:");
            System.out.println("1. Table format");
            System.out.println("2. Detailed format");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            if (choice == 1) {
                DisplayInfo.displayStudentsTable(students);
            } else {
                DisplayInfo.displayStudents(students);
            }
        }
    }

    private void displayStatistics() {
        ArrayList<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students to analyze.");
        } else {
            DisplayInfo.displayRankingStats(students);
        }
    }

    private void clearAllStudents() {
        System.out.print("Are you sure you want to clear all students? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            studentManager.clearAllStudents();
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private void processIOMenu() {
        boolean ioMenuActive = true;
        while (ioMenuActive) {
            DisplayInfo.displayIOMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> exportToMarkdown();
                case 2 -> exportToCSV();
                case 3 -> importFromMarkdown();
                case 4 -> importFromCSV();
                case 5 -> createSampleDataFile();
                case 0 -> ioMenuActive = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void exportToMarkdown() {
        System.out.print("Enter filename for export (e.g., students.md): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = "students.md";
        }
        if (!filename.endsWith(".md")) {
            filename += ".md";
        }
        String exportPath = "Data/" + filename;
        StudentDataIO.exportToMarkdown(studentManager, exportPath);
    }

    private void exportToCSV() {
        System.out.print("Enter filename for export (e.g., students.csv): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = "students.csv";
        }
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }
        String exportPath = "Data/" + filename;
        StudentDataIO.exportToCSV(studentManager, exportPath);
    }

    private void importFromMarkdown() {
        java.io.File dataDir = new java.io.File("Data");
        java.io.FilenameFilter mdFilter = (dir, name) -> name.toLowerCase().endsWith(".md")
                || name.toLowerCase().endsWith(".markdown");
        String[] files = dataDir.list(mdFilter);
        if (files == null || files.length == 0) {
            System.out.println("No markdown files found in Data/ directory.");
            return;
        }
        System.out.println("Available markdown files in Data/: ");
        for (int i = 0; i < files.length; i++) {
            System.out.printf("%d. %s\n", i + 1, files[i]);
        }
        System.out.print("Enter file number or name to import: ");
        String input = scanner.nextLine().trim();
        String filename = null;
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < files.length) {
                filename = "Data/" + files[idx];
            }
        } catch (NumberFormatException e) {
            // Not a number, treat as filename
            for (String f : files) {
                if (f.equalsIgnoreCase(input)) {
                    filename = "Data/" + f;
                    break;
                }
            }
        }
        if (filename == null) {
            System.out.println("Invalid selection.");
            return;
        }
        System.out.print("This will add students to the current data. Continue? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            StudentDataIO.importFromMarkdown(studentManager, filename);
        } else {
            System.out.println("Import cancelled.");
        }
    }

    private void importFromCSV() {
        java.io.File dataDir = new java.io.File("Data");
        java.io.FilenameFilter csvFilter = (dir, name) -> name.toLowerCase().endsWith(".csv");
        String[] files = dataDir.list(csvFilter);
        if (files == null || files.length == 0) {
            System.out.println("No CSV files found in Data/ directory.");
            return;
        }
        System.out.println("Available CSV files in Data/: ");
        for (int i = 0; i < files.length; i++) {
            System.out.printf("%d. %s\n", i + 1, files[i]);
        }
        System.out.print("Enter file number or name to import: ");
        String input = scanner.nextLine().trim();
        String filename = null;
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < files.length) {
                filename = "Data/" + files[idx];
            }
        } catch (NumberFormatException e) {
            // Not a number, treat as filename
            for (String f : files) {
                if (f.equalsIgnoreCase(input)) {
                    filename = "Data/" + f;
                    break;
                }
            }
        }
        if (filename == null) {
            System.out.println("Invalid selection.");
            return;
        }
        System.out.print("This will add students to the current data. Continue? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            StudentDataIO.importFromCSV(studentManager, filename);
        } else {
            System.out.println("Import cancelled.");
        }
    }

    private void createSampleDataFile() {
        System.out.print("Enter filename for sample data (e.g., sample_data.csv): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = "sample_data.csv";
        }
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }
        String exportPath = "Data/" + filename;
        StudentDataIO.createSampleDataFile(exportPath);
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    private float getFloatInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private boolean isValidRank(String rank) {
        return rank != null;
    }
}