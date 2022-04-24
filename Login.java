import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Login extends JComponent implements Runnable {
    Login login;

    JPanel panel;

    // Login GUI ------------------------------------------------------

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

    JPanel mainStudentPanel;
    JButton studentSettingsButton;
    JButton takeButton;
    JButton viewButton;

    JPanel settingsPanel;
    JButton editButton;
    JButton deleteButton;

    JPanel editAccountPanel;
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

    // Edit Quiz GUI ------------------------------------------------------

    JPanel selectCoursePanel;
    JLabel courseLabel;
    JComboBox<Course> courseBox;
    JButton courseSubmitButton;

    JPanel selectQuizPanel;
    JLabel quizLabel;
    JComboBox<Quiz> quizBox;
    JButton quizSubmitButton;

    JPanel editQuizPanel;
    JButton editQuizName;
    JButton addNewQuestionButton;
    JButton removeQuestionButton;
    JButton editQuestion;
    JButton editResponse;
    JButton editAnswerIndex;
    JButton goBack;

    JPanel newQuizNamePanel;
    JLabel newQuizNameLabel;
    JTextField newQuizNameField;
    JButton newQuizNameButton;

    JPanel addQuestionPanel;
    JLabel questionPromptLabel;
    JTextField questionPromptField;
    JLabel questionWeightLabel;
    JTextField questionWeightField;
    JButton addQuestionButton;

    JPanel addResponsePanel;
    JLabel addResponseLabel;
    JTextField addResponseField;
    JButton newResponseButton;
    JButton lastResponseButton;

    JPanel addAnswerPanel;
    JLabel addAnswerLabel;
    JComboBox<String> addAnswerBox;
    JButton addAnswerButton;

    JPanel removeQuestionPanel;
    JLabel removeQuestionLabel;
    JComboBox<String> removeQuestionBox;
    JButton removeQuestionSubmitButton;

    static ArrayList<User> userList;
    User user;

    static ArrayList<Course> courseList;
    Course course;

    static ArrayList<Quiz> quizList;
    Quiz quiz;

    Question question;
    String prompt;
    int weight;
    int index;
    ArrayList<String> responses;

    public Login() {
        userList = LoggingIn.readUserInfo();
        user = null;

        courseList = CourseInfoHandler.readCourseInfo();
        course = null;
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
                editAccountPanel.setVisible(false);
                editUserPanel.setVisible(false);
                editPassPanel.setVisible(false);
                createPanel.setVisible(false);
                selectCoursePanel.setVisible(false);
                selectQuizPanel.setVisible(false);
                editQuizPanel.setVisible(false);
                newQuizNamePanel.setVisible(false);
                addQuestionPanel.setVisible(false);
                addResponsePanel.setVisible(false);
                addAnswerPanel.setVisible(false);
                removeQuestionPanel.setVisible(false);
                welcomePanel.setVisible(true);
                mainItem.setVisible(false);
                user = null;
            }
            if (e.getSource() == mainItem) {
                // returns user to main menu
                settingsPanel.setVisible(false);
                editAccountPanel.setVisible(false);
                editUserPanel.setVisible(false);
                editPassPanel.setVisible(false);
                selectCoursePanel.setVisible(false);
                selectQuizPanel.setVisible(false);
                editQuizPanel.setVisible(false);
                newQuizNamePanel.setVisible(false);
                addQuestionPanel.setVisible(false);
                addResponsePanel.setVisible(false);
                addAnswerPanel.setVisible(false);
                removeQuestionPanel.setVisible(false);
                if (user.isTeacher()) {
                    mainTeacherPanel.setVisible(true);
                } else {
                    mainStudentPanel.setVisible(true);
                }
            }

            // Login GUI ------------------------------------------------------

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
                assert loginBoxInput != null;
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
                editAccountPanel.setVisible(true);
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
                editAccountPanel.setVisible(false);
                editUserPanel.setVisible(true);
            }
            if (e.getSource() == editPassButton) {
                editAccountPanel.setVisible(false);
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
                    assert createBoxInput != null;
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

            // Edit Quiz GUI ------------------------------------------------------

            if (e.getSource() == courseSubmitButton) {
                // TODO: test course & quiz selection menus - not in run method?
                selectCoursePanel.setVisible(false);
                try {
                    course = (Course) courseBox.getSelectedItem();
                    assert course != null;
                    quizList = course.getQuizzes();
                    selectQuizPanel.setVisible(true);
                } catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null, "Error! This course has no quizzes!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    mainTeacherPanel.setVisible(true);
                }
            }
            if (e.getSource() == quizSubmitButton) {
                selectQuizPanel.setVisible(false);
                quiz = (Quiz) quizBox.getSelectedItem();
                editQuizPanel.setVisible(true);
            }
            if (e.getSource() == editQuizName) {
                editQuizPanel.setVisible(false);
                newQuizNamePanel.setVisible(true);
            }
            if (e.getSource() == addNewQuestionButton) {
                editQuizPanel.setVisible(false);
                addQuestionPanel.setVisible(true);
            }
            if (e.getSource() == removeQuestionButton) {
                editQuizPanel.setVisible(false);
                removeQuestionPanel.setVisible(true);
            }
            if (e.getSource() == editQuestion) {
                editQuizPanel.setVisible(false);
            }
            if (e.getSource() == editResponse) {
                editQuizPanel.setVisible(false);
            }
            if (e.getSource() == editAnswerIndex) {
                editQuizPanel.setVisible(false);
            }
            if (e.getSource() == goBack) {
                editQuizPanel.setVisible(false);
            }
            if (e.getSource() == newQuizNameButton) {
                if (newQuizNameField.getText() != null) {
                    quiz.setName(newQuizNameField.getText());
                    newQuizNamePanel.setVisible(false);
                    editQuizPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a new quiz name!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == addQuestionButton) {
                try {
                    weight = Integer.parseInt(questionWeightField.getText());
                    prompt = questionPromptField.getText();
                    addQuestionPanel.setVisible(false);
                    addResponsePanel.setVisible(true);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Error! Please enter an integer!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == newResponseButton) {
                if (addResponseField.getText() != null) {
                    String response = addResponseField.getText();
                    responses.add(response);
                    addResponseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a response!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == lastResponseButton) {
                if (addResponseField.getText() != null) {
                    String response = addResponseField.getText();
                    responses.add(response);
                    addResponsePanel.setVisible(false);
                    addAnswerPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a response!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == addAnswerButton) {
                String correct = (String) addAnswerBox.getSelectedItem();
                for (int i = 0; i < responses.size(); i++) {
                    assert correct != null;
                    if (correct.equals(responses.get(i))) {
                        index = i;
                    }
                }
                question = new Question(prompt, responses, index, weight);
                quiz.addQuestion(question, -1);
                addAnswerPanel.setVisible(false);
                editQuizPanel.setVisible(true);
            }
            if (e.getSource() == removeQuestionButton) {
                removeQuestionPanel.setVisible(false);
                editQuizPanel.setVisible(true);
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

        // Login GUI ------------------------------------------------------

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
        welcomePanel.setVisible(false);

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

        editAccountPanel = new JPanel();
        editAccountPanel.add(editUserButton);
        editAccountPanel.add(editPassButton);
        panel.add(editAccountPanel, BorderLayout.CENTER);
        editAccountPanel.setVisible(false);

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

        // Edit Quiz GUI ------------------------------------------------------

        // creates course selection panel
        courseLabel = new JLabel("Please select a course from the list:");
        Course[] courses = courseList.toArray(new Course[courseList.size()]);
        courseBox = new JComboBox<>(courses);
        courseSubmitButton = new JButton("Submit");
        courseSubmitButton.addActionListener(actionListener);

        selectCoursePanel = new JPanel();
        selectCoursePanel.add(courseLabel);
        selectCoursePanel.add(courseBox);
        selectCoursePanel.add(courseSubmitButton);
        panel.add(selectCoursePanel, BorderLayout.CENTER);
        selectCoursePanel.setVisible(true);

        // creates quiz selection panel
        quizLabel = new JLabel("Please select a quiz from the list");
        Quiz[] quizzes = quizList.toArray(new Quiz[quizList.size()]);
        quizBox = new JComboBox<>(quizzes);
        quizSubmitButton = new JButton("Submit");
        quizSubmitButton.addActionListener(actionListener);

        selectQuizPanel = new JPanel();
        selectQuizPanel.add(quizLabel);
        selectQuizPanel.add(quizBox);
        selectQuizPanel.add(quizSubmitButton);
        panel.add(selectQuizPanel, BorderLayout.CENTER);
        selectQuizPanel.setVisible(false);

        // creates editing menu panel
        editQuizName = new JButton("Edit Quiz Name");
        editQuizName.addActionListener(actionListener);
        addNewQuestionButton = new JButton("Add New Question");
        addNewQuestionButton.addActionListener(actionListener);
        removeQuestionButton = new JButton("Remove Question");
        removeQuestionButton.addActionListener(actionListener);
        editQuestion = new JButton("Edit Question");
        editQuestion.addActionListener(actionListener);
        editResponse = new JButton("Edit Question Answer");
        editResponse.addActionListener(actionListener);
        editAnswerIndex = new JButton("Change Which Answer is Correct");
        editAnswerIndex.addActionListener(actionListener);
        goBack = new JButton("Go Back");
        goBack.addActionListener(actionListener);

        editQuizPanel = new JPanel();
        editQuizPanel.add(editQuizName);
        editQuizPanel.add(addNewQuestionButton);
        editQuizPanel.add(removeQuestionButton);
        editQuizPanel.add(editQuestion);
        editQuizPanel.add(editResponse);
        editQuizPanel.add(editAnswerIndex);
        editQuizPanel.add(goBack);
        panel.add(editQuizPanel, BorderLayout.CENTER);
        editQuizPanel.setVisible(false);

        // creates quiz name changing panel
        newQuizNameLabel = new JLabel("Enter the new quiz name: ");
        newQuizNameField = new JTextField(20);
        newQuizNameButton = new JButton("Submit");
        newQuizNameButton.addActionListener(actionListener);

        newQuizNamePanel = new JPanel();
        newQuizNamePanel.add(newQuizNameLabel);
        newQuizNamePanel.add(newQuizNameField);
        newQuizNamePanel.add(newQuizNameButton);
        panel.add(newQuizNamePanel, BorderLayout.CENTER);
        newQuizNamePanel.setVisible(false);

        // creates question adding panel
        questionPromptLabel = new JLabel("New Question Prompt: ");
        questionPromptField = new JTextField(20);
        questionWeightLabel = new JLabel("Question Weight: ");
        questionWeightField = new JTextField(10);
        addQuestionButton = new JButton("Submit");
        addQuestionButton.addActionListener(actionListener);

        addQuestionPanel = new JPanel();
        addQuestionPanel.add(questionPromptLabel);
        addQuestionPanel.add(questionPromptField);
        addQuestionPanel.add(questionWeightLabel);
        addQuestionPanel.add(questionWeightField);
        addQuestionPanel.add(addQuestionButton);
        panel.add(addQuestionPanel, BorderLayout.CENTER);
        addQuestionPanel.setVisible(false);

        // creates response adding panel
        addResponseLabel = new JLabel("New Answer Choice: ");
        addResponseField = new JTextField(20);
        newResponseButton = new JButton("Add Another Answer Choice");
        newResponseButton.addActionListener(actionListener);
        lastResponseButton = new JButton("Submit Answer Choices");
        lastResponseButton.addActionListener(actionListener);

        addResponsePanel = new JPanel();
        addResponsePanel.add(addResponseLabel);
        addResponsePanel.add(addResponseField);
        addResponsePanel.add(newResponseButton);
        addResponsePanel.add(lastResponseButton);
        panel.add(addResponsePanel, BorderLayout.CENTER);
        addResponsePanel.setVisible(false);

        // creates answer choosing panel
        addAnswerLabel = new JLabel("Select which answer choice is correct: ");
        String[] responseArray = responses.toArray(new String[responses.size()]);
        addAnswerBox = new JComboBox<>(responseArray);
        addAnswerButton = new JButton("Submit Question");
        addAnswerButton.addActionListener(actionListener);

        addAnswerPanel = new JPanel();
        addAnswerPanel.add(addAnswerLabel);
        addAnswerPanel.add(addAnswerBox);
        addAnswerPanel.add(addAnswerButton);
        panel.add(addAnswerPanel, BorderLayout.CENTER);
        addAnswerPanel.setVisible(false);

        // creates question removing panel
        removeQuestionLabel = new JLabel("Select a question to remove: ");
        //removeQuestionBox = new JComboBox<>(removePrompts);
        removeQuestionButton = new JButton("Remove");
        removeQuestionButton.addActionListener(actionListener);

        removeQuestionPanel = new JPanel();
        removeQuestionPanel.add(removeQuestionLabel);
        removeQuestionPanel.add(removeQuestionBox);
        removeQuestionPanel.add(removeQuestionButton);
        panel.add(removeQuestionPanel, BorderLayout.CENTER);
        removeQuestionPanel.setVisible(false);
    }
}