/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月15日 Created
 */
package com.xingren.test.generate;

import java.util.List;

import com.xingren.generate.DBMetaDataGetter;
import com.xingren.generate.entity.TableEntity;
import com.xingren.generate.impl.MySqlConnectionGetter;

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
