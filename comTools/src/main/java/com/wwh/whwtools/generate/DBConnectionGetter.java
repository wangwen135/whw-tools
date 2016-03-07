package com.wwh.whwtools.generate;

import java.sql.Connection;

/**
 * <pre>
 * 连接获取器
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午6:02:59
 *
 */
public interface DBConnectionGetter {

    /**
     * 获取数据库连接
     * 
     * @return
     */
    Connection getConnection();

    /**
     * 获取驱动类
     * 
     * @return
     */
    String getDriverClass();

    /**
     * 获取连接串
     * 
     * @return
     */
    String getURL();

    /**
     * 获取数据库地址
     * 
     * @return
     */
    String getHost();

    /**
     * 获取端口
     * 
     * @return
     */
    int getPort();

    /**
     * 获取登录名
     * 
     * @return
     */
    String getUserName();

    /**
     * 获取数据库名
     * 
     * @return
     */
    String getDBName();

    /**
     * 关闭连接
     */
    void closeConnection();

    /**
     * 刷新连接
     * 
     * @return
     */
    Connection refreshConnection();
}
