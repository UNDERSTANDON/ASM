package Display;
import Object.Student;
import java.util.ArrayList;

public class DisplayInfo {

    // Display a single student
    public static void displayStudent(Student student) {
        if (student == null) {
            System.out.println("No student found.");
            return;
        }
        System.out.println("=====================================");
        System.out.println(student.toString());
        System.out.println("=====================================");
    }

    // Display multiple students
    public static void displayStudents(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.println("\n=== STUDENT LIST ===");
        System.out.println("Total students: " + students.size());
        System.out.println("=====================================");
        
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student #" + (i + 1) + ":");
            displayStudent(students.get(i));
            if (i < students.size() - 1) {
                System.out.println();
            }
        }
    }

    // Display students in a table format
    public static void displayStudentsTable(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.println("\n=== STUDENT TABLE ===");
        System.out.println("Total students: " + students.size());
        System.out.println("=====================================");
        System.out.printf("%-10s %-20s %-8s %-12s%n", "ID", "Name", "Mark", "Ranking");
        System.out.println("=====================================");
        
        for (Student student : students) {
            System.out.printf("%-10d %-20s %-8.2f %-12s%n", 
                student.getId(), 
                student.getName(), 
                student.getMark(), 
                student.getRank());
        }
        System.out.println("=====================================");
    }

    // Display search results
    public static void displaySearchResults(ArrayList<Student> results, String searchType) {
        if (results == null || results.isEmpty()) {
            System.out.println("No students found matching the search criteria.");
            return;
        }
        
        System.out.println("\n=== SEARCH RESULTS (" + searchType + ") ===");
        System.out.println("Found " + results.size() + " student(s):");
        displayStudentsTable(results);
    }

    // Display ranking statistics
    public static void displayRankingStats(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students to analyze.");
            return;
        }
        
        int fail = 0, medium = 0, good = 0, veryGood = 0, excellent = 0;
        
        for (Student student : students) {
            String rank = student.getRank();
            switch (rank) {
                case "Fail" -> fail++;
                case "Medium" -> medium++;
                case "Good" -> good++;
                case "Very Good" -> veryGood++;
                case "Excellent" -> excellent++;
            }
        }
        
        System.out.println("\n=== RANKING STATISTICS ===");
        System.out.println("Total students: " + students.size());
        System.out.println("=====================================");
        System.out.printf("%-12s: %d (%.1f%%)%n", "Fail", fail, (fail * 100.0 / students.size()));
        System.out.printf("%-12s: %d (%.1f%%)%n", "Medium", medium, (medium * 100.0 / students.size()));
        System.out.printf("%-12s: %d (%.1f%%)%n", "Good", good, (good * 100.0 / students.size()));
        System.out.printf("%-12s: %d (%.1f%%)%n", "Very Good", veryGood, (veryGood * 100.0 / students.size()));
        System.out.printf("%-12s: %d (%.1f%%)%n", "Excellent", excellent, (excellent * 100.0 / students.size()));
        System.out.println("=====================================");
    }

    // Display main menu
    public static void displayMainMenu() {
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Students");
        System.out.println("5. Sort Students");
        System.out.println("6. Display All Students");
        System.out.println("7. Display Statistics");
        System.out.println("8. Clear All Students");
        System.out.println("9. Data Import/Export");
        System.out.println("0. Exit");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

    // Display search menu
    public static void displaySearchMenu() {
        System.out.println("\n=== SEARCH OPTIONS ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Mark Range");
        System.out.println("4. Search by Ranking");
        System.out.println("0. Back to Main Menu");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

    // Display sort menu
    public static void displaySortMenu() {
        System.out.println("\n=== SORT OPTIONS ===");
        System.out.println("1. Sort by ID (Bubble Sort)");
        System.out.println("2. Sort by ID (Quick Sort)");
        System.out.println("3. Sort by ID (Merge Sort)");
        System.out.println("4. Sort by Mark (Bubble Sort)");
        System.out.println("5. Sort by Mark (Quick Sort)");
        System.out.println("6. Sort by Mark (Merge Sort)");
        System.out.println("0. Back to Main Menu");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

    // Display IO menu
    public static void displayIOMenu() {
        System.out.println("\n=== DATA IMPORT/EXPORT ===");
        System.out.println("1. Export to Markdown (.md)");
        System.out.println("2. Export to CSV (.csv)");
        System.out.println("3. Import from Markdown (.md)");
        System.out.println("4. Import from CSV (.csv)");
        System.out.println("5. Create Sample Data File");
        System.out.println("0. Back to Main Menu");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }
}
