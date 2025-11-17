import Object.Student;
import StudentManager.StudentManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerformanceTest {
    private static final int[] TEST_SIZES = { 10, 25, 50, 100, 250, 500, 1000 };
    // private static final int[] TEST_SIZES = { 10000 }; // Just for testing
    // private static final int[] TEST_SIZES = { 10, 25, 50, 100, 250, 500, 1000, 2500, 5000, 10000, 100000 }; // Full range

    // If sample size is 10000 or above, skip Bubble Sort to avoid long wait times
    public static void main(String[] args) {
        System.out.println("=== STUDENT MANAGEMENT SYSTEM - PERFORMANCE TEST ===\n");

        System.out.println("Testing sorting algorithm performance with different dataset sizes...\n");

        // Print header
        System.out.printf("%-12s %-15s %-15s %-15s%n", "Size", "Bubble Sort", "Quick Sort", "Merge Sort");
        System.out.println("------------------------------------------------------------");

        for (var size : TEST_SIZES) {
            testSortingPerformance(size);
        }

        for (var size : TEST_SIZES) {
            testingSortingResourceUsage(size);
        }

        System.out.println("\n=== PERFORMANCE TEST COMPLETED ===");
        System.out.println("Note: Times are in milliseconds and may vary based on system performance.");
        exportToMarkdownResult();
    }

    private static void testSortingPerformance(int size) {
        // Create test data
        StudentManager baseData = createTestData(size);

        // Get Clone foreach sort
        StudentManager managerQuickSort = baseData.cloner();
        StudentManager managerMergeSort = baseData.cloner();
        StudentManager managerBubbleSort = baseData.cloner();

        // Test Bubble Sort by ID (skip for large sizes)
        long bubbleTime = -1L;
        if (size <= 10000) {
            bubbleTime = measureSortingTime(() -> managerBubbleSort.silentBubbleSortById(), managerBubbleSort, size);
        }

        // Test Quick Sort by ID
        long quickTime = measureSortingTime(() -> managerQuickSort.silentQuickSortById(), managerQuickSort, size);

        // Test Merge Sort by ID
        long mergeTime = measureSortingTime(() -> managerMergeSort.silentMergeSortById(), managerMergeSort, size);

        // Print results (show SKIP for bubble if not run)
        String bubbleStr = (bubbleTime < 0) ? "SKIP" : String.valueOf(bubbleTime);
        System.out.printf("%-12d %-15s %-15d %-15d%n", size, bubbleStr, quickTime, mergeTime);
    }

    private static long measureSortingTime(Runnable sortOperation, StudentManager manager, int size) {
        // Reset data to unsorted state
        resetToUnsorted(manager, size);

        var startTime = System.nanoTime();
        sortOperation.run();
        var endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }

    private static StudentManager createTestData(int size) {
        StudentManager manager = new StudentManager();
        Random random = new Random(42); // Fixed seed for consistent results

        // Generate a shuffled list of unique IDs in range [1, size*2]
        int maxId = Math.max(size * 2, size + 1);
        List<Integer> ids = new ArrayList<>(maxId);
        for (int i = 1; i <= maxId; i++)
            ids.add(i);
        Collections.shuffle(ids, random);

        for (var i = 0; i < size; i++) {
            int id = ids.get(i);
            float mark = random.nextFloat() * 10.0f; // Random mark 0-10
            String name = "Student" + (i + 1);
            manager.addStudentDirect(new Student(id, mark, name));
        }

        return manager;
    }

    private static void resetToUnsorted(StudentManager manager, int size) {
        // Clear and recreate with same data but different order
        manager.clearAllStudents();
        Random random = new Random(42);

        int maxId = Math.max(size * 2, size + 1);
        List<Integer> ids = new ArrayList<>(maxId);
        for (int i = 1; i <= maxId; i++)
            ids.add(i);
        Collections.shuffle(ids, random);

        for (var i = 0; i < size; i++) {
            int id = ids.get(i);
            float mark = random.nextFloat() * 10.0f;
            String name = "Student" + (i + 1);
            manager.addStudentDirect(new Student(id, mark, name));
        }
    }

    private static void testingSortingResourceUsage(int size) {
        // Create test data
        StudentManager baseData = createTestData(size);

        // Get Clone foreach sort
        StudentManager managerQuickSort = baseData.cloner();
        StudentManager managerMergeSort = baseData.cloner();
        StudentManager managerBubbleSort = baseData.cloner();

        // Test Bubble Sort by ID
        measureResourceUsage(() -> managerBubbleSort.bubbleSortById(), managerBubbleSort);

        // Test Quick Sort by ID
        measureResourceUsage(() -> managerQuickSort.quickSortById(), managerQuickSort);

        // Test Merge Sort by ID
        measureResourceUsage(() -> managerMergeSort.mergeSortById(), managerMergeSort);
    }

    private static void measureResourceUsage(Runnable sortOperation, StudentManager sm) {
        Runtime runtime = Runtime.getRuntime();
        // Optional: suggest a quick GC to reduce noise (not guaranteed)
        System.gc();

        long totalBefore = runtime.totalMemory();
        long freeBefore = runtime.freeMemory();
        long usedBefore = totalBefore - freeBefore;

        // Run the sort
        sortOperation.run();

        long totalAfter = runtime.totalMemory();
        long freeAfter = runtime.freeMemory();
        long usedAfter = totalAfter - freeAfter;

        String label = sm.getClass().getSimpleName();
        int count = sm.getAllStudents().size();

        System.out.println("\n=== RESOURCE USAGE FOR " + label + " (n=" + count + ") ===");
        System.out.println("Used Memory Before: " + (usedBefore / (1024 * 1024)) + " MB");
        System.out.println("Used Memory After:  " + (usedAfter / (1024 * 1024)) + " MB");
        System.out.println("Delta (After - Before): " + ((usedAfter - usedBefore) / (1024 * 1024)) + " MB");
    }

    private static void exportProcessingTimes() {
        new java.io.File("Test/results").mkdirs();

        StringBuilder md = new StringBuilder();
        StringBuilder csv = new StringBuilder();

        md.append("# Processing Time Results\n\n");
        md.append("| Size | Bubble Sort (ms) | Quick Sort (ms) | Merge Sort (ms) |\n");
        md.append("|------|------------------|-----------------|------------------|\n");

        csv.append("Size,Bubble(ms),Quick(ms),Merge(ms)\n");

        for (var size : TEST_SIZES) {
            StudentManager baseData = createTestData(size);

            StudentManager tQuick = baseData.cloner();
            StudentManager tMerge = baseData.cloner();
            StudentManager tBubble = baseData.cloner();

            long bubbleTime = -1L;
            // Skip bubble sort for large sizes to avoid very long runs
            if (size <= 10000) {
                bubbleTime = measureSortingTime(() -> tBubble.bubbleSortById(), tBubble, size);
            }
            long quickTime = measureSortingTime(() -> tQuick.quickSortById(), tQuick, size);
            long mergeTime = measureSortingTime(() -> tMerge.mergeSortById(), tMerge, size);

            String bubbleStr = (bubbleTime < 0) ? "SKIP" : String.valueOf(bubbleTime);
            md.append(String.format("| %d | %s | %d | %d |\n", size, bubbleStr, quickTime, mergeTime));
            csv.append(String.format("%d,%s,%d,%d\n", size, bubbleStr, quickTime, mergeTime));
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/PerformanceTest_Times.md")) {
            w.write(md.toString());
            w.write("\n## Test Configuration\n\n");
            w.write("- Test Seed: 42 (fixed for consistency)\n");
            w.write("- Time Unit: Milliseconds (ms)\n");
            w.write("- Date: " + java.time.LocalDateTime.now() + "\n");
        } catch (java.io.IOException e) {
            System.out.println("Error writing times markdown: " + e.getMessage());
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/PerformanceTest_Times.csv")) {
            w.write(csv.toString());
        } catch (java.io.IOException e) {
            System.out.println("Error writing times CSV: " + e.getMessage());
        }
    }

    private static void exportResourceUsageReports() {
        new java.io.File("Test/results").mkdirs();

        StringBuilder md = new StringBuilder();
        StringBuilder csv = new StringBuilder();

        md.append("# Resource Usage Results\n\n");
        md.append("| Size | Bubble Mem Δ (MB) | Quick Mem Δ (MB) | Merge Mem Δ (MB) |\n");
        md.append("|------|------------------:|------------------:|------------------:|\n");

        csv.append("Size,BubbleMemDelta(MB),QuickMemDelta(MB),MergeMemDelta(MB)\n");

        for (var size : TEST_SIZES) {
            StudentManager baseData = createTestData(size);

            StudentManager mQuick = baseData.cloner();
            StudentManager mMerge = baseData.cloner();
            StudentManager mBubble = baseData.cloner();

            Runtime runtime = Runtime.getRuntime();

            String bubbleMemStr;
            long bubbleMemDelta;
            if (size <= 10000) {
                System.gc();
                long usedBefore = runtime.totalMemory() - runtime.freeMemory();
                mBubble.bubbleSortById();
                long usedAfter = runtime.totalMemory() - runtime.freeMemory();
                bubbleMemDelta = (usedAfter - usedBefore) / (1024 * 1024);
                bubbleMemStr = String.valueOf(bubbleMemDelta);
            } else {
                bubbleMemStr = "SKIP";
            }

            System.gc();
            long usedBefore = runtime.totalMemory() - runtime.freeMemory();
            mQuick.quickSortById();
            long usedAfter = runtime.totalMemory() - runtime.freeMemory();
            long quickMemDelta = (usedAfter - usedBefore) / (1024 * 1024);

            System.gc();
            usedBefore = runtime.totalMemory() - runtime.freeMemory();
            mMerge.mergeSortById();
            usedAfter = runtime.totalMemory() - runtime.freeMemory();
            long mergeMemDelta = (usedAfter - usedBefore) / (1024 * 1024);

            md.append(String.format("| %d | %s | %d | %d |\n", size, bubbleMemStr, quickMemDelta, mergeMemDelta));
            csv.append(String.format("%d,%s,%d,%d\n", size, bubbleMemStr, quickMemDelta, mergeMemDelta));
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/PerformanceTest_Resources.md")) {
            w.write(md.toString());
            w.write("\n## Test Configuration\n\n");
            w.write("- Test Seed: 42 (fixed for consistency)\n");
            w.write("- Memory Delta in Megabytes (MB)\n");
            w.write("- Date: " + java.time.LocalDateTime.now() + "\n");
        } catch (java.io.IOException e) {
            System.out.println("Error writing resources markdown: " + e.getMessage());
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/PerformanceTest_Resources.csv")) {
            w.write(csv.toString());
        } catch (java.io.IOException e) {
            System.out.println("Error writing resources CSV: " + e.getMessage());
        }
    }

    // Wrapper kept for backward compatibility
    private static void exportToMarkdownResult() {
        exportProcessingTimes();
        exportResourceUsageReports();
    }

    
}
