package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of exercises
public class ListOfExercises {

    private List<Exercise> listOfExercises;


    // EFFECTS: Constructs an empty list of exercises
    public ListOfExercises() {
        listOfExercises = new ArrayList<>();
    }

    // EFFECTS: adds exercise to the list of exercise given exercise e
    public void addExercise(Exercise e) {
        listOfExercises.add(e);
    }

    // EFFECTS: removes exercise to the list of exercise given exercise e
    public void removeExercise(Exercise e) {
        listOfExercises.remove(e);
    }

    // REQUIRES: List of exercise must contain > 0 elements, index <= length of list
    // EFFECTS: Return an exercise given index number in list
    public Exercise getExercise(int index) {
        return listOfExercises.get(index);
    }

    public int getLength() {
        return listOfExercises.size();
    }




}
