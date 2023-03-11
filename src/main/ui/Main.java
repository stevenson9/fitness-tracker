package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new GymApp();
        } catch (FileNotFoundException e) {
            System.out.println("File has not been found: Unable to run app");
        }
    }
}
