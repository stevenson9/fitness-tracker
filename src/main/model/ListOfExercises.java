package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of exercises
public class ListOfExercises implements Writable {

    private List<Exercise> listOfExercises;


    // EFFECTS: Constructs an empty list of exercises
    public ListOfExercises() {
        listOfExercises = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to the list of exercise given exercise e
    public void addExercise(Exercise e) {
        listOfExercises.add(e);
    }

    // MODIFIES: this
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("exercises", listOfExercisesToJson());
        return json;
    }

    private JSONArray listOfExercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : listOfExercises) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
