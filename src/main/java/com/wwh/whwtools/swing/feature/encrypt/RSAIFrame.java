package com.wwh.whwtools.swing.feature.encrypt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;
import javax.swing.JTextField;

/**
 * <pre>
 * DES 加密解密
 * </pre>
 *
 * @author wwh
 *
 */
public class RSAIFrame extends BaseJInternalFrame {

    private static final Logger logger = LoggerFactory.getLogger(RSAIFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextArea txta_input;
    private JComboBox<String> cmbbx_charset;

    private JRadioButton rdbtn_txt;

    private JRadioButton rdbtn_base64;

    private JRadioButton rdbtn_hex;

    private JRadioButton rdbtn_out_base64;

    private JRadioButton rdbtn_out_hex;

    private JTextArea txta_out;
    private JButton btn_exchange;
    private JButton btn_priEncode;
    private JButton btn_priDecode;
    private JLabel label_1;
    private JComboBox<String> combx_keyLength;
    private JButton btn_generateKey;
    private JScrollPane scrollPane_2;
    private JTextArea txta_privateKey;
    private JScrollPane scrollPane_3;
    private JTextArea txta_publicKey;
    private JLabel lblNewLabel_1;
    private JLabel label_2;
    private JRadioButton rdbtn_key_base64;
    private JRadioButton rdbtn_key_hex;
    private JLabel label_3;
    private JLabel label_5;
    private JLabel label_6;
    private JButton btn_parseKey;
    private JLabel label_7;
    private JLabel label_8;
    private JComboBox combx_padding;
    private JTextField txt_publicExponent;
    private JTextField txt_modulus;
    private JTextField txt_privateExponent;
    private JTextField txt_keyLength;

    public RSAIFrame() {
        super();
        setTitle("RSA 加密解码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(20, 20, 1002, 766);
        setPreferredSize(new Dimension(655, 485));

        ButtonGroup bgKey = new ButtonGroup();

        ButtonGroup bgSource = new ButtonGroup();

        ButtonGroup bgTarget = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 100, 100, 100, 100, 50, 100, 100, 0 };
        gbl_panel.rowHeights = new int[] { 0, 23, 120, 0, 0, 0, 0, 8, 23, 105, 55, 23, 105, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        label_1 = new JLabel("密钥位数：");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.EAST;
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 0;
        panel.add(label_1, gbc_label_1);

        combx_keyLength = new JComboBox<String>();
        combx_keyLength.setModel(new DefaultComboBoxModel<String>(new String[] { "512", "1024", "2048", "4096" }));
        combx_keyLength.setSelectedIndex(1);
        combx_keyLength.setEditable(true);
        GridBagConstraints gbc_combx_keyLength = new GridBagConstraints();
        gbc_combx_keyLength.fill = GridBagConstraints.HORIZONTAL;
        gbc_combx_keyLength.insets = new Insets(0, 0, 5, 5);
        gbc_combx_keyLength.gridx = 1;
        gbc_combx_keyLength.gridy = 0;
        panel.add(combx_keyLength, gbc_combx_keyLength);

        btn_generateKey = new JButton("生成密钥对");
        btn_generateKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateRsaKeyPair();
            }
        });
        GridBagConstraints gbc_btn_generateKey = new GridBagConstraints();
        gbc_btn_generateKey.insets = new Insets(0, 0, 5, 5);
        gbc_btn_generateKey.gridx = 2;
        gbc_btn_generateKey.gridy = 0;
        panel.add(btn_generateKey, gbc_btn_generateKey);

        rdbtn_key_base64 = new JRadioButton("Base64  ");
        rdbtn_key_base64.setSelected(true);
        GridBagConstraints gbc_rdbtn_key_base64 = new GridBagConstraints();
        gbc_rdbtn_key_base64.anchor = GridBagConstraints.EAST;
        gbc_rdbtn_key_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_key_base64.gridx = 4;
        gbc_rdbtn_key_base64.gridy = 0;
        panel.add(rdbtn_key_base64, gbc_rdbtn_key_base64);
        bgKey.add(rdbtn_key_base64);

        rdbtn_key_hex = new JRadioButton("Hex");
        GridBagConstraints gbc_rdbtn_key_hex = new GridBagConstraints();
        gbc_rdbtn_key_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_key_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_key_hex.gridx = 5;
        gbc_rdbtn_key_hex.gridy = 0;
        panel.add(rdbtn_key_hex, gbc_rdbtn_key_hex);
        bgKey.add(rdbtn_key_hex);

        lblNewLabel_1 = new JLabel("私钥：");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 12));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        label_2 = new JLabel("公钥：");
        label_2.setFont(new Font("宋体", Font.BOLD, 12));
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.anchor = GridBagConstraints.WEST;
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 5;
        gbc_label_2.gridy = 1;
        panel.add(label_2, gbc_label_2);

        scrollPane_2 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.gridwidth = 5;
        gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 2;
        panel.add(scrollPane_2, gbc_scrollPane_2);

        txta_privateKey = new JTextArea();
        txta_privateKey.setLineWrap(true);
        txta_privateKey.setToolTipText(
                "<html>\r\n<p>-----BEGIN RSA PRIVATE KEY-----</p>\r\n......base64......<br>\r\n......xxxx......<br>\r\n......xxxx......<br>\r\n<p>-----END RSA PRIVATE KEY-----</p>\r\n</html>");
        scrollPane_2.setViewportView(txta_privateKey);

        scrollPane_3 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
        gbc_scrollPane_3.gridwidth = 2;
        gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_3.gridx = 5;
        gbc_scrollPane_3.gridy = 2;
        panel.add(scrollPane_3, gbc_scrollPane_3);

        txta_publicKey = new JTextArea();
        txta_publicKey.setLineWrap(true);
        txta_publicKey.setToolTipText(
                "<html>\r\n<p>-----BEGIN PUBLIC KEY-----</p>\r\n......base64......<br>\r\n......xxxx......<br>\r\n......xxxx......<br>\r\n<p>-----END PUBLIC KEY-----</p>\r\n</html>");
        scrollPane_3.setViewportView(txta_publicKey);
        
        label_5 = new JLabel("公钥指数：");
        GridBagConstraints gbc_label_5 = new GridBagConstraints();
        gbc_label_5.anchor = GridBagConstraints.EAST;
        gbc_label_5.insets = new Insets(0, 0, 5, 5);
        gbc_label_5.gridx = 0;
        gbc_label_5.gridy = 3;
        panel.add(label_5, gbc_label_5);
        
        txt_publicExponent = new JTextField();
        GridBagConstraints gbc_txt_publicExponent = new GridBagConstraints();
        gbc_txt_publicExponent.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_publicExponent.insets = new Insets(0, 0, 5, 5);
        gbc_txt_publicExponent.gridx = 1;
        gbc_txt_publicExponent.gridy = 3;
        panel.add(txt_publicExponent, gbc_txt_publicExponent);
        txt_publicExponent.setColumns(5);
        
        label_7 = new JLabel("秘钥长度：");
        GridBagConstraints gbc_label_7 = new GridBagConstraints();
        gbc_label_7.anchor = GridBagConstraints.EAST;
        gbc_label_7.insets = new Insets(0, 0, 5, 5);
        gbc_label_7.gridx = 2;
        gbc_label_7.gridy = 3;
        panel.add(label_7, gbc_label_7);
        
        txt_keyLength = new JTextField();
        txt_keyLength.setEditable(false);
        GridBagConstraints gbc_txt_keyLength = new GridBagConstraints();
        gbc_txt_keyLength.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_keyLength.insets = new Insets(0, 0, 5, 5);
        gbc_txt_keyLength.gridx = 3;
        gbc_txt_keyLength.gridy = 3;
        panel.add(txt_keyLength, gbc_txt_keyLength);
        txt_keyLength.setColumns(15);
        
        label_6 = new JLabel("模数(系数)：");
        GridBagConstraints gbc_label_6 = new GridBagConstraints();
        gbc_label_6.anchor = GridBagConstraints.EAST;
        gbc_label_6.insets = new Insets(0, 0, 5, 5);
        gbc_label_6.gridx = 0;
        gbc_label_6.gridy = 4;
        panel.add(label_6, gbc_label_6);
        
        txt_modulus = new JTextField();
        GridBagConstraints gbc_txt_modulus = new GridBagConstraints();
        gbc_txt_modulus.gridwidth = 6;
        gbc_txt_modulus.insets = new Insets(0, 0, 5, 0);
        gbc_txt_modulus.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_modulus.gridx = 1;
        gbc_txt_modulus.gridy = 4;
        panel.add(txt_modulus, gbc_txt_modulus);
        txt_modulus.setColumns(10);
        
        label_3 = new JLabel("私钥指数：");
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.anchor = GridBagConstraints.EAST;
        gbc_label_3.insets = new Insets(0, 0, 5, 5);
        gbc_label_3.gridx = 0;
        gbc_label_3.gridy = 5;
        panel.add(label_3, gbc_label_3);
        
        txt_privateExponent = new JTextField();
        GridBagConstraints gbc_txt_privateExponent = new GridBagConstraints();
        gbc_txt_privateExponent.gridwidth = 6;
        gbc_txt_privateExponent.insets = new Insets(0, 0, 5, 0);
        gbc_txt_privateExponent.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_privateExponent.gridx = 1;
        gbc_txt_privateExponent.gridy = 5;
        panel.add(txt_privateExponent, gbc_txt_privateExponent);
        txt_privateExponent.setColumns(10);
        
        btn_parseKey = new JButton("解析秘钥对");
        GridBagConstraints gbc_btn_parseKey = new GridBagConstraints();
        gbc_btn_parseKey.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_parseKey.insets = new Insets(0, 0, 5, 5);
        gbc_btn_parseKey.gridx = 0;
        gbc_btn_parseKey.gridy = 6;
        panel.add(btn_parseKey, gbc_btn_parseKey);

        JLabel lblNewLabel = new JLabel("明文内容：");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 8;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        rdbtn_txt = new JRadioButton("文本");
        rdbtn_txt.setToolTipText("getBytes(编码)");
        rdbtn_txt.setSelected(true);
        GridBagConstraints gbc_rdbtn_txt = new GridBagConstraints();
        gbc_rdbtn_txt.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtn_txt.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_txt.gridx = 1;
        gbc_rdbtn_txt.gridy = 8;
        panel.add(rdbtn_txt, gbc_rdbtn_txt);
        bgSource.add(rdbtn_txt);

        rdbtn_base64 = new JRadioButton("Base64");
        GridBagConstraints gbc_rdbtn_base64 = new GridBagConstraints();
        gbc_rdbtn_base64.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_base64.gridx = 2;
        gbc_rdbtn_base64.gridy = 8;
        panel.add(rdbtn_base64, gbc_rdbtn_base64);
        bgSource.add(rdbtn_base64);

        rdbtn_hex = new JRadioButton("Hex");
        GridBagConstraints gbc_rdbtn_hex = new GridBagConstraints();
        gbc_rdbtn_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_hex.gridx = 3;
        gbc_rdbtn_hex.gridy = 8;
        panel.add(rdbtn_hex, gbc_rdbtn_hex);
        bgSource.add(rdbtn_hex);

        JLabel label = new JLabel("文本编码");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 5;
        gbc_label.gridy = 8;
        panel.add(label, gbc_label);

        cmbbx_charset = new JComboBox<String>();
        cmbbx_charset.setModel(new DefaultComboBoxModel<String>(new String[] { "UTF-8", "GBK", "GB2312" }));
        cmbbx_charset.setEditable(true);
        GridBagConstraints gbc_cmbbx_charset = new GridBagConstraints();
        gbc_cmbbx_charset.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbbx_charset.insets = new Insets(0, 0, 5, 0);
        gbc_cmbbx_charset.gridx = 6;
        gbc_cmbbx_charset.gridy = 8;
        panel.add(cmbbx_charset, gbc_cmbbx_charset);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 9;
        panel.add(scrollPane, gbc_scrollPane);

        txta_input = new JTextArea();
        txta_input.setLineWrap(true);
        scrollPane.setViewportView(txta_input);

        JButton btn_pubEncode = new JButton("公钥加密↓");
        btn_pubEncode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                publicKeyEncrypt();
            }
        });
        GridBagConstraints gbc_btn_pubEncode = new GridBagConstraints();
        gbc_btn_pubEncode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_pubEncode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_pubEncode.gridx = 0;
        gbc_btn_pubEncode.gridy = 10;
        panel.add(btn_pubEncode, gbc_btn_pubEncode);

        btn_exchange = new JButton("交换↑↓");
        btn_exchange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exchangeContent();
            }
        });

        JButton btn_pubDecode = new JButton("公钥解密↑");
        btn_pubDecode.setForeground(Color.BLUE);
        btn_pubDecode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 解密
                publicKeyDecrypt();
            }
        });
        GridBagConstraints gbc_btn_pubDecode = new GridBagConstraints();
        gbc_btn_pubDecode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_pubDecode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_pubDecode.gridx = 1;
        gbc_btn_pubDecode.gridy = 10;
        panel.add(btn_pubDecode, gbc_btn_pubDecode);

        btn_priEncode = new JButton("私钥加密↓");
        btn_priEncode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                privateKeyEncrypt();
            }
        });
        GridBagConstraints gbc_btn_priEncode = new GridBagConstraints();
        gbc_btn_priEncode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_priEncode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_priEncode.gridx = 2;
        gbc_btn_priEncode.gridy = 10;
        panel.add(btn_priEncode, gbc_btn_priEncode);

        btn_priDecode = new JButton("私钥解密↑");
        btn_priDecode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                privateKeyDecrypt();
            }
        });
        btn_priDecode.setForeground(Color.BLUE);
        GridBagConstraints gbc_btn_priDecode = new GridBagConstraints();
        gbc_btn_priDecode.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_priDecode.insets = new Insets(0, 0, 5, 5);
        gbc_btn_priDecode.gridx = 3;
        gbc_btn_priDecode.gridy = 10;
        panel.add(btn_priDecode, gbc_btn_priDecode);
        
        label_8 = new JLabel("填充方式：");
        GridBagConstraints gbc_label_8 = new GridBagConstraints();
        gbc_label_8.anchor = GridBagConstraints.EAST;
        gbc_label_8.insets = new Insets(0, 0, 5, 5);
        gbc_label_8.gridx = 4;
        gbc_label_8.gridy = 10;
        panel.add(label_8, gbc_label_8);
        
        combx_padding = new JComboBox();
        combx_padding.setModel(new DefaultComboBoxModel(new String[] {"PKCS1Padding", "NoPadding", "OAEPPadding"}));
        GridBagConstraints gbc_combx_padding = new GridBagConstraints();
        gbc_combx_padding.anchor = GridBagConstraints.WEST;
        gbc_combx_padding.insets = new Insets(0, 0, 5, 5);
        gbc_combx_padding.gridx = 5;
        gbc_combx_padding.gridy = 10;
        panel.add(combx_padding, gbc_combx_padding);
        GridBagConstraints gbc_btn_exchange = new GridBagConstraints();
        gbc_btn_exchange.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_exchange.insets = new Insets(0, 0, 5, 0);
        gbc_btn_exchange.gridx = 6;
        gbc_btn_exchange.gridy = 10;
        panel.add(btn_exchange, gbc_btn_exchange);

        JLabel label_4 = new JLabel("密文内容：");
        GridBagConstraints gbc_label_4 = new GridBagConstraints();
        gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_label_4.insets = new Insets(0, 0, 5, 5);
        gbc_label_4.gridx = 0;
        gbc_label_4.gridy = 11;
        panel.add(label_4, gbc_label_4);

        rdbtn_out_base64 = new JRadioButton("Base64");
        rdbtn_out_base64.setSelected(true);
        GridBagConstraints gbc_rdbtn_out_base64 = new GridBagConstraints();
        gbc_rdbtn_out_base64.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_out_base64.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_out_base64.gridx = 1;
        gbc_rdbtn_out_base64.gridy = 11;
        panel.add(rdbtn_out_base64, gbc_rdbtn_out_base64);
        bgTarget.add(rdbtn_out_base64);

        rdbtn_out_hex = new JRadioButton("Hex");
        GridBagConstraints gbc_rdbtn_out_hex = new GridBagConstraints();
        gbc_rdbtn_out_hex.anchor = GridBagConstraints.WEST;
        gbc_rdbtn_out_hex.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtn_out_hex.gridx = 2;
        gbc_rdbtn_out_hex.gridy = 11;
        panel.add(rdbtn_out_hex, gbc_rdbtn_out_hex);
        bgTarget.add(rdbtn_out_hex);

        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridwidth = 7;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 12;
        panel.add(scrollPane_1, gbc_scrollPane_1);

        txta_out = new JTextArea();
        txta_out.setLineWrap(true);
        scrollPane_1.setViewportView(txta_out);

    }

    /**
     * 生成随机秘钥对
     */
    private void generateRsaKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            keyPairGen.initialize(Integer.valueOf((String) combx_keyLength.getSelectedItem()), new SecureRandom());

            KeyPair keyPair = keyPairGen.generateKeyPair();

            setPublicKey(keyPair.getPublic().getEncoded());
            setPrivateKey(keyPair.getPrivate().getEncoded());

        } catch (NoSuchAlgorithmException e) {
            logger.error("不支持RSA加密算法", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "不支持RSA加密算法", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.error("生成RSA秘钥对失败", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "生成RSA秘钥对失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setPublicKey(byte[] publicKey) {
        if (rdbtn_key_base64.isSelected()) {
            txta_publicKey.setText(CodecUtils.base64Encode(publicKey));
        } else {
            txta_publicKey.setText(CodecUtils.hexEncode(publicKey));
        }
    }

    private void setPrivateKey(byte[] privateKey) {
        if (rdbtn_key_base64.isSelected()) {
            txta_privateKey.setText(CodecUtils.base64Encode(privateKey));
        } else {
            txta_privateKey.setText(CodecUtils.hexEncode(privateKey));
        }
    }

    /**
     * 获取明文内容
     * 
     * @return
     * @throws Exception
     */
    private byte[] getCleartextBytes() throws Exception {
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
     * 获取密文内容
     * 
     * @return
     * @throws Exception
     */
    private byte[] getCiphertextBytes() throws Exception {

        String inputText = txta_out.getText();
        if (inputText == null || "".equals(inputText)) {
            return null;
        }

        if (rdbtn_out_base64.isSelected()) {
            return CodecUtils.base64Decode(inputText);
        }

        if (rdbtn_out_hex.isSelected()) {
            return CodecUtils.hexDecode(inputText);
        }

        return null;
    }

    /**
     * 设置明文内容
     * 
     * @param bytes
     */
    private void setCleartext(byte[] bytes) {
        if (rdbtn_txt.isSelected()) {
            Object c = cmbbx_charset.getSelectedItem();
            String charset = c == null ? "UTF-8" : c.toString();
            try {
                txta_input.setText(new String(bytes, charset));
            } catch (UnsupportedEncodingException e) {
                logger.error("不支持的编码", e);
                JOptionPane.showMessageDialog(RSAIFrame.this, "不支持的编码：" + e.getMessage(), "错误",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdbtn_base64.isSelected()) {
            txta_input.setText(CodecUtils.base64Encode(bytes));
        }
        if (rdbtn_hex.isSelected()) {
            txta_input.setText(CodecUtils.hexEncode(bytes));
        }
    }

    /**
     * 设置密文内容
     * 
     * @param bytes
     */
    private void setCiphertext(byte[] bytes) {

        if (rdbtn_out_base64.isSelected()) {
            txta_out.setText(CodecUtils.base64Encode(bytes));
        }
        if (rdbtn_out_hex.isSelected()) {
            txta_out.setText(CodecUtils.hexEncode(bytes));
        }
    }

    /**
     * 获取秘钥字节数组
     * 
     * @param isPublicKey
     * @return
     */
    private byte[] getKeyBytes(boolean isPublicKey) {
        String content = null;
        if (isPublicKey) {
            content = txta_publicKey.getText();
        } else {
            content = txta_privateKey.getText();
        }
        if (content == null || "".equals(content)) {
            JOptionPane.showMessageDialog(RSAIFrame.this, isPublicKey ? "公钥" : "私钥" + "不能为空！", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            if (rdbtn_key_base64.isSelected()) {
                return CodecUtils.base64Decode(content);
            } else {
                return CodecUtils.hexDecode(content);
            }
        } catch (Exception e) {
            logger.error("将秘钥文本解析成字节数组异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "将秘钥文本解析成字节数组异常\n" + e.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * 获取公钥<br>
     * 公钥是 X.509 编码的
     * 
     * @return
     */
    private RSAPublicKey getPublicKey() {
        byte[] buffer = getKeyBytes(true);
        if (buffer == null) {
            return null;
        }
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error("不支持RSA加密算法", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "不支持RSA加密算法", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidKeySpecException e) {
            logger.error("无效的公钥", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "无效的公钥\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.error("获取公钥异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "获取公钥异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * <pre>
     * 获取私钥<br>
     * 私钥是 PKCS8 编码的
     * The Public-Key Cryptography Standards (PKCS)是由美国RSA数据安全公司及其合作伙伴制定的一组公钥密码学标准
     * </pre>
     * 
     * @return
     */
    private RSAPrivateKey getPrivateKey() {
        byte[] buffer = getKeyBytes(false);
        if (buffer == null) {
            return null;
        }
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error("不支持RSA加密算法", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "不支持RSA加密算法", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidKeySpecException e) {
            logger.error("无效的私钥", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "无效的私钥\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.error("获取私钥异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "获取私钥异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    /**
     * 公钥加密
     */
    private void publicKeyEncrypt() {
        // 获取输入
        byte[] input = null;
        try {
            input = getCleartextBytes();
        } catch (Exception e1) {
            logger.error("RSA公钥加密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(RSAIFrame.this, "RSA公钥加密获取输入byte[]异常\n" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }
        // 获取密钥
        RSAPublicKey publicKey = getPublicKey();
        if (publicKey == null) {
            return;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] ret = cipher.doFinal(input);
            setCiphertext(ret);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("不支持的加密算法", ex);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("公钥加密异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "公钥加密异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 公钥解密
     */
    private void publicKeyDecrypt() {
        // 获取输入
        byte[] input = null;
        try {
            input = getCiphertextBytes();
        } catch (Exception e1) {
            logger.error("RSA公钥解密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(RSAIFrame.this, "RSA公钥解密获取输入byte[]异常\n" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }
        // 获取密钥
        RSAPublicKey publicKey = getPublicKey();
        if (publicKey == null) {
            return;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] ret = cipher.doFinal(input);
            setCleartext(ret);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("不支持的加密算法", ex);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("公钥解密异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "公钥解密异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 私钥解密
     */
    private void privateKeyDecrypt() {
        // 获取输入
        byte[] input = null;
        try {
            input = getCiphertextBytes();
        } catch (Exception e1) {
            logger.error("RSA私钥解密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(RSAIFrame.this, "RSA私钥解密获取输入byte[]异常\n" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }
        // 获取密钥
        RSAPrivateKey privateKey = getPrivateKey();
        if (privateKey == null) {
            return;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] ret = cipher.doFinal(input);
            setCleartext(ret);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("不支持的加密算法", ex);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("私钥解密异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "私钥解密异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * 私钥加密
     */
    private void privateKeyEncrypt() {
        // 获取输入
        byte[] input = null;
        try {
            input = getCleartextBytes();
        } catch (Exception e1) {
            logger.error("RSA私钥加密获取输入byte[]异常", e1);
            JOptionPane.showMessageDialog(RSAIFrame.this, "RSA私钥加密获取输入byte[]异常\n" + e1.getMessage(), "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (input == null) {
            return;
        }
        // 获取密钥
        RSAPrivateKey privateKey = getPrivateKey();
        if (privateKey == null) {
            return;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] ret = cipher.doFinal(input);
            setCiphertext(ret);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("不支持的加密算法", ex);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("私钥加密异常", e);
            JOptionPane.showMessageDialog(RSAIFrame.this, "私钥加密异常\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void exchangeContent() {
        String text = txta_input.getText();
        txta_input.setText(txta_out.getText());
        txta_out.setText(text);
    }

}
