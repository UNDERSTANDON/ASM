package IO;
import Object.Student;
import StudentManager.StudentManager;
import java.io.*;
import java.util.ArrayList;

public class StudentDataIO {
    
    // Export students to markdown file
    public static boolean exportToMarkdown(StudentManager studentManager, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            ArrayList<Student> students = studentManager.getAllStudents();
            
            writer.println("# Student Management System - Data Export");
            writer.println();
            writer.println("Generated on: " + java.time.LocalDateTime.now());
            writer.println("Total students: " + students.size());
            writer.println();
            
            if (students.isEmpty()) {
                writer.println("No students in the system.");
                return true;
            }
            
            writer.println("## Student List");
            writer.println();
            writer.println("| ID | Name | Mark | Ranking |");
            writer.println("|----|------|------|---------|");
            
            for (Student student : students) {
                writer.printf("| %d | %s | %.2f | %s |%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getMark(), 
                    student.getRank());
            }
            
            writer.println();
            writer.println("## Ranking Statistics");
            writer.println();
            
            // Calculate statistics
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
            
            writer.printf("- **Fail**: %d (%.1f%%)%n", fail, (fail * 100.0 / students.size()));
            writer.printf("- **Medium**: %d (%.1f%%)%n", medium, (medium * 100.0 / students.size()));
            writer.printf("- **Good**: %d (%.1f%%)%n", good, (good * 100.0 / students.size()));
            writer.printf("- **Very Good**: %d (%.1f%%)%n", veryGood, (veryGood * 100.0 / students.size()));
            writer.printf("- **Excellent**: %d (%.1f%%)%n", excellent, (excellent * 100.0 / students.size()));
            
            writer.println();
            writer.println("## Raw Data (for import)");
            writer.println();
            writer.println("```");
            for (Student student : students) {
                writer.printf("%d,%s,%.2f%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getMark());
            }
            writer.println("```");
            
            System.out.println("Data exported successfully to: " + filename);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
            return false;
        }
    }
    
    // Import students from markdown file
    public static boolean importFromMarkdown(StudentManager studentManager, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean inRawDataSection = false;
            int importedCount = 0;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                // Check if we're in the raw data section
                if (line.equals("```")) {
                    inRawDataSection = !inRawDataSection;
                    continue;
                }
                
                // Process raw data lines
                if (inRawDataSection && !line.isEmpty() && !line.equals("```")) {
                    if (importStudentFromLine(studentManager, line)) {
                        importedCount++;
                    }
                }
            }
            
            System.out.println("Imported " + importedCount + " students from: " + filename);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error importing data: " + e.getMessage());
            return false;
        }
    }
    
    // Import students from CSV file (alternative format)
    public static boolean importFromCSV(StudentManager studentManager, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int importedCount = 0;
            
            // Skip header if present
            line = reader.readLine();
            if (line != null && (line.toLowerCase().contains("id") || line.toLowerCase().contains("name"))) {
                // This is a header, skip it
            } else if (line != null) {
                // This is data, process it
                if (importStudentFromLine(studentManager, line)) {
                    importedCount++;
                }
            }
            
            // Process remaining lines
            while ((line = reader.readLine()) != null) {
                if (importStudentFromLine(studentManager, line)) {
                    importedCount++;
                }
            }
            
            System.out.println("Imported " + importedCount + " students from: " + filename);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error importing CSV data: " + e.getMessage());
            return false;
        }
    }
    
    // Export students to CSV file
    public static boolean exportToCSV(StudentManager studentManager, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            ArrayList<Student> students = studentManager.getAllStudents();
            
            // Write header
            writer.println("ID,Name,Mark,Ranking");
            
            // Write data
            for (Student student : students) {
                writer.printf("%d,%s,%.2f,%s%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getMark(), 
                    student.getRank());
            }
            
            System.out.println("Data exported successfully to: " + filename);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error exporting CSV data: " + e.getMessage());
            return false;
        }
    }
    
    // Helper method to import a single student from a line
    private static boolean importStudentFromLine(StudentManager studentManager, String line) {
        try {
            // Handle CSV format: ID,Name,Mark
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                float mark = Float.parseFloat(parts[2].trim());
                
                Student student = new Student(id, mark, name);
                return studentManager.addStudent(student);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
        }
        return false;
    }
    
    // Create sample data file
    public static void createSampleDataFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ID,Name,Mark");
            writer.println("1,Alice Johnson,8.5");
            writer.println("2,Bob Smith,6.2");
            writer.println("3,Charlie Brown,9.1");
            writer.println("4,Diana Prince,4.8");
            writer.println("5,Eve Wilson,7.8");
            writer.println("6,Frank Miller,5.5");
            writer.println("7,Grace Lee,8.9");
            writer.println("8,Henry Davis,6.8");
            writer.println("9,Ivy Chen,7.2");
            writer.println("10,Jack Wilson,9.5");
            
            System.out.println("Sample data file created: " + filename);
            
        } catch (IOException e) {
            System.err.println("Error creating sample data file: " + e.getMessage());
        }
    }
    
    // Check if file exists
    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }
    
    // Get file extension
    public static String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0 && lastDot < filename.length() - 1) {
            return filename.substring(lastDot + 1).toLowerCase();
        }
        return "";
    }
}
