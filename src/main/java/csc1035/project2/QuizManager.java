package csc1035.project2;

import java.util.ArrayList;

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

}
