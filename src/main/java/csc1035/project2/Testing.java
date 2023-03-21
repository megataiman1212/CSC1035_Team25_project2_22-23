package csc1035.project2;

import csc1035.project2.question.MultipleChoiceQuestion;
import csc1035.project2.question.ShortResponseQuestion;
import csc1035.project2.question.Topic;
import csc1035.project2.quiz.Quiz;

import java.util.regex.Pattern;

public class Testing {
    public static void main(String[] args) {

        // Initialising demo questions
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(
                "Select 'alpha' for correct, anything else for incorrect",
                Topic.PROGRAMMING,
                "alpha",
                "bravo", "charlie", "delta");

        ShortResponseQuestion srq = new ShortResponseQuestion(
                "Type 'alpha' (case insensitive) for correct, anything else for incorrect",
                Topic.MATHS,
                Pattern.compile("^alpha$", Pattern.CASE_INSENSITIVE)
        );

        // Initialising demo quiz with demo questions
        Quiz q = new Quiz("Demo Quiz", srq, srq, mcq, mcq);

        // Initialise quiz execute process
        q.execute();


        IO.quizManager.findQuizByName("demo quiz").ifPresent(quiz -> System.out.println(quiz.questions.size()));

        System.out.println("=================================================================");

        // =================================================================
        // ===================== Testing methods ===========================
        // =================================================================
        IO.createQuiz();
        IO.readQuiz();
        IO.updateQuiz();
        IO.deleteQuiz();
        // ==================
        IO.createQuestion(q);
        System.out.println("===================== Reading Question ==========================");
        System.out.println(IO.readQuestion(q));
        System.out.println("=================================================================");
        IO.updateQuestion(q);
        IO.deleteQuestion(q);
        System.out.println("=================================================================");



        /*
        Commented in order to prevent all questions from being cleared accidentally
        ----
        System.out.println("\nClearing Questions\n");
        IO.questionManager.clearQuestions();
        System.out.println("\nQuestions Cleared!\n");
         */

        /*
        @todo delete, outdated test
        IO.questionManager.createQuestion(mcq);
        IO.questionManager.createQuestion(srq);
        IO.quizManager.createQuiz(q);
         */
    }
}
