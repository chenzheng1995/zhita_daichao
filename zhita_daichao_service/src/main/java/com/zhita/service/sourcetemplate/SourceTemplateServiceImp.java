package com.zhita.service.sourcetemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceTemplateMapper;

@Service
public class SourceTemplateServiceImp implements SourceTemplateService{

	@Autowired
	SourceTemplateMapper SourceTemplateMapper;
}
