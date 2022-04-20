import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Login extends JComponent implements Runnable {
    Login login;

    JPanel panel;

    JPanel welcomePanel;
    JLabel welcomeLabel;
    JButton loginButton;
    JButton createButton;

    JMenu topMenu;
    JMenuBar menuBar;
    JMenuItem logoutItem;
    JMenuItem mainItem;

    JPanel loginPanel;
    JLabel userLabel;
    JTextField userText;
    JLabel passLabel;
    JPasswordField passText;
    String[] userOptions;
    JComboBox<String> userBox;
    JButton loginSubmitButton;

    JPanel mainTeacherPanel;
    JButton teacherSettingsButton;
    JButton createEditButton;
    
    //JPanel and JButtons for the Create and Edit Panel
    JPanel createAndEditPanel;
    JButton changeQuizzes;
    JButton createACourse;
    JButton viewStudentSubmissions;

    //JPanel and JButtons for Change Quizzes Panel
    JPanel changeQuizzesPanel;
    JButton editQuizzes;
    JButton createAQuiz;
    JButton deleteQuiz;

    //JPanel and JButtons for Create a Quiz Panel
    JPanel createAQuizPanel;
    JButton createAQuizManual;
    JButton uploadAQuiz;

    //JPanel and JButtons for Select Quiz Panel
    JPanel selectEditQuizPanel;
    JButton quizEditSelectSubmit;
    JTextField quizEditName;
    JLabel quizEditNameInfo;
    JTextField courseEditName;
    JLabel courseEditNameInfo;

    JPanel mainStudentPanel;
    JButton studentSettingsButton;
    JButton takeButton;
    JButton viewButton;

    JPanel settingsPanel;
    JButton editButton;
    JButton deleteButton;

    JPanel editPanel;
    JButton editUserButton;
    JButton editPassButton;

    JPanel editUserPanel;
    JLabel newUsernameLabel;
    JTextField newUsernameText;
    JButton newUsernameButton;

    JPanel editPassPanel;
    JLabel newPasswordLabel;
    JPasswordField newPasswordText;
    JButton newPasswordButton;

    JPanel createPanel;
    JLabel createUserLabel;
    JTextField createUserText;
    JLabel createPassLabel;
    JPasswordField createPassText;
    JComboBox<String> newTeacherBox;
    JButton newSubmitButton;

    ArrayList<User> userList;
    User user;

    public Login() {
        userList = LoggingIn.readUserInfo();
        user = null;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logoutItem) {
                // returns user to login screen
                loginPanel.setVisible(false);
                mainTeacherPanel.setVisible(false);
                mainStudentPanel.setVisible(false);
                settingsPanel.setVisible(false);
                editPanel.setVisible(false);
                editUserPanel.setVisible(false);
                editPassPanel.setVisible(false);
                createPanel.setVisible(false);
                welcomePanel.setVisible(true);
                mainItem.setVisible(false);
                user = null;
            }
            if (e.getSource() == mainItem) {
                // returns user to main menu
                settingsPanel.setVisible(false);
                editPanel.setVisible(false);
                editUserPanel.setVisible(false);
                editPassPanel.setVisible(false);
                if (user.isTeacher()) {
                    mainTeacherPanel.setVisible(true);
                } else {
                    mainStudentPanel.setVisible(true);
                }
            }
            if (e.getSource() == loginButton) {
                welcomePanel.setVisible(false);
                loginPanel.setVisible(true);
            }
            if (e.getSource() == createButton) {
                welcomePanel.setVisible(false);
                createPanel.setVisible(true);
            }
            if (e.getSource() == loginSubmitButton) {
                // checks if account information is correct and creates user object
                String username = userText.getText();
                char[] input = passText.getPassword();
                StringBuilder password = new StringBuilder();
                for (char c : input) {
                    password.append(c);
                }
                String loginBoxInput = (String) userBox.getSelectedItem();
                boolean teacherInput = true;
                if (loginBoxInput.equals(userOptions[1])) {
                    teacherInput = false;
                }
                User userAttempt = new User(username, password.toString(), teacherInput);
                boolean checkUser = LoggingIn.checkUser(userAttempt);
                if (checkUser) {
                    user = userAttempt;
                    loginPanel.setVisible(false);
                    if (teacherInput) {
                        mainTeacherPanel.setVisible(true);
                    } else {
                        mainStudentPanel.setVisible(true);
                    }
                    mainItem.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Login information is incorrect!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                userText.setText("");
                passText.setText("");
            }
            if (e.getSource() == teacherSettingsButton || e.getSource() == studentSettingsButton) {
                mainTeacherPanel.setVisible(false);
                mainStudentPanel.setVisible(false);
                settingsPanel.setVisible(true);
            }
            if (e.getSource() == createEditButton) {
                mainTeacherPanel.setVisible(false);
            }
            if (e.getSource() == takeButton) {
                mainStudentPanel.setVisible(false);
            }
            if (e.getSource() == viewButton) {
                mainStudentPanel.setVisible(false);
            }
            if (e.getSource() == editButton) {
                settingsPanel.setVisible(false);
                editPanel.setVisible(true);
            }
            if (e.getSource() == deleteButton) {
                // deletes user account
                int confirmDelete = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this account?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    boolean worked = LoggingIn.deleteAccount(user);
                    if (worked) {
                        settingsPanel.setVisible(false);
                        welcomePanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Could not delete account!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (e.getSource() == editUserButton) {
                editPanel.setVisible(false);
                editUserPanel.setVisible(true);
            }
            if (e.getSource() == editPassButton) {
                editPanel.setVisible(false);
                editPassPanel.setVisible(true);
            }
            if (e.getSource() == newUsernameButton) {
                // changes user's username
                if (newUsernameText.getText() != null) {
                    String newUsername = newUsernameText.getText();
                    // calls editUsername method
                    boolean userSuccess = LoggingIn.editUsername(user, newUsername);
                    if (userSuccess) {
                        user.setUsername(newUsername);
                        editUserPanel.setVisible(false);
                        if (user.isTeacher()) {
                            mainTeacherPanel.setVisible(true);
                        } else {
                            mainStudentPanel.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Could not edit username!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    newUsernameText.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a new username!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == newPasswordButton) {
                // changes user's password
                if (newPasswordText.getPassword() != null) {
                    StringBuilder newPassword = new StringBuilder();
                    char[] newPassInput = newPasswordText.getPassword();
                    for (char c : newPassInput) {
                        newPassword.append(c);
                    }
                    // calls editPassword method
                    boolean passSuccess = LoggingIn.editPassword(user, user.getPassword(), newPassword.toString());
                    if (passSuccess) {
                        user.setPassword(newPassword.toString());
                        editPassPanel.setVisible(false);
                        if (user.isTeacher()) {
                            mainTeacherPanel.setVisible(true);
                        } else {
                            mainStudentPanel.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Could not edit password!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    newPasswordText.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a new password!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == newSubmitButton) {
                // creates new user account
                if (createUserText.getText() != null && createPassText.getPassword() != null) {
                    // accepts all entered information and creates new user object
                    String createUsername = createUserText.getText();
                    char[] createPassInput = createPassText.getPassword();
                    StringBuilder createPassword = new StringBuilder();
                    for (char c : createPassInput) {
                        createPassword.append(c);
                    }
                    String createBoxInput = (String) newTeacherBox.getSelectedItem();
                    boolean createTeacher = true;
                    if (createBoxInput.equals(userOptions[1])) {
                        createTeacher = false;
                    }
                    user = new User(createUsername, createPassword.toString(), createTeacher);
                    // creates new user account or outputs error message
                    boolean success = LoggingIn.createUser(user);
                    if (success) {
                        createPanel.setVisible(false);
                        if (createTeacher) {
                            mainTeacherPanel.setVisible(true);
                        } else {
                            mainStudentPanel.setVisible(true);
                        }
                        mainItem.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! New account could not be created!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    createUserText.setText("");
                    createPassText.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a new username and password!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == changeQuizzes) {
                createAndEditPanel.setVisible(false);
                changeQuizzesPanel.setVisible(true);
            }
            if(e.getSource() == createACourse) {
                createAndEditPanel.setVisible(false);
            }
            if(e.getSource() == viewStudentSubmissions) {
                createAndEditPanel.setVisible(false);
            }
            if(e.getSource() == editQuizzes) {
                changeQuizzesPanel.setVisible(false);
            }
            if(e.getSource() == createAQuiz) {
                changeQuizzesPanel.setVisible(false);
                createAQuizPanel.setVisible(true);
            }
            if(e.getSource() == deleteQuiz) {
                changeQuizzesPanel.setVisible(false);
            }
            if(e.getSource() == createAQuizManual) {
                createAQuizPanel.setVisible(false);
                selectEditQuizPanel.setVisible(true);
            }
            if(e.getSource() == uploadAQuiz) {
                createAQuizPanel.setVisible(false);
            }
            if(e.getSource() == quizEditNameInfo) {
                quizEditName.getText();
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Login());
    }

    public void run() {
        JFrame frame = new JFrame("Quiz Application");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        login = new Login();
        container.add(login, BorderLayout.CENTER);

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // creates menu bar
        menuBar = new JMenuBar();
        topMenu = new JMenu("Options");
        topMenu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(topMenu);
        logoutItem = new JMenuItem("Log Out", KeyEvent.VK_T);
        logoutItem.addActionListener(actionListener);
        mainItem = new JMenuItem("Main Menu", KeyEvent.VK_T);
        mainItem.addActionListener(actionListener);
        topMenu.add(logoutItem);
        topMenu.add(mainItem);
        mainItem.setVisible(false);
        frame.setJMenuBar(menuBar);

        // creates top-level panel containing all others
        panel = new JPanel();
        container.add(panel, BorderLayout.CENTER);

        // creates welcome panel
        welcomeLabel = new JLabel("Welcome to the quiz application! Log in or create a new user account:");
        loginButton = new JButton("Log In");
        loginButton.addActionListener(actionListener);
        createButton = new JButton("Create New Account");
        createButton.addActionListener(actionListener);

        welcomePanel = new JPanel();
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(loginButton);
        welcomePanel.add(createButton);
        panel.add(welcomePanel, BorderLayout.CENTER);

        // creates login panel
        userLabel = new JLabel("Username:");
        userText = new JTextField(20);
        passLabel = new JLabel("Password:");
        passText = new JPasswordField(20);
        userOptions = new String[2];
        userOptions[0] = "Teacher";
        userOptions[1] = "Student";
        userBox = new JComboBox<>(userOptions);
        loginSubmitButton = new JButton("Submit");
        loginSubmitButton.addActionListener(actionListener);

        loginPanel = new JPanel();
        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passLabel);
        loginPanel.add(passText);
        loginPanel.add(userBox);
        loginPanel.add(loginSubmitButton);
        panel.add(loginPanel, BorderLayout.CENTER);
        loginPanel.setVisible(false);

        // creates main menu panel for teachers
        teacherSettingsButton = new JButton("Account Settings");
        teacherSettingsButton.addActionListener(actionListener);
        createEditButton = new JButton("Create and Edit");
        createEditButton.addActionListener(actionListener);

        mainTeacherPanel = new JPanel();
        mainTeacherPanel.add(teacherSettingsButton);
        mainTeacherPanel.add(createEditButton);
        panel.add(mainTeacherPanel, BorderLayout.CENTER);
        mainTeacherPanel.setVisible(false);
        
        // CREATE AND EDIT MENU - creates the Menu
        createAndEditPanel = new JPanel();
        panel.add(createAndEditPanel, BorderLayout.CENTER);


        //CREATE AND EDIT MENU - Creates the Change Quizzes Menu
        changeQuizzes = new JButton("Change Quizzes");
        Dimension accountSettingsSize = changeQuizzes.getPreferredSize();
        changeQuizzes.setBounds(75, 80, 150, accountSettingsSize.height);
        changeQuizzes.addActionListener(actionListener);

        //CREATE AND EDIT MENU - creates the Create A Course Button
        createACourse = new JButton("Create a Course");
        Dimension createAndEditSiz = createACourse.getPreferredSize();
        createACourse.setBounds(75, 130, 150, createAndEditSiz.height);
        createACourse.addActionListener(actionListener);

        //CREATE AND EDIT MENU - Creates the view Student Submissions Button
        viewStudentSubmissions = new JButton("View Student Submissions");
        Dimension viewStudentSubmissionsSiz = viewStudentSubmissions.getPreferredSize();
        viewStudentSubmissions.setBounds(75, 180, 150, viewStudentSubmissionsSiz.height);
        viewStudentSubmissions.addActionListener(actionListener);

        //CREATE AND EDIT MENU - Adds the Buttons to the JPanel
        createAndEditPanel.add(changeQuizzes);
        createAndEditPanel.add(createACourse);
        createAndEditPanel.add(viewStudentSubmissions);
        createAndEditPanel.setVisible(false);

        //CHANGE QUIZZES MENU - creates JPanel
        changeQuizzesPanel = new JPanel();
        panel.add(changeQuizzesPanel, BorderLayout.CENTER);

        //CHANGE QUIZZES MENU - creates the Edit Quizzes Button
        editQuizzes = new JButton("Edit Quiz");
        Dimension editQuizzesSize = editQuizzes.getPreferredSize();
        editQuizzes.setBounds(75, 80, 150, editQuizzesSize.height);
        editQuizzes.addActionListener(actionListener);

        //CHANGE QUIZZES MENU - creates the Create A Quiz Button
        createAQuiz = new JButton("Create a Quiz");
        Dimension createAQuizSiz = createAQuiz.getPreferredSize();
        createAQuiz.setBounds(75, 130, 150, createAQuizSiz.height);
        createAQuiz.addActionListener(actionListener);

        //CHANGE QUIZZES MENU - creates the Delete Quiz Button
        deleteQuiz = new JButton("Delete Quiz");
        Dimension deleteQuizPreferredSize = deleteQuiz.getPreferredSize();
        deleteQuiz.setBounds(75, 180, 150, deleteQuizPreferredSize.height);
        deleteQuiz.addActionListener(actionListener);

        //CHANGE QUIZZES MENU - adds the buttons to the JPanel
        changeQuizzesPanel.add(editQuizzes);
        changeQuizzesPanel.add(createAQuiz);
        changeQuizzesPanel.add(deleteQuiz);
        changeQuizzesPanel.setVisible(false);

        //CREATE A QUIZ MENU - creates the JPanel
        createAQuizPanel = new JPanel();
        panel.add(createAQuizPanel, BorderLayout.SOUTH);

        //CREATE A QUIZ MENU - adds the Create a Quiz Manually Button
        createAQuizManual = new JButton("Create a Quiz");
        Dimension createAQuizManualPreferredSize = createAQuizManual.getPreferredSize();
        //createAQuizManual.setBounds(75, 200, 150, createAQuizManualPreferredSize.height);
        createAQuizManual.addActionListener(actionListener);

        //CREATE A QUIZ MENU - adds the Upload a Quiz File Button
        uploadAQuiz = new JButton("Upload A Quiz File");
        Dimension uploadAQuizPreferredSize = uploadAQuiz.getPreferredSize();
        //uploadAQuiz.setBounds(75, 130, 150, uploadAQuizPreferredSize.height);
        uploadAQuiz.addActionListener(actionListener);

        //CREATE A QUIZ MENU - adds the Buttons to the JPanel
        createAQuizPanel.add(createAQuizManual);
        createAQuizPanel.add(uploadAQuiz);
        createAQuizPanel.setVisible(false);

        //SELECT A QUIZ MENU
        selectEditQuizPanel = new JPanel();
        panel.add(selectEditQuizPanel, BorderLayout.SOUTH);

        // creates main menu panel for students
        studentSettingsButton = new JButton("Account Settings");
        studentSettingsButton.addActionListener(actionListener);
        takeButton = new JButton("Take Quiz");
        takeButton.addActionListener(actionListener);
        viewButton = new JButton("View Quiz Results");
        viewButton.addActionListener(actionListener);

        mainStudentPanel = new JPanel();
        mainStudentPanel.add(studentSettingsButton);
        mainStudentPanel.add(takeButton);
        mainStudentPanel.add(viewButton);
        panel.add(mainStudentPanel, BorderLayout.CENTER);
        mainStudentPanel.setVisible(false);

        // creates account settings panel
        editButton = new JButton("Edit Account");
        editButton.addActionListener(actionListener);
        deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(actionListener);

        settingsPanel = new JPanel();
        settingsPanel.add(editButton);
        settingsPanel.add(deleteButton);
        panel.add(settingsPanel, BorderLayout.CENTER);
        settingsPanel.setVisible(false);

        // creates account editing panel
        editUserButton = new JButton("Edit Username");
        editUserButton.addActionListener(actionListener);
        editPassButton = new JButton("Edit Password");
        editPassButton.addActionListener(actionListener);

        editPanel = new JPanel();
        editPanel.add(editUserButton);
        editPanel.add(editPassButton);
        panel.add(editPanel, BorderLayout.CENTER);
        editPanel.setVisible(false);

        // creates username editing panel
        newUsernameLabel = new JLabel("What is your new username?");
        newUsernameText = new JTextField(20);
        newUsernameButton = new JButton("Submit");
        newUsernameButton.addActionListener(actionListener);

        editUserPanel = new JPanel();
        editUserPanel.add(newUsernameLabel);
        editUserPanel.add(newUsernameText);
        editUserPanel.add(newUsernameButton);
        panel.add(editUserPanel, BorderLayout.CENTER);
        editUserPanel.setVisible(false);

        // creates password editing panel
        newPasswordLabel = new JLabel("What is your new password?");
        newPasswordText = new JPasswordField(20);
        newPasswordButton = new JButton("Submit");
        newPasswordButton.addActionListener(actionListener);

        editPassPanel = new JPanel();
        editPassPanel.add(newPasswordLabel);
        editPassPanel.add(newPasswordText);
        editPassPanel.add(newPasswordButton);
        panel.add(editPassPanel, BorderLayout.CENTER);
        editPassPanel.setVisible(false);

        // creates account creation panel
        createUserLabel = new JLabel("Username");
        createUserText = new JTextField(20);
        createPassLabel = new JLabel("Password:");
        createPassText = new JPasswordField(20);
        newTeacherBox = new JComboBox<>(userOptions);
        newSubmitButton = new JButton("Submit");
        newSubmitButton.addActionListener(actionListener);

        createPanel = new JPanel();
        createPanel.add(createUserLabel);
        createPanel.add(createUserText);
        createPanel.add(createPassLabel);
        createPanel.add(createPassText);
        createPanel.add(newTeacherBox);
        createPanel.add(newSubmitButton);
        panel.add(createPanel, BorderLayout.CENTER);
        createPanel.setVisible(false);
    }
}
