package com.wwh.whwtools.swing.feature.codec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;

/**
 * <pre>
 * Base64编码
 * </pre>
 *
 * @author wwh
 * @date 2015年8月7日 下午6:29:59
 *
 */
public class Base64IFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(Base64IFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextArea txta_input;
    private JCheckBox chkbx_16;
    private JTextArea txta_output;
    private JComboBox<String> cmbbx_charset;

    public Base64IFrame() {
        super();
        setTitle("Base64编码/解码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(10, 10, 600, 370);
        setPreferredSize(new Dimension(600, 370));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 100, 100, 170, 50, 100, 0 };
        gbl_panel.rowHeights = new int[] { 30, 105, 25, 25, 105, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
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

        JLabel label = new JLabel("文本编码");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 2;
        gbc_label.gridy = 0;
        panel.add(label, gbc_label);

        JButton btn_encode = new JButton("编  码");
        btn_encode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = txta_input.getText();
                if (inputText == null || "".equals(inputText)) {
                    return;
                }
                try {
                    Object c = cmbbx_charset.getSelectedItem();
                    String charString = c == null ? "UTF-8" : c.toString();
                    txta_output.setText(CodecUtils.base64Encode(inputText.getBytes(charString)));
                } catch (Exception ex) {
                    log.error("编码错误", ex);
                    JOptionPane.showMessageDialog(Base64IFrame.this, "编码错误：" + ex.getMessage());
                }
            }
        });

        cmbbx_charset = new JComboBox<String>();
        cmbbx_charset.setModel(new DefaultComboBoxModel<String>(new String[] { "UTF-8", "GBK", "GB2312" }));
        cmbbx_charset.setEditable(true);
        GridBagConstraints gbc_cmbbx_charset = new GridBagConstraints();
        gbc_cmbbx_charset.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbbx_charset.insets = new Insets(0, 0, 5, 5);
        gbc_cmbbx_charset.gridx = 3;
        gbc_cmbbx_charset.gridy = 0;
        panel.add(cmbbx_charset, gbc_cmbbx_charset);

        JButton btnBase = new JButton("?");
        btnBase.setToolTipText("Base64说明");
        btnBase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(Base64IFrame.this,
                        "Base64 把每三个8Bit的字节 转换为 四个6Bit的字节（3*8 = 4*6 = 24），\n" + "然后把6Bit再添两位高位0，组成四个8Bit的字节，\n"
                                + "也就是说，转换后的字符串理论上将要比原来的长1/3。\n" + "6Bit刚好又64种组合（2^6=64），用64个字符表示即可，\n"
                                + "Base64码表包括 [A-Za-z0-9+/]，工64个字符，\n" + "当最后不足3字节时，1变2，2变3；不够的位数用0补全，再用=号补满4个字节",
                        "Base64说明", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        GridBagConstraints gbc_btnBase = new GridBagConstraints();
        gbc_btnBase.anchor = GridBagConstraints.EAST;
        gbc_btnBase.insets = new Insets(0, 0, 5, 0);
        gbc_btnBase.gridx = 4;
        gbc_btnBase.gridy = 0;
        panel.add(btnBase, gbc_btnBase);

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

        JButton btn_exchange = new JButton("交换↑↓");
        btn_exchange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = txta_input.getText();
                txta_input.setText(txta_output.getText());
                txta_output.setText(text);
            }
        });

        JButton btn_decode = new JButton("解  码");
        btn_decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String inputText = txta_input.getText();
                if (inputText == null || "".equals(inputText)) {
                    return;
                }
                boolean hex = chkbx_16.isSelected();
                try {
                    Object c = cmbbx_charset.getSelectedItem();
                    String charString = c == null ? "UTF-8" : c.toString();
                    byte[] bytes = CodecUtils.base64Decode(inputText);
                    if (hex) {
                        txta_output.setText(CodecUtils.hexEncode(bytes));
                    } else {
                        txta_output.setText(new String(bytes, charString));
                    }
                } catch (Exception ex) {
                    log.error("解码错误", ex);
                    JOptionPane.showMessageDialog(Base64IFrame.this, "解码错误：" + ex.getMessage());
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

        chkbx_16 = new JCheckBox("解码结果以16进制显示");
        GridBagConstraints gbc_chkbx_16 = new GridBagConstraints();
        gbc_chkbx_16.anchor = GridBagConstraints.NORTH;
        gbc_chkbx_16.fill = GridBagConstraints.HORIZONTAL;
        gbc_chkbx_16.insets = new Insets(0, 0, 5, 5);
        gbc_chkbx_16.gridx = 2;
        gbc_chkbx_16.gridy = 2;
        panel.add(chkbx_16, gbc_chkbx_16);
        GridBagConstraints gbc_btn_exchange = new GridBagConstraints();
        gbc_btn_exchange.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_exchange.anchor = GridBagConstraints.NORTH;
        gbc_btn_exchange.insets = new Insets(0, 0, 5, 0);
        gbc_btn_exchange.gridx = 4;
        gbc_btn_exchange.gridy = 2;
        panel.add(btn_exchange, gbc_btn_exchange);

        JLabel lblBase = new JLabel("Base64编码或解码结果：");
        GridBagConstraints gbc_lblBase = new GridBagConstraints();
        gbc_lblBase.anchor = GridBagConstraints.WEST;
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
