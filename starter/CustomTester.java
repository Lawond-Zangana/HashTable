import org.junit.Test;

import java.lang.reflect.Array;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * The Custom Tester for PA5, which will cover the hidden tests
 */
public class CustomTester {
    
    //Test equals when argument is a non-Student object
   @Test
    public void testStudentEquals(){
        Student student = new Student("Jon", "Jones", "A12345678");
        String testStudent = "should fail";
        Student student2 = new Student("Jon", "Jones", "A12345678");
        assertEquals("Argument is not a Student Object", false, student.equals(testStudent));
        assertEquals("Equal students", true, student.equals(student2));
    }

    /*test compareTo when both students have same first and last name
    * but smaller PID 
    */
    @Test 
    public void testStudentCompareTo(){
        Student student1 = new Student("Jon", "Jones", "12345");
        Student student2 = new Student("Jon", "Jones", "1234");
        Student student3 = new Student("Jon", "Jones", "1234");
        assertEquals("Students have different PID", -1, student1.compareTo(student2));
        assertEquals("Should be the same", 0, student2.compareTo(student3));
    }

    //Try to drop a non-existing student from course
    @Test
    public void testCourseDrop(){
        Course course = new Course("CSE", "12", "Data Structure", 3);
        Student student1 = new Student("Jon", "Jones", "12345");
        course.enroll(student1);
        assertThrows(IllegalArgumentException.class,
            ()->{
            course.drop(null); 
            });
    }

    //Try to enroll a student into a course that's at capacity
    @Test
    public void testCourseEnroll(){
        Course course = new Course("CSE", "12", "Data Structure", 1);
        Student student1 = new Student("Jon", "Jones", "12345");
        course.enroll(student1);
        Student student2 = new Student("Jon", "Jones", "1234");
        assertEquals("Try adding student to max capacity class", false, course.enroll(student2));
    }

    //Call getRoster to a course with a lot of students
    @Test
    public void testCourseGetRoster(){
        Course course = new Course("CSE", "12", "Data Structure", 50);
        Student student1 = new Student("Jon", "Jones", "12345");
        Student student2 = new Student("Jon", "Jones", "123456");
        Student student3 = new Student("Jon", "Jones", "1234567");
        Student student4 = new Student("Jon", "Jones", "12345678");
        Student student5 = new Student("Jon", "Jones", "123456789");
        course.enroll(student1);
        course.enroll(student2);
        course.enroll(student3);
        course.enroll(student4);
        course.enroll(student5);
        ArrayList<Student> arr = new ArrayList<>(); 
        arr.add(student1);
        arr.add(student2);
        arr.add(student3);
        arr.add(student4);
        arr.add(student5);
        Collections.sort(arr);
        assertEquals(arr, course.getRoster());
    }

    //Call sanctuary constructor with a negative argument
    @Test
    public void testSanctConstructor(){
        assertThrows(IllegalArgumentException.class,
            ()->{
                Sanctuary sanctuary = new Sanctuary(-1, 1);

            });

    }

    //Rescue animals where the number will exceed santuary's max capacity
    @Test
    public void testSanctRescuePartial(){
        Sanctuary sanct1 = new Sanctuary(60, 2);
        sanct1.sanctuary.put("Monkey", 50);
        sanct1.rescue("Monkey", 30);
        assertEquals(60, sanct1.getTotalAnimals());
    }

    //Rescue species when sanctuary is at max capacity for species
    @Test
    public void testSanctRescueMaxSpecies(){
        Sanctuary sanct1 = new Sanctuary(60, 2);
        sanct1.sanctuary.put("Monkey", 15);
        sanct1.sanctuary.put("Elephant", 10);
        sanct1.sanctuary.put("Lion", 3);  
        assertEquals(0, sanct1.rescue("Lion", 4));

    }

    //Release some animals from an existing species in sanctuary
    @Test
    public void testSanctReleasePartial(){
        Sanctuary sanct1 = new Sanctuary(50, 3);
        sanct1.sanctuary.put("Monkey", 20);
        sanct1.release("Monkey", 10);
        assertEquals(10, sanct1.getTotalAnimals());
    }   

    //Release more animals of a species than there is in a sanctuary
    @Test
    public void testSanctReleaseTooMany(){
        Sanctuary sanct1 = new Sanctuary(50, 3);
        sanct1.sanctuary.put("Monkey", 20);
        assertThrows(IllegalArgumentException.class,
        ()->{
            sanct1.release("Monkey", 40);

        });
    }

    /*Not all animals can be rescued from closingSanctuary, maxAnimals will
    * be reached */
    @Test
    public void testSanctHelpClosingSanctuaryPartial(){
        Sanctuary sanct1 = new Sanctuary(30, 3);
        sanct1.sanctuary.put("Monkey", 20);
        Sanctuary closing = new Sanctuary(40, 3);
        closing.sanctuary.put("Monkey", 20);
        sanct1.helpClosingSanctuary(closing);
        assertEquals(0, sanct1.helpClosingSanctuary(closing));
    }

}