package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfExercisesTest {
    private Exercise testExercise1;
    private Exercise testExercise2;
    private Exercise testExercise3;
    private ListOfExercises testListOfExercises;

    @BeforeEach
    void runBefore() {
        testExercise1 = new Exercise("Bench Press", 5, 5, 225);
        testExercise2 = new Exercise("Shoulder Press", 12, 4, 35);
        testExercise3 = new Exercise("Incline Dumbbell Press", 10, 4, 50);
        testListOfExercises = new ListOfExercises();
    }

    @Test
    // Test for when nothing has been added to the list of exercises
    void testEmptyList() {
        assertEquals(0, testListOfExercises.getLength());

    }

    @Test
    // Tests for add and remove method
    void testAddRemoveExercise() {
        testListOfExercises.addExercise(testExercise1);

        assertEquals(1, testListOfExercises.getLength());

        testListOfExercises.addExercise(testExercise2);
        testListOfExercises.addExercise(testExercise3);

        assertEquals(3, testListOfExercises.getLength());
        assertEquals(testExercise2, testListOfExercises.getExercise(1));

        testListOfExercises.removeExercise(testExercise2);
        assertEquals(2, testListOfExercises.getLength());
        assertEquals(testExercise3, testListOfExercises.getExercise(1));

    }


}
