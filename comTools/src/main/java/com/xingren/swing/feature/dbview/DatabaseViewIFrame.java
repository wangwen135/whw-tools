/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月19日 Created
 */
package com.xingren.swing.feature.dbview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xingren.generate.DBConnectionGetter;
import com.xingren.generate.DBMetaDataGetter;
import com.xingren.generate.entity.ColumnEntity;
import com.xingren.generate.entity.TableEntity;
import com.xingren.generate.impl.MySqlConnectionGetter;
import com.xingren.swing.comm.GlassPanel;
import com.xingren.swing.comm.MySwingWorker;
import com.xingren.swing.comm.table.ColorRowTable;
import com.xingren.swing.feature.dbview.tableModel.DBTableModel;
import com.xingren.swing.feature.dbview.tableModel.TableTableModel;
import com.xingren.swing.frame.BaseJInternalFrame;

/**
 * <pre>
 * 浏览数据库
 * </pre>
 *
 * @author wwh
 * @date 2015年8月19日 上午10:24:22
 *
 */
public class DatabaseViewIFrame extends BaseJInternalFrame {

    private static final Logger log = LoggerFactory.getLogger(DatabaseViewIFrame.class);

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

    private GlassPanel glassPanel;

    private JLabel lbl_totalSize;

    public DatabaseViewIFrame() {
        super();
        setTitle("浏览数据库");
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

        JLabel label = new JLabel("地址");
        panel_conInfo.add(label);

        txt_host = new JTextField();
        txt_host.setText("192.168.1.110");
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
        txt_dbName.setText("sdb");
        panel_conInfo.add(txt_dbName);
        txt_dbName.setColumns(10);

        JLabel label_3 = new JLabel("用户名");
        panel_conInfo.add(label_3);

        txt_userName = new JTextField();
        txt_userName.setText("sdb");
        panel_conInfo.add(txt_userName);
        txt_userName.setColumns(12);

        JLabel label_4 = new JLabel("密码");
        panel_conInfo.add(label_4);

        txt_pwd = new JPasswordField();
        txt_pwd.setColumns(12);
        panel_conInfo.add(txt_pwd);
        txt_pwd.setText("123456");

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

        JLabel label_5 = new JLabel("匹配");
        GridBagConstraints gbc_label_5 = new GridBagConstraints();
        gbc_label_5.insets = new Insets(0, 0, 0, 5);
        gbc_label_5.gridx = 0;
        gbc_label_5.gridy = 0;
        panel_left_top.add(label_5, gbc_label_5);

        txt_tablePattern = new JTextField();
        txt_tablePattern.setText("%");
        txt_tablePattern.setColumns(14);
        GridBagConstraints gbc_txt_tablePattern = new GridBagConstraints();
        gbc_txt_tablePattern.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tablePattern.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tablePattern.weightx = 1;
        gbc_txt_tablePattern.gridx = 1;
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
        gbc_btn_refresh.gridx = 2;
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
                setTotalSize();
            }
        });

        table_db.setModel(dbModel);

        setDBTableColumnPreferredWidth();

        JPanel panel_left_bottom = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_left_bottom.getLayout();
        flowLayout.setVgap(1);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_left.add(panel_left_bottom, BorderLayout.SOUTH);

        JLabel label_8 = new JLabel("总共：");
        panel_left_bottom.add(label_8);

        lbl_totalSize = new JLabel("");
        panel_left_bottom.add(lbl_totalSize);

        JPanel panel_right = new JPanel();
        splitPane.setRightComponent(panel_right);
        panel_right.setLayout(new BorderLayout(0, 0));

        JPanel panel_right_top = new JPanel();
        panel_right_top.setPreferredSize(new Dimension(10, 28));
        panel_right.add(panel_right_top, BorderLayout.NORTH);

        GridBagLayout gbl_panel_right_top = new GridBagLayout();
        panel_right_top.setLayout(gbl_panel_right_top);

        JLabel label_6 = new JLabel("表名");
        GridBagConstraints gbc_label_6 = new GridBagConstraints();
        gbc_label_6.insets = new Insets(0, 0, 0, 5);
        gbc_label_6.gridx = 0;
        gbc_label_6.gridy = 0;
        panel_right_top.add(label_6, gbc_label_6);

        txt_tableName = new JTextField();
        txt_tableName.setColumns(25);
        GridBagConstraints gbc_txt_tableName = new GridBagConstraints();
        gbc_txt_tableName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tableName.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tableName.gridx = 1;
        gbc_txt_tableName.gridy = 0;
        panel_right_top.add(txt_tableName, gbc_txt_tableName);

        JLabel label_7 = new JLabel("注释");
        GridBagConstraints gbc_label_7 = new GridBagConstraints();
        gbc_label_7.insets = new Insets(0, 0, 0, 5);
        gbc_label_7.gridx = 2;
        gbc_label_7.gridy = 0;
        panel_right_top.add(label_7, gbc_label_7);

        txt_tableRemark = new JTextField();
        GridBagConstraints gbc_txt_tableRemark = new GridBagConstraints();
        gbc_txt_tableRemark.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_tableRemark.insets = new Insets(0, 0, 0, 5);
        gbc_txt_tableRemark.weightx = 1;
        gbc_txt_tableRemark.gridx = 3;
        gbc_txt_tableRemark.gridy = 0;
        panel_right_top.add(txt_tableRemark, gbc_txt_tableRemark);

        JScrollPane scrollPane_table = new JScrollPane();
        panel_right.add(scrollPane_table, BorderLayout.CENTER);

        table_table = new ColorRowTable();
        // table_table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // table_table.setColumnSelectionAllowed(true);
        // table_table.setCellSelectionEnabled(true);
        // table_table.setAutoCreateRowSorter(true);
        // table_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane_table.setViewportView(table_table);

        tableModel = new TableTableModel();
        table_table.setModel(tableModel);

        setCenterTableColumnPreferredWidth();

        // ############ 设置玻璃面板
        glassPanel = new GlassPanel();

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
        table_db.getColumnModel().getColumn(0).setPreferredWidth(115);
        table_db.getColumnModel().getColumn(1).setPreferredWidth(115);
    }

    /**
     * 获取连接
     */
    public void getDBConnection() {
        String host = txt_host.getText();
        if (host == null || "".equals(host)) {
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "地址不能为空");
            txt_host.requestFocus();
            return;
        }
        Integer port = (Integer) ftxt_port.getValue();
        if (port == null) {
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "端口不能为空");
            ftxt_port.requestFocus();
            return;
        }

        String dbName = txt_dbName.getText();
        if (dbName == null || "".equals(dbName)) {
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "数据库名不能为空");
            txt_dbName.requestFocus();
            return;
        }

        String userName = txt_userName.getText();
        if (userName == null || "".equals(userName)) {
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "用户名不能为空");
            txt_userName.requestFocus();
            return;
        }

        String pwd = new String(txt_pwd.getPassword());
        if (pwd == null || "".equals(pwd)) {
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "密码不能为空");
            txt_pwd.requestFocus();
            return;
        }

        try {
            connGetter = new MySqlConnectionGetter(host, port, dbName, userName, pwd);

        } catch (Exception ex) {
            log.error("连接数据库异常", ex);
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, ex.getMessage(), "连接数据库异常\n" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "请连接数据库");
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
                    JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "获取数据库表信息异常", "错误", JOptionPane.ERROR_MESSAGE);
                } finally {
                    glassPanel.setVisible(false);// 关闭玻璃面板
                }
            }
        };
        sWorker.execute();

    }

    public void clearSelectTable() {
        // tableEntity = null;
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

            TableEntity tableEntity = dbModel.getTableEntity(rowIndex);
            txt_tableName.setText(tableEntity.getName());
            txt_tableRemark.setText(tableEntity.getRemark());

            List<ColumnEntity> colList = tableEntity.getColumns();

            tableModel.setColumnList(colList);
            tableModel.fireTableDataChanged();
        }
    }

    public void setTotalSize() {

        lbl_totalSize.setText(dbModel.getRowCount() + "");
    }

    @Override
    protected void frameClosed() {
        closeDBConnection();
    }

    @Override
    protected void findInFrame() {
        JOptionPane.showMessageDialog(DatabaseViewIFrame.this, "查找 ");
        //查找--考虑一下看是否可以做出通用的？？
        
        //可以由一个统一的地方弹出查找窗口，然后返回一个查找对象，回调当前活动的窗口，具体动作由回调窗口决定
    }
}
