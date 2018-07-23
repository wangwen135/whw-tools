package com.wwh.whwtools.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.generator.entity.ColumnEntity;
import com.wwh.whwtools.generator.entity.TableEntity;

/**
 * <pre>
 * 数据库元数据获取者
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午5:54:18
 *
 */
public class DBMetaDataGetter {

    private static final Logger log = LoggerFactory.getLogger(DBMetaDataGetter.class);

    private DBConnectionGetter connGetter;

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public DBMetaDataGetter(DBConnectionGetter getter) {
        this.connGetter = getter;
    }

    /**
     * 获取表信息
     * 
     * @param tableNamePattern
     *            表名匹配模式，支持sql通配符
     * @param types
     *            TABLE VIEW
     * @return
     * @throws Exception
     */
    public List<TableEntity> getTables(String tableNamePattern, String[] types) throws Exception {
        return getTables(tableNamePattern, types, null);
    }

    /**
     * 获取表信息
     * 
     * @param tableNamePattern
     *            表名匹配模式，支持sql通配符
     * @param types
     *            TABLE VIEW
     * @param publishM
     * @return
     * @throws Exception
     */
    public List<TableEntity> getTables(String tableNamePattern, String[] types, MessagePublisher publishM) throws Exception {
        Connection conn = connGetter.getConnection();

        DatabaseMetaData condmd = conn.getMetaData();
        String catalog = conn.getCatalog();
        String schemaPattern = connGetter.getUserName();
        types = types == null ? new String[] { "TABLE", "VIEW" } : types;
        tableNamePattern = tableNamePattern == null ? "%" : tableNamePattern;
        // // 获取数据库表信息
        if (publishM != null)
            publishM.publishMsg("正在获取数据库表信息 ...");

        ResultSet tableRet = condmd.getTables(catalog, schemaPattern, tableNamePattern, types);

        List<TableEntity> list = new ArrayList<TableEntity>();

        TableEntity table;
        while (tableRet.next()) {
            table = new TableEntity();
            String tableName = tableRet.getString("TABLE_NAME");
            if (publishM != null)
                publishM.publishMsg("加载表 " + tableName + " ...");

            log.debug("加载表{} ...", tableName);
            table.setName(tableName);
            String remark = tableRet.getString("REMARKS");
            table.setRemark(remark);

            List<ColumnEntity> colList = new ArrayList<ColumnEntity>();

            ResultSet colRet = condmd.getColumns(catalog, schemaPattern, tableName, null);
            ColumnEntity col;
            while (colRet.next()) {
                col = new ColumnEntity();
                col.setName(colRet.getString("COLUMN_NAME"));// 列名称

                col.setType(colRet.getString("TYPE_NAME"));// 数据源依赖的类型名称，对于UDT，该类型名称是完全限定的

                col.setSize(colRet.getInt("COLUMN_SIZE"));// 列的大小

                col.setDigits(colRet.getInt("DECIMAL_DIGITS"));// 小数部分的位数。对于DECIMAL_DIGITS不适用的数据类型，则返回Null

                col.setNullable(colRet.getInt("NULLABLE") == DatabaseMetaData.columnNullable);// 明确允许使用NULL值

                col.setComments(colRet.getString("REMARKS"));// 注释

                col.setDefaultValue(colRet.getString("COLUMN_DEF"));// 该列的默认值，当值在单引号内时应被解释为一个字符串（可为null）

                // 指示此列是否自动增加
                col.setAutoIncrement("YES".equals(colRet.getString("IS_AUTOINCREMENT")));

                colList.add(col);

            }
            table.setColumns(colList);

            if (publishM != null)
                publishM.publishMsg("获取 " + tableName + " 主键信息 ...");

            // 主键
            ResultSet pkRet = condmd.getPrimaryKeys(catalog, schemaPattern, tableName);
            while (pkRet.next()) {
                String pkCol = pkRet.getString("COLUMN_NAME");
                ColumnEntity column = table.getColumnByName(pkCol);
                column.setPrimaryKey(true);
            }
            if (publishM != null)
                publishM.publishMsg("获取 " + tableName + " 唯一约束信息 ...");

            // 唯一
            ResultSet uniqueRet = condmd.getIndexInfo(catalog, schemaPattern, tableName, true, false);
            while (uniqueRet.next()) {
                String uqCol = uniqueRet.getString("COLUMN_NAME");
                ColumnEntity column = table.getColumnByName(uqCol);
                column.setUnique(true);
            }

            list.add(table);
        }

        return list;
    }

    /**
     * 获取描述信息
     * 
     * @return
     * @throws SQLException
     */
    public String getDescription() throws SQLException {
        Connection conn = connGetter.getConnection();

        StringBuffer sbuf = new StringBuffer();

        sbuf.append("JDBC驱动类：" + connGetter.getDriverClass());
        sbuf.append("\n连接URL：" + connGetter.getURL());

        sbuf.append("\n获取的连接对象是：" + conn);
        sbuf.append("\n当前自动提交模式 " + conn.getAutoCommit());
        sbuf.append("\n当前目录名称 " + conn.getCatalog());
        /**
         * <pre>
         *  java.sql.Connection 
         *  public static final int TRANSACTION_NONE 0 
         *  public static final int TRANSACTION_READ_COMMITTED 2 
         *  public static final int TRANSACTION_READ_UNCOMMITTED 1 
         *  public static final int TRANSACTION_REPEATABLE_READ 4 
         *  public static final int TRANSACTION_SERIALIZABLE 8
         * </pre>
         */
        sbuf.append("\n当前事务隔离级别:" + conn.getTransactionIsolation());

        /**
         * Connection 对象的数据库能够提供描述其表、所支持的 SQL 语法、存储过程、此连接功能等等的信息。此信息是使用
         * getMetaData 方法获得的。
         */
        sbuf.append("\n\nDatabaseMetaData 信息：");

        DatabaseMetaData condmd = conn.getMetaData();

        sbuf.append("\n数据库名:" + condmd.getDatabaseProductName());

        sbuf.append("\n数据库版本号:" + condmd.getDatabaseProductVersion());

        sbuf.append("\n驱动名:" + condmd.getDriverName());

        sbuf.append("\n驱动版本号:" + condmd.getDriverVersion());

        sbuf.append("\n是否支持事务:" + condmd.supportsTransactions());

        sbuf.append("\n默认事务隔离级别:" + condmd.getDefaultTransactionIsolation());

        sbuf.append("\n是否支持指定的事务隔离级别:" + condmd.supportsTransactionIsolationLevel(0));

        sbuf.append("\n是否支持获取主键:" + condmd.supportsGetGeneratedKeys());

        sbuf.append("\n是否支持敏感结果集" + condmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));

        return sbuf.toString();
    }
}
