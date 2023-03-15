package csc1035.project2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 */
public class QuizManager {
    private static ArrayList<Quiz> quizArrayList;

    /**
     * Constructor for the QuizManager class that creates a list of quizzes
     */
    public QuizManager(){
        quizArrayList = new ArrayList<>();
    }

    /**
     * Method to add a quiz to the list of quizArrayList
     * @param quiz the quiz being added to the quizArrayList
     */
    public static void addQuiz(Quiz quiz){
        quizArrayList.add(quiz);
    }

    public static Quiz searchQuizByName(String quizName){
        for (Quiz quiz:quizArrayList){
            String currentQuizName = quiz.getQuizName();

            if (Objects.equals(currentQuizName, quizName)){
                System.out.println("Quiz Found");
                return quiz;
            }
        }
        System.out.println("No quiz found with the name \""+quizName+"\"");
        return null;
    }

    public static Quiz selectQuiz(){
        Scanner scanner = new Scanner(System.in);

        // Allow user input for the quizName
        System.out.println("Enter the name of the quiz : ");
        String quizName = scanner.nextLine().toLowerCase();

        // Assigns the quiz variable with the quiz found by the searchQuizByName
        return QuizManager.searchQuizByName(quizName);
    }

    public static boolean updateQuizName(){
        // Select the quiz
        Quiz name = QuizManager.selectQuiz();
        if(name != null){
            // Allow the user input for the newQuizName
            System.out.println("Enter the new quiz name:");
            Scanner sca = new Scanner(System.in);
            String newQuizName = sca.nextLine().toLowerCase();

            // Set the quizName with the newQuizName
            name.setQuizName(newQuizName);
            return true;
        }
        else
            return false;

    }

    public static boolean deleteQuiz(){
        // Select the quiz
        Quiz quiz = QuizManager.selectQuiz();
        if(quiz != null) {
            // Remove the quiz from the quizArrayList
            quizArrayList.remove(quiz);
            return true;
        }
        else
            return false;
    }
}
