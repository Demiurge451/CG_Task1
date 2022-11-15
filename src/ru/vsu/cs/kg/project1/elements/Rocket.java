package ru.vsu.cs.kg.project1.elements;

import java.awt.*;
import java.awt.geom.Point2D;

public class Rocket extends Element {
    private Nozzle nozzle;
    private Block1 block1;
    private Block2 block2;
    private Block3 block3;
    private Fire fire;
    private Platform platform;

    public Rocket(int width, int height) {
        super(width, height);
        nozzle = new Nozzle(width, height / 50);
        block1 = new Block1(width, (int) (height * 0.6));
        block2 = new Block2(width, (int) (height * 0.5));
        block3 = new Block3(width * 3, height);
        fire = new Fire(nozzle.width, (int) (block1.height / 1.5));
        platform = new Platform(block3.width * 5, (int) (fire.height * 1.25));
    }

    /**
     * класс отвечащий за отрисовку сопла, x координата центра, y координата верхней точки.
     */
    private class Nozzle extends Element {
        private LinearGradientPaint gradient;

        public Nozzle(int width, int height) {
            super(width, height);
        }


        public void draw(Graphics2D g2d, int x, int y) {
            int[] x1 = {(int) (x - 0.5 * width), (int) (x - 0.375 * width),
                    (int) (x + 0.375 * width), (int) (x + 0.5 * width)};
            int[] y1 = {y + height, y, y, y + height};
            createGradient(x, y);
            g2d.setPaint(gradient);
            g2d.fillPolygon(x1, y1, 4);
        }

        private void createGradient(int x, int y) {
            float fractions[] = {0.1f, 1f};
            Color colors[] = {
                    new Color(27, 24, 24),
                    new Color(157, 147, 147),
            };
            Point2D start = new Point2D.Float(x, y);
            Point2D end = new Point2D.Float(x, y + height);
            gradient = new LinearGradientPaint(start, end, fractions, colors);
        }
    }

    /**
     * класс отвечающий за отрисвоку верхнего блока ракеты, x координата центра, y координата верхней точки.
     */
    private class Block1 extends Element {
        private LinearGradientPaint gradient1;
        private LinearGradientPaint gradient2;

        public Block1(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            int blockWithoutNozzlesHeight = height - 3 * nozzle.height;
            createGradient2(x, y, (int) (0.375 * width + 0.375 * width));
            g2d.setPaint(gradient2);
            int[] xPolygon1 = {x, (int) (x - 0.375 * width), (int) (x + 0.375 * width)};
            int[] yPolygon1 = {y, y + blockWithoutNozzlesHeight / 4, y + blockWithoutNozzlesHeight / 4};
            g2d.fillPolygon(xPolygon1, yPolygon1, 3);

            createGradient(x, y + blockWithoutNozzlesHeight / 4, blockWithoutNozzlesHeight / 2 - blockWithoutNozzlesHeight / 4);
            g2d.setPaint(gradient1);
            g2d.fillRect((int) (x - 0.375 * width), y + blockWithoutNozzlesHeight / 4, (int) (0.375 * width + 0.375 * width), blockWithoutNozzlesHeight / 2 - blockWithoutNozzlesHeight / 4);


            createGradient(x, y + blockWithoutNozzlesHeight / 2, blockWithoutNozzlesHeight / 2);
            g2d.setPaint(gradient1);
            g2d.fillRect(x - width / 2, y + blockWithoutNozzlesHeight / 2 + nozzle.height, width, blockWithoutNozzlesHeight / 2);

            nozzle.draw(g2d, x, y + blockWithoutNozzlesHeight / 2);
            g2d.rotate(Math.toRadians(180), x, y + height - 2 * nozzle.height + nozzle.height / 2);
            nozzle.draw(g2d, x, y + height - 2 * nozzle.height);
            g2d.rotate(Math.toRadians(180), x, y + height - 2 * nozzle.height + nozzle.height / 2);
            nozzle.draw(g2d, x, y + height - 2 * nozzle.height + nozzle.height);
        }


        private void createGradient(int x, int y, int height) {
            Point2D start = new Point2D.Float(x, y);
            Point2D end = new Point2D.Float(x, y + height);
            float fractions[] = {0.0f, 0.45f, 1.0f};
            Color colors[] = {
                    new Color(255, 254, 254),
                    new Color(187, 173, 173),
                    new Color(130, 117, 117),
            };
            gradient1 = new LinearGradientPaint(start, end, fractions, colors);
        }

        private void createGradient2(int x, int y, int width) {
            Point2D start = new Point2D.Float(x - width / 2, y);
            Point2D end = new Point2D.Float(x + width / 2, y);
            float fractions[] = {0.05f, 0.5f, 0.95f};
            Color colors[] = {
                    new Color(84, 81, 81),
                    new Color(225, 207, 207),
                    new Color(84, 81, 81),
            };
            gradient2 = new LinearGradientPaint(start, end, fractions, colors);
        }
    }

    /**
     * класс отвечающий за отрисовку среднего блока ракеты
     */
    private class Block2 extends Element {
        private LinearGradientPaint gradient;

        public Block2(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            createGradient(x, y);
            g2d.setPaint(gradient);
            int rectHeight = height - 2 * nozzle.height;
            g2d.fillRect((int) (x - 0.5 * width), y, width, rectHeight);

            g2d.rotate(Math.toRadians(180), x, y + rectHeight + nozzle.height / 2);
            nozzle.draw(g2d, x, y + rectHeight);
            g2d.rotate(Math.toRadians(180), x, y + rectHeight + nozzle.height / 2);
            nozzle.draw(g2d, x, y + rectHeight + nozzle.height);
        }

        private void createGradient(int x, int y) {
            Point2D start = new Point2D.Float(x, y);
            Point2D end = new Point2D.Float(x, y + height);
            float fractions[] = {0.0f, 0.45f, 1.0f};
            Color colors[] = {
                    new Color(255, 254, 254),
                    new Color(187, 173, 173),
                    new Color(130, 117, 117),
            };
            gradient = new LinearGradientPaint(start, end, fractions, colors);
        }
    }

    /**
     * класс отвечащий за отрисовку нижнего блока ракеты, x координата центра, y координата верхней точки.
     */
    private class Block3 extends Element {
        private LinearGradientPaint gradient1;
        private LinearGradientPaint gradient2;

        public Block3(int motorsWidth, int motorsHeight) {
            super(motorsWidth, motorsHeight);
        }

        @Override
        public void draw(Graphics2D g2d, int x, int y) {
            int motorsHeight = height - nozzle.height;
            int motorsWidth = width / 3;

            createGradient(x, y, motorsHeight / 2);
            g2d.setPaint(gradient2);
            g2d.fillRect((int) (x - 0.5 * motorsWidth), y, motorsWidth, motorsHeight / 2);

            g2d.fillArc((int) (x - 1.5 * motorsWidth), y, motorsWidth * 2, motorsHeight, 180, -90);
            g2d.fillArc((int) (x - 0.5 * motorsWidth), y, motorsWidth, motorsHeight, 0, 180);
            g2d.fillArc((int) (x - 0.5 * motorsWidth), y, motorsWidth * 2, motorsHeight, 0, 90);

            g2d.setPaint(new Color(72, 72, 69));
            g2d.drawArc((int) (x - 0.5 * motorsWidth), y, motorsWidth, motorsHeight, 0, 180);
            g2d.drawLine((int) (x - 1.5 * motorsWidth), y + motorsHeight / 2, (int) (x + 1.5 * motorsWidth), y + motorsHeight / 2);
            g2d.drawLine((int) (x - 0.5 * motorsWidth), y, (int) (x + 0.5 * motorsWidth), y);

            nozzle.draw(g2d, x - motorsWidth, (int) (y + 0.5 * motorsHeight));
            nozzle.draw(g2d, x, (int) (y + 0.5 * motorsHeight));
            nozzle.draw(g2d, x + motorsWidth, (int) (y + 0.5 * motorsHeight));

            fire.draw(g2d, x - motorsWidth, (int) (y + 0.5 * motorsHeight + nozzle.height));
            Fire bigFire = new Fire(fire.width, (int) (fire.height * 1.25));
            fire.draw(g2d, x + motorsWidth, (int) (y + 0.5 * motorsHeight + nozzle.height));
            bigFire.draw(g2d, x, (int) (y + 0.5 * motorsHeight + nozzle.height));

        }

        private void createGradient(int x, int y, int height) {
            Point2D start = new Point2D.Float(x, y);
            Point2D end = new Point2D.Float(x, y + height);
            float fractions[] = {0.0f, 0.45f, 1.0f};
            Color colors[] = {
                    new Color(255, 254, 254),
                    new Color(187, 173, 173),
                    new Color(130, 117, 117),
            };
            gradient2 = new LinearGradientPaint(start, end, fractions, colors);
        }
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        y -= (block3.height - nozzle.height) / 2;
        block3.draw(g2d, x, y - nozzle.height);
        block2.draw(g2d, x, y - block2.height - nozzle.height);
        block1.draw(g2d, x, y - block2.height - block1.height - nozzle.height);
    }

    public void drawPlatform(Graphics2D g2d, int x, int y) {
        platform.draw(g2d, x, y);
    }
}
