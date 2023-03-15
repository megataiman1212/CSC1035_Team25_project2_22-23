package csc1035.project2;

import csc1035.project2.question.MultipleChoiceQuestion;
import csc1035.project2.question.Question;
import csc1035.project2.question.ShortResponseQuestion;
import csc1035.project2.question.Topic;
import csc1035.project2.quiz.Quiz;
import org.hibernate.Session;

import java.util.Objects;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // @TODO Delete this! - It's just a demo of how to create the questions and execute them

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

        Quiz q = new Quiz("Demo Quiz", srq, srq, mcq, mcq);
        //q.execute();

        ///////////

        IO.quizManager.findQuizByName("demo quiz").ifPresent(quiz -> System.out.println(quiz.questions.size()));

        System.out.println("\nClearing Questions\n");
        IO.questionManager.clearQuestions();
        System.out.println("\nQuestions Cleared!\n");

        IO.questionManager.createQuestion(mcq);
        IO.questionManager.createQuestion(srq);

        IO.quizManager.createQuiz(q);
    }
}
