package ru.vsu.cs.kg.project1.elements;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * класс для рисования гор, x, y координаты правого верхнего угла
 */
public class Mountains extends Element {
    private int[] x1;
    private int[] y1;

    private int[] x2;
    private int[] y2;

    private int[] x3;
    private int[] y3;
    private Template1 template1;
    private Template2 template2;
    private Template3 template3;

    private LinearGradientPaint gr1;
    private LinearGradientPaint gr2;
    private LinearGradientPaint gr3;

    private List<Integer> templates = new ArrayList<Integer>();
    private int curTemplate;

    public Mountains(int width, int height) {
        super(width, height);
        template1 = new Template1(width / 3, height);
        template2 = new Template2(width / 3, height);
        template3 = new Template3(width / 3, height);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                templates.add((int) (Math.random() * 3 + 1));
            }
        }
    }

    private class Template1 extends Element {
        public Template1(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            Color reset = g2d.getColor();
            g2d.setPaint(gr2);
            g2d.fillPolygon(x2, y2, 3);
            g2d.setPaint(gr1);
            g2d.fillPolygon(x1, y1, 3);
            g2d.setPaint(gr3);
            g2d.fillPolygon(x3, y3, 3);
            g2d.setColor(reset);


        }
    }

    private class Template2 extends Element {
        public Template2(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            Color reset = g2d.getColor();
            g2d.setPaint(gr3);
            g2d.fillPolygon(x3, y3, 3);
            g2d.setPaint(gr2);
            g2d.fillPolygon(x2, y2, 3);
            g2d.setPaint(gr1);
            g2d.fillPolygon(x1, y1, 3);
            g2d.setColor(reset);
            g2d.setPaint(null);
        }
    }

    private class Template3 extends Element {
        public Template3(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            Color reset = g2d.getColor();
            g2d.setPaint(gr1);
            g2d.fillPolygon(x1, y1, 3);
            g2d.setPaint(gr2);
            g2d.fillPolygon(x2, y2, 3);
            g2d.setPaint(gr3);
            g2d.fillPolygon(x3, y3, 3);
            g2d.setColor(reset);
            g2d.setPaint(null);

        }
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        int defaultX = x;
        int defaultY = y;
        for (int i = 1; i <= 3; i++) {
            updateGradients(x, y);
            updatePolygons(1, x, y);
            x += template1.width / 4;
            updatePolygons(2, x, y);
            x += template1.width / 4;
            updatePolygons(3, x, y);
            x += template1.width / 4;
            switch (templates.get(curTemplate % templates.size())) {
                case 1:
                    template1.draw(g2d, defaultX, defaultY);
                    break;
                case 2:
                    template2.draw(g2d, defaultX, defaultY);
                    break;
                case 3:
                    template3.draw(g2d, defaultX, defaultY);
                    break;
            }
            curTemplate++;
        }
    }

    private void updatePolygons(int n, int x, int y) {
        if (n > 3 || n < 1) {
            throw new IndexOutOfBoundsException();
        }
        int[] xn = new int[]{x, x + template1.width / 4, x + template1.width / 2};
        int[] yn = new int[]{y + template1.height, y, y + template1.height};
        switch (n) {
            case 1:
                x1 = xn;
                y1 = yn;
                break;
            case 2:
                x2 = xn;
                y2 = yn;
                break;
            case 3:
                x3 = xn;
                y3 = yn;
                break;
        }

    }

    private void updateGradients(int x, int y) {
        Point start = new Point(x, y + height);
        Point finish = new Point(x, y);

        float fractions1[] = {0.68f, 1.0f};
        Color colors1[] = {
                new Color(62, 55, 49),
                new Color(135, 111, 95),
        };

        float fractions2[] = {0.65f, 1.0f};
        Color colors2[] = {
                new Color(51, 37, 33),
                new Color(135, 121, 116),
        };

        float fractions3[] = {0.7f, 1.0f};
        Color colors3[] = {
                new Color(47, 44, 42),
                new Color(140, 124, 118),

        };
        gr1 = new LinearGradientPaint(start, finish, fractions1, colors1);
        gr2 = new LinearGradientPaint(start, finish, fractions2, colors2);
        gr3 = new LinearGradientPaint(start, finish, fractions3, colors3);
    }
}
