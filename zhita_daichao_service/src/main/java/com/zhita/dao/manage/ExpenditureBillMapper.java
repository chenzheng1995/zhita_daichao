package com.zhita.dao.manage;

import java.math.BigDecimal;
import java.util.List;

import com.zhita.model.manage.DayBill;
import com.zhita.model.manage.ExpenditureBill;

public interface ExpenditureBillMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(ExpenditureBill record);

    int insertSelective(ExpenditureBill record);

    ExpenditureBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpenditureBill record);

    int updateByPrimaryKey(ExpenditureBill record);
    
    //添加支出账单信息
    int addExpenditureBill(ExpenditureBill expenditureBill);
    
    //通过时间模糊查询   当前用户  当前月 所有支出金额总和
    BigDecimal queryTotalMoney(Integer userid,String time);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出详细信息
    List<ExpenditureBill> queryAllByTimeLike(Integer userid,String time);
    
    //通过时间准确查询出当前用户  当前月 每一天  的支出详细信息
    List<ExpenditureBill> queryAllByTime(Integer userid,String time);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出总和
    List<DayBill> querySumByTimeLike(Integer userid,String time);
}