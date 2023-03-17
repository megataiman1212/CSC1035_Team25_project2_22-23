package csc1035.project2;

import csc1035.project2.question.*;
import csc1035.project2.quiz.Quiz;
import csc1035.project2.quiz.QuizManager;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class to act as the interface in-which to interact with the software package
 * Contains methods for:
 * - Main method to begin main user route, displaying program options
 * - Displaying a console interface
 * - Method extending from main to allow the user to select how they would like to manipulateQuizData
 *      - Method to Create quizzes
 *      - Method to Read quizzes
 *      - Method to Edit quizzes
 *      - Method to Delete quizzes
 * - Method extending from main to allow the user to select how they would like to manipulateQuestionData
 *      - Method to Create questions
 *      - Method to Read questions
 *      - Method to Edit questions
 *      - Method to delete questions
 * - Method to execute a saved quiz
 * - @todo previously answer wrong questions
 * @version 1
 */
public class IO {
    public static final Scanner scanner = new Scanner(System.in);
    public static final QuestionManager questionManager = new QuestionManager();
    public static final QuizManager quizManager = new QuizManager();

    /**
     * Main method to provide the main run environment for the entire package.
     * Allow the user to select the functionality they would like to use.
     */
    public static void main(String[] args) {

        // Begin main IO route
        while (true) {

            // Calls a method to display a Console Interface (the main menu)
            consoleInterface();

            // Brings programChoice into scope
            int programChoice = 0;
            try {
                programChoice = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Data entered not an int");
            }

            switch (programChoice) {
                case 1 -> {
                    System.out.println("SAMPLE 1");
                    manipulateQuizData();
                }
                case 2 -> {
                    System.out.println("SAMPLE 2");
                    manipulateQuestionData();
                }
                case 3 -> {
                    System.out.println("SAMPLE 3");
                    executeQuiz();
                }
                case 4 -> {
                    System.out.println("SAMPLE 4");
                }
                // @todo Exit Case
                default -> {
                    System.out.println("=====================");
                    System.out.println("Choice Not Recognised");
                    System.out.println("=====================");
                    System.exit(0);
                }
            }
        }
    }


    /**
     * Static method to provide the main consoleInterface
     */
    public static void consoleInterface() {
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
     * Static method that allows the user to select the program option they would like to complete
     */
    public static void manipulateQuizData() {

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
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Data entered not an int");
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Create Quiz: ");
                createQuiz();
            }
            case 2 -> {
                System.out.println("Read Quiz : ");
                readQuiz();
            }
            case 3 -> {
                System.out.println("Edit/Update Quiz : ");
                updateQuiz();
            }
            case 4 -> {
                System.out.println("Delete Quiz : ");
                deleteQuiz();
            }
            default -> {
                System.out.println("=====================");
                System.out.println("Choice Not Recognised");
                System.out.println("=====================");
            }
        }
    }

    /**
     * Static method to prompt user input to create a quiz object and add to database
     */
    public static void createQuiz() {
        // Allow user input for the name of the quiz
        System.out.println("Enter the quiz name: ");
        String quizName;
        do {
            quizName = scanner.nextLine();
        } while (quizName.length() == 0);

        // Call the method, addQuiz with a new quiz instance
        quizManager.createQuiz(new Quiz(quizName));

        System.out.println("Quiz Successfully Added");
    }

    /**
     * Static method to optionally return a quiz from an inputted string based on a matching quizName
     * @return quiz that matches quizName
     */
    public static Optional<Quiz> readQuiz() {
        System.out.println("Enter the quiz name: ");
        String quizName;
        do {
            quizName = scanner.nextLine();
        } while (quizName.length() == 0);

        Optional<Quiz> quiz = quizManager.findQuizByName(quizName);

        if (quiz.isPresent()) {
            System.out.println("Quiz found!");
        } else {
            System.out.println("No quiz named \"" + quizName + "\" found.");
        }

        return quiz;
    }

    /**
     * Static method to prompt user input to update a quiz object and override database
     */
    public static void updateQuiz() {
        System.out.println("Enter the quiz name:");
        String quizName;
        do{
            quizName = scanner.nextLine();
        }while (quizName.length() == 0);

        Optional<Quiz> quiz = quizManager.findQuizByName(quizName);

        if (quiz.isPresent()){
            Quiz quiz1 = quiz.get();
            String newQuizName;

            do{
                System.out.println("Enter the new quiz name:");
                newQuizName = scanner.nextLine();
            }while (newQuizName.length() == 0);

            quizManager.updateQuiz(quiz1 , newQuizName);
        }
    }

    public static void deleteQuiz() {
        System.out.println("Enter the quiz name:");
        String quizName;
        do{
            quizName = scanner.nextLine();
        }while (quizName.length() == 0);

        Optional<Quiz> quiz = quizManager.findQuizByName(quizName);

        if (quiz.isPresent()){
            Quiz quiz1 = quiz.get();
            quizManager.deleteQuiz(quiz1);
            System.out.println("Deleted successfully");
        }
    }


    // =================================================================
    // ================== Manipulating Questions =======================
    // =================================================================


    /**
     * Static method that allows the user to select the program option they would like to complete
     */
    public static void manipulateQuestionData() {

        // Returns a Quiz object based on user inputs from
        System.out.println("Select a quiz to edit to");
        Optional<Quiz> quizOptional = readQuiz();

        // If there is no quiz found, return
        if (quizOptional.isEmpty()) {
            return;
        }

        Quiz quiz = quizOptional.get();

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
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Data entered not an int");
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Create Question : ");
                createQuestion(quiz);
            }
            case 2 -> {
                System.out.println("Read Question : ");
                readQuestion(quiz);
            }
            case 3 -> {
                System.out.println("Edit/Update Question : ");
                updateQuestion(quiz);
            }
            case 4 -> {
                System.out.println("Delete Question : ");
                deleteQuestion(quiz);
            }
            default -> {
                System.out.println("=====================");
                System.out.println("Choice Not Recognised");
                System.out.println("=====================");
            }
        }
    }

    public static void createQuestion(Quiz quiz) {
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

            // add mcq to the database
            questionManager.createQuestion(mcq);

        } else {
            // Get correctAnswer
            System.out.println("Enter the correct answer : ");
            String correctAnswer = scanner.nextLine().trim();

            // Validate correctAnswer input
            while (correctAnswer.isEmpty()) {
                System.out.println("The correct answer cannot be empty");
                System.out.println("Enter the correct answer : ");
                correctAnswer = scanner.nextLine().trim();
            }

            ShortResponseQuestion srq = new ShortResponseQuestion(questionText, topic, Pattern.compile(correctAnswer, Pattern.CASE_INSENSITIVE));

            quiz.addQuestion(srq);

            // add srq to the database
            questionManager.createQuestion(srq);
        }
    }

    public static void readQuestion(Quiz quiz) {

    }

    public static void updateQuestion(Quiz quiz) {

    }

    public static void deleteQuestion(Quiz quiz) {

    }


    // =================================================================
    // =================================================================
    // =================================================================

    public static void executeQuiz() {
        // Returns a Quiz object based on user inputs from
        readQuiz().ifPresent(Quiz::execute);
    }


    // =================================================================
    // =================================================================
    // =================================================================

}
