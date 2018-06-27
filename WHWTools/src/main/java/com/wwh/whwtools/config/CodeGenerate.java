package com.wwh.whwtools.config;

import java.util.List;

/**
 * <pre>
 * 生成代码配置
 * </pre>
 * 
 * @author wwh
 * @date 2018年3月8日 下午3:20:08
 */
public class CodeGenerate {

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

    /**
     * 输出目录
     */
    private String outputDir;

    /**
     * 隐藏表字段
     */
    private List<String> hideFields;

    /**
     * 自定义变量
     */
    private List<String> customVar;

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

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public List<String> getHideFields() {
        return hideFields;
    }

    public void setHideFields(List<String> hideFields) {
        this.hideFields = hideFields;
    }

    public List<String> getCustomVar() {
        return customVar;
    }

    public void setCustomVar(List<String> customVar) {
        this.customVar = customVar;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CodeGenerate [host=");
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
        builder.append(", outputDir=");
        builder.append(outputDir);
        builder.append(", hideFields=");
        builder.append(hideFields);
        builder.append(", customVar=");
        builder.append(customVar);
        builder.append("]");
        return builder.toString();
    }

}
