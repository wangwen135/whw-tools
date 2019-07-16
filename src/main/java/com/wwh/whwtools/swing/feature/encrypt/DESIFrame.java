package com.wwh.whwtools.swing.feature.encrypt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;
import com.wwh.whwtools.utils.DESCoder;

/**
 * <pre>
 * DES 加密解密
 * </pre>
 *
 * @author wwh
 *
 */
public class DESIFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(DESIFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextArea txta_input;
    private JComboBox<String> cmbbx_charset;
    private JTextField txt_key;

    private JRadioButton rdbtn_txt;

    private JRadioButton rdbtn_base64;

    private JRadioButton rdbtn_hex;

    private JRadioButton rdbtn_key_md5;

    private JRadioButton rdbtn_key_base64;

    private JRadioButton rdbtn_key_hex;

    private JComboBox<String> cmbbx_mode;

    private JComboBox<String> cmbbx_fill;

    private JRadioButton rdbtn_out_txt;

    private JRadioButton rdbtn_out_base64;

    private JRadioButton rdbtn_out_hex;

    private JTextArea txta_out;
    private JButton btn_exchange;

    public DESIFrame() {
        super();
        setTitle("DES 加密解码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(20, 20, 750, 540);
        setPreferredSize(new Dimension(655, 485));

        ButtonGroup bg = new ButtonGroup();

        ButtonGroup bg2 = new ButtonGroup();

        ButtonGroup bg3 = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 100, 100, 100, 100, 100, 100, 105, 0 };
        gbl_panel.rowHeights = new int[] { 23, 105, 35, 21, 24, 55, 23, 105, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("输入内容：");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        rdbtn_txt = new JRadioButton("文本");
        rdbtn_txt.setToolTipText("getBytes(编码)");
        rdbtn_txt.setSelected(true);
        GridBagConstraints gbc_rdbtn_txt = new GridBagConstraints();
        gbc_rdbtn_txt.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_txt.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_txt.gridx = 1;
        gbc_rdbtn_txt.gridy = 0;
        panel.add(rdbtn_txt, gbc_rdbtn_txt);
        bg.add(rdbtn_txt);

        rdbtn_base64 = new JRadioButton("Base64");
        GridBagConstraints gbc_rdbtn_base64 = new GridBagConstraints();
        gbc_rdbtn_base64.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_base64.gridx = 2;
        gbc_rdbtn_base64.gridy = 0;
        panel.add(rdbtn_base64, gbc_rdbtn_base64);
        bg.add(rdbtn_base64);

        rdbtn_hex = new JRadioButton("Hex");
        GridBagConstraints gbc_rdbtn_hex = new GridBagConstraints();
        gbc_rdbtn_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_hex.gridx = 3;
        gbc_rdbtn_hex.gridy = 0;
        panel.add(rdbtn_hex, gbc_rdbtn_hex);
        bg.add(rdbtn_hex);

        JLabel label = new JLabel("文本编码");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.gridwidth = 2;
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 4;
        gbc_label.gridy = 0;
        panel.add(label, gbc_label);

        cmbbx_charset = new JComboBox<String>();
        cmbbx_charset.setModel(new DefaultComboBoxModel<String>(new String[] { "UTF-8", "GBK", "GB2312" }));
        cmbbx_charset.setEditable(true);
        GridBagConstraints gbc_cmbbx_charset = new GridBagConstraints();
        gbc_cmbbx_charset.anchor = GridBagConstraints.NORTH;
        gbc_cmbbx_charset.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbbx_charset.insets = new Insets(0, 0, 5, 0);
        gbc_cmbbx_charset.gridx = 6;
        gbc_cmbbx_charset.gridy = 0;
        panel.add(cmbbx_charset, gbc_cmbbx_charset);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        panel.add(scrollPane, gbc_scrollPane);

        txta_input = new JTextArea();
        scrollPane.setViewportView(txta_input);

        JLabel label_1 = new JLabel("64位(8字节)密钥：");
        label_1.setToolTipText("密钥事实上是56位参与DES运算（第8、16、24、32、40、48、56、64位是校验位");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 2;
        panel.add(label_1, gbc_label_1);
        rdbtn_key_md5 = new JRadioButton("文本-MD5");
        rdbtn_key_md5.setToolTipText("对文本进行MD5计算，将计算结果的前8个字节作为密钥");
        rdbtn_key_md5.setSelected(true);
        GridBagConstraints gbc_rdbtn_key_md5 = new GridBagConstraints();
        gbc_rdbtn_key_md5.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_key_md5.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_key_md5.gridx = 1;
        gbc_rdbtn_key_md5.gridy = 2;
        panel.add(rdbtn_key_md5, gbc_rdbtn_key_md5);
        bg2.add(rdbtn_key_md5);

        rdbtn_key_base64 = new JRadioButton("Base64");
        rdbtn_key_base64.setToolTipText("取前8个字节作为秘钥");
        GridBagConstraints gbc_rdbtn_key_base64 = new GridBagConstraints();
        gbc_rdbtn_key_base64.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_key_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_key_base64.gridx = 2;
        gbc_rdbtn_key_base64.gridy = 2;
        panel.add(rdbtn_key_base64, gbc_rdbtn_key_base64);
        bg2.add(rdbtn_key_base64);

        rdbtn_key_hex = new JRadioButton("Hex");
        rdbtn_key_hex.setToolTipText("取前8个字节作为秘钥");
        GridBagConstraints gbc_rdbtn_key_hex = new GridBagConstraints();
        gbc_rdbtn_key_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_key_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_key_hex.gridx = 3;
        gbc_rdbtn_key_hex.gridy = 2;
        panel.add(rdbtn_key_hex, gbc_rdbtn_key_hex);
        bg2.add(rdbtn_key_hex);

        txt_key = new JTextField();
        GridBagConstraints gbc_txt_key = new GridBagConstraints();
        gbc_txt_key.anchor = GridBagConstraints.NORTH;
        gbc_txt_key.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_key.insets = new Insets(0, 0, 5, 0);
        gbc_txt_key.gridwidth = 7;
        gbc_txt_key.gridx = 0;
        gbc_txt_key.gridy = 3;
        panel.add(txt_key, gbc_txt_key);
        txt_key.setColumns(10);

        JLabel label_2 = new JLabel("加密模式：");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.anchor = GridBagConstraints.EAST;
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 0;
        gbc_label_2.gridy = 4;
        panel.add(label_2, gbc_label_2);

        cmbbx_mode = new JComboBox<String>();
        cmbbx_mode.setModel(new DefaultComboBoxModel<String>(new String[] { "ECB", "CBC", "CFB", "OFB", "PCBC" }));
        GridBagConstraints gbc_cmbbx_mode = new GridBagConstraints();
        gbc_cmbbx_mode.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbbx_mode.insets = new Insets(0, 0, 5, 5);
        gbc_cmbbx_mode.gridx = 1;
        gbc_cmbbx_mode.gridy = 4;
        panel.add(cmbbx_mode, gbc_cmbbx_mode);

        JLabel label_3 = new JLabel("填充方式：");
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.anchor = GridBagConstraints.EAST;
        gbc_label_3.insets = new Insets(0, 0, 5, 5);
        gbc_label_3.gridx = 2;
        gbc_label_3.gridy = 4;
        panel.add(label_3, gbc_label_3);

        JButton btn_encode = new JButton(" 加    密 ");
        btn_encode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                encryption();
            }
        });

        cmbbx_fill = new JComboBox<String>();
        cmbbx_fill.setModel(
                new DefaultComboBoxModel<String>(new String[] { "PKCS5Padding", "ISO10126Padding", "NoPadding" }));
        GridBagConstraints gbc_cmbbx_fill = new GridBagConstraints();
        gbc_cmbbx_fill.gridwidth = 2;
        gbc_cmbbx_fill.anchor = GridBagConstraints.WEST;
        gbc_cmbbx_fill.insets = new Insets(0, 0, 5, 5);
        gbc_cmbbx_fill.gridx = 3;
        gbc_cmbbx_fill.gridy = 4;
        panel.add(cmbbx_fill, gbc_cmbbx_fill);
        GridBagConstraints gbc_btn_encode = new GridBagConstraints();
        gbc_btn_encode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_encode.gridx = 0;
        gbc_btn_encode.gridy = 5;
        panel.add(btn_encode, gbc_btn_encode);

        btn_exchange = new JButton("交换↑↓");
        btn_exchange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = txta_input.getText();
                txta_input.setText(txta_out.getText());
                txta_out.setText(text);
            }
        });

        JButton button = new JButton("解    密");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 解密
                decryption();
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.fill = GridBagConstraints.HORIZONTAL;
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 1;
        gbc_button.gridy = 5;
        panel.add(button, gbc_button);
        GridBagConstraints gbc_btn_exchange = new GridBagConstraints();
        gbc_btn_exchange.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_exchange.insets = new Insets(0, 0, 5, 0);
        gbc_btn_exchange.gridx = 6;
        gbc_btn_exchange.gridy = 5;
        panel.add(btn_exchange, gbc_btn_exchange);

        JLabel label_4 = new JLabel("输出结果：");
        GridBagConstraints gbc_label_4 = new GridBagConstraints();
        gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_label_4.insets = new Insets(0, 0, 5, 5);
        gbc_label_4.gridx = 0;
        gbc_label_4.gridy = 6;
        panel.add(label_4, gbc_label_4);
        rdbtn_out_txt = new JRadioButton("文本");
        rdbtn_out_txt.setToolTipText("new String(结果byte[] , 文本编码)");
        rdbtn_out_txt.setSelected(true);
        GridBagConstraints gbc_rdbtn_out_txt = new GridBagConstraints();
        gbc_rdbtn_out_txt.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_out_txt.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_out_txt.gridx = 1;
        gbc_rdbtn_out_txt.gridy = 6;
        panel.add(rdbtn_out_txt, gbc_rdbtn_out_txt);
        bg3.add(rdbtn_out_txt);

        rdbtn_out_base64 = new JRadioButton("Base64");
        GridBagConstraints gbc_rdbtn_out_base64 = new GridBagConstraints();
        gbc_rdbtn_out_base64.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_out_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_out_base64.gridx = 2;
        gbc_rdbtn_out_base64.gridy = 6;
        panel.add(rdbtn_out_base64, gbc_rdbtn_out_base64);
        bg3.add(rdbtn_out_base64);

        rdbtn_out_hex = new JRadioButton("Hex");
        GridBagConstraints gbc_rdbtn_out_hex = new GridBagConstraints();
        gbc_rdbtn_out_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_out_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_out_hex.gridx = 3;
        gbc_rdbtn_out_hex.gridy = 6;
        panel.add(rdbtn_out_hex, gbc_rdbtn_out_hex);
        bg3.add(rdbtn_out_hex);

        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridwidth = 7;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 7;
        panel.add(scrollPane_1, gbc_scrollPane_1);

        txta_out = new JTextArea();
        scrollPane_1.setViewportView(txta_out);

    }

    /**
     * 设置加密结果
     * 
     * @param bytes
     */
    private void setResult(byte[] bytes) {
        if (rdbtn_out_txt.isSelected()) {
            Object c = cmbbx_charset.getSelectedItem();
            String charset = c == null ? "UTF-8" : c.toString();
            try {
                txta_out.setText(new String(bytes, charset));
            } catch (UnsupportedEncodingException e) {
                log.error("不支持的编码", e);
                JOptionPane.showMessageDialog(DESIFrame.this, "不支持的编码：" + e.getMessage(), "错误",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (rdbtn_out_base64.isSelected()) {
            txta_out.setText(CodecUtils.base64Encode(bytes));
        }

        if (rdbtn_out_hex.isSelected()) {
            txta_out.setText(CodecUtils.hexEncode(bytes));
        }
    }

    /**
     * 获取加密算法
     * 
     * @return
     */
    private String getAlgorithm() {
        String mode = (String) cmbbx_mode.getSelectedItem();
        String fill = (String) cmbbx_fill.getSelectedItem();
        return "DES/" + mode + "/" + fill;
    }

    /**
     * 获取密钥
     * 
     * @return
     * @throws Exception
     */
    private byte[] getKeyBytes() throws Exception {
        String keyStr = txt_key.getText();
        if (keyStr == null || "".equals(keyStr)) {
            return null;
        }

        if (rdbtn_key_md5.isSelected()) {
            return CodecUtils.md5(keyStr.getBytes("UTF-8"));// 强制用UTF-8
        }

        if (rdbtn_key_base64.isSelected()) {
            return CodecUtils.base64Decode(keyStr);
        }

        if (rdbtn_key_hex.isSelected()) {
            return CodecUtils.hexDecode(keyStr);
        }

        return null;
    }

    /**
     * 获取输入
     * 
     * @return
     * @throws Exception
     */
    private byte[] getInputBytes() throws Exception {
        String inputText = txta_input.getText();
        if (inputText == null || "".equals(inputText)) {
            return null;
        }

        if (rdbtn_txt.isSelected()) {
            Object c = cmbbx_charset.getSelectedItem();
            String charset = c == null ? "UTF-8" : c.toString();

            return inputText.getBytes(charset);
        }

        if (rdbtn_base64.isSelected()) {
            return CodecUtils.base64Decode(inputText);
        }

        if (rdbtn_hex.isSelected()) {
            return CodecUtils.hexDecode(inputText);
        }

        return null;
    }

    /**
     * 解密
     */
    private void decryption() {
        // 获取输入
        byte[] input = null;
        try {
            input = getInputBytes();
        } catch (Exception e1) {
            log.error("DES解密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(DESIFrame.this, "DES解密获取输入byte[]异常：" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }

        // 获取密钥
        byte[] key;
        try {
            key = getKeyBytes();
        } catch (Exception e1) {
            log.error("DES解密获取密钥异常", e1);
            JOptionPane.showMessageDialog(DESIFrame.this, "DES解密获取密钥异常：" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (key == null) {
            JOptionPane.showMessageDialog(DESIFrame.this, "密钥不能为空");
            return;
        }

        // 加密算法
        String al = getAlgorithm();

        try {
            byte[] ret = DESCoder.decrypt(input, key, al);
            setResult(ret);
        } catch (Exception ex) {
            log.error("解密错误", ex);
            JOptionPane.showMessageDialog(DESIFrame.this, "解密错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 加密
     */
    private void encryption() {
        // 获取输入
        byte[] input = null;
        try {
            input = getInputBytes();
        } catch (Exception e1) {
            log.error("DES加密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(DESIFrame.this, "DES加密获取输入byte[]异常：" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }

        // 获取密钥
        byte[] key;
        try {
            key = getKeyBytes();
        } catch (Exception e1) {
            log.error("DES加密获取密钥异常", e1);
            JOptionPane.showMessageDialog(DESIFrame.this, "DES加密获取密钥异常：" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (key == null) {
            JOptionPane.showMessageDialog(DESIFrame.this, "密钥不能为空");
            return;
        }

        // 加密算法
        String al = getAlgorithm();

        try {
            byte[] ret = DESCoder.encrypt(input, key, al);
            setResult(ret);
        } catch (Exception ex) {
            log.error("加密错误", ex);
            JOptionPane.showMessageDialog(DESIFrame.this, "加密错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
