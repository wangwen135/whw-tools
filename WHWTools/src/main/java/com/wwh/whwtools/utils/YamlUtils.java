package com.wwh.whwtools.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.yaml.snakeyaml.Yaml;

import com.wwh.whwtools.config.CodeGenerate;
import com.wwh.whwtools.config.DBView;
import com.wwh.whwtools.config.SystemConfig;

/**
 * <pre>
 * 读写配置文件
 * </pre>
 * 
 * @author wwh
 * @date 2018年3月8日 下午3:13:05
 */
public class YamlUtils {
    public static final String CONFIG_FILE_NAME = "config.yml";

    private static SystemConfig config;

    public static void main(String[] args) throws IOException {

        InputStream is = YamlUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);

        Yaml yaml = new Yaml();
        // 读入文件
        Object result = yaml.load(is);

        System.out.println(result.getClass());

        System.out.println(result);
        
        CodeGenerate cg = new CodeGenerate();
        cg.setHost("192.168.1.166");
        cg.setPort(3306);
        cg.setUserName("root");
        cg.setPasswd("root");
        
        DBView dv = new DBView();
        dv.setHost("192.168.1.166");
        dv.setPort(3306);
        dv.setUserName("root");
        dv.setPasswd("root");

        SystemConfig sc = new SystemConfig();
        sc.setDbView(dv);
        sc.setCodeGenerate(cg);
        
       URL url = YamlUtils.class.getClassLoader().getResource(CONFIG_FILE_NAME);
       
       
       
       System.out.println(url.getProtocol());
       
       System.out.println(url.getFile());
             
              
       FileWriter fw = new FileWriter(url.getFile());
       
       yaml.dump(sc, fw);
       
//       yaml.setName(name);
    }

    public static void getConfig() {

    }

    public static void saveConfig() {

    }

    public static void getCacheConfig() {

    }
}
