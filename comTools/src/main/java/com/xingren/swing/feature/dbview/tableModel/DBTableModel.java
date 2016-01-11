/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月19日 Created
 */
package com.xingren.swing.feature.dbview.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xingren.generate.entity.TableEntity;

/**
 * <pre>
 * 数据库表
 * </pre>
 *
 * @author wwh
 * @date 2015年8月19日 上午11:18:52
 *
 */
public class DBTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<TableEntity> tableList;

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public DBTableModel() {

    }

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public DBTableModel(List<TableEntity> tableEntitys) {
        this.tableList = tableEntitys;

    }

    /**
     * 获取 tableList
     *
     * @return the tableList
     */
    public List<TableEntity> getTableList() {
        return tableList;
    }

    /**
     * 设置 tableList
     *
     * @param tableList
     *            the tableList to set
     */
    public void setTableList(List<TableEntity> tableList) {
        this.tableList = tableList;
    }

    public TableEntity getTableEntity(int index) {
        if (tableList == null)
            return null;

        return tableList.get(index);
    }

    // 表格中的列定义
    // 表名 注释 类名
    private static final String[] columns = new String[] { "表名", "注释", "对应类名" };

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        if (tableList == null) {
            return 0;
        } else {
            return tableList.size();
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (tableList == null) {
            return null;
        }
        if (tableList.size() < rowIndex) {
            return null;
        }

        TableEntity entity = tableList.get(rowIndex);

        switch (columnIndex) {
        case 0:
            return entity.getName();
        case 1:
            return entity.getShortRemark();
        case 2:
            return entity.getClassName();

        default:
            return null;
        }
    }

}
