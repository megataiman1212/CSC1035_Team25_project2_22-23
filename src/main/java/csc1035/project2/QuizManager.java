package csc1035.project2;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 */
public class QuizManager {
    private ArrayList<Quiz> quizArrayList;

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
    public void addQuiz(Quiz quiz){
        quizArrayList.add(quiz);
    }

    public Quiz searchQuizByName(String quizName){
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


}
