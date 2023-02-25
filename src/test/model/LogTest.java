package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {

    private Exercise testExercise1;
    private ListOfExercises testListOfExercises;
    private Log testLog;
    private String testDate = "02/25/23";
    private String testType = "Push";

    @BeforeEach
    void runBefore() {
        testExercise1 = new Exercise("Overhead Press", 8, 5, 115);
        testListOfExercises = new ListOfExercises();
        testListOfExercises.addExercise(testExercise1);
        testLog = new Log(testDate, testType, testListOfExercises);
    }

    @Test
    void testConstructor() {
        assertEquals("02/25/23", testLog.getDate());
        assertEquals("Push", testLog.getType());
        assertEquals(testListOfExercises, testLog.getExercises());
    }
}
