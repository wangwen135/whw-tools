package com.wwh.whwtools.test.generate;

import java.util.List;

import com.wwh.whwtools.generate.DBMetaDataGetter;
import com.wwh.whwtools.generate.entity.TableEntity;
import com.wwh.whwtools.generate.impl.MySqlConnectionGetter;

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
