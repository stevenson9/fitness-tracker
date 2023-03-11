package persistence;

import model.ListOfExercises;
import model.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkLogs(String date, String type, ListOfExercises loe, Log log) {
        assertEquals(date, log.getDate());
        assertEquals(type, log.getType());
        assertEquals(loe.getLength(), log.getExercises().getLength());


    }
}
