package com.wwh.whwtools.swing.feature.codec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;

/**
 * <pre>
 * Hex编码
 * </pre>
 *
 * @author wwh
 * @date 2015年8月8日 上午10:29:59
 *
 */
public class HexIFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(HexIFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextArea txta_input;
    private JCheckBox chkbx_base64;
    private JTextArea txta_output;
    private JComboBox<String> cmbbx_charset;

    public HexIFrame() {
        super();
        setTitle("Hex编码/解码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(20, 20, 600, 370);
        setPreferredSize(new Dimension(600, 370));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 100, 100, 120, 100, 100, 0 };
        gbl_panel.rowHeights = new int[] { 30, 105, 25, 25, 105, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("请输入要进行编码或解码的字符：");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        chkbx_base64 = new JCheckBox("内容为Base64");
        GridBagConstraints gbc_chkbx_base64 = new GridBagConstraints();
        gbc_chkbx_base64.anchor = GridBagConstraints.WEST;
        gbc_chkbx_base64.insets = new Insets(0, 0, 5, 5);
        gbc_chkbx_base64.gridx = 2;
        gbc_chkbx_base64.gridy = 0;
        panel.add(chkbx_base64, gbc_chkbx_base64);

        JLabel label = new JLabel("文本编码");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 3;
        gbc_label.gridy = 0;
        panel.add(label, gbc_label);

        cmbbx_charset = new JComboBox<String>();
        cmbbx_charset.setModel(new DefaultComboBoxModel<String>(new String[] { "UTF-8", "GBK", "GB2312" }));
        cmbbx_charset.setEditable(true);
        GridBagConstraints gbc_cmbbx_charset = new GridBagConstraints();
        gbc_cmbbx_charset.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbbx_charset.insets = new Insets(0, 0, 5, 0);
        gbc_cmbbx_charset.gridx = 4;
        gbc_cmbbx_charset.gridy = 0;
        panel.add(cmbbx_charset, gbc_cmbbx_charset);

        JButton btn_exchange = new JButton("交换↑↓");
        btn_exchange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = txta_input.getText();
                txta_input.setText(txta_output.getText());
                txta_output.setText(text);
            }
        });

        JButton btn_encode = new JButton("编码");
        btn_encode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = txta_input.getText();
                if (inputText == null || "".equals(inputText)) {
                    return;
                }
                try {
                    boolean is64 = chkbx_base64.isSelected();
                    Object c = cmbbx_charset.getSelectedItem();
                    String charString = c == null ? "UTF-8" : c.toString();

                    byte[] bytes;
                    if (is64) {
                        bytes = CodecUtils.base64Decode(inputText);
                    } else {
                        bytes = inputText.getBytes(charString);
                    }
                    txta_output.setText(CodecUtils.hexEncode(bytes));

                } catch (Exception ex) {
                    log.error("编码错误", ex);
                    JOptionPane.showMessageDialog(HexIFrame.this, "编码错误：" + ex.getMessage());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridwidth = 5;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        panel.add(scrollPane, gbc_scrollPane);

        txta_input = new JTextArea();
        scrollPane.setViewportView(txta_input);
        GridBagConstraints gbc_btn_encode = new GridBagConstraints();
        gbc_btn_encode.anchor = GridBagConstraints.NORTH;
        gbc_btn_encode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_encode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_encode.gridx = 0;
        gbc_btn_encode.gridy = 2;
        panel.add(btn_encode, gbc_btn_encode);

        JButton btn_decode = new JButton("解码");
        btn_decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String inputText = txta_input.getText();
                if (inputText == null || "".equals(inputText)) {
                    return;
                }
                try {
                    Object c = cmbbx_charset.getSelectedItem();
                    String charString = c == null ? "UTF-8" : c.toString();

                    byte[] bytes = CodecUtils.hexDecode(inputText);
                    txta_output.setText(new String(bytes, charString));
                } catch (Exception ex) {
                    log.error("解码错误", ex);
                    JOptionPane.showMessageDialog(HexIFrame.this, "解码错误：" + ex.getMessage());
                }

            }
        });
        GridBagConstraints gbc_btn_decode = new GridBagConstraints();
        gbc_btn_decode.anchor = GridBagConstraints.NORTH;
        gbc_btn_decode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_decode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_decode.gridx = 1;
        gbc_btn_decode.gridy = 2;
        panel.add(btn_decode, gbc_btn_decode);
        GridBagConstraints gbc_btn_exchange = new GridBagConstraints();
        gbc_btn_exchange.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_exchange.anchor = GridBagConstraints.NORTH;
        gbc_btn_exchange.insets = new Insets(0, 0, 5, 0);
        gbc_btn_exchange.gridx = 4;
        gbc_btn_exchange.gridy = 2;
        panel.add(btn_exchange, gbc_btn_exchange);

        JLabel lblBase = new JLabel("编码或解码结果：");
        GridBagConstraints gbc_lblBase = new GridBagConstraints();
        gbc_lblBase.anchor = GridBagConstraints.NORTH;
        gbc_lblBase.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblBase.insets = new Insets(0, 0, 5, 5);
        gbc_lblBase.gridwidth = 2;
        gbc_lblBase.gridx = 0;
        gbc_lblBase.gridy = 3;
        panel.add(lblBase, gbc_lblBase);

        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridwidth = 5;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 4;
        panel.add(scrollPane_1, gbc_scrollPane_1);

        txta_output = new JTextArea();
        scrollPane_1.setViewportView(txta_output);

    }
}
