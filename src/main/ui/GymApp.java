package ui;

import model.Exercise;
import model.ListOfExercises;
import model.ListOfLogs;
import model.Log;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymApp {

    private ListOfLogs logs;

    private Scanner input;

    public GymApp() {
        runGymApp();
    }

    public void runGymApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            optionMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processOption(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void init() {
        logs = new ListOfLogs();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void optionMenu() {
        System.out.println("\nWelcome to the Gym Tracker App! Select from one of the options below:");
        System.out.println("\ta -> Add a new day");
        System.out.println("\tr -> Remove a past day");
        System.out.println("\tv -> View your progress!");
        System.out.println("\ttp -> Track your progress on a certain exercise!");
        System.out.println("\tq -> quit");

    }


    private void processOption(String command) {
        if (command.equals("a")) {
            addNewDay();
        } else if (command.equals("r")) {
            removeDay();
        } else if (command.equals("v")) {
            viewLog();
        } else if (command.equals("tp")) {
            viewProgress();
        } else {
            System.out.println("That is not a valid option, try again!");
        }

    }

    private void addNewDay() {
        System.out.println("What day would you like to add a new workout to? (MM/DD/YY)");

        String date = input.next();
        String type = pickTypeOfWorkout();
        ListOfExercises loe = newExercises();
        Log log = new Log(date, type, loe);

        if (logs.addLog(log)) {
            System.out.println("New day successfully added!");
        } else {
            System.out.println("Sorry the corresponding date already has a entry!");
        }





    }

    private String pickTypeOfWorkout() {
        String type = "";

        while (!(type.equals("push") || type.equals("pull") || type.equals("legs"))) {
            System.out.println("What kind of workout would you like to add? (Push/Pull/Legs)");
            type = input.next();
            type = type.toLowerCase();
        }

        return type;
    }

    private ListOfExercises newExercises() {

        String cont = "y";

        ListOfExercises loe = new ListOfExercises();


        while (cont.equals("y")) {
            System.out.println("What exercise would you like to add?");
            String workout = input.next();
            System.out.println("How many reps?");
            int reps = Integer.parseInt(input.next());
            System.out.println("How many sets?");
            int sets = Integer.parseInt(input.next());
            System.out.println("What weight did you use? (lbs)");
            int weight = Integer.parseInt(input.next());

            loe.addExercise(new Exercise(workout, reps, sets, weight));

            System.out.println("Would you like to add another exercise? (y or n)");
            cont = input.next();
            cont = cont.toLowerCase();

        }
        return loe;
    }

    private void viewLog() {

        if (logs.getLength() == 0) {
            System.out.println("There is no progress to be shown!");
        }

        for (int i = 0; i < logs.getLength(); i++) {
            System.out.printf("index: [%d]\n", i + 1);
            viewDay(logs.getLog(i));
        }

    }

    private void viewDay(Log log) {

        ListOfExercises loe = log.getExercises();

        System.out.println("----------------------------------");
        System.out.printf("Day: %s\n", log.getDate());
        System.out.printf("Type: %s\n", log.getType());
        System.out.println("----------------------------------");
        printWorkouts(loe);


    }

    private void printWorkouts(ListOfExercises loe) {

        for (int i = 0; i < loe.getLength(); i++) {
            Exercise exerciseSelected = loe.getExercise(i);

            System.out.printf("Exercise: %s,", exerciseSelected.getName());
            System.out.printf(" %d Sets of", exerciseSelected.getSets());
            System.out.printf(" %d Reps", exerciseSelected.getReps());
            System.out.printf(" @ %d lbs\n", exerciseSelected.getWeight());

        }

        System.out.println("----------------------------------");

    }

    private void removeDay() {

        if (logs.getLength() == 0) {
            System.out.println("There are no available days to remove!");
        }

        viewLog();
        System.out.println("Which day would you like to remove? Specify by the index number on the top.\n");
        int index = Integer.parseInt(input.next());

        logs.removeLog(index - 1);

        viewLog();

        System.out.printf("index %d has been successfully removed!", index);
    }

    private void viewProgress() {
        if (logs.getLength() == 0) {
            System.out.println("There are no available exercises to track!");
        }
        System.out.println("What exercise would you like to track? (Case and Space sensitive)");
        String select = input.next();

        ListOfExercises filtered = logs.trackProgress(select);

        System.out.printf("%s Progress! Keep it up!\n", select);
        System.out.println("----------------------------------\n");
        printWorkouts(filtered);

    }








}
