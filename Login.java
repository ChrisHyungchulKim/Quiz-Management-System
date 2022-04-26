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

    //JPanel and JButtons for the Create and Edit Panel
    JPanel createAndEditPanel;
    JButton changeQuizzes;
    JButton createACourse;
    JButton deleteACourse;
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

    JPanel selectCoursePanel;
    JLabel courseLabel;
    JComboBox<String> courseBox;
    JButton courseSubmitButton;

    JPanel selectQuizPanel;
    JLabel quizLabel;
    JComboBox<String> quizBox;
    JButton quizSubmitButton;

    JPanel editQuizPanel;
    JButton editQuizName;
    JButton addNewQuestionButton;
    JButton removeQuestionButton;
    JButton editQuestion;
    JButton editWeight;
    JButton editResponse;
    JButton editAnswerIndex;

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
    JButton newQuestionButton;

    JPanel removeQuestionPanel;
    JLabel removeQuestionLabel;
    JComboBox<String> removeQuestionBox;
    JButton removeQuestionSubmitButton;

    JPanel editPromptPanel;
    JLabel editPromptLabel;
    JComboBox<String> editPromptBox;
    JButton editPromptButton;

    JPanel newPromptPanel;
    JLabel newPromptLabel;
    JTextField newPromptField;
    JButton newPromptButton;

    JPanel editWeightPanel;
    JLabel editWeightLabel;
    JComboBox<String> editWeightBox;
    JButton editWeightButton;

    JPanel newWeightPanel;
    JLabel newWeightLabel;
    JTextField newWeightField;
    JButton newWeightButton;

    JPanel editResponsePanel;
    JLabel editResponseLabel;
    JComboBox<String> editResponseBox;
    JButton editResponseButton;

    JPanel newResponsePanel;
    JComboBox<String> newResponseBox;
    JButton newResponseSubmitButton;

    JPanel enterResponsePanel;
    JLabel enterResponseLabel;
    JTextField enterResponseField;
    JButton enterResponseButton;

    JPanel editAnswerPanel;
    JLabel editAnswerLabel;
    JComboBox<String> editAnswerBox;
    JButton editAnswerButton;

    JPanel responseSelectionPanel;
    JComboBox<String> responseSelection;
    JButton getResponseSelection;

    // Delete/Create Quiz GUI ------------------------------------------------------

    JPanel deleteQuizPanel;
    JLabel deleteQuizLabel;
    JComboBox<String> deleteQuizBox;
    JButton deleteQuizButton;

    JPanel quizFilePanel;
    JLabel quizFileLabel;
    JTextField quizFileField;
    JButton quizFileButton;

    JPanel quizNamePanel;
    JLabel quizNameLabel;
    JTextField quizNameField;
    JButton quizNameButton;

    // Delete/Create Course GUI ------------------------------------------------------

    JPanel deleteCoursePanel;
    JLabel deleteCourseLabel;
    JComboBox<String> deleteCourseBox;
    JButton deleteCourseButton;

    JPanel newCoursePanel;
    JLabel newCourseLabel;
    JTextField newCourseField;
    JButton newCourseButton;

    // Other Fields ------------------------------------------------------

    static ArrayList<User> userList;
    User user;

    Course course;
    String courseName;
    ArrayList<Quiz> quizzes;

    Quiz quiz;
    String quizName;
    ArrayList<Question> questions;

    Question question;
    String prompt;
    String response;
    int weight;
    int index;
    ArrayList<String> responses = new ArrayList<>();

    static Class currentClass = new Class(CourseInfoHandler.readCourseInfo());

    public Login() {
        userList = LoggingIn.readUserInfo();
        user = null;

        course = null;
        quiz = null;
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
                editPromptPanel.setVisible(false);
                newPromptPanel.setVisible(false);
                editWeightPanel.setVisible(false);
                newWeightPanel.setVisible(false);
                editResponsePanel.setVisible(false);
                newResponsePanel.setVisible(false);
                enterResponsePanel.setVisible(false);
                editAnswerPanel.setVisible(false);
                responseSelectionPanel.setVisible(false);
                deleteQuizPanel.setVisible(false);
                quizFilePanel.setVisible(false);
                quizNamePanel.setVisible(false);
                deleteCoursePanel.setVisible(false);
                createAndEditPanel.setVisible(false);
                changeQuizzesPanel.setVisible(false);
                createAQuizPanel.setVisible(false);
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
                editPromptPanel.setVisible(false);
                editWeightPanel.setVisible(false);
                newWeightPanel.setVisible(false);
                editResponsePanel.setVisible(false);
                newResponsePanel.setVisible(false);
                enterResponsePanel.setVisible(false);
                editAnswerPanel.setVisible(false);
                responseSelectionPanel.setVisible(false);
                deleteQuizPanel.setVisible(false);
                quizFilePanel.setVisible(false);
                quizNamePanel.setVisible(false);
                deleteCoursePanel.setVisible(false);
                createAndEditPanel.setVisible(false);
                changeQuizzesPanel.setVisible(false);
                createAQuizPanel.setVisible(false);
                newPromptPanel.setVisible(false);
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
                createAndEditPanel.setVisible(true);
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

            if (e.getSource() == changeQuizzes) {
                createAndEditPanel.setVisible(false);
                String[] courses = new String[currentClass.getCourses().size()];
                for (int i = 0; i < currentClass.getCourses().size(); i++) {
                    courses[i] = currentClass.getCourses().get(i).getCourseName();
                }
                selectCoursePanel.remove(courseBox);
                selectCoursePanel.remove(courseSubmitButton);
                courseBox = new JComboBox<>(courses);
                selectCoursePanel.add(courseBox);
                selectCoursePanel.add(courseSubmitButton);
                selectCoursePanel.setVisible(true);
            }

            if (e.getSource() == createACourse) {
                createAndEditPanel.setVisible(false);
                newCoursePanel.setVisible(true);
            }
             if (e.getSource() == deleteACourse) {
                 createAndEditPanel.setVisible(false);
                 String[] deleteCourses = new String[currentClass.getCourses().size()];
                 for (int i = 0; i < currentClass.getCourses().size(); i++) {
                     deleteCourses[i] = currentClass.getCourses().get(i).getCourseName();
                 }
                 deleteCoursePanel.remove(deleteCourseBox);
                 deleteCoursePanel.remove(deleteCourseButton);
                 deleteCourseBox = new JComboBox<>(deleteCourses);
                 deleteCoursePanel.add(deleteCourseBox);
                 deleteCoursePanel.add(deleteCourseButton);
                 deleteCoursePanel.setVisible(true);
             }
            if (e.getSource() == viewStudentSubmissions) {
                createAndEditPanel.setVisible(false);
            }
            if (e.getSource() == editQuizzes) {
                changeQuizzesPanel.setVisible(false);
                selectQuizPanel.setVisible(true);
            }
            if (e.getSource() == createAQuiz) {
                changeQuizzesPanel.setVisible(false);
                createAQuizPanel.setVisible(true);
            }
            if (e.getSource() == deleteQuiz) {
                changeQuizzesPanel.setVisible(false);
                String[] deleteQuizzes = new String[course.getQuizzes().size()];
                for (int i = 0; i < course.getQuizzes().size(); i++) {
                    deleteQuizzes[i] = course.getQuizzes().get(i).getName();
                }
                deleteQuizPanel.remove(deleteQuizBox);
                deleteQuizPanel.remove(deleteQuizButton);
                deleteQuizBox = new JComboBox<>(deleteQuizzes);
                deleteQuizPanel.add(deleteQuizBox);
                deleteQuizPanel.add(deleteQuizButton);
                deleteQuizPanel.setVisible(true);
            }
            if (e.getSource() == createAQuizManual) {
                createAQuizPanel.setVisible(false);
                quizNamePanel.setVisible(true);
            }
            if (e.getSource() == uploadAQuiz) {
                createAQuizPanel.setVisible(false);
                quizFilePanel.setVisible(true);
            }

            if (e.getSource() == courseSubmitButton) {
                selectCoursePanel.setVisible(false);
                try {
                    String courseName = (String) courseBox.getSelectedItem();
                    for (int i = 0; i < currentClass.getCourses().size(); i++) {
                        assert courseName != null;
                        if (courseName.equals(currentClass.getCourses().get(i).getCourseName())) {
                            course = currentClass.getCourses().get(i);
                        }
                    }
                    //quizList = course.getQuizzes();
                    String[] quizzes = new String[course.getQuizzes().size()];
                    for (int i = 0; i < course.getQuizzes().size(); i++) {
                        quizzes[i] = course.getQuizzes().get(i).getName();
                    }
                    selectQuizPanel.remove(quizBox);
                    selectQuizPanel.remove(quizSubmitButton);
                    quizBox = new JComboBox<>(quizzes);
                    selectQuizPanel.add(quizBox);
                    selectQuizPanel.add(quizSubmitButton);
                    changeQuizzesPanel.setVisible(true);
                } catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null, "Error! This course has no quizzes!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    mainTeacherPanel.setVisible(true);
                }
            }
            if (e.getSource() == quizSubmitButton) {
                selectQuizPanel.setVisible(false);
                String quizName = (String) quizBox.getSelectedItem();
                for (int i = 0; i < course.getQuizzes().size(); i++) {
                    assert quizName != null;
                    if (quizName.equals(course.getQuizzes().get(i).getName())) {
                        quiz = course.getQuizzes().get(i);
                    }
                }
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
                String[] removePrompts = new String[quiz.getQuestions().size()];
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    removePrompts[i] = quiz.getQuestions().get(i).getPrompt();
                }
                removeQuestionPanel.remove(removeQuestionBox);
                removeQuestionPanel.remove(removeQuestionSubmitButton);
                removeQuestionBox = new JComboBox<>(removePrompts);
                removeQuestionPanel.add(removeQuestionBox);
                removeQuestionPanel.add(removeQuestionSubmitButton);
                removeQuestionPanel.setVisible(true);
            }
            if (e.getSource() == editQuestion) {
                editQuizPanel.setVisible(false);
                String[] editingPrompts = new String[quiz.getQuestions().size()];
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    editingPrompts[i] = quiz.getQuestions().get(i).getPrompt();
                }
                editPromptPanel.remove(editPromptBox);
                editPromptPanel.remove(editPromptButton);
                editPromptBox = new JComboBox<>(editingPrompts);
                editPromptPanel.add(editPromptBox);
                editPromptPanel.add(editPromptButton);
                editPromptPanel.setVisible(true);
            }
            if (e.getSource() == editWeight) {
                editQuizPanel.setVisible(false);
                String[] editingWeights = new String[quiz.getQuestions().size()];
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    editingWeights[i] = quiz.getQuestions().get(i).getPrompt();
                }
                editWeightPanel.remove(editWeightBox);
                editWeightPanel.remove(editWeightButton);
                editWeightBox = new JComboBox<>(editingWeights);
                editWeightPanel.add(editWeightBox);
                editWeightPanel.add(editWeightButton);
                editWeightPanel.setVisible(true);
            }
            if (e.getSource() == editResponse) {
                editQuizPanel.setVisible(false);
                String[] editingResponses = new String[quiz.getQuestions().size()];
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    editingResponses[i] = quiz.getQuestions().get(i).getPrompt();
                }
                editResponsePanel.remove(editResponseBox);
                editResponsePanel.remove(editResponseButton);
                editResponseBox = new JComboBox<>(editingResponses);
                editResponsePanel.add(editResponseBox);
                editResponsePanel.add(editResponseButton);
                editResponsePanel.setVisible(true);
            }
            if (e.getSource() == editAnswerIndex) {
                editQuizPanel.setVisible(false);
                String[] editingAnswers = new String[quiz.getQuestions().size()];
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    editingAnswers[i] = quiz.getQuestions().get(i).getPrompt();
                }
                editAnswerPanel.remove(editAnswerBox);
                editAnswerPanel.remove(editAnswerButton);
                editAnswerBox = new JComboBox<>(editingAnswers);
                editAnswerPanel.add(editAnswerBox);
                editAnswerPanel.add(editAnswerButton);
                editAnswerPanel.setVisible(true);
            }



            if (e.getSource() == newQuizNameButton) {
                if (newQuizNameField.getText() != null) {
                    quiz.setName(newQuizNameField.getText());
                    newQuizNameField.setText("");
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
                    responses = new ArrayList<>();
                    addQuestionPanel.setVisible(false);
                    addResponsePanel.setVisible(true);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Error! Please enter an integer!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                questionWeightField.setText("");
                questionPromptField.setText("");
            }
            if (e.getSource() == newResponseButton) {
                if (addResponseField.getText() != null) {
                    response = addResponseField.getText();
                    responses.add(response);
                    addResponseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a response!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == lastResponseButton) {
                if (addResponseField.getText() != null) {
                    response = addResponseField.getText();
                    responses.add(response);
                    String[] responseArray = new String[responses.size()];
                    for (int i = 0; i < responses.size(); i++) {
                        responseArray[i] = responses.get(i);
                    }
                    addResponseField.setText("");
                    addResponsePanel.setVisible(false);
                    addAnswerPanel.remove(addAnswerBox);
                    addAnswerPanel.remove(addAnswerButton);
                    addAnswerPanel.remove(newQuestionButton);
                    addAnswerBox = new JComboBox<>(responseArray);
                    addAnswerPanel.add(addAnswerBox);
                    addAnswerPanel.add(addAnswerButton);
                    addAnswerPanel.add(newQuestionButton);
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
                mainTeacherPanel.setVisible(true);
            }
            if (e.getSource() == newQuestionButton) {
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
                addQuestionPanel.setVisible(true);
            }

            if (e.getSource() == removeQuestionSubmitButton) {
                removeQuestionPanel.setVisible(false);
                String removedPrompt = (String) removeQuestionBox.getSelectedItem();
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    assert removedPrompt != null;
                    if (removedPrompt.equals(quiz.getQuestions().get(i).getPrompt())) {
                        quiz.removeQuestion(quiz.getQuestions().get(i));
                    }
                }
                editQuizPanel.setVisible(true);
            }

            if (e.getSource() == editPromptButton) {
                editPromptPanel.setVisible(false);
                prompt = (String) editPromptBox.getSelectedItem();
                newPromptPanel.setVisible(true);
            }
            if (e.getSource() == newPromptButton) {
                if (newPromptField.getText() != null) {
                    String newPrompt = newPromptField.getText();
                    for (int i = 0; i < quiz.getQuestions().size(); i++) {
                        assert prompt != null;
                        if (prompt.equals(quiz.getQuestions().get(i).getPrompt())) {
                            quiz.getQuestions().get(i).setPrompt(newPrompt);
                        }
                    }
                    newPromptField.setText("");
                    newPromptPanel.setVisible(false);
                    editQuizPanel.setVisible(true);
                } else {
                JOptionPane.showMessageDialog(null, "Error! Please enter a prompt!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == editWeightButton) {
                editWeightPanel.setVisible(false);
                prompt = (String) editWeightBox.getSelectedItem();
                newWeightPanel.setVisible(true);
            }
            if (e.getSource() == newWeightButton) {
                if (newWeightField.getText() != null) {
                    try {
                        String weightString = newWeightField.getText();
                        weight = Integer.parseInt(weightString);
                        for (int i = 0; i < quiz.getQuestions().size(); i++) {
                            assert prompt != null;
                            if (prompt.equals(quiz.getQuestions().get(i).getPrompt())) {
                                quiz.getQuestions().get(i).setWeight(weight);
                            }
                        }
                        newWeightPanel.setVisible(false);
                        editQuizPanel.setVisible(true);
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Error! Please enter an integer!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    newWeightField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a weight!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == editResponseButton) {
                editResponsePanel.setVisible(false);
                prompt = (String) editResponseBox.getSelectedItem();
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    if (prompt.equals(quiz.getQuestions().get(i).getPrompt())) {
                        question = quiz.getQuestions().get(i);
                    }
                }
                String[] questionResponses = new String[question.getResponses().size()];
                for (int x = 0; x < question.getResponses().size(); x++) {
                    questionResponses[x] = question.getResponses().get(x);
                }
                newResponsePanel.remove(newResponseBox);
                newResponseBox = new JComboBox<>(questionResponses);
                newResponsePanel.add(newResponseBox, BorderLayout.WEST);
                newResponsePanel.setVisible(true);
            }
            if (e.getSource() == newResponseSubmitButton) {
                newResponsePanel.setVisible(false);
                response = (String) newResponseBox.getSelectedItem();
                enterResponsePanel.setVisible(true);
            }
            if (e.getSource() == enterResponseButton) {
                if (enterResponseField.getText() != null) {
                    String enteredResponse = enterResponseField.getText();
                    for (int i = 0; i < question.getResponses().size(); i++) {
                        if (response.equals(question.getResponses().get(i))) {
                            question.getResponses().set(i, enteredResponse);
                        }
                    }
                    enterResponseField.setText("");
                    enterResponsePanel.setVisible(false);
                    editQuizPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a new response!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == editAnswerButton) {
                editAnswerPanel.setVisible(false);
                prompt = (String) editAnswerBox.getSelectedItem();
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    if (prompt.equals(quiz.getQuestions().get(i).getPrompt())) {
                        question = quiz.getQuestions().get(i);
                    }
                }
                String[] questionResponses = new String[question.getResponses().size()];
                for (int x = 0; x < question.getResponses().size(); x++) {
                    questionResponses[x] = question.getResponses().get(x);
                }
                responseSelectionPanel.remove(responseSelection);
                responseSelection = new JComboBox<>(questionResponses);
                responseSelectionPanel.add(responseSelection, BorderLayout.WEST);
                responseSelectionPanel.setVisible(true);
            }
            if (e.getSource() == getResponseSelection) {
                responseSelectionPanel.setVisible(false);
                response = (String) responseSelection.getSelectedItem();
                int responseNumber = 0;
                for (int i = 0; i < question.getResponses().size(); i++) {
                    assert response != null;
                    if (response.equals(question.getResponses().get(i))) {
                        responseNumber = i;
                    }
                }
                question.setAnswer(responseNumber);
                editQuizPanel.setVisible(true);
            }

            // Delete/Create Quiz GUI ------------------------------------------------------

            if (e.getSource() == deleteQuizButton) {
                int confirmDelete = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this quiz?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    deleteQuizPanel.setVisible(false);
                    String deleteQuizName = (String) deleteQuizBox.getSelectedItem();
                    for (int i = 0; i < course.getQuizzes().size(); i++) {
                        assert deleteQuizName != null;
                        if (deleteQuizName.equals(course.getQuizzes().get(i).getName())) {
                            course.getQuizzes().remove(i);
                        }
                    }
                    changeQuizzesPanel.setVisible(true);
                }
            }

            if (e.getSource() == quizFileButton) {
                if (quizFileField.getText() != null) {
                    String filename = quizFileField.getText();
                    quiz = new Quiz(filename);
                    course.getQuizzes().add(quiz);
                    quizFileField.setText("");
                    quizFilePanel.setVisible(false);
                    changeQuizzesPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a file name!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == quizNameButton) {
                if (quizNameField.getText() != null) {
                    quizName = quizNameField.getText();
                    questions = new ArrayList<>();
                    quiz = new Quiz(quizName, questions);
                    course.getQuizzes().add(quiz);
                    quizNameField.setText("");
                    quizNamePanel.setVisible(false);
                    addQuestionPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a quiz name!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Delete/Create Course GUI ------------------------------------------------------

            if (e.getSource() == deleteCourseButton) {
                int confirmDelete = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    deleteCoursePanel.setVisible(false);
                    String deleteCourseName = (String) deleteCourseBox.getSelectedItem();
                    for (int i = 0; i < currentClass.getCourses().size(); i++) {
                        assert deleteCourseName != null;
                        if (deleteCourseName.equals(currentClass.getCourses().get(i).getCourseName())) {
                            currentClass.getCourses().remove(i);
                        }
                    }
                    createAndEditPanel.setVisible(true);
                }
            }

            if (e.getSource() == newCourseButton) {
                if (newCourseField.getText() != null) {
                    courseName = newCourseField.getText();
                    quizzes = new ArrayList<>();
                    course = new Course(courseName, quizzes);
                    currentClass.getCourses().add(course);
                    newCourseField.setText("");
                    newCoursePanel.setVisible(false);
                    createAQuizPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error! Please enter a course name!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        frame.setSize(1000, 400);
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
        welcomePanel.setVisible(true);

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

        // creates "create and edit" panel
        //CREATE AND EDIT MENU - Creates the Change Quizzes Button
        changeQuizzes = new JButton("Change Quizzes");
        Dimension accountSettingsSize = changeQuizzes.getPreferredSize();
        changeQuizzes.setBounds(75, 80, 150, accountSettingsSize.height);
        changeQuizzes.addActionListener(actionListener);

        //CREATE AND EDIT MENU - creates the Create A Course Button
        createACourse = new JButton("Create a Course");
        Dimension createAndEditSiz = createACourse.getPreferredSize();
        createACourse.setBounds(75, 130, 150, createAndEditSiz.height);
        createACourse.addActionListener(actionListener);

        //CREATE AND EDIT MENU - creates the Delete A Course Button
        deleteACourse = new JButton("Delete A Course");
        deleteACourse.addActionListener(actionListener);

        //CREATE AND EDIT MENU - Creates the view Student Submissions Button
        viewStudentSubmissions = new JButton("View Student Submissions");
        Dimension viewStudentSubmissionsSiz = viewStudentSubmissions.getPreferredSize();
        viewStudentSubmissions.setBounds(75, 180, 150, viewStudentSubmissionsSiz.height);
        viewStudentSubmissions.addActionListener(actionListener);

        // CREATE AND EDIT MENU - creates the Menu
        createAndEditPanel = new JPanel();
        panel.add(createAndEditPanel, BorderLayout.CENTER);
        createAndEditPanel.add(changeQuizzes);
        createAndEditPanel.add(createACourse);
        createAndEditPanel.add(deleteACourse);
        createAndEditPanel.add(viewStudentSubmissions);
        createAndEditPanel.setVisible(false);

        // creates change quizzes panel
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

        //CHANGE QUIZZES MENU - creates the "Delete Quiz" Button
        deleteQuiz = new JButton("Delete Quiz");
        Dimension deleteQuizPreferredSize = deleteQuiz.getPreferredSize();
        deleteQuiz.setBounds(75, 180, 150, deleteQuizPreferredSize.height);
        deleteQuiz.addActionListener(actionListener);

        //CHANGE QUIZZES MENU - creates JPanel
        changeQuizzesPanel = new JPanel();
        panel.add(changeQuizzesPanel, BorderLayout.CENTER);
        changeQuizzesPanel.add(editQuizzes);
        changeQuizzesPanel.add(createAQuiz);
        changeQuizzesPanel.add(deleteQuiz);
        changeQuizzesPanel.setVisible(false);

        // creates quiz creation panel
        //CREATE A QUIZ MENU - adds the "Create a Quiz Manually" Button
        createAQuizManual = new JButton("Create a Quiz");
        createAQuizManual.addActionListener(actionListener);

        //CREATE A QUIZ MENU - adds the Upload a Quiz File Button
        uploadAQuiz = new JButton("Upload A Quiz File");
        uploadAQuiz.addActionListener(actionListener);

        //CREATE A QUIZ MENU - creates the JPanel
        createAQuizPanel = new JPanel();
        panel.add(createAQuizPanel, BorderLayout.SOUTH);
        createAQuizPanel.add(createAQuizManual);
        createAQuizPanel.add(uploadAQuiz);
        createAQuizPanel.setVisible(false);

        // creates course selection panel
        courseLabel = new JLabel("Please select a course from the list:");
        courseBox = new JComboBox<>();
        courseSubmitButton = new JButton("Submit");
        courseSubmitButton.addActionListener(actionListener);

        selectCoursePanel = new JPanel();
        selectCoursePanel.add(courseLabel);
        selectCoursePanel.add(courseBox);
        selectCoursePanel.add(courseSubmitButton);
        panel.add(selectCoursePanel, BorderLayout.CENTER);
        selectCoursePanel.setVisible(false);

        // creates quiz selection panel
        quizLabel = new JLabel("Please select a quiz from the list");
        quizBox = new JComboBox<>();
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
        addNewQuestionButton = new JButton("Add Question");
        addNewQuestionButton.addActionListener(actionListener);
        removeQuestionButton = new JButton("Remove Question");
        removeQuestionButton.addActionListener(actionListener);
        editQuestion = new JButton("Edit Question");
        editQuestion.addActionListener(actionListener);
        editWeight = new JButton("Edit Weight");
        editWeight.addActionListener(actionListener);
        editResponse = new JButton("Edit Question Answer");
        editResponse.addActionListener(actionListener);
        editAnswerIndex = new JButton("Change Correct Answer");
        editAnswerIndex.addActionListener(actionListener);

        editQuizPanel = new JPanel();
        editQuizPanel.add(editQuizName);
        editQuizPanel.add(addNewQuestionButton);
        editQuizPanel.add(removeQuestionButton);
        editQuizPanel.add(editQuestion);
        editQuizPanel.add(editWeight);
        editQuizPanel.add(editResponse);
        editQuizPanel.add(editAnswerIndex);
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
        lastResponseButton = new JButton("Submit Answer Choice");
        lastResponseButton.addActionListener(actionListener);

        addResponsePanel = new JPanel();
        addResponsePanel.add(addResponseLabel);
        addResponsePanel.add(addResponseField);
        addResponsePanel.add(lastResponseButton);
        addResponsePanel.add(newResponseButton);
        panel.add(addResponsePanel, BorderLayout.CENTER);
        addResponsePanel.setVisible(false);

        // creates answer choosing panel
        addAnswerLabel = new JLabel("Select which answer choice is correct: ");
        addAnswerBox = new JComboBox<>();
        addAnswerButton = new JButton("Submit Question");
        addAnswerButton.addActionListener(actionListener);
        newQuestionButton = new JButton("Add Another Question");
        newQuestionButton.addActionListener(actionListener);

        addAnswerPanel = new JPanel();
        addAnswerPanel.add(addAnswerLabel);
        addAnswerPanel.add(addAnswerBox);
        addAnswerPanel.add(addAnswerButton);
        addAnswerPanel.add(newQuestionButton);
        panel.add(addAnswerPanel, BorderLayout.CENTER);
        addAnswerPanel.setVisible(false);

        // creates question removing panel
        removeQuestionLabel = new JLabel("Select a question to remove: ");
        removeQuestionBox = new JComboBox<>();
        removeQuestionSubmitButton = new JButton("Remove");
        removeQuestionSubmitButton.addActionListener(actionListener);

        removeQuestionPanel = new JPanel();
        removeQuestionPanel.add(removeQuestionLabel);
        removeQuestionPanel.add(removeQuestionBox);
        removeQuestionPanel.add(removeQuestionSubmitButton);
        panel.add(removeQuestionPanel, BorderLayout.CENTER);
        removeQuestionPanel.setVisible(false);

        // creates prompt editing panel
        editPromptLabel = new JLabel("Select question you would like to edit: ");
        editPromptBox = new JComboBox<>();
        editPromptButton = new JButton("Submit");
        editPromptButton.addActionListener(actionListener);

        editPromptPanel = new JPanel();
        editPromptPanel.add(editPromptLabel);
        editPromptPanel.add(editPromptBox);
        editPromptPanel.add(editPromptButton);
        panel.add(editPromptPanel, BorderLayout.CENTER);
        editPromptPanel.setVisible(false);

        // creates panel for entering new prompt
        newPromptLabel = new JLabel("Enter new prompt: ");
        newPromptField = new JTextField(20);
        newPromptButton = new JButton("Submit");
        newPromptButton.addActionListener(actionListener);

        newPromptPanel = new JPanel();
        newPromptPanel.add(newPromptLabel);
        newPromptPanel.add(newPromptField);
        newPromptPanel.add(newPromptButton);
        panel.add(newPromptPanel, BorderLayout.CENTER);
        newPromptPanel.setVisible(false);

        // creates weight editing panel
        editWeightLabel = new JLabel("Select question you would like to edit: ");
        editWeightBox = new JComboBox<>();
        editWeightButton = new JButton("Submit");
        editWeightButton.addActionListener(actionListener);

        editWeightPanel = new JPanel();
        editWeightPanel.add(editWeightLabel);
        editWeightPanel.add(editWeightBox);
        editWeightPanel.add(editWeightButton);
        panel.add(editWeightPanel, BorderLayout.CENTER);
        editWeightPanel.setVisible(false);

        // creates panel for entering new weight
        newWeightLabel = new JLabel("Enter new weight: ");
        newWeightField = new JTextField(10);
        newWeightButton = new JButton("Submit");
        newWeightButton.addActionListener(actionListener);

        newWeightPanel = new JPanel();
        newWeightPanel.add(newWeightLabel);
        newWeightPanel.add(newWeightField);
        newWeightPanel.add(newWeightButton);
        panel.add(newWeightPanel, BorderLayout.CENTER);
        newWeightPanel.setVisible(false);

        // creates panel for editing response
        editResponseLabel = new JLabel("Select which question you would like to edit: ");
        editResponseBox = new JComboBox<>();
        editResponseButton = new JButton("Submit");
        editResponseButton.addActionListener(actionListener);

        editResponsePanel = new JPanel();
        editResponsePanel.add(editResponseLabel);
        editResponsePanel.add(editResponseBox);
        editResponsePanel.add(editResponseButton);
        panel.add(editResponsePanel, BorderLayout.CENTER);
        editResponsePanel.setVisible(false);

        // creates panel for selecting response to edit
        newResponseBox = new JComboBox<>();
        newResponseSubmitButton = new JButton("Select Response to Edit");
        newResponseSubmitButton.addActionListener(actionListener);

        newResponsePanel = new JPanel();
        newResponsePanel.add(newResponseBox);
        newResponsePanel.add(newResponseSubmitButton);
        panel.add(newResponsePanel);
        newResponsePanel.setVisible(false);

        // creates panel for entering new response
        enterResponseLabel = new JLabel("Enter the new response: ");
        enterResponseField = new JTextField(20);
        enterResponseButton = new JButton("Submit");
        enterResponseButton.addActionListener(actionListener);

        enterResponsePanel = new JPanel();
        enterResponsePanel.add(enterResponseLabel);
        enterResponsePanel.add(enterResponseField);
        enterResponsePanel.add(enterResponseButton);
        panel.add(enterResponsePanel, BorderLayout.CENTER);
        enterResponsePanel.setVisible(false);

        // creates panel for changing answer index
        editAnswerLabel = new JLabel("Select which question you would like to edit: ");
        editAnswerBox = new JComboBox<>();
        editAnswerButton = new JButton("Submit");
        editAnswerButton.addActionListener(actionListener);

        editAnswerPanel = new JPanel();
        editAnswerPanel.add(editAnswerLabel);
        editAnswerPanel.add(editAnswerBox);
        editAnswerPanel.add(editAnswerButton);
        panel.add(editAnswerPanel, BorderLayout.CENTER);
        editAnswerPanel.setVisible(false);

        // creates panel for selecting new answer
        responseSelection = new JComboBox<>();
        getResponseSelection = new JButton("Select New Correct Answer");
        getResponseSelection.addActionListener(actionListener);

        responseSelectionPanel = new JPanel();
        responseSelectionPanel.add(responseSelection);
        responseSelectionPanel.add(getResponseSelection);
        panel.add(responseSelectionPanel, BorderLayout.CENTER);
        responseSelectionPanel.setVisible(false);

        // Delete/Create Quiz GUI ------------------------------------------------------

        // creates delete quiz panel
        deleteQuizLabel = new JLabel("Select a quiz to delete from this course: ");
        deleteQuizBox = new JComboBox<>();
        deleteQuizButton = new JButton("Delete");
        deleteQuizButton.addActionListener(actionListener);

        deleteQuizPanel = new JPanel();
        deleteQuizPanel.add(deleteQuizLabel);
        deleteQuizPanel.add(deleteQuizBox);
        deleteQuizPanel.add(deleteQuizButton);
        panel.add(deleteQuizPanel, BorderLayout.CENTER);
        deleteQuizPanel.setVisible(false);

        // creates quiz file entry panel
        quizFileLabel = new JLabel("Enter a quiz file name: ");
        quizFileField = new JTextField(20);
        quizFileButton = new JButton("Submit");
        quizFileButton.addActionListener(actionListener);

        quizFilePanel = new JPanel();
        quizFilePanel.add(quizFileLabel);
        quizFilePanel.add(quizFileField);
        quizFilePanel.add(quizFileButton);
        panel.add(quizFilePanel);
        quizFilePanel.setVisible(false);

        // creates quiz name entry panel
        quizNameLabel = new JLabel("Enter the name of the new quiz: ");
        quizNameField = new JTextField(20);
        quizNameButton = new JButton("Submit & Add Questions");
        quizNameButton.addActionListener(actionListener);

        quizNamePanel = new JPanel();
        quizNamePanel.add(quizNameLabel);
        quizNamePanel.add(quizNameField);
        quizNamePanel.add(quizNameButton);
        panel.add(quizNamePanel, BorderLayout.CENTER);
        quizNamePanel.setVisible(false);

        // Delete/Create Course GUI ------------------------------------------------------

        // creates delete course panel
        deleteCourseLabel = new JLabel("Select a course to delete: ");
        deleteCourseBox = new JComboBox<>();
        deleteCourseButton = new JButton("Delete");
        deleteCourseButton.addActionListener(actionListener);

        deleteCoursePanel = new JPanel();
        deleteCoursePanel.add(deleteCourseLabel);
        deleteCoursePanel.add(deleteCourseBox);
        deleteCoursePanel.add(deleteCourseButton);
        panel.add(deleteCoursePanel, BorderLayout.CENTER);
        deleteCoursePanel.setVisible(false);

        // creates new course panel
        newCourseLabel = new JLabel("Enter the name of the new course: ");
        newCourseField = new JTextField(20);
        newCourseButton = new JButton("Add Quiz");
        newCourseButton.addActionListener(actionListener);

        newCoursePanel = new JPanel();
        newCoursePanel.add(newCourseLabel);
        newCoursePanel.add(newCourseField);
        newCoursePanel.add(newCourseButton);
        panel.add(newCoursePanel, BorderLayout.CENTER);
        newCoursePanel.setVisible(false);
    }
}