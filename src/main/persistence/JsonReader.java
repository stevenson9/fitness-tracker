package persistence;

import model.ListOfExercises;
import model.ListOfLogs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfLogs read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfLogs(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ListOfLogs parseListOfLogs(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfLogs logs = new ListOfLogs();
        ListOfExercises loe = new ListOfExercises();
        addLogs(logs, loe, jsonObject);
        return logs;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addLogs(ListOfLogs logs, ListOfExercises loe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logs");
        for (Object json : jsonArray) {
            JSONObject nextLog = (JSONObject) json;
            addLog(logs, nextLog);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addLog(ListOfLogs wr, ListOfExercises loe, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String type = jsonObject.getString("type");
        ListOfExercises exercises = addExercises(loe, jsonObject.getString("type"));
        Thingy thingy = new Thingy(name, category);
        wr.addThingy(thingy);
    }
}
