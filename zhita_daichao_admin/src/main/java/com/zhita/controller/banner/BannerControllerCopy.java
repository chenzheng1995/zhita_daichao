package com.zhita.controller.banner;

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

import com.zhita.model.manage.ShufflingFigureCopy;
import com.zhita.service.banner.IntBannerServiceCopy;
import com.zhita.service.registe.IntRegisteCopyService;
import com.zhita.util.FolderUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;
/**
 * 轮播图
 * @author lhq
 * @{date} 2019年4月9日
 */
@Controller
@RequestMapping("/bannerCopy")
public class BannerControllerCopy {
	@Autowired
	private IntBannerServiceCopy intBannerServiceCopy;
	@Autowired
	private IntRegisteCopyService intRegisteCopyService;
	
	    //后台管理---查询轮播图全部信息,含分页
		@ResponseBody
		@RequestMapping("/queryAllCopy")
	    public Map<String,Object> queryAll(Integer page,String string){
			string = string.replaceAll("\"", "").replace("[","").replace("]","");
			String [] company= string.split(",");
			PageUtil pageUtil=null;
			List<ShufflingFigureCopy> list=new ArrayList<>();
			List<ShufflingFigureCopy> listto=new ArrayList<>();
			if(company.length==1) {
				
				System.out.println("company.length==1");
				
		    	int totalCount=intBannerServiceCopy.pageCountCopy(company[0]);//该方法是查询轮播图总数量
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
		    	listto=intBannerServiceCopy.queryAllCopy(company[0],pageUtil.getPage(),pageUtil.getPageSize());
		    	pageUtil=new PageUtil(page,10,totalCount);
			}
			else if(company.length>1) {
				
				System.out.println("company.length>1");
				
	    		List<ShufflingFigureCopy> listfor=null;
				for (int i = 0; i < company.length; i++) {
			    	listfor=intBannerServiceCopy.queryAll1Copy(company[i]);

	            	list.addAll(listfor);
				}
				
				System.out.println("传进工具类的page"+page);
				
				ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
				listto.addAll(listPageUtil.getData());
				
				pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
			}
	    	HashMap<String,Object> map=new HashMap<>();
	    	map.put("listshuff",listto);
	    	map.put("pageutil", pageUtil);
	    	map.put("company", company);
	    	return map;
	    }
	    //后台管理---根据标题字段模糊查询轮播图信息，含分页
		@ResponseBody
		@RequestMapping("/queryAllByLikeCopy")
	    public Map<String,Object> queryAllByLike(String title,Integer page,String string){
			PageUtil pageUtil=null;
			List<ShufflingFigureCopy> list=new ArrayList<>();
			List<ShufflingFigureCopy> listto=new ArrayList<>();
			string = string.replaceAll("\"", "").replace("[","").replace("]","");
			String [] company=string.split(",");
			
			//标题为空并且公司名不为空  公司名选择的是  全部项
			if((title==null||"".equals(title))&&(company.length>1)) {
				
				System.out.println("第一个if");
				
	    		List<ShufflingFigureCopy> listfor=null;
				for (int i = 0; i < company.length; i++) {
			    	listfor=intBannerServiceCopy.queryAll1Copy(company[i]);
	            	list.addAll(listfor);
				}
				
				
				System.out.println("传进工具类的page"+page);
				
				ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
				listto.addAll(listPageUtil.getData());
				
				pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
				
			}
			//标题为空并且公司名不为空  公司名选择的不是  全部项
			else if((title==null||"".equals(title))&&(company.length==1)) {
				
				System.out.println("第二个if");
				
		    	int totalCount=intBannerServiceCopy.pageCountCopy(company[0]);//该方法是查询轮播图总数量
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
		    	listto=intBannerServiceCopy.queryAllCopy(company[0],pageUtil.getPage(),pageUtil.getPageSize());
		    	pageUtil=new PageUtil(page,10,totalCount);
			}
			//标题不为空并且公司名不为空  公司名选择的是  全部项
			else if((title!=null||!"".equals(title))&&(company.length>1)) {
				
				System.out.println("第三个if");
				
	    		List<ShufflingFigureCopy> listfor=null;
				for (int i = 0; i < company.length; i++) {
			    	listfor=intBannerServiceCopy.queryAllByLike1Copy(title,company[i]);
	            	list.addAll(listfor);
				}
				
				System.out.println("传进工具类的page"+page);
				
				ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
				listto.addAll(listPageUtil.getData());
				
				pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
				
			}
			//标题不为空并且公司名不为空  公司名选择的不是  全部项
			else if((title!=null||!"".equals(title))&&(company.length==1)){
				
				System.out.println("第四个if");
				
		    	int totalCount=intBannerServiceCopy.pageCountByLikeCopy(title,company[0]);//该方法是根据标题模糊查询轮播图总数量
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
		    	listto=intBannerServiceCopy.queryAllByLikeCopy(title,company[0],pageUtil.getPage(),pageUtil.getPageSize());
		    	pageUtil=new PageUtil(page,10,totalCount);
				
			}
	    	HashMap<String,Object> map=new HashMap<>();
	    	map.put("listshuffByLike",listto);
	    	map.put("pageutil", pageUtil);
	    	return map;
	    }
		
		//后台管理---查询source_dad_son表的所有一级渠道 
	    @ResponseBody
	    @RequestMapping("/selOneSouceCopy")
	    public List<String> selAllName(){
			List<String> listOneSource=intRegisteCopyService.selOneSource();
	    	return listOneSource;
	    }
	    
	    //后台管理---查询source_dad_son表    根据一级渠道查询   当前一级渠道下的所有二级渠道
	    @ResponseBody
	    @RequestMapping("/selTwoSouceCopy")
	    public List<String> selTwoSouceCopy(String oneSouce){
	    	List<String> list=intRegisteCopyService.selTwoSouceCopy(oneSouce);
	    	return list;
	    }
		
	    //后台管理---添加轮播图信息
		@Transactional
		@ResponseBody
		@RequestMapping("/AddAllCopy")
	    public Map<String, Object> AddAll(ShufflingFigureCopy shufflingFigureCopy,MultipartFile file) throws Exception{
			Map<String, Object> map = new HashMap<>();
			int count=intBannerServiceCopy.ifBusinessNameIfExistCopy(shufflingFigureCopy.getBusinessname());
			if(count==0) {
				map.put("noexist", 0);//说明文本框的商家名称是不正确的
			}else {
				map.put("exist", 1);//说明文本框的商家名称是正确的
			}
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
						path = "D://nginx-1.14.2/html/dist/image/shuffling_figure/" + /* System.getProperty("file.separator")+ */trueFileName;
//						OssUtil ossUtil = new OssUtil();
//						String ossPath = ossUtil.uploadFile(iStream, path);
//						if(ossPath.substring(0, 5).equals("https")) {
//							System.out.println("路径为："+ossPath);
//							shufflingFigure.setCover(ossPath);
//							map.put("msg", "图片上传成功");
//						}
						InputStream inStream = file.getInputStream();
						FolderUtil folderUtil = new FolderUtil();
						String code = folderUtil.uploadImage(inStream, path);
						if(code.equals("200")) {
							shufflingFigureCopy.setCover("http://tg.mis8888.com/image/shuffling_figure/"+trueFileName);
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
			intBannerServiceCopy.AddAllCopy(shufflingFigureCopy);
	    	return map;
	    }
	    //后台管理 ---根据主键id查询出轮播图信息
		@ResponseBody
		@RequestMapping("/selectByPrimaryKeyCopy")
	    public ShufflingFigureCopy selectByPrimaryKey(Integer id) {
	    	ShufflingFigureCopy shufflingFigureCopy=intBannerServiceCopy.selectByPrimaryKeyCopy(id);
	    	return shufflingFigureCopy;
	    }
	    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存
		@Transactional
		@ResponseBody
		@RequestMapping("/updateShufflingFigureCopy")
	    public Map<String, Object> updateShufflingFigure(ShufflingFigureCopy shufflingFigureCopy,MultipartFile file) throws Exception{
			System.out.println(shufflingFigureCopy+"------------");
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
						path = "shuffling_figure/" + /* System.getProperty("file.separator")+ */trueFileName;
						OssUtil ossUtil = new OssUtil();
						String ossPath = ossUtil.uploadFile(iStream, path);
						if(ossPath.substring(0, 5).equals("https")) {
							System.out.println("路径为："+ossPath);
							shufflingFigureCopy.setCover(ossPath);
							map.put("msg", "图片上传成功");
						}
						
						System.out.println("存放图片文件的路径:" + ossPath);
					} else {
						map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
						return map;
					}
				} else {
					map.put("msg", "文件类型为空");
					return map;
				}
			}else {
				int id = shufflingFigureCopy.getId();
				String cover = intBannerServiceCopy.getCoverCopy(id); //通过传过来的轮播图id，查询图片的URL
				shufflingFigureCopy.setCover(cover);
			} 
			intBannerServiceCopy.updateShufflingFigureCopy(shufflingFigureCopy);
	    	return map;
	    }
	    //后台管理---根据删除按钮，修改轮播图假删除状态
		@Transactional
		@ResponseBody
		@RequestMapping("/upaFalseDelCopy")
	    public int upaFalseDel(Integer id) {
	    	int num=intBannerServiceCopy.upaFalseDelCopy(id);
	    	return num;
	    }
		//后台管理---根据前端传过来的状态值，对当前对象的状态值进行修改
		@Transactional
		@ResponseBody
		@RequestMapping("/upaStateCopy")
		public int upaState(String state,Integer id) {
			int num=0;
			if(state.equals("1")) {
				num=intBannerServiceCopy.upaStateOpenCopy(id);
			}else {
				num=intBannerServiceCopy.upaStateCloseCopy(id);
			}
			return num;
		}
}
