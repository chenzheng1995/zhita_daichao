package com.zhita.util;

	import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
	 
	public class FolderUtil {                 //复制文件夹和文件夹里的所有东西
		
		public void copyDir(String sourcePath, String newPath) throws IOException {
	        File file = new File(sourcePath);
	        String[] filePath = file.list();
	        
	        if (!(new File(newPath)).exists()) {
	            (new File(newPath)).mkdir();
	        }
	        
	        for (int i = 0; i < filePath.length; i++) {
	            if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
	                copyDir(sourcePath  + file.separator  + filePath[i], newPath  + file.separator + filePath[i]);
	            }
	            
	            if (new File(sourcePath  + file.separator + filePath[i]).isFile()) {
	                copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
	            }
	            
	        }
	    }
		
		public static void copyFile(String oldPath, String newPath) throws IOException {
	        File oldFile = new File(oldPath);
	        File file = new File(newPath);
	        FileInputStream in = new FileInputStream(oldFile);
	        FileOutputStream out = new FileOutputStream(file);

	        byte[] buffer=new byte[2097152];
	        int readByte = 0;
	        while((readByte = in.read(buffer)) != -1){
	            out.write(buffer, 0, readByte);
	        }
	    
	        in.close();
            out.close();
	 
	    }
		
		
	//	========================================================================
		
		
		public boolean DeleteFolder(String sPath) {    //删除指定文件夹和文件夹下的所有东西
			boolean  flag = false;  
			      File file = new File(sPath);  
			      // 判断目录或文件是否存在  
			     if (!file.exists()) {  // 不存在返回 false  
			         return flag;  
			     } else {  
			         // 判断是否为文件  
			         if (file.isFile()) {  // 为文件时调用删除文件方法  
			             return deleteFile(sPath);  
			         } else {  // 为目录时调用删除目录方法  
			             return deleteDirectory(sPath);  
			         }  
			     }  
		}
		
		/** 
		   * 删除单个文件 
		   * @param   sPath    被删除文件的文件名 
		   * @return 单个文件删除成功返回true，否则返回false 
		   */  
		  public boolean deleteFile(String sPath) {  
		     boolean flag = false;  
		      File  file = new File(sPath);  
		      // 路径为文件且不为空则进行删除  
		     if (file.isFile() && file.exists()) {  
		         file.delete();  
		         flag = true;  
		     }  
		     return flag;  
		 }  
		  
		  /** 
		    * 删除目录（文件夹）以及目录下的文件 
		    * @param   sPath 被删除目录的文件路径 
		    * @return  目录删除成功返回true，否则返回false 
		    */  
		   public boolean deleteDirectory(String sPath) {  
		       //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		       if (!sPath.endsWith(File.separator)) {  
		          sPath = sPath + File.separator;  
		     }  
		      File dirFile = new File(sPath);  
		     //如果dir对应的文件不存在，或者不是一个目录，则退出  
		      if (!dirFile.exists() || !dirFile.isDirectory()) {  
		          return false;  
		      }  
		      boolean flag = true;  
		      //删除文件夹下的所有文件(包括子目录)  
		      File[] files = dirFile.listFiles();  
		      for (int i = 0; i < files.length; i++) {  
		          //删除子文件  
		          if (files[i].isFile()) {  
		              flag = deleteFile(files[i].getAbsolutePath());  
		              if (!flag) break;  
		          } //删除子目录  
		          else {  
		             flag = deleteDirectory(files[i].getAbsolutePath());  
		             if (!flag) break;  
		          }  
		      }  
		      if (!flag) return false;  
		     //删除当前目录  
		      if (dirFile.delete()) {  
		          return true;  
		      } else {  
		          return false;  
		      }  
		  }  
		   
		   
		   
		  // ==========================================================================
		   
			   
			   
			   /**
			    * 
			    * @param oldFilepath 旧文件所在路径
			    * @param newFileName 新的文件名
			    * @throws IOException
			    */
			   public void renameFolder(String oldFilepath,String newFileName) throws IOException {  //修改文件的文件名
				    File oldFile = new File(oldFilepath);
				    if(!oldFile.exists())
				    {
				     oldFile.createNewFile();
				    }
				    System.out.println("修改前文件名称是："+oldFile.getName());
				    String rootPath = oldFile.getParent();
				    System.out.println("根路径是："+rootPath);
				    File newFile = new File(rootPath + File.separator + newFileName);
				    System.out.println("修改后文件名称是："+newFile.getName());
				    if (oldFile.renameTo(newFile)) 
				    {
				     System.out.println("修改成功!");
				    } 
				    else 
				    {
				     System.out.println("修改失败");
				    }



			   }
			   

			    
/*	================================================================	*/	    


}
