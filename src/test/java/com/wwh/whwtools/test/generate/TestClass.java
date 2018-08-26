package com.wwh.whwtools.test.generate;

import java.util.List;

import com.wwh.whwtools.generator.DBMetaDataGetter;
import com.wwh.whwtools.generator.entity.TableEntity;
import com.wwh.whwtools.generator.impl.MySqlConnectionGetter;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午6:55:45
 *
 */
public class TestClass {
    public static void main(String[] args) throws Exception {
        MySqlConnectionGetter conGetter = new MySqlConnectionGetter("sdb", "sdb", "123456");
        DBMetaDataGetter mdgetter = new DBMetaDataGetter(conGetter);

        System.out.println(mdgetter.getDescription());

        System.out.println("\n");

        List<TableEntity> list = mdgetter.getTables(null,null);

        for (TableEntity table : list) {
            System.out.println(table);
        }

        conGetter.closeConnection();
    }
}
