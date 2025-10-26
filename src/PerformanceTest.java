import StudentManager.StudentManager;
import Object.Student;
import java.util.Random;

public class PerformanceTest {
    private static final int[] TEST_SIZES = {10, 25, 50, 100, 250, 500, 1000};
    
    public static void main(String[] args) {
        System.out.println("=== STUDENT MANAGEMENT SYSTEM - PERFORMANCE TEST ===\n");
        
        System.out.println("Testing sorting algorithm performance with different dataset sizes...\n");
        
        // Print header
        System.out.printf("%-12s %-15s %-15s %-15s%n", "Size", "Bubble Sort", "Quick Sort", "Merge Sort");
        System.out.println("------------------------------------------------------------");
        
        for (int size : TEST_SIZES) {
            testSortingPerformance(size);
        }
        
        System.out.println("\n=== PERFORMANCE TEST COMPLETED ===");
        System.out.println("Note: Times are in milliseconds and may vary based on system performance.");
    }
    
    private static void testSortingPerformance(int size) {
        // Create test data
        StudentManager manager = createTestData(size);
        
        // Test Bubble Sort by ID
        long bubbleTime = measureSortingTime(() -> manager.bubbleSortById(), manager, size);
        
        // Test Quick Sort by ID
        long quickTime = measureSortingTime(() -> manager.quickSortById(), manager, size);
        
        // Test Merge Sort by ID
        long mergeTime = measureSortingTime(() -> manager.mergeSortById(), manager, size);
        
        // Print results
        System.out.printf("%-12d %-15d %-15d %-15d%n", size, bubbleTime, quickTime, mergeTime);
    }
    
    private static long measureSortingTime(Runnable sortOperation, StudentManager manager, int size) {
        // Reset data to unsorted state
        resetToUnsorted(manager, size);
        
        long startTime = System.nanoTime();
        sortOperation.run();
        long endTime = System.nanoTime();
        
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
    
    private static StudentManager createTestData(int size) {
        StudentManager manager = new StudentManager();
        Random random = new Random(42); // Fixed seed for consistent results
        
        for (int i = 1; i <= size; i++) {
            // Create students with random IDs and marks
            int id = random.nextInt(size * 2) + 1; // Random ID to ensure unsorted
            float mark = random.nextFloat() * 10.0f; // Random mark 0-10
            String name = "Student" + i;
            
            // Ensure unique ID
            while (manager.findStudentById(id) != null) {
                id = random.nextInt(size * 2) + 1;
            }
            
            manager.addStudent(new Student(id, mark, name));
        }
        
        return manager;
    }
    
    private static void resetToUnsorted(StudentManager manager, int size) {
        // Clear and recreate with same data but different order
        manager.clearAllStudents();
        Random random = new Random(42);
        
        for (int i = 1; i <= size; i++) {
            int id = random.nextInt(size * 2) + 1;
            float mark = random.nextFloat() * 10.0f;
            String name = "Student" + i;
            
            while (manager.findStudentById(id) != null) {
                id = random.nextInt(size * 2) + 1;
            }
            
            manager.addStudent(new Student(id, mark, name));
        }
    }
}
