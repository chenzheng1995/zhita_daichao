package com.zhita.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;


public class MD5Util {
	public String EncoderByMd5(String str) {
		if(str==null){
			return null;
		}
		String newstr =null;
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			
			newstr =new String(Base64.encodeBase64(md5.digest(str.getBytes("UTF-8"))),"utf-8");//MD5加密后对byte数组base64编码
		} catch (NoSuchAlgorithmException e) {
			try {
				throw new Exception("md5加密失败，NoSuchAlgorithmException");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			try {
				throw new Exception("base64编码失败，UnsupportedEncoding");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	    return newstr;
	}
}
