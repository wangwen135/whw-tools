package com.wwh.whwtools.config;

/**
 * <pre>
 * 浏览数据库配置类
 * </pre>
 * 
 * @author wwh
 * @date 2018年3月8日 下午3:19:41
 */
public class DBView {

    /**
     * 主机地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 数据库名称
     */
    private String databaseName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 匹配
     */
    private String match;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DBView [host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", databaseName=");
        builder.append(databaseName);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", passwd=");
        builder.append(passwd);
        builder.append(", match=");
        builder.append(match);
        builder.append("]");
        return builder.toString();
    }

    
}
