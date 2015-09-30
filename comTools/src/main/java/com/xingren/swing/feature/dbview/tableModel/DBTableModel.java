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

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "表名";
        } else {
            return "注释";
        }
    }
    
    public int getRowCount() {
        if (tableList == null) {
            return 0;
        } else {
            return tableList.size();
        }
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (tableList == null) {
            return null;
        }
        if (tableList.size() < rowIndex) {
            return null;
        }

        TableEntity entity = tableList.get(rowIndex);
        if (columnIndex == 0) {
            return entity.getName();
        } else {
            return entity.getShortRemark();
        }
    }

}
