package com.wwh.whwtools.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月11日 上午10:40:13
 *
 */
public class ReadJavaFile {

    public static void main(String[] args) throws Exception {

        BufferedReader brd = new BufferedReader(new FileReader(new File(ClassLoader.getSystemResource("IBaseAreaCnDao.java").getFile())));
        // 需要读取一些的变量
        // 包名
        String _package = null;
        // 模块包名
        String modulePackage = null;
        // domainObject 名
        String doName = null;
        // dao 名字
        String daoName = null;
        // id name
        String idName = null;

        String line;
        while ((line = brd.readLine()) != null) {
            System.out.println(line);
            if ("".equals(line)) {
                continue;
            }

            if (line.startsWith("package")) {// 读取包名
                _package = line.replace("package", "").replace(";", "").trim();
                System.out.println(_package);
                modulePackage = _package.replace(".dao", "");
            } else

            if (line.startsWith("import")) {
                doName = line.substring(line.lastIndexOf(".") + 1).replace(";", "");
            } else if (line.startsWith("public interface")) {
                // public interface IBaseAreaCnDao {
                daoName = line.replace("public interface ", "").replace(" {", "");
            } else

            if (line.contains("deleteByPrimaryKey")) {
                String tmp = line.substring(line.indexOf("(")+1,line.lastIndexOf(")"));
                System.out.println(tmp);
                idName = tmp.split(" ")[1];
                System.out.println(tmp.split(" ")[0]);
                idName = line.substring(line.lastIndexOf(" ") + 1).replace(");", "");
                break;// 需要的都读完了
            }
        }
        // 包名
        System.out.println(_package);
        // 模块包名
        System.out.println(modulePackage);
        // domainObject 名
        System.out.println(doName);
        // dao 名字
        System.out.println(daoName);

        // 计算文件名
        // MybatisBaseAreaCnDao.java
        String fileName = daoName.replaceFirst("I", "Mybatis") ;
        System.out.println(fileName);
        
        //计算首字母小写的dao名
        String _daoName = daoName.substring(1);
        _daoName = _daoName.substring(0, 1).toLowerCase() + _daoName.substring(1);
        System.out.println("首字母小写的dao名："+_daoName);
        
        // id name
        System.out.println(idName);

        // 计算日期
        Date date = new Date();
        // 2015年8月10日
        String date1 = new SimpleDateFormat("yyyy年M月d日").format(date);
        System.out.println(date1);
        
        //2015年8月10日 下午7:02:28
        String date2 = new SimpleDateFormat("yyyy年M月d日 ah:mm:ss").format(date);
        System.out.println(date2);
    }
}
