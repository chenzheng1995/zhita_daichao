package com.zhita.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PostAndGet {

//	public static void main(String[] args) {	
//		System.out.println(sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf3af82730448fb6b&secret=72b44f0d762922a4dfac7c7946d3c0f3"));
//		System.out.println(sendPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?","access_token=16_5Qf3nUY9UASFhvjl-RZiyDmSOvpwOfXlfm_qEm68Tndar1Y3BFq5S3fE_DwM7ho_o02v2UtyDN7tN5CAJmHexpEBwkJVL0DaqyOsPyfr_KynTqdRDnCF-ourw5X3MfmyKb0XeetYIUVxlkNmSLKeACACVU&scene=o61Hi5C85t2S9StieLalnuwGuSLM"));
//	}
	public  String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("[POST请求]向地址：" + url + " 发送数据：" + param + " 发生错误!");
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭流异常");
			}
		}
		return result;
	}
	
	public  String sendPost2(String url, String param,String company, String scene,String softwareType) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[10 * 1024];
			int len = -1;
			while((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}

			String s = new String(baos.toByteArray(), "UTF-8");

			try {
				new Gson().fromJson(s, new TypeToken<Map<String, Object>>(){}.getRawType());
				result = s;
			} catch (Exception e) {
//				FileOutputStream fos = new FileOutputStream(new File("d:/2.jpg"));
//				fos.write(baos.toByteArray());
//				fos.flush();
//				fos.close();
				
				InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
				OssUtil sOssUtil = new OssUtil();
				String qrurl = sOssUtil.uploadFile(is2,"QrCode/"+company+"/"+softwareType+"/"+scene+".jpg");			
				Map<String, Object> map = new HashMap<>();
				map.put("qrurl", qrurl);
				result = new Gson().toJson(map);
			}
		} catch (Exception e) {
			System.out.println("[POST请求]向地址：" + url + " 发送数据：" + param + " 发生错误!");
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.out.println("关闭流异常");
				e.printStackTrace();
			}
		}
		return result;
	}

	public  String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(4000);
			conn.connect();// 建立实际的连接
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.out.println("关闭流异常");
				e.printStackTrace();
			}
		}
		return result;
	}

}
