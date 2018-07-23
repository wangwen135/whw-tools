package com.wwh.whwtools.generator.velocity;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 字符串工具
 * </pre>
 *
 * @author wwh
 * @date 2015年9月24日 下午6:04:47
 *
 */
public class MyUtils {

    /**
     * 不重复
     * 
     * @param arg
     * @return
     */
    public boolean unrepeat(String... arg) {
        return !repeat(arg);
    }

    /**
     * 判断是否存在重复
     * 
     * @param arg
     * @return
     */
    public boolean repeat(String... arg) {
        for (int i = 0; i < arg.length; i++) {
            String a1 = arg[i];
            for (int j = i + 1; j < arg.length; j++) {
                String a2 = arg[j];
                if (equal(a1, a2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 首字母大写
     * 
     * @param str
     * @return
     */
    public String initialUpperCase(String str) {
        if (str == null)
            return null;
        if (str.length() < 1)
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 截取字符串，从最后匹配的下一个位置
     * 
     * @param src
     * @param match
     * @return
     */
    public String substringLastIndex(String src, String match) {
        if (src == null)
            return null;
        if (match == null)
            return src;
        int index = src.lastIndexOf(match);
        if (index < 0)
            return src;
        return src.substring(index + 1);
    }

    public boolean isNull(String str) {
        return str == null ? true : false;
    }

    public boolean isNotNull(String str) {
        return !isNull(str);
    }

    public boolean unequal(String a1, String a2) {
        return !this.equal(a1, a2);
    }

    public boolean equal(String a1, String a2) {
        return StringUtils.equals(a1, a2);
    }

}
