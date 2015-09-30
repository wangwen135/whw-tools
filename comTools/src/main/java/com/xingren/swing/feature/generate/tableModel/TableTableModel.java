/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月19日 Created
 */
package com.xingren.swing.feature.generate.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xingren.generate.entity.ColumnEntity;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月19日 下午12:39:38
 *
 */
public class TableTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<ColumnEntity> columnList;

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public TableTableModel() {
    }

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     * @param columnList
     */
    public TableTableModel(List<ColumnEntity> columnList) {
        this.columnList = columnList;
    }

    /**
     * 获取 columnList
     *
     * @return the columnList
     */
    public List<ColumnEntity> getColumnList() {
        return columnList;
    }

    /**
     * 设置 columnList
     *
     * @param columnList
     *            the columnList to set
     */
    public void setColumnList(List<ColumnEntity> columnList) {
        this.columnList = columnList;
    }

    public int getRowCount() {
        if (columnList != null) {
            return columnList.size();
        }
        return 0;
    }

    // 表格中的列定义
    // 列名 类型 默认值 可为空 主键 唯一 注释
    private static final String[] columns = new String[] { "序号", "列名", "类型", "默认值", "可为空", "主键", "唯一", "自增", "注释" };

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Integer.class;
        case 4:
        case 5:
        case 6:
        case 7:
            return Boolean.class;
        default:
            return String.class;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnList == null || columnList.isEmpty()) {
            return null;
        }

        ColumnEntity entity = columnList.get(rowIndex);

        if (entity == null)
            return null;

        switch (columnIndex) {
        case 0:
            return rowIndex + 1;
        case 1:
            return entity.getName();

        case 2:
            // 类型(长度,小数) 无符号

            StringBuffer sbf = new StringBuffer(entity.getType());
            sbf.append("(");
            sbf.append(entity.getSize());
            // 判断是否有小数位
            if (entity.getDigits() > 0) {
                sbf.append(",");
                sbf.append(entity.getDigits());
            }
            sbf.append(")");

            if (entity.isUnsigned()) {
                sbf.append(" UNSIGNED");
            }

            return sbf.toString();

        case 3:
            return entity.getDefaultValue();
        case 4:
            return entity.isNullable();
            // 列名 类型 默认值 可为空 主键 唯一 注释
        case 5:
            return entity.isPrimaryKey();

        case 6:
            return entity.isUnique();

        case 7:
            return entity.isAutoIncrement();

        case 8:
            return entity.getComments();

        default:
            break;
        }

        return null;
    }

}
