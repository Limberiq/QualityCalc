package com.example.qualityjava;

import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomSpiderWebPlot extends SpiderWebPlot {

    public CustomSpiderWebPlot(CategoryDataset dataset) {
        super(dataset);
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        // Вызов метода draw родительского класса для отрисовки графика
        super.draw(g2, area, anchor, parentState, info);

        // Рисуем окружности и метки
        drawValueLabels(g2, area);
        drawCircles(g2, area);
    }

    private void drawValueLabels(Graphics2D g2, Rectangle2D area) {
        double maxValue = 1.0; // Устанавливаем максимальное значение
        double[] levels = {0.2, 0.4, 0.6, 0.8, maxValue}; // Окружности для уровней

        // Настройка шрифта и цвета для меток
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        g2.setColor(Color.BLACK);

        // Рисуем метки только в верхней части окружности
        for (double level : levels) {
            double radius = level * (area.getWidth() / 2 - 80); // Учитываем отступ, чтобы окружности не выходили за пределы
            // Находим координаты для текста на верхней окружности
            double x = area.getCenterX();
            double y = area.getCenterY() - radius;

            // Рисуем метку
            g2.drawString(String.valueOf(level), (float) x, (float) y);
        }
    }

    private void drawCircles(Graphics2D g2, Rectangle2D area) {
        double maxValue = 1.0; // Устанавливаем максимальное значение
        double[] levels = {0.2, 0.4, 0.6, 0.8, maxValue}; // Окружности для уровней

        // Рисуем окружности
        g2.setColor(Color.LIGHT_GRAY); // Цвет окружностей
        for (double level : levels) {
            double radius = level * (area.getWidth() / 2 - 80); // Учитываем отступ, чтобы окружности не выходили за пределы
            g2.drawOval((int) (area.getCenterX() - radius), (int) (area.getCenterY() - radius),
                    (int) (2 * radius), (int) (2 * radius)); // Рисуем окружность
        }
    }
}
