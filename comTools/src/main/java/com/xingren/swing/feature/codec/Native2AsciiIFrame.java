package com.xingren.swing.feature.codec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xingren.swing.frame.BaseJInternalFrame;
import com.xingren.utils.Native2AsciiUtils;

/**
 * <pre>
 * Hex编码
 * </pre>
 *
 * @author wwh
 * @date 2015年9月18日 上午14:29:59
 *
 */
public class Native2AsciiIFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(Native2AsciiIFrame.class);

    private static final long serialVersionUID = 1L;

    private JTextArea txtaNative;

    private JTextArea txtaAscii;

    public Native2AsciiIFrame() {
        super();
        setTitle("Native2Ascii");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(10, 10, 800, 450);
        setPreferredSize(new Dimension(800, 450));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 25, 20, 0, 20, 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 2.0 };
        panel.setLayout(gbl_panel);

        JLabel lblNative = new JLabel("Native");
        lblNative.setFont(new Font("宋体", Font.BOLD, 14));
        GridBagConstraints gbc_lblNative = new GridBagConstraints();
        gbc_lblNative.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNative.insets = new Insets(0, 0, 5, 5);
        gbc_lblNative.gridx = 0;
        gbc_lblNative.gridy = 0;
        panel.add(lblNative, gbc_lblNative);

        JLabel lblAscii = new JLabel("Ascii");
        lblAscii.setFont(new Font("宋体", Font.BOLD, 14));
        GridBagConstraints gbc_lblAscii = new GridBagConstraints();
        gbc_lblAscii.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblAscii.insets = new Insets(0, 0, 5, 0);
        gbc_lblAscii.gridx = 2;
        gbc_lblAscii.gridy = 0;
        panel.add(lblAscii, gbc_lblAscii);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 5;
        gbc_scrollPane.insets = new Insets(0, 1, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        panel.add(scrollPane, gbc_scrollPane);

        txtaNative = new JTextArea();
        scrollPane.setViewportView(txtaNative);

        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 1);
        gbc_scrollPane_1.gridheight = 5;
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 2;
        gbc_scrollPane_1.gridy = 1;
        panel.add(scrollPane_1, gbc_scrollPane_1);

        txtaAscii = new JTextArea();
        scrollPane_1.setViewportView(txtaAscii);

        JButton btnAscii = new JButton("Ascii ->");
        btnAscii.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String natext = txtaNative.getText();
                    String asctext = Native2AsciiUtils.native2Ascii(natext);
                    txtaAscii.setText(asctext);
                } catch (Exception ex) {
                    log.error("编码错误", ex);
                    JOptionPane.showMessageDialog(Native2AsciiIFrame.this, "编码错误：" + ex.getMessage());
                }

            }
        });
        GridBagConstraints gbc_btnAscii = new GridBagConstraints();
        gbc_btnAscii.insets = new Insets(0, 0, 5, 5);
        gbc_btnAscii.gridx = 1;
        gbc_btnAscii.gridy = 2;
        panel.add(btnAscii, gbc_btnAscii);

        JButton btnNative = new JButton("<- Native");
        btnNative.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String asctext = txtaAscii.getText();
                    String natext = Native2AsciiUtils.ascii2Native(asctext);
                    txtaNative.setText(natext);
                } catch (Exception ex) {
                    log.error("解码错误", ex);
                    JOptionPane.showMessageDialog(Native2AsciiIFrame.this, "解码错误：" + ex.getMessage());
                }
            }
        });
        GridBagConstraints gbc_btnNative = new GridBagConstraints();
        gbc_btnNative.insets = new Insets(0, 0, 5, 5);
        gbc_btnNative.gridx = 1;
        gbc_btnNative.gridy = 4;
        panel.add(btnNative, gbc_btnNative);

    }
}
