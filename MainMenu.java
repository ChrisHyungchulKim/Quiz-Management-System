import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.sql.Timestamp;

public class QuizMenu {

    public static final String CREATE_ACCOUNT_QUESTION = "1. Log In\n2. Create Account\n";
    public static final String TEACHER_QUESTION = "Are you a teacher?\n";
    public static final String GENERAL_MENU_TEACHER = "1. Account Settings\n2. Create and Edit\n3. Quit";
    public static final String GENERAL_MENU_STUDENT = "1. Account Settings\n2. Take Quiz\n3. View Quiz results" +
                                                    "\n4. Quit";
    public static final String ABOUT_EDIT_QUIZ_QUESTION = "1. The Quiz Name\n2. A Question\n3. An Answer\n" +
                                                            "4. Which Answer is Correct\n5. Go Back\n";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean ifContinue = true;
        //scanner.nextLine();

        User currentUser = null;

        //reads all the course info from CourseDetails.txt
        Class currentClass = new Class(CourseInfoHandler.readCourseInfo());


        while (ifContinue) {
            //displays the Welcome message
            int accountQuestion = displayWelcome(scanner);

            //gets the User info from the User
            User user = getUserInfo(scanner);

            //if they want to create a new account, it tries to create and if it's already a user
            if (accountQuestion == 2) {
                //System.out.println(user.getUsername());
                accountQuestion = createAccountDialog(user, scanner);

                if (accountQuestion == 3) {
                    ifContinue = false;
                    currentUser = user;
                }
            } else {
                //System.out.println(user.getUsername());
                boolean checkUser = !LoggingIn.checkUser(user);
                if (checkUser) {
                    System.out.println("Your login information isn't correct! Please try again.");
                    //Give the user choice to get back to the main menu
                } else {
                    currentUser = user;
                    ifContinue = false;
                }
            }
        }
        boolean repeat = true;
        int studentGeneralMenuChoice = 0;
        int teacherGeneralMenuChoice = 0;
        while (repeat) {
            if (currentUser.isTeacher()) {

                do {
                    System.out.println(GENERAL_MENU_TEACHER);
                    try {
                        teacherGeneralMenuChoice = scanner.nextInt();
                        if (teacherGeneralMenuChoice != 1 && teacherGeneralMenuChoice != 2
                                && teacherGeneralMenuChoice != 3) {
                            System.out.println("Please enter a number 1 - 3.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number 1-3");
                    }

                } while (teacherGeneralMenuChoice != 1 && teacherGeneralMenuChoice != 2
                        && teacherGeneralMenuChoice != 3);
                scanner.nextLine();
                if (teacherGeneralMenuChoice == 1) {
                    boolean deleted = accountSettingsDialog(currentUser, scanner);
                    if (deleted) {
                        repeat = false;
                    }
                } else if (teacherGeneralMenuChoice == 2) {
                    teacherCreateMenu(currentUser, currentClass, scanner);
                } else {
                    repeat = false;
                }
            } else {
                do {
                    System.out.println(GENERAL_MENU_STUDENT);
                    try {
                        studentGeneralMenuChoice = scanner.nextInt();
                        if (studentGeneralMenuChoice != 1 && studentGeneralMenuChoice != 2
                                && studentGeneralMenuChoice != 3 && studentGeneralMenuChoice != 4) {
                            System.out.println("Please enter a number 1-4");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number 1-3");
                    }

                } while (studentGeneralMenuChoice != 1 && studentGeneralMenuChoice != 2
                        && studentGeneralMenuChoice != 3 && studentGeneralMenuChoice != 4);
                scanner.nextLine();
                if (studentGeneralMenuChoice == 1) {
                    boolean deleted = accountSettingsDialog(currentUser, scanner);
                    if (deleted) {
                        repeat = false;
                    }
                } else if (studentGeneralMenuChoice == 2) {
                    Submission submission = takeQuiz(currentUser, currentClass, scanner);
                    submission.writeSubmission(submission);
                    ArrayList<Submission> testSubmission = readSubmissions(currentClass, scanner);


                } else if(studentGeneralMenuChoice == 3) {

                } else {
                    repeat = false;
                }
            }

        }
    }

    public static ArrayList<Submission> readSubmissions(Class currentClass, Scanner scanner) {
        BufferedReader reader;
        try {
            FileReader filereader = new FileReader("SubmissionDetails.txt");
            reader = new BufferedReader(filereader);
            String line = reader.readLine();
            System.out.println(line);
            ArrayList<User> users = LoggingIn.readUserInfo();


            String studentName = "";
            String courseName = "";
            Course courseUsed = null;
            Quiz currentQuiz = null;
            User student = null;
            ArrayList<String> responses;
            String time = "";
            String quizBeingTaken = "";
            boolean graded = false;
            double grade = 0;

            Submission newSubmission = null;
            ArrayList<Submission> submissions = new ArrayList<>();
            while (line != null) {
                if (line.contains("Student: ")) {
                    responses = new ArrayList<String>();
                    studentName = line.substring(line.indexOf(' ') + 1);
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(studentName)) {
                            student = users.get(i);
                        }
                    }
                    line = reader.readLine();
                    System.out.println(line);

                    if (line.contains("Responses: ")) {
                        String[] responsesArray = line.split(",");
                        responses = new ArrayList<String>(Arrays.asList(responsesArray));
                        line = reader.readLine();
                        System.out.println(line);
                        if (line.contains("Course: ")) {
                            courseName = line.substring(line.indexOf(' ') + 1);
                            for (int i = 0; i < currentClass.getCourses().size(); i++) {
                                if (currentClass.getCourses().get(i).getCourseName().equals(courseName)) {
                                    courseUsed = currentClass.getCourses().get(i);
                                }
                            }
                            line = reader.readLine();
                            System.out.println(line);
                            if (line.contains("Quiz: ")) {
                                quizBeingTaken = line.substring(line.indexOf(' ') + 1);
                                for (int i = 0; i < courseUsed.getQuizzes().size(); i++) {
                                    if (courseUsed.getQuizzes().get(i).getName().equals(quizBeingTaken)) {
                                        currentQuiz = courseUsed.getQuizzes().get(i);
                                    }
                                }
                                line = reader.readLine();
                                System.out.println(line);

                                if (line.contains("Time: ")) {
                                    time = line.substring(line.indexOf(' ') + 1);
                                    line = reader.readLine();
                                    System.out.println(line);
                                    if (line.contains("Graded: ")) {
                                        graded = Boolean.parseBoolean(line.substring(line.indexOf(' ') + 1));
                                        line = reader.readLine();
                                        System.out.println(line);

                                        if(line.contains("Grade: ")) {
                                            grade = Double.parseDouble(line.substring(line.indexOf(' ') + 1));
                                        }
                                        line = reader.readLine();
                                        System.out.println(line);
                                    }
                                }

                            }
                        }
                    }
                    newSubmission = new Submission(student, currentQuiz, courseUsed,
                            responses, time, graded, grade);
                    submissions.add(newSubmission);
                    //line = reader.readLine();
                }

            }
            reader.close();
            return submissions;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Submission takeQuiz(User user, Class currentClass, Scanner scanner) {
        Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        ArrayList<String> responses = new ArrayList<>();
        Quiz quizBeingTaken;

        System.out.println("Which Course is the quiz in?");
        int courseCounter = 0;
        for (int f = 0; f < currentClass.getCourses().size(); f++) {
            System.out.printf("%d. %s\n", courseCounter + 1,
                    currentClass.getCourses().get(courseCounter).getCourseName());
            courseCounter++;
        }
        int courseSelection = scanner.nextInt();
        System.out.println("Which quiz do you want to take?");
        int quizCounter = 0;
        for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1).getQuizzes().size(); f++) {
            System.out.printf("%d. %s\n", quizCounter + 1,
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizCounter).getName());
            quizCounter++;
        }
        int quizSelection = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Name: %s\n", currentClass.getCourses().get(courseSelection - 1)
                .getQuizzes().get(quizSelection - 1).getName());

        for(int i = 0;i < currentClass.getCourses().get(courseSelection - 1)
                        .getQuizzes().get(quizSelection - 1).getQuestions().size(); i++) {
            System.out.println(currentClass.getCourses().get(courseSelection - 1)
                    .getQuizzes().get(quizSelection - 1).getQuestions().get(i).getPrompt());
           for(int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                   .getQuizzes().get(quizSelection - 1).getQuestions().get(i).getResponses().size(); f++) {
               System.out.println(currentClass.getCourses().get(courseSelection - 1)
                       .getQuizzes().get(quizSelection - 1).getQuestions().get(i).getResponses().get(f));
           }

           System.out.println("What is your response?");
           String theirResponse = scanner.nextLine();
           responses.add(theirResponse);
        }
        int ifSubmit = 2;
        while (ifSubmit == 2) {
            System.out.println("Do you want to Submit? 1. Yes\n2. No");
            ifSubmit = scanner.nextInt();
            scanner.nextLine();
            if (ifSubmit == 2) {
                System.out.println("Which Question Number do you want to change?");
                int questionNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.println(currentClass.getCourses()
                        .get(courseSelection - 1).getQuizzes().get(quizSelection - 1).
                        getQuestions().get(questionNumber - 1).getPrompt());
                System.out.println("What is your new response");
                String newResponse = scanner.nextLine();
                responses.set(questionNumber - 1, newResponse);
            }
        }

        return new Submission(user,
                currentClass.getCourses().get(courseSelection - 1).getQuizzes()
                        .get(quizSelection - 1),currentClass.getCourses().get(courseSelection - 1),
                        responses, timestamp.toString(), false, 0);
    }

    public static void teacherCreateMenu(User user, Class currentClass, Scanner scanner) {
        ifTeacherQuizMenuDialog(user, currentClass, scanner);
        CourseInfoHandler.writeCourseInfo(currentClass);
    }


    public static void ifTeacherQuizMenuDialog(User user, Class currentClass, Scanner scanner) {

        //gets whether the user wants to Change quizzes or view student submissions
        int quizMenuChoice = getQuizMenuChoice(scanner);

        //if it chooses change quiz
        if (quizMenuChoice == 1) {
            //it then asks if it wants to edit, create, or delete
            int changeQuizChoice = getChangeMenuChoice(scanner);

            if (changeQuizChoice == 1) {
                //if chooses to edit Quiz
                ifChooseEdit(currentClass, scanner);
            } else if (changeQuizChoice == 2) {
                //if chooses to Create Quiz
                ifChooseCreate(currentClass, scanner);
            } else {
                //if chooses to Delete Quiz
                ifChooseDelete(currentClass, scanner);
            }
        } else if (quizMenuChoice == 2) {
            System.out.println("What is the Name of the Course you want to create?");
            String newCourseName = scanner.nextLine();
            Course newCourse = new Course(newCourseName, new ArrayList<Quiz>());
            currentClass.addCourse(newCourse);
        } else {
            //TODO - Submissions stuff
        }

    }

    public static boolean accountSettingsDialog(User user, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        boolean deletedAccount;

        int accountSettingChoice;
        do {
            System.out.print("1. Edit Account\n2. Delete Account\n");
            accountSettingChoice = scanner.nextInt();
            if (accountSettingChoice != 1 && accountSettingChoice != 2) {
                System.out.println("Please enter a 1 or a 2.");
            }
        } while (accountSettingChoice != 1 && accountSettingChoice != 2);

        if (accountSettingChoice == 1) {
            int editSettingsChoice;
            do {
                System.out.print("1. Edit Username\n2. Edit Password\n");
                editSettingsChoice = scanner.nextInt();
                scanner.nextLine();
                if (editSettingsChoice != 1 && editSettingsChoice != 2) {
                    System.out.println("Please enter a 1 or a 2.");
                }
            } while (editSettingsChoice != 1 && editSettingsChoice != 2);

            if (editSettingsChoice == 1) {
                System.out.println("What is your new username?");
                String newUsername = scanner.nextLine();
                LoggingIn.editUsername(user, newUsername);
            } else {
                System.out.println("What is your new password?");
                String newPassword = scanner.nextLine();
                LoggingIn.editPassword(user, user.getPassword(), newPassword);
            }
            deletedAccount = false;
        } else {
            LoggingIn.deleteAccount(user);
            deletedAccount = true;
        }

        return deletedAccount;
    }

    public static int createAccountDialog(User user, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        boolean alreadyUser;
        String whichOption = null;

        alreadyUser = LoggingIn.createUser(user);

        if (!alreadyUser) {
            System.out.println("That username is already an account.");
            System.out.println("1. I want to log in.\n2. I want to use a different username.");

            whichOption = scanner.nextLine();
        } else {
            whichOption = "3";
        }


        return Integer.parseInt(whichOption);
    }

    public static int displayWelcome(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Section!");

        int accountQuestion = 0;
        do {
            System.out.print(CREATE_ACCOUNT_QUESTION);
            try {
                accountQuestion = scanner.nextInt();
                if (accountQuestion != 1 && accountQuestion != 2) {
                    System.out.println("Please enter either 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter either 1 or 2.");
            }
            scanner.nextLine();
        } while (accountQuestion != 1 && accountQuestion != 2);

        return accountQuestion;
    }

    public static String getUsername(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();


        return username;
    }

    public static String getPassword(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();


        return password;
    }

    public static boolean getIfTeacher(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        String teacher;
        do {
            System.out.print(TEACHER_QUESTION);
            teacher = scanner.nextLine();
            if (!Objects.equals(teacher.toLowerCase(), "yes") && !Objects.equals(teacher.toLowerCase(), "no")) {
                System.out.println("Please enter either yes or no.");
            }
        } while (!Objects.equals(teacher.toLowerCase(), "yes") && !Objects.equals(teacher.toLowerCase(), "no"));

        boolean ifTeacher;
        ifTeacher = teacher.equalsIgnoreCase("yes");

        return ifTeacher;
    }

    public static User getUserInfo(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        //gets username
        String username = getUsername(scanner);

        //gets password
        String password = getPassword(scanner);

        //gets if the User is a teacher
        boolean ifTeacher = getIfTeacher(scanner);

        User user = new User(username, password, ifTeacher);

        return user;
    }

    public static Quiz manuallyCreateQuiz(Scanner scanner) {
        System.out.println("Enter name of the quiz:");
        String name = scanner.nextLine();
        ArrayList<Question> questions = new ArrayList<>();
        int numberOfQuestions = 0;
        do {
            System.out.println("How many questions are there");
            try {
                numberOfQuestions = scanner.nextInt();
                if (numberOfQuestions < 1) {
                    System.out.println("Enter a number larger than 0");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number please.");
            }
            //scanner.nextLine();
        } while (numberOfQuestions < 1);
        scanner.nextLine();
        for (int j = 0; j < numberOfQuestions; j++) {
            System.out.println("Enter the question prompt:");
            String prompt = scanner.nextLine();
            int numberOfResponses = 0;
            do {
                System.out.println("How many responses are there: ");
                try {
                    numberOfResponses = scanner.nextInt();
                    if (numberOfResponses < 1) {
                        System.out.println("Enter a number larger than 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter a number please.");
                }
                scanner.nextLine();
            } while (numberOfResponses < 1);
            //scanner.nextLine();
            ArrayList<String> responses = new ArrayList<>();
            for (int i = 0; i < numberOfResponses; i++) {
                System.out.println("Please enter response number " + (i + 1) + ":");
                responses.add(scanner.nextLine());
            }
            int answer = 0;
            do {
                System.out.println("Please enter the responses number of the answer:");
                try {
                    answer = scanner.nextInt();
                    if (answer > numberOfResponses || answer < 1) {
                        System.out.println("This answer is not on the list.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                }
                //scanner.nextLine();
            } while (answer > numberOfResponses || answer < 1);
            int weight = 0;
            do {
                System.out.println("Please enter the weight of the answer: ");
                try {
                    weight = scanner.nextInt();
                    if (weight < 1) {
                        System.out.println("Weight cannot be lower than 1");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                }
            } while (weight < 1);
            Question q = new Question(prompt, responses, answer, weight);
            questions.add(q);
        }
        //TODO what are submissions? and what to do with the created quiz object
        return new Quiz(name, questions);
    }

    public static int getChangeMenuChoice(Scanner scanner) {
        int changeQuizChoice = 0;
        do {
            System.out.println("1. Edit Quiz\n2. Create Quiz\n3. Delete Quiz");
            try {
                changeQuizChoice = scanner.nextInt();
                if (changeQuizChoice != 1 && changeQuizChoice != 2 && changeQuizChoice != 3) {
                    System.out.println("Please choose an option from the list");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
            scanner.nextLine();
        } while (changeQuizChoice != 1 && changeQuizChoice != 2 && changeQuizChoice != 3);
        return changeQuizChoice;
    }


    public static int getQuizMenuChoice (Scanner scanner) {
        int quizMenuChoice = 0;
        do {
            System.out.println("1. Change Quizzes (Edit/Create/Delete)\n" +
                    "2. Create a Course\n3. View Student Submissions");
            try {
                quizMenuChoice = scanner.nextInt();
                if (quizMenuChoice != 1 && quizMenuChoice != 2) {
                    System.out.println("Please choose an option from the list");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
            scanner.nextLine();
        } while (quizMenuChoice != 1 && quizMenuChoice != 2 && quizMenuChoice != 3);
        return quizMenuChoice;
    }

    public static void ifChooseEdit(Class currentClass, Scanner scanner) {
        System.out.println("Which Course is the quiz in?");
        int courseCounter = 0;
        for (int f = 0; f < currentClass.getCourses().size(); f++) {
            System.out.printf("%d. %s\n", courseCounter + 1,
                    currentClass.getCourses().get(courseCounter).getCourseName());
            courseCounter++;
        }
        int courseSelection = scanner.nextInt();
        System.out.println("Which quiz do you want to edit?");
        int quizCounter = 0;
        for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1).getQuizzes().size(); f++) {
            System.out.printf("%d. %s\n", quizCounter + 1,
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizCounter).getName());
            quizCounter++;
        }
        int quizSelection = scanner.nextInt();
        boolean continueEditing = true;
        while (continueEditing) {
            System.out.println("What do you want to edit about the quiz?");
            System.out.print(ABOUT_EDIT_QUIZ_QUESTION);
            int editQuizSelection = scanner.nextInt();
            scanner.nextLine();
            switch (editQuizSelection) {
                case 1: {
                    System.out.println("What is the new name?");
                    String newName = scanner.nextLine();
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).setName(newName);
                    break;
                }
                case 2: {
                    System.out.println("Which Question do you want to Edit?");
                    int questionCounter = 0;
                    for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).getQuestions().size(); f++) {
                        System.out.printf("%d. %s\n", questionCounter + 1,
                                currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection - 1)
                                        .getQuestions().get(questionCounter).getPrompt());
                        questionCounter++;
                    }
                    int questionSelection = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What do you want the new Question to be?");
                    String newQuestion = scanner.nextLine();
                    currentClass.getCourses().get(courseSelection - 1).getQuizzes().get(questionSelection - 1)
                            .getQuestions().get(questionSelection - 1).setPrompt(newQuestion);
                    break;
                }
                case 3: {
                    System.out.println("Which Question do you want to Edit?");
                    int questionCounter = 0;
                    for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).getQuestions().size(); f++) {
                        System.out.printf("%d. %s\n", questionCounter + 1,
                                currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection - 1)
                                        .getQuestions().get(questionCounter).getPrompt());
                        questionCounter++;
                    }
                    int questionSelection = scanner.nextInt();
                    System.out.println("Which Response do you want to Edit?");
                    int responseCounter = 0;
                    for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1)
                            .getQuestions().get(questionSelection - 1).getResponses().size(); f++) {
                        System.out.printf("%d. %s\n", responseCounter + 1,
                                currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection - 1)
                                        .getQuestions().get(questionSelection - 1).getResponses()
                                        .get(f));
                        responseCounter++;

                    }
                    int responseSelection = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What do you want to change it to?");
                    String newResponse = scanner.nextLine();
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1)
                            .getQuestions().get(questionSelection - 1).getResponses()
                            .set(responseSelection - 1, newResponse);
                    break;
                }
                case 4: {
                    System.out.println("Which Question do you want to Edit?");
                    int questionCounter = 0;
                    for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).getQuestions().size(); f++) {
                        System.out.printf("%d. %s\n", questionCounter + 1,
                                currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection - 1)
                                        .getQuestions().get(questionCounter).getPrompt());
                    }
                    int questionSelection = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Which Response do you want to be the Answer?");
                    int responseCounter = 0;
                    for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1)
                            .getQuestions().get(questionSelection - 1).getResponses().size(); f++) {
                        System.out.printf("%d. %s\n", questionCounter + 1,
                                currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection - 1)
                                        .getQuestions().get(questionCounter).getResponses()
                                        .get(f));
                    }
                    int answerSelection = scanner.nextInt();
                    scanner.nextLine();
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1)
                            .getQuestions().get(questionSelection - 1).setAnswer(answerSelection - 1);
                    break;
                }
                case 5: {
                    continueEditing = false;
                    break;
                }
            }
        }
    }

    public static void ifChooseCreate(Class currentClass, Scanner scanner) {
        //giving the user a choice to upload a quiz or create it manually
        int choice = 0;
        do {
            System.out.printf("1. Create quiz manually\n2. Upload a file\n");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
            scanner.nextLine();
            System.out.println("Which Course is the quiz in?");
            int courseCounter = 0;
            for(int f = 0; f < currentClass.getCourses().size(); f++) {
                System.out.printf("%d. %s\n", courseCounter + 1,
                        currentClass.getCourses().get(courseCounter).getCourseName());
                courseCounter++;
            }
            int courseSelection = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                //Manually creating a quiz

                Quiz quiz = manuallyCreateQuiz(scanner);
                ArrayList<Quiz> newQuizzes = new ArrayList<>();
                newQuizzes = currentClass.getCourses().get(courseSelection - 1).getQuizzes();
                newQuizzes.add(quiz);
                currentClass.getCourses().get(courseSelection - 1).setQuizzes(newQuizzes);
            } else if (choice == 2) {
                System.out.print("Please have it in the following format:\n" +
                        "Quiz_Name: (insert quiz name)\n" +
                        "Question: (insert question)\n" +
                        "Weight: (insert weight)\n" +
                        "Correct_Answer: (insert Correct Answer)\n" +
                        "Answer: (insert Wrong answer)\n" +
                        "Answer: (insert Wrong answer)\n");
                System.out.println("Please enter a file name");
                String fileName = scanner.nextLine();
                Quiz q = new Quiz(fileName);
                currentClass.getCourses().get(courseSelection - 1).addQuiz(q);

            }
        } while (choice != 1 && choice != 2);
    }

    public static void ifChooseDelete(Class currentClass, Scanner scanner) {
        System.out.println("Which Course is the quiz in?");
        int courseCounter = 0;
        for(int f = 0; f < currentClass.getCourses().size(); f++) {
            System.out.printf("%d. %s\n", courseCounter + 1,
                    currentClass.getCourses().get(courseCounter).getCourseName());
            courseCounter++;
        }
        int courseSelection = scanner.nextInt();

        System.out.println("Which quiz do you want to delete?");
        int quizCounter = 0;
        for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1).getQuizzes().size(); f++) {
            System.out.printf("%d. %s\n", quizCounter + 1,
                    currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizCounter).getName());
            quizCounter++;
        }
        int quizSelection = scanner.nextInt();
        scanner.nextLine();
        currentClass.getCourses().get(courseSelection - 1)
                .removeQuiz(currentClass.getCourses().get(courseSelection - 1),
                        currentClass.getCourses().get(courseSelection - 1).getQuizzes()
                                .get(quizSelection - 1));
    }
}


