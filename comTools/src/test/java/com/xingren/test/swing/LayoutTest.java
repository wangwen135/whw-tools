/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月19日 Created
 */
package com.xingren.test.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月19日 下午1:39:25
 *
 */
public class LayoutTest extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LayoutTest frame = new LayoutTest();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LayoutTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 574, 424);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0 };
        gbl_panel.columnWidths = new int[] { 100, 100, 50, 0, 0 };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 0, 5);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);

        JLabel lblNewLabel_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 0;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.insets = new Insets(0, 0, 0, 5);
        gbc_textField_1.weightx = 1;
        gbc_textField_1.gridx = 3;
        gbc_textField_1.gridy = 0;
        panel.add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("New button");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 0;
        panel.add(btnNewButton, gbc_btnNewButton);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.WEST);

        JButton button = new JButton("全屏");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FullScreenViewer fsv = new FullScreenViewer(contentPane);
                fsv.showFullScreenTwo(LayoutTest.this);
            }
        });
        panel_1.add(button);

        JButton button_1 = new JButton("退出");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUndecorated(false);
            }
        });
        panel_1.add(button_1);
        
        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(null);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
        chckbxNewCheckBox.setBounds(24, 21, 103, 23);
        panel_2.add(chckbxNewCheckBox);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
        rdbtnNewRadioButton.setBounds(159, 21, 121, 23);
        panel_2.add(rdbtnNewRadioButton);
        
        JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
        tglbtnNewToggleButton.setVerticalAlignment(SwingConstants.TOP);
        tglbtnNewToggleButton.setSelected(true);
        tglbtnNewToggleButton.setBounds(10, 102, 135, 23);
        panel_2.add(tglbtnNewToggleButton);
    }
}
