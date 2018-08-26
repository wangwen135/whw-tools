package com.wwh.whwtools.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 代码生成器常量类
 * </pre>
 * 
 * @author wwh
 * @date 2018年7月24日 上午12:42:48
 */
public class GeneratorConstant {

    /**
     * <pre>
     * 默认的表名分隔符
     * 大部分情况下约定使用下划线作为分隔符
     * 如表名：wh_user   wh_order
     * 大部分情况下会处理成类名：WhUser   WhOrder
     * </pre>
     */
    public static final String DEFAULT_TABLE_NAME_SEPARATOR = "_";

    /**
     * <pre>
     * 默认表字段的分隔符
     * 大部分情况下约定使用下划线作为分隔符
     * 如字段名：user_id   user_name
     * 大部分情况下会处理成属性名：userId   userName
     * </pre>
     */
    public static final String DEFAULT_TABLE_COLUMN_NAME_SEPARATOR = "_";

    /**
     * <pre>
     * 中线分隔符 '-'
     * 
     * </pre>
     */
    public static final String SEPARATOR_MIDLINE = "-";

    /**
     * <pre>
     * 数据库类型
     * mysql
     * </pre>
     */
    public static enum DBType {
        /**
         * MySQL数据库
         */
        MYSQL
    }

    /**
     * <pre>
     * 数据库类型到java类型映射
     * 应该需要支持从界面上进行配置
     * </pre>
     */
    public static final Map<String, String> DB_TO_JAVA_MAPPING = new HashMap<String, String>();
    static {
        DB_TO_JAVA_MAPPING.put("VARCHAR", "java.lang.String");
        DB_TO_JAVA_MAPPING.put("CHAR", "java.lang.String");
        DB_TO_JAVA_MAPPING.put("BIGINT", "java.lang.Long");
        DB_TO_JAVA_MAPPING.put("INT", "java.lang.Integer");
        DB_TO_JAVA_MAPPING.put("TINYINT", "java.lang.Integer");
        DB_TO_JAVA_MAPPING.put("SMALLINT", "java.lang.Integer");
        DB_TO_JAVA_MAPPING.put("MEDIUMINT", "java.lang.Integer");
        DB_TO_JAVA_MAPPING.put("DECIMAL", "java.math.BigDecimal");
        DB_TO_JAVA_MAPPING.put("DATETIME", "java.util.Date");
        DB_TO_JAVA_MAPPING.put("DATE", "java.util.Date");
        DB_TO_JAVA_MAPPING.put("TIMESTAMP", "java.util.Date");
        DB_TO_JAVA_MAPPING.put("BIT", "java.lang.Boolean");
        DB_TO_JAVA_MAPPING.put("FLOAT", "java.lang.Float");
        DB_TO_JAVA_MAPPING.put("DOUBLE", "java.lang.Double");
        DB_TO_JAVA_MAPPING.put("BLOB", "java.lang.byte[]");
        DB_TO_JAVA_MAPPING.put("TEXT", "java.lang.String");
        DB_TO_JAVA_MAPPING.put("LONGTEXT", "java.lang.String");
        DB_TO_JAVA_MAPPING.put("MEDIUMTEXT", "java.lang.String");
    }

    /**
     * <pre>
     * 数据库类型到Mybatis jdbcType 映射
     * 
     * </pre>
     */
    public static final Map<String, String> DB_TO_MYBATIS_MAPPING = new HashMap<String, String>();
    static {
        DB_TO_MYBATIS_MAPPING.put("INT", "INTEGER");
        DB_TO_MYBATIS_MAPPING.put("DATETIME", "TIMESTAMP");
        DB_TO_MYBATIS_MAPPING.put("DATETIME", "TIMESTAMP");
        DB_TO_MYBATIS_MAPPING.put("TEXT", "VARCHAR");
    }
}
