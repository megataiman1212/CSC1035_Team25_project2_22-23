package csc1035.project2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    public static final Scanner scanner = new Scanner(System.in);
    private static final QuizManager quizManager = new QuizManager();

    /**
     * Main method to provide the main run environment for the entire package.
     * Allow the user to select the functionality they would like to use.
     * @param args @todo comment what args is?
     */
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

                    manipulateQuestionData();

                    System.out.println("HERE 2");
                    System.exit(0);
                case 3:
                    System.out.println("SAMPLE 3");
                    System.exit(0);
                case 4:
                    System.out.println("SAMPLE 4");
                    System.exit(0);

                default:
                    // @TODO add a default case (once we have implemented all over cases)
                    System.out.println("SAMPLE DEFAULT");
            }
        }
    }


    /**
     * Static method to provide the main consoleInterface
     */
    public static void consoleInterface(){
        System.out.println("========================================");
        System.out.println("Program Options");
        System.out.println("----------------");
        System.out.println("1 - Create, Read, Update or delete a quiz");
        System.out.println("2 - Create, Read, Update or delete a question");
        System.out.println("3 - Sample 3");
        System.out.println("4 - Sample 4");
        System.out.println("========================================");
    }
    // =================================================================
    // =================== Manipulating Quizzes ========================
    // =================================================================

    /**
     * Static method that allows the user to select the program option
     * they would like to complete
     */
    public static void manipulateQuizData(){

        System.out.println("========================================");
        System.out.println("Chose Quiz Option");
        System.out.println("----------------");
        System.out.println("1 - Create Quiz");
        System.out.println("2 - Read Quiz");
        System.out.println("3 - Edit/Update Quiz");
        System.out.println("4 - Delete Quiz");
        System.out.println("========================================");

        // Brings choice into scope
        int choice = 0;
        try{
            choice = scanner.nextInt();
        }
        catch (InputMismatchException exception){
            System.out.println("Data entered not an int");
        }

        switch (choice){
            case 1:
                System.out.println("Create Quiz: ");
                createQuiz();
                System.exit(0);
            case 2:
                System.out.println("Read Quiz : ");
                readQuiz();
                System.exit(0);
            case 3:
                System.out.println("Edit/Update Quiz : ");
                updateQuiz();
                System.exit(0);
            case 4:
                System.out.println("Delete Quiz : ");
                deleteQuiz();
                System.exit(0);
        }
    }
    public static void createQuiz(){

    }

    public static void readQuiz(){

    }

    public static void updateQuiz(){

    }

    public static void deleteQuiz(){

    }



    // =================================================================
    // ================== Manipulating Questions =======================
    // =================================================================


    /**
     * Static method that allows the user to select the program option
     * they would like to complete
     */
    public static void manipulateQuestionData(){

        System.out.println("========================================");
        System.out.println("Chose Question Option");
        System.out.println("----------------");
        System.out.println("1 - Create Question");
        System.out.println("2 - Read Question");
        System.out.println("3 - Edit/Update Question");
        System.out.println("4 - Delete Question");
        System.out.println("========================================");

        // Brings choice into scope
        int choice = 0;
        try{
            choice = scanner.nextInt();
        }
        catch (InputMismatchException exception){
            System.out.println("Data entered not an int");
        }

        switch (choice){
            case 1:
                System.out.println("Create Question : ");
                createQuestion();
            case 2:
                System.out.println("Read Question : ");
                readQuestion();
            case 3:
                System.out.println("Edit/Update Question : ");
                updateQuestion();
            case 4:
                System.out.println("Delete Question : ");
                deleteQuestion();
        }
    }
    public static void createQuestion(){
        /*
        // Allow user input for the quizName
        System.out.println("Enter the name of the quiz : ");
        String quizName = scanner.nextLine().toLowerCase();

        // Assigns the quiz variable with the quiz found by the searchQuizByName
        Quiz quiz = QuizManager.searchQuizByName(quizName); // @todo fix static context error


         */

        Quiz quiz = QuizManager.selectQuiz();


        // If there is no quiz found, return
        if (quiz == null) {
            return;
        }



        // @todo Quiz.addQuestion(question);
    }

    public static void readQuestion(){

    }

    public static void updateQuestion(){

    }

    public static void deleteQuestion(){

    }


    // =================================================================
    // =================================================================
    // =================================================================
}
