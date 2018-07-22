package com.wwh.whwtools.swing.comm.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年9月16日 下午2:38:44 
 *
 */
public class CrossTableCellRenderer extends DefaultTableCellRenderer{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComp= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row%2==0){
            cellComp.setBackground(getBackground().darker());
        }else{
            cellComp.setBackground(getBackground().brighter());
        }
        
        return cellComp;
    }

}
