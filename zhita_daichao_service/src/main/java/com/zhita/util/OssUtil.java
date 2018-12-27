package com.zhita.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;

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
	public String uploadFile(InputStream is, String key) throws Exception {
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
	
}
