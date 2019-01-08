package com.zhita.service.blacklist;

public interface BlacklistService {

	int setblacklist(int userId, String name, String idCard, String phone, String creationTime);

}
