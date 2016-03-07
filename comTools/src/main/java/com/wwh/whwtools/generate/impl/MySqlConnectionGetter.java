package com.wwh.whwtools.generate.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wwh.whwtools.generate.DBConnectionGetter;

/**
 * <pre>
 * mysql 连接获取器
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午6:11:21
 *
 */
public class MySqlConnectionGetter implements DBConnectionGetter {

    private static Logger log = LoggerFactory.getLogger(MySqlConnectionGetter.class);

    /**
     * 驱动类
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 默认端口
     */
    public static final int DEFAULT_PORT = 3306;

    /**
     * URL格式
     * 
     * <pre>
     * 0 = host  %s
     * 1 = port  %d
     * 2 = dbName  %s
     * </pre>
     */
    public static final String URL_FORMAT = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf-8";

    /**
     * 连接串
     */
    private String URL;

    private String host;

    private int port;

    private String dbName;

    private String userName;

    private String password;

    private Connection conn;

    /**
     * <pre>
     * 构造方法
     * 默认localhost
     * 默认3306端口
     * </pre>
     *
     */
    public MySqlConnectionGetter(String dbName, String userName, String pwd) {
        this("localhost", dbName, userName, pwd);
    }

    /**
     * <pre>
     * 构造方法
     * 默认3306端口
     * </pre>
     *
     */
    public MySqlConnectionGetter(String host, String dbName, String userName, String pwd) {
        this("localhost", DEFAULT_PORT, dbName, userName, pwd);
    }

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public MySqlConnectionGetter(String host, int port, String dbName, String userName, String pwd) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.password = pwd;
        init();
    }

    private void init() {
        try {
            Class.forName(MySqlConnectionGetter.DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("数据库驱动加载失败", e);
            throw new RuntimeException("数据库驱动加载失败", e);
        }

        URL = String.format(URL_FORMAT, host, port, dbName);

        log.debug("mysql 数据库连接串：{}", URL);

        Properties props = new Properties();

        props.setProperty("user", userName);
        props.setProperty("password", password);
        props.setProperty("remarks", "true"); // 设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");// 设置可以获取tables_remarks信息
        try {
            conn = DriverManager.getConnection(URL, props);
        } catch (SQLException e) {
            log.error("获取数据库连接异常", e);
            throw new RuntimeException("获取数据库连接异常", e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getDBName() {
        return dbName;
    }

    public String getDriverClass() {
        return MySqlConnectionGetter.DRIVER;
    }

    public String getURL() {
        return URL;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error("数据库连接关闭异常", e);
        }
    }

    public Connection refreshConnection() {
        // 先关闭
        closeConnection();
        init();
        return conn;
    }

}
