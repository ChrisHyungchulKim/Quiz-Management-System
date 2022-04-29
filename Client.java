import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends JComponent implements Runnable {

    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;


    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate
    ArrayList<Course> test;
    JButton enterButton; // button to enter information
    JTextField strTextField; // text field for input

    Client paint; // variable of the type SimplePaint
    String input;
    Socket socket;


    public Client() {
        input = "";
        test = new ArrayList<>();
        try {
            socket = new Socket("localhost", 1112);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        try {
//            sock = new Socket("localhost", 1234);
//            stdin = new BufferedReader(new InputStreamReader(System.in));
//        } catch (Exception e) {
//            System.err.println("Cannot connect to the server, try again later.");
//            System.exit(1);
        Client sampleGUI = new Client();
        SwingUtilities.invokeLater(sampleGUI);
    }

//        os = new PrintStream(sock.getOutputStream());

//        try {
//            if ((selectAction()).equals("1")) {
//
//                os.println("1");
//                sendFile();
//
//
////                case "2" -> {
////                    os.println("2");
////                    System.err.print("Enter file name: ");
////                    fileName = stdin.readLine();
////                    os.println(fileName);
////                    receiveFile(fileName);
////                    break;
//
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Not a valid input", "Error",
//                        JOptionPane.PLAIN_MESSAGE); //just an error message, alt tab if it doesn't pop up.
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    //sock.close();
//}

    public void run() {
        JFrame frame = new JFrame();
        strTextField = new JTextField(10);
        enterButton = new JButton("Enter");

        frame.setTitle("Simple Paint Walk through");

        Container content = frame.getContentPane();

        content.setLayout(new BorderLayout());
        paint = new Client();
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

    public static String selectAction() throws IOException {
        System.out.println("1. Send file.");
        System.out.println("2. Receive file.");
        System.out.print("\nMake selection: ");

        return stdin.readLine();
    }

    public static void sendFile() {
        try {
            System.err.print("Enter file name: ");
            fileName = stdin.readLine();

            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);


            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = sock.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to Server.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
    }

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





// We don't need the following method.

//    public static void receiveFile(String fileName) {
//        try {
//            int bytesRead;
//            InputStream in = sock.getInputStream();
//
//            DataInputStream clientData = new DataInputStream(in);
//
//            fileName = clientData.readUTF();
//            OutputStream output = new FileOutputStream(("received_from_server_" + fileName));
//            long size = clientData.readLong();
//            byte[] buffer = new byte[1024];
//            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
//                //Condition: first checks to see if the remaining size is not 0.
//                //Secondly, the read is limited to the minimum of the buffer length and the remaining size.
//                output.write(buffer, 0, bytesRead);
//                size -= bytesRead;
//            }
//
//            output.close();
//            in.close();
//
//            System.out.println("File "+fileName+" received from Server.");
//        } catch (Exception ex) {
//            System.err.println("File does not exist!");
//
//        }
//    }
}
