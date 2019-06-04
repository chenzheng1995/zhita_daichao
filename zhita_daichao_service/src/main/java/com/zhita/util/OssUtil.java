package com.zhita.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;

public class OssUtil {
	
	private static final String HTTP = "https://";
	
	// 阿里云API的外网域名
	private static final String ENDPOINT = "oss-cn-zhangjiakou.aliyuncs.com";
	
	// 阿里云API的bucket名称
	private static final String BUCKET_NAME = "wx-dc";
	
	// 阿里云API的密钥Access Key ID
	private static final String ACCESS_KEY_ID = "LTAISFgfet473EbT";
	
	// 阿里云API的密钥Access Key Secret
	private static final String ACCESS_KEY_SECRET = "PGpTcKhhGL2w9hO4nJ3SXriXGefdL7";
	
	private static final String URL = HTTP + BUCKET_NAME + "." + ENDPOINT + "/";
	
	/**
	 * 文件上传
	 *
	 * @param file
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(File file, String key) throws Exception {
		OssUtil ossUtil = new OssUtil();
		return ossUtil.uploadFile(new FileInputStream(file), key);
	}
	
	/**
	 * 文件上传
	 *
	 * @param is
	 * @param key
	 * @return
	 */
	public static String uploadFile(InputStream is, String key) throws Exception {
		OSSClient client = null;
		try {
			client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
			client.putObject(BUCKET_NAME, key, is);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			client.shutdown();
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return URL + key;
	}

	public static String uploadFile_copy(InputStream is, String key) {
			OSSClient client = null;
		try {
			client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
			client.putObject(BUCKET_NAME, key, is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return URL + key;
	}
	
//	文件流下载
	public BufferedReader IoDownload(String objectName) throws IOException {
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

		// ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
		OSSObject ossObject = ossClient.getObject(BUCKET_NAME, objectName);
		// 读取文件内容。
		System.out.println("Object content:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
		while (true) {
		    String line = reader.readLine();
		    if (line == null) break;

		    System.out.println("\n" + line);
		}
		// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
		reader.close();

		// 关闭OSSClient。
		ossClient.shutdown();
		return reader;
	}
	
}
