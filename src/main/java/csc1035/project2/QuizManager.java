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
        Quiz quiz = QuizManager.searchQuizByName(quizName); // @todo fix static context error
        return quiz;
    }


}
