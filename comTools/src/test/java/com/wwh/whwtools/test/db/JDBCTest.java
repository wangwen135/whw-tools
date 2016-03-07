package com.wwh.whwtools.test.db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月14日 下午6:21:24
 *
 */
public class JDBCTest {

    /**
     * Oracle：oracle.jdbc.driver.OracleDriver
     */
    public static final String DRIVE = "com.mysql.jdbc.Driver";
    /**
     * 连接地址，各个厂商提供 jdbc:oracle:thin:@localhost:1521:ORCL
     */
    // public static final String URL =
    // "jdbc:mysql://192.168.1.110:3306/sdb?useUnicode=true&characterEncoding=utf-8";
    public static final String URL = "jdbc:mysql://localhost:3306/sdb?useUnicode=true&characterEncoding=utf-8";
    /**
     * 用户 密码
     */
    public static final String DBUSER = "sdb";
    public static final String PASSWORD = "123456";

    /**
     * 执行语句
     */
    private static final String SELECT_SQL = "SELECT * FROM base_behavior_type";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;

        /**
         * <pre>
         * oracle.jdbc.driver.OracleDriver 类中有一个static块，类加载时会将自己注册到DriverManager中
         * <code>
         * defaultDriver = new OracleDriver();
         * DriverManager.registerDriver(defaultDriver);
         * </code>
         * </pre>
         */
        Class.forName(DRIVE);

        Enumeration<Driver> erd = DriverManager.getDrivers();
        System.out.println("获取带有当前调用者可以访问的所有当前已加载 JDBC 驱动程序的:");
        /**
         * 包含了两个 sun.jdbc.odbc.JdbcOdbcDriver@8813f2
         * oracle.jdbc.driver.OracleDriver@1d58aae
         */
        for (; erd.hasMoreElements();) {
            Driver driver = erd.nextElement();

            System.out.println(driver);
            System.out.println("MajorVersion:" + driver.getMajorVersion() + " MinorVersion:" + driver.getMinorVersion());
        }

        // 默认0 以秒为单位
        int loginTimeout = DriverManager.getLoginTimeout();
        System.out.println("试图登录到某一数据库时可以等待的最长时间:" + loginTimeout);

        // 将一条消息打印到当前 JDBC 日志流中。
        DriverManager.println("测试信息。。。。。");
        // JDBC 日志流默认为空

        // 构建一个新的
        PrintWriter printWriter = new PrintWriter(System.err);

        // 设置由 DriverManager 和所有驱动程序使用的日志/追踪 PrintWriter 对象。
        DriverManager.setLogWriter(printWriter);

        DriverManager.println("### 测 试 信 息 ### DriverManager.setLogWriter");
        DriverManager.println("设置日志为系统错误输出");

        // conn = DriverManager.getConnection(URL, DBUSER, PASSWORD);

        Properties props = new Properties();

        props.setProperty("user", DBUSER);
        props.setProperty("password", PASSWORD);
        props.setProperty("remarks", "true"); // 设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");// 设置可以获取tables
                                                          // remarks信息
        conn = DriverManager.getConnection(URL, props);

        System.out.println("\n\n获取的连接对象是：" + conn);
        System.out.println("获取此 Connection 对象的当前自动提交模式 " + conn.getAutoCommit());
        System.out.println("获取此 Connection 对象的当前目录名称 " + conn.getCatalog());
        // System.out.println(conn.getClientInfo());
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
        System.out.println("获取此 Connection 对象的当前事务隔离级别:" + conn.getTransactionIsolation());

        /**
         * Connection 对象的数据库能够提供描述其表、所支持的 SQL 语法、存储过程、此连接功能等等的信息。此信息是使用
         * getMetaData 方法获得的。
         */
        System.out.println("\n\nconn.getMetaData()\n");
        DatabaseMetaData condmd = conn.getMetaData();

        System.out.println("数据库名:" + condmd.getDatabaseProductName());

        System.out.println("数据库版本号:" + condmd.getDatabaseProductVersion());

        System.out.println("驱动名:" + condmd.getDriverName());

        System.out.println("驱动版本号:" + condmd.getDriverVersion());

        System.out.println("是否支持事务:" + condmd.supportsTransactions());

        System.out.println("默认事务隔离级别:" + condmd.getDefaultTransactionIsolation());

        System.out.println("是否支持指定的事务隔离级别:" + condmd.supportsTransactionIsolationLevel(0));

        System.out.println("是否支持获取主键:" + condmd.supportsGetGeneratedKeys());

        System.out.println("是否支持敏感结果集" + condmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));

        System.out.println("\n\n");

        String[] types = { "TABLE", "VIEW" };
        // // 获取数据库表信息
        ResultSet tableRet = condmd.getTables(null, "sdb", "%", types);

        printResultSet(tableRet);

        System.out.println("\n\n");

        System.out.println(" 获取表字段信息");

        // base_area_cn
        condmd = conn.getMetaData();
        ResultSet tableColumnRet = condmd.getColumns(conn.getCatalog(), "sdb", "base_area_cn", null);
        printResultSet(tableColumnRet);

        System.out.println("\n\n");

        System.out.println("获取主键");
        ResultSet pkRet = condmd.getPrimaryKeys(conn.getCatalog(), "sdb", "base_area_cn");
        printResultSet(pkRet);

        
        System.out.println("\n\n");

        System.out.println("获取唯一索引：");
        ResultSet uniqueRet  = condmd.getIndexInfo(conn.getCatalog(), "sdb", "base_area_cn", true, false);
        printResultSet(uniqueRet);
        
        
        System.out.println("\n\n");
        System.out.println("获取类型信息");
        ResultSet typeRet = condmd.getTypeInfo();
        printResultSet(typeRet);

        System.out.println("\n\n");

        System.out.println("########################  执行 SQL 语句 #########################");
        System.out.println(SELECT_SQL);
        System.out.println("########################      执行结果      #########################");

        stmt = conn.createStatement();

        // 执行SQL语句来查询数据库

        String sql = SELECT_SQL;
        result = stmt.executeQuery(sql);

        printResultSet(result);

        System.out.println("############  关闭  #############");

        result.close();// 数据库先开后关
        stmt.close();
        conn.close();// 关闭数据库
    }

    /**
     * @param result
     * @throws SQLException
     */
    public static void printResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData rsmd = result.getMetaData();

        // 获得结果集列数
        int columnCount = rsmd.getColumnCount();
        System.out.println("结果集中包含的列数： " + columnCount);

        for (int i = 1; i <= columnCount; i++) {
            System.out.println("列：" + i + " 列名称：" + rsmd.getColumnName(i) + " 列别名：" + rsmd.getColumnLabel(i) + " 数据库类型：" + rsmd.getColumnTypeName(i)
                    + " Java类型：" + rsmd.getColumnClassName(i) + "   不能为空：" + (rsmd.isNullable(i) == ResultSetMetaData.columnNoNulls)

                    + "  自动增长：" + rsmd.isAutoIncrement(i)

            );
        }

        System.out.println("\n查询结果：\n");

        while (result.next()) {// 判断有没有下一行

            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(rsmd.getColumnLabel(i), result.getObject(i));
            }

            System.out.println(map);

        }
    }
}
