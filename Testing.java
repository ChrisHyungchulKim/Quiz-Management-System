import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

//for reference purposes:

public class Testing {

    @Test(timeout = 1000)
    public void simpleTest(){
        Class<?> personClazz = Person.class;
        if(personClazz.getModifiers() == Modifier.PUBLIC + Modifier.ABSTRACT){
            Assert.fail("Was not the right modifiers!");
            return;
        }

    }
    @Test(timeout = 1000)
    public void testChild() {
        Class<?> extension = Student.class;
        Class<?> parent = Person.class;

        if(!extension.getSuperclass().equals(parent)) {
            Assert.fail("Not the right super class!");
        }

        if(!extension.getInterfaces().equals((new Class<?>[]{Object.class, Person.class}))) {
            Assert.fail();
        }
    }

    @Test(timeout = 1000)
    public void testMethod() throws InstantiationException {
        Class<?> extension = Student.class;
        try {


            Constructor<?> constructor = extension.getDeclaredConstructor(String.class, int.class, UUID.class);
            UUID studentID = instance.getStudentID();
            Method m = extension.getDeclaredMethod("updateStudentID");
            Student instance = (Student) constructor.newInstance("Kedar, 21, UUID.randomUUid()");
            m.invoke(instance);
            UUID newID = instance.getStudentID();
            System.out.println(studentID);
            System.out.println(newID);

            if(studentID.equals(newID)) {
                Assert.fail();
            }
            
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}

//real test codes

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Testing {

        //testing for right modifiers
        //if necessary
        @Test(timeout = 1000)
        public void simpleTest() {
            Class<?> quizClazz = Quiz.class;
            Class<?> questionClazz = Question.class;
            Class<?> quizMenuClazz = QuizMenu.class;
            Class<?> courseClazz = Course.class;
            Class<?> courseInfoHandlerClazz = CourseInfoHandler.class;
            Class<?> userClazz = User.class;
            Class<?> loginClazz = LoggingIn.class;


            if (quizClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (questionClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (quizMenuClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (courseClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (courseInfoHandlerClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (userClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }
            if (loginClazz.getModifiers() != Modifier.PUBLIC) {
                Assert.fail("Was not the right modifiers!");
                return;
            }



        }
    
}
        
        
        
