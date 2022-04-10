import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

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
    public void testMethod() {
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
