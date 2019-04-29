package com.zhita.service.sourcetemplate;

import java.util.List;

import com.zhita.model.manage.SourceTemplate;

public interface SourceTemplateService {

	List<SourceTemplate> getTemplate();

	Integer getid(String templateName);

	void setTemplate(String templateName);

	String getTemplateName(Integer oldTemplateId);

}
