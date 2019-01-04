package com.zhita.service.incomebill;

import java.math.BigDecimal;
import java.util.List;

import com.zhita.model.manage.IncomeBill;

public interface IntincomebillService {
	
    //添加收入账单信息
    public int addIncomeBill(IncomeBill incomeBill);
    //通过时间模糊查询   当前用户  当前月 所有收入金额总和
    public BigDecimal queryTotalMoney(Integer userid,String time);
    //通过时间模糊查询出当前用户  当前月 每一天  的收入详细信息
    public List<IncomeBill> queryAllByTimeLike(Integer userid,String time);
    //通过时间模糊查询出当前用户  当前月 每一天  的收入总和
    public BigDecimal querySumByTimeLike(Integer userid,String time);
}
