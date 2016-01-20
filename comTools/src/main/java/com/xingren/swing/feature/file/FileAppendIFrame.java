package com.xingren.swing.feature.file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xingren.swing.frame.BaseJInternalFrame;

/**
 * <pre>
 * 根据条件追加文件
 * </pre>
 *
 * @author wwh
 * @date 2015年12月17日 下午3:31:17
 *
 */
public class FileAppendIFrame extends BaseJInternalFrame {
    public FileAppendIFrame() {

        super();
        setTitle("文件追加");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(10, 10, 600, 470);
        setPreferredSize(new Dimension(600, 470));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panel.add(tabbedPane);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabbedPane.addTab("删除-追加", null, panel_1, null);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 102, 66, 175, 50, 120, 0 };
        gbl_panel_1.rowHeights = new int[] { 15, 91, 24, 15, 23, 21, 23, 30, 38, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        JButton btn_dirSelect = new JButton("选择文件夹");
        btn_dirSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // 选择目标目录
                JFileChooser jfc = new JFileChooser();
                String destDIR = txt_fileDir.getText();
                File f = new File(destDIR);
                jfc.setCurrentDirectory(f);
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = jfc.showDialog(FileAppendIFrame.this, "对该目录进行操作");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    selectDIR = jfc.getSelectedFile();
                    txt_fileDir.setText(selectDIR.getAbsolutePath());
                }

            }
        });

        JLabel label = new JLabel("删除原文件末尾的内容");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.NORTHWEST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridwidth = 3;
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        panel_1.add(label, gbc_label);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridwidth = 5;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        panel_1.add(scrollPane, gbc_scrollPane);

        txta_removeContent = new JTextArea();
        scrollPane.setViewportView(txta_removeContent);

        JCheckBox checkBox = new JCheckBox("忽略文件中的空行");
        checkBox.setSelected(true);
        GridBagConstraints gbc_checkBox = new GridBagConstraints();
        gbc_checkBox.anchor = GridBagConstraints.SOUTH;
        gbc_checkBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_checkBox.insets = new Insets(0, 0, 5, 5);
        gbc_checkBox.gridwidth = 2;
        gbc_checkBox.gridx = 0;
        gbc_checkBox.gridy = 2;
        panel_1.add(checkBox, gbc_checkBox);

        JCheckBox checkBox_3 = new JCheckBox("忽略内容前后的空白字符");
        checkBox_3.setSelected(true);
        GridBagConstraints gbc_checkBox_3 = new GridBagConstraints();
        gbc_checkBox_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_checkBox_3.anchor = GridBagConstraints.NORTH;
        gbc_checkBox_3.insets = new Insets(0, 0, 5, 5);
        gbc_checkBox_3.gridx = 2;
        gbc_checkBox_3.gridy = 2;
        panel_1.add(checkBox_3, gbc_checkBox_3);

        JLabel label_1 = new JLabel("进行操作的文件夹");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridwidth = 3;
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 3;
        panel_1.add(label_1, gbc_label_1);

        txt_fileDir = new JTextField();
        txt_fileDir.setBackground(Color.WHITE);
        txt_fileDir.setEditable(false);
        GridBagConstraints gbc_txt_fileDir = new GridBagConstraints();
        gbc_txt_fileDir.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_fileDir.insets = new Insets(0, 0, 5, 5);
        gbc_txt_fileDir.gridwidth = 4;
        gbc_txt_fileDir.gridx = 0;
        gbc_txt_fileDir.gridy = 4;
        panel_1.add(txt_fileDir, gbc_txt_fileDir);
        txt_fileDir.setColumns(10);
        GridBagConstraints gbc_btn_dirSelect = new GridBagConstraints();
        gbc_btn_dirSelect.gridwidth = 2;
        gbc_btn_dirSelect.anchor = GridBagConstraints.NORTHEAST;
        gbc_btn_dirSelect.insets = new Insets(0, 0, 5, 0);
        gbc_btn_dirSelect.gridx = 3;
        gbc_btn_dirSelect.gridy = 4;
        panel_1.add(btn_dirSelect, gbc_btn_dirSelect);

        JLabel label_2 = new JLabel("追加文件后缀");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 0;
        gbc_label_2.gridy = 5;
        panel_1.add(label_2, gbc_label_2);

        txt_fileSuffix = new JTextField();
        txt_fileSuffix.setText(".append");
        GridBagConstraints gbc_txt_fileSuffix = new GridBagConstraints();
        gbc_txt_fileSuffix.anchor = GridBagConstraints.NORTHWEST;
        gbc_txt_fileSuffix.insets = new Insets(0, 0, 5, 5);
        gbc_txt_fileSuffix.gridx = 1;
        gbc_txt_fileSuffix.gridy = 5;
        panel_1.add(txt_fileSuffix, gbc_txt_fileSuffix);
        txt_fileSuffix.setColumns(10);

        JCheckBox checkBox_1 = new JCheckBox("条件符合才追加");
        checkBox_1.setSelected(true);
        GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
        gbc_checkBox_1.anchor = GridBagConstraints.NORTH;
        gbc_checkBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_checkBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_checkBox_1.gridwidth = 2;
        gbc_checkBox_1.gridx = 0;
        gbc_checkBox_1.gridy = 6;
        panel_1.add(checkBox_1, gbc_checkBox_1);

        JCheckBox checkBox_2 = new JCheckBox("追加前备份老文件");
        GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
        gbc_checkBox_2.anchor = GridBagConstraints.NORTH;
        gbc_checkBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_checkBox_2.insets = new Insets(0, 0, 5, 5);
        gbc_checkBox_2.gridx = 2;
        gbc_checkBox_2.gridy = 6;
        panel_1.add(checkBox_2, gbc_checkBox_2);

        JCheckBox checkBox_4 = new JCheckBox("操作完成删除追加文件");
        checkBox_4.setSelected(true);
        GridBagConstraints gbc_checkBox_4 = new GridBagConstraints();
        gbc_checkBox_4.gridwidth = 2;
        gbc_checkBox_4.anchor = GridBagConstraints.NORTH;
        gbc_checkBox_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_checkBox_4.insets = new Insets(0, 0, 5, 0);
        gbc_checkBox_4.gridx = 3;
        gbc_checkBox_4.gridy = 6;
        panel_1.add(checkBox_4, gbc_checkBox_4);

        JButton button_1 = new JButton("追加操作");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 追加操作
                append();
            }
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_button_1.insets = new Insets(0, 0, 0, 5);
        gbc_button_1.gridx = 2;
        gbc_button_1.gridy = 8;
        panel_1.add(button_1, gbc_button_1);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("在指定位置追加", null, panel_2, null);
    }

    private void append() {
        if (selectDIR == null) {
            JOptionPane.showMessageDialog(FileAppendIFrame.this, "请先选择要操作的目录");
            return;
        }

        String fileSuffix = txt_fileSuffix.getText();
        int suffixLength = fileSuffix.length();
        if (fileSuffix == null || "".equals(fileSuffix)) {
            JOptionPane.showMessageDialog(FileAppendIFrame.this, "追加文件的后缀不能为空");
            return;
        }

        if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(FileAppendIFrame.this, "确认对\n" + selectDIR.getAbsolutePath() + "\n目录下的文件进行操作？", "再次确认",
                JOptionPane.YES_NO_OPTION)) {
            return;
        }

        // 需要移除的内容
        String removeContent = txta_removeContent.getText();
        String[] removeLines = removeContent.split("[\r\n]");
        List<String> removeList = new ArrayList<String>();
        // 去除前后空格和空行
        for (int i = 0; i < removeLines.length; i++) {
            String oneLine = removeLines[i].trim();
            if (!"".equals(oneLine)) {
                removeList.add(oneLine);
            }
        }

        List<File> srcFileList = new ArrayList<File>();
        Map<String, File> appendFileMap = new HashMap<String, File>();

        for (File f : selectDIR.listFiles()) {
            String name = f.getName();
            if (name.endsWith(fileSuffix)) {
                appendFileMap.put(name.substring(0, name.length() - suffixLength), f);
            } else {
                srcFileList.add(f);
            }
        }
        System.out.println("开始进行操作");
        // 记录操作的文件数量
        int operationSize = 0;
        for (File file : srcFileList) {
            // 先判断有没有要追加的文件
            String name = file.getName();
            File appendFile = appendFileMap.get(name);
            if (appendFile == null) {
                System.out.println(name + "  没有要追加的文件");
                continue;
            }
            // 需要关闭文件
            RandomAccessFile raf = null;
            FileInputStream fis = null;
            try {

                raf = new RandomAccessFile(file, "rw");

                // 先判断要删除的内容
                if (!"".equals(removeContent)) {
                    // 如果有要删除的内容，则进行删除操作

                    // removeContent
                    // removeLines
                    int readLength = removeContent.length() * 3;// 可能没有这么长

                    readLength = (int) (readLength > raf.length() ? raf.length() : readLength);

                    byte[] b = new byte[readLength];
                    raf.seek(raf.length() - readLength);
                    raf.read(b);

                    String fileContent = new String(b);
                    System.out.println(fileContent);

                    // 可能还需要判断一下是用什么换行的
                    String[] contentLines = fileContent.split("[\r\n]");

                    boolean matching = true;
                    int surplusLine = 0;
                    // 先抓紧时间做了
                    int removeListIndex = removeList.size();
                    for (int i = contentLines.length - 1; i >= 0; i--) {
                        if (removeListIndex == 0) {
                            // 比较完了就可以跳出了
                            surplusLine = i;
                            break;
                        }
                        String theLast = contentLines[i].trim();
                        if ("".equals(theLast)) {
                            continue;
                        }
                        String theRemove = removeList.get(--removeListIndex);

                        if (!theLast.equals(theRemove)) {
                            matching = false;
                            break;
                        }
                    }

                    if (!matching) {
                        // 如果不匹配，跳过这个文件
                        System.out.println("不匹配");
                        continue;
                    }

                    // 计算剩余的字节数
                    int surplusSize = 0;
                    for (int i = 0; i < surplusLine; i++) {
                        surplusSize += contentLines[i].getBytes().length;
                        surplusSize++;
                    }
                    surplusSize--;

                    // 重新定位文件
                    raf.setLength(raf.length() - readLength + surplusSize);
                }

                // 定位到文件
                raf.seek(raf.length());
                // 追加文件
                fis = new FileInputStream(appendFile);
                FileChannel appendChannel = fis.getChannel();

                System.out.println("追加内容到：" + name);

                FileChannel sourceChannel = raf.getChannel();
                sourceChannel.position(sourceChannel.size());
                sourceChannel.transferFrom(appendChannel, sourceChannel.size(), appendChannel.size());

                sourceChannel.close();
                appendChannel.close();

                // 处理了一个文件
                operationSize++;

            } catch (IOException e) {
                log.error("文件 {} 操作异常", name);
            } finally {
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        JOptionPane.showMessageDialog(FileAppendIFrame.this, "操作完成，一共处理：" + operationSize);
    }

    private static final Logger log = LoggerFactory.getLogger(FileAppendIFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextField txt_fileDir;
    private File selectDIR;
    private JTextField txt_fileSuffix;

    private JTextArea txta_removeContent;
}
