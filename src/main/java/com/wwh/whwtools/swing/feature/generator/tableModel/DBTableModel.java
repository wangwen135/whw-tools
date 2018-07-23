package com.wwh.whwtools.swing.feature.generator.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.wwh.whwtools.generator.entity.TableEntity;

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
        tableList = new ArrayList<TableEntity>();
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
     * 获取所有选择的表
     * 
     * @return
     */
    public List<TableEntity> getAllSelectedTable() {
        List<TableEntity> list = new ArrayList<TableEntity>();
        for (TableEntity te : tableList) {
            if (te.isSelected()) {
                list.add(te);
            }
        }
        return list;
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
        switch (column) {
        case 0:
            return "选择";
        case 1:
            return "表名";

        case 2:
            return "注释";

        default:
            return "";
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
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return true;
        } else
            return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (tableList == null || tableList.size() < rowIndex) {
            return null;
        }

        TableEntity entity = tableList.get(rowIndex);

        switch (columnIndex) {
        case 0:
            return entity.isSelected();
        case 1:
            return entity.getName();

        case 2:
            return entity.getShortRemark();

        default:
            return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (tableList == null || tableList.size() < rowIndex) {
            return;
        }

        TableEntity entity = tableList.get(rowIndex);

        entity.setSelected((Boolean) aValue);
        
        fireTableDataChanged();
    }
}
