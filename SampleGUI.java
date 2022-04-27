import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;

public class SampleGUI extends JComponent implements Runnable {

    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate
    ArrayList<Course> test;
    JButton enterButton; // button to enter information
    JTextField strTextField; // text field for input

    SampleGUI paint; // variable of the type SimplePaint
    String input;
    Socket socket;

    public SampleGUI() {
        input = "";
        test = new ArrayList<>();
        try {
            socket = new Socket("localhost",3005);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SampleGUI sampleGUI = new SampleGUI();
        SwingUtilities.invokeLater(sampleGUI);
    }



    @Override
    public void run() {
        JFrame frame = new JFrame();
        strTextField = new JTextField(10);
        enterButton = new JButton("Enter");

        frame.setTitle("Simple Paint Walk through");

        Container content = frame.getContentPane();

        content.setLayout(new BorderLayout());
        paint = new SampleGUI();
        content.add(paint, BorderLayout.CENTER);

        frame.setSize(250, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.add(strTextField);
        panel.add(enterButton);
        content.add(panel, BorderLayout.NORTH);

        this.readArrayList(socket);


        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                input = strTextField.getText();
                if(input.contains("Update Course Name:")) {
                    serverCommunicator(socket, "Get ArrayList");
                    readArrayList(socket);
                    test.get(0).setCourseName(input.substring(test.indexOf(':') + 1));
                    serverCommunicator(socket, "Sending Arraylist");
                    writeArrayList(socket, test);
                }

            }
        });
    }

    /**
     * This method is used to take to the server and in conjunction with the readCommunication() can be used
     * to talk with a server to get necessary information
     *
     * @param socket -  The socket the that the server communication is started on
     * @param message - The String message that you want to write to the server
     */
    public static void serverCommunicator(Socket socket, String message) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.write(message);
            writer.println();
            writer.flush();


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * This method is to be used after you use the serverCommunicator() to get any message that the server
     * sends back
     *
     * @param socket -  The socket the that the server communication is started on
     * @return - The string that the server writes back
     */
    public static String readCommunication(Socket socket) {
        String s1 = "";

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();

            do {
                if (s1.equals("")) {
                    s1 += line;
                } else {
                    s1 += "\n" + line;
                }
                line = reader.readLine();
                if (line.equals("end")) {
                    return s1;
                }
            } while (true);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return s1;
    }

    public void readArrayList(Socket socket) {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());

            Object object = objectInput.readObject();
            test =  (ArrayList<Course>) object;
            System.out.println(test.get(0).getCourseName());

        } catch (ClassNotFoundException e) {
            System.out.println("The title list has not come from the server");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeArrayList(Socket socket, ArrayList<Course> courses) {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(courses);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
