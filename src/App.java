import Display.DisplayProcessor;
import StudentManager.StudentManager;
import java.util.Scanner;

public class App {
    private static final StudentManager studentManager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System!");
        System.out.println("This system allows you to manage student records with various operations.");
        System.out.println("Use the Data Import/Export feature to load sample data or your own datasets.");
        
        DisplayProcessor processor = new DisplayProcessor(studentManager, scanner);
        
        boolean running = true;
        while (running) {
            running = processor.processMainMenu();
        }
        scanner.close();
    }

}
