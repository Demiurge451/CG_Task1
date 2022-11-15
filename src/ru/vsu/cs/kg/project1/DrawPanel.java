package ru.vsu.cs.kg.project1;

import ru.vsu.cs.kg.project1.elements.Mountains;
import ru.vsu.cs.kg.project1.elements.Rocket;
import ru.vsu.cs.kg.project1.elements.SupportMasts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class DrawPanel extends JPanel {
    final private int panel_width = 1200;
    final private int panel_height = 800;

    private int x;
    private int y;
    private int yVelocity = 1;
    private Timer timer;
    private Rocket rocket;
    private SupportMasts masts;
    private Mountains mountains;
    private double boost = 1.0;

    private JPanel gui = new JPanel(new GridBagLayout());

    public DrawPanel() {
        gui.setBackground(Color.black);
        gui.setSize(new Dimension(panel_width, panel_height));
        gui.add(this);

        x = this.getParent().getWidth() / 2;
        y = (int) (this.getParent().getHeight() * 0.75);
        rocket = new Rocket(panel_width / 30, panel_height / 4);
        masts = new SupportMasts(panel_width / 30 + panel_width / 60, (int) (panel_height / 4 * 0.925), x, 45);
        mountains = new Mountains(panel_width / 2, 60);

        timer = new Timer(10, e -> {
            y -= yVelocity * boost;
            masts.updateDegree();
            boost += 0.1;
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startAnimation();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(this.getWidth() / 1200.0, this.getHeight() / 800.0);
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        drawBackground(g2d);

        if (y < -1.2 * panel_height) {
            timer.stop();
        }

        mountains.draw(g2d, -panel_width / 40, panel_height / 12);
        mountains.draw(g2d, -panel_width / 40 - panel_width / 6 + panel_width / 2, panel_height / 12);
        mountains.draw(g2d, -panel_width / 40 - panel_width / 3 + 2 * panel_width / 2, panel_height / 12);

        rocket.draw(g2d, x, y);
        masts.draw(g2d, panel_width / 2 + panel_width / 10, (int) (panel_height * 0.75));
        rocket.drawPlatform(g2d, panel_width / 2, (int) (panel_height * 0.75));
    }

    public void drawBackground(Graphics2D g2d) {
        Point2D start = new Point2D.Float(0, panel_height);
        Point2D end = new Point2D.Float(0, 0);
        float fractions[] = {0.0f, 0.8f, 0.85f, 1.0f};
        Color colors[] = {
                new Color(115, 62, 17),
                new Color(46, 36, 30),
                new Color(24, 21, 21),
                new Color(69, 132, 173),

        };
        LinearGradientPaint gradient = new LinearGradientPaint(start, end, fractions, colors);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, panel_width, panel_height);
        g2d.setPaint(null);
    }

    private void startAnimation() {
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension dimension = this.getParent().getSize();
        Dimension fixedRatioSize;
        int height;
        int width;
        if ((double) dimension.width / dimension.height > 3.0 / 2) {
            height = dimension.height;
            width = (int) (dimension.height * (3.0 / 2));
        } else {
            width = dimension.width;
            height = (int) (dimension.width * (2.0 / 3));
        }
        fixedRatioSize = new Dimension(width, height);

        return new Dimension(fixedRatioSize);
    }

    public JPanel getGui() {
        return gui;
    }
}
