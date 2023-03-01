package csc1035.project2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Begin main IO route
        while (true) {

            // Calls a method to display a Console Interface (the main menu)
            consoleInterface();

            // Brings programChoice into scope
            int programChoice = 0;
            try{
                programChoice = scanner.nextInt();
            }
            catch (InputMismatchException exception){
                System.out.println("Data entered not an int");
            }

            switch (programChoice){
                case 1:
                    System.out.println("SAMPLE 1");
                    System.exit(0);
                case 2:
                    System.out.println("SAMPLE 2");
                    System.exit(0);
                case 3:
                    System.out.println("SAMPLE 3");
                    System.exit(0);
                case 4:
                    System.out.println("SAMPLE 4");
                    System.exit(0);
            }
        }
    }


    /**
     * Static method to provide the main consoleInterface
     */
    public static void consoleInterface(){
        System.out.println("========================================");
        System.out.println("Quiz Options");
        System.out.println("----------------");
        System.out.println("1 - Sample 1");
        System.out.println("2 - Sample 2");
        System.out.println("3 - Sample 3");
        System.out.println("4 - Sample 4");
        System.out.println("========================================");
    }

    // =================================================================
    // ================== Manipulating Questions =======================
    // =================================================================

    public static void createQuestion(){

    }

    public static void readQuestion(){

    }

    public static void updateQuestion(){

    }

    public static void deleteQuestion(){

    }


    // =================================================================
}
