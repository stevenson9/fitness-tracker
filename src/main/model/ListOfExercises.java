package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfExercises {

    private List<Exercise> listOfExercises;

    public ListOfExercises() {
        listOfExercises = new ArrayList<>();
    }

    public boolean addExercise(Exercise e) {
        if (listOfExercises.contains(e)) {

            listOfExercises.add(e);
        }
        return false;
    }


    public boolean removeExercise(Exercise e) {
        if (listOfExercises.contains(e)) {

            listOfExercises.remove(e);
        }
        return false;
    }



}
