package com.zhita.service.sourcetemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceTemplateMapper;
import com.zhita.model.manage.SourceTemplate;

@Service
public class SourceTemplateServiceImp implements SourceTemplateService{

	@Autowired
	SourceTemplateMapper SourceTemplateMapper;

	@Override
	public List<SourceTemplate> getTemplate() {
		List<SourceTemplate> list = SourceTemplateMapper.getTemplate ();
		return list;
	}

	@Override
	public Integer getid(String templateName) {
		Integer number = SourceTemplateMapper.getid(templateName);
		return number;
	}

	@Override
	public void setTemplate(String templateName) {
		SourceTemplateMapper.setTemplate(templateName);
		
	}

	@Override
	public String getTemplateName(Integer oldTemplateId) {
		String oldTemplateName = SourceTemplateMapper.getTemplateName(oldTemplateId);
		return oldTemplateName;
	}
}
