Instructions: 
-> Pretty Simple, just download the files, compile, and run the main method in the MainMenu.java method


User Class:
This allows you to create a user with a username and password and role. Used in LoggingIn Feature whenever you read the UserDetails.txt file. When the LoggingIn Class reads the UserDetails.txt file, it creates the user object and then edits from it. Tested with a main method and tested whenever I use the LoggingIn class.

Class Class:
This groups the Courses into one single place to allow for accessors and mutators to get to them. Essentially it’s a complicated version of a static ArrayList. Tested with a sample main method and whenever I use a class that uses the class object.

CourseInfoHandler:
This reads the info from CourseDetailstxt and creates courses based on it. Very helpful because it allows a way to get at all the information about courses from the beginning of the program. Tested with a sample Main method and whever it's referenced in another class.

LoggingIn:
This is essentially all the methods to help with the logging in feature. It does stuff like check if the user exists, edits the username, edits the password, creates User, deletes account, and checks redundancy. Rishab added a readUserInfo Class to help with whenever we need an array list of the user objects. Tested with a sample main method and whenever it's referenced in another class.

Question:
This is an object to hold questions and their information in. The Question class includes several methods that allow users to interact with question objects however they please. For instance, it allows teachers to edit specific question attributes like point value and to randomize answer choices if desired. Question was tested with a sample main method and whenever it's referenced in another class.
          
Quiz:
This is an object that holds questions with a quiz name. The Quiz class includes several methods that allow users to interact with quizzes in a variety of ways. The Constructors let teachers create quizzes manually or by entering a quiz file. The methods provide the functionality to edit quizzes, calculate the max score of quizzes, randomize the order of quiz questions, create submission strings for both teachers and students to view individual quiz results, and to access the submissions for a specific student. There is also an equals method to help teachers delete quizzes and a toString method to print quiz details. Quiz was tested with a sample main method and whenever it's referenced in another class.
          
Course:
This is an class that creates an object that holds the information about a course, specifically the course name and the quizzes within the course. This was tested with a main method and whenever it's referenced in another class.

QuizMenu:
This is the class that handles the main method and displays the output menu. It includes all of the methods to help implement different classes and objects. Each method within Quiz Menu was tested and it's furthered been tested by its own main method.

Submission:
This is a class that is an object of different submissions. It holds the name of the User who submits,  the Quiz being taken, Course of the quiz, response list, timestamp, boolean if it's graded, and a list of grades. This has been tested with a test class and further tested with the test method.


