package com.zhita.service.newstype;

import java.util.List;

public interface NewsTypeService {

	Integer getTypeId(String typename, String company);

	List<String> getnewstype(String company);

	String gettypename(int typeid);

}
