package DataRandomizer;

import java.io.IOException;

public class DataGenerator {
    // This class's sole purpose is to randomly generate student data and export to CSV files
    // The name is "Custom_DataSet.csv"
    // Student name will be "Student_" + counter
    // ID will be sequential starting from 1
    // Mark will be random float between 0 and 10

    private static final int TOTAL_STUDENTS = 100000;
    public static void generateData() {
        // Implementation of data generation and CSV export goes here
        try {
            try (java.io.FileWriter writer = new java.io.FileWriter("Data/Custom_DataSet.csv")) {
                writer.write("ID,Name,Mark\n");
                java.util.Random rand = new java.util.Random();
                for (int i = 1; i <= TOTAL_STUDENTS; i++) {
                    String name = "Student_" + i;
                    float mark = rand.nextFloat() * 10; // Random float between 0 and 10
                    writer.write(i + "," + name + "," + String.format("%.2f", mark) + "\n");
                }
            }
            System.out.println("Custom_DataSet.csv generated with " + TOTAL_STUDENTS + " students.");
        } catch (IOException e) {
            System.out.println("Error generating data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        generateData();
    }
}
