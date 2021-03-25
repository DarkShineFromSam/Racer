package ru.java2e;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Java Racer");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 900);
        jFrame.add(new Road());
        jFrame.setVisible(true);


    }
}
