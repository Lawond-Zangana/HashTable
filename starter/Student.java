/*
 * Name: Lawond Zangana
 * Email: lazangana@ucsd.edu
 * PID: A17708568
 */


import java.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Student implements Comparable<Student> {

//private instance variables
private final String firstName;
private final String lastName; 
private final String PID; 


//Initialize the Student's information
public Student(String firstName, String lastName, String PID){

    //Throw if arguments are null
    if(firstName == null || lastName == null || PID == null){
        throw new IllegalArgumentException(); 
    }

    this.firstName = firstName; 
    this.lastName = lastName;
    this.PID = PID; 
   
}

//Return the student's first name 
public String getFirstName(){
    return firstName;
}

//Return the student's last name
public String getLastName(){
    return lastName;
}

//Return the student's PID
public String getPID(){
    return PID;
}

//Equals method
@Override
public boolean equals(Object o){
    if(o==null) return false;

    else if(o instanceof Student){
        Student temp = (Student) o;
        if ( temp.firstName == firstName && temp.lastName == lastName 
        && temp.PID == PID){
            return true; 
        }
    }
    
    return false; 

}

//hashCode method
@Override
public int hashCode(){
    //return the hash value for these Objects
    return Objects.hash(firstName, lastName, PID);

}

//compareTo method
public int compareTo(Student o){

    //Throw if o is null
    if(o == null){
        throw new NullPointerException();
    }

    if(o.lastName.compareTo(lastName) != 0){
        return o.lastName.compareTo(lastName);
    } else
    if(o.firstName.compareTo(firstName) != 0){
        return o.firstName.compareTo(firstName);
    } else
    if(o.PID.compareTo(PID) != 0){
        return o.PID.compareTo(PID);
    } else 
    return 0;
}



}