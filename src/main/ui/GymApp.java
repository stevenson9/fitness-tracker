package ui;

import model.Exercise;
import model.ListOfExercises;
import model.ListOfLogs;
import model.Log;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Gym Tracker Application
public class GymApp extends JPanel {

    private ListOfLogs logs;
    private Log log;
    private ListOfExercises listOfExercises = new ListOfExercises();

    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/tracker.json";

    private JFrame frame;
    private JPanel optionPanel;
    private JPanel logPanel;
    private JPanel loePanel;
    private JPanel exercisePanel;
    private JPanel mainPanel;

    private JList list;

    private DefaultListModel listModel;
    private AddListener addListener;

    private JTextField date;
    private JTextField type;
    private JTextField name;
    private JTextField reps;
    private JTextField sets;
    private JTextField weight;


    // EFFECTS: runs the gym tracker application
    public GymApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        init();
        initializeGUI();

    }

    // EFFECTS: updates the GUI after a new change
    private void updateGui() {
        frame.revalidate();
        frame.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the whole GUI, including frame and buttons
    public void initializeGUI() {

        setFrame();

        optionPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        dayButtons();

        saveLoad();

        mainPanel.add(optionPanel);

        initializeLogPanel();

        loePanel = new JPanel();
        listModel = new DefaultListModel<>();

        list = new JList(listModel);

        loePanel.add(list);
        listModel.addElement("EXAMPLE: " + "Date: " +  log.getDate() + " Type: " + log.getType() + " | Exercise: "
                + log.getExercises().getExercise(0).getName() + " "
                + log.getExercises().getExercise(0).getReps() + "x"
                + log.getExercises().getExercise(0).getSets() + " @ "
                + log.getExercises().getExercise(0).getWeight() + "lbs");


        mainPanel.add(loePanel);
    }

    // EFFECTS: Responsible for creating the Add/Remove Day Buttons
    private void dayButtons() {
        JButton addDay = new JButton("Add Day");
        addListener = new AddListener(addDay);
        addDay.setActionCommand("Add Day");
        addDay.addActionListener(addListener);

        optionPanel.add(addDay);

        JButton removeDay = new JButton("Remove Day");
        removeDay.setActionCommand("Remove Day");
        removeDay.addActionListener(new RemoveListener());


        optionPanel.add(removeDay);
    }

    // MODIFIES: this
    // EFFECTS: Responsible for creating the Save/Load Buttons
    private void saveLoad() {
        JButton saveTracker = new JButton("Save Tracker");
        saveTracker.addActionListener(e -> saveTracker());
        optionPanel.add(saveTracker);

        JButton loadTracker = new JButton("Load Tracker");
        loadTracker.addActionListener(e -> loadTracker());
        optionPanel.add(loadTracker);
    }

    // MODIFIES: this
    // EFFECTS: Responsible for creating the overall frame of the GUI
    private void setFrame() {
        frame = new JFrame("Gym Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JLabel background = new JLabel(new ImageIcon("./image/background.png"));
        background.setLayout(new FlowLayout());

        frame.setContentPane(background);

        frame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        frame.add(mainPanel);



    }


    // MODIFIES: this
    // Initializes the Panel that contains the logs
    public void initializeLogPanel() {
        logPanel = new JPanel();

        date = new JTextField(6);
        date.addActionListener(addListener);
        date.getDocument().addDocumentListener(addListener);

        type = new JTextField(5);
        type.addActionListener(addListener);
        type.getDocument().addDocumentListener(addListener);

        logPanel.add(date);
        logPanel.add(type);


        exercisePanel = new JPanel();

        exerciseField();


        mainPanel.add(logPanel);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the text fields that users can input their exercise details
    private void exerciseField() {
        name = new JTextField(8);
        name.addActionListener(addListener);
        name.getDocument().addDocumentListener(addListener);
        exercisePanel.add(name);

        reps = new JTextField(4);
        reps.addActionListener(addListener);
        reps.getDocument().addDocumentListener(addListener);
        exercisePanel.add(reps);

        sets = new JTextField(4);
        sets.addActionListener(addListener);
        sets.getDocument().addDocumentListener(addListener);
        exercisePanel.add(sets);

        weight = new JTextField(6);
        weight.addActionListener(addListener);
        weight.getDocument().addDocumentListener(addListener);
        exercisePanel.add(weight);

        logPanel.add(exercisePanel);
    }

    // MODIFIES: this
    // EFFECTS: Actions for when the remove button is pressed.
    class RemoveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            logs.removeLog(index);

        }



    }

    // MODIFIES: this
    // EFFECTS: Actions for when the Add button is pressed.
    class AddListener implements ActionListener, DocumentListener {

        private boolean alreadyEnabled = true;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String time = date.getText();
            String day = type.getText();
            String exercise = name.getText();
            int rep = Integer.parseInt(reps.getText());
            int set = Integer.parseInt(sets.getText());
            int heavy = Integer.parseInt(weight.getText());

            ListOfExercises loe = new ListOfExercises();

            loe.addExercise(new Exercise(exercise, rep, set, heavy));

            Log log = new Log(time, day, loe);



            logs.addLog(log);

            printLog(log);

            resetFields();

            updateGui();



        }


        private void resetFields() {
            date.requestFocusInWindow();
            date.setText("");
            type.requestFocusInWindow();
            type.setText("");
            name.requestFocusInWindow();
            name.setText("");
            reps.requestFocusInWindow();
            reps.setText("");
            sets.requestFocusInWindow();
            sets.setText("");
            weight.requestFocusInWindow();
            weight.setText("");
        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    // MODIFIES: this
    // EFFECTS: Adds the new log onto the panel which contains all the log information
    public void printLog(Log log) {
        listModel.addElement("Date: " +  log.getDate() + " Type: " + log.getType() + " | Exercise: "
                + log.getExercises().getExercise(0).getName() + " "
                + log.getExercises().getExercise(0).getReps() + "x"
                + log.getExercises().getExercise(0).getSets() + " @ "
                + log.getExercises().getExercise(0).getWeight() + "lbs");
    }



    // MODIFIES: this
    // EFFECTS: initializes the list of logs
    private void init() {
        logs = new ListOfLogs();
        listOfExercises.addExercise(new Exercise("Bench Press", 5, 5, 145));
        log = new Log("02/25/23", "Push", listOfExercises);
        logs.addLog(log);





    }


    // MODIFIES: this
    // EFFECTS: adds a new day entry to tracker
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

    // EFFECTS: allows the user to pick between 3 workout types, push, pull, or legs
    //          - if user does not pick one of the 3, prompts the user again
    private String pickTypeOfWorkout() {
        String type = "";

        while (!(type.equals("push") || type.equals("pull") || type.equals("legs"))) {
            System.out.println("What kind of workout would you like to add? (Push/Pull/Legs)");
            type = input.next();
            type = type.toLowerCase();
        }
        return type;
    }


    // MODIFIES: this
    // EFFECTS: allows the user to construct a new exercise and add it to a list of exercises
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

    // EFFECTS: allows the user to see all entries of the gym tracker app
    private void viewLog() {

        if (logs.getLength() == 0) {
            System.out.println("There is no progress to be shown!");
        }

        for (int i = 0; i < logs.getLength(); i++) {
            System.out.printf("index: [%d]\n", i + 1);
            viewDay(logs.getLog(i));
        }
    }

    // EFFECTS: allows the user to view a certain entry day, given the log
    private void viewDay(Log log) {
        ListOfExercises loe = log.getExercises();

        System.out.println("----------------------------------");
        System.out.printf("Day: %s\n", log.getDate());
        System.out.printf("Type: %s\n", log.getType());
        System.out.println("----------------------------------");

        printWorkouts(loe);
    }

    // EFFECTS: iterates through the list of exercises and shows the user information on
    //          all exercises inside the given list of exercises
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

    // MODIFIES: this
    // EFFECTS: removes an entry decided by the user
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

    // EFFECTS: allows the user to view progress of an exercise they choose
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

    // EFFECTS: saves the listoflogs to a file
    private void saveTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(logs);
            jsonWriter.close();
            System.out.println("Saved all days in tracker to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listoflogs from a file
    private void loadTracker() {
        try {
            logs = jsonReader.read();
            System.out.println("Loaded your previous days from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        listModel.removeAllElements();
        for (Log log : logs.returnLogs()) {
            printLog(log);
        }

        updateGui();


    }








}
