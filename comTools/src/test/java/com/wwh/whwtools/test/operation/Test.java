package com.wwh.whwtools.test.operation;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月15日 下午1:10:49
 *
 */
public class Test {

    public static void main(String[] args) {
        
        short i = (short) (Integer.valueOf("e501", 16) & 0xffff);
        
        System.out.println(i);
        
        //补码=反码+1
        short s = (short) (i ^ 0xffff);
        System.out.println(s);
        
    }
    
    public static void main3(String[] args) {
        System.out.println((short) (Integer.valueOf("e501", 16) & 0xffff));

        Integer i = Integer.valueOf("e501", 16);
        System.out.println(i);

        byte[] bytes = new byte[] { (byte) 0xe5, 0x01 };

        short i2 = (short) ((0xff & bytes[1]) | (0xff00 & (bytes[0] << 8)));

        System.out.println(i2);

    }

    public static void main2(String[] args) {
        swap1();
        swap2();

    }

    // 计算绝对值
    int abs(int x) {
        int y;
        y = x >> 31;
        return (x ^ y) - y; // or: (x+y)^y
    }

    // 返回X,Y 的平均值
    int average(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    // )判断一个整数是不是2的幂,对于一个数 x >= 0，判断他是不是2的幂
    boolean power2(int x) {
        return ((x & (x - 1)) == 0) && (x != 0);
    }

    /**
     * 
     */
    public static void swap2() {
        int x = 100;
        int y = 200;

        x ^= y;
        y ^= x;
        x ^= y;

        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }

    /**
     * 
     */
    public static void swap1() {
        int x = 100;
        int y = 200;

        x = x + y;
        y = x - y;
        x = x - y;

        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}
