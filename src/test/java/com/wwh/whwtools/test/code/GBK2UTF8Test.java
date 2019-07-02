package com.wwh.whwtools.test.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GBK2UTF8Test {

	public static final Set<String> suffixArray = new HashSet<String>(
			Arrays.asList(".java", ".properties", ".xml", ".yml", ".md"));

	// 换行符号
	// line.separator

	/**
	 * 回车
	 */
	public static final String CR = "\r";

	/**
	 * 换行
	 */
	public static final String LF = "\n";

	/**
	 * 回车换行
	 */
	public static final String CRLF = "\r\n";

	// 换行 回车换行

	public static void main(String[] args) throws IOException {
		// GBK编码格式源码路径
		String sourceFile = "E:\\workspace\\winform\\jar-encrypt-decrypt";

		// 转为UTF-8编码格式源码路径
		String outFile = "E:\\tmp\\UTF-8_Out";

		File srcDir = new File(sourceFile);
		if (!srcDir.isDirectory()) {
			System.out.println("源目录不存在");
			return;
		}

		File outDir = new File(outFile);

		if (!outDir.exists()) {
			outDir.mkdirs();
		}

		if (!outDir.isDirectory()) {
			System.out.println("输出必须是目录");
			return;
		}

		Charset srcCharset = null;
		Charset targetCharset = null;

		try {
			srcCharset = Charset.forName("GBK");
			targetCharset = Charset.forName("UTF-8");
		} catch (UnsupportedCharsetException e) {
			System.out.println("不支持编码" + e.getCharsetName());
		}

		List<File> files = recursionFileList(srcDir);

		convert(srcDir, outDir, files, srcCharset, targetCharset);

	}

	/**
	 * 递归查找文件
	 * 
	 * @param dir
	 * @return
	 */
	public static List<File> recursionFileList(File dir) {
		List<File> list = new ArrayList<File>();

		File[] f = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {

				if (pathname.isDirectory()) {
					return true;
				}

				String name = pathname.getName();
				String suffix = name.substring(name.lastIndexOf("."));

				return suffixArray.contains(suffix);
			}
		});

		for (File file : f) {
			if (file.isDirectory()) {
				list.addAll(recursionFileList(file));
			} else {
				list.add(file);
			}
		}

		return list;
	}

	public static void convert(File srcDir, File outDir, List<File> fileList, Charset srcCharset, Charset targetCharset)
			throws IOException {
		String basePath = srcDir.getAbsolutePath();

		for (File srcFile : fileList) {
			String fPath = srcFile.getAbsolutePath();
			fPath = fPath.replace(basePath, "");

			File outFile = new File(outDir, fPath);

			outFile.getParentFile().mkdirs();

			outFile.createNewFile();

			try (BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(srcFile), srcCharset));
					OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outFile), targetCharset)) {

				String line;

				while ((line = bufReader.readLine()) != null) {

					osw.write(line);

					// 换行
					osw.write(LF);
				}
			}

		}

	}

}
