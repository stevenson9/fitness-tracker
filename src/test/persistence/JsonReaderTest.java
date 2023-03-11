package persistence;

import model.Exercise;
import model.ListOfExercises;
import model.ListOfLogs;
import model.Log;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{


    @Test
    void testReaderWrongFile() {
        JsonReader reader = new JsonReader("./data/random.json");
        try {
            ListOfLogs logs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoLogs() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfLogs.json");
        try {
            ListOfLogs logs = reader.read();
            assertEquals(0, logs.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSimpleLogs() {
        JsonReader reader = new JsonReader("./data/testReaderSimpleLogs.json");

        try {
            ListOfLogs logs = reader.read();

            ListOfExercises loe1 = new ListOfExercises();
            loe1.addExercise( new Exercise("Bench Press", 5, 5, 145));
            loe1.addExercise( new Exercise("Overhead Press", 8, 4, 105));

            ListOfExercises loe2 = new ListOfExercises();
            loe2.addExercise( new Exercise("Deadlift", 5, 5, 225));
            loe2.addExercise( new Exercise("Dumbbell Curls", 12, 4, 25));

            checkLogs("03/10/23", "push", loe1, logs.getLog(0));
            checkLogs("03/11/23", "pull", loe2, logs.getLog(1));

            assertEquals(loe1.getExercise(0).getName(),
                    logs.getLog(0).getExercises().getExercise(0).getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
