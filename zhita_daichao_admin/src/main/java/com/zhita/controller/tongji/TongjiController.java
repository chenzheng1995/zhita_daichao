package com.zhita.controller.tongji;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.SourceTongji;
import com.zhita.model.manage.TongjiSorce;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.tongji.IntTongjiService;
import com.zhita.util.DateListUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.RedisClientUtil;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping("/tongji")
public class TongjiController {
	@Autowired
	private IntTongjiService intTongjiService;
	@Autowired
	private IntRegisteService intRegisteService;
	@Autowired
	private CommodityFootprintService commodityFootprintService;
	@Autowired
	private IntMerchantService intMerchantService;

	// 后台管理----渠道方看的渠道统计，含分页
	@ResponseBody
	@RequestMapping("/queryAllTongji")
	public Map<String, Object> queryAllTongji(Integer page, String company, String sourceName) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		List<TongjiSorce> listsource = new ArrayList<>();
		List<TongjiSorce> listsourceto = new ArrayList<>(); 
		RedisClientUtil redisClientUtil = new RedisClientUtil();
		Timestamps timestamps = new Timestamps();
		List<String> list = intTongjiService.queryTime(company, sourceName);// 查询出当前渠道所有的注册时间
		if(list!=null&&!list.isEmpty()) {
		List<String> list1 = new ArrayList<>();// 用来存时间戳转换后的时间
		for (int i = 0; i < list.size(); i++) {
			list1.add(Timestamps.stampToDate1(list.get(i)));
		}
		HashSet h = new HashSet(list1);
		list1.clear();
		list1.addAll(h);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		for (int i = 0; i < list1.size(); i++) {			
//			String startTime = list1.get(i) + " " + "00:00:00";
			String startTime = list1.get(i);
			String startTimestamps = timestamps.dateToStamp(startTime);
//			String endTime = list1.get(i) + " " + "23:59:59";
			String endTime = list1.get(i);
			String endTimestamps = (Long.parseLong(timestamps.dateToStamp(endTime))+86400000)+"";
			float appnum = intTongjiService.queryApplicationNumber(company, sourceName, startTimestamps, endTimestamps);// 得到申请数  
			String discount = intTongjiService.queryDiscount(sourceName, company);// 得到折扣率
			int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
			int uv = 0;
			String cvr = null;
			if (redisClientUtil.getSourceClick(company + sourceName + list1.get(i) + "Key") == null) {
				uv = 0;
			} else {
				uv = Integer.parseInt(redisClientUtil.getSourceClick(company + sourceName + list1.get(i) + "Key"));

			}
			if ((appnum < 0.000001) || (uv == 0)) {
				cvr = 0 + "%";// 得到转化率
			} else {
				cvr = (appnum / uv) + "%";// 得到转化率
			}
			
			TongjiSorce tongjiSorce = new TongjiSorce();
			tongjiSorce.setDate(list1.get(i));// 日期
			tongjiSorce.setSourceName(sourceName);// 渠道名称
			tongjiSorce.setUv(uv);// uv
			if (appnum >= 100) {
				tongjiSorce.setAppNum((int)(appnum * discount1 / 100));// 申请数
			} else {
				tongjiSorce.setAppNum(appnum);// 申请数
			}
			tongjiSorce.setCvr(cvr);// 转化率
			listsource.add(tongjiSorce);
		}		
		
		DateListUtil.ListSort1(listsource);//将集合按照日期进行倒排序
		
		ListPageUtil listPageUtil = new ListPageUtil(listsource, page, 10);
		listsourceto.addAll(listPageUtil.getData());
		
		
		PageUtil pageUtil = new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),
				listPageUtil.getTotalCount());
		map.put("listsourceto", listsourceto);
		map.put("pageutil", pageUtil);
		}
		return map;
	}

	// 后台管理----渠道方看的渠道统计，通过时间查询，含分页
	@ResponseBody
	@RequestMapping("/queryAllTongjiByDate")
	public Object queryAllTongjiByDate(String company, String source, String date) throws ParseException {
		RedisClientUtil redisClientUtil = new RedisClientUtil();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate = sdf.format(newDate);// 传进来日期的后一天

		String startTime = Timestamps.dateToStamp(date.replace("/", "-"));// 将传进来的时间转换为时间戳格式
		String endTime = Timestamps.dateToStamp(nextDate);// 将传进来的时间的后一天转换为时间戳格式
		float appnum = intTongjiService.queryApplicationNumber(company, source, startTime, endTime);// 得到申请数
		String discount = intTongjiService.queryDiscount(source, company);// 得到折扣率
		int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
		int uv = 0;
		String cvr = null;
		if (redisClientUtil.getSourceClick(company + source + date + "Key") == null) {
			uv = 0;
		} else {
			uv = Integer.parseInt(redisClientUtil.getSourceClick(company + source + date + "Key"));

		}
		if ((appnum < 0.000001) || (uv == 0)) {
			cvr = 0 + "%";// 得到转化率
		} else {
			cvr = (appnum / uv) + "%";// 得到转化率
		}

		TongjiSorce tongjiSorce = new TongjiSorce();
		tongjiSorce.setDate(date);// 日期
		tongjiSorce.setSourceName(source);// 渠道名称
		tongjiSorce.setUv(uv);// uv
		if (appnum >= 100) {
			tongjiSorce.setAppNum(appnum * discount1 / 100);// 申请数
		} else {
			tongjiSorce.setAppNum(appnum);// 申请数
		}
		tongjiSorce.setCvr(cvr);// 转化率
		return tongjiSorce;
	}

		// 后台管理---我们自己看的统计数据 通过日期查询(某个时间段的)
		@ResponseBody
		@RequestMapping("/queryAllPage")
		public Map<String, Object> queryAllPage1(Integer page, String string, String dateStart,String dateEnd) throws ParseException {
			string = string.replaceAll("\"", "").replace("[", "").replace("]", "");
			String[] company = string.split(",");

			PageUtil pageUtil = null;
			List<Source> listsource = new ArrayList<>();
			List<TongjiSorce> listto = new ArrayList<>();
			List<TongjiSorce> listtopage = new ArrayList<>();//经过分页后的数据集合

			String startTime = Timestamps.dateToStamp(dateStart);// 将开始时间转换为时间戳格式
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dateEnd));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date newDate = calendar.getTime();
			String nextDate = sdf.format(newDate);// 结束日期的后一天
			
			String endTime = Timestamps.dateToStamp(nextDate);// 将结束日期的后一天转换为时间戳格式
			
			List<String> listdate=DateListUtil.getDays(dateStart, dateEnd);

			if (company.length == 1) {

				System.out.println("company.length==1");

				listsource = intMerchantService.queryAll(company[0]);// 查询出所有的渠道信息
				RedisClientUtil redisClientUtil = new RedisClientUtil();
				for (int i = 0; i < listsource.size(); i++) {
					String source = listsource.get(i).getSourcename();

					float appnum = intTongjiService.queryApplicationNumber(company[0], source, startTime, endTime);// 得到申请数
					String discount = intTongjiService.queryDiscount(source, company[0]);// 得到折扣率
					int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
					int sumappnum = intTongjiService.queryUV(company[0], source, startTime, endTime);// 得到点过甲方贷款商家总的人数
					int uv = 0;
					String cvr = null;
					for (int j = 0; j <listdate.size(); j++) {
						int uvi=0;
						if (redisClientUtil.getSourceClick(company[0] + source + listdate.get(j) + "Key") == null) {
							uvi = 0;
						} else {
							uvi = Integer.parseInt(redisClientUtil.getSourceClick(company[0] + source + listdate.get(j) + "Key"));
						}
						uv=uv+uvi;
					}
					if ((appnum < 0.000001) || (uv == 0)) {
						cvr = 0 + "%";// 得到转化率
					} else {
						cvr = (new DecimalFormat("#.00").format(appnum / uv * 100)) + "%";// 得到转化率
					}
					TongjiSorce tongjiSorce = new TongjiSorce();
					tongjiSorce.setSourceName(source);// 渠道名称
					tongjiSorce.setUv(uv);// uv
					tongjiSorce.setAppNum(appnum);// 真实的申请数
					if (appnum >= 100) {
						tongjiSorce.setAppNum1(appnum * discount1 / 100);// 折扣后的申请数
					} else {
						tongjiSorce.setAppNum1(appnum);
					}
					tongjiSorce.setCvr(cvr);// 转化率
					tongjiSorce.setSumappnum(sumappnum);// 点过甲方总的人数
					listto.add(tongjiSorce);
				}
			} else if (company.length > 1) {

				System.out.println("company.length>1");

				List<Source> listsourcefor = null;
				RedisClientUtil redisClientUtil = new RedisClientUtil();
				for (int j = 0; j < company.length; j++) {
					listsourcefor = intMerchantService.queryAll(company[j]);// 查询出所有的渠道信息
					listsource.addAll(listsourcefor);

					for (int i = 0; i < listsourcefor.size(); i++) {
						String source = listsourcefor.get(i).getSourcename();
						float appnum = intTongjiService.queryApplicationNumber(company[j], source, startTime, endTime);// 得到申请数
						String discount = intTongjiService.queryDiscount(source, company[j]);// 得到折扣率
						int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
						int sumappnum = intTongjiService.queryUV(company[j], source, startTime, endTime);// 得到甲方总申请数
						int uv = 0;
						String cvr = null;
						for (int k = 0; k <listdate.size(); k++) {
							int uvi=0;
							if (redisClientUtil.getSourceClick(company[0] + source + listdate.get(k) + "Key") == null) {
								uvi = 0;
							} else {
								uvi = Integer.parseInt(redisClientUtil.getSourceClick(company[j] + source + listdate.get(k) + "Key"));
							}
							uv=uv+uvi;
						}
						if ((appnum < 0.000001) || (uv == 0)) {
							cvr = 0 + "%";// 得到转化率
						} else {
							cvr = (new DecimalFormat("#.00").format(appnum / uv * 100)) + "%";// 得到转化率
						}

						TongjiSorce tongjiSorce = new TongjiSorce();
						tongjiSorce.setSourceName(source);// 渠道名称
						tongjiSorce.setUv(uv);// uv
						tongjiSorce.setAppNum(appnum);// 真实的申请数
						if (appnum >= 100) {
							tongjiSorce.setAppNum1(appnum * discount1 / 100);// 折扣后的申请数
						} else {
							tongjiSorce.setAppNum1(appnum);
						}
						tongjiSorce.setCvr(cvr);// 转化率
						tongjiSorce.setSumappnum(sumappnum);// 点过甲方总的人数
						listto.add(tongjiSorce);
					}
				}
			}
			Integer uvsum=0;
			Integer registenum=0;
			for (int i = 0; i < listto.size(); i++) {
				uvsum=uvsum+listto.get(i).getUv();
				registenum=(int) (registenum+listto.get(i).getAppNum());
			}
			if (listto.size() != 0) {
				ListPageUtil listPageUtil = new ListPageUtil(listto, page, 10);
				listtopage.addAll(listPageUtil.getData());

				pageUtil = new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),
						listPageUtil.getTotalCount());

			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("listsource", listsource);
			map.put("listtopage", listtopage);
			map.put("pageutil", pageUtil);
			map.put("company", company);
			map.put("uvsum", uvsum);
			map.put("registenum", registenum);
			return map;
		}

		// 后台管理---我们自己看的统计数据 通过日期查询
		@ResponseBody
		@RequestMapping("/queryAllPageDetail")
		public Map<String, Object> queryAllPage(String company,String sourcename, String dateStart,String dateEnd) throws ParseException {
			List<String> listdate=DateListUtil.getDays(dateStart, dateEnd);
			
			List<TongjiSorce> listto = new ArrayList<>();
			for (int i = 0; i < listdate.size(); i++) {
				String date=listdate.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(date));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date newDate = calendar.getTime();
				String nextDate = sdf.format(newDate);// 传进来日期的后一天
				String startTime = Timestamps.dateToStamp(date);// 将传进来的时间转换为时间戳格式
				String endTime = Timestamps.dateToStamp(nextDate);// 将传进来的时间的后一天转换为时间戳格式

					RedisClientUtil redisClientUtil = new RedisClientUtil();
					float appnum = intTongjiService.queryApplicationNumber(company, sourcename, startTime, endTime);// 得到申请数
					String discount = intTongjiService.queryDiscount(sourcename, company);// 得到折扣率
					int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
					int sumappnum = intTongjiService.queryUV(company, sourcename, startTime, endTime);// 得到点过甲方贷款商家总的人数
					int uv = 0;
					String cvr = null;
					if (redisClientUtil.getSourceClick(company + sourcename + date + "Key") == null) {
						uv = 0;
					} else {
						uv = Integer.parseInt(redisClientUtil.getSourceClick(company + sourcename+ date + "Key"));
					}
					if ((appnum < 0.000001) || (uv == 0)) {
						cvr = 0 + "%";// 得到转化率
					} else {
						cvr = (new DecimalFormat("#.00").format(appnum / uv * 100)) + "%";// 得到转化率
					}
					TongjiSorce tongjiSorce = new TongjiSorce();
					tongjiSorce.setDate(date);// 日期
					tongjiSorce.setSourceName(sourcename);// 渠道名称
					tongjiSorce.setUv(uv);// uv
					tongjiSorce.setAppNum(appnum);// 真实的申请数
					if (appnum >= 100) {
						tongjiSorce.setAppNum1(appnum * discount1 / 100);// 折扣后的申请数
					} else {
						tongjiSorce.setAppNum1(appnum);
					}
					tongjiSorce.setCvr(cvr);// 转化率
					tongjiSorce.setSumappnum(sumappnum);// 点过甲方总的人数
					listto.add(tongjiSorce);
			}
			DateListUtil.ListSort1(listto);
			HashMap<String, Object> map = new HashMap<>();
			map.put("listto",listto);
			return map;
		}
		
	/*// 后台管理---我们自己看的统计数据 通过日期查询
	@ResponseBody
	@RequestMapping("/queryAllPage")
	public Map<String, Object> queryAllPage(Integer page, String string, String date) throws ParseException {
		string = string.replaceAll("\"", "").replace("[", "").replace("]", "");
		String[] company = string.split(",");

		PageUtil pageUtil = null;
		List<Source> listsource = new ArrayList<>();
		List<TongjiSorce> listto = new ArrayList<>();
		List<TongjiSorce> listtopage = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate = sdf.format(newDate);// 传进来日期的后一天
		String startTime = Timestamps.dateToStamp(date);// 将传进来的时间转换为时间戳格式
		String endTime = Timestamps.dateToStamp(nextDate);// 将传进来的时间的后一天转换为时间戳格式

		if (company.length == 1) {

			System.out.println("company.length==1");

			listsource = intMerchantService.queryAll(company[0]);// 查询出所有的渠道信息
			RedisClientUtil redisClientUtil = new RedisClientUtil();
			for (int i = 0; i < listsource.size(); i++) {
				String source = listsource.get(i).getSourcename();

				float appnum = intTongjiService.queryApplicationNumber(company[0], source, startTime, endTime);// 得到申请数
				String discount = intTongjiService.queryDiscount(source, company[0]);// 得到折扣率
				int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
				int sumappnum = intTongjiService.queryUV(company[0], source, startTime, endTime);// 得到点过甲方贷款商家总的人数
				int uv = 0;
				String cvr = null;
				if (redisClientUtil.getSourceClick(company[0] + source + date + "Key") == null) {
					uv = 0;
				} else {
					uv = Integer.parseInt(redisClientUtil.getSourceClick(company[0] + source + date + "Key"));
				}
				if ((appnum < 0.000001) || (uv == 0)) {
					cvr = 0 + "%";// 得到转化率
				} else {
					cvr = (new DecimalFormat("#.00").format(appnum / uv * 100)) + "%";// 得到转化率
				}
				TongjiSorce tongjiSorce = new TongjiSorce();
				tongjiSorce.setDate(date);// 日期
				tongjiSorce.setSourceName(source);// 渠道名称
				tongjiSorce.setUv(uv);// uv
				tongjiSorce.setAppNum(appnum);// 真实的申请数
				if (appnum >= 100) {
					tongjiSorce.setAppNum1(appnum * discount1 / 100);// 折扣后的申请数
				} else {
					tongjiSorce.setAppNum1(appnum);
				}
				tongjiSorce.setCvr(cvr);// 转化率
				tongjiSorce.setSumappnum(sumappnum);// 点过甲方总的人数
				listto.add(tongjiSorce);
			}
		} else if (company.length > 1) {

			System.out.println("company.length>1");

			List<Source> listsourcefor = null;
			RedisClientUtil redisClientUtil = new RedisClientUtil();
			for (int j = 0; j < company.length; j++) {
				listsourcefor = intMerchantService.queryAll(company[j]);// 查询出所有的渠道信息
				listsource.addAll(listsourcefor);

				for (int i = 0; i < listsourcefor.size(); i++) {
					String source = listsourcefor.get(i).getSourcename();
					float appnum = intTongjiService.queryApplicationNumber(company[j], source, startTime, endTime);// 得到申请数
					String discount = intTongjiService.queryDiscount(source, company[j]);// 得到折扣率
					int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
					int sumappnum = intTongjiService.queryUV(company[j], source, startTime, endTime);// 得到甲方总申请数
					int uv = 0;
					String cvr = null;
					if (redisClientUtil.getSourceClick(company[0] + source + date + "Key") == null) {
						uv = 0;
					} else {
						uv = Integer.parseInt(redisClientUtil.getSourceClick(company[j] + source + date + "Key"));
					}
					if ((appnum < 0.000001) || (uv == 0)) {
						cvr = 0 + "%";// 得到转化率
					} else {
						cvr = (new DecimalFormat("#.00").format(appnum / uv * 100)) + "%";// 得到转化率
					}

					TongjiSorce tongjiSorce = new TongjiSorce();
					tongjiSorce.setDate(date);// 日期
					tongjiSorce.setSourceName(source);// 渠道名称
					tongjiSorce.setUv(uv);// uv
					tongjiSorce.setAppNum(appnum);// 真实的申请数
					if (appnum >= 100) {
						tongjiSorce.setAppNum1(appnum * discount1 / 100);// 折扣后的申请数
					} else {
						tongjiSorce.setAppNum1(appnum);
					}
					tongjiSorce.setCvr(cvr);// 转化率
					tongjiSorce.setSumappnum(sumappnum);// 点过甲方总的人数
					listto.add(tongjiSorce);
				}
			}
		}
		if (listto.size() != 0) {
			ListPageUtil listPageUtil = new ListPageUtil(listto, page, 10);
			listtopage.addAll(listPageUtil.getData());

			pageUtil = new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),
					listPageUtil.getTotalCount());

		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("listsource", listsource);
		map.put("listtopage", listtopage);
		map.put("pageutil", pageUtil);
		map.put("company", company);
		return map;
	}*/

	// 后台管理---统计详情功能
	@ResponseBody
	@RequestMapping("/queryAllPageBySource")
	public Map<String, Object> queryAllPageBySource(Integer page, String source, String date) throws ParseException {
		PageUtil pageUtil = null;
		List<SourceTongji> listsourceTongji = new ArrayList<>();// 刚开始查询出来的数据
		List<SourceTongji> listsourceTongjito = new ArrayList<>();// 经过处理后的数据

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate = sdf.format(newDate);// 传进来日期的后一天
		String startTime = Timestamps.dateToStamp(date.replace("/", "-"));// 将传进来的时间转换为时间戳格式
		String endTime = Timestamps.dateToStamp(nextDate);// 将传进来的时间的后一天转换为时间戳格式

		int totalCount = intTongjiService.pageCount(source, startTime, endTime);// 该方法是查询统计总条数
		pageUtil = new PageUtil(page, 10, totalCount);
		if (page < 1) {
			page = 1;
		} else if (page > pageUtil.getTotalPageCount()) {
			if (totalCount == 0) {
				page = pageUtil.getTotalPageCount() + 1;
			} else {
				page = pageUtil.getTotalPageCount();
			}
		}
		int pages = (page - 1) * pageUtil.getPageSize();
		pageUtil.setPage(pages);

		listsourceTongji = intTongjiService.queryAllPage(source, startTime, endTime, pageUtil.getPage(),
				pageUtil.getPageSize());
		pageUtil = new PageUtil(page, 10, totalCount);
		for (int i = 0; i < listsourceTongji.size(); i++) {
			int uv = intTongjiService.queryUV1(listsourceTongji.get(i).getBusinessName(),
					listsourceTongji.get(i).getSourceName(), startTime, endTime);
			SourceTongji sourceTongji = new SourceTongji();
			sourceTongji.setId(listsourceTongji.get(i).getId());
			sourceTongji.setBusinessName(listsourceTongji.get(i).getBusinessName());
			sourceTongji.setSourceName(listsourceTongji.get(i).getSourceName());
			sourceTongji.setUv(uv);
			listsourceTongjito.add(sourceTongji);
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("listsourceTongjito", listsourceTongjito);
		map.put("pageUtil", pageUtil);
		return map;
	}
}

// 后台管理---通过渠道名称 查询统计表所有信息，含分页
// @ResponseBody
// @RequestMapping("/queryAllPageBySourceName")
/*
 * public Map<String,Object> queryAllPageBySourceName(Integer page,String
 * string,String string1,String date){ string = string.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] company= string.split(",");
 * string1 = string1.replaceAll("\"", "").replace("[","").replace("]","");
 * String [] sourceName= string1.split(",");
 * 
 * PageUtil pageUtil=null; List<Source> listsource=new ArrayList<>();
 * List<TongjiSorce> listto=new ArrayList<>(); List<TongjiSorce> listtopage=new
 * ArrayList<>(); //不存在日期为空的情况 //日期为空 公司名选择的是全部 渠道选择的是全部
 * if((date==null||"".equals(date))&&(company.length>1)&&(sourceName.length>1))
 * {
 * 
 * } //日期为空 公司名选择的是全部 渠道选择的不是全部 else
 * if((date==null||"".equals(date))&&(company.length>1)&&(sourceName.length==1))
 * {
 * 
 * } //日期为空 公司名选择的不是全部 渠道选择的是全部 else
 * if((date==null||"".equals(date))&&(company.length==1)&&(sourceName.length>1))
 * {
 * 
 * } //日期为空 公司名选择的不是全部 渠道选择的不是全部 else
 * if((date==null||"".equals(date))&&(company.length==1)&&(sourceName.length==1)
 * ) {
 * 
 * } //日期不为空 公司名选择的是全部 渠道选择的是全部
 * if((date!=null||!"".equals(date))&&(company.length>1)&&(sourceName.length>1))
 * { for (int i = 0; i < sourceName.length; i++) {
 * 
 * } } //日期不为空 公司名选择的是全部 渠道选择的不是全部 else
 * if((date!=null||!"".equals(date))&&(company.length>1)&&(sourceName.length==1)
 * ) {
 * 
 * } //日期不为空 公司名选择的不是全部 渠道选择的是全部 else
 * if((date!=null||!"".equals(date))&&(company.length==1)&&(sourceName.length>1)
 * ) {
 * 
 * } //日期不为空 公司名选择的不是全部 渠道选择的不是全部 else
 * if((date!=null||!"".equals(date))&&(company.length==1)&&(sourceName.length==1
 * )) {
 * 
 * }
 * 
 * }
 */

// 后台管理---关联查询统计所有信息，含分页
/*
 * @ResponseBody
 * 
 * @RequestMapping("/queryAllPage1") public Map<String,Object>
 * queryAllPage1(Integer page,String string){ string = string.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] company= string.split(",");
 * PageUtil pageUtil=null; List<Source> listsource=new ArrayList<>();
 * List<SourceTongji> listsourceTongji=new ArrayList<>();//刚开始查询出来的数据
 * List<SourceTongji> listsourceTongjifor=new ArrayList<>();//经过封装后的数据
 * List<SourceTongji> listsourceTongjitoListPage=new ArrayList<>();//最终的数据
 * if(company.length==1) {
 * 
 * System.out.println("company.length==1");
 * 
 * listsource=intMerchantService.queryAll(company[0]);//查询出所有的渠道信息 int
 * totalCount=intTongjiService.pageCount(company[0]);//该方法是查询统计总条数 pageUtil=new
 * PageUtil(page,2,totalCount); if(page<1) { page=1; } else
 * if(page>pageUtil.getTotalPageCount()) { if(totalCount==0) {
 * page=pageUtil.getTotalPageCount()+1; }else {
 * page=pageUtil.getTotalPageCount(); } } int
 * pages=(page-1)*pageUtil.getPageSize(); pageUtil.setPage(pages);
 * 
 * listsourceTongji=intTongjiService.queryAllPage(company[0],pageUtil.getPage(),
 * pageUtil.getPageSize()); for (int i = 0; i < listsourceTongji.size(); i++) {
 * int pv=intTongjiService.queryPV(company[0],
 * listsourceTongji.get(i).getSourceName()); int
 * uv=intTongjiService.queryUV(company[0],
 * listsourceTongji.get(i).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listsourceTongji.get(i).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listsourceTongji.get(i).getId());
 * sourceTongji.setDate(listsourceTongji.get(i).getDate());
 * sourceTongji.setBusinessName(listsourceTongji.get(i).getBusinessName());
 * sourceTongji.setSourceName(listsourceTongji.get(i).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%");
 * listsourceTongjitoListPage.add(sourceTongji); } pageUtil=new
 * PageUtil(page,2,totalCount); } else if(company.length>1) {
 * 
 * System.out.println("company.length>1");
 * 
 * List<Source> listsourcefor=null; for (int i = 0; i < company.length; i++) {
 * listsourcefor=intMerchantService.queryAll(company[i]);//查询出所有的渠道信息
 * listsource.addAll(listsourcefor);
 * 
 * listsourceTongji=intTongjiService.queryAllPage1(company[i]); for (int j = 0;
 * j < listsourceTongji.size(); j++) { int
 * pv=intTongjiService.queryPV(company[i],
 * listsourceTongji.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i],
 * listsourceTongji.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listsourceTongji.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listsourceTongji.get(j).getId());
 * sourceTongji.setDate(listsourceTongji.get(j).getDate());
 * sourceTongji.setBusinessName(listsourceTongji.get(j).getBusinessName());
 * sourceTongji.setSourceName(listsourceTongji.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listsourceTongjifor.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listsourceTongjifor.size(); i++) {
 * System.out.println(listsourceTongjifor.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listsourceTongjifor,page,2);
 * listsourceTongjitoListPage.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount()); }
 * HashMap<String,Object> map=new HashMap<>(); map.put("listsource",
 * listsource);
 * map.put("listsourceTongjitoListPage",listsourceTongjitoListPage);
 * map.put("pageutil", pageUtil); map.put("company", company); return map; }
 * //后台管理---通过渠道名称 查询统计表所有信息，含分页
 * 
 * @ResponseBody
 * 
 * @RequestMapping("/queryAllPageBySourceName") public Map<String,Object>
 * queryAllPageBySourceName(Integer page,String string,String string1){ string =
 * string.replaceAll("\"", "").replace("[","").replace("]",""); String []
 * company= string.split(","); string1 = string1.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] sourceName=
 * string1.split(","); PageUtil pageUtil=null; List<SourceTongji>
 * listSourceTongjione=new ArrayList<>(); List<SourceTongji>
 * listSourceTongjitwo=new ArrayList<>(); List<SourceTongji> listone=new
 * ArrayList<>(); List<SourceTongji> listtwo=new ArrayList<>();//对集合进行处理后的集合
 * 最终集合 // List<SourceTongji> listoneto=new ArrayList<>(); //公司名选择的是全部 渠道选择的是全部
 * if((company.length>1)&&(sourceName.length>1)) {
 * 
 * System.out.println("第一个if："+"company.length>1-----sourceName.length>1");
 * 
 * for (int i = 0; i < company.length; i++) { for (int j = 0; j <
 * sourceName.length; j++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[j]);
 * for (int k = 0; k < listone.size(); k++) { int
 * pv=intTongjiService.queryPV(company[i], listone.get(k).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i], listone.get(k).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listone.get(k).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(k).getId());
 * sourceTongji.setDate(listone.get(k).getDate());
 * sourceTongji.setBusinessName(listone.get(k).getBusinessName());
 * sourceTongji.setSourceName(listone.get(k).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * } listSourceTongjitwo.addAll(listSourceTongjione); }
 * 
 * for (int i = 0; i < listSourceTongjitwo.size(); i++) {
 * System.out.println(listSourceTongjitwo.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjitwo,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的是全部 渠道选择的不是全部 else if((company.length>1)&&(sourceName.length==1)) {
 * 
 * System.out.println("第二个if："+"company.length>1-----sourceName.length==1");
 * 
 * for (int i = 0; i < company.length; i++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[0]);
 * 
 * for (int j = 0; j < listone.size(); j++) { int
 * pv=intTongjiService.queryPV(company[i], listone.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i], listone.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listone.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(j).getId());
 * sourceTongji.setDate(listone.get(j).getDate());
 * sourceTongji.setBusinessName(listone.get(j).getBusinessName());
 * sourceTongji.setSourceName(listone.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listSourceTongjione.size(); i++) {
 * System.out.println(listSourceTongjione.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的不是全部 渠道选择的是全部 else if((company.length==1)&&(sourceName.length>1)) {
 * 
 * System.out.println("第三个if："+"company.length==1-----sourceName.length>1");
 * 
 * for (int i = 0; i < sourceName.length; i++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[0],sourceName[i]);
 * 
 * for (int j = 0; j < listone.size(); j++) { int
 * pv=intTongjiService.queryPV(company[0], listone.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[0], listone.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listone.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(j).getId());
 * sourceTongji.setDate(listone.get(j).getDate());
 * sourceTongji.setBusinessName(listone.get(j).getBusinessName());
 * sourceTongji.setSourceName(listone.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listSourceTongjione.size(); i++) {
 * System.out.println(listSourceTongjione.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的不是全部 渠道选择的不是全部 else if((company.length==1)&&(sourceName.length==1))
 * {
 * 
 * System.out.println("第四个if："+"company.length==1-----sourceName.length==1");
 * 
 * int
 * totalCount=intTongjiService.pageCountBySourceName(company[0],sourceName[0]);/
 * /该方法是查询统计总条数 pageUtil=new PageUtil(page,2,totalCount); if(page<1) { page=1; }
 * else if(page>pageUtil.getTotalPageCount()) { if(totalCount==0) {
 * page=pageUtil.getTotalPageCount()+1; }else {
 * page=pageUtil.getTotalPageCount(); } } int
 * pages=(page-1)*pageUtil.getPageSize(); pageUtil.setPage(pages);
 * 
 * listone=intTongjiService.queryAllPageBySourceName(company[0],sourceName[0],
 * pageUtil.getPage(),pageUtil.getPageSize()); for (int i = 0; i <
 * listone.size(); i++) { int pv=intTongjiService.queryPV(company[0],
 * listone.get(i).getSourceName()); int uv=intTongjiService.queryUV(company[0],
 * listone.get(i).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listone.get(i).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(i).getId());
 * sourceTongji.setDate(listone.get(i).getDate());
 * sourceTongji.setBusinessName(listone.get(i).getBusinessName());
 * sourceTongji.setSourceName(listone.get(i).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listtwo.add(sourceTongji); } pageUtil=new
 * PageUtil(page,2,totalCount); }
 * 
 * HashMap<String, Object> map=new HashMap<>();
 * map.put("listSourceTongjitwo",listtwo); map.put("pageutil", pageUtil); return
 * map; }
 */

// 后台管理---通过渠道名称 查询统计表所有信息，含分页
// @ResponseBody
// @RequestMapping("/queryAllPageBySourceName")
/*
 * public Map<String,Object> queryAllPageBySourceName(Integer page,String
 * string,String string1,String date){ string = string.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] company= string.split(",");
 * string1 = string1.replaceAll("\"", "").replace("[","").replace("]","");
 * String [] sourceName= string1.split(",");
 * 
 * PageUtil pageUtil=null; List<Source> listsource=new ArrayList<>();
 * List<TongjiSorce> listto=new ArrayList<>(); List<TongjiSorce> listtopage=new
 * ArrayList<>();
 * 
 * //公司名选择的是全部 渠道选择的是全部 if((company.length>1)&&(sourceName.length>1)) {
 * List<Source> listsourcefor=null; RedisClientUtil redisClientUtil = new
 * RedisClientUtil(); for (int j = 0; j < company.length; j++) {
 * listsourcefor=intMerchantService.queryAll(company[j]);//查询出所有的渠道信息
 * listsource.addAll(listsourcefor);
 * 
 * for (int i = 0; i < listsourcefor.size(); i++) { String
 * source=listsourcefor.get(i).getSourcename(); String SourceClick =
 * redisClientUtil.get(company[j]+source+"Key");//通过key得到value,就是得到uv
 * if(SourceClick==null) { redisClientUtil.set(company[j]+source+"Key","1");
 * System.out.println(redisClientUtil.getSourceClick(company[j]+source+"Key"));
 * }else {
 * redisClientUtil.set(company[j]+source+"Key",Integer.parseInt(redisClientUtil.
 * getSourceClick(company[j]+source+"Key"))+1+"");
 * //由于value是string类型的，所以先转换成int类型，+1之后在转换成string类型
 * System.out.println(redisClientUtil.getSourceClick(company[j]+source+"Key"));
 * } float appnum=intTongjiService.queryApplicationNumber(company[j],
 * source,startTime,endTime);//得到申请数 String
 * discount=intTongjiService.queryDiscount(source, company[j]);//得到折扣率 int
 * discount1=Integer.parseInt(discount.substring(0, discount.length()-1)); int
 * sumappnum=intTongjiService.queryUV(company[j],
 * source,startTime,endTime);//得到甲方总申请数 Integer
 * uv=Integer.parseInt(redisClientUtil.getSourceClick(company[j]+source+"Key"));
 * String cvr=(appnum/uv)+"%";//得到转化率
 * 
 * TongjiSorce tongjiSorce=new TongjiSorce(); tongjiSorce.setDate(date);//日期
 * tongjiSorce.setSourceName(source);//渠道名称 tongjiSorce.setUv(uv);//uv
 * tongjiSorce.setAppNum(appnum);//真实的申请数
 * tongjiSorce.setAppNum1(appnum*discount1/100);//折扣后的申请数
 * tongjiSorce.setCvr(cvr);//转化率 tongjiSorce.setSumappnum(sumappnum);//点过甲方总的人数
 * listto.add(tongjiSorce); } } } //公司名选择的是全部 渠道选择的不是全部 else
 * if((company.length>1)&&(sourceName.length==1)) {
 * 
 * } //公司名选择的不是全部 渠道选择的是全部 else if((company.length==1)&&(sourceName.length>1)) {
 * 
 * } //公司名选择的不是全部 渠道选择的不是全部 else if((company.length==1)&&(sourceName.length==1))
 * {
 * 
 * } }
 */

// 后台管理---关联查询统计所有信息，含分页
/*
 * @ResponseBody
 * 
 * @RequestMapping("/queryAllPage1") public Map<String,Object>
 * queryAllPage1(Integer page,String string){ string = string.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] company= string.split(",");
 * PageUtil pageUtil=null; List<Source> listsource=new ArrayList<>();
 * List<SourceTongji> listsourceTongji=new ArrayList<>();//刚开始查询出来的数据
 * List<SourceTongji> listsourceTongjifor=new ArrayList<>();//经过封装后的数据
 * List<SourceTongji> listsourceTongjitoListPage=new ArrayList<>();//最终的数据
 * if(company.length==1) {
 * 
 * System.out.println("company.length==1");
 * 
 * listsource=intMerchantService.queryAll(company[0]);//查询出所有的渠道信息 int
 * totalCount=intTongjiService.pageCount(company[0]);//该方法是查询统计总条数 pageUtil=new
 * PageUtil(page,2,totalCount); if(page<1) { page=1; } else
 * if(page>pageUtil.getTotalPageCount()) { if(totalCount==0) {
 * page=pageUtil.getTotalPageCount()+1; }else {
 * page=pageUtil.getTotalPageCount(); } } int
 * pages=(page-1)*pageUtil.getPageSize(); pageUtil.setPage(pages);
 * 
 * listsourceTongji=intTongjiService.queryAllPage(company[0],pageUtil.getPage(),
 * pageUtil.getPageSize()); for (int i = 0; i < listsourceTongji.size(); i++) {
 * int pv=intTongjiService.queryPV(company[0],
 * listsourceTongji.get(i).getSourceName()); int
 * uv=intTongjiService.queryUV(company[0],
 * listsourceTongji.get(i).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listsourceTongji.get(i).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listsourceTongji.get(i).getId());
 * sourceTongji.setDate(listsourceTongji.get(i).getDate());
 * sourceTongji.setBusinessName(listsourceTongji.get(i).getBusinessName());
 * sourceTongji.setSourceName(listsourceTongji.get(i).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%");
 * listsourceTongjitoListPage.add(sourceTongji); } pageUtil=new
 * PageUtil(page,2,totalCount); } else if(company.length>1) {
 * 
 * System.out.println("company.length>1");
 * 
 * List<Source> listsourcefor=null; for (int i = 0; i < company.length; i++) {
 * listsourcefor=intMerchantService.queryAll(company[i]);//查询出所有的渠道信息
 * listsource.addAll(listsourcefor);
 * 
 * listsourceTongji=intTongjiService.queryAllPage1(company[i]); for (int j = 0;
 * j < listsourceTongji.size(); j++) { int
 * pv=intTongjiService.queryPV(company[i],
 * listsourceTongji.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i],
 * listsourceTongji.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listsourceTongji.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listsourceTongji.get(j).getId());
 * sourceTongji.setDate(listsourceTongji.get(j).getDate());
 * sourceTongji.setBusinessName(listsourceTongji.get(j).getBusinessName());
 * sourceTongji.setSourceName(listsourceTongji.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listsourceTongjifor.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listsourceTongjifor.size(); i++) {
 * System.out.println(listsourceTongjifor.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listsourceTongjifor,page,2);
 * listsourceTongjitoListPage.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount()); }
 * HashMap<String,Object> map=new HashMap<>(); map.put("listsource",
 * listsource);
 * map.put("listsourceTongjitoListPage",listsourceTongjitoListPage);
 * map.put("pageutil", pageUtil); map.put("company", company); return map; }
 * //后台管理---通过渠道名称 查询统计表所有信息，含分页
 * 
 * @ResponseBody
 * 
 * @RequestMapping("/queryAllPageBySourceName") public Map<String,Object>
 * queryAllPageBySourceName(Integer page,String string,String string1){ string =
 * string.replaceAll("\"", "").replace("[","").replace("]",""); String []
 * company= string.split(","); string1 = string1.replaceAll("\"",
 * "").replace("[","").replace("]",""); String [] sourceName=
 * string1.split(","); PageUtil pageUtil=null; List<SourceTongji>
 * listSourceTongjione=new ArrayList<>(); List<SourceTongji>
 * listSourceTongjitwo=new ArrayList<>(); List<SourceTongji> listone=new
 * ArrayList<>(); List<SourceTongji> listtwo=new ArrayList<>();//对集合进行处理后的集合
 * 最终集合 // List<SourceTongji> listoneto=new ArrayList<>(); //公司名选择的是全部 渠道选择的是全部
 * if((company.length>1)&&(sourceName.length>1)) {
 * 
 * System.out.println("第一个if："+"company.length>1-----sourceName.length>1");
 * 
 * for (int i = 0; i < company.length; i++) { for (int j = 0; j <
 * sourceName.length; j++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[j]);
 * for (int k = 0; k < listone.size(); k++) { int
 * pv=intTongjiService.queryPV(company[i], listone.get(k).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i], listone.get(k).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listone.get(k).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(k).getId());
 * sourceTongji.setDate(listone.get(k).getDate());
 * sourceTongji.setBusinessName(listone.get(k).getBusinessName());
 * sourceTongji.setSourceName(listone.get(k).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * } listSourceTongjitwo.addAll(listSourceTongjione); }
 * 
 * for (int i = 0; i < listSourceTongjitwo.size(); i++) {
 * System.out.println(listSourceTongjitwo.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjitwo,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的是全部 渠道选择的不是全部 else if((company.length>1)&&(sourceName.length==1)) {
 * 
 * System.out.println("第二个if："+"company.length>1-----sourceName.length==1");
 * 
 * for (int i = 0; i < company.length; i++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[0]);
 * 
 * for (int j = 0; j < listone.size(); j++) { int
 * pv=intTongjiService.queryPV(company[i], listone.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[i], listone.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[i],
 * listone.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(j).getId());
 * sourceTongji.setDate(listone.get(j).getDate());
 * sourceTongji.setBusinessName(listone.get(j).getBusinessName());
 * sourceTongji.setSourceName(listone.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listSourceTongjione.size(); i++) {
 * System.out.println(listSourceTongjione.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的不是全部 渠道选择的是全部 else if((company.length==1)&&(sourceName.length>1)) {
 * 
 * System.out.println("第三个if："+"company.length==1-----sourceName.length>1");
 * 
 * for (int i = 0; i < sourceName.length; i++) {
 * listone=intTongjiService.queryAllPageBySourceName1(company[0],sourceName[i]);
 * 
 * for (int j = 0; j < listone.size(); j++) { int
 * pv=intTongjiService.queryPV(company[0], listone.get(j).getSourceName()); int
 * uv=intTongjiService.queryUV(company[0], listone.get(j).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listone.get(j).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(j).getId());
 * sourceTongji.setDate(listone.get(j).getDate());
 * sourceTongji.setBusinessName(listone.get(j).getBusinessName());
 * sourceTongji.setSourceName(listone.get(j).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listSourceTongjione.add(sourceTongji); }
 * }
 * 
 * for (int i = 0; i < listSourceTongjione.size(); i++) {
 * System.out.println(listSourceTongjione.get(i)+"整合后的集合"); }
 * 
 * System.out.println("传进工具类的page"+page);
 * 
 * ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
 * listtwo.addAll(listPageUtil.getData());
 * 
 * pageUtil=new PageUtil(listPageUtil.getCurrentPage(),
 * listPageUtil.getPageSize(),listPageUtil.getTotalCount());
 * 
 * } //公司名选择的不是全部 渠道选择的不是全部 else if((company.length==1)&&(sourceName.length==1))
 * {
 * 
 * System.out.println("第四个if："+"company.length==1-----sourceName.length==1");
 * 
 * int
 * totalCount=intTongjiService.pageCountBySourceName(company[0],sourceName[0]);/
 * /该方法是查询统计总条数 pageUtil=new PageUtil(page,2,totalCount); if(page<1) { page=1; }
 * else if(page>pageUtil.getTotalPageCount()) { if(totalCount==0) {
 * page=pageUtil.getTotalPageCount()+1; }else {
 * page=pageUtil.getTotalPageCount(); } } int
 * pages=(page-1)*pageUtil.getPageSize(); pageUtil.setPage(pages);
 * 
 * listone=intTongjiService.queryAllPageBySourceName(company[0],sourceName[0],
 * pageUtil.getPage(),pageUtil.getPageSize()); for (int i = 0; i <
 * listone.size(); i++) { int pv=intTongjiService.queryPV(company[0],
 * listone.get(i).getSourceName()); int uv=intTongjiService.queryUV(company[0],
 * listone.get(i).getSourceName()); int
 * appnum=intTongjiService.queryApplicationNumber(company[0],
 * listone.get(i).getSourceName()); SourceTongji sourceTongji=new
 * SourceTongji(); sourceTongji.setId(listone.get(i).getId());
 * sourceTongji.setDate(listone.get(i).getDate());
 * sourceTongji.setBusinessName(listone.get(i).getBusinessName());
 * sourceTongji.setSourceName(listone.get(i).getSourceName());
 * sourceTongji.setPv(pv); sourceTongji.setUv(uv);
 * sourceTongji.setApplicationNumber(appnum);
 * sourceTongji.setCvr(appnum/uv+"%"); listtwo.add(sourceTongji); } pageUtil=new
 * PageUtil(page,2,totalCount); }
 * 
 * HashMap<String, Object> map=new HashMap<>();
 * map.put("listSourceTongjitwo",listtwo); map.put("pageutil", pageUtil); return
 * map; }
 */
