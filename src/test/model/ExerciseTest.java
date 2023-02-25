package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    private Exercise testExercise;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Bench Press", 5, 5, 225);
    }

    @Test
    void testConstructor() {
        assertEquals("Bench Press", testExercise.getName());
        assertEquals(5, testExercise.getReps());
        assertEquals(5, testExercise.getSets());
        assertEquals(225, testExercise.getWeight());
    }


}
