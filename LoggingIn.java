import java.io.*;
import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoggingIn {

    private static ArrayList<User> userList = new ArrayList<>();

    public static boolean checkUser(User user) {
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
                if (userDetailFile.get(i).contains(user.getUsername())) {
                    if (userDetailFile.get(i + 1).contains(user.getPassword())) {
                        if (userDetailFile.get(i + 2).contains(String.valueOf(user.isTeacher()))) {
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
    public static boolean editPassword(User user, String oldPassword, String newPassword) {
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
                if (userDetailFile.get(i).contains(user.getUsername())) {
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

            //Add by Rishab
            for(int i = 0; i < userList.size(); i++) {
                if (user.equals(userList.get(i))) {
                    userList.get(i).setPassword(newPassword);
                }
            }

            writer.close();
            return worked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editUsername(User user, String newUsername) {
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
                if (userDetailFile.get(i).contains(user.getUsername())) {
                    String[] editUser = userDetailFile.get(i).split(" ");
                    for (int f = 0; f < editUser.length; f++) {
                        if (editUser[f].contains(user.getUsername())) {
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

            //Add by Rishab
            for(int i = 0; i < userList.size(); i++) {
                if (user.equals(userList.get(i))) {
                    userList.get(i).setUsername(newUsername);
                }
            }

            writer.close();
            return worked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createUser(User user) {
        try {
            //establish FileWriter to the UserDetails.txt text file
            FileWriter writer = new FileWriter("UserDetails.txt", true);
            boolean contains = checkRedundant(user.getUsername());
            System.out.println(contains);

            if (contains) {
                return false;
            } else {
                String userDetails = String.format("Username: %s\nPassword: %s\nTeacher: %s\n",
                        user.getUsername(), user.getPassword(), user.isTeacher());

                //Add by Rishab
                userList.add(user);

                writer.write(userDetails);
                writer.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAccount(User user)  {
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
                if (userDetailFile.get(i).contains(user.getUsername())) {
                    if (userDetailFile.get(i + 1).contains(user.getPassword())) {
                        userDetailFile.set(i, "01100100 01100101 01101100 01100101 01110100 01100101 01100100");
                        userDetailFile.set(i + 1, "01100100 01100101 01101100 01100101 01110100 01100101 01100100");
                        userDetailFile.set(i + 2, "01100100 01100101 01101100 01100101 01110100 01100101 01100100");
                        worked = true;
                    }
                }
            }
            FileWriter writer = new FileWriter("UserDetails.txt");
            for(int i = 0; i < userDetailFile.size(); i++) {
                writer.write(userDetailFile.get(i) + "\n");
            }

            //Add By Rishab
            for (User u : userList) {
                if(user.equals(u)) {
                    userList.remove(u);
                }
            }


            writer.close();
            return worked;

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

    public static void readUserInfo() {

        String username;
        String password;
        boolean teacher;
        String line;

        try {

            File courseInfoFile = new File("UserDetails.txt");
            FileReader fileReader = new FileReader(courseInfoFile);
            BufferedReader bfr = new BufferedReader(fileReader);

            line = bfr.readLine();
            username = "";
            password = "";


            while (line != null) {
                username = line.substring(line.indexOf(' ') + 1);
                line = bfr.readLine();
                password = line.substring(line.indexOf(' ') + 1);
                line = bfr.readLine();
                if (line.substring(line.indexOf(' ') + 1).equals("true")) {
                    teacher = true;
                } else {
                    teacher = false;
                }
                userList.add(new User(username, password, teacher));
                line = bfr.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
