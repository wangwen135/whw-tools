package com.wwh.whwtools.swing.feature.generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.generic.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.constant.ProjectConstant;
import com.wwh.whwtools.generator.DBConnectionGetter;
import com.wwh.whwtools.generator.DBMetaDataGetter;
import com.wwh.whwtools.generator.GenerateHelp;
import com.wwh.whwtools.generator.entity.ColumnEntity;
import com.wwh.whwtools.generator.entity.TableEntity;
import com.wwh.whwtools.generator.impl.MySqlConnectionGetter;
import com.wwh.whwtools.generator.velocity.MyUtils;
import com.wwh.whwtools.swing.comm.GlassPanel;
import com.wwh.whwtools.swing.comm.MySwingWorker;
import com.wwh.whwtools.swing.comm.table.ColorRowTable;
import com.wwh.whwtools.swing.feature.generator.tableModel.DBTableModel;
import com.wwh.whwtools.swing.feature.generator.tableModel.TableTableModel;
import com.wwh.whwtools.swing.frame.BaseJInternalFrame;

/**
 * <pre>
 * 代码生成
 * </pre>
 *
 * @author wwh
 * @date 2015年8月22日 上午10:24:22
 *
 */
public class GenerateCodeIFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(GenerateCodeIFrame.class);

    private static final long serialVersionUID = 1L;
    private JTextField txt_host;
    private JTextField txt_dbName;
    private JTextField txt_userName;
    private JPasswordField txt_pwd;

    private JFormattedTextField ftxt_port;

    private DBConnectionGetter connGetter;
    private JTextField txt_tablePattern;
    private JTable table_db;
    private DBTableModel dbModel;
    private JTextField txt_tableName;
    private JTextField txt_tableRemark;
    private JTable table_table;
    private TableTableModel tableModel;
    private JTextField txt_destDIR;
    private JTextField txt_vmFile;

    private File selectDIR;
    private File selectVMFile;

    private TableEntity tableEntity;

    // private VelocityEngine velocityEngine;

    private JTextField txt_fileNameTemplate;

    private JTextArea txta_hide;

    private JCheckBox ckBox_hide;

    private JComboBox<String> cbox_encoding;

    private JTextArea txta_customVar;

    private JTextField txt_selectSize;
    private JTextField txt_totalSize;

    private GlassPanel glassPanel;

    private JCheckBox ckBox_coverFileName;
    private JTextField txt_rmTbPrefix;
    private JTextField txt_rmFdPrefix;

    public GenerateCodeIFrame() {
        super();
        setTitle("生成代码");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(0, 0, 1000, 680);
        setPreferredSize(new Dimension(1000, 680));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_conInfo = new JPanel();
        FlowLayout fl_panel_conInfo = (FlowLayout) panel_conInfo.getLayout();
        fl_panel_conInfo.setVgap(2);
        fl_panel_conInfo.setAlignment(FlowLayout.LEFT);
        panel.add(panel_conInfo, BorderLayout.NORTH);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setToolTipText("目前只支持MySQL数据库");
        comboBox.setEnabled(false);
        comboBox.setEditable(true);
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "MySQL" }));
        panel_conInfo.add(comboBox);

        JLabel label = new JLabel("  地址");
        panel_conInfo.add(label);

        txt_host = new JTextField();
        txt_host.setText("192.168.1.210");
        panel_conInfo.add(txt_host);
        txt_host.setColumns(15);

        JLabel label_1 = new JLabel("端口");
        panel_conInfo.add(label_1);

        ftxt_port = new JFormattedTextField(new DecimalFormat("######"));
        ftxt_port.setColumns(6);
        ftxt_port.setValue(new Integer(3306));
        panel_conInfo.add(ftxt_port);

        JLabel label_2 = new JLabel("数据库");
        panel_conInfo.add(label_2);

        txt_dbName = new JTextField();
        txt_dbName.setText("dbName");
        panel_conInfo.add(txt_dbName);
        txt_dbName.setColumns(10);

        JLabel label_3 = new JLabel("用户名");
        panel_conInfo.add(label_3);

        txt_userName = new JTextField();
        txt_userName.setText("root");
        panel_conInfo.add(txt_userName);
        txt_userName.setColumns(12);

        JLabel label_4 = new JLabel("密码");
        panel_conInfo.add(label_4);

        txt_pwd = new JPasswordField();
        txt_pwd.setColumns(12);
        panel_conInfo.add(txt_pwd);
        txt_pwd.setText("root");

        JButton btn_connection = new JButton("连接");
        btn_connection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getDBConnection();
                if (connGetter != null)
                    refreshDBTable();
            }
        });
        panel_conInfo.add(btn_connection);

        JButton button = new JButton("断开");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeDBConnection();
            }
        });
        panel_conInfo.add(button);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        panel.add(splitPane, BorderLayout.CENTER);

        JPanel panel_left = new JPanel();
        splitPane.setLeftComponent(panel_left);
        panel_left.setLayout(new BorderLayout(0, 0));

        JPanel panel_left_top = new JPanel();
        panel_left_top.setPreferredSize(new Dimension(10, 28));

        panel_left.add(panel_left_top, BorderLayout.NORTH);
        GridBagLayout gbl_panel_left_top = new GridBagLayout();

        panel_left_top.setLayout(gbl_panel_left_top);

        JButton btn_checkAll = new JButton("全");
        btn_checkAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAllDBTable();
            }
        });
        btn_checkAll.setMargin(new Insets(1, 1, 1, 1));
        GridBagConstraints gbc_btn_checkAll = new GridBagConstraints();
        gbc_btn_checkAll.insets = new Insets(0, 3, 0, 3);
        gbc_btn_checkAll.gridx = 0;
        gbc_btn_checkAll.gridy = 0;
        panel_left_top.add(btn_checkAll, gbc_btn_checkAll);

        JButton btn_inverse = new JButton("反");
        btn_inverse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inverseDBTableSelect();
            }
        });
        btn_inverse.setMargin(new Insets(1, 1, 1, 1));
        GridBagConstraints gbc_btn_inverse = new GridBagConstraints();
        gbc_btn_inverse.insets = new Insets(0, 0, 0, 5);
        gbc_btn_inverse.gridx = 1;
        gbc_btn_inverse.gridy = 0;
        panel_left_top.add(btn_inverse, gbc_btn_inverse);

        JLabel label_5 = new JLabel("匹配");
        GridBagConstraints gbc_label_5 = new GridBagConstraints();
        gbc_label_5.insets = new Insets(0, 0, 0, 5);
        gbc_label_5.gridx = 2;
        gbc_label_5.gridy = 0;
        panel_left_top.add(label_5, gbc_label_5);

        txt_tablePattern = new JTextField();
        txt_tablePattern.setText("%");
        txt_tablePattern.setColumns(14);
        GridBagConstraints gbc_txt_tablePattern = new GridBagConstraints();
        gbc_txt_tablePattern.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tablePattern.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tablePattern.weightx = 1;
        gbc_txt_tablePattern.gridx = 3;
        gbc_txt_tablePattern.gridy = 0;
        panel_left_top.add(txt_tablePattern, gbc_txt_tablePattern);

        JButton btn_refresh = new JButton("刷新");
        btn_refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDBTable();
            }
        });
        GridBagConstraints gbc_btn_refresh = new GridBagConstraints();
        gbc_btn_refresh.insets = new Insets(0, 0, 0, 4);
        gbc_btn_refresh.gridx = 4;
        gbc_btn_refresh.gridy = 0;
        panel_left_top.add(btn_refresh, gbc_btn_refresh);

        JScrollPane scrollPane_db = new JScrollPane();
        panel_left.add(scrollPane_db, BorderLayout.CENTER);

        table_db = new JTable();
        table_db.setRowHeight(22);
        table_db.setAutoCreateRowSorter(true);
        table_db.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table_db.setColumnSelectionAllowed(true);
        table_db.setCellSelectionEnabled(true);
        table_db.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() > 1) {
                        selectTable();
                    }
                }
            }
        });

        scrollPane_db.setViewportView(table_db);
        table_db.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        dbModel = new DBTableModel();
        dbModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                setTotalAndSelected();
            }
        });

        table_db.setModel(dbModel);

        setDBTableColumnPreferredWidth();

        JPanel panel_left_bottom = new JPanel();
        panel_left.add(panel_left_bottom, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_left_bottom = new GridBagLayout();
        gbl_panel_left_bottom.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0 };

        panel_left_bottom.setLayout(gbl_panel_left_bottom);

        JLabel lblNewLabel_1 = new JLabel("总共：");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        panel_left_bottom.add(lblNewLabel_1, gbc_lblNewLabel_1);

        txt_totalSize = new JTextField();
        txt_totalSize.setBackground(Color.WHITE);
        txt_totalSize.setEditable(false);
        GridBagConstraints gbc_txt_totalSize = new GridBagConstraints();
        gbc_txt_totalSize.insets = new Insets(0, 0, 0, 5);
        gbc_txt_totalSize.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_totalSize.gridx = 1;
        gbc_txt_totalSize.gridy = 0;
        panel_left_bottom.add(txt_totalSize, gbc_txt_totalSize);
        txt_totalSize.setColumns(10);

        JLabel lblNewLabel = new JLabel("选中：");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 0;
        panel_left_bottom.add(lblNewLabel, gbc_lblNewLabel);

        txt_selectSize = new JTextField();
        txt_selectSize.setBackground(Color.WHITE);
        txt_selectSize.setEditable(false);
        GridBagConstraints gbc_txt_selectSize = new GridBagConstraints();
        gbc_txt_selectSize.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_selectSize.gridx = 3;
        gbc_txt_selectSize.gridy = 0;
        panel_left_bottom.add(txt_selectSize, gbc_txt_selectSize);
        txt_selectSize.setColumns(10);

        JPanel panel_right = new JPanel();
        splitPane.setRightComponent(panel_right);
        panel_right.setLayout(new BorderLayout(0, 0));

        JPanel panel_right_top = new JPanel();
        panel_right_top.setPreferredSize(new Dimension(10, 370));
        panel_right_top.setLayout(new BorderLayout(0, 0));
        panel_right.add(panel_right_top, BorderLayout.NORTH);

        JPanel panel_right_buttom = new JPanel();
        panel_right_buttom.setPreferredSize(new Dimension(10, 30));
        panel_right_top.add(panel_right_buttom, BorderLayout.SOUTH);

        GridBagLayout gbl_panel_right_top = new GridBagLayout();
        gbl_panel_right_top.rowWeights = new double[] { 0.0 };
        gbl_panel_right_top.rowHeights = new int[] { 0 };
        panel_right_buttom.setLayout(gbl_panel_right_top);

        JLabel label_6 = new JLabel("表名");
        GridBagConstraints gbc_label_6 = new GridBagConstraints();
        gbc_label_6.anchor = GridBagConstraints.SOUTH;
        gbc_label_6.insets = new Insets(0, 0, 0, 5);
        gbc_label_6.gridx = 0;
        gbc_label_6.gridy = 0;
        panel_right_buttom.add(label_6, gbc_label_6);

        txt_tableName = new JTextField();
        txt_tableName.setBackground(Color.WHITE);
        txt_tableName.setEditable(false);
        txt_tableName.setColumns(25);
        GridBagConstraints gbc_txt_tableName = new GridBagConstraints();
        gbc_txt_tableName.anchor = GridBagConstraints.SOUTH;
        gbc_txt_tableName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tableName.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tableName.gridx = 1;
        gbc_txt_tableName.gridy = 0;
        panel_right_buttom.add(txt_tableName, gbc_txt_tableName);

        JLabel label_7 = new JLabel("注释");
        GridBagConstraints gbc_label_7 = new GridBagConstraints();
        gbc_label_7.anchor = GridBagConstraints.SOUTH;
        gbc_label_7.insets = new Insets(0, 0, 0, 5);
        gbc_label_7.gridx = 2;
        gbc_label_7.gridy = 0;
        panel_right_buttom.add(label_7, gbc_label_7);

        txt_tableRemark = new JTextField();
        txt_tableRemark.setBackground(Color.WHITE);
        txt_tableRemark.setEditable(false);
        GridBagConstraints gbc_txt_tableRemark = new GridBagConstraints();
        gbc_txt_tableRemark.anchor = GridBagConstraints.SOUTH;
        gbc_txt_tableRemark.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tableRemark.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tableRemark.weightx = 1;
        gbc_txt_tableRemark.gridx = 3;
        gbc_txt_tableRemark.gridy = 0;
        panel_right_buttom.add(txt_tableRemark, gbc_txt_tableRemark);

        JPanel panel_generate = new JPanel();
        panel_generate.setBorder(new TitledBorder(null, "\u8BBE\u7F6E\u533A\u57DF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_right_top.add(panel_generate, BorderLayout.CENTER);
        GridBagLayout gbl_panel_generate = new GridBagLayout();
        gbl_panel_generate.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel_generate.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel_generate.columnWeights = new double[] { 0.0, 1.0, 1.0, 2.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel_generate.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel_generate.setLayout(gbl_panel_generate);

        JLabel label_8 = new JLabel("生成文件位置：");
        GridBagConstraints gbc_label_8 = new GridBagConstraints();
        gbc_label_8.anchor = GridBagConstraints.EAST;
        gbc_label_8.insets = new Insets(0, 0, 5, 5);
        gbc_label_8.gridx = 0;
        gbc_label_8.gridy = 0;
        panel_generate.add(label_8, gbc_label_8);

        txt_destDIR = new JTextField();
        txt_destDIR.setBackground(Color.WHITE);
        txt_destDIR.setEditable(false);
        GridBagConstraints gbc_txt_destDIR = new GridBagConstraints();
        gbc_txt_destDIR.gridwidth = 4;
        gbc_txt_destDIR.insets = new Insets(0, 0, 5, 5);
        gbc_txt_destDIR.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_destDIR.gridx = 1;
        gbc_txt_destDIR.gridy = 0;
        panel_generate.add(txt_destDIR, gbc_txt_destDIR);
        txt_destDIR.setColumns(10);

        JButton btn_desc = new JButton("选择文件");
        btn_desc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 选择目标目录
                JFileChooser jfc = new JFileChooser();
                String destDIR = txt_destDIR.getText();
                File f = new File(destDIR);
                jfc.setCurrentDirectory(f);
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = jfc.showDialog(GenerateCodeIFrame.this, "生成到该目录");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    selectDIR = jfc.getSelectedFile();
                    txt_destDIR.setText(selectDIR.getAbsolutePath());
                }

            }
        });
        GridBagConstraints gbc_btn_desc = new GridBagConstraints();
        gbc_btn_desc.insets = new Insets(0, 0, 5, 0);
        gbc_btn_desc.gridx = 5;
        gbc_btn_desc.gridy = 0;
        panel_generate.add(btn_desc, gbc_btn_desc);

        JLabel label_10 = new JLabel("文件编码：");
        GridBagConstraints gbc_label_10 = new GridBagConstraints();
        gbc_label_10.anchor = GridBagConstraints.EAST;
        gbc_label_10.insets = new Insets(0, 0, 5, 5);
        gbc_label_10.gridx = 0;
        gbc_label_10.gridy = 1;
        panel_generate.add(label_10, gbc_label_10);

        cbox_encoding = new JComboBox<String>();
        cbox_encoding.setModel(new DefaultComboBoxModel<String>(new String[] { "UTF-8", "GBK" }));
        GridBagConstraints gbc_cbox_encoding = new GridBagConstraints();
        gbc_cbox_encoding.insets = new Insets(0, 0, 5, 5);
        gbc_cbox_encoding.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbox_encoding.gridx = 1;
        gbc_cbox_encoding.gridy = 1;
        panel_generate.add(cbox_encoding, gbc_cbox_encoding);

        JButton btn_javaMapping = new JButton("JAVA类型映射");
        btn_javaMapping.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "JAVA类型映射");
            }
        });
        GridBagConstraints gbc_btn_javaMapping = new GridBagConstraints();
        gbc_btn_javaMapping.anchor = GridBagConstraints.EAST;
        gbc_btn_javaMapping.insets = new Insets(0, 0, 5, 5);
        gbc_btn_javaMapping.gridx = 2;
        gbc_btn_javaMapping.gridy = 1;
        panel_generate.add(btn_javaMapping, gbc_btn_javaMapping);

        JButton btn_explain = new JButton("模板变量说明");
        btn_explain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "看代码 O(∩_∩)O~~");
            }
        });
        GridBagConstraints gbc_btn_explain = new GridBagConstraints();
        gbc_btn_explain.insets = new Insets(0, 0, 5, 5);
        gbc_btn_explain.gridx = 3;
        gbc_btn_explain.gridy = 1;
        panel_generate.add(btn_explain, gbc_btn_explain);

        JButton btn_usage = new JButton("使用说明(WEB)");
        btn_usage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(ProjectConstant.GITHUB_WIKI));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btn_usage = new GridBagConstraints();
        gbc_btn_usage.anchor = GridBagConstraints.WEST;
        gbc_btn_usage.insets = new Insets(0, 0, 5, 5);
        gbc_btn_usage.gridx = 4;
        gbc_btn_usage.gridy = 1;
        panel_generate.add(btn_usage, gbc_btn_usage);

        ckBox_hide = new JCheckBox("隐藏表字段：");
        ckBox_hide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ckBox_hide.isSelected()) {
                    txta_hide.setEnabled(true);
                } else {
                    txta_hide.setEnabled(false);
                }
            }
        });
        GridBagConstraints gbc_ckBox_hide = new GridBagConstraints();
        gbc_ckBox_hide.anchor = GridBagConstraints.EAST;
        gbc_ckBox_hide.insets = new Insets(0, 0, 5, 5);
        gbc_ckBox_hide.gridx = 0;
        gbc_ckBox_hide.gridy = 2;
        panel_generate.add(ckBox_hide, gbc_ckBox_hide);

        JLabel label_11 = new JLabel("自定义变量设置：");
        GridBagConstraints gbc_label_11 = new GridBagConstraints();
        gbc_label_11.gridwidth = 2;
        gbc_label_11.anchor = GridBagConstraints.WEST;
        gbc_label_11.insets = new Insets(0, 0, 5, 5);
        gbc_label_11.gridx = 3;
        gbc_label_11.gridy = 2;
        panel_generate.add(label_11, gbc_label_11);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 3;
        panel_generate.add(scrollPane, gbc_scrollPane);

        txta_hide = new JTextArea();
        txta_hide.setEnabled(false);
        txta_hide.setToolTipText("<html>\r\n被隐藏的字段不会出现在${table.columns}集合中<br>\r\n一行表示一个字段，可以使用列名 或 对应的java属性名\r\n</html>");
        txta_hide.setText("createTime\r\ncreateUserId\r\nmodifyTime\r\nmodifyUserId\r\ndeleteTime\r\ndeleteUserId\r\noptions\r\nversion");
        scrollPane.setViewportView(txta_hide);

        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.gridwidth = 3;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 3;
        gbc_scrollPane_1.gridy = 3;
        panel_generate.add(scrollPane_1, gbc_scrollPane_1);

        txta_customVar = new JTextArea();
        txta_customVar.setToolTipText("<html>\r\n一行表示一个变量<br>\r\n用‘=’分割，‘=’两侧可以出现空格\r\n</html>");
        txta_customVar.setText("author = wwh\r\npackageName = com.wwh.tools");
        scrollPane_1.setViewportView(txta_customVar);

        JLabel lbl_rmTbPrefix = new JLabel("去除表名前缀：");
        GridBagConstraints gbc_lbl_rmTbPrefix = new GridBagConstraints();
        gbc_lbl_rmTbPrefix.anchor = GridBagConstraints.EAST;
        gbc_lbl_rmTbPrefix.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_rmTbPrefix.gridx = 0;
        gbc_lbl_rmTbPrefix.gridy = 4;
        panel_generate.add(lbl_rmTbPrefix, gbc_lbl_rmTbPrefix);

        txt_rmTbPrefix = new JTextField();
        GridBagConstraints gbc_txt_rmTbPrefix = new GridBagConstraints();
        gbc_txt_rmTbPrefix.gridwidth = 2;
        gbc_txt_rmTbPrefix.insets = new Insets(0, 0, 5, 5);
        gbc_txt_rmTbPrefix.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_rmTbPrefix.gridx = 1;
        gbc_txt_rmTbPrefix.gridy = 4;
        panel_generate.add(txt_rmTbPrefix, gbc_txt_rmTbPrefix);
        txt_rmTbPrefix.setColumns(10);

        JLabel lbl_rmFdPrefix = new JLabel("去除字段前缀：");
        GridBagConstraints gbc_lbl_rmFdPrefix = new GridBagConstraints();
        gbc_lbl_rmFdPrefix.anchor = GridBagConstraints.EAST;
        gbc_lbl_rmFdPrefix.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_rmFdPrefix.gridx = 3;
        gbc_lbl_rmFdPrefix.gridy = 4;
        panel_generate.add(lbl_rmFdPrefix, gbc_lbl_rmFdPrefix);

        txt_rmFdPrefix = new JTextField();
        GridBagConstraints gbc_txt_rmFdPrefix = new GridBagConstraints();
        gbc_txt_rmFdPrefix.gridwidth = 2;
        gbc_txt_rmFdPrefix.insets = new Insets(0, 0, 5, 0);
        gbc_txt_rmFdPrefix.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_rmFdPrefix.gridx = 4;
        gbc_txt_rmFdPrefix.gridy = 4;
        panel_generate.add(txt_rmFdPrefix, gbc_txt_rmFdPrefix);
        txt_rmFdPrefix.setColumns(10);

        JLabel label_9 = new JLabel("生成文件名模板：");
        GridBagConstraints gbc_label_9 = new GridBagConstraints();
        gbc_label_9.anchor = GridBagConstraints.EAST;
        gbc_label_9.insets = new Insets(0, 0, 5, 5);
        gbc_label_9.gridx = 0;
        gbc_label_9.gridy = 5;
        panel_generate.add(label_9, gbc_label_9);

        txt_fileNameTemplate = new JTextField();
        txt_fileNameTemplate.setText("${table.className}");
        txt_fileNameTemplate
                .setToolTipText("<html>\r\n自动计算文件名<br>\r\n在模板中通过：<br>\r\n${fileNameAll}       可获取完整文件名<br>\r\n${fileName}           可获取文件名<br>\r\n#{fileNameSuffix}   获取文件后缀<br>\r\n</html>");
        GridBagConstraints gbc_txt_fileNameTemplate = new GridBagConstraints();
        gbc_txt_fileNameTemplate.gridwidth = 3;
        gbc_txt_fileNameTemplate.insets = new Insets(0, 0, 5, 5);
        gbc_txt_fileNameTemplate.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_fileNameTemplate.gridx = 1;
        gbc_txt_fileNameTemplate.gridy = 5;
        panel_generate.add(txt_fileNameTemplate, gbc_txt_fileNameTemplate);
        txt_fileNameTemplate.setColumns(10);

        JButton btn_testFileName = new JButton("测试文件名");
        btn_testFileName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                testFileName();
            }
        });
        GridBagConstraints gbc_btn_testFileName = new GridBagConstraints();
        gbc_btn_testFileName.insets = new Insets(0, 0, 5, 5);
        gbc_btn_testFileName.gridx = 4;
        gbc_btn_testFileName.gridy = 5;
        panel_generate.add(btn_testFileName, gbc_btn_testFileName);

        ckBox_coverFileName = new JCheckBox("覆盖文件名");
        ckBox_coverFileName.setToolTipText("以模板文件名.vm之前的内容覆盖文件名");
        ckBox_coverFileName.setSelected(true);
        GridBagConstraints gbc_ckBox_coverFileName = new GridBagConstraints();
        gbc_ckBox_coverFileName.insets = new Insets(0, 0, 5, 0);
        gbc_ckBox_coverFileName.gridx = 5;
        gbc_ckBox_coverFileName.gridy = 5;
        panel_generate.add(ckBox_coverFileName, gbc_ckBox_coverFileName);

        JLabel lblDo = new JLabel("模板文件：");
        GridBagConstraints gbc_lblDo = new GridBagConstraints();
        gbc_lblDo.anchor = GridBagConstraints.EAST;
        gbc_lblDo.insets = new Insets(0, 0, 5, 5);
        gbc_lblDo.gridx = 0;
        gbc_lblDo.gridy = 6;
        panel_generate.add(lblDo, gbc_lblDo);

        txt_vmFile = new JTextField();
        txt_vmFile.setBackground(Color.WHITE);
        txt_vmFile.setEditable(false);
        GridBagConstraints gbc_txt_vmFile = new GridBagConstraints();
        gbc_txt_vmFile.gridwidth = 4;
        gbc_txt_vmFile.insets = new Insets(0, 0, 5, 5);
        gbc_txt_vmFile.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_vmFile.gridx = 1;
        gbc_txt_vmFile.gridy = 6;
        panel_generate.add(txt_vmFile, gbc_txt_vmFile);
        txt_vmFile.setColumns(10);

        JButton btn_generateAll = new JButton("生成左侧勾选的表");
        btn_generateAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateAllFile();
            }
        });

        JButton btn_selectVMFile = new JButton("选择模板文件");
        btn_selectVMFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dovm = txt_vmFile.getText();

                File vmFile = chooseVMFile(dovm);

                if (vmFile != null) {
                    txt_vmFile.setText(vmFile.getAbsolutePath());
                    selectVMFile = vmFile;

                    if (ckBox_coverFileName.isSelected()) {
                        String _name = vmFile.getName();
                        txt_fileNameTemplate.setText(_name.substring(0, _name.lastIndexOf(".")));
                    }
                }
            }
        });
        GridBagConstraints gbc_btn_selectVMFile = new GridBagConstraints();
        gbc_btn_selectVMFile.insets = new Insets(0, 0, 5, 0);
        gbc_btn_selectVMFile.gridx = 5;
        gbc_btn_selectVMFile.gridy = 6;
        panel_generate.add(btn_selectVMFile, gbc_btn_selectVMFile);
        GridBagConstraints gbc_btn_generateAll = new GridBagConstraints();
        gbc_btn_generateAll.gridwidth = 2;
        gbc_btn_generateAll.insets = new Insets(0, 0, 0, 5);
        gbc_btn_generateAll.gridx = 1;
        gbc_btn_generateAll.gridy = 7;
        panel_generate.add(btn_generateAll, gbc_btn_generateAll);

        JButton btn_generate = new JButton("生成当前选中的表");
        btn_generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateOneFile();
            }
        });
        GridBagConstraints gbc_btn_generate = new GridBagConstraints();
        gbc_btn_generate.gridwidth = 2;
        gbc_btn_generate.insets = new Insets(0, 0, 0, 5);
        gbc_btn_generate.gridx = 3;
        gbc_btn_generate.gridy = 7;
        panel_generate.add(btn_generate, gbc_btn_generate);

        JScrollPane scrollPane_table = new JScrollPane();
        panel_right.add(scrollPane_table, BorderLayout.CENTER);

        table_table = new ColorRowTable();
        scrollPane_table.setViewportView(table_table);

        tableModel = new TableTableModel();
        table_table.setModel(tableModel);

        setCenterTableColumnPreferredWidth();

        // 注释掉这个就可以使用windowbuiler GUI插件了
        // ############ 设置玻璃面板
        glassPanel = new GlassPanel("/image/loading1.gif");
        setGlassPane(glassPanel);
    }

    /**
     * 设置表格列的首选宽度
     */
    public void setCenterTableColumnPreferredWidth() {
        table_table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table_table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table_table.getColumnModel().getColumn(3).setPreferredWidth(105);
        table_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table_table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table_table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table_table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table_table.getColumnModel().getColumn(8).setPreferredWidth(250);
    }

    /**
     * 
     */
    public void setDBTableColumnPreferredWidth() {
        table_db.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_db.getColumnModel().getColumn(1).setPreferredWidth(125);
        table_db.getColumnModel().getColumn(2).setPreferredWidth(135);
    }

    /**
     * 获取连接
     */
    public void getDBConnection() {
        String host = txt_host.getText();
        if (host == null || "".equals(host)) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "地址不能为空");
            txt_host.requestFocus();
            return;
        }
        Integer port = (Integer) ftxt_port.getValue();
        if (port == null) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "端口不能为空");
            ftxt_port.requestFocus();
            return;
        }

        String dbName = txt_dbName.getText();
        if (dbName == null || "".equals(dbName)) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "数据库名不能为空");
            txt_dbName.requestFocus();
            return;
        }

        String userName = txt_userName.getText();
        if (userName == null || "".equals(userName)) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "用户名不能为空");
            txt_userName.requestFocus();
            return;
        }

        String pwd = new String(txt_pwd.getPassword());
        if (pwd == null || "".equals(pwd)) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "密码不能为空");
            txt_pwd.requestFocus();
            return;
        }

        try {
            connGetter = new MySqlConnectionGetter(host, port, dbName, userName, pwd);

        } catch (Exception ex) {
            log.error("连接数据库异常", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, ex.getMessage(), "连接数据库异常\n" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 断开连接
     */
    public void closeDBConnection() {
        if (connGetter != null) {
            connGetter.closeConnection();
            connGetter = null;
        }
    }

    /**
     * 刷新数据库中的表
     */
    public void refreshDBTable() {
        if (connGetter == null) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请连接数据库");
            return;
        }
        final DBMetaDataGetter dbmdGetter = new DBMetaDataGetter(connGetter);
        String pattern = txt_tablePattern.getText().trim();
        if ("".equals(pattern)) {
            pattern = "%";
        }

        final String pattern2 = pattern;
        glassPanel.setVisible(true);// 开启玻璃面板
        MySwingWorker<List<TableEntity>> sWorker = new MySwingWorker<List<TableEntity>>() {

            @Override
            protected List<TableEntity> doInBackground() throws Exception {
                return dbmdGetter.getTables(pattern2, null, this);
            }

            @Override
            protected void process(List<String> chunks) {
                for (String string : chunks) {
                    glassPanel.setCurrentMessage(string);
                }
            }

            @Override
            protected void done() {
                try {
                    List<TableEntity> tableList = get();
                    dbModel.setTableList(tableList);
                    dbModel.fireTableDataChanged();

                    clearSelectTable();
                } catch (Exception e1) {
                    log.error("获取数据库表信息异常", e1);
                    JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "获取数据库表信息异常", "错误", JOptionPane.ERROR_MESSAGE);
                } finally {
                    glassPanel.setVisible(false);// 关闭玻璃面板
                }
            }
        };
        sWorker.execute();

    }

    /**
     * 清空选择
     */
    public void clearSelectTable() {
        tableEntity = null;
        txt_tableName.setText("");
        txt_tableRemark.setText("");

        tableModel.setColumnList(null);
        tableModel.fireTableDataChanged();
    }

    /**
     * 选择表
     */
    public void selectTable() {
        int rowIndex = table_db.getSelectedRow();
        if (rowIndex >= 0) {
            rowIndex = table_db.convertRowIndexToModel(rowIndex);

            tableEntity = dbModel.getTableEntity(rowIndex);
            txt_tableName.setText(tableEntity.getName());
            txt_tableRemark.setText(tableEntity.getRemark());

            List<ColumnEntity> colList = tableEntity.getColumns();

            tableModel.setColumnList(colList);
            tableModel.fireTableDataChanged();
        }
    }

    @Override
    protected void frameClosed() {
        closeDBConnection();
    }

    /**
     * 获取模板引擎
     * 
     * @return
     */
    private synchronized VelocityEngine getVelocityEngine() {
        // if (velocityEngine == null) {

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        ve.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        // 指定一个空的绝对路径
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "");

        // 因为编码会变所以就不能缓存了
        ve.setProperty(RuntimeConstants.INPUT_ENCODING, cbox_encoding.getSelectedItem().toString());

        ve.init();
        // velocityEngine = ve;
        // }

        return ve;
    }

    private VelocityContext getVelocityContext(TableEntity tableEntity, File descFile) {
        VelocityContext context = new VelocityContext();

        // 表实体对象
        context.put("table", tableEntity);

        // 日期工具
        context.put("DateTool", new DateTool());

        context.put("strUtil", new StringUtils());

        context.put("MyUtil", new MyUtils());

        // 日期
        Date date = new Date();
        context.put("date", date);

        // 下面这个两个日期为了方便用，其实可以通过工具获得
        // 2015年8月10日
        String date1 = new SimpleDateFormat("yyyy年M月d日").format(date);
        context.put("date1", date1);

        // 2015年8月10日 下午7:02:28
        String date2 = new SimpleDateFormat("yyyy年M月d日 ah:mm:ss").format(date);
        context.put("date2", date2);

        // 自定义的变量
        // 如作者等
        Map<String, Object> varMap = getCustomVar();
        if (varMap != null) {
            for (Entry<String, Object> varEntry : varMap.entrySet()) {
                context.put(varEntry.getKey(), varEntry.getValue());
            }
        }

        if (descFile != null) {
            // 目标文件
            context.put("file", descFile);
            /**
             * <pre>
             * ${fileNameAll}       可获取完整文件名<br>
             * ${fileName}           可获取文件名<br>
             * #{fileNameSuffix}   获取文件后缀<br>
             * </pre>
             */
            String name = descFile.getName();
            context.put("fileNameAll", name);

            int index = name.lastIndexOf(".");
            if (index < 0) {
                context.put("fileName", name);
                context.put("fileNameSuffix", "");
            } else {
                context.put("fileName", name.substring(0, index));
                context.put("fileNameSuffix", name.substring(index + 1));
            }
        }
        return context;
    }

    /**
     * 选择模板文件
     * 
     * @param oldVM
     *            原来的模板文件
     */
    public File chooseVMFile(String oldVM) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("vm 模板文件", "vm", "VM");
        chooser.setFileFilter(filter);

        if (oldVM != null && !"".equals(oldVM)) {
            try {
                File f = new File(oldVM);
                chooser.setCurrentDirectory(f.getParentFile());
            } catch (Exception ex) {
                log.error("文件异常", ex);
            }
        }

        int returnVal = chooser.showOpenDialog(GenerateCodeIFrame.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    /**
     * 返回文件名
     * 
     * @return
     */
    private String evaluateFileName(VelocityContext ctx, String template) {
        VelocityEngine ve = getVelocityEngine();
        StringWriter writer = new StringWriter();
        ve.evaluate(ctx, writer, "", template);
        return writer.toString();
    }

    /**
     * 获取隐藏字段
     * 
     * @return
     */
    private List<String> getHideField() {
        if (ckBox_hide.isSelected()) {
            String s = txta_hide.getText();
            if (s == null || "".equals(s) || "".equals(s.trim())) {
                return null;
            }
            String[] fields = s.split("[\r]?\n");
            // 移除掉空行

            List<String> list = arrayToList(fields);
            if (list.isEmpty()) {
                return null;
            }
            return list;
        }
        return null;
    }

    private List<String> arrayToList(String... s) {
        List<String> list = new ArrayList<String>();
        if (s == null)
            return list;
        for (String str : s) {
            if (str == null || "".equals(str.trim())) {
                continue;
            }
            list.add(str);
        }
        return list;
    }

    /**
     * 获取自定义的变量
     * 
     * @return
     */
    private Map<String, Object> getCustomVar() {
        String s = txta_customVar.getText();
        if (s == null || "".equals(s) || "".equals(s.trim())) {
            return null;
        }
        String[] fields = s.split("[\r]?\n");

        List<String> list = arrayToList(fields);
        Map<String, Object> map = new HashMap<String, Object>();
        for (String str : list) {
            String[] arrays = str.split("[ ]*=[ ]*", 2);
            if (arrays.length != 2) {
                continue;
            }
            map.put(arrays[0], arrays[1]);
        }

        return map;
    }

    /**
     * 移除表名前缀
     * 
     * @param tableEntity
     * @param prefix
     * @return
     * @throws Exception
     */
    public TableEntity removeTablePrefix(TableEntity tableEntity, String prefix) throws Exception {
        if (prefix != null && !"".equals(prefix)) {
            String name = tableEntity.getName();
            if (name.startsWith(prefix)) {
                String newName = name.substring(prefix.length());
                if (newName.startsWith("_")) {
                    newName = newName.substring(1);
                }
                TableEntity tableEntity2 = tableEntity.clone();
                // tableEntity2.setName(newName); name保持不变，只改变计算后的类名
                tableEntity2.setClassName(GenerateHelp.getClassNameByTableName(newName));

                return tableEntity2;
            }
        }
        return tableEntity;
    }

    public TableEntity removeTableColumnPrefxi(TableEntity tableEntity, String prefix) throws Exception {
        if (prefix != null && !"".equals(prefix)) {
            TableEntity te2 = tableEntity.clone();
            List<ColumnEntity> clist = te2.getColumns();
            for (ColumnEntity ce : clist) {
                // TODO 未完成的
            }

        }

        return tableEntity;
    }

    /**
     * 生成一个文件
     */
    public void generateOneFile() {
        if (!checkInput()) {
            return;
        }

        if (tableEntity == null) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请先选择一张表");
            return;
        }

        // 重新设置tableEntity，去除掉其中被忽略的属性
        TableEntity tableEntity2;
        try {
            tableEntity2 = filterTableEntity(tableEntity);
        } catch (Exception e) {
            log.error("对象属性过滤异常", e);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "对象属性过滤异常", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fileNameT = txt_fileNameTemplate.getText();
        String fileName;
        // 计算文件名可能会出错
        VelocityContext vct = getVelocityContext(tableEntity2, null);
        try {
            fileName = evaluateFileName(vct, fileNameT);
        } catch (Exception ex) {
            log.error("计算文件异常", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "计算文件名异常\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File descFile = new File(selectDIR, fileName);

        VelocityEngine ve = getVelocityEngine();

        VelocityContext context = getVelocityContext(tableEntity2, descFile);

        Template t = null;
        try {
            t = ve.getTemplate(selectVMFile.getAbsolutePath());
        } catch (Exception ex) {
            log.error("获取模板文件错误", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "获取模板文件错误\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // FileWriter writer = null;
        OutputStreamWriter writer = null;
        boolean b = false;
        try {
            // 指定编码
            writer = new OutputStreamWriter(new FileOutputStream(descFile), cbox_encoding.getSelectedItem().toString());

            // writer = new FileWriter(descFile);
            t.merge(context, writer);
            writer.flush();
            writer.close();
            b = true;
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "文件生成成功：\n" + descFile.getName());

        } catch (Exception ex) {
            log.error("生成代码文件异常", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "生成代码文件异常\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (writer != null && !b)
                try {
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            try {
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public TableEntity filterTableEntity(TableEntity input) throws Exception {
        // 重新设置tableEntity，去除掉其中被忽略的属性
        List<String> hideField = getHideField();
        if (hideField != null) {
            TableEntity tableEntity2 = input.clone();
            for (String hf : hideField) {
                tableEntity2.removeColumn(hf); // 移除属性
            }
            return tableEntity2;
        }
        return input;
    }

    /**
     * @return
     */
    public boolean checkInput() {
        // 先连接数据库

        if (selectDIR == null) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请先选择生成目标文件夹");
            return false;
        }

        // 获取模板文件
        if (selectVMFile == null) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请先选择vm模板文件");
            return false;
        }

        // 计算目标文件
        return checkFileNameInput();
    }

    /**
     * @return
     */
    public boolean checkFileNameInput() {
        String fileNameT = txt_fileNameTemplate.getText();
        if (fileNameT == null || "".equals(fileNameT.trim())) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "文件名模板不能为空");
            return false;
        }
        return true;
    }

    public boolean checkDBModel() {
        if (dbModel == null || dbModel.getTableList() == null || dbModel.getTableList().isEmpty()) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请先加载数据库表信息");
            return false;
        }
        return true;
    }

    /**
     * 全部生成
     */
    public void generateAllFile() {
        if (!checkDBModel()) {
            return;
        }
        if (!checkInput()) {
            return;
        }

        VelocityEngine ve = getVelocityEngine();
        Template t = null;
        try {
            t = ve.getTemplate(selectVMFile.getAbsolutePath());
        } catch (Exception ex) {
            log.error("获取模板文件错误", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "获取模板文件错误\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<TableEntity> tableList = dbModel.getAllSelectedTable();

        if (tableList.isEmpty()) {
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "请先勾选要生成的表");
            return;
        }

        String fileNameT = txt_fileNameTemplate.getText();

        for (TableEntity te : tableList) {

            // 重新设置tableEntity，去除掉其中被忽略的属性
            TableEntity tableEntity2;
            try {
                tableEntity2 = filterTableEntity(te);
            } catch (Exception e) {
                log.error("对象属性过滤异常，表：" + te.getName(), e);
                JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "对象属性过滤异常，表：" + te.getName(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String fileName;
            // 计算文件名可能会出错
            VelocityContext vct = getVelocityContext(tableEntity2, null);
            try {
                fileName = evaluateFileName(vct, fileNameT);
            } catch (Exception ex) {
                log.error("计算文件异常，表：" + te.getName(), ex);
                JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "计算文件名异常，表：" + te.getName() + "\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File descFile = new File(selectDIR, fileName);

            VelocityContext context = getVelocityContext(tableEntity2, descFile);

            // FileWriter writer = null;
            OutputStreamWriter writer = null;
            boolean b = false;
            try {
                // 指定编码
                writer = new OutputStreamWriter(new FileOutputStream(descFile), cbox_encoding.getSelectedItem().toString());

                // writer = new FileWriter(descFile);
                t.merge(context, writer);
                writer.flush();
                writer.close();
                b = true;

            } catch (Exception ex) {
                log.error("生成代码文件异常，表：" + te.getName(), ex);
                JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "生成代码文件异常，表：" + te.getName() + "\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            } finally {
                if (writer != null && !b)
                    try {
                        writer.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "全部生成成功");
    }

    /**
     * 
     */
    public void testFileName() {
        if (!checkFileNameInput()) {
            return;
        }

        String fileNameT = txt_fileNameTemplate.getText();

        // 应该先加载表信息
        VelocityContext vct;
        if (tableEntity == null) {
            if (!checkDBModel()) {
                return;
            }
            vct = getVelocityContext(dbModel.getTableEntity(0), null);
        } else {
            vct = getVelocityContext(tableEntity, null);
        }

        // 计算文件名可能会出错
        try {
            String fileName = evaluateFileName(vct, fileNameT);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, fileName);
        } catch (Exception ex) {
            log.error("计算文件异常", ex);
            JOptionPane.showMessageDialog(GenerateCodeIFrame.this, "计算文件名异常\n" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 全选
     */
    public void checkAllDBTable() {
        if (dbModel == null || dbModel.getTableList() == null || dbModel.getTableList().isEmpty()) {
            return;
        }
        List<TableEntity> list = dbModel.getTableList();

        for (TableEntity tableEntity : list) {
            tableEntity.setSelected(true);
        }
        dbModel.fireTableDataChanged();
    }

    /**
     * 反选
     */
    public void inverseDBTableSelect() {
        if (dbModel == null || dbModel.getTableList() == null || dbModel.getTableList().isEmpty()) {
            return;
        }
        List<TableEntity> list = dbModel.getTableList();

        for (TableEntity tableEntity : list) {
            tableEntity.setSelected(!tableEntity.isSelected());
        }
        dbModel.fireTableDataChanged();
    }

    /**
     * 
     */
    public void setTotalAndSelected() {
        List<TableEntity> list = dbModel.getTableList();
        int total = list.size();
        int select = 0;
        for (TableEntity tableEntity : list) {
            if (tableEntity.isSelected()) {
                select++;
            }
        }
        txt_totalSize.setText(total + "");
        txt_selectSize.setText(select + "");
    }

}
