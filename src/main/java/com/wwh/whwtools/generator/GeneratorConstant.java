package com.wwh.whwtools.generator;

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
}
