package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfLogs {

    private List<Log> listOfLogs;

    public ListOfLogs() {
        listOfLogs = new ArrayList<>();
    }

    public void addLog(Log l) {
        listOfLogs.add(l);
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
}
