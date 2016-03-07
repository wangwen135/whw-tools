package com.wwh.whwtools.test.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * @author gloomy fish
 * @date 2011-03-03
 */
public class FullScreenViewer {
    private Dimension size;
    private JPanel sourcePanel;
    private JPanel buttonsPanel;
    private JButton exitBtn;

    /**
     * 
     display any JComponent as full screen, need to pass in root Container
     * 
     * invoke swing graphic utility tool, it's a bug!! when double buffer image
     * JFreechart * @param JFrame rootComponent
     */
    public void showFullScreenOne(Frame rootComponent) {
        final JDialog dialog = new JDialog(rootComponent, true);
        dialog.getContentPane().setLayout(new BorderLayout());
        final Container parent = sourcePanel.getParent();
        dialog.getContentPane().add(sourcePanel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        dialog.setUndecorated(true);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            try {
                gd.setFullScreenWindow(dialog);
            } finally {
                gd.setFullScreenWindow(null);
                dialog.dispose();
                if (parent != null) {
                    sourcePanel.setSize(size);
                    parent.add(sourcePanel);
                }
            }
        }
    }

    /**
     * * it's a bug!! when double buffer image JFreechart
     * 
     * @param owner
     */
    public void showFullScreenTwo(Frame owner) {
        final JDialog dialog = new JDialog(owner, true);
        dialog.getContentPane().setLayout(new BorderLayout());
        final Container parent = sourcePanel.getParent();
        dialog.getContentPane().add(sourcePanel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
                if (parent != null) {
                    sourcePanel.setSize(size);
                    parent.add(sourcePanel);
                }
            }
        });
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        dialog.setUndecorated(true);
        dialog.setSize(xSize, ySize);
        dialog.setResizable(false);
        dialog.setVisible(true);
        dialog.toFront();
    }

    public FullScreenViewer(JPanel sourcePanel) {
        this.sourcePanel = sourcePanel;
        initComponents();
    }

    private void initComponents() {
        exitBtn = new JButton("Exit");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(exitBtn);
        size = new Dimension(sourcePanel.getSize().width, sourcePanel.getSize().height);
    }
}