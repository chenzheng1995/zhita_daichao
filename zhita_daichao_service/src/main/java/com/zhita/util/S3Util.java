package com.zhita.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Util {
	static AmazonS3 s3;
	static String AWS_ACCESS_KEY = "AKIAI5WGBFVGY4W3HA3Q"; // 【你的 access_key】
	static String AWS_SECRET_KEY = "mp8kve4csttv9Isht6kqHFd+XYhGC+GlJKSNrdrF"; // 【你的 aws_secret_key】
	String bucketName = "wx-dc"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶

	    static {
	        s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
	        s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1)); // 此处根据自己的 s3 地区位置改变
	    }

	    public String uploadToS3(File tempFile, String remoteFileName) throws IOException {
	        try {
	            String bucketPath = bucketName + "/upload" ;
	            s3.putObject(new PutObjectRequest(bucketPath, remoteFileName, tempFile)
	                    .withCannedAcl(CannedAccessControlList.PublicRead));
	            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, remoteFileName);
	            URL url = s3.generatePresignedUrl(urlRequest);
	            return url.toString();
	        } catch (AmazonServiceException ase) {
	            ase.printStackTrace();
	        } catch (AmazonClientException ace) {
	            ace.printStackTrace();
	        }
	        return null;
	    }

	    public static void main(String[] args) throws IOException {
	    	S3Util s3Util = new S3Util();
	    	 File uploadFile = new File("C:\\Users\\Administrator\\Desktop\\image\\1.png");
		        String uploadKey = "test/1.png";
		        s3Util.uploadToS3(uploadFile,uploadKey);
		}
	    
	}
