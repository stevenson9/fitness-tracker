package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a single exercise with name reps and sets and weight utilized
public class Exercise implements Writable {

    private String exerciseName;
    private int numberOfReps;
    private int numberOfSets;
    private int weightUsed;

    // REQUIRES: name has non-zero length
    // EFFECTS: constructs an exercise with given name reps sets and weight
    public Exercise(String name, int reps, int sets, int weight) {

        this.exerciseName = name;
        this.numberOfReps = reps;
        this.numberOfSets = sets;
        this.weightUsed = weight;

    }

    public String getName() {
        return this.exerciseName;
    }

    public int getWeight() {
        return this.weightUsed;
    }

    public int getReps() {
        return this.numberOfReps;
    }

    public int getSets() {
        return this.numberOfSets;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", exerciseName);
        json.put("reps", numberOfReps);
        json.put("sets", numberOfSets);
        json.put("weight", weightUsed);
        return json;
    }
}
