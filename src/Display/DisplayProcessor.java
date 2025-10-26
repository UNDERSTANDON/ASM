package Display;
import IO.StudentDataIO;
import Object.Student;
import StudentManager.StudentManager;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("\n=== ADD STUDENT ===");
        System.out.print("Enter Student ID: ");
        int id = getIntInput();
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Student Mark (0-10): ");
        float mark = getFloatInput();
        
        Student student = new Student(id, mark, name);
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
        StudentDataIO.exportToMarkdown(studentManager, filename);
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
        StudentDataIO.exportToCSV(studentManager, filename);
    }

    private void importFromMarkdown() {
        System.out.print("Enter filename to import from: ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            System.out.println("No filename provided.");
            return;
        }
        
        if (!StudentDataIO.fileExists(filename)) {
            System.out.println("File not found: " + filename);
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
        System.out.print("Enter filename to import from: ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            System.out.println("No filename provided.");
            return;
        }
        
        if (!StudentDataIO.fileExists(filename)) {
            System.out.println("File not found: " + filename);
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
        StudentDataIO.createSampleDataFile(filename);
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
}