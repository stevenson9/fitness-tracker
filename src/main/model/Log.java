package model;

import java.util.List;

public class Log {

    private String date;
    private String type;
    private ListOfExercises listOfExercises;

    public Log(String date, String type, ListOfExercises loe) {
        this.date = date;
        this.type = type;
        this.listOfExercises = loe;
    }

    public String getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public ListOfExercises getExercises() {
        return this.listOfExercises;
    }



}
