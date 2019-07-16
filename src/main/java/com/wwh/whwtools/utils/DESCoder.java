package com.wwh.whwtools.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * <pre>
 * DES 编码器 
 * 
 * 秘钥长度：56 bit 
 * 
 * 工作模式： ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128
 * 填充方式： Nopadding/PKCS5Padding/ISO10126Padding/
 * 
 * </pre>
 * 
 */
public class DESCoder {
    /**
     * 字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * <pre>
     * 密钥长度
     * 明文按64位进行分组，密钥长64位，
     * 密钥事实上是56位参与DES运算
     * （第8、16、24、32、40、48、56、64位是校验位， 使得每个密钥都有奇数个1）
     * 分组后的明文组和56位的密钥按位替代或交换的方法形成密文组的加密方法。
     * </pre>
     */
    private static final int KEY_SIZE_56 = 56;

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "DES";

    /**
     * 默认的 算法/模式/填充
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /**
     * <pre>
     * 根据密码获取密钥
     * 使用UTF-8 编码将password串转成byte[]
     * 再进行MD5 得到16个byte
     * 实际上只会使用前的8个byte
     * </pre>
     * 
     * @param password 密码
     * @return 16个byte 128位的密钥
     * @throws Exception
     */
    public static byte[] getKeyByPassword(String password) throws Exception {
        return CodecUtils.md5(password.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 获取随机秘钥
     * 
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] getRandomSecretKey() throws Exception {
        // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        // 初始化此密钥生成器，使其具有确定的密钥大小
        // 随机填充
        kg.init(KEY_SIZE_56);
        // 生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 返回秘钥内容
        return secretKey.getEncoded();
    }

    /**
     * 将秘钥字节数组转换成密钥对象
     * 
     * @param key 二进制密钥内容，只取前面8个字节
     * @return Key 密钥对象
     * @throws Exception
     */
    public static Key getSecretKey(byte[] key) throws Exception {
        // 使用key中的前8个字节作为DES key的密钥材料创建DESKeySpec对象
        // 构成DES密钥的字节包括key[0]和key[7]之间的字节
        DESKeySpec dks = new DESKeySpec(key);
        // 实例化 DES 密钥工厂
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        // 从提供的密钥规范(密钥材料)生成密钥对象。
        SecretKey secretKey = skf.generateSecret(dks);
        return secretKey;
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key  二进制密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     * 
     * @param data            待加密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        // 还原密钥
        Key k = getSecretKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     * 
     * @param data            待加密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key  二进制密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     * 
     * @param data            待解密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        // 还原密钥
        Key k = getSecretKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     * 
     * @param data            待解密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 执行操作
        return cipher.doFinal(data);
    }

}
