import Display.DisplayProcessor;
import StudentManager.StudentManager;
import java.util.Scanner;

/**
 * Main entry point for the Student Management System application.
 * Initializes core components and manages the main program loop.
 * 
 * System Components:
 * - StudentManager: Data management and operations
 * - DisplayProcessor: User interface and input handling
 * - Scanner: User input handling
 * 
 * Program Flow:
 * 1. Initialize system components
 * 2. Display welcome message
 * 3. Enter main program loop
 * 4. Process user input
 * 5. Clean up resources on exit
 * 
 * @author UNDERSTANDON
 * @version 1.0
 */
public class App {
    private static final StudentManager studentManager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (scanner) {
            System.out.println("Welcome to Student Management System!");
            System.out.println("This system allows you to manage student records with various operations.");
            System.out.println("Use the Data Import/Export feature to load sample data or your own datasets.");
            
            DisplayProcessor processor = new DisplayProcessor(studentManager, scanner);
            
            boolean running = true;
            while (running) {
                running = processor.processMainMenu();
            }
        }
    }

}
