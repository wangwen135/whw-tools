package com.wwh.whwtools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 编码解码器
 * 用来处理常用的如：Hex、Base64、URL、HTML、XML  
 * 以及 SHA1、MD5
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月6日 上午9:59:56
 *
 */
public class CodecUtils {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(CodecUtils.class);

    /**
     * 默认URL编码
     */
    public static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * Hex编码.
     * 
     * @param input
     * @return
     */
    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     * 
     * @param input
     * @return 如果是奇数个将抛出异常
     */
    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * 测试一个给定的字符串是否只包含有效Base64字母字符。目前该方法将空格视为有效。
     * 
     * @param base64
     * @return
     */
    public static boolean isBase64(String base64) {
        return Base64.isBase64(base64);
    }

    /**
     * Base64编码.
     * 
     * @param input
     * @return
     */
    public static String base64Encode(byte[] input) {
        try {
            // byte[] containing Base64 characters in their UTF-8
            // representation.
            return new String(Base64.encodeBase64(input), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符�?,/=转为其他字符, 见RFC3548).
     * 
     * @param input
     * @return
     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     * 
     * @param input
     * @return
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     * 
     * @param input
     * @return
     */
    public static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL编码，使用指定 编码
     * 
     * @param input
     * @param character
     * @return
     */
    public static String urlEncode(String input, String character) {
        try {
            return URLEncoder.encode(input, character);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL 解码, 使用指定编码
     */
    public static String urlDecode(String input, String character) {
        try {
            return URLDecoder.decode(input, character);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * HTML 转码.
     * 
     * @param html
     * @return
     */
    public static String htmlEscape(String html) {
        return StringEscapeUtils.escapeHtml(html);
    }

    /**
     * HTML 解码.
     * 
     * @param htmlEscaped
     * @return
     */
    public static String htmlUnescape(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml(htmlEscaped);
    }

    /**
     * XML 转码.
     * 
     * @param xml
     * @return
     */
    public static String xmlEscape(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * XML 解码.
     * 
     * @param xmlEscaped
     * @return
     */
    public static String xmlUnescape(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    // =========消息摘要==========

    /**
     * 计算MD5摘要并返回值为一个32个字符的十六进制字符串
     * 
     * @param data
     * @return
     */
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * 计算MD5摘要并返回值为一个32个字符的十六进制字符串
     * 
     * @param data
     * @return
     */
    public static String md5Hex(InputStream data) throws IOException {
        return DigestUtils.md5Hex(data);
    }

    /**
     * 计算MD5摘要并返回值为16个元素byte[]
     * 
     * @param data
     * @return
     */
    public static byte[] md5(byte[] data) {
        return DigestUtils.md5(data);
    }

    /**
     * 计算了SHA-1摘要并返回值为十六进制字符串
     * 
     * @param data
     * @return
     */
    public static String sha1Hex(String data) {
        return DigestUtils.sha1Hex(data);
    }

    /**
     * 计算了SHA-1摘要并返回一个byte[]
     * 
     * @param data
     * @return
     */
    public static byte[] sha1(byte[] data) {
        return DigestUtils.sha1(data);
    }

}
