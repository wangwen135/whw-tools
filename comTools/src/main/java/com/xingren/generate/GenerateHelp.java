/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月15日 Created
 */
package com.xingren.generate;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 辅助类
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午6:41:46
 *
 */
public class GenerateHelp {
    /**
     * <pre>
     * 数据库类型
     * mysql
     * </pre>
     */
    public static enum DBType {
        MYSQL
    }

    public static final Map<String, String> typeMap = new HashMap<String, String>();
    static {
        typeMap.put("VARCHAR", "java.lang.String");
        typeMap.put("CHAR", "java.lang.String");
        typeMap.put("BIGINT", "java.lang.Long");
        typeMap.put("INT", "java.lang.Integer");
        typeMap.put("TINYINT", "java.lang.Integer");
        typeMap.put("SMALLINT", "java.lang.Integer");
        typeMap.put("MEDIUMINT", "java.lang.Integer");
        typeMap.put("DECIMAL", "java.math.BigDecimal");
        
        typeMap.put("DATETIME", "java.util.Date");
        typeMap.put("DATE", "java.util.Date");
        // ######
        typeMap.put("TIMESTAMP", "java.util.Date");
        typeMap.put("BIT", "java.lang.Boolean");
        typeMap.put("FLOAT", "java.lang.Float");
        typeMap.put("DOUBLE", "java.lang.Double");
        typeMap.put("BLOB", "java.lang.byte[]");
    }

    /**
     * 根据数据库类型 获取 java 类型
     * 
     * @param dbType
     * @return
     */
    public static String getJavaTypeByDBType(String dbType) {

        if (dbType == null || "".equals(dbType)) {
            throw new IllegalArgumentException("dbType 不能为空");
        }
        dbType = dbType.toUpperCase();
        String javaClass = typeMap.get(dbType);
        if (javaClass == null)
            throw new IllegalArgumentException("错误或不支持的数据库类型：" + dbType);
        else
            return javaClass;
    }

    public static void main(String[] args) {
        System.out.println(getJavaTypeByDBType("varchar"));
    }

    /**
     * 根据表名获取java类名
     * 
     * @param tableName
     * @return
     */
    public static String getClassNameByTableName(String tableName) {
        if (tableName == null)
            throw new IllegalArgumentException("表名不能为空");

        String[] cs = tableName.split("_");
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < cs.length; i++) {
            String c = cs[i];
            if (!"".equals(c)) {
                sbf.append(c.substring(0, 1).toUpperCase());
                sbf.append(c.substring(1));
            }
        }
        return sbf.toString();
    }

    /**
     * 根据字段名获取属性名
     * 
     * @return
     */
    public static String getPropertyByColumn(String column) {
        if (column == null)
            throw new IllegalArgumentException("字段不能为空");

        String[] cs = column.split("_");
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < cs.length; i++) {
            if (i == 0) {
                sbf.append(cs[0]);
            } else {
                String c = cs[i];
                if (!"".equals(c)) {
                    sbf.append(c.substring(0, 1).toUpperCase());
                    sbf.append(c.substring(1));
                }
            }
        }
        return sbf.toString();
    }

}
