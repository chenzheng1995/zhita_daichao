package com.zhita.dao.manage;

import java.math.BigDecimal;
import java.util.List;

import com.zhita.model.manage.DayBill;
import com.zhita.model.manage.IncomeBill;

public interface IncomeBillMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeBill record);

    int insertSelective(IncomeBill record);

    IncomeBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeBill record);

    int updateByPrimaryKey(IncomeBill record);
    
    //添加收入账单信息
    int addIncomeBill(IncomeBill incomeBill);
    
    //通过时间模糊查询   当前用户  当前月 所有收入金额总和
    BigDecimal queryTotalMoney(Integer userid,String time, String company);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的收入详细信息
    List<IncomeBill> queryAllByTimeLike(Integer userid,String time, String company);
    
    //通过时间准确查询出当前用户  当前月 每一天  的收入详细信息
    List<IncomeBill> queryAllByTime(Integer userid,String time, String company);
    
    //通过时间模糊查询出当前用户  当前月 每一天  的收入总和
    List<DayBill> querySumByTimeLike(Integer userid,String time, String company);
    
}