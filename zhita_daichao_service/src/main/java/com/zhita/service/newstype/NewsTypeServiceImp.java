package com.zhita.service.newstype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.NewsTypeMapper;

@Service(value="newsTypeServiceImp")
public class NewsTypeServiceImp implements NewsTypeService{

	@Autowired
    NewsTypeMapper newsTypeMapper;

	@Override
	public Integer getTypeId(String typename, String company) {
		Integer typeId = newsTypeMapper.getTypeId(typename,company);
		return typeId;
	}

	@Override
	public List<String> getnewstype(String company) {
		List<String> typelist = new ArrayList<>();
		typelist = newsTypeMapper.getnewstype(company);
		return typelist;
	}

}
