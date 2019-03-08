package com.zhita.controller.article;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.service.article.ArticleService;
import com.zhita.util.OssUtil;

@Controller
@RequestMapping(value = "/news")
public class ArticleController {
	@Autowired
	ArticleService articleService;

//小程序---记账页面----查询出所有收入大分类
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
			String content, String isStick) throws Exception {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(company) || StringUtils.isEmpty(author)
				|| StringUtils.isEmpty(content) || StringUtils.isEmpty(isStick)) {
			map.put("msg", "title,company,author,content和isStick不能为空");
			map.put("SCode", "401");
			return map;
		} else {
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
						registrationTime);
				if (number == 1) {
					map.put("msg", "数据插入成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "用户数据插入失败");
					map.put("SCode", "405");
				}
			}else {
				int number = articleService.setNewsNotOssimagePath(title, company, author, ossarticlePath, isStick,registrationTime);
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

}