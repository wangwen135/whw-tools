package com.wwh.whwtools.swing.feature.digest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

/**
 * <pre>
 * MD5
 * </pre>
 *
 * @author wwh
 * @date 2015年8月8日 上午14:29:59
 *
 */
public class MD5IFrame extends BaseJInternalFrame {

	private static final Logger log = LoggerFactory.getLogger(MD5IFrame.class);

	private static final long serialVersionUID = 1L;
	private JTextArea txta_input;
	private JCheckBox chkbx_base64;
	private JComboBox<String> cmbbx_charset;
	private JTextField txt_up;
	private JTextField txt_lower;
	private JTextField txt_compare;

	public MD5IFrame() {
		super();
		setTitle("MD5");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(60, 50, 590, 322);
		setPreferredSize(new Dimension(590, 322));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 120, 50, 151, 70, 105, 0 };
		gbl_panel.rowHeights = new int[] { 21, 105, 40, 21, 21, 40, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("请输入要进行编码的字符：");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		chkbx_base64 = new JCheckBox("内容为Base64");
		GridBagConstraints gbc_chkbx_base64 = new GridBagConstraints();
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

		JButton btn_encode = new JButton("摘要");
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

					byte[] b = CodecUtils.md5(bytes);

					String ret = CodecUtils.hexEncode(b);

					txt_lower.setText(ret);
					txt_up.setText(ret.toUpperCase());

				} catch (Exception ex) {
					log.error("编码错误", ex);
					JOptionPane.showMessageDialog(MD5IFrame.this, "编码错误：" + ex.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btn_encode = new GridBagConstraints();
		gbc_btn_encode.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_encode.anchor = GridBagConstraints.NORTH;
		gbc_btn_encode.insets = new Insets(0, 0, 5, 5);
		gbc_btn_encode.gridx = 0;
		gbc_btn_encode.gridy = 2;
		panel.add(btn_encode, gbc_btn_encode);

		JLabel lblHex = new JLabel("Hex 小写");
		GridBagConstraints gbc_lblHex = new GridBagConstraints();
		gbc_lblHex.anchor = GridBagConstraints.EAST;
		gbc_lblHex.insets = new Insets(0, 0, 5, 5);
		gbc_lblHex.gridx = 0;
		gbc_lblHex.gridy = 3;
		panel.add(lblHex, gbc_lblHex);

		txt_up = new JTextField();
		GridBagConstraints gbc_txt_up = new GridBagConstraints();
		gbc_txt_up.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_up.insets = new Insets(0, 0, 5, 5);
		gbc_txt_up.gridwidth = 3;
		gbc_txt_up.gridx = 1;
		gbc_txt_up.gridy = 3;
		panel.add(txt_up, gbc_txt_up);
		txt_up.setColumns(10);

		JLabel lblHex_1 = new JLabel("Hex 大写");
		GridBagConstraints gbc_lblHex_1 = new GridBagConstraints();
		gbc_lblHex_1.anchor = GridBagConstraints.EAST;
		gbc_lblHex_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblHex_1.gridx = 0;
		gbc_lblHex_1.gridy = 4;
		panel.add(lblHex_1, gbc_lblHex_1);

		txt_lower = new JTextField();
		GridBagConstraints gbc_txt_lower = new GridBagConstraints();
		gbc_txt_lower.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_lower.insets = new Insets(0, 0, 5, 5);
		gbc_txt_lower.gridwidth = 3;
		gbc_txt_lower.gridx = 1;
		gbc_txt_lower.gridy = 4;
		panel.add(txt_lower, gbc_txt_lower);
		txt_lower.setColumns(10);

		JLabel label_1 = new JLabel("目标值");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 5;
		panel.add(label_1, gbc_label_1);

		txt_compare = new JTextField();
		txt_compare.setColumns(10);
		GridBagConstraints gbc_txt_compare = new GridBagConstraints();
		gbc_txt_compare.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_compare.insets = new Insets(0, 0, 0, 5);
		gbc_txt_compare.gridwidth = 3;
		gbc_txt_compare.gridx = 1;
		gbc_txt_compare.gridy = 5;
		panel.add(txt_compare, gbc_txt_compare);

		JButton btn_compare = new JButton("对比");
		btn_compare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txt_compare.getText();
				if (text == null || "".equals(text)) {
					return;
				}

				String text2 = txt_up.getText();
				if (text2 == null || "".equals(text2)) {
					return;
				}

				if (text.equalsIgnoreCase(text2)) {
					JOptionPane.showMessageDialog(MD5IFrame.this, "结果 与 目标值 匹配");
				} else {
					JOptionPane.showMessageDialog(MD5IFrame.this, "结果 与 目标值 不匹配", "不匹配", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btn_compare = new GridBagConstraints();
		gbc_btn_compare.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_compare.gridx = 4;
		gbc_btn_compare.gridy = 5;
		panel.add(btn_compare, gbc_btn_compare);

	}
}
