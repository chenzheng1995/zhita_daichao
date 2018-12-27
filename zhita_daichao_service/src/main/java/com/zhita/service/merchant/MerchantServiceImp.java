package com.zhita.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceMapper;
import com.zhita.model.manage.Source;

@Service
public class MerchantServiceImp implements IntMerchantService{
	@Autowired
	private SourceMapper sourceMapper;
	
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAll(){
    	List<Source> list=sourceMapper.queryAll();
    	return list;
    }
}
