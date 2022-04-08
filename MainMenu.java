import java.util.Objects;
import java.util.Scanner;

public class QuizMenu {

    public static final String CREATE_ACCOUNT_QUESTION = "1. Log In\n2.Create Account\n";
    public static final String TEACHER_QUESTION = "Are you a teacher?\n";
    public static final String GENERAL_MENU = "1. Account Settings\n2. Quiz Menu\n" +
                                                "3. Quizzes\n4. Quit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean ifContinue = true;

        int accountQuestion = displayWelcome();
        //scanner.nextLine();


        User currentUser = null;

        while (ifContinue) {
            //if they want to create a new account, it tries to create and if it's already a user
            if (accountQuestion == 2) {
                User user = getUserInfo();
                //System.out.println(user.getUsername());
                accountQuestion = createAccountDialog(user);

                if (accountQuestion == 3) {
                    ifContinue = false;
                    currentUser = user;
                }

            } else {
                User user = getUserInfo();
                //System.out.println(user.getUsername());
                boolean checkUser = !LoggingIn.checkUser(user);
                if (checkUser) {
                    System.out.println("Your login information isn't correct! Please try again.");
                } else {
                    currentUser = user;
                    ifContinue = false;
                }
            }
        }
        int generalMenuChoice = 0;
        while (generalMenuChoice != 4) {
            System.out.println(GENERAL_MENU);
            generalMenuChoice = scanner.nextInt();
            if (generalMenuChoice == 1) {
                boolean deleted = accountSettingsDialog(currentUser);
                if (deleted) {
                    generalMenuChoice = 4;
                }
            }
        }

    }

    public static void quizMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        if (user.isTeacher()) {
            int quizMenuChoice;
            do {
                System.out.println("1. Change Quizzes (Edit/Create/Delete)\n2. View Student Submissions");
                quizMenuChoice = scanner.nextInt();
            } while (quizMenuChoice != 1 && quizMenuChoice != 2);

            int changeQuizChoice;
            if (quizMenuChoice == 1) {
                do {
                    System.out.println("1. Edit Quiz\n2. Create Quiz\n3. Delete Quiz");
                    changeQuizChoice = scanner.nextInt();

                } while (changeQuizChoice != 1 && changeQuizChoice != 2 && changeQuizChoice != 3);

                if (changeQuizChoice == 1) {

                } else if (changeQuizChoice == 2) {

                } else {

                }


            } else {

            }

        }
    }

    public static boolean accountSettingsDialog(User user) {
        Scanner scanner = new Scanner(System.in);

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


    public static int createAccountDialog(User user) {
        Scanner scanner = new Scanner(System.in);

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

    public static int displayWelcome() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to the Quiz Section!");

        int accountQuestion;
        do {
            System.out.print(CREATE_ACCOUNT_QUESTION);
            accountQuestion = scanner.nextInt();
            if (accountQuestion != 1 && accountQuestion != 2) {
                System.out.println("Please enter either 1 or 2.");
            }
        } while (accountQuestion != 1 && accountQuestion != 2);

        return accountQuestion;
    }

    public static String getUsername() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();


        return username;
    }

    public static String getPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();


        return password;
    }

    public static boolean getIfTeacher() {
        Scanner scanner = new Scanner(System.in);

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

    public static User getUserInfo() {
        Scanner scanner = new Scanner(System.in);
        //gets username
        String username = getUsername();

        //gets password
        String password = getPassword();

        //gets if the User is a teacher
        boolean ifTeacher = getIfTeacher();

        User user = new User(username, password, ifTeacher);

        
        return user;
    }
}

