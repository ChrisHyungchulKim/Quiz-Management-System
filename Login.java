import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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

    // Take Quiz Menu
    Class currentClass = new Class(CourseInfoHandler.readCourseInfo());
    JPanel takeChooseCoursePanel;
    JComboBox<String> courseChoice;
    JLabel pickCourseLabel;
    JButton courseChoiceSubmit;
    ArrayList<String> courses;
    ArrayList<String> quizzes;

    JPanel takeChooseQuizPanel;
    JComboBox<String> quizChoice;
    JLabel pickQuizLabel;
    JButton quizChoiceSubmit;
    String cSelection;
    String qSelection;
    int courseSelection;
    int quizSelection;

    JPanel takeQuiz;
    JComboBox<String>[] answerChoices;
    JLabel[] questionPrompt;
    JButton takeQuizSubmit;

    JPanel submitPanel;
    JButton submitYesButton;
    JButton submitNoButton;
    JLabel submitPrompt;

    JPanel submitPanelNo;
    JComboBox<Integer> questionNumbers;
    JButton submitPanelNoButton;
    JLabel submitPanelNoPrompt;

    JPanel changeAnswer;
    JLabel changeAnswerPrompt;
    JButton changeAnswerSubmit;
    int questionNumber;

    // View Submissions
    JPanel viewChooseCoursePanel;
    JComboBox<String> courseChoiceView;
    JButton viewChooseCourseButton;

    JPanel viewChooseQuizPanel;
    JComboBox<String> quizChoiceView;
    JButton viewChooseQuizButton;

    JPanel viewSubmission;
    JTextArea submissionsDetails;
    JButton mainMenuButton;

    ArrayList<String> responses;

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
                takeChooseCoursePanel.setVisible(false);
                takeChooseQuizPanel.setVisible(false);
                takeQuiz.setVisible(false);
                submitPanel.setVisible(false);
                submitPanelNo.setVisible(false);
                changeAnswer.setVisible(false);
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
                courses = new ArrayList<>();

                int courseCounter = 0;
                for (int f = 0; f < currentClass.getCourses().size(); f++) {
                    courses.add(currentClass.getCourses().get(courseCounter).getCourseName());
                    courseCounter++;
                }
                courseChoice = new JComboBox(courses.toArray());
                pickCourseLabel = new JLabel("Pick a course: ");
                takeChooseCoursePanel.add(pickCourseLabel, 0);
                takeChooseCoursePanel.add(courseChoice, 1);
                takeChooseCoursePanel.setVisible(true);
            }
            if (e.getSource() == courseChoiceSubmit) {
                takeChooseCoursePanel.setVisible(false);
                quizzes = new ArrayList<>();
                cSelection = (String) courseChoice.getSelectedItem();
                courseSelection = 0;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).equals(cSelection)) {
                        courseSelection = i;
                    }
                }

                courseSelection++;
                int quizCounter = 0;
                for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1).getQuizzes().size(); f++) {
                    quizzes.add(currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizCounter).getName());
                    quizCounter++;
                }

                quizChoice = new JComboBox(quizzes.toArray());
                pickQuizLabel = new JLabel("Pick a quiz: ");
                takeChooseQuizPanel.add(pickQuizLabel, 0);
                takeChooseQuizPanel.add(quizChoice, 1);
                takeChooseQuizPanel.setVisible(true);
            }
            if (e.getSource() == quizChoiceSubmit) {
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                takeChooseQuizPanel.setVisible(false);
                qSelection = (String) quizChoice.getSelectedItem();
                quizSelection = 0;
                for (int i = 0; i < quizzes.size(); i++) {
                    if (quizzes.get(i).equals(qSelection)) {
                        quizSelection = i;
                    }
                }
                quizSelection++;
                int num = currentClass.getCourses().get(courseSelection - 1)
                        .getQuizzes().get(quizSelection - 1).getQuestions().size();
                questionPrompt = new JLabel[num];
                answerChoices = new JComboBox[num];
                for (int i = 0; i < num; i++) {
                    questionPrompt[i] = new JLabel(currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).getQuestions().get(i).getPrompt());
                    answerChoices[i] = new JComboBox(currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizSelection - 1).getQuestions().get(i).getResponses().toArray());
                    takeQuiz.add(questionPrompt[i]);
                    takeQuiz.add(answerChoices[i]);
                }
                takeQuiz.add(takeQuizSubmit);
                takeQuiz.setVisible(true);
            }
            if (e.getSource() == takeQuizSubmit) {
                takeQuiz.setVisible(false);
                responses = new ArrayList<>();
                int num = currentClass.getCourses().get(courseSelection - 1)
                        .getQuizzes().get(quizSelection - 1).getQuestions().size();
                for (int i = 0; i < num; i++) {
                    responses.add((String) answerChoices[i].getSelectedItem());
                }
                submitPanel.setVisible(true);
            }
            if (e.getSource() == submitYesButton) {
                submitPanel.setVisible(false);
                Date date = new Date();
                long time = date.getTime();
                Timestamp timestamp = new Timestamp(time);
                Submission submission = new Submission(user,
                        currentClass.getCourses().get(courseSelection - 1).getQuizzes()
                                .get(quizSelection - 1), currentClass.getCourses().get(courseSelection - 1),
                        responses, timestamp.toString(), false, new ArrayList<String>());
                submission.writeSubmission(submission, true);
                mainStudentPanel.setVisible(true);
            }
            if (e.getSource() == submitNoButton) {
                submitPanel.setVisible(false);
                int num = currentClass.getCourses().get(courseSelection - 1)
                        .getQuizzes().get(quizSelection - 1).getQuestions().size();
                Integer[] qNum = new Integer[num];
                for (int i = 0; i < num; i++) {
                    qNum[i] = i + 1;
                }
                questionNumbers = new JComboBox(qNum);

                submitPanelNo.add(questionNumbers, 1);

                submitPanelNo.setVisible(true);
            }
            if (e.getSource() == submitPanelNoButton) {
                submitPanelNo.setVisible(false);
                questionNumber = questionNumbers.getSelectedIndex();
                String question = currentClass.getCourses()
                        .get(courseSelection - 1).getQuizzes().get(quizSelection - 1).
                        getQuestions().get(questionNumber).getPrompt();
                changeAnswerPrompt = new JLabel(question);
                changeAnswer.add(changeAnswerPrompt);
                changeAnswer.add(answerChoices[questionNumber]);

                changeAnswer.setVisible(true);
            }
            if (e.getSource() == changeAnswerSubmit) {
                changeAnswer.setVisible(false);
                Date date = new Date();
                long time = date.getTime();
                Timestamp timestamp = new Timestamp(time);
                responses.set(questionNumbers.getSelectedIndex(),
                        (String) answerChoices[questionNumber].getSelectedItem());
                Submission submission = new Submission(user,
                        currentClass.getCourses().get(courseSelection - 1).getQuizzes()
                                .get(quizSelection - 1), currentClass.getCourses().get(courseSelection - 1),
                        responses, timestamp.toString(), false, new ArrayList<String>());
                mainStudentPanel.setVisible(true);
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
            if (e.getSource() == changeQuizzes) {
                createAndEditPanel.setVisible(false);
                changeQuizzesPanel.setVisible(true);
            }
            if (e.getSource() == createACourse) {
                createAndEditPanel.setVisible(false);
            }
            if (e.getSource() == viewStudentSubmissions) {
                createAndEditPanel.setVisible(false);
            }
            if (e.getSource() == editQuizzes) {
                changeQuizzesPanel.setVisible(false);
            }
            if (e.getSource() == createAQuiz) {
                changeQuizzesPanel.setVisible(false);
                createAQuizPanel.setVisible(true);
            }
            if (e.getSource() == deleteQuiz) {
                changeQuizzesPanel.setVisible(false);
            }
            if (e.getSource() == createAQuizManual) {
                createAQuizPanel.setVisible(false);
                selectEditQuizPanel.setVisible(true);
            }
            if (e.getSource() == uploadAQuiz) {
                createAQuizPanel.setVisible(false);
            }
            if (e.getSource() == quizEditNameInfo) {
                quizEditName.getText();
            }

//            ==================================================================================================
//            View submission
            if (e.getSource() == viewButton) {
                mainStudentPanel.setVisible(false);
                courses = new ArrayList<>();

                int courseCounter = 0;
                for (int f = 0; f < currentClass.getCourses().size(); f++) {
                    courses.add(currentClass.getCourses().get(courseCounter).getCourseName());
                    courseCounter++;
                }
                courseChoiceView = new JComboBox(courses.toArray());
                pickCourseLabel = new JLabel("Pick a course: ");
                viewChooseCoursePanel.add(pickCourseLabel, 0);
                viewChooseCoursePanel.add(courseChoiceView, 1);
                viewChooseCoursePanel.setVisible(true);
            }
            if (e.getSource() == viewChooseCourseButton) {
                viewChooseCoursePanel.setVisible(false);
                quizzes = new ArrayList<>();
                cSelection = (String) courseChoiceView.getSelectedItem();
                courseSelection = 0;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).equals(cSelection)) {
                        courseSelection = i;
                    }
                }

                courseSelection++;
                int quizCounter = 0;
                for (int f = 0; f < currentClass.getCourses().get(courseSelection - 1).getQuizzes().size(); f++) {
                    quizzes.add(currentClass.getCourses().get(courseSelection - 1)
                            .getQuizzes().get(quizCounter).getName());
                    quizCounter++;
                }

                quizChoiceView = new JComboBox(quizzes.toArray());
                pickQuizLabel = new JLabel("Pick a quiz: ");
                viewChooseQuizPanel.add(pickQuizLabel, 0);
                viewChooseQuizPanel.add(quizChoiceView, 1);
                viewChooseQuizPanel.setVisible(true);
            }
            if (e.getSource() == viewChooseQuizButton) {
                viewChooseQuizPanel.setVisible(false);

                String results = "";
                courseSelection = courseChoiceView.getSelectedIndex() + 1;
                quizSelection = quizChoiceView.getSelectedIndex();
                // TODO: When implementing delete the scanner call because its not needed
                ArrayList<Submission> submissions = QuizMenu.readSubmissions(currentClass);
                //assert submissions != null;
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                if (submissions != null) {
                    int counter = 0;
                    for (Submission s : submissions) {
                        if (s.getStudent().getUsername().equals(user.getUsername())
                                && s.getCourseOfQuiz().getCourseName()
                                .equals(currentClass.getCourses().get(courseSelection - 1).getCourseName())
                                && s.getQuizBeingTaken().getName()
                                .equals(currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection).getName())
                                && !s.isGraded()) {
                            results += String.format("Quiz Name: %s\n", s.getQuizBeingTaken().getName());
                            for (int i = 0; i < s.getResponses().size(); i++) {
                                results += String.format("Question: %s\n", s.getQuizBeingTaken().getQuestions().get(i));
                                results += String.format("Your Answer: %s\n", s.getResponses().get(i));
                                results += String.format("Your Grade: Not Graded\n");
                            }
                            results += String.format("Submission Timestamp: %s\n", s.getTime());
                        } else if (s.getStudent().getUsername().equals(user.getUsername())
                                && s.getCourseOfQuiz().getCourseName()
                                .equals(currentClass.getCourses().get(courseSelection - 1).getCourseName())
                                && s.getQuizBeingTaken().getName()
                                .equals(currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection).getName())
                                && s.isGraded()) {
                            results += String.format("Quiz Name: %s\n", s.getQuizBeingTaken().getName());
                            for (int i = 0; i < s.getResponses().size(); i++) {
                                results += String.format("Question: %s\n", s.getQuizBeingTaken().getQuestions().get(i));
                                results += String.format("Your Answer: %s\n", s.getResponses().get(i));
                                results += String.format("Your Grade: %s\n", s.getGrades().get(i));
                            }
                            results += String.format("Submission Timestamp: %s\n", s.getTime());
                        } else if (!(s.getStudent().getUsername().equals(user.getUsername())
                                && s.getCourseOfQuiz().getCourseName()
                                .equals(currentClass.getCourses().get(courseSelection - 1).getCourseName())
                                && s.getQuizBeingTaken().getName()
                                .equals(currentClass.getCourses().get(courseSelection - 1)
                                        .getQuizzes().get(quizSelection).getName())) && (results.equals(""))) {
                            results = String.format("Quiz Name: %s\nNo Submissions to view",
                                    currentClass.getCourses().get(courseSelection - 1)
                                    .getQuizzes().get(quizSelection).getName());
                        }
                    }
                    counter++;
                } else {
                    results = "No Submissions to view\n";
                }
                submissionsDetails = new JTextArea(results);
                viewSubmission.add(submissionsDetails, 0);
                viewSubmission.setVisible(true);
            }
            if (e.getSource() == mainMenuButton) {
                viewSubmission.setVisible(false);
                mainStudentPanel.setVisible(true);
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

        // Take Quiz Menu

        // Choosing the course
        takeChooseCoursePanel = new JPanel();
        courseChoiceSubmit = new JButton("Submit");
        courseChoiceSubmit.addActionListener(actionListener);

        takeChooseCoursePanel.add(courseChoiceSubmit);

        panel.add(takeChooseCoursePanel, BorderLayout.CENTER);
        takeChooseCoursePanel.setVisible(false);

        // Choosing the quiz
        takeChooseQuizPanel = new JPanel();
        quizChoiceSubmit = new JButton("Submit");
        quizChoiceSubmit.addActionListener(actionListener);

        takeChooseQuizPanel.add(quizChoiceSubmit);

        panel.add(takeChooseQuizPanel, BorderLayout.CENTER);
        takeChooseQuizPanel.setVisible(false);

        // Taking the quiz
        takeQuiz = new JPanel();
        takeQuizSubmit = new JButton("Submit");
        takeQuizSubmit.addActionListener(actionListener);

        panel.add(takeQuiz, BorderLayout.CENTER);
        takeQuiz.setVisible(false);

        // Submit panel
        submitPanel = new JPanel();
        submitPrompt = new JLabel("Do you want to submit?");
        submitYesButton = new JButton("Yes");
        submitYesButton.addActionListener(actionListener);
        submitNoButton = new JButton("No");
        submitNoButton.addActionListener(actionListener);

        submitPanel.add(submitPrompt);
        submitPanel.add(submitYesButton);
        submitPanel.add(submitNoButton);

        panel.add(submitPanel, BorderLayout.CENTER);
        submitPanel.setVisible(false);

        // Submit panel No option
        submitPanelNo = new JPanel();
        submitPanelNoPrompt = new JLabel("Which Question Number do you want to change?");
        submitPanelNoButton = new JButton("Submit");
        submitPanelNoButton.addActionListener(actionListener);

        submitPanelNo.add(submitPanelNoPrompt);
        submitPanelNo.add(submitPanelNoButton);

        panel.add(submitPanelNo, BorderLayout.CENTER);
        submitPanelNo.setVisible(false);

        // Re-asking the question
        changeAnswer = new JPanel();
        changeAnswerSubmit = new JButton("Submit");
        changeAnswerSubmit.addActionListener(actionListener);

        changeAnswer.add(changeAnswerSubmit);

        panel.add(changeAnswer, BorderLayout.CENTER);
        changeAnswer.setVisible(false);

//      ==================================================================================================

        // Viewing Submissions

        // Choosing a course
        viewChooseCoursePanel = new JPanel();
        viewChooseCourseButton = new JButton("Submit");
        viewChooseCourseButton.addActionListener(actionListener);

        viewChooseCoursePanel.add(viewChooseCourseButton);

        panel.add(viewChooseCoursePanel, BorderLayout.CENTER);
        viewChooseCoursePanel.setVisible(false);

        // Choosing quiz
        viewChooseQuizPanel = new JPanel();
        viewChooseQuizButton = new JButton("Submit");
        viewChooseQuizButton.addActionListener(actionListener);

        viewChooseQuizPanel.add(viewChooseQuizButton);

        panel.add(viewChooseQuizPanel);
        viewChooseQuizPanel.setVisible(false);

        // Viewing results
        viewSubmission = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(actionListener);

        viewSubmission.add(mainMenuButton);

        panel.add(viewSubmission, BorderLayout.CENTER);
        viewSubmission.setVisible(false);
    }
}
