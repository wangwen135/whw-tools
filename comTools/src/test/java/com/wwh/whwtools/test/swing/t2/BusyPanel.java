package com.wwh.whwtools.test.swing.t2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BusyPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final float PI = 3.1415f;
    private String info;
    private boolean stopped = true;
    private int step;
    private int radius;
    private int barWidth;
    private int barHeight;
    private Color defaultColor;
    private int startIndex = 0;
    private int delay = 300;
    private int darkBarCount = 5;
    private int fontSize = 40;
    private float alpha = 0.2f;

    public BusyPanel() {
        setOpaque(false);
        setStep(15);
        setRadius(150);
        setDefaultColor(new Color(100, 100, 100));
        setBarWidth((int) (radius * 0.6));
        setBarHeight(10);
        setDefaultColor(new Color(50, 50, 50));
        info = "Default Information";

        setFocusable(true);
        handleEvent();
    }

    public void run() {
        try {
            while (!stopped) {
                repaint();
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (!stopped) {
            return;
        }

        stopped = false;

        Thread thread = new Thread(this);
        thread.start();
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    public void stop() {
        stopped = true;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Set the string that will be shown in the panel.
     * 
     * @param str
     *            is will be shown.
     */
    public void setString(String str) {
        info = str;
    }

    public String getString() {
        return info;
    }

    public void setStep(int step) {
        if (step == this.step) {
            return;
        }
        if (step < 4) {
            step = 4;
        }

        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public int getRadius() {
        return radius;
    }

    public int getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(int barWidth) {
        if (barWidth == this.barWidth) {
            return;
        }

        if (barWidth > this.radius) {
            barWidth = this.radius;
        }

        this.barWidth = barWidth;
    }

    public int getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(int barHeight) {
        if (barHeight == this.barHeight) {
            return;
        }
        if (barHeight > 20) {
            barHeight = 20;
        }
        this.barHeight = barHeight;
    }

    public void setRadius(int radius) {
        if (radius == this.radius) {
            return;
        }
        if (radius < 10) {
            radius = 10;
        }

        this.radius = radius;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        if (delay == this.delay) {
            return;
        }

        if (delay < 100) {
            delay = 100;
        }

        this.delay = delay;
    }

    public int getDarkBarCount() {
        return darkBarCount;
    }

    public void setDarkBarCount(int darkBarCount) {
        if (darkBarCount == this.darkBarCount) {
            return;
        }

        if (darkBarCount < 3) {
            darkBarCount = 3;
        }

        this.darkBarCount = darkBarCount;
    }

    public void setDefaultColor(Color color) {
        if (color.equals(this.defaultColor)) {
            return;
        }
        this.defaultColor = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        if (fontSize == this.fontSize) {
            return;
        }

        if (fontSize < 8) {
            fontSize = 8;
        }
        this.fontSize = fontSize;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        if (alpha < 0.0f) {
            alpha = 0.0f;
        }

        if (alpha > 1.0f) {
            alpha = 1.0f;
        }

        this.alpha = alpha;
    }

    private void handleEvent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Toolkit.getDefaultToolkit().beep();
            }
        });
    }

    @Override
    public void setVisible(boolean v) {
        if (v) {
            requestFocus();
        }

        super.setVisible(v);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int centerx = width / 2;
        int centery = height / 2;

        // Draw the information string.
        Font font = new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), fontSize);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int stringWidth = metrics.stringWidth(info);
        int fontx = (width - stringWidth) / 2;
        int fonty = (height / 2 - radius) - 20;
        g2d.setColor(Color.RED);
        g2d.drawString(info, fontx, fonty);

        // Fill the semi-transparent background.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(g2d.getBackground().darker().darker().darker());
        g2d.fillRect(0, 0, width, height);

        // Draw scrolling bars.
        g2d.translate(centerx, centery);

        int startx = radius - barWidth;
        int starty = -(barHeight / 2);
        int arcWidth = barHeight;
        float deltaAngle = PI * 2 / (float) step;
        startIndex = (startIndex + 1) % step;

        g2d.setColor(defaultColor);
        for (int i = 0; i < step; ++i) {
            g2d.fillRoundRect(startx, starty, barWidth, barHeight, arcWidth, arcWidth);
            g2d.rotate(deltaAngle);
        }

        Color color = defaultColor;
        g2d.rotate(startIndex * deltaAngle);
        for (int i = 0; i < darkBarCount; ++i) {
            g2d.setColor(color);
            g2d.fillRoundRect(startx, starty, barWidth, barHeight, arcWidth, arcWidth);
            g2d.rotate(deltaAngle);

            color = color.darker();
        }

    }
}