package com.wwh.whwtools.generate.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.wwh.whwtools.generate.GenerateHelp;

/**
 * <pre>
 * 表 实体对象
 * </pre>
 *
 * @author wwh
 * @date 2015年8月17日 上午9:58:51
 *
 */
public class TableEntity implements Cloneable {

    /**
     * 选择
     */
    private boolean selected = true;

    /**
     * 表名
     */
    private String name;

    /**
     * java 类名
     */
    private String className;

    /**
     * 表注释
     */
    private String remark;

    /**
     * 短注释
     */
    private String shortRemark;

    /**
     * 列
     */
    private List<ColumnEntity> columns;

    // ###################################################################

    /**
     * 对象克隆
     */
    public TableEntity clone() throws CloneNotSupportedException {
        TableEntity te = (TableEntity) super.clone();
        if (columns != null) {
            List<ColumnEntity> list = new ArrayList<ColumnEntity>();
            list.addAll(getColumns());
            te.setColumns(list);
        }
        return te;
    }

    /**
     * 获取需要导入的java类型
     * 
     * @return 如：java.util.Date java.math.BigDecimal
     */
    public Set<String> getImportClass() {
        Set<String> set = new TreeSet<String>();
        for (ColumnEntity c : columns) {
            String type = c.getJavaTypeClass();
            if (type != null && !type.startsWith("java.lang.")) {
                set.add(type);
            }
        }

        return set;
    }

    /**
     * 移除列
     * 
     * @param col
     *            列名 或者 属性名
     */
    public void removeColumn(String col) {
        if (col == null || "".equals(col)) {
            return;
        }
        for (int i = 0; i < columns.size(); i++) {
            ColumnEntity c = columns.get(i);
            if (col.equals(c.getName()) || col.equals(c.getProperty())) {
                columns.remove(i);
                return;
            }
        }

    }

    /**
     * 获取首字母小写的类名称
     * 
     * @return
     */
    public String getLowerClassName() {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    /**
     * 获取主键--针对只有一个主键的情况
     * 
     * @return
     */
    public ColumnEntity getPrimaryKey() {
        for (ColumnEntity ce : columns) {
            if (ce.isPrimaryKey()) {
                return ce;
            }
        }
        return null;
    }

    /**
     * 获取主键集合
     * 
     * @return
     */
    public List<ColumnEntity> getPrimaryKeys() {
        List<ColumnEntity> list = new ArrayList<ColumnEntity>();
        for (ColumnEntity ce : columns) {
            if (ce.isPrimaryKey()) {
                list.add(ce);
            }
        }
        return list;
    }

    /**
     * 获取非主键列
     * 
     * @return
     */
    public List<ColumnEntity> getNotPkColumns() {
        List<ColumnEntity> list = new ArrayList<ColumnEntity>();
        for (ColumnEntity ce : columns) {
            if (!ce.isPrimaryKey()) {
                list.add(ce);
            }
        }
        return list;
    }

    /**
     * 获取列数
     *
     * @return the columnCount
     */
    public int getColumnCount() {

        return columns.size();
    }

    /**
     * 根据索引获取列
     * 
     * @param index
     * @return
     */
    public ColumnEntity getColumn(int index) {
        return columns.get(index);
    }

    /**
     * 根据名字获取列
     * 
     * @param name
     * @return
     */
    public ColumnEntity getColumnByName(String name) {
        for (ColumnEntity column : columns) {
            if (column.getName().equals(name)) {
                return column;
            }
        }
        return null;
    }

    // ###################################################################

    @Override
    public String toString() {
        String s = "Table [name=" + name + ", remark=" + remark + ", shortRemark=" + shortRemark + ", columnCount=" + getColumnCount() + ", columns：";
        for (ColumnEntity column : columns) {
            s += "\n";
            s += column.toString();
        }
        s += "]";

        return s;
    }

    /**
     * 获取 name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     *
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.className = GenerateHelp.getClassNameByTableName(name);
        this.name = name;
    }

    /**
     * 获取 remark
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        if (remark != null && !"".equals(remark)) {
            String shortRemark2 = remark.split("[（(\r\n]")[0];
            if ("".equals(shortRemark2)) {
                shortRemark2 = remark.split("[\r\n]")[0];
            }
            setShortRemark(shortRemark2);
        }
        this.remark = remark;
    }

    /**
     * 获取 shortRemark
     *
     * @return the shortRemark
     */
    public String getShortRemark() {
        return shortRemark;
    }

    /**
     * 设置 shortRemark
     *
     * @param shortRemark
     *            the shortRemark to set
     */
    public void setShortRemark(String shortRemark) {
        this.shortRemark = shortRemark;
    }

    /**
     * 获取 columns
     *
     * @return the columns
     */
    public List<ColumnEntity> getColumns() {
        return columns;
    }

    /**
     * 设置 columns
     *
     * @param columns
     *            the columns to set
     */
    public void setColumns(List<ColumnEntity> columns) {
        columns = columns == null ? new ArrayList<ColumnEntity>() : columns;
        this.columns = columns;
    }

    /**
     * 获取 className
     *
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置 className
     *
     * @param className
     *            the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取 selected
     *
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 设置 selected
     *
     * @param selected
     *            the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
