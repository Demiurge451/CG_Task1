package ru.vsu.cs.kg.project1.elements;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class SupportMasts extends Element{
    private BottomPart bottomPart;
    private Mast mast;
    private int xRocket;
    private int degree;
    private int fixedDegree = 0;


    public SupportMasts(int width, int height, int xRocket, int degree) {
        super(width, height);
        this.bottomPart = new BottomPart(width, height / 9);
        this.mast = new Mast((int) (width * 0.3), height - height / 9);
        this.xRocket = xRocket;
        this.degree = degree;
    }

    private class BottomPart extends Element{
        private LinearGradientPaint gradient;
        public BottomPart(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            createGradient(x, y - height);
            g2d.setPaint(gradient);
            g2d.fillRect((int) (x - 0.5 * width), y - height, width, height);
        }

        private void createGradient(int x, int y) {
            float fractions[] = {0.1f, 1f};
            Color colors[] = {
                    new Color(89, 85, 85),
                    new Color(51, 48, 48),

            };
            Point2D start = new Point2D.Float(x, y);
            Point2D end = new Point2D.Float(x, y + height);
            gradient = new LinearGradientPaint(start, end, fractions, colors);
        }
    }

    private class Mast extends Element {
        public Mast(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            g2d.setPaint(Color.BLACK);
            if (degree < -30 && fixedDegree == 0){
                fixedDegree = degree;
            }
            degree = fixedDegree == 0 ? degree : fixedDegree;
            g2d.rotate(Math.toRadians(-degree), x, y);
            g2d.drawLine(x - width / 2, y, x - width / 2, y - height);
            g2d.drawLine(x + width / 2, y, x + width / 2, y - height + width);
            g2d.drawLine(x + width / 2, y - height + width, x - width / 2, y - height);
            int ind = y;
            while (ind > y - height + 2 * width){
                g2d.drawLine(x - width / 2, ind, x + width / 2, ind - width);
                g2d.drawLine(x + width / 2, ind, x - width / 2, ind - width);
                ind -= width / 2;
            }
            int y1 = ind;
            while (y1 > y - height + width){
                g2d.drawLine(x + width / 2, y1, x - width / 2, y1 - width);
                y1 -= width / 2;
            }
            g2d.rotate(Math.toRadians(degree), x, y);
        }
    }



    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        mast.draw(g2d, x, (int) (y - bottomPart.height * 0.7));
        bottomPart.draw(g2d, x, y);
        AffineTransform reset = g2d.getTransform();
        g2d.translate(x, 0);
        g2d.scale(-1, 1);
        g2d.translate(-x + 2 * (x - xRocket), 0);
        mast.draw(g2d, x, (int) (y - bottomPart.height * 0.7));
        bottomPart.draw(g2d, x, y);
        g2d.setTransform(reset);
    }

    public void updateDegree() {
        degree--;
    }
}
