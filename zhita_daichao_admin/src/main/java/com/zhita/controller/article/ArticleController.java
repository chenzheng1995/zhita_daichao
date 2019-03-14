package com.zhita.controller.article;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.News;
import com.zhita.service.article.ArticleService;
import com.zhita.service.newstype.NewsTypeService;
import com.zhita.util.ArticleUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.PostAndGet;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping(value = "/news")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	NewsTypeService newsTypeService;

//增加文章
	/**
	 * 
	 * @param title      文章标题
	 * @param company    公司名
	 * @param titleImage 标题图片 可以不传
	 * @param author     作者
	 * @param content    文章内容
	 * @param isStick    是否置顶(1置顶，0不置顶) 可以不传
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/setnews")
	@Transactional
	public Map<String, String> setnews(String title, String company, MultipartFile titleImage, String author,
			String content, String isStick,String typename) throws Exception {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(company) || StringUtils.isEmpty(author)
				|| StringUtils.isEmpty(content) || StringUtils.isEmpty(isStick)) {
			map.put("msg", "title,company,author,content,typename和isStick不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			Integer typeId = newsTypeService.getTypeId(typename,company);
			String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
			OssUtil ossUtil = new OssUtil();
			String ossimagePath = null;
			ByteArrayInputStream InputStringStream = new ByteArrayInputStream(content.getBytes());
			String ossarticlePath = ossUtil.uploadFile(InputStringStream, "news/article/" + title + ".txt");
			if (titleImage != null) {
				if (titleImage.getSize() != 0) {// 判断上传的文件是否为空
					String path = null;// 文件路径
					String type = null;// 文件类型
					InputStream iStream = titleImage.getInputStream();
					String fileName = titleImage.getOriginalFilename();// 文件原名称
					// 判断文件类型
					type = fileName.indexOf(".") != -1
							? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
							: null;
					if (type != null) {// 判断文件类型是否为空
						if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
								|| "JPG".equals(type.toUpperCase())) {
							// 自定义的文件名称
							String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
							// 设置存放图片文件的路径
							path = "news/image/" + /* System.getProperty("file.separator")+ */trueFileName;
							ossimagePath = ossUtil.uploadFile(iStream, path);
							if (ossimagePath.substring(0, 5).equals("https")) {
								System.out.println("路径为：" + ossimagePath);
								map.put("msg", "图片上传成功");
								map.put("SCode", "201");
							}

							System.out.println("存放图片文件的路径:" + ossimagePath);
						} else {
							map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
							map.put("SCode", "402");
							return map;
						}
					} else {
						map.put("msg", "文件类型为空");
						map.put("SCode", "403");
						return map;
					}
				}

				int number = articleService.setnews(title, company, ossimagePath, author, ossarticlePath, isStick,
						registrationTime,typeId);
				if (number == 1) {
					map.put("msg", "数据插入成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "用户数据插入失败");
					map.put("SCode", "405");
				}
			}else {
				int number = articleService.setNewsNotOssimagePath(title, company, author, ossarticlePath, isStick,registrationTime,typeId);
				if (number == 1) {
					map.put("msg", "数据插入成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "用户数据插入失败");
					map.put("SCode", "405");
				}
			}

			return map;

		}
	}
	
	//获取某个类型的文章的数据,含分页	
		@ResponseBody
		@RequestMapping("/getAllnews")
		@Transactional
		public Map<String, Object> getAllnews(Integer page,String company,String typename){
			int totalCount =0;
			List<News> list = null;
			Map<String, Object> map = new HashMap<>();
			Integer typeId = newsTypeService.getTypeId(typename,company);
			if("全部".equals(typename)) {
				totalCount=articleService.pageCountAll1(company);//该方法是查询文章总条数
			}else {
			    totalCount=articleService.pageCount1(company,typeId);//该方法是查询文章总条数
			}
		  	PageUtil pageUtil=new PageUtil(page,10,totalCount);
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
		  	Timestamps timestamps = new Timestamps();
		  	String date = null;
		  	if("全部".equals(typename)) {
		  	    list=articleService.getAdminNewsByAll(pageUtil.getPage(),pageUtil.getPageSize(),company);
		  	}else {
		  	    list=articleService.getAdminAllnews(pageUtil.getPage(),pageUtil.getPageSize(),company,typeId);	
		  	} 	
		      for (News news : list) {   	  
		       date  = news.getDate(); //获取时间戳
		       date = timestamps.stampToDate1(date);   
		       news.setDate(date);
				}
		      map.put("AllNewsList", list);
		      map.put("pageutil", pageUtil);
			return map;
			
		}
		
	//根据id获取文章内容
		@ResponseBody
		@RequestMapping("/getcontent")
		@Transactional
		public Map<String, Object> getcontent(Integer id){
			Map<String, Object> map = new HashMap<>();
		  	PostAndGet postAndGet =new PostAndGet();
		    ArticleUtil articleUtil = new ArticleUtil();
			String contentUrl = articleService.getcontent(id);
			String content = postAndGet.sendGet1(contentUrl);//获取txt文件的内容
			String result = articleUtil.txt2String(content);
			map.put("content",result);			
			return map;
			
		}	
		

	
	//修改文章
		/**
		 * 
		 * @param title      文章标题
		 * @param company    公司名
		 * @param titleImage 标题图片 可以不传
		 * @param author     作者
		 * @param content    文章内容
		 * @param isStick    是否置顶(1置顶，0不置顶) 可以不传
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping("/updatenews")
		@Transactional
		public Map<String, String> updatenews(String title, String company, MultipartFile titleImage, String author,
				String content, String isStick,int id,String typename) throws Exception {
			Map<String, String> map = new HashMap<>();
			if (StringUtils.isEmpty(title) || StringUtils.isEmpty(company) || StringUtils.isEmpty(author)
					|| StringUtils.isEmpty(content) || StringUtils.isEmpty(isStick)) {
				map.put("msg", "title,company,author,content,typename和isStick不能为空");
				map.put("SCode", "401");
				return map;
			} else {
				Integer typeId = newsTypeService.getTypeId(typename,company);
				String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
				OssUtil ossUtil = new OssUtil();
				String ossimagePath = null;
				
				if(titleImage==null) {
					ossimagePath = "0";
				}
				ByteArrayInputStream InputStringStream = new ByteArrayInputStream(content.getBytes());

				String ossarticlePath = ossUtil.uploadFile(InputStringStream, "news/article/" + title + ".txt");
				if(titleImage!=null) {
					if (titleImage.getSize() != 0) {// 判断上传的文件是否为空
						String path = null;// 文件路径
						String type = null;// 文件类型
						InputStream iStream = titleImage.getInputStream();
						String fileName = titleImage.getOriginalFilename();// 文件原名称
						// 判断文件类型
						type = fileName.indexOf(".") != -1
								? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
								: null;
						if (type != null) {// 判断文件类型是否为空
							if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
									|| "JPG".equals(type.toUpperCase())) {
								// 自定义的文件名称
								String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
								// 设置存放图片文件的路径
								path = "news/image/" + /* System.getProperty("file.separator")+ */trueFileName;
								ossimagePath = ossUtil.uploadFile(iStream, path);
								if (ossimagePath.substring(0, 5).equals("https")) {
									System.out.println("路径为：" + ossimagePath);
									map.put("msg", "图片上传成功");
									map.put("SCode", "201");
								}

								System.out.println("存放图片文件的路径:" + ossimagePath);
							} else {
								map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
								map.put("SCode", "402");
								return map;
							}
						} else {
							map.put("msg", "文件类型为空");
							map.put("SCode", "403");
							return map;
						}
					}
				}

					int number = articleService.updatenews(title, company, ossimagePath, author, ossarticlePath, isStick,
							registrationTime,id,typeId);
					if (number == 1) {
						map.put("msg", "数据修改成功");
						map.put("SCode", "200");
					} else {
						map.put("msg", "数据修改失败");
						map.put("SCode", "405");
					}
				}
				return map;

			}
		
	//删除文章（假删除）
		
		@ResponseBody
		@RequestMapping("/deletenews")
		@Transactional
		public Map<String, String> deletenews(int id) {
			Map<String, String> map = new HashMap<>();
			int number = articleService.deletenews(id);
			if (number == 1) {
				map.put("msg", "数据修改成功");
				map.put("SCode", "200");
			} else {
				map.put("msg", "数据修改失败");
				map.put("SCode", "405");
			}

			return map;
		}
		
		//查询文章分类
		@ResponseBody
		@RequestMapping("/getnewstype")
		@Transactional
		public Map<String, Object> getnewstype(String company){
			Map<String, Object> map = new HashMap<>();
			List<String> typelist = new ArrayList<>();
			typelist = newsTypeService.getnewstype(company);
		    map.put("typelist", typelist);
			return map;
		}
}