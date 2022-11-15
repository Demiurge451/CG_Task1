package ru.vsu.cs.kg.project1;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel drawPanel;

    public MainWindow() throws HeadlessException {
        drawPanel = new DrawPanel();
        this.add(drawPanel.getGui());
        this.pack();
    }


}
