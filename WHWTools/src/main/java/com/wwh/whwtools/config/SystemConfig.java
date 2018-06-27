package com.wwh.whwtools.config;

/**
 * <pre>
 * 配置类
 * </pre>
 * 
 * @author wwh
 * @date 2018年3月8日 下午3:21:54
 */
public class SystemConfig {

    private DBView dbView;

    private CodeGenerate codeGenerate;

    public DBView getDbView() {
        return dbView;
    }

    public void setDbView(DBView dbView) {
        this.dbView = dbView;
    }

    public CodeGenerate getCodeGenerate() {
        return codeGenerate;
    }

    public void setCodeGenerate(CodeGenerate codeGenerate) {
        this.codeGenerate = codeGenerate;
    }

}
