import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Project 5 - studentGUI.java Class
 * <p>
 * This Class creates a graphical user interface of the main menu
 *
 *
 * @author Khoa Raisr
 * @version April 17, 2022
 */

public class studentGUI extends JComponent implements Runnable {
    studentGUI studentView;

    // Student main menu buttons
    JButton accountSettingsButton;
    JButton takeQuizButton;
    JButton quizResultsButton;
    JButton quitButton;

    // Frame and panels for the main menu
    JFrame frame;
    JPanel centerPanel;

    FlowLayout flowLayout;
    Container content;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Choices from the main menu
            if (e.getSource() == accountSettingsButton) {

            }
            if (e.getSource() == takeQuizButton) {
                QuizMenu.takeQuiz(QuizMenu.getCurrentUser(), QuizMenu.getCurrentClass());
                // Code below works (changing the layout)
//                centerPanel.removeAll();
//                flowLayout = new FlowLayout();
//                centerPanel.setLayout(flowLayout);
//                flowLayout.setAlignment(FlowLayout.CENTER);
//                flowLayout.layoutContainer(content);
//                content.add(centerPanel);
//                revalidate();
//                repaint();
            }
            if (e.getSource() == quizResultsButton) {
//                QuizMenu.displayResults();
            }
            if (e.getSource() == quitButton) {

            }
        }
    };

//    public static void main(String[] args) {
//
//    }

    public void mainMenuStudent() {
        // Creating the window object for student GUI
        frame = new JFrame();
        frame.setTitle("Student Main Menu");
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        studentView = new studentGUI();
        content.add(studentView, BorderLayout.CENTER);

        // Creating a panel and buttons
        centerPanel = new JPanel();

        accountSettingsButton = new JButton("Account Settings");
        takeQuizButton = new JButton("Take a Quiz");
        takeQuizButton.addActionListener(actionListener);
        quizResultsButton = new JButton("View quiz results");
        quitButton = new JButton("Quit");

        // Adding buttons to the layout
        centerPanel.add(accountSettingsButton);
        centerPanel.add(takeQuizButton);
        centerPanel.add(quizResultsButton);
        centerPanel.add(quitButton);

        content.add(centerPanel, BorderLayout.CENTER);

        // Setting size of the window and parameters
        frame.setSize(200, 180);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        mainMenuStudent();
    }
}
