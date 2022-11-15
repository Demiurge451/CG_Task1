package ru.vsu.cs.kg.project1;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainWindow.setSize(1156, 800);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }
}
