package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a list of log entries
public class ListOfLogs implements Writable {

    private List<Log> listOfLogs;

    // EFFECTS: Constructs an empty list of logs
    public ListOfLogs() {
        listOfLogs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: iterate through the existing list of logs,
    //          - if there is already a log with the same date as the one you are trying to add
    //              -return false and don't add to list of logs
    //          - otherwise return true and add it to the list of logs
    public boolean addLog(Log l) {
        for (Log log : listOfLogs) {
            String date = log.getDate();

            if (Objects.equals(date, l.getDate())) {
                return false;
            }
        }
        listOfLogs.add(l);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: removes log entry to the list of exercise given index i
    public void removeLog(int i) {
        listOfLogs.remove(i);
    }

    public int getLength() {
        return listOfLogs.size();
    }

    // EFFECTS: returns a Log given the index i of the list
    public Log getLog(int i) {
        return listOfLogs.get(i);
    }

    public List<Log> returnLogs() {
        return listOfLogs;
    }

    // MODIFIES: filteredExercise
    // EFFECTS: given a string e,
    //          - create new filtered list of exercise
    //          - iterate through the list of logs and retrieve the list of exercise for each
    //          - iterate through the list of exercise and see if there are any exercise with same name as string e,
    //              - if found, add to new filtered list
    //          - when the iterations are complete, return the newly filtered list of exercises.
    public ListOfExercises trackProgress(String e) {
        ListOfExercises filteredExercise = new ListOfExercises();

        for (Log log : listOfLogs) {
            ListOfExercises exercises = log.getExercises();
            for (int i = 0; i < exercises.getLength(); i++) {
                if (exercises.getExercise(i).getName().equals(e)) {
                    filteredExercise.addExercise(exercises.getExercise(i));
                }
            }
        }
        return filteredExercise;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("logs", listOfLogsToJson());
        return json;
    }

    // EFFECTS: returns the logs in listoflogs as a JSON array
    private JSONArray listOfLogsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Log log : listOfLogs) {
            jsonArray.put(log.toJson());
        }

        return jsonArray;
    }
}
