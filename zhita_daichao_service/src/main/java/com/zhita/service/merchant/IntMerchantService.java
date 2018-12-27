package com.zhita.service.merchant;

import java.util.List;

import com.zhita.model.manage.Source;

public interface IntMerchantService {
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAll();
}
