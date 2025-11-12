package StudentManager;
import Object.Student;
import java.util.ArrayList;

/**
 * Manages the collection of students and provides CRUD operations, search functionality,
 * and sorting algorithms for student data manipulation.
 * 
 * Features:
 * - CRUD operations (Create, Read, Update, Delete)
 * - Search operations (by ID, name, mark range, ranking)
 * - Multiple sorting algorithms:
 *   - Bubble Sort (O(n²))
 *   - Quick Sort (O(n log n))
 *   - Merge Sort (O(n log n))
 * 
 * Performance Notes:
 * - Bubble Sort: Simple but inefficient for large datasets
 * - Quick Sort: Best average-case performance
 * - Merge Sort: Consistent performance, stable sort
 * 
 * @author UNDERSTANDON
 * @version 1.0
 */
public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();

    // CRUD

    // Add a new student
    public boolean addStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Cannot add null student.");
            return false;
        }
        
        // Check if student ID already exists
        if (findStudentById(student.getId()) != null) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists.");
            return false;
        }
        
        students.add(student);
        System.out.println("Student added successfully!");
        return true;
    }

    public void addStudentDirect(Student student) {
        if (isAddable(student))
            students.add(student);
        else
            System.out.println("Failed to add student.");
    }

    public boolean isAddable(Student student) {
        if (student == null) {
            System.out.println("Error: Cannot add null student.");
            return false;
        }
        
        // Check if student ID already exists
        if (findStudentById(student.getId()) != null) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists.");
            return false;
        }
        
        return true;
    }

    // Edit an existing student
    public boolean editStudent(int id, String newName, float newMark) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return false;
        }
        
        student.setName(newName);
        student.setMark(newMark);
        student.getRank(newMark); // Update ranking
        System.out.println("Student updated successfully!");
        return true;
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return false;
        }
        
        students.remove(student);
        System.out.println("Student deleted successfully!");
        return true;
    }

    // Search by ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Search by name (case-insensitive)
    public ArrayList<Student> findStudentsByName(String name) {
        ArrayList<Student> results = new ArrayList<>();
        String searchName = name.toLowerCase();
        
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchName)) {
                results.add(student);
            }
        }
        return results;
    }

    // Search by mark range
    public ArrayList<Student> findStudentsByMarkRange(float minMark, float maxMark) {
        ArrayList<Student> results = new ArrayList<>();
        
        for (Student student : students) {
            if (student.getMark() >= minMark && student.getMark() <= maxMark) {
                results.add(student);
            }
        }
        return results;
    }

    // Search by ranking
    public ArrayList<Student> findStudentsByRank(String rank) {
        ArrayList<Student> results = new ArrayList<>();
        
        for (Student student : students) {
            if (student.getRank(student.getMark()).equals(rank)) {
                results.add(student);
            }
        }
        return results;
    }

    // Bubble Sort by ID
    public void bubbleSortById() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by ID using Bubble Sort.");
    }

    

    // Bubble Sort by Mark
    public void bubbleSortByMark() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMark() > students.get(j + 1).getMark()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by Mark using Bubble Sort.");
    }

    // Quick Sort by ID
    public void quickSortById() {
        if (!students.isEmpty()) {
            quickSortByIdHelper(0, students.size() - 1);
            System.out.println("Students sorted by ID using Quick Sort.");
        }
    }

    

    private void quickSortByIdHelper(int low, int high) {
        if (low < high) {
            int pi = partitionById(low, high);
            quickSortByIdHelper(low, pi - 1);
            quickSortByIdHelper(pi + 1, high);
        }
    }

    private int partitionById(int low, int high) {
        int pivot = students.get(high).getId();
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (students.get(j).getId() <= pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        
        return i + 1;
    }

    // Quick Sort by Mark
    public void quickSortByMark() {
        if (!students.isEmpty()) {
            quickSortByMarkHelper(0, students.size() - 1);
            System.out.println("Students sorted by Mark using Quick Sort.");
        }
    }

    private void quickSortByMarkHelper(int low, int high) {
        if (low < high) {
            int pi = partitionByMark(low, high);
            quickSortByMarkHelper(low, pi - 1);
            quickSortByMarkHelper(pi + 1, high);
        }
    }

    private int partitionByMark(int low, int high) {
        float pivot = students.get(high).getMark();
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (students.get(j).getMark() <= pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        
        return i + 1;
    }

    // Merge Sort by ID
    public void mergeSortById() {
        if (!students.isEmpty()) {
            mergeSortByIdHelper(0, students.size() - 1);
            System.out.println("Students sorted by ID using Merge Sort.");
        }
    }

    private void mergeSortByIdHelper(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortByIdHelper(left, mid);
            mergeSortByIdHelper(mid + 1, right);
            mergeById(left, mid, right);
        }
    }

    private void mergeById(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList<Student> leftArray = new ArrayList<>();
        ArrayList<Student> rightArray = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftArray.add(students.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArray.add(students.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray.get(i).getId() <= rightArray.get(j).getId()) {
                students.set(k, leftArray.get(i));
                i++;
            } else {
                students.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            students.set(k, leftArray.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            students.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    

    // Merge Sort by Mark
    public void mergeSortByMark() {
        if (!students.isEmpty()) {
            mergeSortByMarkHelper(0, students.size() - 1);
            System.out.println("Students sorted by Mark using Merge Sort.");
        }
    }

    private void mergeSortByMarkHelper(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortByMarkHelper(left, mid);
            mergeSortByMarkHelper(mid + 1, right);
            mergeByMark(left, mid, right);
        }
    }

    private void mergeByMark(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList<Student> leftArray = new ArrayList<>();
        ArrayList<Student> rightArray = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftArray.add(students.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArray.add(students.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray.get(i).getMark() <= rightArray.get(j).getMark()) {
                students.set(k, leftArray.get(i));
                i++;
            } else {
                students.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            students.set(k, leftArray.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            students.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    // Get all students
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Get student count
    public int getStudentCount() {
        return students.size();
    }

    // Clear all students
    public void clearAllStudents() {
        students.clear();
        System.out.println("All students cleared.");
    }

    // For Testing Purposes Only

    // Cloner 
    public StudentManager clone() {
        StudentManager copyData = new StudentManager();
        for (var i : this.getAllStudents()) {
            copyData.addStudentDirect(new Student(i.getId(), i.getMark(), i.getName()));
            
        }
        return copyData;
    }
    
    
    // Silent Merge Sort by ID
    public void silentMergeSortById() {
        if (!students.isEmpty()) {
            mergeSortByIdHelper(0, students.size() - 1);
        }
    }
    
    // Silent Quick Sort by ID
    public void silentQuickSortById() {
        if (!students.isEmpty()) {
            quickSortByIdHelper(0, students.size() - 1);
        }
    }
    
    // Silent Bubble Sort by ID
    public void silentBubbleSortById() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}
