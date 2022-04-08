import java.util.Scanner;

public class MainMenu {

    public static final String WELCOME_PROMPT = "Welcome to QuizSpace!\n";
    public static final String START_PROMPT = "Please Enter the number corresponding to the option you want to " +
            "select\n1.Log In\n2.Create Account\n";



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.printf("%s", WELCOME_PROMPT);

        loginMenu(scanner);


    }

    public static void loginMenu(Scanner scanner) {
        String choice;
        String username;
        String password;
        String teacherAnswer;
        boolean teacher;

        while (true) {

            System.out.printf("%s", START_PROMPT);
            choice = scanner.nextLine();

            if (choice.equals("1")) {

                System.out.printf("Log In Menu:\nEnter Username:\n");
                username = scanner.nextLine();
                System.out.printf("Enter Password:\n");
                password = scanner.nextLine();

                while (true) {

                    System.out.printf("Are you a teacher?(Y/N):\n");
                    teacherAnswer = scanner.nextLine();
                    if (teacherAnswer.equals("Y")) {
                        teacher = true;
                        break;
                    } else if (teacherAnswer.equals("N")) {
                        teacher = false;
                        break;
                    } else {
                        System.out.printf("Answer invalid\nEnter either 'Y' or 'N'\n");
                    }

                }

                if(LoggingIn.checkUser(username, password, teacher)) {
                    // TODO: return user


                } else {
                    System.out.printf("No user exists with the credential\n");
                }

            } else if (choice.equals("2")) {
                //TODO: Create a new user using Logging

                System.out.printf("Create User Menu:\nEnter Username:\n");
                username = scanner.nextLine();
                System.out.printf("Enter Password:\n");
                password = scanner.nextLine();

                while (true) {

                    System.out.printf("Are you a teacher?(Y/N):\n");
                    teacherAnswer = scanner.nextLine();
                    if (teacherAnswer.equals("Y")) {
                        teacher = true;
                        break;
                    } else if (teacherAnswer.equals("N")) {
                        teacher = false;
                        break;
                    } else {
                        System.out.printf("Answer invalid\nEnter either 'Y' or 'N'\n");
                    }
                }

                LoggingIn.createUser(username, password, teacher);
                // TODO: return user
                break;

            } else {
                System.out.printf("Invalid Input! Try Again");
            }

        }
    }

    public static void studentMenu(Scanner scanner) {
        System.out.printf("Select Course:");
    }

    public static void teacherMenu(Scanner scanner) {
        System.out.printf("Select Course:");
    }

}
