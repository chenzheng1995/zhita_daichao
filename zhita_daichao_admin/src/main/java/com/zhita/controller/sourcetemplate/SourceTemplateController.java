package com.zhita.controller.sourcetemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.SourceTemplate;
import com.zhita.service.sourcetemplate.SourceTemplateService;
import com.zhita.util.FolderUtil;
import com.zhita.util.ZipUtil;

@Controller
@RequestMapping("/SourceTemplate")
public class SourceTemplateController {

		
		@Autowired
		SourceTemplateService sourceTemplateService;

		
		//添加或修改模板		
		/**
		 * 
		 * @param file 模板的压缩包
		 * @param templateName 模板的名字
		 * @throws IOException
		 */		
		@RequestMapping("/UploadFolder")
		@ResponseBody
		@Transactional
		public void UploadFolder(MultipartFile file,String templateName) throws IOException {
			Integer number = sourceTemplateService.getid(templateName);
			if(number==null) {
				sourceTemplateService.setTemplate(templateName);
			}
            FolderUtil folderUtil = new FolderUtil();
		    File toFile = null;
		    if(file.getSize()<=0){
		        file = null;
		    }else {
		            InputStream ins = null;
		            ins = file.getInputStream();
		            toFile = new File(file.getOriginalFilename());
		            inputStreamToFile(ins, toFile);
		            ins.close();
		    }
		    String FileName = toFile.getName().replace(".zip","");
            ZipUtil zipUtil = new ZipUtil();
            zipUtil.unZipFiles(toFile,"E:\\nginx-1.14.2\\html\\dist\\promote\\");   //将文件夹解压
            folderUtil.DeleteFolder("E:\\nginx-1.14.2\\html\\dist\\promote\\"+templateName);//删除文件夹
            folderUtil.renameFolder("E:\\nginx-1.14.2\\html\\dist\\promote\\"+FileName,templateName);//重命名文件夹
           			
		}
		
		
		
		
		//根据模板的名称获取缩略图		
		/**
		 * 
		 * 
		 * @param templateName 模板的名字
		 * @throws IOException
		 */		
		@RequestMapping("/getThumbnail")
		@ResponseBody
		@Transactional
		public String getThumbnail(String templateName){
			String ThumbnailPath = "http://tg.rong51dai.com/promote/"+templateName+"/image.jpg";
			return ThumbnailPath;
           			
		}
		
		
		//获取模板的所有信息
		/**
		 * 
		 * 
		 * @param templateName 模板的名字
		 * @throws IOException
		 */		
		@RequestMapping("/getTemplate")
		@ResponseBody
		@Transactional
		public List<SourceTemplate> getTemplate(){
			List<SourceTemplate> list = sourceTemplateService.getTemplate ();
			return list;          			
		}
		
		
		
		
		//MultipartFile转File的方法
		public static void inputStreamToFile(InputStream ins, File file) {
		    try {
		        OutputStream os = new FileOutputStream(file);
		        int bytesRead = 0;
		        byte[] buffer = new byte[8192];
		        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
		            os.write(buffer, 0, bytesRead);
		        }
		        os.close();
		        ins.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		}	
}
