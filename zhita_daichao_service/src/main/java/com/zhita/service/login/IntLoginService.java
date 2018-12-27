package com.zhita.service.login;

import com.zhita.model.manage.User;

public interface IntLoginService {
	User findFormatByLoginName(String phone, String openId);

	int insertfootprint(String phone, String nickName, String openId, String registrationTime);
}
