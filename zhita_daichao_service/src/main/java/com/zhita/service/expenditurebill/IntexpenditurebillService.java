package com.zhita.service.expenditurebill;

import java.math.BigDecimal;
import java.util.List;

import com.zhita.model.manage.ExpenditureBill;

public interface IntexpenditurebillService {
	
    //添加支出账单信息
    public int addExpenditureBill(ExpenditureBill expenditureBill);
    
    //通过时间模糊查询   当前用户  当前月 所有支出金额总和
    public BigDecimal queryTotalMoney(Integer userid,String time);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出详细信息
    public List<ExpenditureBill> queryAllByTimeLike(Integer userid,String time);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出总和
    public BigDecimal querySumByTimeLike(Integer userid,String time);
}
