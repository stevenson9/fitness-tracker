package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


// Represents an entry into tracker app with date, type of workout, and list of exercises
public class Log implements Writable {

    private String date;
    private String type;
    private ListOfExercises listOfExercises;

    // REQUIRES: date has non-zero length, type has non-zero length
    // EFFECTS: constructs a log entry with given date type and exercises
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("type", type);
        json.put("exercises", exercisesToJson());
        return json;
    }


    private JSONArray exercisesToJson() {
        JSONArray jsonObject = listOfExercises.listOfExercisesToJson();

        return jsonObject;
    }

}
