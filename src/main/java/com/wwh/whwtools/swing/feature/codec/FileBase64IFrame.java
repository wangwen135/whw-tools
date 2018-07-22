package com.wwh.whwtools.swing.feature.codec;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.swing.frame.BaseJInternalFrame;
import com.wwh.whwtools.utils.CodecUtils;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2016年1月20日 下午5:19:47
 *
 */
public class FileBase64IFrame extends BaseJInternalFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(FileBase64IFrame.class);
    private JTextField txtInputFile;
    private JTextField txtOutputFile;

    private JTextArea txtaBase64;
    private File inputFile;

    /**
     * Create the frame.
     */
    public FileBase64IFrame() {
        super();
        setTitle("文件 Base64编码/解码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(10, 10, 800, 450);
        setPreferredSize(new Dimension(800, 450));
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panelInput = new JPanel();
        getContentPane().add(panelInput, BorderLayout.NORTH);
        panelInput.setLayout(new BorderLayout(0, 0));

        JPanel panelInputFile = new JPanel();
        panelInput.add(panelInputFile);

        JLabel label = new JLabel("输入文件：");
        panelInputFile.add(label);

        txtInputFile = new JTextField();
        panelInputFile.add(txtInputFile);
        txtInputFile.setColumns(40);
        txtInputFile.setEditable(false);
        txtInputFile.setBackground(Color.WHITE);

        JButton btnInputFileChose = new JButton("选择");
        btnInputFileChose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectInputFile();
            }
        });
        panelInputFile.add(btnInputFileChose);

        JPanel panelInputButton = new JPanel();
        panelInput.add(panelInputButton, BorderLayout.EAST);

        JButton btnToBase64 = new JButton("将文件编码成Base64串");
        btnToBase64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toBase64();
            }
        });
        panelInputButton.add(btnToBase64);

        JPanel panelContent = new JPanel();
        getContentPane().add(panelContent, BorderLayout.CENTER);
        panelContent.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panelContent.add(scrollPane, BorderLayout.CENTER);

        txtaBase64 = new JTextArea();
        txtaBase64.setLineWrap(true);
        scrollPane.setViewportView(txtaBase64);

        JPanel panelOutput = new JPanel();
        getContentPane().add(panelOutput, BorderLayout.SOUTH);
        panelOutput.setLayout(new BorderLayout(0, 0));

        JPanel panelOutputFile = new JPanel();
        panelOutput.add(panelOutputFile);

        JLabel label_1 = new JLabel("输出文件：");
        panelOutputFile.add(label_1);

        txtOutputFile = new JTextField();
        panelOutputFile.add(txtOutputFile);
        txtOutputFile.setColumns(40);

        JButton btnOutputFileChose = new JButton("选择");
        btnOutputFileChose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectOutputFile();
            }
        });
        panelOutputFile.add(btnOutputFileChose);

        JPanel panelOutputButton = new JPanel();
        panelOutput.add(panelOutputButton, BorderLayout.EAST);

        JButton btnFromBase64 = new JButton("将Base64串解码成文件");
        btnFromBase64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                base64ToFile();
            }
        });
        panelOutputButton.add(btnFromBase64);
    }

    /**
     * 选择输入文件
     */
    private void selectInputFile() {
        JFileChooser chooser = new JFileChooser();
        if (inputFile != null) {
            try {
                chooser.setCurrentDirectory(inputFile.getParentFile());
            } catch (Exception ex) {
                log.error("文件异常", ex);
            }
        }
        int returnVal = chooser.showOpenDialog(FileBase64IFrame.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile();
            txtInputFile.setText(inputFile.getAbsolutePath());
        }
    }

    /**
     * 转成base64
     */
    private void toBase64() {
        if (inputFile == null) {
            JOptionPane.showMessageDialog(FileBase64IFrame.this, "请先选择文件");
            return;
        }
        FileInputStream fis = null;
        try {
            byte[] bytes = new byte[1024];
            int length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            fis = new FileInputStream(inputFile);

            while ((length = fis.read(bytes)) > 0) {
                baos.write(bytes, 0, length);
            }

            txtaBase64.setText(CodecUtils.base64Encode(baos.toByteArray()));
        } catch (Exception e) {
            log.error("编码失败", e);
            JOptionPane.showMessageDialog(this, "编码失败\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
    }

    /**
     * 选择输出文件
     */
    private void selectOutputFile() {
        // 选择目标目录
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int ret = jfc.showDialog(this, "选择");
        if (ret == JFileChooser.APPROVE_OPTION) {
            txtOutputFile.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * base64 转成文件
     */
    private void base64ToFile() {

        String base64Str = txtaBase64.getText();
        if (base64Str.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Base64 内容不能为空");
            return;
        }

        if (!CodecUtils.isBase64(base64Str)) {
            JOptionPane.showMessageDialog(this, "文本域中的内容不是有效的Base64字符串", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fileName = txtOutputFile.getText();
        if (fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请选择或输入文件路径", "提示", JOptionPane.WARNING_MESSAGE);
            txtOutputFile.requestFocus();
            return;
        }

        File f = new File(fileName);

        if (f.isDirectory()) {
            JOptionPane.showMessageDialog(this, "您选择的是目录\n请输入文件名", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }
        FileOutputStream fos = null;
        try {

            if (f.exists()) {
                if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(this, "文件已经存在，是否继续？\n将删除原文件并输出新文件", "是否继续", JOptionPane.YES_NO_OPTION)) {
                    return;
                } else {
                    // 删除原来的文件
                    f.delete();
                }
            }
            // 创建必要的上级目录
            f.getParentFile().mkdirs();
            // 创建文件
            f.createNewFile();

            byte[] bytes = CodecUtils.base64Decode(base64Str);

            fos = new FileOutputStream(f);

            fos.write(bytes);

            fos.flush();

            fos.close();

            JOptionPane.showMessageDialog(this, "完成");

        } catch (Exception e) {
            log.error("输出文件操作失败", e);
            JOptionPane.showMessageDialog(this, "操作失败\n" + e.getMessage(), "异常", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }

    }
}
