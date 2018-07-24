package com.wwh.whwtools.generator.entity;

import com.wwh.whwtools.generator.GenerateHelp;

/**
 * <pre>
 * 列 实体对象
 * </pre>
 *
 * @author wwh
 * @date 2015年8月17日 上午10:10:16
 *
 */
public class ColumnEntity {

    /**
     * 列名
     */
    private String name;

    /**
     * java 属性名
     */
    private String property;

    /**
     * 字段类型
     */
    private String type;

    /**
     * <pre>
     * 对应的java类型
     * 如果：java.lang.String
     * </pre>
     */
    private String javaTypeClass;

    /**
     * COLUMN_SIZE 列表示给定列的指定列大小。对于数值数据，这是最大精度。对于字符数据，这是字符长度。对于日期时间数据类型，这是 String
     * 表示形式的字符长度（假定允许的最大小数秒组件的精度）。对于二进制数据，这是字节长度。对于 ROWID
     * 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
     */
    private int size;

    /**
     * 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
     */
    private int digits;

    /**
     * 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
     */
    private String defaultValue;

    /**
     * 表注释
     */
    private String comments;

    /**
     * 允许为空
     */
    private boolean nullable;

    /**
     * 主键
     */
    private boolean primaryKey;

    /**
     * 唯一
     */
    private boolean unique;

    /**
     * 无符号的
     */
    private boolean unsigned;

    /**
     * 自动增长
     */
    private boolean autoIncrement;

    /**
     * 获取测试值
     * 
     * @return
     */
    public String getTestValue() {
        switch (javaTypeClass) {
        case "java.lang.String":
            if (hasDefaultValue()) {
                return "\"" + getDefaultValue() + "\"";
            } else if (comments != null && comments.length() < size) {
                return "\"" + comments + "\"";
            } else {
                if (name.length() <= size) {
                    return "\"" + name + "\"";
                } else {
                    return "\"" + name.substring(0, size) + "\"";
                }
            }
        case "java.lang.Long":
            if (hasDefaultValue()) {
                return getDefaultValue() + "L";
            } else {
                return "1L";
            }
        case "java.lang.Integer":
            if (hasDefaultValue()) {
                return getDefaultValue();
            } else {
                return "1";
            }
        case "java.math.BigDecimal":
            if (hasDefaultValue()) {
                return "new BigDecimal(" + getDefaultValue() + ")";
            } else {
                return "BigDecimal.ZERO";
            }
        case "java.util.Date":
            return "new Date()";
        case "java.lang.Boolean":
            return "false";

        default:
            break;
        }
        return "null";
    }

    /**
     * 获取对应的java 类 名字
     * 
     * @return
     */
    public String getJavaClassName() {
        return javaTypeClass.substring(javaTypeClass.lastIndexOf(".") + 1);
    }

    /**
     * java set 方法
     * 
     * @return
     */
    public String getJavaSetMethod() {
        return "set" + getUpProperty();
    }

    /**
     * java get 方法
     * 
     * @return
     */
    public String getJavaGetMethod() {
        /*
         * if ("java.lang.Boolean".equals(javaTypeClass)) { return "is" +
         * getUpProperty(); } else { return "get" + getUpProperty(); }
         */
        return "get" + getUpProperty();
    }

    /**
     * 获取首字母大写的属性名
     * 
     * @return
     */
    public String getUpProperty() {
        return property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    /**
     * 是否有默认值
     * 
     * @return
     */
    public boolean hasDefaultValue() {
        if (defaultValue == null || "".equals(defaultValue))
            return false;
        else
            return true;
    }

    /**
     * 获取列名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 设置列名称
     * 根据列名计算java属性名，并赋值
     * </pre>
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.property = GenerateHelp.getPropertyByColumn(name);// 设置属性名称
        this.name = name;
    }

    /**
     * 获取 java属性名
     *
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置 java属性名
     *
     * @param property
     *            the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取 type
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 获取Mybatis中的jdbcType
     * 
     * @return
     */
    public String getMbJdbcType() {
        return GenerateHelp.convert2MybatisJdbcType(type);
    }

    /**
     * 设置 type
     *
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        // 判断是否有 BIGINT
        if (type.endsWith(" UNSIGNED")) {
            this.type = type.substring(0, type.lastIndexOf(" UNSIGNED"));
            this.unsigned = true;
        } else {
            this.type = type;
        }

        setJavaTypeClass(GenerateHelp.getJavaTypeByDBType(this.type));

    }

    /**
     * 获取 javaTypeClass
     *
     * @return the javaTypeClass
     */
    public String getJavaTypeClass() {
        return javaTypeClass;
    }

    /**
     * 设置 javaTypeClass
     *
     * @param javaTypeClass
     *            the javaTypeClass to set
     */
    public void setJavaTypeClass(String javaTypeClass) {
        this.javaTypeClass = javaTypeClass;
    }

    /**
     * 获取 defaultValue
     *
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置 defaultValue
     *
     * @param defaultValue
     *            the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 获取 nullable
     *
     * @return the nullable
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * 设置 nullable
     *
     * @param nullable
     *            the nullable to set
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 获取 comments
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置 comments
     *
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取 primaryKey
     *
     * @return the primaryKey
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * 设置 primaryKey
     *
     * @param primaryKey
     *            the primaryKey to set
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * 获取 unsigned
     *
     * @return the unsigned
     */
    public boolean isUnsigned() {
        return unsigned;
    }

    /**
     * 设置 unsigned
     *
     * @param unsigned
     *            the unsigned to set
     */
    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    /**
     * 获取 autoIncrement
     *
     * @return the autoIncrement
     */
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    /**
     * 设置 autoIncrement
     *
     * @param autoIncrement
     *            the autoIncrement to set
     */
    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    /**
     * 获取 size
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置 size
     *
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取 digits
     *
     * @return the digits
     */
    public int getDigits() {
        return digits;
    }

    /**
     * 设置 digits
     *
     * @param digits
     *            the digits to set
     */
    public void setDigits(int digits) {
        this.digits = digits;
    }

    /**
     * 获取 unique
     *
     * @return the unique
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * 设置 unique
     *
     * @param unique
     *            the unique to set
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    @Override
    public String toString() {
        return "ColumnEntity [name=" + name + ", property=" + property + ", type=" + type + ", size=" + size
                + ", digits=" + digits + ", defaultValue=" + defaultValue + ", comments=" + comments + ", nullable="
                + nullable + ", primaryKey=" + primaryKey + ", unique=" + unique + ", unsigned=" + unsigned
                + ", autoIncrement=" + autoIncrement + "]";
    }
}
