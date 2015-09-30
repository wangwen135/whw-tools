/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月12日 Created
 */
package com.xingren.test.generate.base.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月12日 下午7:07:41
 *
 */
public class InsertSQLMap {

    public static void main(String[] args) throws Exception {
        File srcDir = new File("C:/java/SDB_workspace/shundaibang/sdb-forest/src/main/resources/sqlmap");
        File descDir = new File("C:\\tmp\\sqlmap");
        descDir.mkdirs();
        String[] javaFile = srcDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

        for (String s : javaFile) {
            GenerateOne(new File(srcDir, s), new File(descDir, s));
        }

    }

    public static void GenerateOne(File srcFile, File descFile) throws Exception {

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("sqlmap.vm");
        VelocityContext ctx = getVContext(srcFile);

        descFile.delete();
        descFile.createNewFile();

        BufferedWriter bufwt = new BufferedWriter(new FileWriter(descFile));
        BufferedReader bufrd = new BufferedReader(new FileReader(srcFile));
        String line;
        while ((line = bufrd.readLine()) != null) {
            if (line.equals("</mapper>")) {
                break;
            }
            bufwt.write(line);
            bufwt.write("\r\n");
        }

        bufrd.close();

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        System.out.println("下面开始输出内容，呢嘛  还不如重新写一个呢");
        final String string = sw.toString();
        System.out.println(string);
        bufwt.write(string);

        bufwt.write("</mapper>");
        bufwt.flush();
        bufwt.close();

    }

    public static VelocityContext getVContext(File srcFile) throws Exception {

        BufferedReader bufrd = new BufferedReader(new FileReader(srcFile));
        VelocityContext vctx = new VelocityContext();

        boolean tableNameOk = false;
        String line;
        while ((line = bufrd.readLine()) != null) {
            // System.out.println(line);
            if ("".equals(line)) {
                continue;
            }

            // namespace="com.xingren.sdb.forest.dao.IBaseAreaCnDao"
            if (line.contains("namespace")) {
                String namespace = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                System.out.println("namespace  = " + namespace);
                vctx.put("namespace", namespace);

            } else
            // <resultMap id="BaseResultMap"
            // type="com.xingren.sdb.forest.model.BaseAreaCnDO">
            // 可能分成两行
            // 简单的搞吧，懒得用dom4j 了
            if (line.contains("<resultMap id=\"BaseResultMap\"")) {
                if (!line.contains("type")) {
                    line = bufrd.readLine();// 接着读下一行
                }
                line = line.substring(line.indexOf("type"));
                String BaseResultMap = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                System.out.println("BaseResultMap = " + BaseResultMap);
                vctx.put("BaseResultMap", BaseResultMap);
                vctx.put("parameterType", BaseResultMap);

            }
            // <id column="area_cn_id" property="areaCnId" />
            else if (line.contains("<id column=")) {
                String[] as = line.split("\"");
                String idColumn = as[1];
                System.out.println("idColumn = " + idColumn);
                vctx.put("idColumn", idColumn);
                String idProperty = as[3];
                System.out.println("idProperty = " + idProperty);
                vctx.put("idProperty", idProperty);
                // 接着读取column
                Map<String, String> columns = new LinkedHashMap<String, String>();

                vctx.put("columns", columns);

                for (;; line = bufrd.readLine()) {
                    if (line.contains("</resultMap>")) {
                        break;
                    }

                    if (line.contains("<result column=")) {
                        String[] clms = line.split("\"");
                        String column = clms[1];
                        System.out.print("column = " + column);

                        String property = clms[3];
                        System.out.println("      property = " + property);

                        columns.put(column, property);
                    }
                }

            }
            // 读取表名
            else if (line.contains("FROM ")) {
                // 读一个就可以了
                if (tableNameOk) {
                    continue;
                }
                // FROM base_area_cn
                String tableName = line.substring(line.lastIndexOf(" ") + 1);
                System.out.println("tableName = " + tableName);
                vctx.put("tableName", tableName);
                tableNameOk = true;

                break;
            }

            // break;// 需要的都读完了
        }
        bufrd.close();

        return vctx;
    }
}
