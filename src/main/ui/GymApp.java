package ui;

import model.ListOfLogs;

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

    private void optionMenu() {
        System.out.println("\nWelcome to the Gym Tracker App! Select from one of the options below:");
        System.out.println("\ta -> Add a new day");
        System.out.println("\tr -> Remove a past day");
        System.out.println("\tv -> View your progress!");
        System.out.println("\tq -> quit");

    }


    private void processOption(String command) {
        if (command.equals("a")) {
            addNewDay();
        } else if (command.equals("r")) {
            removeDay();
        } else if (command.equals("v")) {
            viewLog();
        } else {
            System.out.println("That is not a valid option, try again!");
        }

    }








}
