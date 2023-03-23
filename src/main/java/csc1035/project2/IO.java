package csc1035.project2;

import csc1035.project2.question.*;
import csc1035.project2.quiz.Quiz;
import csc1035.project2.quiz.QuizManager;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Class to act as the interface in-which to interact with the software package
 * Contains methods for:
 * - Main method to begin main user route, displaying program options
 * - Displaying a console interface
 * - Method extending from main to allow the user to select how they would like to manipulateQuizData
 *      - Method to Create quiz objects
 *      - Method to Read quiz objects
 *      - Method to Edit quiz objects
 *      - Method to Delete quiz objects
 * - Method extending from main to allow the user to select how they would like to manipulateQuestionData
 *      - Method to Create question objects
 *      - Method to Read question objects
 *      - Method to Edit question objects
 *      - Method to delete question objects
 * - Method to execute a saved quiz
 * - Method to print a list of questions based on user choices
 * - Method to execute a quiz of questions previously answered incorrect
 * - Import and Export Methods
 *      - Method to export a list of questions to a specified file
 *      - Method to import a list of questions from a specified file
 *
 * @version 1
 */
public class IO {
    public static final Scanner scanner = new Scanner(System.in);
    public static final QuestionManager questionManager = new QuestionManager();
    public static final QuizManager quizManager = new QuizManager();
    private ImportQuestion importQuestion;


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
                    System.out.println("====================");
                    System.out.println("Manipulate Quiz Data");
                    System.out.println("====================");
                    manipulateQuizData();
                }
                case 2 -> {
                    System.out.println("========================");
                    System.out.println("Manipulate Question Data");
                    System.out.println("========================");
                    manipulateQuestionData();
                }
                case 3 -> {
                    System.out.println("==============");
                    System.out.println("Executing quiz");
                    System.out.println("==============");
                    executeQuiz();
                }
                case 4 -> {
                    System.out.println("===================================");
                    System.out.println("Execute Quiz of incorrect questions");
                    System.out.println("===================================");
                    // @todo quiz of incorrect questions method
                }
                case 5 -> {
                    System.out.println("===================================================");
                    System.out.println("Generate a quiz of specified length, topic and type");
                    System.out.println("===================================================");
                    generateQuiz();
                }
                case 6 -> {
                    System.out.println("========================");
                    System.out.println("Import or Export to file");
                    System.out.println("========================");
                    importOrExport();
                }
                case 7 -> {
                    System.out.println("============================================");
                    System.out.println("Print a subset of questions by type or topic");
                    System.out.println("============================================");
                    listSubsetOfQuestions();
                }
                case 8 -> {
                    System.out.println("===============");
                    System.out.println("Exiting Program");
                    System.out.println("===============");
                    System.exit(0);
                }
                default -> {
                    System.out.println("=====================");
                    System.out.println("Choice Not Recognised");
                    System.out.println("=====================");
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
        System.out.println("3 - Execute a quiz");
        System.out.println("4 - Execute quiz of questions previously answered incorrectly");
        System.out.println("5 - Generate a quiz of specified length, topic and type");
        System.out.println("6 - Import or Export a list of questions to a file");
        System.out.println("7 - Print a subset of questions by type or topic");
        System.out.println("8 - Exit Program");
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
                System.out.println(readQuiz());
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

    /**
     * Static method to prompt user input to delete a quiz from the database
     */
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
        System.out.println("Select a quiz to access questions to : ");
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

    /**
     * Method to prompt user input to create a question object and add to the database
     * @param quiz quiz that question will be held within
     */
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

            // Establish MultipleChoiceQuestion object using inputs from above
            MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(questionText, topic, correctAnswer, wrongAnswersStr);

            // Add mcq to the quiz
            quiz.addQuestion(mcq);

            // Add mcq to the database
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

            // Establish ShortResponseQuestion object using inputs from above
            ShortResponseQuestion srq = new ShortResponseQuestion(questionText, topic, Pattern.compile(correctAnswer, Pattern.CASE_INSENSITIVE));

            // Add srq to the quiz
            quiz.addQuestion(srq);

            // Add srq to the database
            questionManager.createQuestion(srq);
        }
    }

    /**
     * Static method to optionally return a question from an inputted string based on question id
     */
    public static void readQuestion(Quiz quiz) {
        ArrayList<Question> a = new ArrayList<>(quiz.questions);

        System.out.println("Id:" + questionManager.sendId(a));
        System.out.println("Question:" + quiz.questions);

        if (quiz.questions.isEmpty()) {
            System.out.println("This quiz does not contain any questions");
        } else {
            System.out.println("Enter the id of the question: ");
            int id = scanner.nextInt();

            Optional<Question> question = questionManager.findQuestionById(id);


            if (question.isPresent()) {
                System.out.println("Question found!");
            } else {
                System.out.println("No question named \"" + id + "\" found.");
            }
        }
    }

    /**
     * Static method to prompt user input to update a question object and override database
     */
    public static void updateQuestion(Quiz quiz) {
        ArrayList<Question> listOfQuestions = new ArrayList<>(quiz.questions);

        System.out.println("Id:" + questionManager.sendId(listOfQuestions));
        System.out.println("Question:" + quiz.questions);

        if (quiz.questions.isEmpty()) {
            System.out.println("This quiz does not contain any questions");
        } else {
            System.out.println("Enter the id of the question: ");
            int id = scanner.nextInt();

            Optional<Question> question = questionManager.findQuestionById(id);
            scanner.nextLine();
            if(question.isPresent()){


                System.out.println("Enter your updates (or type same to keep it the same):");
                System.out.println("==============================================================");

                String updatePrompt;
                Topic updateTopic;
                Question question1 = question.get();

                // Get question text
                System.out.println("1. Enter the question prompt :");
                updatePrompt = scanner.nextLine();
                if(updatePrompt.equals("same")){
                    updatePrompt = question1.getQuestion();
                }

                System.out.println("2. Enter the question topic (Programming, Databases, Architecture, Maths):");
                String topicStr = scanner.nextLine().toUpperCase();
                if(topicStr.equalsIgnoreCase("same")){
                    updateTopic = question1.getTopic();
                }

                else{
                    try{
                        // Create the topic input to Topic type
                        updateTopic = Topic.valueOf(topicStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid topic");
                        return;
                    }
                }
                    questionManager.updateQuestion(question1,updatePrompt, updateTopic);
            }
        }
    }

    /**
     * Static method to prompt user input to delete a question from the database
     */
    public static void deleteQuestion(Quiz quiz) {
        ArrayList<Question> listOfQuestions = new ArrayList<>(quiz.questions);

        System.out.println("Id:" + questionManager.sendId(listOfQuestions));
        System.out.println("Question:" + quiz.questions);

        if(quiz.questions.isEmpty()){
            System.out.println("This quiz does not contain any questions");
        }else{
            System.out.println("Enter the id of the question:");
            int id = scanner.nextInt();

            Optional<Question> question = questionManager.findQuestionById(id);
            scanner.nextLine();

            if(question.isPresent()){
                Question question1 = question.get();
                questionManager.deleteQuestion(question1);
                System.out.println("Deleted successfully!");
            }
        }

    }


    // =================================================================
    // =================================================================
    // =================================================================

    /**
     * Method to execute a saved quiz
     */
    public static void executeQuiz() {
        // Returns a Quiz object based on user inputs from readQuiz and executes the quiz
        readQuiz().ifPresent(Quiz::execute);
    }

    // =================================================================
    // ====================== Import Export ============================
    // =================================================================

    /**
     * Static method to provide the interface to choose whether to import or export
     */
    public static void importOrExport(){
        System.out.println("Type \"1\" Import");
        System.out.println("Type \"2\" to Export");
        // Brings programChoice into scope
        int importOrExportChoice = 0;
        try {
            importOrExportChoice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Data entered not an int");
        }
        switch (importOrExportChoice){
            case 1-> importQuestions();
            case 2 -> exportQuestions();
            default -> {
                System.out.println("=====================");
                System.out.println("Choice Not Recognised");
                System.out.println("=====================");
                System.exit(0);
            }
        }
    }

    /**
     * Static method to export a list of question to a hardcoded file
     * Mirrors importQuestions
     */
    public static void exportQuestions(){
        ExportQuestion exportQuestion = new ExportQuestion();


    }

    /**
     * Static method to import a list of questions from a hardcoded file
     * Mirrors exportQuestions
     */
    public static void importQuestions(){
        ImportQuestion importQuestion = new ImportQuestion();
        System.out.print(importQuestion.importQuestionFromFile());

    }


    // =================================================================
    // =================================================================
    // =================================================================

    /**
     * Static method to generate a quiz of questions previously answered incorrectly
     */
    public static void generateIncorrectQuiz(){

    }

    /**
     * Static method to generate and execute a quiz of questions based on user choice
     */
    public static void generateQuiz (){

        System.out.println("Enter the desired size of quiz : ");

        // Brings choice into scope
        int quizSize = 0;
        try {
            quizSize= scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Data entered not an int");
        }

        System.out.println("Select question type you would like to test yourself with");
        System.out.println("----------------");
        System.out.println("All, MCQ, SRQ");

        String type = scanner.nextLine().trim().toLowerCase();

        // Validate type input
        while (type.isEmpty() ||
                (!Objects.equals(type, "all") &&
                        !Objects.equals(type, "mcq") &&
                        !Objects.equals(type, "srq"))) {
            System.out.println("Choice not recognised");
            System.out.println("All, MCQ, SRQ");
            System.out.println("Try again : ");
            type = scanner.nextLine().trim().toLowerCase();
        }

        System.out.println("Select question topic you would like to test yourself on");
        System.out.println("----------------");
        System.out.println("    PROGRAMMING,\n" +
                "    DATABASES,\n" +
                "    ARCHITECTURE,\n" +
                "    MATHS");

        /* @todo add incorrect question functionality
        System.out.println("Would you like to only test yourself using questions you have answered incorrectly");
        System.out.println("----------------");
        System.out.println("Yes, No");

        String incorrectOnly = scanner.nextLine().trim().toLowerCase();

        while (type.isEmpty() || (!Objects.equals(type, "yes") && !Objects.equals(type, "no"))) {
            System.out.println("Choice not recognised");
            System.out.println("Yes, No");
            System.out.println("Try again : ");
            incorrectOnly = scanner.nextLine().trim().toLowerCase();
        }

         */


        String topic = scanner.nextLine().trim().toLowerCase();

        // Validate topic input
        while (topic.isEmpty() ||
                (!Objects.equals(topic, "programming") &&
                        !Objects.equals(topic, "databases") &&
                        !Objects.equals(topic, "architecture")&&
                        !Objects.equals(topic, "maths")&&
                        !Objects.equals(topic, "all"))) {

            System.out.println("Choices cannot be empty");
            System.out.println("    PROGRAMMING,\n" +
                    "    Databases,\n" +
                    "    Architecture,\n" +
                    "    Maths" +
                    "    All");
            System.out.println("Try again : ");
            topic = scanner.nextLine().trim().toLowerCase();
        }




        List<Question> outputList = new ArrayList<>();

        Set<Question> questions = questionManager.getQuestions();
        for (Question question : questions){
            if (question.getTopic().name().equals(topic)){
                switch (type) {
                    case "all" -> outputList.add(question);
                    case "mcq" -> {
                        if (question instanceof MultipleChoiceQuestion) {
                            outputList.add(question);
                        }
                    }
                    case "srq" -> {
                        if (question instanceof ShortResponseQuestion) {
                            outputList.add(question);
                        }
                    }
                }
            }
            else if (topic.equals("all")){
                switch (type) {
                    case "all" -> outputList.add(question);
                    case "mcq" -> {
                        if (question instanceof MultipleChoiceQuestion) {
                            outputList.add(question);
                        }
                    }
                    case "srq" -> {
                        if (question instanceof ShortResponseQuestion) {
                            outputList.add(question);
                        }
                    }
                }
            }
        }

        /*
        if (incorrectOnly.equals("yes")){
            for (Question question : outputList) {
                if (question.getIncorrect
           }
        }

         */

        Random rand = new Random();
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < quizSize; i++) {
            questionList.add(outputList.get(rand.nextInt(outputList.size())));
        }
        Quiz q = new Quiz(null, questionList.get(quizSize));
        q.execute();
    }

    // =================================================================
    // =================================================================
    // =================================================================

    /**
     * Method to create a list of questions that match a user's desired preferences and print to the console
     */
    public static void listSubsetOfQuestions(){
        System.out.println("Select question type you would like to display");
        System.out.println("----------------");
        System.out.println("All, MCQ, SRQ");

        String type = scanner.nextLine().trim().toLowerCase();

        // Validate type input
        while (type.isEmpty() ||
                (!Objects.equals(type, "all") &&
                !Objects.equals(type, "mcq") &&
                !Objects.equals(type, "srq"))) {
            System.out.println("Choice not recognised");
            System.out.println("All, MCQ, SRQ");
            System.out.println("Try again : ");
            type = scanner.nextLine().trim().toLowerCase();
        }

        System.out.println("Select question topic you would like to display");
        System.out.println("----------------");
        System.out.println("    PROGRAMMING,\n" +
                "    DATABASES,\n" +
                "    ARCHITECTURE,\n" +
                "    MATHS");

        String topic = scanner.nextLine().trim().toLowerCase();

        // Validate topic input
        while (topic.isEmpty() ||
                (!Objects.equals(topic, "programming") &&
                !Objects.equals(topic, "databases") &&
                !Objects.equals(topic, "architecture")&&
                !Objects.equals(topic, "maths")&&
                !Objects.equals(topic, "all"))) {

            System.out.println("Choices cannot be empty");
            System.out.println("    PROGRAMMING,\n" +
                    "    Databases,\n" +
                    "    Architecture,\n" +
                    "    Maths" +
                    "    All");
            System.out.println("Try again : ");
            topic = scanner.nextLine().trim().toLowerCase();
        }

        List<Question> outputList = new ArrayList<>();

        Set<Question> questions = questionManager.getQuestions();
        for (Question question : questions){
            if (question.getTopic().name().equals(topic)){
                switch (type) {
                    case "all" -> outputList.add(question);
                    case "mcq" -> {
                        if (question instanceof MultipleChoiceQuestion) {
                            outputList.add(question);
                        }
                    }
                    case "srq" -> {
                        if (question instanceof ShortResponseQuestion) {
                            outputList.add(question);
                        }
                    }
                }
            }
            else if (topic.equals("all")){
                switch (type) {
                    case "all" -> outputList.add(question);
                    case "mcq" -> {
                        if (question instanceof MultipleChoiceQuestion) {
                            outputList.add(question);
                        }
                    }
                    case "srq" -> {
                        if (question instanceof ShortResponseQuestion) {
                            outputList.add(question);
                        }
                    }
                }
            }
        }

        System.out.println(outputList);

    }

}
