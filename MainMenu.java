import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu {

    public static final String CREATE_ACCOUNT_QUESTION = "1. Log In\n2. Create Account\n";
    public static final String TEACHER_QUESTION = "Are you a teacher?\n";
    public static final String GENERAL_MENU = "1. Account Settings\n2. Quiz Menu\n" +
                                                "3. Quizzes\n4. Quit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean ifContinue = true;
        //scanner.nextLine();

        User currentUser = null;

        while (ifContinue) {
            int accountQuestion = displayWelcome(scanner);
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
        int generalMenuChoice = 0;
        while (generalMenuChoice != 4) {
            System.out.println(GENERAL_MENU);
            try {
                generalMenuChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number 1-4");
            }
            scanner.nextLine();
            if (generalMenuChoice == 1) {
                boolean deleted = accountSettingsDialog(currentUser, scanner);
                if (deleted) {
                    generalMenuChoice = 4;
                }
            } else if (generalMenuChoice == 2) {
                quizMenu(currentUser, scanner);
            }

        }

    }

    public static void quizMenu(User user, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        if (user.isTeacher()) {
            int quizMenuChoice = 0;
            do {
                System.out.println("1. Change Quizzes (Edit/Create/Delete)\n2. View Student Submissions");
                try {
                    quizMenuChoice = scanner.nextInt();
                    if (quizMenuChoice != 1 && quizMenuChoice != 2) {
                        System.out.println("Please choose an option from the list");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                }
                scanner.nextLine();
            } while (quizMenuChoice != 1 && quizMenuChoice != 2);

            int changeQuizChoice = 0;
            if (quizMenuChoice == 1) {
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

                if (changeQuizChoice == 1) {
                    //TODO how to determine which quiz to edit
                } else if (changeQuizChoice == 2) {
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
                        if (choice == 1) {
                            //Manually creating a quiz
                            manuallyCreateQuiz(scanner);
                        } else if (choice == 2) {
                            System.out.println("Please enter a file name");
                            String fileName = scanner.nextLine();
                            Quiz q = new Quiz(fileName);
                        }
                    } while (choice != 1 || choice != 2);

                } else if (changeQuizChoice == 3) {
                    //TODO there is no delete quiz method in quiz class
                }
            } else if (quizMenuChoice == 2) {
                //TODO view student submissions
                System.out.println("Enter students username:");
                String username = scanner.nextLine();
            }
        } else {
            //TODO student menu
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

    public static void manuallyCreateQuiz(Scanner scanner) {
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
                    continue;
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number please");
            }
            scanner.nextLine();
        } while (numberOfQuestions < 1);
        scanner.nextLine();
        for (int j = 0; j < numberOfQuestions; j++) {
            System.out.println("Enter the question prompt:");
            String prompt = scanner.nextLine();
            int numberOfResponses = 0;
            do {
                System.out.println("How many responses are there");
                try {
                    numberOfResponses = scanner.nextInt();
                    if (numberOfResponses < 1) {
                        System.out.println("Enter a number larger than 0");
                        continue;
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter a number please");
                }
                scanner.nextLine();
            } while (numberOfResponses < 1);
            scanner.nextLine();
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
                        System.out.println("This answer is not on the list");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                }
                scanner.nextLine();
            } while (answer > numberOfResponses || answer < 1);
            int weight = 0;
            do {
                System.out.println("Please enter the weight of the answer: ");
                try {
                    weight = scanner.nextInt();
                    if (weight < 1) {
                        System.out.println("Weight cannot be lower than 1");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                }
            } while (weight < 1);
            Question q = new Question(prompt, responses, answer, weight);
            questions.add(q);
        }
        //TODO what are submissions? and what to do with the created quiz object
        Quiz quiz = new Quiz(name, questions, null);
    }
}

