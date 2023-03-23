package csc1035.project2.question;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ImportQuestion {
    List<Question> questionList = new ArrayList<>();

    public List<Question>  importQuestionFromFile() {
        String splitCsvBy;
        String filePath = //will need to be updated;
        splitCsvBy = ",";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = "";
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String [] splitArray = line.split(splitCsvBy);

                Question question = new Question(splitArray[0], splitArray[1]) {
                    @Override
                    public boolean execute() {
                        return false;
                    }
                };
                // This is assuming the file format in csv is seperated as "Question, Topic"
                questionList.add(question);
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File not found. Please check the file path and try again.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.print("IO Exception.");
            e.printStackTrace();
        }
        return questionList;
    }

}
