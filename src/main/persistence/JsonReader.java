package persistence;

import model.Exercise;
import model.ListOfExercises;
import model.ListOfLogs;
import model.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from a json file source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads listoflogs from file and returns it;
    // IOException is thrown when data cannot be read
    public ListOfLogs read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfLogs(jsonObject);
    }

    // EFFECTS: returns the source file as a string after reading
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listoflogs from JSON object and returns it
    private ListOfLogs parseListOfLogs(JSONObject jsonObject) {
        ListOfLogs logs = new ListOfLogs();
        addLogs(logs, jsonObject);
        return logs;
    }

    // MODIFIES: logs
    // EFFECTS: parses logs from JSON object and adds them to logs
    private void addLogs(ListOfLogs logs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logs");
        for (Object json : jsonArray) {
            JSONObject nextLog = (JSONObject) json;
            addLog(logs,nextLog);
        }
    }

    // MODIFIES: logs
    // EFFECTS: parses individual log JSON object and adds it to logs
    private void addLog(ListOfLogs logs, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String type = jsonObject.getString("type");
        ListOfExercises exercises = parseListOfLoe(jsonObject.getJSONArray("exercises"));
        Log log = new Log(date, type, exercises);
        logs.addLog(log);


    }

    // EFFECTS: parses listofexercise from JSON Array and returns it
    private ListOfExercises parseListOfLoe(JSONArray jsonArray) {
        ListOfExercises loe = new ListOfExercises();
        addExercises(loe, jsonArray);
        return loe;
    }

    // MODIFIES: loe
    // EFFECTS: adds each individual exercise in parsed listofexercise to loe
    private void addExercises(ListOfExercises loe, JSONArray jsonArray) {

        for (Object json : jsonArray) {
            JSONObject nextLog = (JSONObject) json;
            addExercise(loe, nextLog);
        }


    }

    // MODIFIES: loe
    // EFFECTS: constructs each new exercise in loe and adds it to loe
    private void addExercise(ListOfExercises loe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int reps = jsonObject.getInt("reps");
        int sets = jsonObject.getInt("sets");
        int weight = jsonObject.getInt("weight");

        Exercise e = new Exercise(name, reps, sets, weight);
        loe.addExercise(e);


    }

}
