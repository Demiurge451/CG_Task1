package ru.vsu.cs.kg.project1.elements;

import java.awt.*;
import java.awt.geom.Point2D;

public class Fire extends Element {
    private LinearGradientPaint gradient1;
    private LinearGradientPaint gradient2;
    public Fire(int width, int height) {
        super(width, height);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        createGradients(x, y);
        int curWidth = width;
        int curHeight = height;
        for (int i = 0; i < 2; i++) {
            if (i % 2 == 1) {
                g2d.setPaint(gradient2);
            } else {
                g2d.setPaint(gradient1);
            }

            int[] x1 = new int[]{x - curWidth / 2, x - curWidth / 2, x, x + curWidth / 2, x + curWidth / 2};
            int[] y1 = new int[]{y, y + curHeight - curHeight / 2, y + curHeight, y + curHeight - curHeight / 2, y};

            g2d.fillPolygon(x1, y1, 5);
            curWidth /= 1.3;
            curHeight /= 1.3;
        }
    }

    private void createGradients(int x, int y) {
        Point2D start = new Point2D.Float(x, y);
        Point2D end = new Point2D.Float(x, y + height);
        float fractions1[] = {0.35f, 1f};
        Color colors1[] = {
                new Color(176, 14, 14),
                new Color(255, 89, 12),
        };

        float fractions2[] = {0.2f, 1f};
        Color colors2[] = {
                new Color(255, 18, 18),
                new Color(255, 224, 22),
        };
        gradient1 = new LinearGradientPaint(start, end, fractions1, colors1);
        gradient2 = new LinearGradientPaint(start, end, fractions2, colors2);
    }
}
