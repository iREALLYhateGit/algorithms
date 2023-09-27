package ru.portfolio;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class FlagPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Рисуем фон
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(60, 179, 113));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Рисуем дизайн флага
        g2d.setColor(new Color(255, 255, 255));
        int width = getWidth() / 8; // задаем размер элемента
        int height = getHeight() / 5;
        int space = 10;

        // Рисуем верхнюю часть флага
        Path2D.Double path = new Path2D.Double();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int x = i * width + (j % 2) * width / 2;
                int y = j * height / 2;
                if (j == 0) {
                    path.moveTo(x + space, y + height / 2);
                } else {
                    path.lineTo(x + space, y + height / 2);
                }
                path.lineTo(x + width / 2, y + space);
                path.lineTo(x + width - space, y + height / 2);
                path.lineTo(x + width / 2, y + height - space);
                path.closePath();
            }
        }
        g2d.fill(path);

        // Рисуем нижнюю часть флага
        g2d.setColor(new Color(0, 56, 147));
        g2d.fillRect(2 * width + space, height + space * 3, 4 * width - space * 2, 2 * height - space * 2);

        // Рисуем меч
        g2d.setColor(new Color(255, 255, 255));
        g2d.setStroke(new BasicStroke(3));
        Path2D.Double blade = new Path2D.Double();
        blade.moveTo(5 * width, 3.5 * height);
        blade.lineTo(6.5 * width, 2 * height);
        blade.lineTo(6 * width, 3 * height);
        blade.lineTo(6 * width, 2 * height);
        blade.lineTo(6 * width, 10 * height);
        blade.lineTo(6 * width, 2 * height);
        blade.lineTo(6.5 * width, 1.5 * height);
        blade.closePath();
        g2d.draw(blade);

        Path2D.Double hilt = new Path2D.Double();
        hilt.moveTo(5.5 * width, 1.5 * height);
        hilt.lineTo(5.5 * width, height + space);
        hilt.closePath();
        g2d.draw(hilt);

        Line2D.Double pommel = new Line2D.Double(6 * width, height + space, 5 * width, height + space + space);
        g2d.draw(pommel);
    }
}

public class Flag {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flag");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlagPanel panel = new FlagPanel();
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}