package com.wwh.whwtools.generate;

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

	/**
	 * 数据库类型到java类型映射
	 */
	public static final Map<String, String> javaTypeMap = new HashMap<String, String>();
	static {
		javaTypeMap.put("VARCHAR", "java.lang.String");
		javaTypeMap.put("CHAR", "java.lang.String");
		javaTypeMap.put("BIGINT", "java.lang.Long");
		javaTypeMap.put("INT", "java.lang.Integer");
		javaTypeMap.put("TINYINT", "java.lang.Integer");
		javaTypeMap.put("SMALLINT", "java.lang.Integer");
		javaTypeMap.put("MEDIUMINT", "java.lang.Integer");
		javaTypeMap.put("DECIMAL", "java.math.BigDecimal");

		javaTypeMap.put("DATETIME", "java.util.Date");
		javaTypeMap.put("DATE", "java.util.Date");
		// ######
		javaTypeMap.put("TIMESTAMP", "java.util.Date");
		javaTypeMap.put("BIT", "java.lang.Boolean");
		javaTypeMap.put("FLOAT", "java.lang.Float");
		javaTypeMap.put("DOUBLE", "java.lang.Double");
		javaTypeMap.put("BLOB", "java.lang.byte[]");
		javaTypeMap.put("TEXT", "java.lang.String");
		javaTypeMap.put("LONGTEXT", "java.lang.String");
	}

	/**
	 * 数据库类型到Mybatis jdbcType 映射
	 */
	public static final Map<String, String> mybatisJdbcTypeMap = new HashMap<String, String>();
	static {
		mybatisJdbcTypeMap.put("INT", "INTEGER");
		mybatisJdbcTypeMap.put("DATETIME", "TIMESTAMP");
		mybatisJdbcTypeMap.put("DATETIME", "TIMESTAMP");
		mybatisJdbcTypeMap.put("TEXT", "VARCHAR");
	}

	/**
	 * 将数据库中的类型 转换成 Mybatis中的jdbcType
	 * 
	 * @param dbType
	 *            数据库中类型
	 * @return Mybatis中的jdbcType
	 */
	public static String convert2MybatisJdbcType(String dbType) {
		if (dbType == null || "".equals(dbType)) {
			return dbType;
		}
		dbType = dbType.toUpperCase();
		String mbType = mybatisJdbcTypeMap.get(dbType);
		return mbType == null ? dbType : mbType;
	}

	/**
	 * 根据数据库类型 获取 java 类型
	 * 
	 * @param dbType
	 *            数据库类型
	 * @return 对应的java类型
	 */
	public static String getJavaTypeByDBType(String dbType) {

		if (dbType == null || "".equals(dbType)) {
			throw new IllegalArgumentException("dbType 不能为空");
		}
		dbType = dbType.toUpperCase();
		String javaClass = javaTypeMap.get(dbType);
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
