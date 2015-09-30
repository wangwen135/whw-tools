/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年9月16日 Created
 */
package com.xingren.swing.comm.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * <pre>
 * 行颜色交替的表格
 * </pre>
 *
 * @author wwh
 * @date 2015年9月16日 下午2:55:57
 *
 */
public class ColorRowTable extends JTable {

    private static final long serialVersionUID = 1L;

    /**
     * 奇数行颜色
     */
    private Color oddRowColor = Color.WHITE;

    /**
     * 偶数行颜色
     */
    private Color evenRowColor = new Color(237, 243, 253);

    public ColorRowTable() {
        super();
        initTable();
    }

    public ColorRowTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
        initTable();
    }

    public ColorRowTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        initTable();
    }

    public ColorRowTable(TableModel dm) {
        super(dm);
        initTable();
    }

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     * @param oddRowColor
     *            奇数行颜色
     * @param evenRowColor
     *            偶数行颜色
     */
    public ColorRowTable(Color oddRowColor, Color evenRowColor) {
        super();
        this.oddRowColor = oddRowColor;
        this.evenRowColor = evenRowColor;
        initTable();
    }

    /**
     * 初始化表格
     */
    private void initTable() {
        setRowHeight(22);
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        setColumnSelectionAllowed(true);
        setCellSelectionEnabled(true);
        setAutoCreateRowSorter(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component cellComp = super.prepareRenderer(renderer, row, column);
        if (!isCellSelected(row, column))
            if (row % 2 == 0) {
                cellComp.setBackground(evenRowColor);
            } else {
                cellComp.setBackground(oddRowColor);
            }
        return cellComp;
    }

    /**
     * 获取 oddRowColor
     *
     * @return the oddRowColor
     */
    public Color getOddRowColor() {
        return oddRowColor;
    }

    /**
     * 设置 oddRowColor
     *
     * @param oddRowColor
     *            the oddRowColor to set
     */
    public void setOddRowColor(Color oddRowColor) {
        this.oddRowColor = oddRowColor;
    }

    /**
     * 获取 evenRowColor
     *
     * @return the evenRowColor
     */
    public Color getEvenRowColor() {
        return evenRowColor;
    }

    /**
     * 设置 evenRowColor
     *
     * @param evenRowColor
     *            the evenRowColor to set
     */
    public void setEvenRowColor(Color evenRowColor) {
        this.evenRowColor = evenRowColor;
    }

}
