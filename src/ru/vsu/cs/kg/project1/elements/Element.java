package ru.vsu.cs.kg.project1.elements;

import java.awt.*;

abstract class Element {
    protected int width;
    protected int height;

    public Element(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g2d, int x, int y);
}
