package com.wwh.whwtools.test.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class SwingWorkerTest extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingWorkerTest frame = new SwingWorkerTest();
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
    public SwingWorkerTest() {
        setTitle("进度条测试面板");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 564, 394);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        progressBar.setBounds(82, 31, 264, 22);
        contentPane.add(progressBar);

        progressBar.setString("正在加载。。。");
        progressBar.setValue(2);

        JButton btnd = new JButton("测试按钮(D)");
        btnd.setMnemonic('d');
        btnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SwingWorkerTest.this, "这是一个测试");
            }
        });
        btnd.setBounds(82, 114, 182, 23);
        contentPane.add(btnd);

        // 毛玻璃效果
        final GlassPanel glassPanel = new GlassPanel();

        setGlassPane(glassPanel);

        JButton btns = new JButton("开启玻璃面板(S)");
        btns.setMnemonic('s');
        btns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                glassPanel.setVisible(true);
                SwingWorker<List<String>, String> sworker = new SwingWorker<List<String>, String>() {

                    @Override
                    protected List<String> doInBackground() throws Exception {
                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < 50; i++) {

                            if (i == 20) {
                                throw new RuntimeException("测试异常");
                            }

                            Thread.sleep(300);

                            String msg = "正在处理：第【" + i + "】个数据 ...";
                            list.add(msg);
                            publish(msg);
                        }
                        return list;
                    }

                    @Override
                    protected void process(List<String> chunks) {
                        for (String string : chunks) {
                            glassPanel.setCurrentMessage(string);
                        }
                    }

                    @Override
                    protected void done() {
                        // 设置值到表格中
                        try {
                            List<String> ret = get();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            glassPanel.setVisible(false);
                        }

                    }

                };
                sworker.execute();
            }
        });
        btns.setBounds(82, 197, 182, 23);
        contentPane.add(btns);

        JButton btnNewButton = new JButton("关闭玻璃面板(C)");
        btnNewButton.setMnemonic('C');
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                glassPanel.setVisible(false);
            }
        });
        btnNewButton.setBounds(82, 250, 182, 23);
        contentPane.add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(298, 115, 137, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(317, 172, 152, 101);
        contentPane.add(textArea);
    }
}
