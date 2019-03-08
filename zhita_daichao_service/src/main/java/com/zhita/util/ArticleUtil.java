package com.zhita.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArticleUtil {

	public static void writeFile(String filePath, String content) {
		FileOutputStream fos = null;
		try {
			// 1、根据文件路径创建输出流
			fos = new FileOutputStream(filePath);

			// 2、把string转换为byte数组；
			byte[] array = content.getBytes();
			// 3、把byte数组输出；
			fos.write(array);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	public static String txt2String(File file) {
//		StringBuilder result = new StringBuilder();
//		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));// 构造一个BufferedReader类来读取文件
//			String s = null;
//			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
//				if (s.equals("")) {
//					s = "@newline";
//				}
//				result.append(s);
//			}
//			br.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		String resultString = result.toString();
//		String result1 = trimTest(resultString);
//		return result1;
//	}
//
//	public static String trimTest(String text) {
//		String result = text;
//		while (true) {
//			if (result.endsWith("@newline")) {
//				String a = result.substring(0,result.length() - 8);
//				result = a;
//			} else {
//				return result;
//			}
//		}
//	}

	public static String txt2String(String content) {
		String string = content.replaceAll("\r|\n","@"); 
		String result = trimTest(string);
		return result;
	}

	public static String trimTest(String text) {
		String result = text;
		while (true) {
			if (result.endsWith("@@")) {
				String a = result.substring(0,result.length() - 2);
				result = a;
			} else {
				return result;
			}
		}
	}
}
