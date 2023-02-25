package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListOfLogs {

    private List<Log> listOfLogs;

    public ListOfLogs() {
        listOfLogs = new ArrayList<>();
    }

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


    public void removeLog(int i) {
        listOfLogs.remove(i);
    }

    public int getLength() {
        return listOfLogs.size();
    }

    //EFFECTS: return specified index of log
    public Log getLog(int i) {
        return listOfLogs.get(i);
    }

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



}
