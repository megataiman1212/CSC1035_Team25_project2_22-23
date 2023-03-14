package csc1035.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import csc1035.project2.question.MultipleChoiceQuestion;
import csc1035.project2.question.ShortResponseQuestion;
import csc1035.project2.question.Topic;

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
                    manipulateQuizData();
                    break;
                case 2:
                    System.out.println("SAMPLE 2");
                    manipulateQuestionData();
                    break;
                case 3:
                    System.out.println("SAMPLE 3");
                    executeQuiz();
                    break;
                case 4:
                    System.out.println("SAMPLE 4");
                    break;

                // @todo Exit Case
                default:
                    System.out.println("=====================");
                    System.out.println("Choice Not Recognised");
                    System.out.println("=====================");

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
        System.out.println("3 - Attempt a quiz");
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
                break;
            case 2:
                System.out.println("Read Quiz : ");
                readQuiz();
                break;
            case 3:
                System.out.println("Edit/Update Quiz : ");
                updateQuiz();
                break;
            case 4:
                System.out.println("Delete Quiz : ");
                deleteQuiz();
                break;
            default:
                System.out.println("=====================");
                System.out.println("Choice Not Recognised");
                System.out.println("=====================");
        }
    }
    public static void createQuiz(){
        Scanner scannerOverride = new Scanner(System.in);

        // Allow user input for the name of the quiz
        System.out.println("Enter the quiz name : ");
        String quizName = scannerOverride.nextLine().toLowerCase();

        // Call the method, addQuiz with a new quiz instance
        QuizManager.addQuiz(new Quiz(quizName));

        System.out.println("Quiz Successfully Added");
    }

    public static void readQuiz(){
        System.out.println(QuizManager.selectQuiz());
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

        // Returns a Quiz object based on user inputs from
        System.out.println("Select a quiz to edit to");
        Quiz quiz = QuizManager.selectQuiz();

        // If there is no quiz found, return
        if (quiz == null) {
            return;
        }

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
                createQuestion(quiz);
                break;
            case 2:
                System.out.println("Read Question : ");
                readQuestion(quiz);
                break;
            case 3:
                System.out.println("Edit/Update Question : ");
                updateQuestion(quiz);
                break;
            case 4:
                System.out.println("Delete Question : ");
                deleteQuestion(quiz);
                break;
            default:
                System.out.println("=====================");
                System.out.println("Choice Not Recognised");
                System.out.println("=====================");
        }
    }

    public static void createQuestion(Quiz quiz){
        // Get question type
        System.out.println("Enter the question type (MCQ, SRQ):");
        String type = scanner.nextLine().toLowerCase();

        // Validate question type input
        while (!type.equals("mcq") && !type.equals("srq")) {
            System.out.println("Invalid question type");
            System.out.println("Please enter MCQ or SRQ");
            type = scanner.nextLine().toLowerCase();
        }

        // Get question text
        System.out.println("Enter the question prompt :");
        String questionText = scanner.nextLine().trim();

        // Validate question text input
        while (questionText.isEmpty()) {
            System.out.println("Question text cannot be empty");
            System.out.println("Enter the question prompt : ");
            questionText = scanner.nextLine().trim();
        }

        // Get topic
        System.out.println("Enter the topic (Programming, Databases, Architecture, Maths):");
        String topicStr = scanner.nextLine().toUpperCase();

        // Validate topic input
        Topic topic;
        try {
            topic = Topic.valueOf(topicStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid topic");
            return;
        }

        // Prompt other inputs based on question type
        if (type.equals("mcq")) {

            // Get correctAnswer
            System.out.println("Enter the correct answer : ");
            String correctAnswer = scanner.nextLine().trim();

            // Validate correctAnswer input
            while (correctAnswer.isEmpty()) {
                System.out.println("The correct answer cannot be empty");
                System.out.println("Enter the correct answer : ");
                correctAnswer = scanner.nextLine().trim();
            }

            // Get choices
            System.out.println("Enter the incorrect choices (separated by comma):");
            String wrongAnswersStr = scanner.nextLine().trim();

            // Validate choices input
            while (wrongAnswersStr.isEmpty()) {
                System.out.println("Choices cannot be empty");
                System.out.println("Enter the incorrect choices (separated by comma):");
                wrongAnswersStr = scanner.nextLine().trim();
            }

            MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(questionText, topic, correctAnswer, wrongAnswersStr);
            quiz.addQuestion(mcq);

            // @todo add mcq to the database

            }

        else {
            // Get correctAnswer
            System.out.println("Enter the correct answer : ");
            String correctAnswer = scanner.nextLine().trim();

            // Validate correctAnswer input
            while (correctAnswer.isEmpty()) {
                System.out.println("The correct answer cannot be empty");
                System.out.println("Enter the correct answer : ");
                correctAnswer = scanner.nextLine().trim();
            }

            ShortResponseQuestion srq = new ShortResponseQuestion(questionText, topic, Pattern.compile(correctAnswer,Pattern.CASE_INSENSITIVE));

            quiz.addQuestion(srq);

            // @todo add srq to the database
        }
    }

    public static void readQuestion(Quiz quiz){

    }

    public static void updateQuestion(Quiz quiz){

    }

    public static void deleteQuestion(Quiz quiz){

    }


    // =================================================================
    // =================================================================
    // =================================================================

    public static void executeQuiz(){
        // Returns a Quiz object based on user inputs from
        System.out.println("Select a quiz to execute");
        Quiz quiz = QuizManager.selectQuiz();

        // If there is no quiz found, return
        if (quiz == null) {
            return;
        }

        quiz.execute();
    }


    // =================================================================
    // =================================================================
    // =================================================================

}
