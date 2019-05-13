package com.zhita.controller.adv;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.Advertising;
import com.zhita.service.adv.IntAdvService;
import com.zhita.util.FolderUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/adv")
public class AdvController {
	@Autowired
	private IntAdvService intAdvService;
	
    //后台管理---查询广告表全部信息,含分页
	@ResponseBody
	@RequestMapping("/queryAll")
    public Map<String,Object> queryAll(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
		PageUtil pageUtil=null;
		List<Advertising> list=new ArrayList<>();
		List<Advertising> listto=new ArrayList<>();
		if(company.length==1) {
			
			System.out.println("company.length==1");
			
	    	int totalCount=intAdvService.pageCount(company[0]);//该方法是查询广告表总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intAdvService.queryAll(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		List<Advertising> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intAdvService.queryAll1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listadv",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
    //后台管理---根据标题字段模糊查询广告表信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllByLike")
    public Map<String,Object> queryAllByLike(String title,Integer page,String string){
		PageUtil pageUtil=null;
		List<Advertising> list=new ArrayList<>();
		List<Advertising> listto=new ArrayList<>();
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company=string.split(",");
		
		//标题为空并且公司名不为空  公司名选择的是  全部项
		if((title==null||"".equals(title))&&(company.length>1)) {
			
			System.out.println("第一个If");
			
	   		List<Advertising> listfor=null;
	   		for (int i = 0; i < company.length; i++) {
		    	listfor=intAdvService.queryAll1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());		
		}
		//标题为空并且公司名不为空  公司名选择的不是  全部项
		else if((title==null||"".equals(title))&&(company.length==1)) {
			
			System.out.println("第二个If");
			
	    	int totalCount=intAdvService.pageCount(company[0]);//该方法是查询广告表总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intAdvService.queryAll(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		//标题不为空并且公司名不为空  公司名选择的是  全部项
		else if((title!=null||!"".equals(title))&&(company.length>1)) {
			
			System.out.println("第三个if");
			
	   		List<Advertising> listfor=null;
	   		for (int i = 0; i < company.length; i++) {
		    	listfor=intAdvService.queryAllByLike1(title,company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());		
		}
		//标题不为空并且公司名不为空  公司名选择的不是  全部项
		else if((title!=null||!"".equals(title))&&(company.length==1)){ 
			
			System.out.println("第四个if");
			
	    	int totalCount=intAdvService.pageCountByLike(title,company[0]);//该方法是根据标题模糊查询轮播图总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intAdvService.queryAllByLike(title,company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
			
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listadvByLike",listto);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---添加广告表信息
	@Transactional
	@ResponseBody
	@RequestMapping("/AddAll")
    public Map<String, Object> AddAll(Advertising advertising,MultipartFile file) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					path = "D://nginx-1.14.2/html/dist/image/advertising/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						advertising.setCover(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					InputStream inStream = file.getInputStream();
					FolderUtil folderUtil = new FolderUtil();
					String code = folderUtil.uploadImage(inStream, path);
					if(code.equals("200")) {
						advertising.setCover("http://tg.mis8888.com/image/advertising/"+trueFileName);
						map.put("msg", "图片上传成功");
					}else {
						map.put("msg", "图片上传失败");
					}
					
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			map.put("msg", "请上传图片");
			return map;
		} 
    	intAdvService.AddAll(advertising);
    	return map;
    }
    //后台管理 ---根据主键id查询出广告表信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Advertising selectByPrimaryKey(Integer id) {
		Advertising advertising=intAdvService.selectByPrimaryKey(id);
    	return advertising;
    }
	//后台管理---根据传过来的广告对象，对当前对象进行修改保存
	@Transactional
	@ResponseBody
	@RequestMapping("/updateAdvertising")
    public Map<String, Object> updateAdvertising(Advertising advertising,MultipartFile file) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (file.getSize()!=0) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					path = "D://nginx-1.14.2/html/dist/image/advertising/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						advertising.setCover(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					InputStream inStream = file.getInputStream();
					FolderUtil folderUtil = new FolderUtil();
					String code = folderUtil.uploadImage(inStream, path);
					if(code.equals("200")) {
						advertising.setCover("http://tg.mis8888.com/image/advertising/"+trueFileName);
						map.put("msg", "图片上传成功");
					}else {
						map.put("msg", "图片上传失败");
					}
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			int id = advertising.getId();
			String cover = intAdvService.getCover(id); //通过传过来的广告id，查询商标的URL，查询商标的URL
			advertising.setCover(cover);
		} 
    	intAdvService.updateAdvertising(advertising);
    	return map;
    }
	

	
    //后台管理---根据删除按钮，修改广告表假删除状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaFalseDel")
    public int upaFalseDel(Integer id) {
    	int num=intAdvService.upaFalseDel(id);
    	return num;
    }
	//后台管理---根据前端传过来的状态值，对当前对象的状态值进行修改
	@Transactional
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intAdvService.upaStateOpen(id);
		}else {
			num=intAdvService.upaStateClose(id);
		}
		return num;
	}
	
}
