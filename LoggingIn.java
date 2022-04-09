import java.io.*;
import java.util.ArrayList;

public class LoggingIn {

    private static ArrayList<User> userList;
    public static final String CREATE_ACCOUNT_QUESTION = "1. Log In\n2.Create Account\n";
    public static final String TEACHER_QUESTION = "Are you a teacher?\n";

    //Sample Main Method
    /**public static void main(String[] args) {

     Scanner scanner = new Scanner(System.in);

     boolean ifContinue = true;

     System.out.println("Welcome to the Quiz Section!");

     int accountQuestion;
     do {
     System.out.print(CREATE_ACCOUNT_QUESTION);
     accountQuestion = scanner.nextInt();
     scanner.nextLine();
     if (accountQuestion != 1 && accountQuestion != 2) {
     System.out.println("Please enter either 1 or 2.");
     }
     } while (accountQuestion != 1 && accountQuestion != 2);

     while (ifContinue) {
     System.out.println("Please enter your username: ");
     String username = scanner.nextLine();

     System.out.println("Please enter your password: ");
     String password = scanner.nextLine();

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

     if (accountQuestion == 2) {
     createUser(username, password, ifTeacher);
     ifContinue = false;
     } else {
     boolean checkUser = !checkUser(username, password, ifTeacher);
     if (checkUser) {
     System.out.println("Your login information isn't correct! Please try again.");
     } else {
     ifContinue = false;
     }
     }
     }



     }*/


    public static boolean checkUser(String username, String password, boolean teacher) {
        try {
            boolean hasCorrectInfo = false;

            //read the file and put it in an Array list
            BufferedReader reader = new BufferedReader(new FileReader("UserDetails.txt"));
            ArrayList<String> userDetailFile = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                userDetailFile.add(line);
                line = reader.readLine();
            }

            //go through the userDetailFile and check for the line with the username that you want to edit
            for (int i = 0; i < userDetailFile.size(); i++) {
                if (userDetailFile.get(i).contains(username)) {
                    if (userDetailFile.get(i + 1).contains(password)) {
                        if (userDetailFile.get(i + 2).contains(String.valueOf(teacher))) {
                            hasCorrectInfo = true;
                        }
                    }
                }
            }
            return hasCorrectInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editPassword(String username, String oldPassword, String newPassword) {
        try {
            boolean worked = false;

            //read the file and put it in an Array list
            BufferedReader reader = new BufferedReader(new FileReader("UserDetails.txt"));
            ArrayList<String> userDetailFile = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                userDetailFile.add(line);
                line = reader.readLine();
            }

            //go through the userDetailFile and check for the line with the username that you want to edit
            for (int i = 0; i < userDetailFile.size(); i++) {
                if (userDetailFile.get(i).contains(username)) {
                    String[] editUser = userDetailFile.get(i + 1).split(" ");
                    for (int f = 0; f < editUser.length; f++) {
                        if (editUser[f].contains(oldPassword)) {
                            String checkPassword = editUser[f];
                            if (checkPassword.equals(oldPassword)) {
                                editUser[f] = " " + newPassword;
                                StringBuilder formatEditUser = new StringBuilder();
                                for(int a = 0; a < editUser.length; a++) {
                                    formatEditUser.append(editUser[a]);
                                }
                                userDetailFile.set(i + 1, formatEditUser.toString());
                            }
                        }
                    }
                }
            }
            FileWriter writer = new FileWriter("UserDetails.txt");
            for(int i = 0; i < userDetailFile.size(); i++) {
                writer.write(userDetailFile.get(i) + "\n");
            }
            writer.close();
            return worked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editUsername(String username, String newUsername) {
        try {
            boolean worked = false;

            //read the file and put it in an Array list
            BufferedReader reader = new BufferedReader(new FileReader("UserDetails.txt"));
            ArrayList<String> userDetailFile = new ArrayList<>();
            String line = reader.readLine();
            System.out.println(line);
            while (line != null) {
                userDetailFile.add(line);
                line = reader.readLine();
            }

            //go through the userDetailFile and check for the line with the username that you want to edit
            for (int i = 0; i < userDetailFile.size(); i++) {
                if (userDetailFile.get(i).contains(username)) {
                    String[] editUser = userDetailFile.get(i).split(" ");
                    for (int f = 0; f < editUser.length; f++) {
                        if (editUser[f].contains(username)) {
                            editUser[f] = " " + newUsername;
                        }
                    }
                    StringBuilder formatEditUser = new StringBuilder();
                    for(int a = 0; a < editUser.length; a++) {
                        formatEditUser.append(editUser[a]);
                    }
                    userDetailFile.set(i, formatEditUser.toString());
                    worked = true;
                }
            }
            FileWriter writer = new FileWriter("UserDetails.txt");
            for(int i = 0; i < userDetailFile.size(); i++) {
                writer.write(userDetailFile.get(i) + "\n");
            }

            

            writer.close();
            return worked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createUser(String username, String password, boolean teacher) {
        try {
            //establish FileWriter to the UserDetails.txt text file
            FileWriter writer = new FileWriter("UserDetails.txt", true);
            boolean contains = checkRedundant(username);

            if (contains) {
                return false;
            } else {
                userList.add(new User(username, password,teacher));
                String userDetails = String.format("Username: %s\nPassword: %s\nTeacher: %s\n",
                        username, password, teacher);
                writer.write(userDetails);
                writer.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkRedundant(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UserDetails.txt"));
            String line = reader.readLine();
            boolean contains = false;
            while (line != null) {
                if (line.contains(username)) {
                    contains = true;
                }
                line = reader.readLine();
            }

            return contains;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUser(String username) {
        for (User u : userList) {
            if(username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    public static void removeUser(String username) {
        for (User u : userList) {
            if(username.equals(u.getUsername())) {
                userList.remove(u);
            }
        }
    }
}
