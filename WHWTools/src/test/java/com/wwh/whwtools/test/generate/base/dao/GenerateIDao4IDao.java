package com.wwh.whwtools.test.generate.base.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * <pre>
 * 读取接口java文件 生成实现类
 * </pre>
 *
 * @author wwh
 * @date 2015年8月11日 上午11:44:47
 *
 */
public class GenerateIDao4IDao {

    public static void main(String[] args) throws Exception {
        File srcDir = new File("C:/java/SDB_workspace/shundaibang/sdb-forest/src/main/java/com/xingren/sdb/forest/dao");
        File descDir = new File("C:\\tmp\\");

        String[] javaFile = srcDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });

        for (String s : javaFile) {
            GenerateOne(new File(srcDir, s), descDir);
        }

    }

    /**
     * @throws Exception
     * @throws IOException
     */
    public static void GenerateOne(File srcFile, File descDir) throws Exception {

        VelocityContext ctx = readFile(srcFile);

        // 文件名：
        String className = ctx.get("className") + ".java";

        File descFile = new File(descDir, className);
        descFile.delete();
        descFile.createNewFile();

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("IXXDao.vm", "UTF-8");

        final FileWriter writer = new FileWriter(descFile);
        t.merge(ctx, writer);

        writer.flush();
        writer.close();

        System.out.println("生成完毕：" + descFile.getAbsolutePath());
    }

    public static VelocityContext readFile(File f) throws Exception {
        VelocityContext vctx = new VelocityContext();

        BufferedReader brd = new BufferedReader(new FileReader(f));
        // 需要读取一些的变量
        // 包名
        // _package
        // 模块包名
        // modulePackage
        // 领域对象名
        // doName
        // dao 名字
        // daoName
        // 属性id 类型
        // idType
        // 属性id name
        // idName

        String line;
        while ((line = brd.readLine()) != null) {
            System.out.println(line);
            if ("".equals(line)) {
                continue;
            }

            if (line.startsWith("package")) {// 读取包名
                String _package = line.replace("package", "").replace(";", "").trim();
                System.out.println("包名：" + _package);
                vctx.put("_package", _package);
                String modulePackage = _package.replace(".dao", "");
                System.out.println("模块包名：" + modulePackage);
                vctx.put("modulePackage", modulePackage);
            } else

            if (line.startsWith("import")) {
                String doName = line.substring(line.lastIndexOf(".") + 1).replace(";", "");
                System.out.println("领域对象名：" + doName);
                vctx.put("doName", doName);

            } else if (line.startsWith("public interface")) {

                String daoName = line.replace("public interface ", "").replace(" {", "");
                System.out.println("DAO名：" + daoName);

                vctx.put("daoName", daoName);

                // java类名
                String className = daoName;
                System.out.println("java类名：" + className);
                vctx.put("className", className);

                // 计算首字母小写的dao名
                String _daoName = daoName.substring(1);
                _daoName = _daoName.substring(0, 1).toLowerCase() + _daoName.substring(1);
                System.out.println("首字母小写的dao名：" + _daoName);

                vctx.put("_daoName", _daoName);

            } else if (line.contains("deleteByPrimaryKey")) {

                String tmp = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
                final String[] split = tmp.split(" ");
                String idType = split[0];
                String idName = split[1];

                System.out.println("属性ID类型：" + idType);
                vctx.put("idType", idType);

                System.out.println("属性ID名：" + idName);
                vctx.put("idName", idName);

                brd.close();
                break;// 需要的都读完了
            }
        }

        // 计算日期
        Date date = new Date();
        // 2015年8月10日
        String date1 = new SimpleDateFormat("yyyy年M月d日").format(date);
        System.out.println(date1);

        vctx.put("date1", date1);

        // 2015年8月10日 下午7:02:28
        String date2 = new SimpleDateFormat("yyyy年M月d日 ah:mm:ss").format(date);
        System.out.println(date2);
        vctx.put("date2", date2);

        return vctx;
    }
}
