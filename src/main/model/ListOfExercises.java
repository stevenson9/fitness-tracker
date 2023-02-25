package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfExercises {

    private List<Exercise> listOfExercises;

    public ListOfExercises() {
        listOfExercises = new ArrayList<>();
    }

    public void addExercise(Exercise e) {
        listOfExercises.add(e);
    }


    public void removeExercise(Exercise e) {
        listOfExercises.remove(e);
    }

    public Exercise getExercise(int index) {
        return listOfExercises.get(index);
    }

    public int getLength() {
        return listOfExercises.size();
    }




}
