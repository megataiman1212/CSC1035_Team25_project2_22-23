package csc1035.project2.question;

import java.util.*;

public class MultipleChoiceQuestion extends Question {
    final String[] wrongAnswers;
    final String correctAnswer;


    public MultipleChoiceQuestion(String question, Topic topic, String correctAnswer, String... wrongAnswers) {
        super(question, topic);
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public void execute() {
        System.out.println(this.question);

        // @TODO Refactor this into an `IO` class

        ArrayList<String> allAnswers = new ArrayList<>(List.of(wrongAnswers));
        allAnswers.add(correctAnswer);
        Collections.shuffle(allAnswers);

        for (int i = 0; i < allAnswers.size(); i++) {
            System.out.printf("\t%s - %s%n", i + 1, allAnswers.get(i));
        }

        System.out.print("Enter your choice... ");
        Scanner s = new Scanner(System.in);

        // @TODO This will crash if a non integer value is entered!
        int answer = s.nextInt();

        if(allAnswers.get(answer - 1).equals(correctAnswer)) {
            System.out.println("Correct :)");
        } else {
            System.out.println("Wrong :(");
        }
    }
}
