package com.zhita.service.login;

import com.zhita.model.manage.ManageLogin;

public interface IntLoginService {
	ManageLogin findFormatByLoginName(String userName);
}
