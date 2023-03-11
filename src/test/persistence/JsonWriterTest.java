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

public class JsonWriterTest extends JsonTest{


    @Test
    void testWriterFileInvalid() {
        try {
            ListOfLogs logs = new ListOfLogs();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfLogs() {
        try {
            ListOfLogs logs = new ListOfLogs();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfLogs.json");
            logs = reader.read();
            assertEquals(0, logs.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSimpleLogs() {
        try {
            ListOfLogs logs = new ListOfLogs();

            ListOfExercises loe1 = new ListOfExercises();
            loe1.addExercise( new Exercise("Bench Press", 5, 5, 145));
            loe1.addExercise( new Exercise("Overhead Press", 8, 4, 105));

            ListOfExercises loe2 = new ListOfExercises();
            loe2.addExercise( new Exercise("Deadlift", 5, 5, 225));
            loe2.addExercise( new Exercise("Dumbbell Curls", 12, 4, 25));

            Log log1 = new Log("03/10/23","push", loe1);
            Log log2 = new Log("03/11/23", "pull", loe2);

            logs.addLog(log1);
            logs.addLog(log2);

            JsonWriter writer = new JsonWriter("./data/testWriterSimpleLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSimpleLogs.json");
            logs = reader.read();

            checkLogs("03/10/23", "push", loe1, logs.getLog(0));
            checkLogs("03/11/23", "pull", loe2, logs.getLog(1));

            assertEquals("Bench Press",
                    logs.getLog(0).getExercises().getExercise(0).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
