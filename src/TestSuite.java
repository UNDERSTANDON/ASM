import IO.StudentDataIO;
import Object.Student;
import StudentManager.StudentManager;
import java.util.ArrayList;

public class TestSuite {
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== STUDENT MANAGEMENT SYSTEM - TEST SUITE ===\n");
        
        // Run all test categories
        testStudentCreation();
        testStudentManagerCRUD();
        testSearchFunctionality();
        testSortingAlgorithms();
        testRankingSystem();
        testIOFunctionality();
        testEdgeCases();
        
        // Print final results
        printTestResults();
    }
    
    private static void testStudentCreation() {
        System.out.println("1. TESTING STUDENT CREATION");
        System.out.println("============================");
        
        try {
            // Test normal student creation
            Student student1 = new Student(1, 8.5f, "Alice Johnson");
            assert student1.getId() == 1 : "ID should be 1";
            assert student1.getName().equals("Alice Johnson") : "Name should be Alice Johnson";
            assert student1.getMark() == 8.5f : "Mark should be 8.5";
            assert student1.getRank().equals("Very Good") : "Rank should be Very Good";
            testPassed("Normal student creation");
            
            // Test edge case marks
            Student student2 = new Student(2, 0.0f, "Fail Student");
            assert student2.getRank().equals("Fail") : "0.0 mark should be Fail";
            testPassed("Fail ranking (0.0)");
            
            Student student3 = new Student(3, 10.0f, "Perfect Student");
            assert student3.getRank().equals("Excellent") : "10.0 mark should be Excellent";
            testPassed("Excellent ranking (10.0)");
            
            // Test boundary values
            Student student4 = new Student(4, 5.0f, "Medium Student");
            assert student4.getRank().equals("Medium") : "5.0 mark should be Medium";
            testPassed("Medium ranking (5.0)");
            
            Student student5 = new Student(5, 6.5f, "Good Student");
            assert student5.getRank().equals("Good") : "6.5 mark should be Good";
            testPassed("Good ranking (6.5)");
            
            Student student6 = new Student(6, 7.5f, "Very Good Student");
            assert student6.getRank().equals("Very Good") : "7.5 mark should be Very Good";
            testPassed("Very Good ranking (7.5)");
            
            Student student7 = new Student(7, 9.0f, "Excellent Student");
            assert student7.getRank().equals("Excellent") : "9.0 mark should be Excellent";
            testPassed("Excellent ranking (9.0)");
            
        } catch (AssertionError e) {
            testFailed("Student creation: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testStudentManagerCRUD() {
        System.out.println("2. TESTING STUDENT MANAGER CRUD OPERATIONS");
        System.out.println("===========================================");
        
        StudentManager manager = new StudentManager();
        
        try {
            // Test Add
            Student student1 = new Student(1, 8.5f, "Alice Johnson");
            boolean addResult = manager.addStudent(student1);
            assert addResult : "Add should return true";
            assert manager.getStudentCount() == 1 : "Student count should be 1";
            testPassed("Add student");
            
            // Test duplicate ID
            Student student2 = new Student(1, 7.0f, "Duplicate ID");
            boolean duplicateResult = manager.addStudent(student2);
            assert !duplicateResult : "Duplicate ID should return false";
            assert manager.getStudentCount() == 1 : "Student count should still be 1";
            testPassed("Duplicate ID prevention");
            
            // Test Edit
            boolean editResult = manager.editStudent(1, "Alice Smith", 9.0f);
            assert editResult : "Edit should return true";
            Student editedStudent = manager.findStudentById(1);
            assert editedStudent.getName().equals("Alice Smith") : "Name should be updated";
            assert editedStudent.getMark() == 9.0f : "Mark should be updated";
            testPassed("Edit student");
            
            // Test Delete
            boolean deleteResult = manager.deleteStudent(1);
            assert deleteResult : "Delete should return true";
            assert manager.getStudentCount() == 0 : "Student count should be 0";
            testPassed("Delete student");
            
            // Test delete non-existent
            boolean deleteNonExistent = manager.deleteStudent(999);
            assert !deleteNonExistent : "Delete non-existent should return false";
            testPassed("Delete non-existent student");
            
        } catch (AssertionError e) {
            testFailed("CRUD operations: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testSearchFunctionality() {
        System.out.println("3. TESTING SEARCH FUNCTIONALITY");
        System.out.println("===============================");
        
        StudentManager manager = new StudentManager();
        
        // Add test data
        manager.addStudent(new Student(1, 8.5f, "Alice Johnson"));
        manager.addStudent(new Student(2, 6.2f, "Bob Smith"));
        manager.addStudent(new Student(3, 9.1f, "Charlie Brown"));
        manager.addStudent(new Student(4, 4.8f, "Diana Prince"));
        manager.addStudent(new Student(5, 7.8f, "Eve Wilson"));
        
        try {
            // Test search by ID
            Student found = manager.findStudentById(3);
            assert found != null : "Student with ID 3 should be found";
            assert found.getName().equals("Charlie Brown") : "Found student should be Charlie Brown";
            testPassed("Search by ID");
            
            // Test search by name
            ArrayList<Student> nameResults = manager.findStudentsByName("Alice");
            assert nameResults.size() == 1 : "Should find 1 student with 'Alice'";
            assert nameResults.get(0).getName().equals("Alice Johnson") : "Should find Alice Johnson";
            testPassed("Search by name");
            
            // Test search by mark range
            ArrayList<Student> markResults = manager.findStudentsByMarkRange(7.0f, 9.0f);
            assert markResults.size() == 2 : "Should find 2 students in range 7.0-9.0";
            testPassed("Search by mark range");
            
            // Test search by ranking
            ArrayList<Student> rankResults = manager.findStudentsByRank("Excellent");
            assert rankResults.size() == 1 : "Should find 1 Excellent student";
            testPassed("Search by ranking");
            
        } catch (AssertionError e) {
            testFailed("Search functionality: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testSortingAlgorithms() {
        System.out.println("4. TESTING SORTING ALGORITHMS");
        System.out.println("=============================");
        
        StudentManager manager = new StudentManager();
        
        // Add test data in random order
        manager.addStudent(new Student(3, 8.5f, "Charlie"));
        manager.addStudent(new Student(1, 6.2f, "Alice"));
        manager.addStudent(new Student(2, 9.1f, "Bob"));
        
        try {
            // Test Bubble Sort by ID
            manager.bubbleSortById();
            ArrayList<Student> students = manager.getAllStudents();
            assert students.get(0).getId() == 1 : "First student should have ID 1";
            assert students.get(1).getId() == 2 : "Second student should have ID 2";
            assert students.get(2).getId() == 3 : "Third student should have ID 3";
            testPassed("Bubble Sort by ID");
            
            // Test Quick Sort by Mark
            manager.quickSortByMark();
            students = manager.getAllStudents();
            assert students.get(0).getMark() == 6.2f : "First student should have mark 6.2";
            assert students.get(1).getMark() == 8.5f : "Second student should have mark 8.5";
            assert students.get(2).getMark() == 9.1f : "Third student should have mark 9.1";
            testPassed("Quick Sort by Mark");
            
            // Test Merge Sort by ID
            manager.mergeSortById();
            students = manager.getAllStudents();
            assert students.get(0).getId() == 1 : "First student should have ID 1 after merge sort";
            assert students.get(1).getId() == 2 : "Second student should have ID 2 after merge sort";
            assert students.get(2).getId() == 3 : "Third student should have ID 3 after merge sort";
            testPassed("Merge Sort by ID");
            
        } catch (AssertionError e) {
            testFailed("Sorting algorithms: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testRankingSystem() {
        System.out.println("5. TESTING RANKING SYSTEM");
        System.out.println("=========================");
        
        StudentManager manager = new StudentManager();
        
        // Add students with different marks to test all rankings
        manager.addStudent(new Student(1, 2.5f, "Fail Student"));
        manager.addStudent(new Student(2, 5.5f, "Medium Student"));
        manager.addStudent(new Student(3, 6.8f, "Good Student"));
        manager.addStudent(new Student(4, 8.2f, "Very Good Student"));
        manager.addStudent(new Student(5, 9.5f, "Excellent Student"));
        
        try {
            ArrayList<Student> students = manager.getAllStudents();
            
            // Test each ranking
            assert students.get(0).getRank().equals("Fail") : "2.5 should be Fail";
            assert students.get(1).getRank().equals("Medium") : "5.5 should be Medium";
            assert students.get(2).getRank().equals("Good") : "6.8 should be Good";
            assert students.get(3).getRank().equals("Very Good") : "8.2 should be Very Good";
            assert students.get(4).getRank().equals("Excellent") : "9.5 should be Excellent";
            testPassed("All ranking categories");
            
            // Test boundary values
            Student boundary1 = new Student(6, 5.0f, "Boundary Medium");
            assert boundary1.getRank().equals("Medium") : "5.0 should be Medium";
            testPassed("Boundary value 5.0");
            
            Student boundary2 = new Student(7, 6.5f, "Boundary Good");
            assert boundary2.getRank().equals("Good") : "6.5 should be Good";
            testPassed("Boundary value 6.5");
            
            Student boundary3 = new Student(8, 7.5f, "Boundary Very Good");
            assert boundary3.getRank().equals("Very Good") : "7.5 should be Very Good";
            testPassed("Boundary value 7.5");
            
            Student boundary4 = new Student(9, 9.0f, "Boundary Excellent");
            assert boundary4.getRank().equals("Excellent") : "9.0 should be Excellent";
            testPassed("Boundary value 9.0");
            
        } catch (AssertionError e) {
            testFailed("Ranking system: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testIOFunctionality() {
        System.out.println("6. TESTING IO FUNCTIONALITY");
        System.out.println("===========================");
        
        StudentManager manager = new StudentManager();
        
        // Add test data
        manager.addStudent(new Student(1, 8.5f, "Alice Johnson"));
        manager.addStudent(new Student(2, 6.2f, "Bob Smith"));
        manager.addStudent(new Student(3, 9.1f, "Charlie Brown"));
        
        try {
            // Test CSV export
            boolean csvExport = StudentDataIO.exportToCSV(manager, "Test/results/test_export.csv");
            assert csvExport : "CSV export should return true";
            testPassed("CSV export");
            
            // Test Markdown export
            boolean mdExport = StudentDataIO.exportToMarkdown(manager, "Test/results/test_export.md");
            assert mdExport : "Markdown export should return true";
            testPassed("Markdown export");
            
            // Test CSV import
            StudentManager newManager = new StudentManager();
            boolean csvImport = StudentDataIO.importFromCSV(newManager, "Test/data/sample_students.csv");
            assert csvImport : "CSV import should return true";
            assert newManager.getStudentCount() == 10 : "Should import 10 students";
            testPassed("CSV import");
            
            // Test file existence check
            boolean exists = StudentDataIO.fileExists("Test/data/sample_students.csv");
            assert exists : "Sample file should exist";
            testPassed("File existence check");
            
        } catch (AssertionError e) {
            testFailed("IO functionality: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testEdgeCases() {
        System.out.println("7. TESTING EDGE CASES");
        System.out.println("=====================");
        
        StudentManager manager = new StudentManager();
        
        try {
            // Test null student
            boolean nullResult = manager.addStudent(null);
            assert !nullResult : "Adding null student should return false";
            testPassed("Null student handling");
            
            // Test empty manager operations
            Student emptyResult = manager.findStudentById(1);
            assert emptyResult == null : "Finding in empty manager should return null";
            testPassed("Empty manager search");
            
            ArrayList<Student> emptySearch = manager.findStudentsByName("test");
            assert emptySearch.isEmpty() : "Search in empty manager should return empty list";
            testPassed("Empty manager name search");
            
            // Test invalid mark
            Student invalidStudent = new Student(1, -1.0f, "Invalid Mark");
            String invalidRank = invalidStudent.getRank();
            assert invalidRank == null : "Invalid mark should return null rank";
            testPassed("Invalid mark handling");
            
            // Test very large mark
            Student largeStudent = new Student(2, 15.0f, "Large Mark");
            String largeRank = largeStudent.getRank();
            assert largeRank == null : "Mark > 10 should return null rank";
            testPassed("Large mark handling");
            
        } catch (AssertionError e) {
            testFailed("Edge cases: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testPassed(String testName) {
        testsPassed++;
        System.out.println("✓ " + testName);
    }
    
    private static void testFailed(String testName) {
        testsFailed++;
        System.out.println("✗ " + testName);
    }
    
    private static void printTestResults() {
        System.out.println("=== TEST RESULTS ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        System.out.println("Success Rate: " + String.format("%.1f%%", (testsPassed * 100.0 / (testsPassed + testsFailed))));
        
        if (testsFailed == 0) {
            System.out.println("\n🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("\n⚠️  Some tests failed. Please review the implementation.");
        }
    }
}
