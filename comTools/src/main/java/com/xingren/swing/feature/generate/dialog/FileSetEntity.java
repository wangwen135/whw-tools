/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月22日 Created
 */
package com.xingren.swing.feature.generate.dialog;

/**
 * <pre>
 * 文件设置实体
 * </pre>
 *
 * @author wwh
 * @date 2015年8月22日 下午3:33:25
 *
 */
public class FileSetEntity {
    public static final String PLACEHOLDER = "${tableClass}";

    private String doFileName = PLACEHOLDER + "DO";
    private String doFileNameSuffix = ".java";
    private String doFileOutput = "DO";

    private String dtoFileName = PLACEHOLDER + "DTO";
    private String dtoFileNameSuffix = ".java";
    private String dtoFileOutput = "DTO";

    private String idaoFileName = "I" + PLACEHOLDER + "Dao";
    private String idaoFileNameSuffix = ".java";
    private String idaoFileOutput = "IDao";

    private String daoFileName = "Mybatis" + PLACEHOLDER + "Dao";
    private String daoFileNameSuffix = ".java";
    private String daoFileOutput = "MybatisDao";

    private String sqlmapFileName = "sqlmap_" + PLACEHOLDER;
    private String sqlmapFileNameSuffix = ".xml";
    private String sqlmapFileOutput = "sqlmap";

    private String daoTestFileName = "Test" + PLACEHOLDER + "Dao";
    private String daoTestFileNameSuffix = ".java";
    private String daoTestFileOutput = "DaoTest";

    private String iserviceFileName = "I" + PLACEHOLDER + "Service";
    private String iserviceFileNameSuffix = ".java";
    private String iserviceFileOutput = "IService";

    private String serviceFileName = PLACEHOLDER + "ServiceImpl";
    private String serviceFileNameSuffix = ".java";
    private String serviceFileOutput = "ServiceImpl";

    private String serviceTestFileName = "Test" + PLACEHOLDER + "Service";
    private String serviceTestFileNameSuffix = ".java";
    private String serviceTestFileOutput = "ServiceTest";

    /**
     * 获取 doFileName
     *
     * @return the doFileName
     */
    public String getDoFileName() {
        return doFileName;
    }

    /**
     * 设置 doFileName
     *
     * @param doFileName
     *            the doFileName to set
     */
    public void setDoFileName(String doFileName) {
        this.doFileName = doFileName;
    }

    /**
     * 获取 doFileNameSuffix
     *
     * @return the doFileNameSuffix
     */
    public String getDoFileNameSuffix() {
        return doFileNameSuffix;
    }

    /**
     * 设置 doFileNameSuffix
     *
     * @param doFileNameSuffix
     *            the doFileNameSuffix to set
     */
    public void setDoFileNameSuffix(String doFileNameSuffix) {
        this.doFileNameSuffix = doFileNameSuffix;
    }

    /**
     * 获取 doFileOutput
     *
     * @return the doFileOutput
     */
    public String getDoFileOutput() {
        return doFileOutput;
    }

    /**
     * 设置 doFileOutput
     *
     * @param doFileOutput
     *            the doFileOutput to set
     */
    public void setDoFileOutput(String doFileOutput) {
        this.doFileOutput = doFileOutput;
    }

    /**
     * 获取 dtoFileName
     *
     * @return the dtoFileName
     */
    public String getDtoFileName() {
        return dtoFileName;
    }

    /**
     * 设置 dtoFileName
     *
     * @param dtoFileName
     *            the dtoFileName to set
     */
    public void setDtoFileName(String dtoFileName) {
        this.dtoFileName = dtoFileName;
    }

    /**
     * 获取 dtoFileNameSuffix
     *
     * @return the dtoFileNameSuffix
     */
    public String getDtoFileNameSuffix() {
        return dtoFileNameSuffix;
    }

    /**
     * 设置 dtoFileNameSuffix
     *
     * @param dtoFileNameSuffix
     *            the dtoFileNameSuffix to set
     */
    public void setDtoFileNameSuffix(String dtoFileNameSuffix) {
        this.dtoFileNameSuffix = dtoFileNameSuffix;
    }

    /**
     * 获取 dtoFileOutput
     *
     * @return the dtoFileOutput
     */
    public String getDtoFileOutput() {
        return dtoFileOutput;
    }

    /**
     * 设置 dtoFileOutput
     *
     * @param dtoFileOutput
     *            the dtoFileOutput to set
     */
    public void setDtoFileOutput(String dtoFileOutput) {
        this.dtoFileOutput = dtoFileOutput;
    }

    /**
     * 获取 idaoFileName
     *
     * @return the idaoFileName
     */
    public String getIdaoFileName() {
        return idaoFileName;
    }

    /**
     * 设置 idaoFileName
     *
     * @param idaoFileName
     *            the idaoFileName to set
     */
    public void setIdaoFileName(String idaoFileName) {
        this.idaoFileName = idaoFileName;
    }

    /**
     * 获取 idaoFileNameSuffix
     *
     * @return the idaoFileNameSuffix
     */
    public String getIdaoFileNameSuffix() {
        return idaoFileNameSuffix;
    }

    /**
     * 设置 idaoFileNameSuffix
     *
     * @param idaoFileNameSuffix
     *            the idaoFileNameSuffix to set
     */
    public void setIdaoFileNameSuffix(String idaoFileNameSuffix) {
        this.idaoFileNameSuffix = idaoFileNameSuffix;
    }

    /**
     * 获取 idaoFileOutput
     *
     * @return the idaoFileOutput
     */
    public String getIdaoFileOutput() {
        return idaoFileOutput;
    }

    /**
     * 设置 idaoFileOutput
     *
     * @param idaoFileOutput
     *            the idaoFileOutput to set
     */
    public void setIdaoFileOutput(String idaoFileOutput) {
        this.idaoFileOutput = idaoFileOutput;
    }

    /**
     * 获取 daoFileName
     *
     * @return the daoFileName
     */
    public String getDaoFileName() {
        return daoFileName;
    }

    /**
     * 设置 daoFileName
     *
     * @param daoFileName
     *            the daoFileName to set
     */
    public void setDaoFileName(String daoFileName) {
        this.daoFileName = daoFileName;
    }

    /**
     * 获取 daoFileNameSuffix
     *
     * @return the daoFileNameSuffix
     */
    public String getDaoFileNameSuffix() {
        return daoFileNameSuffix;
    }

    /**
     * 设置 daoFileNameSuffix
     *
     * @param daoFileNameSuffix
     *            the daoFileNameSuffix to set
     */
    public void setDaoFileNameSuffix(String daoFileNameSuffix) {
        this.daoFileNameSuffix = daoFileNameSuffix;
    }

    /**
     * 获取 daoFileOutput
     *
     * @return the daoFileOutput
     */
    public String getDaoFileOutput() {
        return daoFileOutput;
    }

    /**
     * 设置 daoFileOutput
     *
     * @param daoFileOutput
     *            the daoFileOutput to set
     */
    public void setDaoFileOutput(String daoFileOutput) {
        this.daoFileOutput = daoFileOutput;
    }

    /**
     * 获取 sqlmapFileName
     *
     * @return the sqlmapFileName
     */
    public String getSqlmapFileName() {
        return sqlmapFileName;
    }

    /**
     * 设置 sqlmapFileName
     *
     * @param sqlmapFileName
     *            the sqlmapFileName to set
     */
    public void setSqlmapFileName(String sqlmapFileName) {
        this.sqlmapFileName = sqlmapFileName;
    }

    /**
     * 获取 sqlmapFileNameSuffix
     *
     * @return the sqlmapFileNameSuffix
     */
    public String getSqlmapFileNameSuffix() {
        return sqlmapFileNameSuffix;
    }

    /**
     * 设置 sqlmapFileNameSuffix
     *
     * @param sqlmapFileNameSuffix
     *            the sqlmapFileNameSuffix to set
     */
    public void setSqlmapFileNameSuffix(String sqlmapFileNameSuffix) {
        this.sqlmapFileNameSuffix = sqlmapFileNameSuffix;
    }

    /**
     * 获取 sqlmapFileOutput
     *
     * @return the sqlmapFileOutput
     */
    public String getSqlmapFileOutput() {
        return sqlmapFileOutput;
    }

    /**
     * 设置 sqlmapFileOutput
     *
     * @param sqlmapFileOutput
     *            the sqlmapFileOutput to set
     */
    public void setSqlmapFileOutput(String sqlmapFileOutput) {
        this.sqlmapFileOutput = sqlmapFileOutput;
    }

    /**
     * 获取 daoTestFileName
     *
     * @return the daoTestFileName
     */
    public String getDaoTestFileName() {
        return daoTestFileName;
    }

    /**
     * 设置 daoTestFileName
     *
     * @param daoTestFileName
     *            the daoTestFileName to set
     */
    public void setDaoTestFileName(String daoTestFileName) {
        this.daoTestFileName = daoTestFileName;
    }

    /**
     * 获取 daoTestFileNameSuffix
     *
     * @return the daoTestFileNameSuffix
     */
    public String getDaoTestFileNameSuffix() {
        return daoTestFileNameSuffix;
    }

    /**
     * 设置 daoTestFileNameSuffix
     *
     * @param daoTestFileNameSuffix
     *            the daoTestFileNameSuffix to set
     */
    public void setDaoTestFileNameSuffix(String daoTestFileNameSuffix) {
        this.daoTestFileNameSuffix = daoTestFileNameSuffix;
    }

    /**
     * 获取 daoTestFileOutput
     *
     * @return the daoTestFileOutput
     */
    public String getDaoTestFileOutput() {
        return daoTestFileOutput;
    }

    /**
     * 设置 daoTestFileOutput
     *
     * @param daoTestFileOutput
     *            the daoTestFileOutput to set
     */
    public void setDaoTestFileOutput(String daoTestFileOutput) {
        this.daoTestFileOutput = daoTestFileOutput;
    }

    /**
     * 获取 iserviceFileName
     *
     * @return the iserviceFileName
     */
    public String getIserviceFileName() {
        return iserviceFileName;
    }

    /**
     * 设置 iserviceFileName
     *
     * @param iserviceFileName
     *            the iserviceFileName to set
     */
    public void setIserviceFileName(String iserviceFileName) {
        this.iserviceFileName = iserviceFileName;
    }

    /**
     * 获取 iserviceFileNameSuffix
     *
     * @return the iserviceFileNameSuffix
     */
    public String getIserviceFileNameSuffix() {
        return iserviceFileNameSuffix;
    }

    /**
     * 设置 iserviceFileNameSuffix
     *
     * @param iserviceFileNameSuffix
     *            the iserviceFileNameSuffix to set
     */
    public void setIserviceFileNameSuffix(String iserviceFileNameSuffix) {
        this.iserviceFileNameSuffix = iserviceFileNameSuffix;
    }

    /**
     * 获取 iserviceFileOutput
     *
     * @return the iserviceFileOutput
     */
    public String getIserviceFileOutput() {
        return iserviceFileOutput;
    }

    /**
     * 设置 iserviceFileOutput
     *
     * @param iserviceFileOutput
     *            the iserviceFileOutput to set
     */
    public void setIserviceFileOutput(String iserviceFileOutput) {
        this.iserviceFileOutput = iserviceFileOutput;
    }

    /**
     * 获取 serviceFileName
     *
     * @return the serviceFileName
     */
    public String getServiceFileName() {
        return serviceFileName;
    }

    /**
     * 设置 serviceFileName
     *
     * @param serviceFileName
     *            the serviceFileName to set
     */
    public void setServiceFileName(String serviceFileName) {
        this.serviceFileName = serviceFileName;
    }

    /**
     * 获取 serviceFileNameSuffix
     *
     * @return the serviceFileNameSuffix
     */
    public String getServiceFileNameSuffix() {
        return serviceFileNameSuffix;
    }

    /**
     * 设置 serviceFileNameSuffix
     *
     * @param serviceFileNameSuffix
     *            the serviceFileNameSuffix to set
     */
    public void setServiceFileNameSuffix(String serviceFileNameSuffix) {
        this.serviceFileNameSuffix = serviceFileNameSuffix;
    }

    /**
     * 获取 serviceFileOutput
     *
     * @return the serviceFileOutput
     */
    public String getServiceFileOutput() {
        return serviceFileOutput;
    }

    /**
     * 设置 serviceFileOutput
     *
     * @param serviceFileOutput
     *            the serviceFileOutput to set
     */
    public void setServiceFileOutput(String serviceFileOutput) {
        this.serviceFileOutput = serviceFileOutput;
    }

    /**
     * 获取 serviceTestFileName
     *
     * @return the serviceTestFileName
     */
    public String getServiceTestFileName() {
        return serviceTestFileName;
    }

    /**
     * 设置 serviceTestFileName
     *
     * @param serviceTestFileName
     *            the serviceTestFileName to set
     */
    public void setServiceTestFileName(String serviceTestFileName) {
        this.serviceTestFileName = serviceTestFileName;
    }

    /**
     * 获取 serviceTestFileNameSuffix
     *
     * @return the serviceTestFileNameSuffix
     */
    public String getServiceTestFileNameSuffix() {
        return serviceTestFileNameSuffix;
    }

    /**
     * 设置 serviceTestFileNameSuffix
     *
     * @param serviceTestFileNameSuffix
     *            the serviceTestFileNameSuffix to set
     */
    public void setServiceTestFileNameSuffix(String serviceTestFileNameSuffix) {
        this.serviceTestFileNameSuffix = serviceTestFileNameSuffix;
    }

    /**
     * 获取 serviceTestFileOutput
     *
     * @return the serviceTestFileOutput
     */
    public String getServiceTestFileOutput() {
        return serviceTestFileOutput;
    }

    /**
     * 设置 serviceTestFileOutput
     *
     * @param serviceTestFileOutput
     *            the serviceTestFileOutput to set
     */
    public void setServiceTestFileOutput(String serviceTestFileOutput) {
        this.serviceTestFileOutput = serviceTestFileOutput;
    }

}
