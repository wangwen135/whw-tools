/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年9月5日 Created
 */
package com.xingren.test.swing.t2;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    BusyPanel glassPanel = new BusyPanel();
    private JTextField textField;

    public MainFrame() {

        setGlassPane(glassPanel);
        
        setBounds(100, 100, 564, 394);
        
        getContentPane().setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(101, 85, 66, 21);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(155, 136, 93, 23);
        getContentPane().add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(308, 221, 54, 15);
        getContentPane().add(lblNewLabel);
        
        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(10, 217, 93, 23);
        getContentPane().add(btnNewButton_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(386, 59, 32, 21);
        getContentPane().add(comboBox);
        
        JButton btnNewButton_2 = new JButton("New button");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusy(true);
            }
        });
        btnNewButton_2.setBounds(178, 10, 93, 23);
        getContentPane().add(btnNewButton_2);
        glassPanel.start();
        glassPanel.setVisible(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setBusy(boolean busy) {
        if (busy) {
            glassPanel.stop();
            glassPanel.start();
            glassPanel.setVisible(true);
        } else {
            glassPanel.stop();
            glassPanel.setVisible(false);
        }
    }
}