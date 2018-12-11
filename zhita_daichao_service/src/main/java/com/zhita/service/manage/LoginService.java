package com.zhita.service.manage;

import com.zhita.model.manage.ManageLogin;

public interface LoginService {

	ManageLogin findFormatByLoginName(String userName);

}
