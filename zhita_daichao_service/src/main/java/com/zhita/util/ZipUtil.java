package com.zhita.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

//    public static void main(String[] args) {
//        zipDir("/Users/xcl/work2018/FHM_car300/src/test/java", "/Users/xcl/work2018/FHM_car300/src/test/java/java.zip");
//        //zipFile("/Users/xcl/work2018/FHM_car300/src/test/java/test.bpmn","/Users/xcl/work2018/FHM_car300/src/test/java/test.bpmn.zip");
//    }

    public static void zipFile(String srcFile, String zipFile) {
        File f = new File(srcFile);
        if (f.exists() && f.isFile()) {
            zipFiles(Arrays.asList(f), zipFile);
        }
    }

    public static void zipDir(String srcDir, String zipFile) {
        File src = new File(srcDir);
        if (src.exists() && src.isDirectory()) {
            List<File> files = Arrays.asList(src.listFiles());
            zipFiles(files, zipFile);
        }
    }

    public static void zipFiles(List<File> files, String zipDestFile) {
        File zipFile = new File(zipDestFile);
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            zipPutFiles(files, zos, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void zipPutFiles(List<File> files, ZipOutputStream zos, String zipDir) {
        files.forEach(f -> {
            File file = f;
            if (f.isFile()) {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
                    String baseDir = zipDir;
                    ZipEntry entry = new ZipEntry(baseDir + file.getName());
                    zos.putNextEntry(entry);
                    int count;
                    byte[] buf = new byte[1024];
                    while ((count = bis.read(buf)) != -1) {
                        zos.write(buf, 0, count);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (f.isDirectory()) {
                String baseDir = zipDir + f.getName() + "/";
                try {
                    zipPutFiles(Arrays.asList(f.listFiles()), zos, baseDir);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    
   /* ================================================================================*/
    
    /**
     * 
     * @param zipPath 压缩包路径
     * @param descDir 解压后的文件夹路径
     */
    public void unZipFiles(File zipFile,String descDir)throws IOException
    {
      File pathFile = new File(descDir);
      if(!pathFile.exists())
      {
        pathFile.mkdirs();
      }
      //解决zip文件中有中文目录或者中文文件
      ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
      for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
      {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        String zipEntryName = entry.getName();
        InputStream in = zip.getInputStream(entry);
        String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
        //判断路径是否存在,不存在则创建文件路径
        File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
        if(!file.exists())
        {
          file.mkdirs();
        }
        //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
        if(new File(outPath).isDirectory())
        {
          continue;
        }
        //输出文件路径信息
        System.out.println(outPath);
        OutputStream out = new FileOutputStream(outPath);
        byte[] buf1 = new byte[1024];
        int len;
        while((len=in.read(buf1))>0)
        {
          out.write(buf1,0,len);
        }
        in.close();
        out.close();
      }
      System.out.println("******************解压完毕********************");
    }
//    public static void main(String[] args) throws IOException {
//      /**
//       * 解压文件
//       */
//      File zipFile = new File("d:/资料.zip");
//      String path = "d:/zipfile/";
//      unZipFiles(zipFile, path);
//    }
    
    
}
