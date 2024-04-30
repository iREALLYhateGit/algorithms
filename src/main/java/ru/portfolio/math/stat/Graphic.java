package ru.portfolio.math.stat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphic extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        int w = getWidth();
        int h = getHeight();

        double x_min = -5.0;
        double x_max = 5.0;
        double y_min = -5.0;
        double y_max = 25.0;

// Оси координат
        g2d.draw(new Line2D.Double(w / 2.0, 0, w / 2.0, h));
        g2d.draw(new Line2D.Double(0, h / 2.0, w, h / 2.0));

// Кривая
        g2d.setColor(Color.RED);
        for (int i = 0; i < w; i++) {
            double x = x_min + (x_max - x_min) / w * i;
            double y = MathStatAlgo.getY(x);
            double x0 = w / 2.0 + x / (x_max - x_min) * w;
            double y0 = h / 2.0 - y / (y_max - y_min) * h;
            g2d.draw(new Line2D.Double(x0, y0, x0, y0));
        }
    }

    public static void drawGraphic() {
        JFrame frame = new JFrame("Graph Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graphic panel = new Graphic();
        frame.getContentPane().add(panel);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
