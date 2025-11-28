import IO.StudentDataIO;
import Object.Student;
import StudentManager.StudentManager;
import java.io.File;
import java.util.ArrayList;

public class HeapSortComparisonTest {
    public static void main(String[] args) {
        System.out.println("=== HEAP SORT vs MERGE SORT vs QUICK SORT COMPARISON ===\n");

        // Load datasets
        ArrayList<String> largeDataset = StudentDataIO.loadDataSet("Data/large_dataset.csv");
        ArrayList<String> extremeDataset = StudentDataIO.loadDataSet("Data/Custom_DataSet.csv");

        if (largeDataset == null || largeDataset.isEmpty()) {
            System.out.println("Error: Could not load large_dataset.csv");
            return;
        }
        if (extremeDataset == null || extremeDataset.isEmpty()) {
            System.out.println("Error: Could not load extreme_large_dataset.csv");
            return;
        }

        System.out.println("Large Dataset Size: " + largeDataset.size());
        System.out.println("Extreme Dataset Size: " + extremeDataset.size() + "\n");

        // Test with both datasets
        StringBuilder timesMarkdown = new StringBuilder();
        StringBuilder timesCSV = new StringBuilder();
        StringBuilder spaceMarkdown = new StringBuilder();
        StringBuilder spaceCSV = new StringBuilder();

        timesMarkdown.append("# Time Complexity Comparison\n\n");
        timesMarkdown.append("| Dataset | Size | Top K | Heap (ms) | Merge Sort (ms) | Quick Sort (ms) |\n");
        timesMarkdown.append("|---------|------|-------|-----------|-----------------|------------------|\n");

        timesCSV.append("Dataset,Size,TopK,Heap(ms),MergeSort(ms),QuickSort(ms)\n");

        spaceMarkdown.append("# Space Complexity Analysis\n\n");
        spaceMarkdown.append("| Algorithm | Space Complexity | Notes |\n");
        spaceMarkdown.append("|-----------|------------------|---------|\n");
        spaceMarkdown.append("| Max-Heap (getTopK) | O(k) | Only stores top K students |\n");
        spaceMarkdown.append("| Merge Sort | O(n) | Requires temporary arrays for merging |\n");
        spaceMarkdown.append("| Quick Sort | O(log n) | In-place sorting, recursion stack |\n");

        spaceCSV.append("Algorithm,SpaceComplexity,Notes\n");
        spaceCSV.append("Max-Heap,O(k),Only stores top K students\n");
        spaceCSV.append("Merge Sort,O(n),Requires temporary arrays for merging\n");
        spaceCSV.append("Quick Sort,O(log n),In-place sorting recursion stack\n");

        int k = 10; // Get top 10 students

        // Test with large dataset
        System.out.println("--- Testing with Large Dataset (" + largeDataset.size() + " students) ---");
        testDataset(largeDataset, "Large", k, timesMarkdown, timesCSV);

        // Test with extreme dataset
        System.out.println("\n--- Testing with Extreme Dataset (" + extremeDataset.size() + " students) ---");
        testDataset(extremeDataset, "Extreme", k, timesMarkdown, timesCSV);

        // Export results
        exportResults(timesMarkdown, timesCSV, spaceMarkdown, spaceCSV);
    }

    private static void testDataset(ArrayList<String> dataset, String datasetName, int k,
                                     StringBuilder timesMarkdown, StringBuilder timesCSV) {
        // Create managers for each algorithm
        StudentManager heapManager = new StudentManager();
        StudentManager mergeManager = new StudentManager();
        StudentManager quickManager = new StudentManager();

        // Load data into all managers
        for (String line : dataset) {
            String[] parts = line.split(",");
            if (parts.length == 3 && !parts[0].equals("ID")) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    float mark = Float.parseFloat(parts[2]);
                    Student student = new Student(id, mark, name);
                    heapManager.addStudentDirect(student);
                    mergeManager.addStudentDirect(student);
                    quickManager.addStudentDirect(student);
                } catch (NumberFormatException e) {
                    // Skip malformed lines
                }
            }
        }

        // Test Heap (getTopK)
        long heapStart = System.nanoTime();
        ArrayList<Student> topStudents = heapManager.getTopStudentsByMarkWithMaxHeap(k);
        long heapEnd = System.nanoTime();
        long heapTime = (heapEnd - heapStart) / 1_000_000;

        // Test Merge Sort (entire dataset)
        long mergeStart = System.nanoTime();
        mergeManager.mergeSortByMark();
        long mergeEnd = System.nanoTime();
        long mergeTime = (mergeEnd - mergeStart) / 1_000_000;

        // Test Quick Sort (entire dataset)
        long quickStart = System.nanoTime();
        quickManager.quickSortByMark();
        long quickEnd = System.nanoTime();
        long quickTime = (quickEnd - quickStart) / 1_000_000;

        // Print to console
        System.out.printf("Heap (top %d): %d ms\n", k, heapTime);
        System.out.printf("Merge Sort: %d ms\n", mergeTime);
        System.out.printf("Quick Sort: %d ms\n", quickTime);

        // Top students
        System.out.println("Top " + k + " students by mark:");
        for (int i = 0; i < topStudents.size(); i++) {
            Student s = topStudents.get(i);
            System.out.printf("  %d. ID:%d Name:%s Mark:%.1f\n", i + 1, s.getId(), s.getName(), s.getMark());
        }

        // Append to markdown and CSV
        timesMarkdown.append(String.format("| %s | %d | %d | %d | %d | %d |\n",
                datasetName, dataset.size(), k, heapTime, mergeTime, quickTime));
        timesCSV.append(String.format("%s,%d,%d,%d,%d,%d\n",
                datasetName, dataset.size(), k, heapTime, mergeTime, quickTime));
    }

    private static void exportResults(StringBuilder timesMarkdown, StringBuilder timesCSV,
                                      StringBuilder spaceMarkdown, StringBuilder spaceCSV) {
        new File("Test/results").mkdirs();

        // Export time results
        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/HeapSort_Comparison_Times.md")) {
            w.write(timesMarkdown.toString());
            w.write("\n## Notes\n\n");
            w.write("- Heap: getTopStudentsByMark(k) - extracts top K students\n");
            w.write("- Merge Sort: Full sort by mark (descending)\n");
            w.write("- Quick Sort: Full sort by mark (descending)\n");
            w.write("- Date: " + java.time.LocalDateTime.now() + "\n");
            System.out.println("\nResults exported to Test/results/HeapSort_Comparison_Times.md");
        } catch (java.io.IOException e) {
            System.out.println("Error writing times markdown: " + e.getMessage());
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/HeapSort_Comparison_Times.csv")) {
            w.write(timesCSV.toString());
            System.out.println("Results exported to Test/results/HeapSort_Comparison_Times.csv");
        } catch (java.io.IOException e) {
            System.out.println("Error writing times CSV: " + e.getMessage());
        }

        // Export space complexity results
        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/HeapSort_Comparison_Space.md")) {
            w.write(spaceMarkdown.toString());
            w.write("\n## Summary\n\n");
            w.write("- Max-Heap approach is most space-efficient when retrieving only top K students\n");
            w.write("- For complete sorting, Quick Sort uses least extra space (in-place)\n");
            w.write("- Merge Sort guarantees O(n log n) time but uses O(n) extra space\n");
            w.write("- Date: " + java.time.LocalDateTime.now() + "\n");
            System.out.println("Results exported to Test/results/HeapSort_Comparison_Space.md");
        } catch (java.io.IOException e) {
            System.out.println("Error writing space markdown: " + e.getMessage());
        }

        try (java.io.FileWriter w = new java.io.FileWriter("Test/results/HeapSort_Comparison_Space.csv")) {
            w.write(spaceCSV.toString());
            System.out.println("Results exported to Test/results/HeapSort_Comparison_Space.csv");
        } catch (java.io.IOException e) {
            System.out.println("Error writing space CSV: " + e.getMessage());
        }
    }
}
