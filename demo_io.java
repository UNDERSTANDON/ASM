import IO.StudentDataIO;
import StudentManager.StudentManager;
import Object.Student;
import Display.DisplayInfo;

public class demo_io {
    public static void main(String[] args) {
        System.out.println("=== STUDENT MANAGEMENT SYSTEM - IO DEMO ===\n");
        
        // Create sample data file
        System.out.println("1. Creating sample data file...");
        StudentDataIO.createSampleDataFile("sample_data.csv");
        
        // Create a student manager and add some students
        System.out.println("\n2. Adding some students to manager...");
        StudentManager manager = new StudentManager();
        manager.addStudent(new Student(1, 8.5f, "Alice Johnson"));
        manager.addStudent(new Student(2, 6.2f, "Bob Smith"));
        manager.addStudent(new Student(3, 9.1f, "Charlie Brown"));
        
        // Export to markdown
        System.out.println("\n3. Exporting to markdown...");
        StudentDataIO.exportToMarkdown(manager, "test_export.md");
        
        // Export to CSV
        System.out.println("\n4. Exporting to CSV...");
        StudentDataIO.exportToCSV(manager, "test_export.csv");
        
        // Create a new manager and import from CSV
        System.out.println("\n5. Testing import functionality...");
        StudentManager newManager = new StudentManager();
        StudentDataIO.importFromCSV(newManager, "sample_data.csv");
        
        System.out.println("\n6. Displaying imported students:");
        System.out.println("Total students in new manager: " + newManager.getStudentCount());
        DisplayInfo.displayStudentsTable(newManager.getAllStudents());
        
        System.out.println("\n=== IO DEMO COMPLETED ===");
        System.out.println("Files created:");
        System.out.println("- sample_data.csv (sample data)");
        System.out.println("- test_export.md (markdown export)");
        System.out.println("- test_export.csv (CSV export)");
        System.out.println("\nYou can now use the main application to import these files!");
    }
}
