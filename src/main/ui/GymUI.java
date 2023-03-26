package ui;

import javax.swing.*;
import java.awt.*;

public class GymUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public GymUI() {
        super("Gym Tracker");

        initializeFrame();

        JButton addLog = new JButton("Add Log");
        JButton removeLog = new JButton("Delete Log");





    }

    private void initializeFrame() {

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new GymUI();
    }



}
