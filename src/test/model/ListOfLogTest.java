package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfLogTest {

    private Exercise testExercise1 = new Exercise("Bench Press", 5, 5, 145);
    private Exercise testExercise2 = new Exercise("Incline Dumbbell Press", 10, 4, 45);

    private Exercise testExercise3 = new Exercise("Squat", 5, 5, 205);

    private Exercise testExercise4 = new Exercise("Bench Press", 5, 5, 185);
    private Exercise testExercise5 = new Exercise("Overhead Press", 5, 5, 115);

    private ListOfExercises testListOfExercises1 = new ListOfExercises();
    private ListOfExercises testListOfExercises2 = new ListOfExercises();
    private ListOfExercises testListOfExercises3 = new ListOfExercises();

    private Log testLog1;
    private Log testLog2;
    private Log testLog3;
    private Log testSameDateLog;

    private ListOfLogs testListOfLogs;

    private String testDate1 = "02/25/23";
    private String testDate2 = "02/26/23";
    private String testDate3 = "02/27/23";

    private String testType1 = "Push";
    private String testType2 = "Legs";
    private String testType3 = "Push";

    @BeforeEach
    void runBefore() {
        testListOfLogs = new ListOfLogs();
        testListOfExercises1.addExercise(testExercise1);
        testListOfExercises1.addExercise(testExercise2);

        testListOfExercises2.addExercise(testExercise3);

        testListOfExercises3.addExercise(testExercise4);
        testListOfExercises3.addExercise(testExercise5);

        testLog1 = new Log(testDate1, testType1, testListOfExercises1);
        testLog2 = new Log(testDate2, testType2, testListOfExercises2);
        testLog3 = new Log(testDate3, testType3, testListOfExercises3);
        testSameDateLog = new Log(testDate1, testType1, testListOfExercises3);
    }

    @Test
    // Test for when nothing has been added to the list of logs
    void testEmptyConstructor() {
        assertEquals(0, testListOfLogs.getLength());
    }

    @Test
    // Testing the add and remove methods
    void testAddRemoveLogs() {
        assertTrue(testListOfLogs.addLog(testLog1));
        assertTrue(testListOfLogs.addLog(testLog2));
        assertTrue(testListOfLogs.addLog(testLog3));

        assertEquals(3, testListOfLogs.getLength());
        assertEquals(testLog1, testListOfLogs.getLog(0));
        assertEquals(testLog2, testListOfLogs.getLog(1));
        assertEquals(testLog3, testListOfLogs.getLog(2));

        testListOfLogs.removeLog(1);
        assertEquals(2, testListOfLogs.getLength());
        assertEquals(testLog1, testListOfLogs.getLog(0));
        assertEquals(testLog3, testListOfLogs.getLog(1));
    }

    @Test
    // Test for when a user tries to add a log that has the same date has a log already in the list
    void testAddSameDateLog() {
        assertTrue(testListOfLogs.addLog(testLog1));
        assertTrue(testListOfLogs.addLog(testLog2));

        assertEquals(2, testListOfLogs.getLength());

        assertFalse(testListOfLogs.addLog(testSameDateLog));

        assertEquals(2, testListOfLogs.getLength());
    }

    @Test
    // Test for TrackProgress method
    void testTrackProgress() {
        assertTrue(testListOfLogs.addLog(testLog1));
        assertTrue(testListOfLogs.addLog(testLog2));
        assertTrue(testListOfLogs.addLog(testLog3));

        ListOfExercises filteredList = testListOfLogs.trackProgress("Bench Press");

        assertEquals(2, filteredList.getLength());
        assertEquals("Bench Press", filteredList.getExercise(0).getName());
        assertEquals("Bench Press", filteredList.getExercise(1).getName());
        assertEquals(145, filteredList.getExercise(0).getWeight());
        assertEquals(185, filteredList.getExercise(1).getWeight());

    }

    @Test

    void testReturnLog() {
        testListOfLogs.addLog(testLog1);
        testListOfLogs.addLog(testLog2);
        testListOfLogs.addLog(testLog3);

        List<Log> logs = testListOfLogs.returnLogs();

        assertEquals(3, logs.size());
        assertEquals(testLog1, logs.get(0));



    }





}
