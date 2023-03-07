package csc1035.project2.question;

import csc1035.project2.IO;

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
    public boolean execute() {
        ArrayList<String> allAnswers = new ArrayList<>(List.of(wrongAnswers));
        allAnswers.add(correctAnswer);
        Collections.shuffle(allAnswers);

        showPrompt(allAnswers);
        int answer = getInput(allAnswers.size());

        return allAnswers.get(answer - 1).equals(correctAnswer);
    }

    public void showPrompt(ArrayList<String> allAnswers) {
        System.out.println(question);

        for (int i = 0; i < allAnswers.size(); i++) {
            System.out.printf("\t%s - %s%n", i + 1, allAnswers.get(i));
        }
    }

    public int getInput(int max) {
        System.out.print("Enter your choice... ");

        int answer;

        while (true) {
            String line = IO.scanner.nextLine().trim();
            try {
                answer = Integer.parseInt(line);
                if (answer >= 1 && answer <= max) break;
            } catch (Exception ignored) {
            }

            System.out.print("Invalid choice, try again... ");
        }

        return answer;
    }
}
