package ru.vsu.cs.kg.project1.elements;

import java.awt.*;
import java.awt.geom.Point2D;

public class Platform extends Element{
    private LinearGradientPaint gradient;
    public Platform(int width, int height) {
        super(width, height);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        int[] x1 = {x - width / 2 + width / 6, x - width / 2, x + width / 2, x + width / 2 - width / 6};
        int[] y1 = {y, y + height, y + height, y};
        createGradient(x, y);
        g2d.setPaint(gradient);
        g2d.fillPolygon(x1, y1, 4);
    }

    private void createGradient(int x, int y) {
        float fractions[] = {0.5f, 1f};
        Color colors[] = {
                new Color(56, 52, 52),
                new Color(115, 62, 17),
        };
        Point2D start = new Point2D.Float(x, y);
        Point2D end = new Point2D.Float(x, y + height);
        gradient = new LinearGradientPaint(start, end, fractions, colors);
    }
}
