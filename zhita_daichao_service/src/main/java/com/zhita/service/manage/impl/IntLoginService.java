package com.zhita.service.manage.impl;

import com.zhita.model.manage.ManageLogin;

public interface IntLoginService {
	 ManageLogin findFormatByLoginName(String userName);
}
