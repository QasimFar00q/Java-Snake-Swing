package org.example;
import org.example.GUIs.SnakeGUI;

import javax.swing.*;
import java.awt.*;

public class Main {



    public static void main(String[] args) {


        System.out.println("Program Initialised");

        JFrame frame = new JFrame("Game");
        frame.setVisible(true);
        frame.setSize(610,610);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGUI game = new SnakeGUI(625,625);
        frame.add(game);
        frame.pack();
        frame.requestFocus();

    }
}



