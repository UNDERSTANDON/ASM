package Object;

/**
 * Represents a student entity in the Student Management System.
 * This class manages student information including ID, name, marks, and automatic ranking calculation.
 * 
 * Ranking System:
 * - Excellent: 9.0 - 10.0
 * - Very Good: 7.5 - 8.9
 * - Good: 6.5 - 7.4
 * - Medium: 5.0 - 6.4
 * - Fail: 0.0 - 4.9
 * 
 * @author UNDERSTANDON
 * @version 1.0
 */
public class Student {
    private int id;
    private String name;
    private float mark;
    private String rank;

    public Student(int id, float mark, String name) {
        this.id = id;
        this.mark = mark;
        this.name = name;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getRank(float mark) {
        if (mark < 0 || mark > 10.0) {
            this.rank = null;
            System.out.println("Invalid marking!");
            return this.rank;
        }

        if (mark >= 9.0) {
            this.rank = "Excellent";
        } else if (mark >= 7.5) {
            this.rank = "Very Good";
        } else if (mark >= 6.5) {
            this.rank = "Good";
        } else if (mark >= 5.0) {
            this.rank = "Medium";
        } else {
            this.rank = "Fail";
        }
        return this.rank;
    }

    // Get rank for current mark
    public String getRank() {
        return getRank(this.mark);
    }

    // Get rank property
    public String getRankProperty() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "Student ID: " + this.id + "\nStudent's full name: " + this.name + "\nMarks of Student: " + this.mark + "\nStudent ranking: " + getRank();
    }

}
