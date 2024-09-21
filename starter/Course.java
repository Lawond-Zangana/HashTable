import java.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {

    //Create instance variables
    HashSet<Student> enrolled; 
    private final int capacity; 
    private final String department; 
    private final String number; 
    private final String description; 

    //Initialize the Course's information
    public Course(String department, String number, String description, 
    int capacity){

        //Throw if arguments are null
        if(department == null || number == null || description == null){
            throw new IllegalArgumentException();
        }

        //Throw if capacity is 0 or negative
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }

        //Initialize variables
        this.department = department;
        this.number = number; 
        this.description = description; 
        this.capacity = capacity; 
        enrolled = new HashSet<Student>(); 
    }

    //getDepartment method 
    public String getDepartment(){
        return department; 
    }

    //getNumber method
    public String getNumber(){
        return number;
    }

    //getDescription method 
    public String getDescription(){
        return description; 
    }

    //getCapacity method 
    public int getCapacity(){
        return capacity;
    }

    //enroll method
    public boolean enroll(Student student){

        //throw if student is null
        if(student == null){
            throw new IllegalArgumentException();
        }
        
       if(isFull() == false && enrolled.add(student) == true){
        return true;
       }
       return false;
    }

    //drop method 
    public boolean drop(Student student){

         //throw if student is null
        if(student == null){
            throw new IllegalArgumentException();
        }

        //Remove student from course if they are in it
        if(enrolled.contains(student) == true){
           enrolled.remove(student);
           return true; 
        }
        return false; 
    }

    //cancel method
    public void cancel(){
        enrolled.clear();
    }


    //isFull method
    public boolean isFull(){
        if(enrolled.size() >= capacity){
            return true;
        }
        return false;
    }

    //getEnrolledCount method 
    public int getEnrolledCount(){
        //return how many people are currently enrolled
        return enrolled.size();
    }

    //getAvailableSeats method
    public int getAvailableSeats(){
        //return how many available seats there are
        return capacity - enrolled.size();
    }
    
    //getStudents method 
    public HashSet<Student> getStudents(){
        //return a shallow copy of the enrolled HashSet
       return (HashSet<Student>) enrolled.clone();

    }

    //getRoster method 
    public ArrayList<Student> getRoster(){

        //Create new arraylist
        ArrayList<Student> roster = new ArrayList<Student>();

        //Use for each loop to add from enrolled to roster
        for(Student i: enrolled){
            roster.add(i);
        }

        //Sort your new arraylist
        Collections.sort(roster);

        //return arraylist
        return roster;
    }

    //toString method 
    @Override
    public String toString(){

        String s = String.format("%s %s [%d] %s", 
        department, number, capacity, description);
        return s; 
    }
}