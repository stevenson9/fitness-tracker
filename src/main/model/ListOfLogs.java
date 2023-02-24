package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfLogs {

    private List<Log> listOfLogs;

    public ListOfLogs() {
        listOfLogs = new ArrayList<>();
    }

    public void addExercise(Log l) {
        listOfLogs.add(l);
    }


    public void removeExercise(Log l) {
        listOfLogs.remove(l);
    }
}
