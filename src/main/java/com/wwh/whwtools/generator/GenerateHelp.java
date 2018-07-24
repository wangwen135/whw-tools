package com.wwh.whwtools.generator;

import java.util.List;

import com.wwh.whwtools.generator.entity.ColumnEntity;
import com.wwh.whwtools.generator.entity.TableEntity;

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
        String mbType = GeneratorConstant.DB_TO_MYBATIS_MAPPING.get(dbType);
        return mbType == null ? dbType : mbType;
    }

    /**
     * 根据数据库类型 获取 java 类型 <br/>
     * 通过映射表实现，如果没有配置映射就返回数据库中定义的type
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
        String javaClass = GeneratorConstant.DB_TO_MYBATIS_MAPPING.get(dbType);
        if (javaClass == null) {
            // 可以考虑返回Object？
            throw new IllegalArgumentException("错误或不支持的数据库类型：" + dbType);
        } else {
            return javaClass;
        }
    }

    /**
     * <pre>
     * 根据表名获取java类名
     * 如：
     * user  -->  User
     * user_group  -->  userGroup
     * </pre>
     * 
     * @param tableName
     * @return
     */
    public static String getClassNameByTableName(String tableName) {
        if (tableName == null)
            throw new IllegalArgumentException("表名不能为空");

        String[] cs = tableName.split(GeneratorConstant.DEFAULT_TABLE_NAME_SEPARATOR);
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
     * <pre>
     * 根据字段名获取属性名
     * 如：
     * user_id  -->  userId
     * user_group_id  -->  userGroupId
     * </pre>
     * 
     * @return
     */
    public static String getPropertyByColumn(String column) {
        if (column == null)
            throw new IllegalArgumentException("字段不能为空");

        String[] cs = column.split(GeneratorConstant.DEFAULT_TABLE_COLUMN_NAME_SEPARATOR);
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

    /**
     * 移除表名前缀<br/>
     * 只处理ClassName
     * 
     * @param tableEntity
     * @param prefix
     * @return 如果包含前缀返回的是克隆后的对象
     * @throws Exception
     */
    public static TableEntity removeTablePrefix(TableEntity tableEntity, String prefix) throws Exception {
        if (prefix != null && !"".equals(prefix)) {
            String name = tableEntity.getName();
            // 全部转大写后匹配
            if (name.toUpperCase().startsWith(prefix.toUpperCase())) {
                String newName = name.substring(prefix.length());
                if (newName.startsWith("_")) {
                    newName = newName.substring(1);
                }
                TableEntity tableEntity2 = tableEntity.clone();
                tableEntity2.setClassName(GenerateHelp.getClassNameByTableName(newName));
                return tableEntity2;
            }
        }
        return tableEntity;
    }

    /**
     * 移除字段前缀<br/>
     * 处理所有字段的Property属性
     * 
     * @param tableEntity
     * @param prefix
     * @return 返回克隆后经过处理的Entity对象
     * @throws Exception
     */
    public static TableEntity removeTableColumnPrefxi(TableEntity tableEntity, String prefix) throws Exception {
        if (prefix != null && !"".equals(prefix)) {
            TableEntity te2 = tableEntity.clone();
            List<ColumnEntity> clist = te2.getColumns();
            for (ColumnEntity ce : clist) {
                String colName = ce.getName();
                // 全部转大写后匹配
                if (colName.toUpperCase().startsWith(prefix.toUpperCase())) {
                    String newName = colName.substring(prefix.length());
                    if (newName.startsWith("_")) {
                        newName = newName.substring(1);
                    }
                    ce.setProperty(GenerateHelp.getPropertyByColumn(newName));
                }
            }
            return te2;
        }
        return tableEntity;
    }

}
