package com.zhita.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.NoticeMapper;

@Service(value="NoticeServiceImp")
public class NoticeServiceImp implements NoticeService{
	@Autowired
	NoticeMapper noticeMapper;

	@Override
	public List<String> getname() {
		List<String> namelist = noticeMapper.getname();
		return namelist;
	}

	@Override
	public List<String> getmoney() {
		List<String> moneylist = noticeMapper.getmoney();
		return moneylist;
	}

}
