import java.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class Sanctuary{

    //Create instance variables
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    public Sanctuary(int maxAnimals, int maxSpecies){

        //Throw if true
        if(maxAnimals <= 0 || maxSpecies <= 0 || maxSpecies > maxAnimals){
            throw new IllegalArgumentException();
        }

        //Initialize variables
        sanctuary = new HashMap<String, Integer>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    
    }

    //countForSpecies method 
    public int countForSpecies(String species){

        //Throw if true
        if(species == null){
            throw new IllegalArgumentException();
        }
        
        //if species does not exist return 0
        if(sanctuary.containsKey(species) == false){
            return 0;
        }

        //return the number of animals for a specific species
        return sanctuary.get(species);
       
       
        }


    //getTotalAnimals method
    public int getTotalAnimals(){

       Set<String> set1 = sanctuary.keySet();

        int counter = 0;
        for(String test :set1){
           counter += sanctuary.get(test);
        }
            return counter;

        }
        

    //getTotalSpecies method 
    public int getTotalSpecies(){
        return sanctuary.size();
    }

    //getMaxAnimals method
    public int getMaxAnimals(){
        return maxAnimals;
    }

    //getMaxSpecies method 
    public int getMaxSpecies(){
        return maxSpecies;
    }

    //rescue method
    public int rescue(String species, int num){ 

        //Throw if true
        if(num <= 0 || species == null){
            throw new IllegalArgumentException();
        }
        //create a variable for how much space is left
        int spaceLeft = getMaxAnimals() - getTotalAnimals(); 
        if(spaceLeft == 0){
            return num; 
        }
        //If statement that checks whether the animal exists
        if(!sanctuary.containsKey(species)){
            if(getMaxSpecies() == getTotalSpecies()){
                return num;
            }
            if(num <= spaceLeft){
                sanctuary.put(species, num);
                return 0;
            }
            int returned = num - spaceLeft;
            sanctuary.put(species, spaceLeft);
            return returned;
        }

        //int value for current amount of animals
        int curr = sanctuary.get(species); 
        
        //if the number being added exceeds, return the amount left
        if(num > spaceLeft){
            sanctuary.put(species, spaceLeft + curr);
            return num - spaceLeft;
        }
    
        //if the number being added doesn't exceed maxAnimal, just return 0
        int newNum = num + curr; 
        sanctuary.put(species, newNum);
        return 0; 
    }

    //release method
    public void release(String species, int num){

        //Throw if true 
        if(num <= 0 || species == null || sanctuary.containsKey(species) == false){
                throw new IllegalArgumentException(); 
            }
            
        //Create variable for how many animals are there
        int current = sanctuary.get(species);

        //Check if true
        if(num > current){
            throw new IllegalArgumentException(); 
        }

        //Create variable for how many animals would need to be released
        int newInt = current - num; 

        //Remove number of animals from sanctuary
        sanctuary.put(species, newInt);

         //If there are no more animals, remove the species
         if(newInt == 0){
            sanctuary.remove(species);
        }
    }

    //helpClosingSanctuary method 
    public int helpClosingSanctuary(Sanctuary closingSanctuary){
        //Throw if true
        if(closingSanctuary == null){
            throw new IllegalArgumentException();
        }

        // create keySet
        Set<String> newKeySet = closingSanctuary.sanctuary.keySet();
        // turn keyset into arraylist
        ArrayList<String> arrList = new ArrayList<>(newKeySet);
        // sort arraylist
        Collections.sort(arrList);

        //Create a counter
        int counter = 0; 

        
        // use arraylist for for each loop
        for(String i: arrList){
            if(getMaxAnimals() == getTotalAnimals()){
                break;
            }
            int totalCount = closingSanctuary.sanctuary.get(i);
            int notRescued = this.rescue(i, totalCount);
            int couldRescue = totalCount - notRescued; 
            if(couldRescue > 0){
            closingSanctuary.release(i, couldRescue);
            counter += couldRescue;
            }
        }

        return counter;

      
        
    }

}

