package model;

public class Exercise {

    private String exerciseName;
    private int numberOfReps;
    private int numberOfSets;
    private int weightUsed;


    public Exercise(String name, int reps, int sets, int weight) {

        this.exerciseName = name;
        this.numberOfReps = reps;
        this.numberOfSets = sets;
        this.weightUsed = weight;

    }

    public String getName() {
        return this.exerciseName;
    }

    public int weight() {
        return this.weightUsed;
    }

    public int getReps() {
        return this.numberOfReps;
    }

    public int getSets() {
        return this.numberOfSets;
    }







}
